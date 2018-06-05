package com.jandoant.stp_entities;

import Jama.Matrix;

/**
 * Klasse StpCylindricalSurface
 * Created by Jan Doant on 25.04.2018
 */
public class StpCylindricalSurface extends StpElementarySurface {

    //Attribute
    double radius;

    //Konstruktor
    public StpCylindricalSurface(int id, String name, int positionId, double radius) {
        super(id, name, positionId);
        this.radius = radius;
    }

    //Methoden
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpCylindricalSurface that = (StpCylindricalSurface) o;

        return Double.compare(that.radius, radius) == 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", radius=" + radius +
                "}";
    }





}
