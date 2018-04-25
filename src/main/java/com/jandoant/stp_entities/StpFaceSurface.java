package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpFaceSurface
 * Created by Jan Doant on 25.04.2018
 */
public abstract class StpFaceSurface extends StpFace {

    //Attribute
    int faceGeometryId;
    StpSurface faceGeometry;
    Boolean sameSense;

    //Konstruktor
    public StpFaceSurface(int id, String name, ArrayList<Integer> boundsIds, int faceGeometryId, Boolean sameSense) {
        super(id, name, boundsIds);
        this.faceGeometryId = faceGeometryId;
        this.sameSense = sameSense;
    }

    //Methoden

    public void convertFromIds(ArrayList<StpFaceBound> possibleBounds, ArrayList<StpSurface> possibleFaceGeometries){
        super.convertFromIds(possibleBounds);

        for (StpSurface surface: possibleFaceGeometries) {
            if(this.faceGeometryId == surface.getId()){
                this.faceGeometry = surface;
            }
        }
    }

    public StpSurface getFaceGeometry() {
        return faceGeometry;
    }

    public void setFaceGeometry(StpSurface faceGeometry) {
        this.faceGeometry = faceGeometry;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", faceGeometryId=" + faceGeometryId +
                ", faceGeometry=" + faceGeometry +
                ", sameSense=" + sameSense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpFaceSurface that = (StpFaceSurface) o;

        if (faceGeometryId != that.faceGeometryId) return false;
        if (faceGeometry != null ? !faceGeometry.equals(that.faceGeometry) : that.faceGeometry != null) return false;
        return sameSense.equals(that.sameSense);
    }

}
