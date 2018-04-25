package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpOrientedEdge
 * Created by Jan Doant on 11.04.2018
 */
public class StpOrientedEdge extends StpEdge {

    //Attribute
    int edgeElementId;
    StpEdge edgeElement;
    boolean orientation;

    //Konstruktor

    public StpOrientedEdge(int id, String name, int edgeStartVertexId, int edgeEndVertexId, int edgeElementId, boolean orientation) {
        super(id, name, edgeStartVertexId, edgeEndVertexId);
        this.edgeElementId = edgeElementId;
        this.orientation = orientation;
    }

    //Methoden
    public void convertFromIds(ArrayList<StpVertex> possibleVertices, ArrayList<StpEdge> possibleEdgeElements) {

        //super.convertFromIds(possibleVertices);

        //Erstmal edgeElement festlegen
        for (StpEdge edge : possibleEdgeElements) {
            if (this.edgeElementId == edge.getId()) {
                this.edgeElement = edge;
            }
        }

        //beide Vertices gleich
        if (this.edgeElementId == this.edgeStartVertexId && this.edgeElementId == this.edgeEndVertexId) {
            this.edgeStartVertex = this.edgeElement.getEdgeStartVertex();
            this.edgeEndVertex = this.edgeElement.getEdgeEndVertex();
        }

        //beide vertices unterschiedlich
        if (this.edgeElementId != this.edgeStartVertexId && this.edgeElementId != this.edgeEndVertexId) {
            super.convertFromIds(possibleVertices);
        }

        //startVertexId unterschiedlich
        if (this.edgeElementId == this.edgeStartVertexId) {
            this.edgeStartVertex = this.edgeElement.getEdgeStartVertex();
            for (StpVertex vtx : possibleVertices) {
                if (vtx.getId() == this.edgeEndVertexId) {
                    this.edgeEndVertex = vtx;
                }
            }
        }

        //endVertexId unterschiedlich
        if (this.edgeElementId == this.edgeEndVertexId) {
            this.edgeEndVertex = this.edgeElement.getEdgeEndVertex();
            for (StpVertex vtx : possibleVertices) {
                if (vtx.getId() == this.edgeStartVertexId) {
                    this.edgeStartVertex = vtx;
                }
            }
        }

    }

    @Override
    public String toString() {
        return super.toString() +
                ", edgeElementId=" + edgeElementId +
                ", edgeElement=" + edgeElement +
                ", orientation=" + orientation +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpOrientedEdge that = (StpOrientedEdge) o;

        if (edgeElementId != that.edgeElementId) return false;
        if (orientation != that.orientation) return false;
        return edgeElement != null ? edgeElement.equals(that.edgeElement) : that.edgeElement == null;
    }

    public StpEdge getEdgeElement() {
        return edgeElement;
    }

    public void setEdgeElement(StpEdge edgeElement) {
        this.edgeElement = edgeElement;
    }
}
