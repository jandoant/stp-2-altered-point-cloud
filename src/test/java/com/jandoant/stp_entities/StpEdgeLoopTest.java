package com.jandoant.stp_entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StpEdgeLoopTest {

    ArrayList<StpRepresentationItem> allAvailableEntities;
    StpEdgeLoop edgeLoop;

    @Test
    void testToString() {

        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(11);
        edgesIds.add(12);
        edgesIds.add(13);
        edgesIds.add(14);

        StpEdgeLoop loop = new StpEdgeLoop(12, "Loop1", edgesIds);

        String expected = "StpEdgeLoop{id=12, name='Loop1', edgesIds=[11, 12, 13, 14], edgesList=[]}";

        assertTrue(loop.toString().equals(expected));
    }

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals() {

        ArrayList<Integer> edgesIds1 = new ArrayList<>();
        edgesIds1.add(11);
        edgesIds1.add(12);
        edgesIds1.add(13);
        edgesIds1.add(14);

        ArrayList<Integer> edgesIds2 = new ArrayList<>();
        edgesIds2.add(11);
        edgesIds2.add(12);
        edgesIds2.add(13);
        edgesIds2.add(14);

        StpEdgeLoop loop1 = new StpEdgeLoop(12, "Loop1", edgesIds1);
        StpEdgeLoop loop2 = new StpEdgeLoop(12, "Loop1", edgesIds2);

        assertTrue(loop1.equals(loop2));
        assertTrue(loop2.equals(loop1));
    }

    @Test
    @DisplayName("should return true if the two compared instances have different id-values")
    void testEqualsNotId() {

        ArrayList<Integer> edgesIds1 = new ArrayList<>();
        edgesIds1.add(11);
        edgesIds1.add(12);
        edgesIds1.add(13);
        edgesIds1.add(14);

        ArrayList<Integer> edgesIds2 = new ArrayList<>();
        edgesIds2.add(11);
        edgesIds2.add(12);
        edgesIds2.add(13);
        edgesIds2.add(14);

        StpEdgeLoop loop1 = new StpEdgeLoop(11, "Loop1", edgesIds1);
        StpEdgeLoop loop2 = new StpEdgeLoop(12, "Loop1", edgesIds2);

        assertFalse(loop1.equals(loop2));
        assertFalse(loop2.equals(loop1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different name-values")
    void testEqualsNotName() {

        ArrayList<Integer> edgesIds1 = new ArrayList<>();
        edgesIds1.add(11);
        edgesIds1.add(12);
        edgesIds1.add(13);
        edgesIds1.add(14);

        ArrayList<Integer> edgesIds2 = new ArrayList<>();
        edgesIds2.add(11);
        edgesIds2.add(12);
        edgesIds2.add(13);
        edgesIds2.add(14);

        StpEdgeLoop loop1 = new StpEdgeLoop(11, "Loop1", edgesIds1);
        StpEdgeLoop loop2 = new StpEdgeLoop(11, "Loop2", edgesIds2);

        assertFalse(loop1.equals(loop2));
        assertFalse(loop2.equals(loop1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different edgesIds-values")
    void testEqualsNotEdgesIds() {

        ArrayList<Integer> edgesIds1 = new ArrayList<>();
        edgesIds1.add(11);
        edgesIds1.add(12);
        edgesIds1.add(13);
        edgesIds1.add(14);

        ArrayList<Integer> edgesIds2 = new ArrayList<>();
        edgesIds2.add(11);
        edgesIds2.add(12);
        edgesIds2.add(13);
        edgesIds2.add(15);

        StpEdgeLoop loop1 = new StpEdgeLoop(11, "Loop1", edgesIds1);
        StpEdgeLoop loop2 = new StpEdgeLoop(11, "Loop1", edgesIds2);

        assertFalse(loop1.equals(loop2));
        assertFalse(loop2.equals(loop1));
    }

    @BeforeEach
    void setUp() {

        allAvailableEntities = new ArrayList<>();

        StpRepresentationItem[] entitiesArr = {

                new StpCartesianPoint(11, "", 1.0, 2.3, 3.3),
                new StpCartesianPoint(12, "", 1.0, 2.3, 3.3),
                new StpCartesianPoint(13, "", 1.0, 2.3, 3.3),
                new StpCartesianPoint(14, "", 1.0, 2.3, 3.3),
                new StpCartesianPoint(15, "", 1.0, 2.3, 3.3),
                new StpCartesianPoint(16, "", 1.0, 2.3, 3.3),
                new StpCartesianPoint(17, "", 1.0, 2.3, 3.3),
                new StpCartesianPoint(18, "", 1.0, 2.3, 3.3),
                new StpCartesianPoint(19, "", 1.0, 2.3, 3.3),

                new StpDirection(20, "", 1.0, 4.3, 1.2),
                new StpDirection(21, "", 1.0, 4.3, 1.2),
                new StpDirection(22, "", 1.0, 4.3, 1.2),
                new StpDirection(23, "", 1.0, 4.3, 1.2),
                new StpDirection(24, "", 1.0, 4.3, 1.2),
                new StpDirection(25, "", 1.0, 4.3, 1.2),

                new StpAxis2Placement3D(31, "", 11, 21, 22),
                new StpAxis2Placement3D(32, "", 12, 22, 23),
                new StpAxis2Placement3D(33, "", 13, 23, 24),
                new StpAxis2Placement3D(34, "", 14, 24, 25),

                new StpVertexPoint(41, "", 11),
                new StpVertexPoint(42, "", 12),
                new StpVertexPoint(43, "", 13),
                new StpVertexPoint(44, "", 14),
                new StpVertexPoint(45, "", 14),

                new StpVector(51, "", 20, 1.0),
                new StpVector(52, "", 21, 1.0),
                new StpVector(53, "", 22, 1.0),
                new StpVector(54, "", 23, 1.0),
                new StpVector(55, "", 24, 1.0),

                new StpLine(61, "", 11, 51),
                new StpLine(62, "", 12, 52),
                new StpLine(63, "", 13, 53),
                new StpLine(64, "", 14, 54),
                new StpLine(65, "", 15, 55),
                new StpLine(66, "", 16, 51),

                new StpEdgeCurve(71, "35", 41, 44, 61, true),
                new StpEdgeCurve(72, "36", 42, 45, 62, true),
                new StpEdgeCurve(73, "37", 43, 41, 63, true),
                new StpEdgeCurve(74, "38", 44, 42, 64, true),
                new StpEdgeCurve(75, "39", 45, 43, 65, true),
                new StpEdgeCurve(76, "40", 41, 44, 66, true),

                new StpOrientedEdge(81, "41", 71, 71, 71, true),
                new StpOrientedEdge(82, "42", 72, 72, 72, true),
                new StpOrientedEdge(83, "43", 73, 73, 73, false),
                new StpOrientedEdge(84, "44", 74, 74, 74, false)
        };
        allAvailableEntities.addAll(Arrays.asList(entitiesArr));

    }

    @Test
    @DisplayName("should write correct amount of edges from given Lists")
    void testConvertFromIdsAmountEdges1() {

        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(81);
        edgesIds.add(82);
        edgesIds.add(83);
        edgesIds.add(84);

        edgeLoop = new StpEdgeLoop(21, "", edgesIds);

        //act
        edgeLoop.convertFromIds(allAvailableEntities);

        //expectations
        int expectedAmount = 4;
        //assert
        assertEquals(edgeLoop.edgesList.size(), expectedAmount);
    }

    @Test
    @DisplayName("should write correct amount of edges from given Lists")
    void testConvertFromIdsAmountEdges2() {

        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(81);
        edgesIds.add(82);

        edgeLoop = new StpEdgeLoop(21, "", edgesIds);
        //act
        edgeLoop.convertFromIds(allAvailableEntities);
        //expectations
        int expectedAmount = 2;
        //assert
        assertEquals(edgeLoop.edgesList.size(), expectedAmount);
    }

    @Test
    @DisplayName("should return true if the correct instances of StpOrientedEdges have been applied")
    void testConvertFromIdsCorrectEdgesApplied() {

        ArrayList<Integer> edgesIdsArr = new ArrayList<>();
        edgesIdsArr.add(81);
        edgesIdsArr.add(82);
        edgesIdsArr.add(83);
        edgesIdsArr.add(84);

        edgeLoop = new StpEdgeLoop(21, "", edgesIdsArr);

        //act
        edgeLoop.convertFromIds(allAvailableEntities);

        assertTrue(edgeLoop.edgesList.contains(allAvailableEntities.get(41))
                && edgeLoop.edgesList.contains(allAvailableEntities.get(42))
                && edgeLoop.edgesList.contains(allAvailableEntities.get(43))
                && edgeLoop.edgesList.contains(allAvailableEntities.get(44))
        );
    }

    @Test
    @DisplayName("should return false if the incorrect instances of StpOrientedEdges have been applied")
    void testConvertFromIdsIncorrectEdgesApplied() {

        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(81);
        edgesIds.add(82);

        edgeLoop = new StpEdgeLoop(21, "", edgesIds);
        //act
        edgeLoop.convertFromIds(allAvailableEntities);

        assertFalse(edgeLoop.edgesList.contains(allAvailableEntities.get(43))
                || edgeLoop.edgesList.contains(allAvailableEntities.get(44))

        );
    }

    @Test
    void testGetEdgesList() {

        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(33);

        StpEdgeLoop loop = new StpEdgeLoop(23, "", edgesIds);

        ArrayList<StpRepresentationItem> availableEntities = new ArrayList();
        availableEntities.add(new StpOrientedEdge(33, "", 30, 30, 30, true));
        availableEntities.add(new StpEdgeCurve(30, "", 28, 28, 26, true));
        availableEntities.add(new StpVertexPoint(28, "", 80));
        availableEntities.add(new StpCircle(26, "", 61, 10));
        availableEntities.add(new StpCartesianPoint(80, "", 0, 0, 0));
        availableEntities.add(new StpAxis2Placement3D(61, "", 81, 70, 71));
        availableEntities.add(new StpCartesianPoint(81, "", 0, 0, 0));
        availableEntities.add(new StpDirection(70, "", 0, 0, -1));
        availableEntities.add(new StpDirection(71, "", -1, 0, 0));

        loop.convertFromIds(availableEntities);

        System.out.println(loop.getEdgesList().get(0));

    }

    @AfterEach
    void tearDown() {
        allAvailableEntities = null;
        edgeLoop = null;
    }
}