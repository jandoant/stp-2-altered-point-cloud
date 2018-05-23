package com.jandoant.stp_entities;

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

    public StpSurface getFaceGeometry() {
        return faceGeometry;
    }

    public void setFaceGeometry(StpSurface faceGeometry) {
        this.faceGeometry = faceGeometry;
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
            System.out.println(point);
        }

        System.out.println(pointCloud.size());

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

    public void meshPolygon(double distanceOfPoints) {

        //transformieren der x,y,z Koordinaten in uv Koordinaten

        //Polygon in uv Koordinaten erstellen

        //Netz von Punkten in uv Koordinaten erstellen

        //alle Punkte aus dem Netz löschen, die nicht innerhalb des Polygons liegen

        //Kanten diskretisieren

        //Punktewolke erstellen aus Netz und Punkten der Kanten

    }
}
