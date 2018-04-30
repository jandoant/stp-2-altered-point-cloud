package com.jandoant.stp_entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StpCylindricalSurfaceTest {

    ArrayList<StpRepresentationItem> allAvailableEntities;
    StpCylindricalSurface cyl;

    @Test
    void testToString() {

        StpCylindricalSurface cyl = new StpCylindricalSurface(12, "CylindricalSurface1", 13, 14.0);

        String expected = "StpCylindricalSurface{id=12, name='CylindricalSurface1', positionId=13, position=null, radius=14.0}";

        assertEquals(cyl.toString(), expected);

    }

    @Test
    @DisplayName("should return true if the compared instances have the same values")
    void testEquals() {

        StpCylindricalSurface cyl1 = new StpCylindricalSurface(12, "Cyl1", 34, 10.0);
        StpCylindricalSurface cyl2 = new StpCylindricalSurface(12, "Cyl1", 34, 10.0);

        assertEquals(cyl1, cyl2);
        assertEquals(cyl2, cyl1);

    }

    @Test
    @DisplayName("should return true if the compared instances have the same values and get assigned the same PositionObject")
    void testEquals2() {

        StpCylindricalSurface cyl1 = new StpCylindricalSurface(12, "Cyl1", 34, 10.0);
        StpCylindricalSurface cyl2 = new StpCylindricalSurface(12, "Cyl1", 34, 10.0);

        StpAxis2Placement3D pos1 = new StpAxis2Placement3D(34, "", 11, 67, 80);
        StpAxis2Placement3D pos2 = new StpAxis2Placement3D(34, "", 11, 67, 80);

        cyl1.setPosition(pos1);
        cyl2.setPosition(pos2);

        assertEquals(cyl1, cyl2);
        assertEquals(cyl2, cyl1);

    }

    @Test
    @DisplayName("should return true if the compared instances have different id-values")
    void testEqualsNotId() {

        StpCylindricalSurface cyl1 = new StpCylindricalSurface(12, "Cyl1", 34, 10.0);
        StpCylindricalSurface cyl2 = new StpCylindricalSurface(13, "Cyl1", 34, 10.0);

        assertNotEquals(cyl1, cyl2);
        assertNotEquals(cyl2, cyl1);

    }

    @Test
    @DisplayName("should return true if the compared instances have different name-values")
    void testEqualsNotName() {

        StpCylindricalSurface cyl1 = new StpCylindricalSurface(12, "Cyl1", 34, 10.0);
        StpCylindricalSurface cyl2 = new StpCylindricalSurface(12, "Cyl2", 34, 10.0);

        assertNotEquals(cyl1, cyl2);
        assertNotEquals(cyl2, cyl1);

    }

    @Test
    @DisplayName("should return true if the compared instances have different positionId-values")
    void testEqualsNotPositionId() {

        StpCylindricalSurface cyl1 = new StpCylindricalSurface(12, "Cyl1", 34, 10.0);
        StpCylindricalSurface cyl2 = new StpCylindricalSurface(12, "Cyl1", 35, 10.0);

        assertNotEquals(cyl1, cyl2);
        assertNotEquals(cyl2, cyl1);

    }

    @Test
    @DisplayName("should return true if the compared instances have different radius-values")
    void testEqualsNotRadius() {

        StpCylindricalSurface cyl1 = new StpCylindricalSurface(12, "Cyl1", 34, 10.0);
        StpCylindricalSurface cyl2 = new StpCylindricalSurface(12, "Cyl1", 34, 10.1);

        assertNotEquals(cyl1, cyl2);
        assertNotEquals(cyl2, cyl1);

    }

    @Test
    @DisplayName("should return true if the compared instances have different position-values")
    void testEqualsNotPosition() {

        StpCylindricalSurface cyl1 = new StpCylindricalSurface(12, "Cyl1", 34, 10.0);
        StpCylindricalSurface cyl2 = new StpCylindricalSurface(12, "Cyl1", 34, 10.1);

        StpAxis2Placement3D pos1 = new StpAxis2Placement3D(34, "", 11, 67, 80);
        StpAxis2Placement3D pos2 = new StpAxis2Placement3D(35, "", 11, 67, 80);

        cyl1.setPosition(pos1);
        cyl2.setPosition(pos2);

        assertNotEquals(cyl1, cyl2);
        assertNotEquals(cyl2, cyl1);

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

        cyl = new StpCylindricalSurface(35, "", 58, 13.0);

    }

    @Test
    @DisplayName("should write correct Position from given Lists")
    void testConvertFromIdsPosition() {

        cyl.convertFromIDs(allAvailableEntities);

        StpAxis2Placement3D expected = (StpAxis2Placement3D) allAvailableEntities.get(1);
        StpAxis2Placement3D actual = cyl.getPosition();

        assertTrue(actual.equals(expected));
    }

    @AfterEach
    void tearDown() {
        allAvailableEntities = null;
        cyl = null;
    }

}



