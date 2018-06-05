package com.jandoant.stp_entities;

import Jama.Matrix;
import com.jandoant.helper.MathHelper;
import javafx.geometry.Point2D;

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

    public static StpCartesianPoint convertToCartesianPoint(Point2D pt) {

        return new StpCartesianPoint(-1, "", pt.getX(), pt.getY(), 0);

    }

    public static double distance(StpCartesianPoint centerPoint, StpCartesianPoint startingPoint) {

        return Math.sqrt(
                Math.pow(centerPoint.getX() - startingPoint.getX(), 2) +
                        Math.pow(centerPoint.getY() - startingPoint.getY(), 2) +
                        Math.pow(centerPoint.getZ() - startingPoint.getZ(), 2)
        );
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

    public StpCartesianPoint baseTransform(Matrix transformationMatrix) {

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

    public StpCartesianPoint cylinderTransform() {

        double r = Math.sqrt(this.x * this.x + this.y * this.y);
        double phi = Math.toDegrees(Math.atan2(this.y, this.x));

        // Reihenfolge der Paramter beachten
        return new StpCartesianPoint(-1, "", phi, this.z, r);
    }

    public StpCartesianPoint cylinderTransformToCartesian() {

        //z ist eigentlich R, y ist eigentlich phi
        double newX = this.z * Math.cos(Math.toRadians(this.x));
        double newY = this.z * Math.sin(Math.toRadians(this.x));

        return new StpCartesianPoint(-1, "", newX, newY, this.y);
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String print(String seperator) {

        return MathHelper.round(this.x) + seperator + MathHelper.round(this.y) + seperator + MathHelper.round(this.z);

    }

}
