package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpFaceBound
 * Created by Jan Doant on 25.04.2018
 */
public class StpFaceBound extends StpTopologicalRepresentationItem {

    //Attribute
    int boundId;
    StpLoop bound;
    Boolean orientation;

    StpPlane plane;

    //Konstruktor
    public StpFaceBound(int id, String name, int boundId, Boolean orientation) {
        super(id, name);
        this.boundId = boundId;
        this.orientation = orientation;
    }

    //Methoden
    @Override
    public void convertFromIds(ArrayList<StpRepresentationItem> availableEntities) {
        for (StpRepresentationItem entity : availableEntities) {
            if (entity.getId() == this.boundId) {
                this.bound = (StpLoop) entity;
                this.bound.convertFromIds(availableEntities);
                return;
            }
        }
    }

    public StpLoop getBound() {
        return bound;
    }

    public void setBound(StpLoop bound) {
        this.bound = bound;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", boundId=" + boundId +
                ", bound=" + bound +
                ", orientation=" + orientation + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpFaceBound that = (StpFaceBound) o;

        if (boundId != that.boundId) return false;
        if (bound != null ? !bound.equals(that.bound) : that.bound != null) return false;
        return orientation.equals(that.orientation);
    }

    public boolean isOuterFaceBound() {

        return this.getClass().getSimpleName().equals("StpFaceOuterBound");

    }

    public boolean isPolygon() {

        StpEdgeLoop edgeLoop = (StpEdgeLoop) this.getBound();

        return edgeLoop.getEdgesList().size() > 2;

    }

    public boolean isCircle() {

        StpEdgeLoop edgeLoop = (StpEdgeLoop) this.getBound();

        return edgeLoop.getEdgesList().size() == 1;

    }

    public String getType() {

        return this.getClass().getSimpleName();
    }
}
