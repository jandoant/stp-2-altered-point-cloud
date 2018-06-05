package com.jandoant.stp_entities;

import Jama.Matrix;

/**
 * Klasse StpPlane
 * Created by Jan Doant on 24.04.2018
 */
public class StpPlane extends StpElementarySurface {

    //Attribute

    //Konstruktor
    public StpPlane(int id, String name, int positionId) {
        super(id, name, positionId);
    }

    //Methoden
    @Override
    public String toString() {
        return super.toString() + "}";
    }

    public StpVector getLocationVector() {

        return this.getPosition().getLocation().convertToVector();
    }

    public StpVector getNormalVector() {

        StpVector normalVector = this.getPosition().getAxis().convertToVector();
        return StpVector.normalize(normalVector);

    }

    public StpVector[] getDirectionVectors() {

        StpVector[] directionVectors = new StpVector[2];

        directionVectors[0] = StpVector.normalize(this.getPosition().getRefDirection().convertToVector());
        directionVectors[1] = StpVector.normalize(StpVector.crossProduct(this.getNormalVector(), directionVectors[0]));

        return directionVectors;

    }






}
