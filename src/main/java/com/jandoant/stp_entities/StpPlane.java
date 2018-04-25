package com.jandoant.stp_entities;

/**
 * Klasse StpPlane
 * Created by Jan Doant on 24.04.2018
 */
public class StpPlane extends StpElementarySurface {

    //Attribute

    //Konstruktor
    public StpPlane(int id, String name, int positionId) {
        super(id, name, positionId);
    }

    //Methoden

    @Override
    public String toString() {
        return super.toString() + "}";
    }
}
