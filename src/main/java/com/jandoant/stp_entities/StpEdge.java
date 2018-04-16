package com.jandoant.stp_entities;

/**
 * Klasse StpEdge
 * Created by Jan Doant on 11.04.2018
 */
public class StpEdge extends StpTopologicalRepresentationItem {

    //Attribute
    StpVertex edgeStart;
    StpVertex edgeEnd;

    //Konstruktor

    public StpEdge(int id, String name, StpVertex edgeStart, StpVertex edgeEnd) {
        this.id = id;
        this.name = name;
        this.edgeStart = edgeStart;
        this.edgeEnd = edgeEnd;
    }
    //Methoden

}
