package com.jandoant.stp_entities;

/**
 * Klasse StpPlacement
 * Created by Jan Doant on 16.04.2018
 */
public abstract class StpPlacement extends StpGeometricRepresentationItem {

    //Attribute
    protected int locationId;
    protected StpCartesianPoint location;

    public StpPlacement(int id, String name, int locationId) {
        super(id, name);
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpPlacement that = (StpPlacement) o;

        if (locationId != that.locationId) return false;
        return location != null ? location.equals(that.location) : that.location == null;
    }

    //Methoden
    public StpCartesianPoint getLocation() {
        return this.location;
    }
}
