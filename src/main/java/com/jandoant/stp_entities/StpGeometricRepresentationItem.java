package com.jandoant.stp_entities;

/**
 * A geometric representation item is a representation item that has the additional meaning of having geometric position or orientation or both.
 * <p>
 * Created by Jan Doant on 11.04.2018
 */
public abstract class StpGeometricRepresentationItem extends StpRepresentationItem {

    //Attribute

    //Konstruktor

    public StpGeometricRepresentationItem(int id, String name) {
        super(id, name);
    }

    //Methoden

    @Override
    public String toString() {
        return super.toString();
    }
}
