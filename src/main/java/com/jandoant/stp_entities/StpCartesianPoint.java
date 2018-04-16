package com.jandoant.stp_entities;

import java.util.Objects;

/**
 * A startingPoint defined by its coordinates in a three dimensional rectangular Cartesian coordinate system,
 * or in a two dimensional parameter space.
 * The entity is defined in a three dimensional space.
 *
 * Created by Jan Doant on 11.04.2018
 */
public class StpCartesianPoint extends StpPoint {

    public double x,y,z;

    /**
     *
     * @param id - ID of the Entity
     * @param name - Name of the Entity
     * @param x - x-Coordinate
     * @param y - y-Coordinate
     * @param z - z-Coordinate
     */
    public StpCartesianPoint(int id, String name, double x, double y, double z) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "StpCartesianPoint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StpCartesianPoint that = (StpCartesianPoint) o;
        return  Double.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0 &&
                Double.compare(that.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
