package com.jandoant.stp_entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StpEdgeCurveTest {

    @Test
    @DisplayName("should display the correct Message to describe Object-Instance")
    void testToStringWithTrue() {

        String testDescription = "#27=EDGE_CURVE('',#23,#24,#15,.T.);";

        StpEdgeCurve testEdgeCurve = new StpEdgeCurve(
                27,
                "",
                23,
                24,
                15,
                true);

        String expected = "StpEdgeCurve{id=27, name='', edgeStartVertexId=23, edgeStartVertex=null, edgeEndVertexId=24, edgeEndVertex=null, edgeGeometryId=15, edgeGeometry=null, sameSense=true}";

        String actual = testEdgeCurve.toString();

        assertTrue(actual.equals(expected));

    }

    @Test
    @DisplayName("should display the correct Message to describe another Object-Instance")
    void testToStringWithFalse() {

        String testDescription = "#27=EDGE_CURVE('',#23,#24,#15,.F.);";

        StpEdgeCurve testEdgeCurve = new StpEdgeCurve(
                27,
                "",
                23,
                24,
                15,
                false);

        String expected = "StpEdgeCurve{id=27, name='', edgeStartVertexId=23, edgeStartVertex=null, edgeEndVertexId=24, edgeEndVertex=null, edgeGeometryId=15, edgeGeometry=null, sameSense=false}";

        String actual = testEdgeCurve.toString();

        assertTrue(actual.equals(expected));

    }

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals() {
        //setup
        StpEdgeCurve curve1 = new StpEdgeCurve(27, "Curve1", 23, 24, 15, true);
        StpEdgeCurve curve2 = new StpEdgeCurve(27, "Curve1", 23, 24, 15, true);
        //assert
        assertTrue(curve1.equals(curve2));
        assertTrue(curve2.equals(curve1));
    }

    @Test
    @DisplayName("should return true if the two compared instances have different id-values")
    void testEqualsNotId() {
        //setup
        StpEdgeCurve curve1 = new StpEdgeCurve(27, "Curve1", 23, 24, 15, true);
        StpEdgeCurve curve2 = new StpEdgeCurve(28, "Curve1", 23, 24, 15, true);
        //assert
        assertFalse(curve1.equals(curve2));
        assertFalse(curve2.equals(curve1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different name-values")
    void testEqualsNotName() {
        //setup
        StpEdgeCurve curve1 = new StpEdgeCurve(27, "Curve1", 23, 24, 15, true);
        StpEdgeCurve curve2 = new StpEdgeCurve(27, "Curve2", 23, 24, 15, true);
        //assert
        assertFalse(curve1.equals(curve2));
        assertFalse(curve2.equals(curve1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different edgeStartVertexId-values")
    void testEqualsNotedgeStartVertexId() {
        //setup
        StpEdgeCurve curve1 = new StpEdgeCurve(27, "Curve1", 23, 24, 15, true);
        StpEdgeCurve curve2 = new StpEdgeCurve(27, "Curve1", 24, 24, 15, true);
        //assert
        assertFalse(curve1.equals(curve2));
        assertFalse(curve2.equals(curve1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different edgeStartVertexId-values")
    void testEqualsNotedgeEndVertexId() {
        //setup
        StpEdgeCurve curve1 = new StpEdgeCurve(27, "Curve1", 23, 24, 15, true);
        StpEdgeCurve curve2 = new StpEdgeCurve(27, "Curve1", 23, 25, 15, true);
        //assert
        assertFalse(curve1.equals(curve2));
        assertFalse(curve2.equals(curve1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different edgeStartVertexId-values")
    void testEqualsNotedgeGeometryId() {
        //setup
        StpEdgeCurve curve1 = new StpEdgeCurve(27, "Curve1", 23, 24, 15, true);
        StpEdgeCurve curve2 = new StpEdgeCurve(27, "Curve1", 23, 24, 16, true);
        //assert
        assertFalse(curve1.equals(curve2));
        assertFalse(curve2.equals(curve1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different edgeStartVertexId-values")
    void testEqualsNotSameSense() {
        //setup
        StpEdgeCurve curve1 = new StpEdgeCurve(27, "Curve1", 23, 24, 15, true);
        StpEdgeCurve curve2 = new StpEdgeCurve(27, "Curve1", 23, 24, 15, false);
        //assert
        assertFalse(curve1.equals(curve2));
        assertFalse(curve2.equals(curve1));
    }

    ArrayList<StpVertex> possibleVertices;
    ArrayList<StpCurve> possibleEdgeGeometries;
    StpEdgeCurve edgeCurve;

    @BeforeEach
    void setUp() {

        //setUp empty Lists
        possibleVertices = new ArrayList<>();
        possibleEdgeGeometries = new ArrayList<>();

        //Put all available Cartesian Points in List
        StpVertex[] vertices = {
                new StpVertexPoint(12, "", 44),
                new StpVertexPoint(13, "", 45),
                new StpVertexPoint(14, "", 46),
                new StpVertexPoint(15, "", 47)
        };
        possibleVertices.addAll(Arrays.asList(vertices));

        //Put all available Directions in List
        StpCurve[] curves = {
                new StpLine(22, "", 34, 35),
                new StpLine(23, "", 36, 37),
                new StpLine(24, "", 38, 39),
                new StpLine(25, "", 40, 41),
        };

        possibleEdgeGeometries.addAll(Arrays.asList(curves));

        //extracted Object from String-description
        edgeCurve = new StpEdgeCurve(10, "", 13, 15, 24, true);
    }

    @Test
    @DisplayName("should write correct edgeStartVertex from given Lists")
    void testConvertFromIdsEdgeStartVertex() {

        //act -- fill the fields
        edgeCurve.convertFromIds(possibleVertices, possibleEdgeGeometries);
        //expectations
        StpVertex expectedEgeStartVertex = possibleVertices.get(1);
        //assert
        assertTrue(edgeCurve.getEdgeStartVertex().equals(expectedEgeStartVertex));
    }

    @Test
    @DisplayName("should write correct edgeEndVertex from given Lists")
    void testConvertFromIdsEdgeEndVertex() {

        //act -- fill the fields
        edgeCurve.convertFromIds(possibleVertices, possibleEdgeGeometries);
        //expectations
        StpVertex expectedEdgeEndVertex = possibleVertices.get(3);
        //assert
        assertTrue(edgeCurve.getEdgeEndVertex().equals(expectedEdgeEndVertex));
    }

    @Test
    @DisplayName("should write correct edgeGeometry from given Lists")
    void testConvertFromIdsEdgeGeometry() {

        //act -- fill the fields
        edgeCurve.convertFromIds(possibleVertices, possibleEdgeGeometries);
        //expectations
        StpCurve expectedEdgeGeometry = possibleEdgeGeometries.get(2);
        //assert
        assertTrue(edgeCurve.getEdgeGeometry().equals(expectedEdgeGeometry));
    }

    @AfterEach
    void tearDown() {
        possibleVertices = null;
        possibleEdgeGeometries = null;
        edgeCurve = null;
    }
}