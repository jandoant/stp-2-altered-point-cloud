package com.jandoant.deformation;

/**
 * Klasse DeformUniDirectionalSine
 * Created by Jan Doant on 06.06.2018
 */
public class DeformUniDirectionalSine implements DeformationFunction {

    public final static String DIRECTION_U = "u";
    public final static String DIRECTION_V = "v";

    String direction;
    double amplitude;
    double period;
    double phaseshift;

    @Override
    public double f(double u, double v) {
        if (direction.equals(DIRECTION_U)) {
            return this.amplitude * Math.sin(this.period * u / (2 * Math.PI) - (this.phaseshift * 2 * Math.PI)/(this.period));
        } else {
            return this.amplitude * Math.sin(this.period * v / (2 * Math.PI) - (this.phaseshift * 2 * Math.PI)/(this.period));
        }
    }
}
