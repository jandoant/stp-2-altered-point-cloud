package com.jandoant.stp_entities;

/**
 * Klasse StpPlacement
 * Created by Jan Doant on 16.04.2018
 */
public abstract class StpPlacement extends StpGeometricRepresentationItem {

    //Attribute
    protected int locationId;
    protected StpCartesianPoint location;

    //Methoden
    public StpCartesianPoint getLocation() {
        return this.location;
    }
}
