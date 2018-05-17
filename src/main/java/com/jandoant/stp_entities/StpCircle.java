package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpCircle
 * Created by Jan Doant on 17.05.2018
 */
public class StpCircle extends StpConic {


    //Attribute
    double radius;

    //Konstruktor
    public StpCircle(int id, String name, int positionId, double radius) {
        super(id, name, positionId);
        this.radius = radius;
    }

    //Methoden
    public StpVector getCenterPoint(){
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpCircle stpCircle = (StpCircle) o;

        return Double.compare(stpCircle.radius, radius) == 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", radius=" + radius +
                "}";
    }




}
