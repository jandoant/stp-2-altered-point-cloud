package com.jandoant.stp_entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StpCircleTest {

    @Test
    void testToString() {

        StpCircle c = new StpCircle(11, "Circle1", 34, 3.0);

        String expected = "StpCircle{id=11, name='Circle1', positionId=34, position=null, radius=3.0}";

        assertEquals(expected, c.toString());

    }

    @Test
    void testEquals() {
        StpCircle c1 = new StpCircle(11, "Circle1", 34, 3.0);
        StpCircle c2 = new StpCircle(11, "Circle1", 34, 3.0);

        assertTrue(c1.equals(c2));
        assertTrue(c2.equals(c1));

    }

    @Test
    void testEqualsNotId() {
        StpCircle c1 = new StpCircle(11, "Circle1", 34, 3.0);
        StpCircle c2 = new StpCircle(12, "Circle1", 34, 3.0);

        assertFalse(c1.equals(c2));
        assertFalse(c2.equals(c1));

    }

    @Test
    void testEqualsNotName() {
        StpCircle c1 = new StpCircle(11, "Circle1", 34, 3.0);
        StpCircle c2 = new StpCircle(11, "Circle2", 34, 3.0);

        assertFalse(c1.equals(c2));
        assertFalse(c2.equals(c1));

    }

    @Test
    void testEqualsNotPositionId() {
        StpCircle c1 = new StpCircle(11, "Circle1", 34, 3.0);
        StpCircle c2 = new StpCircle(11, "Circle1", 35, 3.0);

        assertFalse(c1.equals(c2));
        assertFalse(c2.equals(c1));

    }

    @Test
    void testEqualsNotRadius() {
        StpCircle c1 = new StpCircle(11, "Circle1", 34, 3.0);
        StpCircle c2 = new StpCircle(11, "Circle1", 34, 2.0);

        assertFalse(c1.equals(c2));
        assertFalse(c2.equals(c1));

    }

    @Test
    void testEqualsNotPosition() {

        StpAxis2Placement3D pos = new StpAxis2Placement3D(44, "", 12, 13, 10);

        StpCircle c1 = new StpCircle(11, "Circle1", 34, 3.0);
        StpCircle c2 = new StpCircle(11, "Circle1", 34, 3.0);

        c1.setPosition(pos);

        assertFalse(c1.equals(c2));
        assertFalse(c2.equals(c1));

    }

    @Test
    void testConvertFromIds() {

        ArrayList<StpRepresentationItem> allAvailableEntities = new ArrayList();

        allAvailableEntities.add(new StpAxis2Placement3D(12, "", 34, 45, 55));
        allAvailableEntities.add(new StpAxis2Placement3D(13, "", 35, 46, 56));
        allAvailableEntities.add(new StpAxis2Placement3D(14, "", 36, 47, 57));
        allAvailableEntities.add(new StpAxis2Placement3D(15, "", 37, 48, 58));

        StpCircle circle = new StpCircle(99, "", 13, 10.0);

        circle.convertFromIds(allAvailableEntities);

        assertEquals(circle.getPosition(), allAvailableEntities.get(1));

    }

    @Test
    void testGetMidVector() {

        StpCartesianPoint testLocation = new StpCartesianPoint(12, "", 4, -7, 8);

        StpAxis2Placement3D testPosition = new StpAxis2Placement3D(13, "", 12, 23, 33);

        StpCircle circle = new StpCircle(99, "", 13, 10.0);


        testPosition.setLocation(testLocation);
        circle.setPosition(testPosition);

        StpVector expectedVector = new StpVector(-1, "", 4, -7, 8);


        assertEquals(expectedVector, circle.getCenterVector());



    }
}
