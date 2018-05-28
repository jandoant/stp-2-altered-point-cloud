package com.jandoant.geometric;

import com.jandoant.stp_entities.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CircleUVWTest {

    private StpAdvancedFace createCircularAdvancedFace() {

        ArrayList<Integer> boundsIds = new ArrayList<>();
        boundsIds.add(20);

        StpAdvancedFace advancedFace = new StpAdvancedFace(38, "", boundsIds, 16, true);

        //the bounds side
        StpFaceOuterBound outerBound = new StpFaceOuterBound(20, "", 24, true);

        StpCartesianPoint cp78 = new StpCartesianPoint(78, "", 10, 0, 30);

        StpVertexPoint vp27 = new StpVertexPoint(27, "", 78);

        vp27.setVertexGeometry(cp78);

        StpVector v39 = new StpVector(39, "", 137, 10);
        StpVector v40 = new StpVector(40, "", 138, 10);
        StpVector v41 = new StpVector(41, "", 139, 10);
        StpVector v42 = new StpVector(42, "", 140, 10);

        StpDirection dir137 = new StpDirection(137, "", 0, -1, 0);
        StpDirection dir138 = new StpDirection(138, "", 0, 0, 1);
        StpDirection dir139 = new StpDirection(139, "", 0, -1, 0);
        StpDirection dir140 = new StpDirection(140, "", 0, 0, 1);

        v39.setDirection(dir137);
        v40.setDirection(dir138);
        v41.setDirection(dir139);
        v42.setDirection(dir140);

        StpEdgeCurve curve29 = new StpEdgeCurve(29, "", 27, 27, 25, true);

        StpAxis2Placement3D position60 = new StpAxis2Placement3D(60, "", 79, 68, 69);

        StpCartesianPoint location79 = new StpCartesianPoint(79, "Origin", 0, 0, 30);
        StpDirection axis68 = new StpDirection(68, "center_axis", 0, 0, 1);
        StpDirection refDirection69 = new StpDirection(69, "ref_axis", -1, 0, 0);

        position60.setLocation(location79);
        position60.setAxis(axis68);
        position60.setRefDirection(refDirection69);

        StpCircle circle25 = new StpCircle(25, "", 60, 10);

        circle25.setPosition(position60);

        curve29.setEdgeGeometry(circle25);
        curve29.setEdgeStartVertex(vp27);
        curve29.setEdgeEndVertex(vp27);

        StpOrientedEdge oe34 = new StpOrientedEdge(34, "", 27, 27, 29, true);

        oe34.setEdgeStartVertex(vp27);
        oe34.setEdgeEndVertex(vp27);
        oe34.setEdgeElement(curve29);

        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(34);

        StpEdgeLoop edgeLoop = new StpEdgeLoop(24, "", edgesIds);

        ArrayList<StpOrientedEdge> oeList = new ArrayList<>();
        oeList.add(oe34);

        edgeLoop.setEdgesList(oeList);

        outerBound.setBound(edgeLoop);

        ArrayList<StpFaceBound> bounds = new ArrayList<>();
        bounds.add(outerBound);

        advancedFace.setBounds(bounds);

        //the FaceGeometry side
        StpPlane plane = new StpPlane(16, "", 63);

        StpCartesianPoint location = new StpCartesianPoint(83, "Origin", 0, 0, 30);
        StpDirection axis = new StpDirection(74, "center_axis", 0, 0, 1);
        StpDirection refDirection = new StpDirection(75, "ref_axis", 1, 0, 0);

        StpAxis2Placement3D position = new StpAxis2Placement3D(63, "", 83, 74, 75);

        position.setLocation(location);
        position.setAxis(axis);
        position.setRefDirection(refDirection);

        plane.setPosition(position);

        advancedFace.setFaceGeometry(plane);

        return advancedFace;
    }

    @Test
    void testMeshEdge() {

        StpAdvancedFace af = createCircularAdvancedFace();

        StpFaceBound bound = af.getBounds().get(0);
        StpPlane plane = (StpPlane) af.getFaceGeometry();

        CircleUVW circle = new CircleUVW(bound, plane, true);

        circle.mesh(4, 1);

        ArrayList<StpCartesianPoint> expectedMesh = new ArrayList<>();
        expectedMesh.add(new StpCartesianPoint(-1, "", 10, 0, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0, 10, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", -10, 0, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0, -10, 0));

        assertEquals(expectedMesh, circle.getMeshUVW());

    }

    @Test
    void testMeshFull() {

        StpAdvancedFace af = createCircularAdvancedFace();

        StpFaceBound bound = af.getBounds().get(0);
        StpPlane plane = (StpPlane) af.getFaceGeometry();

        CircleUVW circle = new CircleUVW(bound, plane, true);

        circle.mesh(4, 2);

        ArrayList<StpCartesianPoint> expectedMesh = new ArrayList<>();
        expectedMesh.add(new StpCartesianPoint(-1, "", 10, 0, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0, 10, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", -10, 0, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0, -10, 0));

        expectedMesh.add(new StpCartesianPoint(-1, "", 5, 0, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0, 5, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", -5, 0, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0, -5, 0));

        assertEquals(expectedMesh, circle.getMeshUVW());

    }

    @Test
    void testContainsTrue() {

        StpAdvancedFace af = createCircularAdvancedFace();

        StpFaceBound bound = af.getBounds().get(0);
        StpPlane plane = (StpPlane) af.getFaceGeometry();

        CircleUVW circle = new CircleUVW(bound, plane, true);

        StpCartesianPoint pt = new StpCartesianPoint(-1, "", 3, -4, 0);

        assertTrue(circle.contains(pt));

    }

    @Test
    void testContainsFalse() {

        StpAdvancedFace af = createCircularAdvancedFace();

        StpFaceBound bound = af.getBounds().get(0);
        StpPlane plane = (StpPlane) af.getFaceGeometry();

        CircleUVW circle = new CircleUVW(bound, plane, true);

        StpCartesianPoint pt = new StpCartesianPoint(-1, "", 5, -9, 0);

        assertFalse(circle.contains(pt));

    }
}