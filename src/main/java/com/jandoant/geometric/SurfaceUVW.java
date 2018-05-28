package com.jandoant.geometric;

import com.jandoant.stp_entities.*;

import java.util.ArrayList;

/**
 * Klasse SurfaceUVW
 * Created by Jan Doant on 26.05.2018
 */
public abstract class SurfaceUVW {

    //Attribute
    protected StpFaceBound bound;
    protected StpPlane plane;
    protected boolean isPositive;

    protected ArrayList<StpOrientedEdge> edges;
    protected StpCartesianPoint[] edgePointsXYZ;
    protected StpCartesianPoint[] edgePointsUVW;

    protected ArrayList<StpCartesianPoint> meshUVW;

    //Konstruktor

    public SurfaceUVW(StpFaceBound bound, StpPlane plane, boolean isPositive) {
        this.bound = bound;
        this.plane = plane;
        this.isPositive = isPositive;

        this.init();

        this.meshUVW = new ArrayList<>();
    }

    //Methoden
    protected void init() {
        // 1. Finden aller Kanten dieser Umrandung
        this.findEdges();

        // 2. Finden der Eckpunkte dieser Umrandung in xyz-Koordinaten
        this.getEdgePointsOfBoundXYZ();

        // 3. Transformieren der Eckpunkte in uvw-Koordinaten
        this.transformAllPointsToUVW();
    }

    protected void findEdges() {

        /*
        Es sollen alle Kanten gefunden werden und als Liste zurückgegeben werden.
        Falls es sich bei der Umrandung um einen Kreis handelt, wird eine Liste mit einer Kante zurückgegeben.
        Falls es sich bei der Umrandung um ein Polygon handelt, wird eine Liste mit mindestens drei Kanten zurückgegeben
         */

        // 1. Finden des Kantenverbundes dieser FaceBound
        StpEdgeLoop edgeLoop = (StpEdgeLoop) this.bound.getBound();

        // 2. Rückgabe einer Liste aller Kanten dieses Kantenverbundes
        this.edges = edgeLoop.getEdgesList();

    }

    protected void getEdgePointsOfBoundXYZ() {

        /*
        Aus den outer orientedEdges sollen die EdgePoints des Polygons extrahiert werden.
        Dafür werden die jeweiligen Anfangspukte der Edges gesucht und in ein Array geschrieben.
        Das Ergebnis-Array hat die gleiche Größe, wie die Anzahl der Edges, da es genauso viele Eckpunkte wie Edges gibt.
         */

        this.edgePointsXYZ = new StpCartesianPoint[this.edges.size()];

        for (int i = 0; i < this.edges.size(); i++) {

            StpCartesianPoint startingPointOfEdge;

            // falls die orientation false ist, müssen Start- und EndVertex umgedreht werden
            if (this.edges.get(i).getOrientation() == true) {
                startingPointOfEdge = this.edges.get(i).getEdgeStartVertex().convertToCartesianPoint();
            } else {
                startingPointOfEdge = this.edges.get(i).getEdgeEndVertex().convertToCartesianPoint();
            }

            this.edgePointsXYZ[i] = startingPointOfEdge;
        }
    }

    private void transformAllPointsToUVW() {

        this.edgePointsUVW = new StpCartesianPoint[this.edgePointsXYZ.length];

        for (int i = 0; i < this.edgePointsXYZ.length; i++) {

            this.edgePointsUVW[i] = this.transformPointToUVW(this.edgePointsXYZ[i]);
        }
    }

    protected StpCartesianPoint transformPointToUVW(StpCartesianPoint pointXYZ) {
        // vorher Ortsvektor dieser Ebene abziehen
        StpVector locationVector = this.plane.getLocationVector();
        pointXYZ.move(locationVector, -locationVector.getMagnitude());
        // transformieren mithilfe der entsprechenden Transformationsmatrix der Ebene
        return pointXYZ.transform(this.plane.getXYZtoUVWTransformationMatrix());

    }

    public ArrayList<StpCartesianPoint> getMeshUVW() {
        return this.meshUVW;
    }

    public abstract boolean contains(StpCartesianPoint pt);
}
