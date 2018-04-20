package com.jandoant.stp_entities;

/**
 * Klasse StpDirection
 * Created by Jan Doant on 11.04.2018
 */
public class StpDirection extends StpGeometricRepresentationItem {

    //int id;
    //String name;

    double xDirection;
    double yDirection;
    double zDirection;

    public StpDirection(int id, String name, double xDirection, double yDirection, double zDirection) {
        super(id, name);
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.zDirection = zDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StpDirection that = (StpDirection) o;

        if (Double.compare(that.xDirection, xDirection) != 0) return false;
        if (Double.compare(that.yDirection, yDirection) != 0) return false;
        return Double.compare(that.zDirection, zDirection) == 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", xDirection=" + xDirection +
                ", yDirection=" + yDirection +
                ", zDirection=" + zDirection +
                "}";
    }
}
