package com.jandoant.deformation;

/**
 * Klasse DeformQuadratic
 * Created by Jan Doant on 06.06.2018
 */
public class DeformQuadratic implements DeformationFunction {

    //Attribute

    String direction;

    double a2, a1, a0;

    public final static String DIRECTION_U = "u";
    public final static String DIRECTION_V = "v";

    //Konstruktor
    public DeformQuadratic(String direction, double a2, double a1, double a0) {
        this.direction = direction;
        this.a2 = a2;
        this.a1 = a1;
        this.a0 = a0;
    }

    //Methoden
    @Override
    public double f(double u, double v) {
        if (direction.equals(DIRECTION_U)) {
            return this.a2 * u * u + this.a1 * u + this.a0;
        } else {
            return this.a2 * v * v + this.a1 * v + this.a0;
        }
    }

}
