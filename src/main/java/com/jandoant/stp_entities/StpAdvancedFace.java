package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpAdvancedFace
 * Created by Jan Doant on 25.04.2018
 */
public class StpAdvancedFace extends StpFaceSurface {

    //Attribute

    //Konstruktor

    public StpAdvancedFace(int id, String name, ArrayList<Integer> boundsIds, int faceGeometryId, Boolean sameSense) {
        super(id, name, boundsIds, faceGeometryId, sameSense);
    }

    //Methoden
    @Override
    public String toString() {
        return super.toString() + "}";
    }
}
