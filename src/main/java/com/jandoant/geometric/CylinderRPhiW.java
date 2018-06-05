package com.jandoant.geometric;

import com.jandoant.stp_entities.*;

import java.util.ArrayList;

/**
 * Klasse CylinderRPhiW
 * Created by Jan Doant on 29.05.2018
 */
public class CylinderRPhiW {

    private StpAdvancedFace advancedFace;
    private StpCylindricalSurface cylindricalSurface;
    private ArrayList<StpCircle> circles;

    private ArrayList<StpCartesianPoint> meshPhiWR;

    public CylinderRPhiW(StpAdvancedFace advancedFace) {

        this.advancedFace = advancedFace;

        //find the cylindrical Surface
        this.cylindricalSurface = (StpCylindricalSurface) this.advancedFace.getFaceGeometry();

        //find the circles
        circles = new ArrayList<>();
        ArrayList<StpFaceBound> bounds = this.advancedFace.getBounds();
        for (StpFaceBound bound : bounds) {
            StpEdgeLoop edgeLoop = (StpEdgeLoop) bound.getBound();
            StpEdgeCurve edgeCurve = (StpEdgeCurve) edgeLoop.getEdgesList().get(0).getEdgeElement();
            StpCircle circle = (StpCircle) edgeCurve.getEdgeGeometry();
            circles.add(circle);
        }

        this.meshPhiWR = new ArrayList<>();

    }

    public void mesh(int numOfRadialSegments, int numOfRings) {

        // 1. find center points and their distance
        StpCartesianPoint cpt0XYZ = circles.get(0).getCenterVector().transformToCartesianPoint();
        StpCartesianPoint cpt1XYZ = circles.get(1).getCenterVector().transformToCartesianPoint();

        double distanceOfCenterPoints = StpCartesianPoint.distance(cpt0XYZ, cpt1XYZ);

        // 2. transform center points to uvw
        StpCartesianPoint cpt0UVW = cpt0XYZ.baseTransform(this.cylindricalSurface.getXYZtoUVWTransformationMatrix());
        StpCartesianPoint cpt1UVW = cpt1XYZ.baseTransform(this.cylindricalSurface.getXYZtoUVWTransformationMatrix());

        // 1. Inkremente berechnen
        double axialIncrement = distanceOfCenterPoints / ((double) numOfRings - 1);
        double angularIncrement = 360.0 / (double) numOfRadialSegments;

        // 2. Grenzen des Rasters bestimmen
        double wMin = Math.min(cpt0UVW.getZ(), cpt1UVW.getZ());
        double wMax = Math.min(cpt1UVW.getZ(), cpt1UVW.getZ());

        // 3. Rasterpunkte bilden
        double sumOfAngularTranslation = 0;

        double currentW = wMin;

        // all but last row
        while (currentW < wMax) {

            while (sumOfAngularTranslation < 360) {

                StpCartesianPoint pt = new StpCartesianPoint(-1, "", sumOfAngularTranslation, currentW, circles.get(0).getRadius());

                this.meshPhiWR.add(pt);

                sumOfAngularTranslation += angularIncrement;
            }

            sumOfAngularTranslation = 0;
            currentW += axialIncrement;
        }

        //last Row
        currentW = wMax;
        while (sumOfAngularTranslation < 360) {

            StpCartesianPoint pt = new StpCartesianPoint(-1, "", sumOfAngularTranslation, currentW, circles.get(0).getRadius());

            this.meshPhiWR.add(pt);

            sumOfAngularTranslation += angularIncrement;
        }

    }

    public ArrayList<StpCartesianPoint> getMeshPhiWR() {
        return meshPhiWR;
    }

    //Methoden

}
