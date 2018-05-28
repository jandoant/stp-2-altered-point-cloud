package com.jandoant.stp_entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StpFaceBoundTest {

    @Test
    void testToString() {

        StpFaceBound fb = new StpFaceBound(11, "FaceBound1", 23, true);

        String expected = "StpFaceBound{id=11, name='FaceBound1', boundId=23, bound=null, orientation=true}";

        assertEquals(expected, fb.toString());

    }

    @Test
    void testEquals() {

        StpFaceBound fb1 = new StpFaceBound(11, "FaceBound1", 23, true);
        StpFaceBound fb2 = new StpFaceBound(11, "FaceBound1", 23, true);

        assertTrue(fb1.equals(fb2));
        assertTrue(fb2.equals(fb1));
    }

    @Test
    void testEqualsNotId() {

        StpFaceBound fb1 = new StpFaceBound(11, "FaceBound1", 23, true);
        StpFaceBound fb2 = new StpFaceBound(12, "FaceBound1", 23, true);

        assertFalse(fb1.equals(fb2));
        assertFalse(fb2.equals(fb1));
    }

    @Test
    void testEqualsNotName() {

        StpFaceBound fb1 = new StpFaceBound(11, "FaceBound1", 23, true);
        StpFaceBound fb2 = new StpFaceBound(11, "FaceBound2", 23, true);

        assertFalse(fb1.equals(fb2));
        assertFalse(fb2.equals(fb1));
    }

    @Test
    void testEqualsNotBoundId() {

        StpFaceBound fb1 = new StpFaceBound(11, "FaceBound1", 23, true);
        StpFaceBound fb2 = new StpFaceBound(11, "FaceBound1", 24, true);

        assertFalse(fb1.equals(fb2));
        assertFalse(fb2.equals(fb1));
    }

    @Test
    void testEqualsNotOrientation() {

        StpFaceBound fb1 = new StpFaceBound(11, "FaceBound1", 23, true);
        StpFaceBound fb2 = new StpFaceBound(11, "FaceBound1", 23, false);

        assertFalse(fb1.equals(fb2));
        assertFalse(fb2.equals(fb1));
    }

    @Test
    void testEqualsNotBound() {

        ArrayList<Integer> edgesIds = new ArrayList();
        edgesIds.add(67);
        edgesIds.add(68);
        edgesIds.add(69);

        StpEdgeLoop bound = new StpEdgeLoop(24, "", edgesIds);

        StpFaceBound fb1 = new StpFaceBound(11, "FaceBound1", 23, true);
        StpFaceBound fb2 = new StpFaceBound(11, "FaceBound1", 24, true);

        fb2.setBound(bound);

        assertFalse(fb1.equals(fb2));
        assertFalse(fb2.equals(fb1));
    }

    @Test
    void testConvertFromIds() {

        ArrayList<StpRepresentationItem> availableEntities = new ArrayList();

        ArrayList<Integer> edgesIds0 = new ArrayList();
        edgesIds0.add(60);
        edgesIds0.add(61);
        edgesIds0.add(62);

        ArrayList<Integer> edgesIds1 = new ArrayList();
        edgesIds1.add(63);
        edgesIds1.add(64);
        edgesIds1.add(65);

        ArrayList<Integer> edgesIds2 = new ArrayList();
        edgesIds2.add(66);
        edgesIds2.add(67);
        edgesIds2.add(68);

        StpLoop[] bounds = {
                new StpEdgeLoop(20, "", edgesIds0),
                new StpEdgeLoop(21, "", edgesIds1),
                new StpEdgeLoop(22, "", edgesIds2)
        };

        availableEntities.addAll(Arrays.asList(bounds));

        StpFaceBound fb = new StpFaceBound(11, "FaceBound1", 22, true);

        fb.convertFromIds(availableEntities);

        assertTrue(availableEntities.get(2).equals(fb.getBound()));

    }

    @Test
    void testIsFaceOuterBoundTrue() {

        StpFaceBound fb = new StpFaceOuterBound(12, "", 34, true);

        assertTrue(fb.isOuterFaceBound());

    }

    @Test
    void testIsFaceOuterBoundFalse() {

        StpFaceBound fb = new StpFaceBound(13, "", 35, true);

        assertFalse(fb.isOuterFaceBound());

    }
}