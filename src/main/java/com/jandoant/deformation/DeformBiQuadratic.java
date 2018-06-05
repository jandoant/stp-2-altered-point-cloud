package com.jandoant.deformation;

/**
 * Klasse DeformBiQuadratic
 * Created by Jan Doant on 06.06.2018
 */
public class DeformBiQuadratic implements DeformationFunction {

    double a0, a1, a2;
    double b0, b1, b2;

    public DeformBiQuadratic(double a2, double a1, double a0, double b2, double b1, double b0) {
        this.a0 = a0;
        this.a1 = a1;
        this.a2 = a2;
        this.b0 = b0;
        this.b1 = b1;
        this.b2 = b2;
    }

    @Override
    public double f(double u, double v) {
        return this.a2 * u * u + this.a1 * u + this.a0 + this.b2 * v * v + this.b1 * u + this.b0;
    }
}
