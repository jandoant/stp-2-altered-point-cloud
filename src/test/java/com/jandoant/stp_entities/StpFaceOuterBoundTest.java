package com.jandoant.stp_entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StpFaceOuterBoundTest {

    @Test
    void testToString() {

        StpFaceOuterBound outerBound = new StpFaceOuterBound(12, "FaceOuterBound1", 23, true);

        String expected = "StpFaceOuterBound{id=12, name='FaceOuterBound1', boundId=23, bound=null, orientation=true}";

    }

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals() {

        StpFaceOuterBound outerBound1 = new StpFaceOuterBound(12, "FaceOuterBound1", 23, true);
        StpFaceOuterBound outerBound2 = new StpFaceOuterBound(12, "FaceOuterBound1", 23, true);

        assertTrue(outerBound1.equals(outerBound2));
        assertTrue(outerBound2.equals(outerBound1));

    }

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEqualsBound() {

        StpFaceOuterBound outerBound1 = new StpFaceOuterBound(12, "FaceOuterBound1", 23, true);
        StpFaceOuterBound outerBound2 = new StpFaceOuterBound(12, "FaceOuterBound1", 23, true);

        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(71);
        edgesIds.add(72);
        edgesIds.add(73);
        edgesIds.add(74);

        StpEdgeLoop loop1 = new StpEdgeLoop(14, "", edgesIds);
        StpEdgeLoop loop2 = new StpEdgeLoop(14, "", edgesIds);

        outerBound1.setBound(loop1);
        outerBound2.setBound(loop2);

        assertTrue(outerBound1.equals(outerBound2));
        assertTrue(outerBound2.equals(outerBound1));

    }

    @Test
    @DisplayName("should return false if the two compared instances have different id-values")
    void testEqualsNotId() {

        StpFaceOuterBound outerBound1 = new StpFaceOuterBound(12, "FaceOuterBound1", 23, true);
        StpFaceOuterBound outerBound2 = new StpFaceOuterBound(13, "FaceOuterBound1", 23, true);

        assertFalse(outerBound1.equals(outerBound2));
        assertFalse(outerBound2.equals(outerBound1));

    }

    @Test
    @DisplayName("should return false if the two compared instances have different name-values")
    void testEqualsNotName() {

        StpFaceOuterBound outerBound1 = new StpFaceOuterBound(12, "FaceOuterBound1", 23, true);
        StpFaceOuterBound outerBound2 = new StpFaceOuterBound(12, "FaceOuterBound2", 23, true);

        assertFalse(outerBound1.equals(outerBound2));
        assertFalse(outerBound2.equals(outerBound1));

    }

    @Test
    @DisplayName("should return false if the two compared instances have different boundId-values")
    void testEqualsNotBoundId() {

        StpFaceOuterBound outerBound1 = new StpFaceOuterBound(12, "FaceOuterBound1", 23, true);
        StpFaceOuterBound outerBound2 = new StpFaceOuterBound(12, "FaceOuterBound1", 24, true);

        assertFalse(outerBound1.equals(outerBound2));
        assertFalse(outerBound2.equals(outerBound1));

    }

    @Test
    @DisplayName("should return false if the two compared instances have different orientation-values")
    void testEqualsNotOrientation() {

        StpFaceOuterBound outerBound1 = new StpFaceOuterBound(12, "FaceOuterBound1", 23, true);
        StpFaceOuterBound outerBound2 = new StpFaceOuterBound(12, "FaceOuterBound1", 23, false);

        assertFalse(outerBound1.equals(outerBound2));
        assertFalse(outerBound2.equals(outerBound1));

    }

    @Test
    @DisplayName("should return false if the two compared instances have different bound-values")
    void testEqualsNotBound() {

        StpFaceOuterBound outerBound1 = new StpFaceOuterBound(12, "FaceOuterBound1", 23, true);
        StpFaceOuterBound outerBound2 = new StpFaceOuterBound(12, "FaceOuterBound1", 23, true);

        ArrayList<Integer> edgesIds1 = new ArrayList<>();
        edgesIds1.add(71);
        edgesIds1.add(72);
        edgesIds1.add(73);
        edgesIds1.add(74);
        StpEdgeLoop loop1 = new StpEdgeLoop(14, "", edgesIds1);

        ArrayList<Integer> edgesIds2 = new ArrayList<>();
        edgesIds2.add(71);
        edgesIds2.add(72);
        edgesIds2.add(73);
        edgesIds2.add(75);
        StpEdgeLoop loop2 = new StpEdgeLoop(14, "", edgesIds2);

        outerBound1.setBound(loop1);
        outerBound2.setBound(loop2);

        assertFalse(outerBound1.equals(outerBound2));
        assertFalse(outerBound2.equals(outerBound1));

    }


    ArrayList<StpLoop> possibleBounds;
    StpFaceOuterBound outerBound;

    @BeforeEach
    void setUp() {

        Integer [] edges41 = {51, 52, 53};
        Integer [] edges42 = {54, 55, 56, 57};
        Integer [] edges43 = {58, 59};
        Integer [] edges44 = {60, 61, 62, 63, 64, 65};

        ArrayList<Integer> edgesIds41 = new ArrayList<>();
        ArrayList<Integer> edgesIds42 = new ArrayList<>();
        ArrayList<Integer> edgesIds43 = new ArrayList<>();
        ArrayList<Integer> edgesIds44 = new ArrayList<>();
        edgesIds41.addAll(Arrays.asList(edges41));
        edgesIds41.addAll(Arrays.asList(edges42));
        edgesIds41.addAll(Arrays.asList(edges43));
        edgesIds41.addAll(Arrays.asList(edges44));


        StpLoop [] loops = {
                new StpEdgeLoop(41, "", edgesIds41),
                new StpEdgeLoop(42, "", edgesIds42),
                new StpEdgeLoop(43, "", edgesIds43),
                new StpEdgeLoop(44, "", edgesIds44)
        };

        possibleBounds = new ArrayList<>();
        possibleBounds.addAll(Arrays.asList(loops));


        outerBound = new StpFaceOuterBound(12, "", 43, true);
    }
    @Test
    @DisplayName("it should write the correct Bound From the List")
    void testConvertFromIdsBound() {

        outerBound.convertFromIds(possibleBounds);

        StpLoop expectedBound = possibleBounds.get(2);

        StpLoop actualBound = outerBound.getBound();

        assertTrue(expectedBound.equals(actualBound));

    }

    @AfterEach
    void tearDown() {
        possibleBounds = null;
        outerBound = null;
    }
}