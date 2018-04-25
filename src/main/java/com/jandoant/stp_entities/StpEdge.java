package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpEdge
 * Created by Jan Doant on 11.04.2018
 */
public abstract class StpEdge extends StpTopologicalRepresentationItem {

    //Attribute

    int edgeStartVertexId;
    int edgeEndVertexId;

    StpVertex edgeStartVertex;
    StpVertex edgeEndVertex;

    public StpEdge(int id, String name, int edgeStartVertexId, int edgeEndVertexId) {
        super(id, name);
        this.edgeStartVertexId = edgeStartVertexId;
        this.edgeEndVertexId = edgeEndVertexId;
    }

    public void convertFromIds(ArrayList<StpVertex> possibleVertices) {
        for (StpVertex vtx : possibleVertices) {

            if (vtx.getId() == this.edgeStartVertexId) {
                this.edgeStartVertex = vtx;
            }

            if (vtx.getId() == this.edgeEndVertexId) {
                this.edgeEndVertex = vtx;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpEdge stpEdge = (StpEdge) o;

        if (edgeStartVertexId != stpEdge.edgeStartVertexId) return false;
        if (edgeEndVertexId != stpEdge.edgeEndVertexId) return false;
        if (edgeStartVertex != null ? !edgeStartVertex.equals(stpEdge.edgeStartVertex) : stpEdge.edgeStartVertex != null)
            return false;
        return edgeEndVertex != null ? edgeEndVertex.equals(stpEdge.edgeEndVertex) : stpEdge.edgeEndVertex == null;
    }

    //Methoden
    @Override
    public String toString() {
        return super.toString() +
                ", edgeStartVertexId=" + edgeStartVertexId +
                ", edgeStartVertex=" + edgeStartVertex +
                ", edgeEndVertexId=" + edgeEndVertexId +
                ", edgeEndVertex=" + edgeEndVertex;
    }

    public StpVertex getEdgeStartVertex() {
        return edgeStartVertex;
    }

    public void setEdgeStartVertex(StpVertex edgeStartVertex) {
        this.edgeStartVertex = edgeStartVertex;
    }

    public StpVertex getEdgeEndVertex() {
        return edgeEndVertex;
    }

    public void setEdgeEndVertex(StpVertex edgeEndVertex) {
        this.edgeEndVertex = edgeEndVertex;
    }
}
