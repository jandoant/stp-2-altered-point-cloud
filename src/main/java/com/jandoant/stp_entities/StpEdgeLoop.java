package com.jandoant.stp_entities;

/**
 * Klasse StpEdgeLoop
 * Created by Jan Doant on 11.04.2018
 */
public class StpEdgeLoop extends StpLoop {

    //Attribute
    StpOrientedEdge[] edgeList;

    //Konstruktor
    public StpEdgeLoop(int id, String name, StpOrientedEdge[] edgeList) {
        this.id = id;
        this.name = name;
        this.edgeList = edgeList;
    }

    //Methoden

}
