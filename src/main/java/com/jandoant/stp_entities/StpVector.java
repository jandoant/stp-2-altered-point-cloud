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

    //Konstruktor
    public StpVector(int id, String name, int directionId, double magnitude) {
        super(id, name);
        this.directionId = directionId;
        this.magnitude = magnitude;
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

    public StpDirection getDirection() {
        return direction;
    }

    public void setDirection(StpDirection direction) {
        this.direction = direction;
    }
}
