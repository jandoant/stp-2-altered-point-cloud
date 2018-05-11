package com.jandoant.helper;

/**
 * Klasse MathHelper
 * Created by Jan Doant on 11.05.2018
 */
public class MathHelper {

    //Attribute
    private static final int DECIMAL_PLACES = 10;
    //Konstruktor

    //Methoden

    public static double round(double value) {

        double factor = Math.pow(10, DECIMAL_PLACES);
        return Math.round(value * factor) / factor;

    }

}
