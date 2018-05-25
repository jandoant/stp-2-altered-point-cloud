package com.jandoant.stp_entities;

import Jama.Matrix;
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

        plane.convertFromIds(allAvailableEntities);

        StpAxis2Placement3D expectedPosition = (StpAxis2Placement3D) allAvailableEntities.get(1);
        StpAxis2Placement3D actualPosition = plane.getPosition();

        assertTrue(actualPosition.equals(expectedPosition));
    }

    @Test
    void testGetLocationVector() {

        //SetUp
        StpAxis2Placement3D position = new StpAxis2Placement3D(34, "", 12, 13, 14);
        position.setLocation(new StpCartesianPoint(12, "", 3, 5, -1));

        StpPlane plane = new StpPlane(92, "", 34);
        plane.setPosition(position);

        //act
        StpVector actualLocationVector = plane.getLocationVector();

        //assert
        StpVector expectedLocationVector = new StpVector(-1, "", 3, 5, -1);
        assertEquals(expectedLocationVector, actualLocationVector);

    }

    @Test
    void testGetNormalVector() {

        //SetUp
        StpAxis2Placement3D position = new StpAxis2Placement3D(34, "", 12, 13, 14);
        position.setAxis(new StpDirection(13, "", 1, 0, 0));

        StpPlane plane = new StpPlane(92, "", 34);
        plane.setPosition(position);

        //act
        StpVector actualNormalVector = plane.getNormalVector();

        //assert
        StpVector expectedNormalVector = new StpVector(-1, "", 1, 0, 0);
        assertEquals(expectedNormalVector, actualNormalVector);

    }

    @Test
    void testGetDirectionVectors() {

        //SetUp
        StpAxis2Placement3D position = new StpAxis2Placement3D(34, "", 12, 13, 14);
        position.setAxis(new StpDirection(13, "", 0, 0, 1));
        position.setRefDirection(new StpDirection(14, "", 1, 0, 0));

        StpPlane plane = new StpPlane(92, "", 34);
        plane.setPosition(position);

        //act
        StpVector[] directionVectors = plane.getDirectionVectors();

        //assert
        StpVector dirVector0 = new StpVector(-1, "", 1, 0, 0);
        StpVector dirVector1 = new StpVector(-1, "", 0, 1, 0);

        assertEquals(directionVectors[0], dirVector0);
        assertEquals(directionVectors[1], dirVector1);

    }

    @Test
    void testGetTransformationMatrixWorldToLocal() {

        //setup
        StpDirection axis = new StpDirection(42, "center_axis", 1, 0, 0);
        StpDirection refDirection = new StpDirection(43, "ref_direction", 0, 1, 0);

        StpAxis2Placement3D position = new StpAxis2Placement3D(31, "", 41, 42, 43);
        position.setAxis(axis);
        position.setRefDirection(refDirection);

        StpPlane plane = new StpPlane(11, "", 31);
        plane.setPosition(position);

        //act
        Matrix actualMatrix = plane.getXYZtoUVWTransformationMatrix();

        //assert

        double[][] expectedVals = {
                {0.0, 1.0, 0.0},
                {0.0, 0.0, 1.0},
                {1.0, 0.0, 0.0}
        };

        assertArrayEquals(expectedVals, actualMatrix.getArray());

    }

    @Test
    void testGetTransformationMatrixLocalToWorld() {

        //setup
        StpDirection axis = new StpDirection(42, "center_axis", 1, 0, 0);
        StpDirection refDirection = new StpDirection(43, "ref_direction", 0, 1, 0);

        StpAxis2Placement3D position = new StpAxis2Placement3D(31, "", 41, 42, 43);
        position.setAxis(axis);
        position.setRefDirection(refDirection);

        StpPlane plane = new StpPlane(11, "", 31);
        plane.setPosition(position);

        //act
        Matrix actualMatrix = plane.getUVWToXYZTransformationMatrix();

        //assert

        double[][] expectedVals = {
                {0.0, 0.0, 1.0},
                {1.0, 0.0, 0.0},
                {0.0, 1.0, 0.0}
        };

        assertArrayEquals(expectedVals, actualMatrix.getArray());

    }

    @AfterEach
    void tearDown() {
        allAvailableEntities = null;
        plane = null;
    }
}