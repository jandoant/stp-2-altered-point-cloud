package com.jandoant.stp_entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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




}