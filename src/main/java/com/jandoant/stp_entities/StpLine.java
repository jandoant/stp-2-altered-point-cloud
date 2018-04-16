package com.jandoant.stp_entities;

/**
 * A line is an unbounded curve with constant tangent direction. A line is defined by a point and a direction.
 * The positive direction of the line is in the direction of the direction vector.
 *
 * Created by Jan Doant on 11.04.2018
 */
public class StpLine extends StpCurve {

    //Attribute
    StpCartesianPoint startingPoint;
    StpVector direction;

    /**
     * Constructs and initializes a Line from a startingPoint.
     * The direction and length of the Line is desctibed through a Vector
     *
     * @param id - ID of the Curve
     * @param name - Name of the Curve
     * @param startingPoint - Starting Point of the Curve
     * @param direction - Direction and length of the Line, represented by a StpVector
     */

    public StpLine(int id, String name, StpCartesianPoint startingPoint, StpVector direction) {
        this.id = id;
        this.name = name;
        this.startingPoint = startingPoint;
        this.direction = direction;
    }

    //Methoden

}
