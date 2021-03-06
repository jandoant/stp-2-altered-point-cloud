package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpVertexPoint
 * Created by Jan Doant on 11.04.2018
 */
public class StpVertexPoint extends StpVertex {

    //Attribute
    private int vertexGeometryId;

    private StpPoint vertexGeometry;

    //Konstruktor
    public StpVertexPoint(int id, String name, int vertexGeometryId) {
        super(id, name);
        this.vertexGeometryId = vertexGeometryId;
    }

    //Methoden
    @Override
    public void convertFromIds(ArrayList<StpRepresentationItem> availableEntities) {

        for (StpRepresentationItem entity : availableEntities) {
            if (entity.getId() == this.vertexGeometryId) {
                this.vertexGeometry = (StpPoint) entity;
                this.vertexGeometry.convertFromIds(availableEntities);
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

    @Override
    public String toString() {
        return super.toString() +
                ", vertexGeometryId=" + vertexGeometryId +
                ", vertexGeometry=" + vertexGeometry +
                "}";
    }

    @Override
    public double getX() {
        return this.vertexGeometry.getX();
    }

    @Override
    public double getY() {
        return this.vertexGeometry.getY();
    }

    @Override
    public double getZ() {
        return this.vertexGeometry.getZ();
    }

    @Override
    public StpCartesianPoint convertToCartesianPoint() {

        return new StpCartesianPoint(-1, "", this.vertexGeometry.getX(), this.vertexGeometry.getY(), this.vertexGeometry.getZ());

    }
}
