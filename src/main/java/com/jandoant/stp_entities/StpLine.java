package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * A line is an unbounded curve with constant tangent direction. A line is defined by a point and a direction.
 * The positive direction of the line is in the direction of the direction vector.
 * <p>
 * Created by Jan Doant on 11.04.2018
 */
public class StpLine extends StpCurve {

    //int id;
    //String name;

    //Attribute
    private int startingPointId;
    private int directionVectorId;

    private StpCartesianPoint startingPoint;
    private StpVector directionVector;

    //Konstruktor
    public StpLine(int id, String name, int startingPointId, int directionVectorId) {
        super(id, name);
        this.startingPointId = startingPointId;
        this.directionVectorId = directionVectorId;
    }

    public void convertFromIds(ArrayList<StpCartesianPoint> possibleStartingPoints, ArrayList<StpVector> possibleDirectionVectors) {

        for (StpCartesianPoint pt : possibleStartingPoints) {
            if (pt.getId() == this.startingPointId) {
                this.startingPoint = pt;
            }
        }

        for (StpVector dir : possibleDirectionVectors) {

            if (dir.getId() == this.directionVectorId) {
                this.directionVector = dir;
            }
        }

    }

    public StpCartesianPoint getStartingPoint() {
        return startingPoint;
    }

    //Methoden
    public void setStartingPoint(StpCartesianPoint startingPoint) {
        this.startingPoint = startingPoint;
    }

    public StpVector getDirectionVector() {
        return directionVector;
    }

    public void setDirectionVector(StpVector directionVector) {
        this.directionVector = directionVector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpLine stpLine = (StpLine) o;

        if (startingPointId != stpLine.startingPointId) return false;
        if (directionVectorId != stpLine.directionVectorId) return false;
        if (startingPoint != null ? !startingPoint.equals(stpLine.startingPoint) : stpLine.startingPoint != null)
            return false;
        return directionVector != null ? directionVector.equals(stpLine.directionVector) : stpLine.directionVector == null;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", startingPointId=" + startingPointId +
                ", startingPoint=" + startingPoint +
                ", directionVectorId=" + directionVectorId +
                ", directionVector=" + directionVector +
                "}";
    }
}
