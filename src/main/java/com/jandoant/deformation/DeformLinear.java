package com.jandoant.deformation;

/**
 * Klasse DeformLinear
 * Created by Jan Doant on 06.06.2018
 */
public class DeformLinear implements DeformationFunction {

    double slope;
    double offset;
    String direction;

    public DeformLinear(double slope, double offset, String direction) {
        this.slope = slope;
        this.offset = offset;
        this.direction = direction;
    }

    @Override
    public double f(double u, double v) {
        if (direction.equals(DeformationFunction.DIRECTION_U)) {
            return this.slope * u + this.offset;
        } else {
            return this.slope * v + this.offset;
        }
    }
}
