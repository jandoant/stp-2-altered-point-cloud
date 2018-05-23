package com.jandoant.stp_entities;

import Jama.Matrix;

import java.util.ArrayList;

/**
 * A startingPoint defined by its coordinates in a three dimensional rectangular Cartesian coordinate system,
 * or in a two dimensional parameter space.
 * The entity is defined in a three dimensional space.
 * <p>
 * Created by Jan Doant on 11.04.2018
 */
public class StpCartesianPoint extends StpPoint {

    private double x, y, z;

    public StpCartesianPoint(int id, String name, double x, double y, double z) {
        super(id, name);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void convertFromIds(ArrayList<StpRepresentationItem> availableEntities) {
        //is not needed, because StpCartesian does not reference other entities
    }

    @Override
    public String toString() {
        return super.toString() +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpCartesianPoint that = (StpCartesianPoint) o;

        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        return Double.compare(that.z, z) == 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void move(StpVector dir, double distance) {

        StpVector dirNormalized = StpVector.normalize(dir);
        dirNormalized.scale(distance);

        this.x += dirNormalized.getX();
        this.y += dirNormalized.getY();
        this.z += dirNormalized.getZ();

    }

    public static StpCartesianPoint move(StpCartesianPoint oldPoint, StpVector dir, double distance) {

        StpVector dirNormalized = StpVector.normalize(dir);
        dirNormalized.scale(distance);

        double newX = oldPoint.x + dirNormalized.getX();
        double newY = oldPoint.y + dirNormalized.getY();
        double newZ = oldPoint.z + dirNormalized.getZ();

        return new StpCartesianPoint(-1, "", newX, newY, newZ);
    }

    public StpVector convertToVector() {

        return new StpVector(-1, "", this.x, this.y, this.z);

    }

    public StpCartesianPoint transform(Matrix transformationMatrix) {

        //make CartesianPoint a 1x3 Matrix
        double[][] vals = {
                {this.x},
                {this.y},
                {this.z}
        };
        Matrix ptMatrix = new Matrix(vals);

        //multiply with transformation matrix
        double[][] values = (transformationMatrix.times(ptMatrix)).getArray();

        //make new 1x3 matrix a Cartesian Point again

        double newX = values[0][0];
        double newY = values[1][0];
        double newZ = values[2][0];

        return new StpCartesianPoint(-1, "", newX, newY, newZ);

    }
}
