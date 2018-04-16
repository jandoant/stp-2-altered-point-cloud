package com.jandoant.stp_entities;

/**
 * Klasse StpVector
 * Created by Jan Doant on 11.04.2018
 */
public class StpVector extends StpGeometricRepresentationItem {

    //Attribute
    StpDirection orientation;
    double magnitude;

    //Konstruktor
    public StpVector(int id, String name, StpDirection orientation, double magnitude) {
        this.id = id;
        this.name = name;
        this.orientation = orientation;
        this.magnitude = magnitude;
    }

    //Methoden

}
