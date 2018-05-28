package com.jandoant.geometric;

import com.jandoant.stp_entities.StpCartesianPoint;
import com.jandoant.stp_entities.StpFaceBound;
import com.jandoant.stp_entities.StpPlane;
import com.jandoant.stp_entities.StpVector;
import javafx.geometry.Bounds;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

/**
 * Klasse PolygonUVW
 * Created by Jan Doant on 26.05.2018
 */
public class PolygonUVW extends SurfaceUVW {

    private Polygon polygon;

    //Konstruktor
    public PolygonUVW(StpFaceBound bound, StpPlane plane, boolean isPositive) {
        super(bound, plane, isPositive);

        this.polygon = new Polygon();
        this.initPolygon();
    }

    private void initPolygon() {
        // 4. Erstellen des äußeren Polygons in uvw-Koordinaten
        this.make2DPolygon();
    }

    //Methoden
    public void mesh(double distanceOfPoints) {

        // 5. Meshen des äußeren Poygons (Kanten und innen) und Hinzufügen der Punkte zur Point Cloud der Fläche
        // 5.1. Diskretisiern der Kanten -> Punkte zur Point Cloud hinzu
        this.addEdgeMesh(distanceOfPoints);

        if (this.isPositive) {
            // 5.2. Innenraum des Polygons meshen -> Punkte zur Point Cloud hinzu
            this.addInsideMesh(distanceOfPoints);
        }

    }

    private void make2DPolygon() {

        // 4. Build the 2D Polygon
        int numOfPoints = this.edgePointsUVW.length;

        double[] coordinates = new double[numOfPoints * 2];

        for (int i = 0; i < numOfPoints; i++) {

            double u = this.edgePointsUVW[i].getX();
            double v = this.edgePointsUVW[i].getY();

            // apply u
            coordinates[2 * i] = u;

            //appy v
            coordinates[2 * i + 1] = v;

        }

        this.polygon = new Polygon(coordinates);

    }

    private void addEdgeMesh(double distanceOfPoints) {

        /*
        Diskretisiert die Kanten eines Polygons und fügt die Ergebnispunkte der Punktewolke hinzu.
         */

        // 1. Kanten des Polygons als Vektoren beschreiben (dazu ist die Liste der Eckpunkte nötig)
        ArrayList<StpVector> edgeVectors = new ArrayList<>();

        for (int i = 0; i < this.edgePointsUVW.length; i++) {

            StpVector v0 = new StpVector(-1, "", this.edgePointsUVW[i].getX(), this.edgePointsUVW[i].getY(), 0);

            StpVector v1;

            if (i + 1 == this.edgePointsUVW.length) {
                v1 = new StpVector(-1, "", this.edgePointsUVW[0].getX(), this.edgePointsUVW[0].getY(), 0);

            } else {
                v1 = new StpVector(-1, "", this.edgePointsUVW[i + 1].getX(), this.edgePointsUVW[i + 1].getY(), 0);

            }

            edgeVectors.add(StpVector.subtract(v1, v0));

        }

        // 3. Kanten des Polygons meshen und die Punkte in Point Cloud schreiben
        for (StpVector v : edgeVectors) {

            double sumOfTranslation = 0;
            double totalDistance = v.getMagnitude();
            int i = edgeVectors.indexOf(v);

            StpCartesianPoint currPoint = this.edgePointsUVW[i];

            while (sumOfTranslation < totalDistance) {

                this.meshUVW.add(new StpCartesianPoint(-1, "", currPoint.getX(), currPoint.getY(), currPoint.getZ()));

                currPoint.move(v, distanceOfPoints);

                sumOfTranslation += distanceOfPoints;
            }

            //move Location Vector back
            currPoint.move(v, -v.getMagnitude());

        }

    }

    private void addInsideMesh(double distanceOfPoints) {

        // 1. Finden der Bounding-Box des Polygons
        Bounds boundingBox = this.polygon.getBoundsInLocal();

        double uMin = boundingBox.getMinX();
        double uMax = boundingBox.getMaxX();

        double vMin = boundingBox.getMinY();
        double vMax = boundingBox.getMaxY();

        //mesh inside of polygon2D
        double v = vMin;

        while (v <= vMax) {

            double u = uMin;

            while (u <= uMax) {

                StpCartesianPoint pt = new StpCartesianPoint(-1, "", u, v, 0);

                if (this.contains(pt) && !this.meshUVW.contains(pt)) {
                    this.meshUVW.add(pt);
                }

                u += distanceOfPoints;
            }

            v += distanceOfPoints;
        }

    }

    public boolean contains(StpCartesianPoint uvwPoint) {

        return this.polygon.contains(uvwPoint.getX(), uvwPoint.getY());

    }


}
