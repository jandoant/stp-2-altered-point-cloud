package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpFace
 * Created by Jan Doant on 25.04.2018
 */
public abstract class StpFace extends StpTopologicalRepresentationItem {

    //Attribute
    protected ArrayList<Integer> boundsIds;
    protected ArrayList<StpFaceBound> bounds;

    //Konstruktor
    public StpFace(int id, String name, ArrayList<Integer> boundsIds) {
        super(id, name);
        this.boundsIds = boundsIds;
        this.bounds = new ArrayList<>();
    }

    //Methoden
    @Override
    public void convertFromIds(ArrayList<StpRepresentationItem> availableEntities) {

        for (StpRepresentationItem entity : availableEntities) {

            if (this.boundsIds.contains(entity.getId())) {

                StpFaceBound faceBound = (StpFaceBound) entity;
                faceBound.convertFromIds(availableEntities);
                this.bounds.add(faceBound);
            }
        }
    }

    public ArrayList<StpFaceBound> getBounds() {
        return bounds;
    }

    public void setBounds(ArrayList<StpFaceBound> bounds) {
        this.bounds = bounds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpFace stpFace = (StpFace) o;

        if (!boundsIds.equals(stpFace.boundsIds)) return false;
        return bounds != null ? bounds.equals(stpFace.bounds) : stpFace.bounds == null;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", boundsIds=" + boundsIds +
                ", bounds=" + bounds;
    }
}
