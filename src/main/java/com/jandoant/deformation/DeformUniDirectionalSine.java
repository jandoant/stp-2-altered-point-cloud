package com.jandoant.deformation;

/**
 * Klasse DeformUniDirectionalSine
 * Created by Jan Doant on 06.06.2018
 */
public class DeformUniDirectionalSine implements DeformationFunction {

    String direction;
    double amplitude;
    double period;
    double phaseshift;
    double offset;

    public DeformUniDirectionalSine(double amplitude, double period, double phaseshift, double offset, String direction) {
        this.direction = direction;
        this.amplitude = amplitude;
        this.period = period;
        this.phaseshift = phaseshift;
        this.offset = offset;
    }

    @Override
    public double f(double u, double v) {
        if (direction.equals(DeformationFunction.DIRECTION_U)) {
            return this.amplitude * Math.sin(this.period * u / (2 * Math.PI) - (this.phaseshift * 2 * Math.PI) / (this.period)) + this.offset;
        } else {
            return this.amplitude * Math.sin(this.period * v / (2 * Math.PI) - (this.phaseshift * 2 * Math.PI) / (this.period)) + this.offset;
        }
    }
}
