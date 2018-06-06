package com.jandoant.stp_entities;

import com.jandoant.deformation.DeformationFunction;
import com.jandoant.geometric.SurfaceUVW;

import java.util.ArrayList;

/**
 * Klasse StpFaceSurface
 * Created by Jan Doant on 25.04.2018
 */
public abstract class StpFaceSurface extends StpFace {

    //Attribute
    int faceGeometryId;
    StpSurface faceGeometry;
    Boolean sameSense;

    ArrayList<StpCartesianPoint> localPointCloud;

    //Konstruktor
    public StpFaceSurface(int id, String name, ArrayList<Integer> boundsIds, int faceGeometryId, Boolean sameSense) {
        super(id, name, boundsIds);
        this.faceGeometryId = faceGeometryId;
        this.sameSense = sameSense;

        this.localPointCloud = new ArrayList<>();
    }

    //Methoden

    public StpSurface getFaceGeometry() {
        return faceGeometry;
    }

    public void setFaceGeometry(StpSurface faceGeometry) {
        this.faceGeometry = faceGeometry;
    }

    @Override
    public void convertFromIds(ArrayList<StpRepresentationItem> availableEntities) {

        super.convertFromIds(availableEntities);

        for (StpRepresentationItem entity : availableEntities) {
            if (this.faceGeometryId == entity.getId()) {
                this.faceGeometry = (StpSurface) entity;
                this.faceGeometry.convertFromIds(availableEntities);
            }
        }
    }

    public void addPositiveSurfaceUVW(SurfaceUVW positiveBound) {

        this.localPointCloud.addAll(positiveBound.getMeshUVW());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", faceGeometryId=" + faceGeometryId +
                ", faceGeometry=" + faceGeometry +
                ", sameSense=" + sameSense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpFaceSurface that = (StpFaceSurface) o;

        if (faceGeometryId != that.faceGeometryId) return false;
        if (faceGeometry != null ? !faceGeometry.equals(that.faceGeometry) : that.faceGeometry != null) return false;
        return sameSense.equals(that.sameSense);
    }

    public String getType() {
        return this.faceGeometry.getClass().getSimpleName();
    }

    public ArrayList<StpCartesianPoint> getLocalPointCloud() {
        return this.localPointCloud;
    }

    public void removeNegativeSurfaceUVW(SurfaceUVW negativeSurfaceUVW) {

        /*
        Löscht alle Punkte, die innerhalb eines negativen Polygons liegen.
        Fügt die diskretisierten Punkte der Kanten des negativen Polygons hinzu.
         */

        ArrayList<StpCartesianPoint> pointsToRemove = new ArrayList<>();

        // 1. alle Punkte der Point Cloud löschen, die innerhalb der Kontur liegen
        for (StpCartesianPoint pt : this.localPointCloud) {

            if (negativeSurfaceUVW.contains(pt)) {

                pointsToRemove.add(pt);

            }
        }
        this.localPointCloud.removeAll(pointsToRemove);

        // 2. im Anschluss die Kanten des inneren Polygons meshen (der PointCloud hinzufügen)
        //only add the EdgePoints that I dont have already!!!!!
        for (StpCartesianPoint pt : negativeSurfaceUVW.getMeshUVW()) {

            if (!this.localPointCloud.contains(pt)) {
                this.localPointCloud.add(pt);
            }

        }

    }

    public ArrayList<StpFaceBound> getPositiveBounds() {

        ArrayList<StpFaceBound> result = new ArrayList<>();

        for (StpFaceBound bound : this.getBounds()) {
            if (bound.getType().equals("StpFaceOuterBound")) {
                result.add(bound);
            }
        }

        return result;

    }

    public ArrayList<StpFaceBound> getNegativeBounds() {

        ArrayList<StpFaceBound> result = new ArrayList<>();

        for (StpFaceBound bound : this.getBounds()) {

            if (bound.getType().equals("StpFaceBound")) {
                result.add(bound);
            }
        }

        return result;

    }

    public void applyDeformationFunction(DeformationFunction df) {

        for (StpCartesianPoint pt : this.localPointCloud) {

            double newZ = df.f(pt.getX(), pt.getY());

            pt.setZ(newZ + pt.getZ());
        }
    }

    public void setLocalPointCloud(ArrayList<StpCartesianPoint> localPointCloud) {
        this.localPointCloud = localPointCloud;
    }

    public ArrayList<StpCartesianPoint> getPointCloudXYZ() {

        ArrayList<StpCartesianPoint> pointCloudXYZ = new ArrayList<>();

        //transform all points in the local localPointCloud, then give that list back
        if (this.getType().equals("StpPlane")) {

            StpPlane plane = (StpPlane) this.faceGeometry;

            for (StpCartesianPoint pt : this.localPointCloud) {

                pointCloudXYZ.add(pt.baseTransform(plane.getUVWToXYZTransformationMatrix()));
            }
        }

        if (this.getType().equals("StpCylindricalSurface")) {

            StpCylindricalSurface cylindricalSurface = (StpCylindricalSurface) this.faceGeometry;

            for (StpCartesianPoint pt : this.localPointCloud) {

                StpCartesianPoint ptUVW = pt.cylinderTransformToCartesian();
                pointCloudXYZ.add(ptUVW.baseTransform(cylindricalSurface.getUVWToXYZTransformationMatrix()));

            }
        }

        return pointCloudXYZ;

    }

    public String print() {

        String printString = "ADVANCED_FACE (" + this.getType() + ") with ID=" + this.id + "\n";

        for (StpCartesianPoint pt : this.getPointCloudXYZ()) {
            printString += pt.print(";") + "\n";
        }

        printString += "\n";

        return printString;
    }
}
