package com.jandoant.stp_entities;

/**
 * Klasse StpRepresentationItem
 * Created by Jan Doant on 11.04.2018
 */
public abstract class StpRepresentationItem {

    //Attribute
    protected String name;
    protected int id;

    //Methoden
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "StpRepresentationItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
