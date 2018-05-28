package com.jandoant.stp_entities;

import com.jandoant.geometric.SurfaceUVW;
import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;

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

    ArrayList<StpCartesianPoint> pointCloud;

    //Konstruktor
    public StpFaceSurface(int id, String name, ArrayList<Integer> boundsIds, int faceGeometryId, Boolean sameSense) {
        super(id, name, boundsIds);
        this.faceGeometryId = faceGeometryId;
        this.sameSense = sameSense;

        this.pointCloud = new ArrayList<>();
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

        this.pointCloud.addAll(positiveBound.getMeshUVW());
    }

    private void meshEdgesOf2DPolygon(Polygon polygon, double distanceOfPoints) {

        /*
        Diskretisiert die Kanten eines Polygons und fügt die Ergebnispunkte der Punktewolke hinzu.
         */

        // 1. Liste von Eckpunkten des Polygons erstellen
        ObservableList<Double> edgeCoordinates = polygon.getPoints();

        ArrayList<StpCartesianPoint> edgePoints = new ArrayList<>();

        for (int i = 0; i < edgeCoordinates.size() / 2; i++) {

            double u = edgeCoordinates.get(2 * i);
            double v = edgeCoordinates.get(2 * i + 1);

            StpCartesianPoint pt = new StpCartesianPoint(-1, "", u, v, 0);

            edgePoints.add(pt);

        }

        // 2. Kanten des Polygons als Vektoren beschreiben (dazu ist die Liste der Eckpunkte nötig)
        ArrayList<StpVector> edgeVectors = new ArrayList<>();

        for (int i = 0; i < edgePoints.size(); i++) {

            StpVector v0 = new StpVector(-1, "", edgePoints.get(i).getX(), edgePoints.get(i).getY(), 0);

            StpVector v1;

            if (i + 1 == edgePoints.size()) {
                v1 = new StpVector(-1, "", edgePoints.get(0).getX(), edgePoints.get(0).getY(), 0);

            } else {
                v1 = new StpVector(-1, "", edgePoints.get(i + 1).getX(), edgePoints.get(i + 1).getY(), 0);

            }

            edgeVectors.add(StpVector.subtract(v1, v0));

        }

        // 3. Kanten des Polygons meshen und die Punkte in Point Cloud schreiben
        for (StpVector v : edgeVectors) {

            double sumOfTranslation = 0;
            double totalDistance = v.getMagnitude();
            int i = edgeVectors.indexOf(v);

            StpCartesianPoint currPoint = edgePoints.get(i);

            while (sumOfTranslation < totalDistance) {

                this.pointCloud.add(new StpCartesianPoint(-1, "", currPoint.getX(), currPoint.getY(), currPoint.getZ()));

                currPoint.move(v, distanceOfPoints);

                sumOfTranslation += distanceOfPoints;
            }

            //move Location Vector back
            currPoint.move(v, -v.getMagnitude());

        }

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

    public void meshCylinder(int numOfRadialSegments, int numOfRings) {

        System.out.println("Hey here a cylinder gets meshed");

        //find the circular edge curves
        StpEdgeCurve edgeCurve0 = findCircularEdgeCurve(0);
        StpEdgeCurve edgeCurve1 = findCircularEdgeCurve(1);

        //find the Ortsvectors to rotate
        StpVector startingVector0 = findVectorToRotate(edgeCurve0);
        StpVector startingVector1 = findVectorToRotate(edgeCurve1);

        //find the midpoints  of the circles
        StpVector centerVector0 = findCenterVectorOfCircle(edgeCurve0);
        StpVector centerVector1 = findCenterVectorOfCircle(edgeCurve1);

        //build the axis
        StpVector axis = StpVector.subtract(centerVector1, centerVector0);

        //find out the increment of the movement
        double translateIncrement = axis.getMagnitude() / (double) (numOfRings - 1);

        System.out.println(translateIncrement);

        //find out the increment that each point has to rotate by
        double angleIncrement = 360.0 / (double) numOfRadialSegments;

        //first and nextRings but not the last ring to avoid rounding errors
        double sumOfTranslation = 0;

        while (sumOfTranslation < axis.getMagnitude() - 0.5 * translateIncrement) {

            createSingleRing(startingVector0, axis, centerVector0, angleIncrement);

            startingVector0.move(axis, translateIncrement);
            centerVector0.move(axis, translateIncrement);

            sumOfTranslation += translateIncrement;

        }

        //lastRing
        createSingleRing(startingVector1, axis, centerVector1, angleIncrement);

        for (StpCartesianPoint point : pointCloud) {
            //System.out.println(point);
        }

        //System.out.println(pointCloud.size());

    }

    private void createSingleRing(StpVector vectorToRotate, StpVector axis, StpVector pivot, double angleIncrement) {
        //create one ring of Points
        double sumOfAngle = 0;

        while (sumOfAngle < 360) {

            //write a new Point to the point cloud before the rotation
            addToPointCloud(vectorToRotate);

            //rotate
            vectorToRotate.rotate(angleIncrement, pivot, axis);

            sumOfAngle += angleIncrement;

        }
        //move vector to beginning position
        vectorToRotate.rotate(-sumOfAngle, pivot, axis);
    }

    private void addToPointCloud(StpVector vectorToRotate) {
        //write a new Point to the point cloud before the rotation
        StpCartesianPoint point = vectorToRotate.transformToCartesianPoint();
        pointCloud.add(point);
    }

    protected StpVector findCenterVectorOfCircle(StpEdgeCurve edgeCurve) {

        //extract the Circle
        StpCircle circle = (StpCircle) edgeCurve.getEdgeGeometry();

        //return the Center Vector of the Circle
        return circle.getCenterVector();
    }

    private StpVector findVectorToRotate(StpEdgeCurve edgeCurve) {

        //extract VertexPoint
        StpVertexPoint vertexPoint = (StpVertexPoint) edgeCurve.getEdgeStartVertex();

        //extract the CartesianPoint
        StpCartesianPoint pointToRotate = (StpCartesianPoint) vertexPoint.getVertexGeometry();

        //return the Ortsvector to rotate
        return new StpVector(-1, "", pointToRotate.getX(), pointToRotate.getY(), pointToRotate.getZ());

    }

    private StpEdgeCurve findCircularEdgeCurve(int positionOfFaceBoundInBoundsList) {

        //extract the FaceBound
        StpFaceBound facebound = this.bounds.get(positionOfFaceBoundInBoundsList);

        //extract the EdgeLoop
        StpEdgeLoop bound = (StpEdgeLoop) facebound.getBound();

        //extract the OrientedEdge
        StpOrientedEdge orientedEdge = bound.getEdgesList().get(0);

        //return the Circular Edge Curve
        return (StpEdgeCurve) orientedEdge.getEdgeElement();
    }

    public ArrayList<StpCartesianPoint> getPointCloud() {
        return pointCloud;
    }

    public void removeNegativeSurfaceUVW(SurfaceUVW negativeSurfaceUVW) {

        /*
        Löscht alle Punkte, die innerhalb eines negativen Polygons liegen.
        Fügt die diskretisierten Punkte der Kanten des negativen Polygons hinzu.
         */

        // 1. alle Punkte der Point Cloud löschen, die innerhalb der Kontur liegen
        for (StpCartesianPoint pt : this.pointCloud) {

            if (negativeSurfaceUVW.contains(pt)) {

                this.pointCloud.remove(pt);

            }
        }
        // 2. im Anschluss die Kanten des inneren Polygons meshen (der PointCloud hinzufügen)
        //only add the EdgePoints that I dont have already!!!!!

        for (StpCartesianPoint pt : negativeSurfaceUVW.getMeshUVW()) {

            if (!this.pointCloud.contains(pt)) {
                this.pointCloud.add(pt);
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
}
