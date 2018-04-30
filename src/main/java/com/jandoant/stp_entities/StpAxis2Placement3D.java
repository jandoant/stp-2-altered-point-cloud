package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpAxis2Placement3D
 * Created by Jan Doant on 16.04.2018
 */
public class StpAxis2Placement3D extends StpPlacement {

    //Attribute
    private int axisId, refDirectionId;

    private StpDirection axis;
    private StpDirection refDirection;

    //Konstruktor
    public StpAxis2Placement3D(int id, String name, int locationId, int axisId, int refDirectionId) {

        super(id, name, locationId);

        this.axisId = axisId;
        this.refDirectionId = refDirectionId;
    }

    //Methoden
    @Override
    public void convertFromIds(ArrayList<StpRepresentationItem> availableEntities) {

        for (StpRepresentationItem entitiy : availableEntities) {

            if (entitiy.getId() == this.locationId) {
                this.location = (StpCartesianPoint) entitiy;
            }

            if (entitiy.getId() == this.axisId) {
                this.axis = (StpDirection) entitiy;
            }

            if (entitiy.getId() == this.refDirectionId) {
                this.refDirection = (StpDirection) entitiy;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpAxis2Placement3D that = (StpAxis2Placement3D) o;

        if (axisId != that.axisId) return false;
        if (refDirectionId != that.refDirectionId) return false;
        if (axis != null ? !axis.equals(that.axis) : that.axis != null) return false;
        return refDirection != null ? refDirection.equals(that.refDirection) : that.refDirection == null;
    }

    public StpDirection getAxis() {
        return axis;
    }

    public StpDirection getRefDirection() {
        return refDirection;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", axisId=" + axisId +
                ", axis=" + axis +
                ", refDirectionId=" + refDirectionId +
                ", refDirection=" + refDirection +
                "}";
    }
}
