package com.jandoant.stp_entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StpVertexPointTest {

    ArrayList<StpRepresentationItem> allAvailableEntities;
    StpVertexPoint vpt;

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

    @Test
    void testConvertToCartesianPoint() {

        //setup
        StpVertexPoint vp = new StpVertexPoint(12, "", 34);

        vp.setVertexGeometry(new StpCartesianPoint(34, "", 3.3, 1.2, -6.7));

        //act
        StpCartesianPoint actualPoint = vp.convertToCartesianPoint();

        //assert
        StpCartesianPoint expectedPoint = new StpCartesianPoint(-1, "", 3.3, 1.2, -6.7);

        assertEquals(expectedPoint, actualPoint);

    }

    @BeforeEach
    void setUp() {

        //setUp empty Lists
        allAvailableEntities = new ArrayList<>();

        //Put all available Directions in List
        StpPoint[] points = {
                new StpCartesianPoint(69, "", 0.0, 0.0, 0.0),
                new StpCartesianPoint(70, "", 0.0, 20.0, 0.0),
                new StpCartesianPoint(72, "", 0.0, 20.0, 30.0),
                new StpCartesianPoint(74, "", 0.0, 0.0, 30.0)
        };
        allAvailableEntities.addAll(Arrays.asList(points));

        //extracted Object from String-description
        vpt = new StpVertexPoint(26, "", 74);
    }

    @Test
    @DisplayName("should write correct VertexGeometry from given List")
    void testConvertFromIdsLocation() {
        //act
        vpt.convertFromIds(allAvailableEntities);
        //expectations
        StpPoint expectedPoint = (StpPoint) allAvailableEntities.get(3);
        //assert
        assertTrue(vpt.getVertexGeometry().equals(expectedPoint));
    }

    @Test
    void testToString() {

        StpVertexPoint pt = new StpVertexPoint(12, "Point1", 13);

        String expected = "StpVertexPoint{id=12, name='Point1', vertexGeometryId=13, vertexGeometry=null}";

        assertTrue(pt.toString().equals(expected));

    }
}