package com.jandoant.stp_entities;

import Jama.Matrix;

import java.util.ArrayList;

/**
 * Klasse StpElementarySurface
 * Created by Jan Doant on 23.04.2018
 */
public abstract class StpElementarySurface extends StpSurface {

    protected StpAxis2Placement3D position;
    //Attribute
    int positionId;

    //Konstruktor
    public StpElementarySurface(int id, String name, int positionId) {
        super(id, name);
        this.positionId = positionId;
    }

    //Methoden
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpElementarySurface that = (StpElementarySurface) o;

        if (positionId != that.positionId) return false;
        return position != null ? position.equals(that.position) : that.position == null;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", positionId=" + positionId +
                ", position=" + position;
    }

    public StpAxis2Placement3D getPosition() {
        return position;
    }

    public void setPosition(StpAxis2Placement3D position) {
        this.position = position;
    }

    @Override
    public void convertFromIds(ArrayList<StpRepresentationItem> availableEntites) {

        for (StpRepresentationItem entity : availableEntites) {
            if (this.positionId == entity.getId()) {
                this.position = (StpAxis2Placement3D) entity;
                this.position.convertFromIds(availableEntites);
            }
        }

    }

    protected Matrix getXYZBaseMatrix() {

        //is always the same
        double[][] baseWorldVals = {
                {1.0, 0.0, 0.0},
                {0.0, 1.0, 0.0},
                {0.0, 0.0, 1.0}
        };

        return new Matrix(baseWorldVals);
    }

    protected Matrix getUVWBaseMatrix(){

        //wird für jede StpCylindrical Surface aus der Position extrahiert
        StpVector u = this.position.getRefDirection().convertToVector();
        StpVector w = this.position.getAxis().convertToVector();

        StpVector v = StpVector.crossProduct(w, u);

        double[][] baseLocalVals = {
                {u.getX(), v.getX(), w.getX()},
                {u.getY(), v.getY(), w.getY()},
                {u.getZ(), v.getZ(), w.getZ()}
        };

        return new Matrix(baseLocalVals);
    }

    public Matrix getXYZtoUVWTransformationMatrix() {

        Matrix XYZBaseMatrix = getXYZBaseMatrix();
        Matrix UVWBaseMatrix = getUVWBaseMatrix();

        return (UVWBaseMatrix.inverse()).times(XYZBaseMatrix);
    }

    public Matrix getUVWToXYZTransformationMatrix() {
        return this.getXYZtoUVWTransformationMatrix().inverse();
    }



}
