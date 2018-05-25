package com.jandoant.stp_entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StpOrientedEdgeTest {

    ArrayList<StpRepresentationItem> allAvailableEntities;

    StpOrientedEdge edgeNormal;
    StpOrientedEdge edgeWithInheritance1;
    StpOrientedEdge edgeWithInheritance2;
    StpOrientedEdge edgeWithInheritance3;

    @Test
    void testToString() {

        StpOrientedEdge oEdge = new StpOrientedEdge(12, "OrientedEdge1", 13, 14, 15, true);

        String expected = "StpOrientedEdge{id=12, name='OrientedEdge1', edgeStartVertexId=13, edgeStartVertex=null, edgeEndVertexId=14, edgeEndVertex=null, edgeElementId=15, edgeElement=null, orientation=true}";

        assertTrue(oEdge.toString().equals(expected));
    }

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals() {

        StpOrientedEdge e1 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);
        StpOrientedEdge e2 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);

        assertTrue(e1.equals(e2));
        assertTrue(e2.equals(e1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different id-values")
    void testEqualsNotId() {

        StpOrientedEdge e1 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);
        StpOrientedEdge e2 = new StpOrientedEdge(11, "Edge1", 13, 14, 15, true);

        assertFalse(e1.equals(e2));
        assertFalse(e2.equals(e1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different name-values")
    void testEqualsNotName() {

        StpOrientedEdge e1 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);
        StpOrientedEdge e2 = new StpOrientedEdge(12, "Edge2", 13, 14, 15, true);

        assertFalse(e1.equals(e2));
        assertFalse(e2.equals(e1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different edgeStartVertexId-values")
    void testEqualsNotEdgeStartVertexId() {

        StpOrientedEdge e1 = new StpOrientedEdge(12, "Edge1", 11, 14, 15, true);
        StpOrientedEdge e2 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);

        assertFalse(e1.equals(e2));
        assertFalse(e2.equals(e1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different edgeStartVertex-values")
    void testEqualsNotEdgeStartVertex() {

        StpOrientedEdge e1 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);
        StpOrientedEdge e2 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);

        e1.setEdgeStartVertex(new StpVertexPoint(23, "", 10));

        assertFalse(e1.equals(e2));
        assertFalse(e2.equals(e1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different edgeEndVertexId-values")
    void testEqualsNotEdgeEndVertexId() {

        StpOrientedEdge e1 = new StpOrientedEdge(12, "Edge1", 13, 16, 15, true);
        StpOrientedEdge e2 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);

        assertFalse(e1.equals(e2));
        assertFalse(e2.equals(e1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different edgeEndVertex-values")
    void testEqualsNotEdgeEndVertex() {

        StpOrientedEdge e1 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);
        StpOrientedEdge e2 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);

        e1.setEdgeEndVertex(new StpVertexPoint(23, "", 10));

        assertFalse(e1.equals(e2));
        assertFalse(e2.equals(e1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different edgeElementId-values")
    void testEqualsNotEdgeElementId() {

        StpOrientedEdge e1 = new StpOrientedEdge(12, "Edge1", 13, 14, 16, true);
        StpOrientedEdge e2 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);

        assertFalse(e1.equals(e2));
        assertFalse(e2.equals(e1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different edgeElement-values")
    void testEqualsNotEdgeElement() {

        StpOrientedEdge e1 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);

        StpOrientedEdge e2 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);

        e2.setEdgeElement(new StpEdgeCurve(33, "", 56, 57, 58, true));

        assertFalse(e1.equals(e2));
        assertFalse(e2.equals(e1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different orientation-values")
    void testEqualsNotOrientation() {

        StpOrientedEdge e1 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, false);
        StpOrientedEdge e2 = new StpOrientedEdge(12, "Edge1", 13, 14, 15, true);

        assertFalse(e1.equals(e2));
        assertFalse(e2.equals(e1));
    }

    @BeforeEach
    void setUp() {

        //setUp empty Lists
        allAvailableEntities = new ArrayList<>();

        //Put all available Cartesian Points in List
        StpRepresentationItem[] entities = {
                new StpVertexPoint(11, "", 74),
                new StpVertexPoint(12, "", 75),
                new StpVertexPoint(13, "", 76),
                new StpVertexPoint(14, "", 77),
                new StpVertexPoint(15, "", 78),
                new StpEdgeCurve(41, "", 12, 13, 61, true),
                new StpEdgeCurve(42, "", 14, 15, 61, false),
                new StpEdgeCurve(43, "", 14, 12, 61, true),
                new StpEdgeCurve(44, "", 11, 15, 64, true),
        };
        allAvailableEntities.addAll(Arrays.asList(entities));

        //extracted Object from String-description
        edgeNormal = new StpOrientedEdge(91, "", 12, 13, 44, true);

        edgeWithInheritance1 = new StpOrientedEdge(92, "", 44, 44, 44, true);
        edgeWithInheritance2 = new StpOrientedEdge(93, "", 12, 44, 44, true);
        edgeWithInheritance3 = new StpOrientedEdge(93, "", 44, 13, 44, true);
    }

    @Test
    @DisplayName("should write correct EdgeStartVertexId from given Lists")
    void testConvertFromIdsEdgeStartVertexId() {

        edgeNormal.convertFromIds(allAvailableEntities);

        //expectations
        int expectedEdgeStartVertexId = 12;

        //assert
        assertEquals(expectedEdgeStartVertexId, edgeNormal.getEdgeStartVertexId());
    }

    @Test
    @DisplayName("should write correct EdgeStartVertexId from given Lists")
    void testConvertFromIdsEdgeEndVertexId() {

        edgeNormal.convertFromIds(allAvailableEntities);

        //expectations
        int expectedEdgeStartVertexId = 13;

        //assert
        assertEquals(edgeNormal.getEdgeEndVertexId(), expectedEdgeStartVertexId);
    }

    @Test
    @DisplayName("should write correct EdgeStartVertex from given Lists")
    void testConvertFromIdsEdgeStartVertex() {

        //act -- fill the fields
        edgeNormal.convertFromIds(allAvailableEntities);
        //expectations
        StpVertex expectedEdgeStartVertex = (StpVertex) allAvailableEntities.get(1);
        //assert
        assertTrue(edgeNormal.getEdgeStartVertex().equals(expectedEdgeStartVertex));
    }

    @Test
    @DisplayName("should write correct EdgeEndVertex from given Lists")
    void testConvertFromIdsEdgeEndVertex() {

        //act -- fill the fields
        edgeNormal.convertFromIds(allAvailableEntities);
        //expectations
        StpVertex expectedEdgeEndVertex = (StpVertex) allAvailableEntities.get(2);
        //assert
        assertTrue(edgeNormal.getEdgeEndVertex().equals(expectedEdgeEndVertex));
    }

    @Test
    @DisplayName("should write correct EdgeElement from given Lists")
    void testConvertFromIdsEdgeElement() {

        //act -- fill the fields
        edgeNormal.convertFromIds(allAvailableEntities);
        //expectations
        StpEdge expectedEdgeElement = (StpEdge) allAvailableEntities.get(8);

        //assert
        assertTrue(edgeNormal.getEdgeElement().equals(expectedEdgeElement));
    }

    @Test
    @DisplayName("should write correct EdgeStartVertexId (inherited) from given Lists")
    void testConvertFromIdsEdgeStartVertexIdInherited1() {

        edgeWithInheritance1.convertFromIds(allAvailableEntities);

        //expectations
        int expectedEdgeStartVertexId = 11;

        //assert
        assertEquals(edgeWithInheritance1.getEdgeStartVertexId(), expectedEdgeStartVertexId);
    }

    @Test
    @DisplayName("should write correct EdgeStartVertexId (inherited) from given Lists")
    void testConvertFromIdsEdgeEndVertexIdInherited1() {

        edgeWithInheritance1.convertFromIds(allAvailableEntities);

        //expectations
        int expectedEdgeEndVertexId = 15;

        //assert
        assertEquals(edgeWithInheritance1.getEdgeEndVertexId(), expectedEdgeEndVertexId);
    }

    @Test
    @DisplayName("should write correct EdgeStartVertex (inherited) from given Lists")
    void testConvertFromIdsEdgeStartVertexInherited1() {

        StpEdge edgeElement = (StpEdge) allAvailableEntities.get(8);
        edgeElement.convertFromIds(allAvailableEntities);

        edgeWithInheritance1.convertFromIds(allAvailableEntities);

        //expectations
        StpVertex expectedEdgeStartVertex = (StpVertex) allAvailableEntities.get(0);

        StpVertex actualEdgeStartVertex = edgeWithInheritance1.getEdgeStartVertex();

        //assert
        assertTrue(expectedEdgeStartVertex.equals(actualEdgeStartVertex));
    }

    @Test
    @DisplayName("should write correct EdgeEndVertex (inherited) from given Lists")
    void testConvertFromIdsEdgeEndVertexInherited1() {

        //act -- fill the fields
        StpEdge edgeElement = (StpEdge) allAvailableEntities.get(8);
        edgeElement.convertFromIds(allAvailableEntities);

        edgeWithInheritance1.convertFromIds(allAvailableEntities);

        //expectations
        StpVertex expectedEdgeEndVertex = (StpVertex) allAvailableEntities.get(4);

        StpVertex actualEdgeEndVertex = edgeWithInheritance1.getEdgeEndVertex();

        //assert
        assertTrue(expectedEdgeEndVertex.equals(actualEdgeEndVertex));
    }

    @Test
    @DisplayName("should write correct EdgeStartVertexId (inherited) from given Lists")
    void testConvertFromIdsEdgeStartVertexIdInherited2() {

        edgeWithInheritance2.convertFromIds(allAvailableEntities);

        //expectations
        int expectedEdgeStartVertexId = 12;

        //assert
        assertEquals(edgeWithInheritance2.getEdgeStartVertexId(), expectedEdgeStartVertexId);
    }

    @Test
    @DisplayName("should write correct EdgeEndVertexId (inherited) from given Lists")
    void testConvertFromIdsEdgeEndVertexIdInherited2() {

        edgeWithInheritance2.convertFromIds(allAvailableEntities);
        
        //expectations
        int expectedEdgeEndVertexId = 15;

        //assert
        assertEquals(edgeWithInheritance2.getEdgeEndVertexId(), expectedEdgeEndVertexId);
    }

    @Test
    @DisplayName("should write correct EdgeStartVertex (inherited) from given Lists")
    void testConvertFromIdsEdgeStartVertexInherited2() {

        //act -- fill the fields
        StpEdge edgeElement = (StpEdge) allAvailableEntities.get(8);
        edgeElement.convertFromIds(allAvailableEntities);

        edgeWithInheritance2.convertFromIds(allAvailableEntities);

        //expectations
        StpVertex expectedEdgeStartVertex = (StpVertex) allAvailableEntities.get(1);

        StpVertex actualEdgeStartVertex = edgeWithInheritance2.getEdgeStartVertex();

        //assert
        assertTrue(expectedEdgeStartVertex.equals(actualEdgeStartVertex));
    }

    @Test
    @DisplayName("should write correct EdgeEndVertex (inherited) from given Lists")
    void testConvertFromIdsEdgeEndVertexInherited2() {

        //act -- fill the fields
        StpEdge edgeElement = (StpEdge) allAvailableEntities.get(8);
        edgeElement.convertFromIds(allAvailableEntities);

        edgeWithInheritance2.convertFromIds(allAvailableEntities);

        //expectations
        StpVertex expectedEdgeEndVertex = (StpVertex) allAvailableEntities.get(4);

        StpVertex actualEdgeEndVertex = edgeWithInheritance2.getEdgeEndVertex();

        //assert
        assertTrue(expectedEdgeEndVertex.equals(actualEdgeEndVertex));
    }

    @Test
    @DisplayName("should write correct EdgeStartVertexId (inherited) from given Lists")
    void testConvertFromIdsEdgeStartVertexIdInherited3() {

        edgeWithInheritance3.convertFromIds(allAvailableEntities);

        //expectations
        int expectedEdgeStartVertexId = 11;

        //assert
        assertEquals(edgeWithInheritance3.getEdgeStartVertexId(), expectedEdgeStartVertexId);
    }

    @Test
    @DisplayName("should write correct EdgeStartVertexId (inherited) from given Lists")
    void testConvertFromIdsEdgeEndVertexIdInherited3() {

        edgeWithInheritance3.convertFromIds(allAvailableEntities);

        //expectations
        int expectedEdgeEndVertexId = 13;

        //assert
        assertEquals(edgeWithInheritance3.getEdgeEndVertexId(), expectedEdgeEndVertexId);
    }

    @Test
    @DisplayName("should write correct EdgeStartVertex (inherited) from given Lists")
    void testConvertFromIdsEdgeStartVertexInherited3() {

        //act -- fill the fields
        StpEdge edgeElement = (StpEdge) allAvailableEntities.get(8);
        edgeElement.convertFromIds(allAvailableEntities);

        edgeWithInheritance3.convertFromIds(allAvailableEntities);

        //expectations
        StpVertex expectedEdgeStartVertex = (StpVertex) allAvailableEntities.get(0);
        StpVertex actualEdgeStartVertex = edgeWithInheritance3.getEdgeStartVertex();

        //assert
        assertTrue(expectedEdgeStartVertex.equals(actualEdgeStartVertex));
    }

    @Test
    @DisplayName("should write correct EdgeEndVertex (inherited) from given Lists")
    void testConvertFromIdsEdgeEndVertexInherited3() {

        //act -- fill the fields
        StpEdge edgeElement = (StpEdge) allAvailableEntities.get(8);
        edgeElement.convertFromIds(allAvailableEntities);

        edgeWithInheritance3.convertFromIds(allAvailableEntities);

        //expectations
        StpVertex expectedEdgeEndVertex = (StpVertex) allAvailableEntities.get(2);

        StpVertex actualEdgeEndVertex = edgeWithInheritance3.getEdgeEndVertex();

        //assert
        assertTrue(expectedEdgeEndVertex.equals(actualEdgeEndVertex));
    }

    @AfterEach
    void tearDown() {
        allAvailableEntities = null;

        edgeNormal = null;
        edgeWithInheritance1 = null;
        edgeWithInheritance2 = null;
        edgeWithInheritance3 = null;
    }


}