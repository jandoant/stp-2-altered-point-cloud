package com.jandoant.deformation;

/**
 * Klasse DeformTimesAdapter
 * Created by Jan Doant on 04.06.2018
 */
public class DeformTimesAdapter implements DeformationFunction {

    @Override
    public double f(double x, double y) {
        return x*y;
    }

}
