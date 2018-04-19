package com.jandoant.stp_entities;

/**
 * Klasse StpEdgeCurve
 * Created by Jan Doant on 11.04.2018
 */
public class StpEdgeCurve extends StpEdge {

    //Attribute

    //String name;
    //int id;

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
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + edgeGeometryId;
        result = 31 * result + (edgeGeometry != null ? edgeGeometry.hashCode() : 0);
        result = 31 * result + (sameSense ? 1 : 0);
        return result;
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






}
