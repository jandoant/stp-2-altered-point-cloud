package com.jandoant.stp_entities;

/**
 * Klasse StpDirection
 * Created by Jan Doant on 11.04.2018
 */
public class StpDirection extends StpGeometricRepresentationItem {

    double xDirection;
    double yDirection;
    double zDirection;

    public StpDirection(int id, String name, double xDirection, double yDirection , double zDirection){
        this.id = id;
        this.name = name;
        //to make sure that directions are of length 1
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.zDirection = zDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StpDirection that = (StpDirection) o;

        if (id != that.id) return false;
        if (Double.compare(that.xDirection, xDirection) != 0) return false;
        if (Double.compare(that.yDirection, yDirection) != 0) return false;
        if (Double.compare(that.zDirection, zDirection) != 0) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(xDirection);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yDirection);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(zDirection);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "StpDirection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", xDirection=" + xDirection +
                ", yDirection=" + yDirection +
                ", zDirection=" + zDirection +
                '}';
    }


}
