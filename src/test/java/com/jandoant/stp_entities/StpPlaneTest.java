package com.jandoant.stp_entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StpPlaneTest {

    ArrayList<StpRepresentationItem> allAvailableEntities;
    StpPlane plane;

    @Test
    void testToString() {

        StpPlane plane = new StpPlane(12, "Plane1", 13);

        String expected = "StpPlane{id=12, name='Plane1', positionId=13, position=null}";

        assertEquals(plane.toString(), expected);
    }

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals() {

        StpPlane plane1 = new StpPlane(12, "Plane1", 13);
        StpPlane plane2 = new StpPlane(12, "Plane1", 13);

        assertTrue(plane1.equals(plane2));
        assertTrue(plane2.equals(plane1));

    }

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals2() {

        StpPlane plane1 = new StpPlane(12, "Plane1", 13);
        StpPlane plane2 = new StpPlane(12, "Plane1", 13);

        StpAxis2Placement3D pos1 = new StpAxis2Placement3D(34, "", 11, 67, 80);
        StpAxis2Placement3D pos2 = new StpAxis2Placement3D(34, "", 11, 67, 80);

        plane1.setPosition(pos1);
        plane2.setPosition(pos2);

        assertTrue(plane1.equals(plane2));
        assertTrue(plane2.equals(plane1));

    }

    @Test
    @DisplayName("should return true if the two compared instances have different id-values")
    void testEqualsNotId() {

        StpPlane plane1 = new StpPlane(12, "Plane1", 13);
        StpPlane plane2 = new StpPlane(13, "Plane1", 13);

        assertFalse(plane1.equals(plane2));
        assertFalse(plane2.equals(plane1));

    }

    @Test
    @DisplayName("should return true if the two compared instances have different name-values")
    void testEqualsNotName() {

        StpPlane plane1 = new StpPlane(12, "Plane1", 13);
        StpPlane plane2 = new StpPlane(12, "Plane2", 13);

        assertFalse(plane1.equals(plane2));
        assertFalse(plane2.equals(plane1));

    }

    @Test
    @DisplayName("should return true if the two compared instances have different positionId-values")
    void testEqualsNotPositionId() {

        StpPlane plane1 = new StpPlane(12, "Plane1", 13);
        StpPlane plane2 = new StpPlane(12, "Plane1", 14);

        assertFalse(plane1.equals(plane2));
        assertFalse(plane2.equals(plane1));

    }

    @Test
    @DisplayName("should return true if the two compared instances have different position-values")
    void testEqualsNotPosition() {

        StpPlane plane1 = new StpPlane(12, "Plane1", 13);
        StpPlane plane2 = new StpPlane(12, "Plane1", 14);

        StpAxis2Placement3D pos1 = new StpAxis2Placement3D(34, "", 11, 67, 80);
        StpAxis2Placement3D pos2 = new StpAxis2Placement3D(35, "", 11, 68, 81);

        plane1.setPosition(pos1);
        plane2.setPosition(pos2);

        assertFalse(plane1.equals(plane2));
        assertFalse(plane2.equals(plane1));

    }

    @BeforeEach
    void setUp() {

        allAvailableEntities = new ArrayList<>();

        StpRepresentationItem[] entitiesArr = {
                new StpAxis2Placement3D(57, "", 67, 59, 60),
                new StpAxis2Placement3D(58, "", 68, 81, 61),
                new StpAxis2Placement3D(59, "", 69, 80, 62),
        };

        allAvailableEntities.addAll(Arrays.asList(entitiesArr));

        plane = new StpPlane(35, "", 58);

    }

    @Test
    @DisplayName("should write correct Position from given Lists")
    void testConvertFromIdsPosition() {

        plane.convertFromIDs(allAvailableEntities);

        StpAxis2Placement3D expectedPosition = (StpAxis2Placement3D) allAvailableEntities.get(1);
        StpAxis2Placement3D actualPosition = plane.getPosition();

        assertTrue(actualPosition.equals(expectedPosition));
    }

    @AfterEach
    void tearDown() {
        allAvailableEntities = null;
        plane = null;
    }
}