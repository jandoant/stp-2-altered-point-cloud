package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpConic
 * Created by Jan Doant on 17.05.2018
 */
public abstract class StpConic extends StpCurve {


    //Attribute

    int positionId;

    StpAxis2Placement3D position;

    //Konstruktor
    public StpConic(int id, String name, int positionId) {
        super(id, name);
        this.positionId = positionId;
    }

    //Methoden
    @Override
    public void convertFromIds(ArrayList<StpRepresentationItem> availableEntities) {

        for (StpRepresentationItem entity: availableEntities) {

            if(this.positionId == entity.getId()){
                this.position = (StpAxis2Placement3D) entity;
                this.position.convertFromIds(availableEntities);
            }

        }




    }

    public StpAxis2Placement3D getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpConic stpConic = (StpConic) o;

        if (positionId != stpConic.positionId) return false;
        return position != null ? position.equals(stpConic.position) : stpConic.position == null;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", positionId=" + positionId +
                ", position=" + position;
    }

    public void setPosition(StpAxis2Placement3D position) {
        this.position = position;
    }
}
