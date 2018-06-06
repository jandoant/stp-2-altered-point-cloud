package com.jandoant.deformation;

public interface DeformationFunction {

    public final static String DIRECTION_U = "u";
    public final static String DIRECTION_V = "v";

    public double f(double u, double v);

}
