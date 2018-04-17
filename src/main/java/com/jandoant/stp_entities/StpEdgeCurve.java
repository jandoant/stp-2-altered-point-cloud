package com.jandoant.stp_entities;

/**
 * Klasse StpEdgeCurve
 * Created by Jan Doant on 11.04.2018
 */
public class StpEdgeCurve extends StpEdge {

    //Attribute
    StpCurve edgeGeometry;
    boolean sameSense;

    //Konstruktor

    public StpEdgeCurve(int id, String name, StpVertex edgeStart, StpVertex edgeEnd, StpCurve edgeGeometry, boolean sameSense) {
        super(id, name, edgeStart, edgeEnd);
        this.edgeGeometry = edgeGeometry;
        this.sameSense = sameSense;
    }

    //Methoden

}
