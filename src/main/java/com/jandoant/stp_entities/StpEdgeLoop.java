package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpEdgeLoop
 * Created by Jan Doant on 11.04.2018
 */
public class StpEdgeLoop extends StpLoop {

    //Attribute
    ArrayList<Integer> edgesIds;
    ArrayList<StpOrientedEdge> edgesList;

    //Konstruktor

    public StpEdgeLoop(int id, String name, ArrayList<Integer> edgesIds) {
        super(id, name);
        this.edgesIds = edgesIds;

        this.edgesList = new ArrayList<>();

    }

    //Methoden
    @Override
    public void convertFromIds(ArrayList<StpRepresentationItem> availableEntities) {

        for (StpRepresentationItem entity : availableEntities) {

            if (this.edgesIds.contains(entity.getId())) {

                StpOrientedEdge orientedEdge = (StpOrientedEdge) entity;

                orientedEdge.convertFromIds(availableEntities);

                this.edgesList.add(orientedEdge);
            }

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpEdgeLoop that = (StpEdgeLoop) o;

        if (!edgesIds.equals(that.edgesIds)) return false;
        return edgesList != null ? edgesList.equals(that.edgesList) : that.edgesList == null;
    }

    @Override
    public int hashCode() {
        int result = edgesIds.hashCode();
        result = 31 * result + (edgesList != null ? edgesList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", edgesIds=" + edgesIds +
                ", edgesList=" + edgesList +
                "}";
    }
}
