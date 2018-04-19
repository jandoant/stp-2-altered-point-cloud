package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpVertexPoint
 * Created by Jan Doant on 11.04.2018
 */
public class StpVertexPoint extends StpVertex {

    //int id;
    //String name;

    //Attribute
    private int vertexGeometryId;

    private StpPoint vertexGeometry;

    //Konstruktor
    public StpVertexPoint(int id, String name, int vertexGeometryId) {
        super(id, name);
        this.vertexGeometryId = vertexGeometryId;
    }

    //Methoden
    public void convertFromIds(ArrayList<StpPoint> possibleVertexGeometries) {

        for (StpPoint pt : possibleVertexGeometries) {
            if (pt.getId() == this.vertexGeometryId) {
                this.vertexGeometry = pt;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpVertexPoint that = (StpVertexPoint) o;

        if (vertexGeometryId != that.vertexGeometryId) return false;
        return vertexGeometry != null ? vertexGeometry.equals(that.vertexGeometry) : that.vertexGeometry == null;
    }

    public int getVertexGeometryId() {
        return vertexGeometryId;
    }

    public void setVertexGeometryId(int vertexGeometryId) {
        this.vertexGeometryId = vertexGeometryId;
    }

    public StpPoint getVertexGeometry() {
        return vertexGeometry;
    }

    public void setVertexGeometry(StpPoint vertexGeometry) {
        this.vertexGeometry = vertexGeometry;
    }
}
