package com.jandoant.stp_entities;

import java.util.ArrayList;

/**
 * Klasse StpAxis2Placement3D
 * Created by Jan Doant on 16.04.2018
 */
public class StpAxis2Placement3D extends StpPlacement {

    //Attribute
    private int axisId, refDirectionId;

    private StpDirection axis;
    private StpDirection refDirection;

    //Konstruktor
    public StpAxis2Placement3D(int id, String name, int locationId, int axisId, int refDirectionId) {

        this.id = id;
        this.name = name;

        this.locationId = locationId;
        this.axisId = axisId;
        this.refDirectionId = refDirectionId;
    }

    //Methoden
    public void convertFromIds(ArrayList<StpCartesianPoint> possibleLocations, ArrayList<StpDirection> possibleDirections) {

        for (StpCartesianPoint pt : possibleLocations) {
            if (pt.getId() == this.locationId) {
                this.location = pt;
            }
        }

        for (StpDirection dir : possibleDirections) {

            if (dir.getId() == this.axisId) {
                this.axis = dir;
            }

            if (dir.getId() == this.refDirectionId) {
                this.refDirection = dir;
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        StpAxis2Placement3D that = (StpAxis2Placement3D) o;

        if (this.id != that.id) return false;
        if (!name.equals(that.name)) return false;

        if (this.locationId != that.locationId) return false;
        if (this.axisId != that.axisId) return false;
        if (this.refDirectionId != that.refDirectionId) return false;

        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (axis != null ? !axis.equals(that.axis) : that.axis != null) return false;
        return refDirection != null ? refDirection.equals(that.refDirection) : that.refDirection == null;
    }

    public StpDirection getAxis() {
        return axis;
    }

    public StpDirection getRefDirection() {
        return refDirection;
    }
}
