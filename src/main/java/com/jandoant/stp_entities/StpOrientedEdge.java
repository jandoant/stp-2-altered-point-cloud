package com.jandoant.stp_entities;

/**
 * Klasse StpOrientedEdge
 * Created by Jan Doant on 11.04.2018
 */
public class StpOrientedEdge extends StpEdge {

    //Attribute
    StpEdge edgeElement;
    boolean orientation;

    //Konstruktor
    public StpOrientedEdge(int id, String name, StpVertex edgeStart, StpVertex edgeEnd, StpEdge edgeElement, boolean orientation) {
        super(id, name, edgeStart, edgeEnd);
        this.edgeElement = edgeElement;
        this.orientation = orientation;
    }

    //Methoden

}
