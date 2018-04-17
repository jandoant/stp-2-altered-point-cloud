package com.jandoant.stp_entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StpAxis2Placement3DTest {

    ArrayList<StpCartesianPoint> possibleLocations;
    ArrayList<StpDirection> possibleDirections;
    StpAxis2Placement3D placement;

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals() {

        StpAxis2Placement3D plmt1 = new StpAxis2Placement3D(23, "Plmt1", 12, 13, 14);
        StpAxis2Placement3D plmt2 = new StpAxis2Placement3D(23, "Plmt1", 12, 13, 14);

        assertTrue(plmt1.equals(plmt2));
        assertTrue(plmt2.equals(plmt1));
    }

    @Test
    @DisplayName("should return false if the two compared instances different id-values")
    void testEqualsNotId() {

        StpAxis2Placement3D plmt1 = new StpAxis2Placement3D(22, "Plmt1", 12, 13, 14);
        StpAxis2Placement3D plmt2 = new StpAxis2Placement3D(23, "Plmt1", 12, 13, 14);

        assertFalse(plmt1.equals(plmt2));
        assertFalse(plmt2.equals(plmt1));
    }

    @Test
    @DisplayName("should return false if the two compared instances different name-values")
    void testEqualsNotName() {

        StpAxis2Placement3D plmt1 = new StpAxis2Placement3D(23, "Plmt1", 12, 13, 14);
        StpAxis2Placement3D plmt2 = new StpAxis2Placement3D(23, "Plmt2", 12, 13, 14);

        assertFalse(plmt1.equals(plmt2));
        assertFalse(plmt2.equals(plmt1));
    }

    @Test
    @DisplayName("should return false if the two compared instances different LocationId-values")
    void testEqualsNotLocationId() {

        StpAxis2Placement3D plmt1 = new StpAxis2Placement3D(23, "Plmt1", 12, 13, 14);
        StpAxis2Placement3D plmt2 = new StpAxis2Placement3D(23, "Plmt1", 14, 13, 14);

        assertFalse(plmt1.equals(plmt2));
        assertFalse(plmt2.equals(plmt1));
    }

    @Test
    @DisplayName("should return false if the two compared instances different AxisId-values")
    void testEqualsNotAxisId() {

        StpAxis2Placement3D plmt1 = new StpAxis2Placement3D(23, "Plmt1", 12, 15, 14);
        StpAxis2Placement3D plmt2 = new StpAxis2Placement3D(23, "Plmt1", 12, 13, 14);

        assertFalse(plmt1.equals(plmt2));
        assertFalse(plmt2.equals(plmt1));
    }

    @Test
    @DisplayName("should return false if the two compared instances different refDirectionId-values")
    void testEqualsNotRefDirectionId() {

        StpAxis2Placement3D plmt1 = new StpAxis2Placement3D(23, "Plmt1", 12, 13, 14);
        StpAxis2Placement3D plmt2 = new StpAxis2Placement3D(23, "Plmt1", 12, 13, 16);

        assertFalse(plmt1.equals(plmt2));
        assertFalse(plmt2.equals(plmt1));
    }

    @BeforeEach
    void setUp() {

        //setUp empty Lists
        possibleLocations = new ArrayList<>();
        possibleDirections = new ArrayList<>();

        //Put all available Cartesian Points in List
        StpCartesianPoint[] points = {
                new StpCartesianPoint(76, "", 0.0, 0.0, 30.0),
                new StpCartesianPoint(75, "", 0.0, 20.0, 30.0),
                new StpCartesianPoint(68, "Origin", 0.0, 10.0, 15.0)};

        possibleLocations.addAll(Arrays.asList(points));

        //Put all available Directions in List
        StpDirection[] directions = {
                new StpDirection(61, "center_axis", 1.0, 0.0, 0.0),
                new StpDirection(62, "ref_axis", 0.0, 0.0, 1.0),
                new StpDirection(63, "", 0.0, 1.0, 0.0),
                new StpDirection(64, "", 0.0, 0.0, 1.0)
        };
        possibleDirections.addAll(Arrays.asList(directions));

        //extracted Object from String-description
        placement = new StpAxis2Placement3D(58, "", 68, 61, 62);
    }

    @Test
    @DisplayName("should write correct Location from given Lists")
    void testConvertFromIdsLocation() {

        //act -- fill the fields
        placement.convertFromIds(possibleLocations, possibleDirections);
        //expectations
        StpCartesianPoint expectedLocation = possibleLocations.get(2);
        //assert
        assertTrue(placement.getLocation().equals(expectedLocation));
    }

    @Test
    @DisplayName("should write correct Axis from given Lists")
    void testConvertFromIdsAxis() {

        //act -- fill the fields
        placement.convertFromIds(possibleLocations, possibleDirections);
        //expectations
        StpDirection expectedAxis = possibleDirections.get(0);
        //assert
        assertTrue(placement.getAxis().equals(expectedAxis));
    }

    @Test
    @DisplayName("should write correct RefDirection from given Lists")
    void testConvertFromIdsRefDirection() {

        //act -- fill the fields
        placement.convertFromIds(possibleLocations, possibleDirections);
        //expectation
        StpDirection expectedRefDirection = possibleDirections.get(1);
        //assert
        assertTrue(placement.getRefDirection().equals(expectedRefDirection));
    }

    @AfterEach
    void tearDown() {
        possibleLocations = null;
        possibleDirections = null;
        placement = null;
    }

}