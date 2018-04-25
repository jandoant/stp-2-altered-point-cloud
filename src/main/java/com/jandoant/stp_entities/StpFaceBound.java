package com.jandoant.stp_entities;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Klasse StpFaceBound
 * Created by Jan Doant on 25.04.2018
 */
public abstract class StpFaceBound extends StpTopologicalRepresentationItem{

    //Attribute
    int boundId;
    StpLoop bound;
    Boolean orientation;

    //Konstruktor
    public StpFaceBound(int id, String name, int boundId, Boolean orientation) {
        super(id, name);
        this.boundId = boundId;
        this.orientation = orientation;
    }

    //Methoden

    public void convertFromIds(ArrayList<StpLoop> possibleBounds){
        for (StpLoop loop: possibleBounds) {
            if(loop.getId() == this.boundId){
                this.bound = loop;
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
                ", orientation=" + orientation;
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
}
