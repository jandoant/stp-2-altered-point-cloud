package com.jandoant.deformation;

/**
 * Klasse DeformBiLinear
 * Created by Jan Doant on 06.06.2018
 */
public class DeformBiLinear implements DeformationFunction {

    double a, b, c;

    public DeformBiLinear(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double f(double u, double v) {
        return this.a * u + this.b * v + this.c;
    }

}
