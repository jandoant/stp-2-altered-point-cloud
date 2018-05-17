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

    ArrayList<StpCartesianPoint> pointCloud;

    String type;

    //Konstruktor
    public StpFaceSurface(int id, String name, ArrayList<Integer> boundsIds, int faceGeometryId, Boolean sameSense) {
        super(id, name, boundsIds);
        this.faceGeometryId = faceGeometryId;
        this.sameSense = sameSense;
    }

    //Methoden
    @Override
    public void convertFromIds(ArrayList<StpRepresentationItem> availableEntities) {

        super.convertFromIds(availableEntities);

        for (StpRepresentationItem entity : availableEntities) {
            if (this.faceGeometryId == entity.getId()) {
                this.faceGeometry = (StpSurface) entity;
                this.faceGeometry.convertFromIds(availableEntities);

                this.type = this.faceGeometry.getClass().getSimpleName();

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

    public String getType() {
        return this.type;
    }

    public void meshCylinder(int numOfRadialSegments, int numOfAxialSegments){

        //find the circles
        
        //extract the midpoint of the circles

        //make the axis of the circles

        //find one of the points to rotate (doesn't matter which circle it is)

        //find out the increment that each point has to rotate by

        //rotate the point x number of times and write a new cp to the point cloud list

        //find out the increment of the movement

        //move each CP by the increment in the direction of the axis

        //write each new CP to the point cloud list










    };

}
