package com.jandoant.stp_entities;

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

    @Override
    public int hashCode() {
        int result = edgeStartVertexId;
        result = 31 * result + edgeEndVertexId;
        result = 31 * result + (edgeStartVertex != null ? edgeStartVertex.hashCode() : 0);
        result = 31 * result + (edgeEndVertex != null ? edgeEndVertex.hashCode() : 0);
        return result;
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
}
