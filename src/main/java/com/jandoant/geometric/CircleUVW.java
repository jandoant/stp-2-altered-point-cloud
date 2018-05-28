package com.jandoant.geometric;

import com.jandoant.stp_entities.*;

/**
 * Klasse CircleUVW
 * Created by Jan Doant on 26.05.2018
 */
public class CircleUVW extends SurfaceUVW {

    //Attribute
    double radius;
    private StpCircle circle;
    private StpCartesianPoint centerPointXYZ;
    private StpCartesianPoint centerPointUVW;

    private StpCartesianPoint startingPointUVW;

    //Konstruktor
    public CircleUVW(StpFaceBound bound, StpPlane plane, boolean isPositive) {
        super(bound, plane, isPositive);

        initCircle();
    }

    @Override
    public boolean contains(StpCartesianPoint pt) {

        double distance = StpCartesianPoint.distance(this.centerPointUVW, pt);

        return distance <= this.radius;

    }

    private void initCircle() {

        this.findCircle();
        this.findRadius();

        this.findCenterPointXYZ();
        this.centerPointUVW = this.transformPointToUVW(this.centerPointXYZ);

        this.startingPointUVW = new StpCartesianPoint(-1, "", this.centerPointUVW.getX() + this.radius, this.centerPointUVW.getY(), this.centerPointUVW.getZ());
    }

    private void findCircle() {
        StpEdgeLoop edgeLoop = (StpEdgeLoop) this.bound.getBound();
        StpOrientedEdge orientedEdge = edgeLoop.getEdgesList().get(0);
        StpEdgeCurve edgeCurve = (StpEdgeCurve) orientedEdge.getEdgeElement();

        this.circle = (StpCircle) edgeCurve.getEdgeGeometry();
    }

    private void findCenterPointXYZ() {

        this.centerPointXYZ = circle.getCenterVector().transformToCartesianPoint();
    }

    private void findRadius() {
        this.radius = this.circle.getRadius();
    }

    //Methoden
    public void mesh(int numOfAngularSegments, int numOfRings) {

        // 1.Inkremente berechnen
        double angularIncrement = 360.0 / (double) numOfAngularSegments;
        double radialIncrement = this.radius / (double) numOfRings;

        StpVector pivot = this.centerPointUVW.convertToVector();
        StpVector axis = new StpVector(-1, "", 0, 0, 1);

        StpVector cursor = StpVector.subtract(startingPointUVW.convertToVector(), centerPointUVW.convertToVector());

        //2. Ringe bilden
        double sumOfRadialTranslation = 0;
        while (sumOfRadialTranslation < this.radius) {

            createSingleRing(angularIncrement, pivot, axis, cursor);

            cursor.normalize();
            cursor.scale(this.radius - radialIncrement);

            sumOfRadialTranslation += radialIncrement;
        }
    }

    private void createSingleRing(double angularIncrement, StpVector pivot, StpVector axis, StpVector cursor) {

        double sumOfAngle = 0;

        while (sumOfAngle < 360.0) {

            //write a new Point to the point cloud before the rotation
            this.meshUVW.add(cursor.transformToCartesianPoint());

            //rotate
            cursor.rotate(angularIncrement, pivot, axis);

            sumOfAngle += angularIncrement;

        }

        //rotate vector to beginning position
        cursor.rotate(-sumOfAngle, pivot, axis);
    }

}
