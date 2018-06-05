package com.jandoant.deformation;

/**
 * Klasse DeformConstant
 * Created by Jan Doant on 06.06.2018
 */
public class DeformConstant implements DeformationFunction {

    double constant;

    public DeformConstant(double constant) {
        this.constant = constant;
    }

    @Override
    public double f(double u, double v) {
        return this.constant;
    }

}
