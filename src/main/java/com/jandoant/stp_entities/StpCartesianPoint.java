package com.jandoant.stp_entities;

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
}
