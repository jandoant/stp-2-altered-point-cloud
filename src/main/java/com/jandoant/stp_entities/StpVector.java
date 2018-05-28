package com.jandoant.stp_entities;

import com.jandoant.helper.MathHelper;

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

        this.x = MathHelper.round(x);
        this.y = MathHelper.round(y);
        this.z = MathHelper.round(z);

        //build magnitude from x,y,z inputs
        this.updateMagnitudeFromXYZ();
        //build normalized direction from x,y,z inputs
        this.updateDirectionFromXYZ();

    }

    public static StpVector create(StpCartesianPoint p0, StpCartesianPoint p1) {

        return new StpVector(-1, "", p1.getX() - p0.getX(), p1.getY() - p0.getY(), p1.getZ() - p0.getZ());

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
        if (Double.compare(stpVector.x, x) != 0) return false;
        if (Double.compare(stpVector.y, y) != 0) return false;
        if (Double.compare(stpVector.z, z) != 0) return false;
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

        if (this.direction != null) {
            this.x = this.direction.xDirection * this.magnitude;
            this.y = this.direction.yDirection * this.magnitude;
            this.z = this.direction.zDirection * this.magnitude;
        }

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

    public static StpVector subtract(StpVector v1, StpVector v0) {

        double newX = v1.getX() - v0.getX();
        double newY = v1.getY() - v0.getY();
        double newZ = v1.getZ() - v0.getZ();

        return new StpVector(-1, "", newX, newY, newZ);

    }

    public void subtract(StpVector otherVector) {

        this.x -= otherVector.getX();
        this.y -= otherVector.getY();
        this.z -= otherVector.getZ();

        //because x,y,z changes
        this.updateMagnitudeFromXYZ();
        this.updateDirectionFromXYZ();

    }

    public static StpVector scale(StpVector v, double scaleFactor) {

        return new StpVector(-1, "", v.getX() * scaleFactor, v.getY() * scaleFactor, v.getZ() * scaleFactor);

    }

    public void scale(double scaleFactor) {

        this.x *= scaleFactor;
        this.y *= scaleFactor;
        this.z *= scaleFactor;

        //because x,y,z changes
        this.updateMagnitudeFromXYZ();
        this.updateDirectionFromXYZ();
    }

    public static double dotProduct(StpVector v1, StpVector v2) {

        return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();

    }

    public double dotProduct(StpVector otherVector) {

        return this.getX() * otherVector.getX() + this.getY() * otherVector.getY() + this.getZ() * otherVector.getZ();

    }

    public static StpVector crossProduct(StpVector v1, StpVector v2) {

        double newX = v1.getY() * v2.getZ() - v1.getZ() * v2.getY();
        double newY = v1.getZ() * v2.getX() - v1.getX() * v2.getZ();
        double newZ = v1.getX() * v2.getY() - v1.getY() * v2.getX();

        return new StpVector(-1, "", newX, newY, newZ);
    }

    public void crossProduct(StpVector otherVector) {

        double newX = this.getY() * otherVector.getZ() - this.getZ() * otherVector.getY();
        double newY = this.getZ() * otherVector.getX() - this.getX() * otherVector.getZ();
        double newZ = this.getX() * otherVector.getY() - this.getY() * otherVector.getX();

        this.x = newX;
        this.y = newY;
        this.z = newZ;

        //because x,y,z changes
        this.updateMagnitudeFromXYZ();
        this.updateDirectionFromXYZ();
    }

    public static StpVector normalize(StpVector v) {

        return new StpVector(-1, "", v.getX() / v.getMagnitude(), v.getY() / v.getMagnitude(), v.getZ() / v.getMagnitude());

    }

    public void normalize() {

        this.x /= this.magnitude;
        this.y /= this.magnitude;
        this.z /= this.magnitude;

        //because x,y,z changes
        this.updateMagnitudeFromXYZ();
        this.updateDirectionFromXYZ();

    }

    public static boolean areOrthogonal(StpVector v1, StpVector v2) {

        return StpVector.dotProduct(v1, v2) == 0;

    }

    public boolean isOrthogonalTo(StpVector otherVector) {

        return this.dotProduct(otherVector) == 0;

    }

    public void rotate(double angle, StpVector pivot, StpVector axis) {

        //axis in den Ursprung verschieben
        this.x -= pivot.getX();
        this.y -= pivot.getY();
        this.z -= pivot.getZ();

        //drehen um Ursprungsgerade
        if (axis.isParallel2XAxis()) {
            rotateAroundX(angle);
        } else if (axis.isParallel2YAxis()) {
            rotateAroundY(angle);
        } else if (axis.isParallel2ZAxis()) {
            rotateAroundZ(angle);
        } else {
            rotateAroundAnyAxis(axis, angle);
        }

        //axis wieder zurück verschieben
        this.x += pivot.getX();
        this.y += pivot.getY();
        this.z += pivot.getZ();

        //because x,y,z changes
        this.updateMagnitudeFromXYZ();
        this.updateDirectionFromXYZ();

    }

    private void rotateAroundAnyAxis(StpVector axis, double angle) {

        StpVector axisNormalized = StpVector.normalize(axis);

        //deg->rad
        angle = Math.toRadians(angle);

        //Anwendung der Drehmatrix für beliebige Achsen durch den Ursprung
        double n1 = axisNormalized.getX();
        double n2 = axisNormalized.getY();
        double n3 = axisNormalized.getZ();

        double newX = (n1 * n1 * (1 - Math.cos(angle)) + Math.cos(angle)) * this.x +
                (n1 * n2 * (1 - Math.cos(angle)) - n3 * Math.sin(angle)) * this.y +
                (n1 * n3 * (1 - Math.cos(angle)) + n2 * Math.sin(angle)) * this.z;

        double newY = (n2 * n1 * (1 - Math.cos(angle)) + n3 * Math.sin(angle)) * this.x +
                (n2 * n2 * (1 - Math.cos(angle)) + Math.cos(angle)) * this.y +
                (n2 * n3 * (1 - Math.cos(angle)) - n1 * Math.sin(angle)) * this.z;

        double newZ = (n3 * n1 * (1 - Math.cos(angle)) - n2 * Math.sin(angle)) * this.x +
                (n3 * n2 * (1 - Math.cos(angle)) + n1 * Math.sin(angle)) * this.y +
                (n3 * n3 * (1 - Math.cos(angle)) + Math.cos(angle)) * this.z;

        //Neubelegung der Werte -  Rundung auf einigermaßen sinnvolle Angaben
        this.x = MathHelper.round(newX);
        this.y = MathHelper.round(newY);
        this.z = MathHelper.round(newZ);

    }

    private void rotateAroundX(double angle) {

        //deg->rad
        angle = Math.toRadians(angle);

        //Anwendung der Drehmatrix für X-Achse
        double newY = Math.cos(angle) * this.y - Math.sin(angle) * this.z;
        double newZ = Math.sin(angle) * this.y + Math.cos(angle) * this.z;

        //Neubelegung der Werte -  Rundung auf einigermaßen sinnvolle Angaben
        this.y = MathHelper.round(newY);
        this.z = MathHelper.round(newZ);
    }

    private void rotateAroundY(double angle) {

        //deg->rad
        angle = Math.toRadians(angle);

        //Anwendung der Drehmatrix für Y-Achse
        double newX = Math.cos(angle) * this.x + Math.sin(angle) * this.z;
        double newZ = -Math.sin(angle) * this.x + Math.cos(angle) * this.z;

        //Neubelegung der Werte -  Rundung auf einigermaßen sinnvolle Angaben
        this.x = MathHelper.round(newX);
        this.z = MathHelper.round(newZ);
    }

    private void rotateAroundZ(double angle) {

        //deg->rad
        angle = Math.toRadians(angle);

        //Anwendung der Drehmatrix für Z-Achse
        double newX = Math.cos(angle) * this.x - Math.sin(angle) * this.y;
        double newY = Math.sin(angle) * this.x + Math.cos(angle) * this.y;

        //Neubelegung der Werte -  Rundung auf einigermaßen sinnvolle Angaben
        this.x = MathHelper.round(newX);
        this.y = MathHelper.round(newY);
    }

    private boolean isParallel2XAxis() {
        return this.x != 0 && this.y == 0 && this.z == 0;
    }

    private boolean isParallel2YAxis() {
        return this.x == 0 && this.y != 0 && this.z == 0;
    }

    private boolean isParallel2ZAxis() {
        return this.x == 0 && this.y == 0 && this.z != 0;
    }

    public void move(StpVector dir, double distance) {

        StpVector dirNormalized = StpVector.normalize(dir);
        dirNormalized.scale(distance);

        this.x += dirNormalized.getX();
        this.y += dirNormalized.getY();
        this.z += dirNormalized.getZ();

        //because x,y,z changes
        this.updateMagnitudeFromXYZ();
        this.updateDirectionFromXYZ();
    }

    public StpCartesianPoint transformToCartesianPoint() {
        return new StpCartesianPoint(-1, "", this.x, this.y, this.z);
    }
}
