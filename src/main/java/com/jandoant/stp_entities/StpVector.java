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

    double x, y, z;

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

        //build magnitude from x,y,z inputs
        this.updateMagnitudeFromXYZ();
        //build normalized direction from x,y,z inputs
        this.updateDirectionFromXYZ();

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

        //because direction changes
        this.updateXYZFromDirectionAndMagnitude();
    }

    private void updateXYZFromDirectionAndMagnitude() {

        this.x = this.direction.xDirection * this.magnitude;
        this.y = this.direction.yDirection * this.magnitude;
        this.z = this.direction.zDirection * this.magnitude;

    }

    private void updateDirectionFromXYZ() {
        this.directionId = -1;
        this.direction = new StpDirection(-1, "", this.x / this.magnitude, this.y / this.magnitude, this.z / this.magnitude);
    }

    private void updateMagnitudeFromXYZ() {
        this.magnitude = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", directionId=" + directionId +
                ", direction=" + direction +
                ", magnitude=" + magnitude +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                "}";
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public double getMagnitude() {
        return this.magnitude;
    }

    public StpDirection getDirection() {
        return direction;
    }

    public void setDirection(StpDirection direction) {
        this.direction = direction;
        this.updateXYZFromDirectionAndMagnitude();
    }

    public static StpVector add(StpVector v1, StpVector v2) {

        double newX = v1.getX() + v2.getX();
        double newY = v1.getY() + v2.getY();
        double newZ = v1.getZ() + v2.getZ();

        return new StpVector(-1, "", newX, newY, newZ);
    }

    public void add(StpVector otherVector) {

        this.x += otherVector.getX();
        this.y += otherVector.getY();
        this.z += otherVector.getZ();

        //because x,y,z changes
        this.updateMagnitudeFromXYZ();
        this.updateDirectionFromXYZ();

    }

    public StpVector subtract(StpVector otherVector) {

        double newX = this.getX() - otherVector.getX();
        double newY = this.getY() - otherVector.getY();
        double newZ = this.getZ() - otherVector.getZ();

        return new StpVector(-1, "", newX, newY, newZ);

    }

    public StpVector scale(double scaleFactor) {

        return new StpVector(-1, "", this.getX() * scaleFactor, this.getY() * scaleFactor, this.getZ() * scaleFactor);

    }

    public double dotProduct(StpVector otherVector) {

        return this.getX() * otherVector.getX() + this.getY() * otherVector.getY() + this.getZ() * otherVector.getZ();

    }

    public StpVector crossProduct(StpVector v2) {

        double newX = this.getY() * v2.getZ() - this.getZ() * v2.getY();
        double newY = this.getZ() * v2.getX() - this.getX() * v2.getZ();
        double newZ = this.getX() * v2.getY() - this.getY() * v2.getX();

        return new StpVector(-1, "", newX, newY, newZ);
    }

    public StpVector normalize() {

        return new StpVector(-1, "", this.getX() / this.magnitude, this.getY() / this.magnitude, this.getZ() / this.magnitude);

    }

    public boolean isOrthogonalTo(StpVector otherVector) {

        return this.dotProduct(otherVector) == 0;

    }
}
