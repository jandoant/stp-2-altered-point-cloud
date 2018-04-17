package com.jandoant.stp_entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StpVertexPointTest {

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals() {

        StpVertexPoint pt1 = new StpVertexPoint(12, "Point1", 13);
        StpVertexPoint pt2 = new StpVertexPoint(12, "Point1", 13);

        assertTrue(pt1.equals(pt2));
        assertTrue(pt2.equals(pt1));

    }

    @Test
    @DisplayName("should return true if the two compared instances have different id-values")
    void testEqualsNotId() {

        StpVertexPoint pt1 = new StpVertexPoint(12, "Point1", 13);
        StpVertexPoint pt2 = new StpVertexPoint(13, "Point1", 13);

        assertFalse(pt1.equals(pt2));
        assertFalse(pt2.equals(pt1));

    }

    @Test
    @DisplayName("should return true if the two compared instances have different name-values")
    void testEqualsNotName() {

        StpVertexPoint pt1 = new StpVertexPoint(12, "Point1", 13);
        StpVertexPoint pt2 = new StpVertexPoint(12, "Point2", 13);

        assertFalse(pt1.equals(pt2));
        assertFalse(pt2.equals(pt1));

    }

    @Test
    @DisplayName("should return true if the two compared instances have different name-values")
    void testEqualsNotvertexGeometryId() {

        StpVertexPoint pt1 = new StpVertexPoint(12, "Point1", 13);
        StpVertexPoint pt2 = new StpVertexPoint(12, "Point1", 14);

        assertFalse(pt1.equals(pt2));
        assertFalse(pt2.equals(pt1));

    }

    ArrayList<StpPoint> possibleVertexGeometries;
    StpVertexPoint point;

    @BeforeEach
    void setUp() {

        //setUp empty Lists
        possibleVertexGeometries = new ArrayList<>();

        //Put all available Directions in List
        StpPoint[] points = {
                new StpCartesianPoint(69, "", 0.0, 0.0, 0.0),
                new StpCartesianPoint(70, "", 0.0, 20.0, 0.0),
                new StpCartesianPoint(72, "", 0.0, 20.0, 30.0),
                new StpCartesianPoint(74, "", 0.0, 0.0, 30.0)
        };
        possibleVertexGeometries.addAll(Arrays.asList(points));
        //extracted Object from String-description
        point = new StpVertexPoint(26, "", 74);
    }

    @Test
    @DisplayName("should write correct VertexGeometry from given List")
    void testConvertFromIdsLocation() {
        //act
        point.convertFromIds(possibleVertexGeometries);
        //expectations
        StpCartesianPoint expectedPoint = (StpCartesianPoint) possibleVertexGeometries.get(3);
        //assert
        assertTrue(point.getVertexGeometry().equals(expectedPoint));
    }

}