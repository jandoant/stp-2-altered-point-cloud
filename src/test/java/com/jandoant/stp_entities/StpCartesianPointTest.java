package com.jandoant.stp_entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StpCartesianPointTest {

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals() {
        //setup
        StpCartesianPoint pt1 = new StpCartesianPoint(12, "Point 1", 3.30, 1.2, 0.0);
        StpCartesianPoint pt2 = new StpCartesianPoint(12, "Point 1", 3.3, 1.20, 0.000);
        //assert
        assertTrue(pt1.equals(pt2));

    }

    @Test
    @DisplayName("should return false if the two compared instances have different id-values")
    void testEqualsNotId() {
        //setup
        StpCartesianPoint pt1 = new StpCartesianPoint(12, "Point 1", 3.3, 1.2, 0.0);
        StpCartesianPoint pt2 = new StpCartesianPoint(11, "Point 1", 3.3, 1.2, 0.0);
        //assert
        assertFalse(pt1.equals(pt2));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different name-values")
    void testEqualsNotName() {
        //setup
        StpCartesianPoint pt1 = new StpCartesianPoint(12, "Point 1", 3.3, 1.2, 0.0);
        StpCartesianPoint pt2 = new StpCartesianPoint(12, "Point 2", 3.3, 1.2, 0.0);
        //assert
        assertFalse(pt1.equals(pt2));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different x-values")
    void testEqualsNotX() {
        //setup
        StpCartesianPoint pt1 = new StpCartesianPoint(12, "Point 1", 3.31, 1.2, 0.0);
        StpCartesianPoint pt2 = new StpCartesianPoint(12, "Point 1", 3.3, 1.2, 0.0);

        //assert
        assertFalse(pt1.equals(pt2));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different y-values")
    void testEqualsNotY() {
        //setup
        StpCartesianPoint pt1 = new StpCartesianPoint(12, "Point 1", 3.3, 1.2, 0.0);
        StpCartesianPoint pt2 = new StpCartesianPoint(12, "Point 1", 3.3, 2.4, 0.0);

        //assert
        assertFalse(pt1.equals(pt2));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different z-values")
    void testEqualsNotZ() {
        //setup
        StpCartesianPoint pt1 = new StpCartesianPoint(12, "Point 1", 3.3, 1.2, 0.04);
        StpCartesianPoint pt2 = new StpCartesianPoint(12, "Point 1", 3.3, 1.2, 0.0);

        //assert
        assertFalse(pt1.equals(pt2));
    }

    @Test
    @DisplayName("should display the correct Message to describe Object-Instance")
    void testToString() {
        StpCartesianPoint pt = new StpCartesianPoint(12, "Point1", 3.3, 4.4, 5.5);

        String expected = "StpCartesianPoint{id=12, name='Point1', x=3.3, y=4.4, z=5.5}";

        assertEquals(pt.toString(), expected);
    }

    @Test
    @DisplayName("should be able to move a cartesian point along a vector by a certain distance by mutating the original Vector")
    void testMoveCPNonStatic() {

        StpCartesianPoint cp = new StpCartesianPoint(-1, "", 0, 0, 0);

        StpVector dir = new StpVector(-1, "", 1, 2, 5);

        double distance = 3.0;

        cp.move(dir, distance);

        double expectedX = 0.5477225575;
        double expectedY = 1.095445115;
        double expectedZ = 2.7386127875;

        double allowedErr = Math.pow(10, -9);

        assertEquals(expectedX, cp.getX(), allowedErr);
        assertEquals(expectedY, cp.getY(), allowedErr);
        assertEquals(expectedZ, cp.getZ(), allowedErr);

    }
}