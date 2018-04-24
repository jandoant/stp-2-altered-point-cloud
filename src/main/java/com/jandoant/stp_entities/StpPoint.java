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

    /**
     * Klasse StpFace
     * Created by Jan Doant on 24.04.2018
     */
    public static class StpFace extends StpElementarySurface {

        //Attribute

        //Konstruktor
        public StpFace(int id, String name, int postiionId) {
            super(id, name, postiionId);
        }

        //Methoden


    }
}
