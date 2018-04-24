package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpElementarySurface
 * Created by Jan Doant on 23.04.2018
 */
public abstract class StpElementarySurface extends StpSurface {

    //Attribute
    int positionId;
    protected StpAxis2Placement3D position;

    //Konstruktor
    public StpElementarySurface(int id, String name, int positionId) {
        super(id, name);
        this.positionId = positionId;
    }

    //Methoden
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpElementarySurface that = (StpElementarySurface) o;

        if (positionId != that.positionId) return false;
        return position != null ? position.equals(that.position) : that.position == null;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", positionId=" + positionId +
                ", position=" + position;
    }

    public StpAxis2Placement3D getPosition() {
        return position;
    }

    public void setPosition(StpAxis2Placement3D position) {
        this.position = position;
    }

    public void convertFromIDs(ArrayList<StpAxis2Placement3D> possiblePositions) {

        for (StpAxis2Placement3D placement: possiblePositions) {
            if(this.positionId == placement.getId()){
                this.position = placement;
            }
        }



    }
}
