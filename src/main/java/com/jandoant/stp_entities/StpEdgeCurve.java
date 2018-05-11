package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpEdgeCurve
 * Created by Jan Doant on 11.04.2018
 */
public class StpEdgeCurve extends StpEdge {

    //Attribute

    int edgeGeometryId;

    StpCurve edgeGeometry;
    boolean sameSense;

    //Konstruktor

    public StpEdgeCurve(int id, String name, int edgeStartVertexId, int edgeEndVertexId, int edgeGeometryId, boolean sameSense) {
        super(id, name, edgeStartVertexId, edgeEndVertexId);
        this.edgeGeometryId = edgeGeometryId;
        this.sameSense = sameSense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpEdgeCurve that = (StpEdgeCurve) o;

        if (edgeGeometryId != that.edgeGeometryId) return false;
        if (sameSense != that.sameSense) return false;
        return edgeGeometry != null ? edgeGeometry.equals(that.edgeGeometry) : that.edgeGeometry == null;
    }

    @Override
    public void convertFromIds(ArrayList<StpRepresentationItem> availableEntities) {

        super.convertFromIds(availableEntities);

        for (StpRepresentationItem entity : availableEntities) {
            if (entity.getId() == this.edgeGeometryId) {
                this.edgeGeometry = (StpCurve) entity;
                this.edgeGeometry.convertFromIds(availableEntities);
            }
        }
    }

    //Methoden
    @Override
    public String toString() {
        return super.toString() +
                ", edgeGeometryId=" + edgeGeometryId +
                ", edgeGeometry=" + edgeGeometry +
                ", sameSense=" + sameSense +
                "}";
    }

    public StpCurve getEdgeGeometry() {
        return edgeGeometry;
    }
}
