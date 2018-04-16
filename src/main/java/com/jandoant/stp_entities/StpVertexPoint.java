package com.jandoant.stp_entities;

/**
 * Klasse StpVertexPoint
 * Created by Jan Doant on 11.04.2018
 */
public class StpVertexPoint extends StpVertex {

    //Attribute
    StpPoint vertexGeometry;

    //Konstruktor
    public StpVertexPoint(int id, String name, StpPoint vertexGeometry) {
        this.id = id;
        this.name = name;
        this.vertexGeometry = vertexGeometry;
    }

    //Methoden

}
