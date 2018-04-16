package com.jandoant.stp_entities;

/**
 * A line is an unbounded curve with constant tangent direction. A line is defined by a point and a direction.
 * The positive direction of the line is in the direction of the direction vector.
 *
 * Created by Jan Doant on 11.04.2018
 */
public class StpLine extends StpCurve {

    //int id;
    //String name;

    //Attribute

    int startingPointId;
    int directionVectorId;

    StpCartesianPoint startingPoint;
    StpVector directionVector;

    //Konstruktor
    public StpLine(int id, String name, int startingPointId, int directionVectorId) {
        this.id = id;
        this.name = name;
        this.startingPointId = startingPointId;
        this.directionVectorId = directionVectorId;
    }

    //Methoden
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StpLine stpLine = (StpLine) o;

        if (id != stpLine.id) return false;
        if (startingPointId != stpLine.startingPointId) return false;
        if (directionVectorId != stpLine.directionVectorId) return false;
        if (!name.equals(stpLine.name)) return false;
        if (startingPoint != null ? !startingPoint.equals(stpLine.startingPoint) : stpLine.startingPoint != null)
            return false;
        return directionVector != null ? directionVector.equals(stpLine.directionVector) : stpLine.directionVector == null;
    }
}
