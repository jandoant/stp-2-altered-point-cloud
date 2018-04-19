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

    public StpRepresentationItem(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "id=" + this.id +
                ", name='" + this.name + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StpRepresentationItem that = (StpRepresentationItem) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }
}
