package com.jandoant.stp_entities;

/**
 * Klasse StpFaceOuterBound
 * Created by Jan Doant on 25.04.2018
 */
public class StpFaceOuterBound extends StpFaceBound {

    //Attribute

    //Konstruktor
    public StpFaceOuterBound(int id, String name, int boundId, Boolean orientation) {
        super(id, name, boundId, orientation);
    }

    //Methoden
    @Override
    public String toString() {
        return super.toString() + "}";
    }
}
