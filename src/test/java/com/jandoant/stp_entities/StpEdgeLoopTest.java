package com.jandoant.stp_entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StpEdgeLoopTest {

    ArrayList<StpOrientedEdge> possibleEdges;
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

        possibleEdges = new ArrayList<>();

        StpOrientedEdge[] orientedEdges = {
                new StpOrientedEdge(71, "0", 59, 59, 59, true),
                new StpOrientedEdge(72, "1", 60, 60, 60, true),
                new StpOrientedEdge(73, "2", 61, 61, 61, false),
                new StpOrientedEdge(74, "3", 62, 62, 62, false),
                new StpOrientedEdge(75, "4", 63, 63, 63, true),
                new StpOrientedEdge(76, "5", 64, 64, 64, true),
                new StpOrientedEdge(77, "6", 65, 65, 65, false),
                new StpOrientedEdge(78, "7", 66, 66, 66, false),
                new StpOrientedEdge(79, "8", 67, 67, 67, true),
                new StpOrientedEdge(80, "9", 68, 68, 68, true)
        };
        possibleEdges.addAll(Arrays.asList(orientedEdges));
    }

    @Test
    @DisplayName("should write correct amount of edges from given Lists")
    void testConvertFromIdsAmountEdges1() {

        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(71);
        edgesIds.add(72);
        edgesIds.add(73);
        edgesIds.add(74);

        edgeLoop = new StpEdgeLoop(21, "", edgesIds);
        //act
        edgeLoop.convertFromIds(possibleEdges);
        //expectations
        int expectedAmount = 4;
        //assert
        assertEquals(edgeLoop.edgesList.size(), expectedAmount);
    }

    @Test
    @DisplayName("should write correct amount of edges from given Lists")
    void testConvertFromIdsAmountEdges2() {

        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(71);
        edgesIds.add(72);
        edgesIds.add(73);
        edgesIds.add(74);
        edgesIds.add(75);

        edgeLoop = new StpEdgeLoop(21, "", edgesIds);
        //act
        edgeLoop.convertFromIds(possibleEdges);
        //expectations
        int expectedAmount = 5;
        //assert
        assertEquals(edgeLoop.edgesList.size(), expectedAmount);
    }

    @Test
    @DisplayName("should return true if the correct instances of StpOrientedEdges have been applied")
    void testConvertFromIdsCorrectEdgesApplied() {
        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(71);
        edgesIds.add(72);
        edgesIds.add(73);
        edgesIds.add(74);

        edgeLoop = new StpEdgeLoop(21, "", edgesIds);
        //act
        edgeLoop.convertFromIds(possibleEdges);

        assertTrue(edgeLoop.edgesList.contains(possibleEdges.get(0))
                && edgeLoop.edgesList.contains(possibleEdges.get(1))
                && edgeLoop.edgesList.contains(possibleEdges.get(2))
                && edgeLoop.edgesList.contains(possibleEdges.get(3))
        );
    }

    @Test
    @DisplayName("should return false if the incorrect instances of StpOrientedEdges have been applied")
    void testConvertFromIdsIncorrectEdgesApplied() {

        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(71);
        edgesIds.add(72);
        edgesIds.add(73);
        edgesIds.add(74);

        edgeLoop = new StpEdgeLoop(21, "", edgesIds);
        //act
        edgeLoop.convertFromIds(possibleEdges);

        assertFalse(edgeLoop.edgesList.contains(possibleEdges.get(5))
                || edgeLoop.edgesList.contains(possibleEdges.get(6))
                || edgeLoop.edgesList.contains(possibleEdges.get(7))
                || edgeLoop.edgesList.contains(possibleEdges.get(8))
                || edgeLoop.edgesList.contains(possibleEdges.get(9))
        );
    }

    @AfterEach
    void tearDown() {
        possibleEdges = null;
        edgeLoop = null;
    }
}