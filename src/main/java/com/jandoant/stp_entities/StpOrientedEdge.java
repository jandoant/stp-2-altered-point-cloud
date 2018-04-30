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
    public void convertFromIds(ArrayList<StpRepresentationItem> allEntities) {

        //Erstmal edgeElement festlegen
        //die edgeElement-Informationen kommen aus der Entity-Liste
        for (StpRepresentationItem entity : allEntities) {
            if (this.edgeElementId == entity.getId()) {
                this.edgeElement = (StpEdge) entity;
            }
        }

        //beide vertices unterschiedlich => not inherited
        if (this.edgeElementId != this.edgeStartVertexId && this.edgeElementId != this.edgeEndVertexId) {

            //System.out.println("beide unterschiedlich");

            //die edgeStart-Informationen kommen aus der Entity-Liste
            //die edgeEnd-Informationen kommen aus der Entity-Liste
            super.convertFromIds(allEntities);
        }

        //beide Vertices gleich zu EdgeElementId =>inherited
        if (this.edgeElementId == this.edgeStartVertexId && this.edgeElementId == this.edgeEndVertexId) {

            //System.out.println("beide gleich");

            //die edgeStart-Informationen stammen aus dem edgeElement
            this.edgeStartVertexId = this.edgeElement.getEdgeStartVertexId();
            this.edgeEndVertexId = this.edgeElement.getEdgeEndVertexId();

            //die edgeEnd-Informationen stammen aus dem edgeElement
            this.edgeStartVertex = this.edgeElement.getEdgeStartVertex();
            this.edgeEndVertex = this.edgeElement.getEdgeEndVertex();
        }

        //nur startVertexId unterschiedlich
        if (this.edgeElementId != this.edgeStartVertexId && this.edgeElementId == this.edgeEndVertexId) {

            //System.out.println("erster unterschiedlich");

            //die edgeStart-Informationen kommen aus der Entity-Liste
            for (StpRepresentationItem entity : allEntities) {
                if (entity.getId() == this.edgeStartVertexId) {

                    this.edgeStartVertex = (StpVertex) entity;

                }
            }

            //die edgeEnd-Informationen stammen aus dem edgeElement
            this.edgeEndVertexId = this.edgeElement.getEdgeEndVertexId();
            this.edgeEndVertex = this.edgeElement.getEdgeEndVertex();
        }

        //endVertexId unterschiedlich
        if (this.edgeElementId == this.edgeStartVertexId && this.edgeElementId != this.edgeEndVertexId) {

            //System.out.println("zweiter unterscheidlich");

            //die edgeStart-Informationen stammen aus dem edgeElement
            this.edgeStartVertexId = this.edgeElement.getEdgeStartVertexId();
            this.edgeStartVertex = this.edgeElement.getEdgeStartVertex();

            //die edgeEnd-Informationen kommen aus der Entity-Liste
            for (StpRepresentationItem entity : allEntities) {
                if (entity.getId() == this.edgeEndVertexId) {
                    this.edgeEndVertex = (StpVertex) entity;
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
