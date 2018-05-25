package com.jandoant.stp_entities;

/**
 * Klasse StpPoint
 * Created by Jan Doant on 11.04.2018
 */
public abstract class StpPoint extends StpGeometricRepresentationItem {

    //Attribute

    //Konstruktor
    public StpPoint(int id, String name) {
        super(id, name);
    }

    //Methoden
    public abstract double getX();
    public abstract double getY();
    public abstract double getZ();

}
