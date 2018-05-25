package com.jandoant.stp_entities;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
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

    public StpCartesianPoint[] getOuterEdgePointsUVW() {

        /*
        Es sollen alle Eckpunkte des äußeren Polygons gefunden werden.
        Diese Eckpunkte werden in uvw-Koordinaten transformiert und als Array zurückgegeben.
        */

        // alle Eckpukte des äußeren Polygons finden
        StpCartesianPoint[] outerEdgePointsXYZ = this.getOuterEdgePointsXYZ();

        // alle Eckpunkte in uvw transformieren und zurück geben
        return this.transformEdgePointsToUVW(outerEdgePointsXYZ);

    }

    private StpCartesianPoint[] getOuterEdgePointsXYZ() {

        /*
        Es sollen alle Eckpunkte des äußeren Polygons gefunden werden.
        Diese Eckpunkte werden in xyz-Koordinaten als Array zurückgegeben.
        */

        // alle Kanten des äußeren Polygons finden
        ArrayList<StpOrientedEdge> outerEdges = findOuterEdges();

        // alle Eckpunkte des äußeren Polygons aus diesen Kanten extrahieren
        return getEdgePointsOfPolygon(outerEdges);

    }

    public StpCartesianPoint[] getInnerEdgePointsXYZ() {

        //Todo: Implement

        return null;
    }

    private StpCartesianPoint[] transformEdgePointsToUVW(StpCartesianPoint[] edgePointsOfPolygon) {

        /*
        Jeder einzelne Eckpunkt des Polygons muss in uvw transformiert werden.
        Dazu ist es auch notwendig den Ortsvektor der Ebene vorher abzuziehen.
        Die transformierten Punkte werden in ein neues Ergebnis-Array geschrieben und zurückgegeben
         */

        StpCartesianPoint[] result = new StpCartesianPoint[edgePointsOfPolygon.length];

        for (int i = 0; i < edgePointsOfPolygon.length; i++) {

            // auf welcher Ebene befiondet sich das Polygon?
            StpPlane plane = (StpPlane) this.faceGeometry;

            // vorher Ortsvektor dieser Ebene abziehen
            StpVector locationVector = plane.getLocationVector();
            edgePointsOfPolygon[i].move(locationVector, -locationVector.getMagnitude());

            // transformieren mithilfe der entsprechenden Transformationsmatrix der Ebene
            result[i] = edgePointsOfPolygon[i].transform(plane.getXYZtoUVWTransformationMatrix());
        }

        //in uvw transformierte Punkte zurückgeben
        return result;
    }

    private StpCartesianPoint[] getEdgePointsOfPolygon(ArrayList<StpOrientedEdge> outerEdges) {

        /*
        Aus den outer orientedEdges sollen die EdgePoints des Polygons extrahiert werden.
        Dafür werden die jeweiligen Anfangspukte der Edges gesucht und in ein Array geschrieben.
        Das Ergebnis-Array hat die gleiche Größe, wie die Anzahl der Edges, da es genauso viele Eckpunkte wie Edges gibt.
         */

        StpCartesianPoint[] result = new StpCartesianPoint[outerEdges.size()];

        for (int i = 0; i < outerEdges.size(); i++) {

            StpCartesianPoint startingPointOfEdge;

            // falls die orientation false ist, müssen Start- und EndVertex umgedreht werden
            if (outerEdges.get(i).orientation == true) {
                startingPointOfEdge = outerEdges.get(i).getEdgeStartVertex().convertToCartesianPoint();
            } else {
                startingPointOfEdge = outerEdges.get(i).getEdgeEndVertex().convertToCartesianPoint();
            }

            result[i] = startingPointOfEdge;
        }

        return result;
    }

    private ArrayList<StpOrientedEdge> findOuterEdges() {

        /*
        Es sollen alle Kanten des äußeren Polygons gefunden werden.
         */

        // finden de äußeren Umrandung des äußeren Polygons
        StpFaceBound faceOuterBound = this.getBounds().get(0);

        // finden des Kantenverbundes des äüßeren Polygons
        StpEdgeLoop outerEdgeLoop = (StpEdgeLoop) faceOuterBound.getBound();

        // Rückgabe einer Liste aller Kanten des äußeren Polygons
        return outerEdgeLoop.getEdgesList();

    }

    public ArrayList<StpCartesianPoint> getPointCloud() {
        return pointCloud;
    }

    public ArrayList<StpCartesianPoint> mesh2DPolygonUVW(StpCartesianPoint[] outerEdgePointsUVW, double distanceOfPoints) {

        /*
        Soll ein 2D-Polygon aus den Eckpunkten des Polygons erzeugen.
        Ermittlung der Bounding-Box des Polygons und füllen dieser Bounding-Box mit Punkteraster im  vorgegebenen Abstand.
         */

        // erstellen des 2D-Polygons in uvw Kooridinaten

        // die Skalierung ist notwendig, weil Polygon nur Integer annimmt

        int numOfPoints = outerEdgePointsUVW.length;

        // 2D Polygon erstellen

        double[] coordinates = new double[numOfPoints * 2];

        for (int i = 0; i < numOfPoints; i++) {

            double u = outerEdgePointsUVW[i].getX();
            double v = outerEdgePointsUVW[i].getY();

            // apply u
            coordinates[2 * i] = u;

            //appy v
            coordinates[2 * i + 1] = v;

        }

        ArrayList<StpVector> edgeVectors = new ArrayList<>();

        for (int i = 0; i < numOfPoints; i++) {

            //build the edgeVectors
            StpVector v0 = new StpVector(-1, "", outerEdgePointsUVW[i].getX(), outerEdgePointsUVW[i].getY(), 0);
            StpVector v1;

            if (i + 1 == numOfPoints) {
                v1 = new StpVector(-1, "", outerEdgePointsUVW[0].getX(), outerEdgePointsUVW[0].getY(), 0);

            } else {
                v1 = new StpVector(-1, "", outerEdgePointsUVW[i + 1].getX(), outerEdgePointsUVW[i + 1].getY(), 0);

            }

            edgeVectors.add(StpVector.subtract(v1, v0));

        }

        //mesh the edges of the polygon

        ArrayList<Point2D> mesh = new ArrayList<>();

        for (StpVector v : edgeVectors) {

            double sumOfTranslation = 0;
            double totalDistance = v.getMagnitude();
            int i = edgeVectors.indexOf(v);

            StpCartesianPoint currPoint = outerEdgePointsUVW[i];

            while (sumOfTranslation < totalDistance) {

                mesh.add(new Point2D(currPoint.getX(), currPoint.getY()));

                currPoint.move(v, distanceOfPoints);

                sumOfTranslation += distanceOfPoints;
            }

            //move Location Vector back
            outerEdgePointsUVW[i].move(v, -v.getMagnitude());

        }

        Polygon polygon = new Polygon(coordinates);

        //bounding Box des Polygons
        Bounds boundingBox = polygon.getBoundsInLocal();

        double uMin = boundingBox.getMinX();
        double uMax = boundingBox.getMaxX();

        double vMin = boundingBox.getMinY();
        double vMax = boundingBox.getMaxY();

        //mesh inside of polygon
        double v = vMin;

        while (v <= vMax) {

            double u = uMin;

            while (u <= uMax) {

                Point2D pt = new Point2D(u, v);

                if (polygon.contains(pt) && !mesh.contains(pt)) {
                    mesh.add(pt);
                }

                u += distanceOfPoints;
            }

            v += distanceOfPoints;
        }

        // convert all Point2D to StpCartesianPoints


        ArrayList<StpCartesianPoint> result = new ArrayList<>();

        for (Point2D pt : mesh) {
            result.add(StpCartesianPoint.convertToCartesianPoint(pt));
        }


        return result;

    }
}
