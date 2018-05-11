package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpVector
 * Created by Jan Doant on 11.04.2018
 */
public class StpVector extends StpGeometricRepresentationItem {

    int directionId;

    //Attribute
    private StpDirection direction;
    private double magnitude;

    private double x, y, z;

    //Konstruktor
    public StpVector(int id, String name, int directionId, double magnitude) {
        super(id, name);
        this.directionId = directionId;
        this.magnitude = magnitude;
    }

    public StpVector(int id, String name, double x, double y, double z) {
        super(id, name);
        this.x = x;
        this.y = y;
        this.z = z;

        this.magnitude = Math.sqrt(x*x+y*y+z*z);

        this.directionId = -1;
        this.direction = new StpDirection(-1, "", x/this.magnitude, y/this.magnitude, z/this.magnitude);

    }

    //Methoden
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpVector stpVector = (StpVector) o;

        if (directionId != stpVector.directionId) return false;
        if (Double.compare(stpVector.magnitude, magnitude) != 0) return false;
        return direction != null ? direction.equals(stpVector.direction) : stpVector.direction == null;
    }

    @Override
    public void convertFromIds(ArrayList<StpRepresentationItem> availableEntites) {

        for (StpRepresentationItem entity : availableEntites) {
            if (entity.getId() == this.directionId) {
                this.direction = (StpDirection) entity;
                this.direction.convertFromIds(availableEntites);
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                ", directionId=" + directionId +
                ", direction=" + direction +
                ", magnitude=" + magnitude +
                "}";
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

    public double getMagnitude() {
        return magnitude;
    }

    public StpDirection getDirection() {
        return direction;
    }

    public void setDirection(StpDirection direction) {
        this.direction = direction;
    }

    public StpVector add(StpVector otherVector) {

        double newX = this.x + otherVector.getX();
        double newY = this.y + otherVector.getY();
        double newZ = this.z + otherVector.getZ();

        return new StpVector(-1, "", newX, newY, newZ);
    }

    public StpVector subtract(StpVector otherVector) {

        double newX = this.x - otherVector.getX();
        double newY = this.y - otherVector.getY();
        double newZ = this.z - otherVector.getZ();

        return new StpVector(-1, "", newX, newY, newZ);

    }
}
