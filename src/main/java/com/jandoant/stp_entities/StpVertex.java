package com.jandoant.stp_entities;

/**
 * Klasse StpVertex
 * Created by Jan Doant on 11.04.2018
 */
public abstract class StpVertex extends StpTopologicalRepresentationItem {

    //Attribute

    //Konstruktor
    public StpVertex(int id, String name) {
        super(id, name);
    }

    //Methoden
    public abstract double getX();
    public abstract double getY();
    public abstract double getZ();

    public abstract StpCartesianPoint convertToCartesianPoint();

}
