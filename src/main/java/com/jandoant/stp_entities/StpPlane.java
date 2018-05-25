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

    public Matrix getXYZtoUVWTransformationMatrix() {

        Matrix XYZBaseMatrix = getXYZBaseMatrix();
        Matrix UVWBaseMatrix = getUVWBaseMatrix();

        return (UVWBaseMatrix.inverse()).times(XYZBaseMatrix);
    }

    public Matrix getUVWToXYZTransformationMatrix() {
        return this.getXYZtoUVWTransformationMatrix().inverse();
    }



    private Matrix getUVWBaseMatrix(){

        //wird für jede StpPlane erstellt aus Normalenvektor und den Richtungsvektoren

        StpVector u = this.getDirectionVectors()[0];
        StpVector v = this.getDirectionVectors()[1];
        StpVector w = this.getNormalVector();

        double[][] baseLocalVals = {
                {u.getX(), v.getX(), w.getX()},
                {u.getY(), v.getY(), w.getY()},
                {u.getZ(), v.getZ(), w.getZ()}
        };

        return new Matrix(baseLocalVals);
    }


    private Matrix getXYZBaseMatrix() {

        //is always the same
        double[][] baseWorldVals = {
                {1.0, 0.0, 0.0},
                {0.0, 1.0, 0.0},
                {0.0, 0.0, 1.0}
        };

        return new Matrix(baseWorldVals);
    }

}
