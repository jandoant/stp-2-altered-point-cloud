package com.jandoant.stp_entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StpAdvancedFaceTest {

    ArrayList<StpRepresentationItem> allAvailableEntities;
    StpAdvancedFace advancedFace;
    ArrayList<Integer> boundsIds;

    @BeforeEach
    void setUp() {

        allAvailableEntities = new ArrayList<>();

        StpRepresentationItem[] entitiesArr = {
                new StpFaceOuterBound(51, "", 31, true),
                new StpFaceOuterBound(52, "", 32, true),
                new StpFaceOuterBound(53, "", 33, true),
                new StpFaceOuterBound(54, "", 34, true),
                new StpPlane(42, "", 78),
                new StpPlane(43, "", 79),
                new StpPlane(44, "", 80),
                new StpCylindricalSurface(45, "", 81, 10.0)
        };
        allAvailableEntities.addAll(Arrays.asList(entitiesArr));

        boundsIds = new ArrayList<>();
        boundsIds.add(52);
        boundsIds.add(53);
        advancedFace = new StpAdvancedFace(12, "", boundsIds, 44, true);
    }

    @Test
    void testToString() {

        Integer[] boundsIdsArr = {33, 34, 35, 36};

        ArrayList<Integer> boundsIds = new ArrayList<>();
        boundsIds.addAll(Arrays.asList(boundsIdsArr));

        StpAdvancedFace advancedFace = new StpAdvancedFace(12, "AdvancedFace1", boundsIds, 44, true);

        String expected = "StpAdvancedFace{id=12, name='AdvancedFace1', boundsIds=[33, 34, 35, 36], bounds=[], faceGeometryId=44, faceGeometry=null, sameSense=true}";

        assertEquals(advancedFace.toString(), expected);

    }

    @Test
    @DisplayName("should return true if the compared instances have the same values")
    void testEquals() {

        Integer[] boundsIdsArr1 = {33, 34, 35, 36};
        Integer[] boundsIdsArr2 = {33, 34, 35, 36};

        ArrayList<Integer> boundsIds1 = new ArrayList<>();
        ArrayList<Integer> boundsIds2 = new ArrayList<>();

        boundsIds1.addAll(Arrays.asList(boundsIdsArr1));
        boundsIds2.addAll(Arrays.asList(boundsIdsArr2));

        StpAdvancedFace af1 = new StpAdvancedFace(12, "", boundsIds1, 44, true);
        StpAdvancedFace af2 = new StpAdvancedFace(12, "", boundsIds2, 44, true);

        assertTrue(af1.equals(af2));
        assertTrue(af2.equals(af1));
    }

    @Test
    @DisplayName("should return true if the compared instances have the same values")
    void testEqualsBounds() {

        Integer[] boundsIdsArr1 = {33, 34, 35, 36};
        Integer[] boundsIdsArr2 = {33, 34, 35, 36};

        ArrayList<Integer> boundsIds1 = new ArrayList<>();
        ArrayList<Integer> boundsIds2 = new ArrayList<>();

        boundsIds1.addAll(Arrays.asList(boundsIdsArr1));
        boundsIds2.addAll(Arrays.asList(boundsIdsArr2));

        StpAdvancedFace af1 = new StpAdvancedFace(12, "", boundsIds1, 44, true);
        StpAdvancedFace af2 = new StpAdvancedFace(12, "", boundsIds2, 44, true);

        ArrayList<StpFaceBound> bounds1 = new ArrayList<>();
        ArrayList<StpFaceBound> bounds2 = new ArrayList<>();

        StpFaceBound[] boundsArr1 = {
                new StpFaceOuterBound(22, "", 33, true),
                new StpFaceOuterBound(23, "", 34, true),
                new StpFaceOuterBound(24, "", 35, false),
        };
        StpFaceBound[] boundsArr2 = {
                new StpFaceOuterBound(22, "", 33, true),
                new StpFaceOuterBound(23, "", 34, true),
                new StpFaceOuterBound(24, "", 35, false),
        };

        bounds1.addAll(Arrays.asList(boundsArr1));
        bounds2.addAll(Arrays.asList(boundsArr2));

        af1.setBounds(bounds1);
        af2.setBounds(bounds2);

        assertTrue(af1.equals(af2));
        assertTrue(af2.equals(af1));
    }

    @Test
    @DisplayName("should return true if the compared instances have the same values")
    void testEqualsFaceGeometry() {

        Integer[] boundsIdsArr1 = {33, 34, 35, 36};
        Integer[] boundsIdsArr2 = {33, 34, 35, 36};

        ArrayList<Integer> boundsIds1 = new ArrayList<>();
        ArrayList<Integer> boundsIds2 = new ArrayList<>();

        boundsIds1.addAll(Arrays.asList(boundsIdsArr1));
        boundsIds2.addAll(Arrays.asList(boundsIdsArr2));

        StpAdvancedFace af1 = new StpAdvancedFace(12, "", boundsIds1, 44, true);
        StpAdvancedFace af2 = new StpAdvancedFace(12, "", boundsIds2, 44, true);

        StpSurface surface1 = new StpPlane(51, "", 65);
        StpSurface surface2 = new StpPlane(51, "", 65);

        af1.setFaceGeometry(surface1);
        af2.setFaceGeometry(surface2);

        assertTrue(af1.equals(af2));
        assertTrue(af2.equals(af1));
    }

    @Test
    @DisplayName("should return false if the compared instances have different id-values")
    void testEqualsNotId() {

        Integer[] boundsIdsArr1 = {33, 34, 35, 36};
        Integer[] boundsIdsArr2 = {33, 34, 35, 36};

        ArrayList<Integer> boundsIds1 = new ArrayList<>();
        ArrayList<Integer> boundsIds2 = new ArrayList<>();

        boundsIds1.addAll(Arrays.asList(boundsIdsArr1));
        boundsIds2.addAll(Arrays.asList(boundsIdsArr2));

        StpAdvancedFace af1 = new StpAdvancedFace(12, "", boundsIds1, 44, true);
        StpAdvancedFace af2 = new StpAdvancedFace(13, "", boundsIds2, 44, true);

        assertFalse(af1.equals(af2));
        assertFalse(af2.equals(af1));
    }

    @Test
    @DisplayName("should return false if the compared instances have different name-values")
    void testEqualsNotName() {

        Integer[] boundsIdsArr1 = {33, 34, 35, 36};
        Integer[] boundsIdsArr2 = {33, 34, 35, 36};

        ArrayList<Integer> boundsIds1 = new ArrayList<>();
        ArrayList<Integer> boundsIds2 = new ArrayList<>();

        boundsIds1.addAll(Arrays.asList(boundsIdsArr1));
        boundsIds2.addAll(Arrays.asList(boundsIdsArr2));

        StpAdvancedFace af1 = new StpAdvancedFace(12, "", boundsIds1, 44, true);
        StpAdvancedFace af2 = new StpAdvancedFace(12, "a", boundsIds2, 44, true);

        assertFalse(af1.equals(af2));
        assertFalse(af2.equals(af1));
    }

    @Test
    @DisplayName("should return false if the compared instances have different boundsIds-values")
    void testEqualsNotBoundsIds() {

        Integer[] boundsIdsArr1 = {33, 34, 35, 36};
        Integer[] boundsIdsArr2 = {33, 34, 35, 37};

        ArrayList<Integer> boundsIds1 = new ArrayList<>();
        ArrayList<Integer> boundsIds2 = new ArrayList<>();

        boundsIds1.addAll(Arrays.asList(boundsIdsArr1));
        boundsIds2.addAll(Arrays.asList(boundsIdsArr2));

        StpAdvancedFace af1 = new StpAdvancedFace(12, "", boundsIds1, 44, true);
        StpAdvancedFace af2 = new StpAdvancedFace(12, "", boundsIds2, 44, true);

        assertFalse(af1.equals(af2));
        assertFalse(af2.equals(af1));
    }

    @Test
    @DisplayName("should return false if the compared instances have different faceGeometryId-values")
    void testEqualsNotFaceGeometryId() {

        Integer[] boundsIdsArr1 = {33, 34, 35, 36};
        Integer[] boundsIdsArr2 = {33, 34, 35, 36};

        ArrayList<Integer> boundsIds1 = new ArrayList<>();
        ArrayList<Integer> boundsIds2 = new ArrayList<>();

        boundsIds1.addAll(Arrays.asList(boundsIdsArr1));
        boundsIds2.addAll(Arrays.asList(boundsIdsArr2));

        StpAdvancedFace af1 = new StpAdvancedFace(12, "", boundsIds1, 44, true);
        StpAdvancedFace af2 = new StpAdvancedFace(12, "", boundsIds2, 45, true);

        assertFalse(af1.equals(af2));
        assertFalse(af2.equals(af1));
    }

    @Test
    @DisplayName("should return false if the compared instances have different sameSense-values")
    void testEqualsNotSameSense() {

        Integer[] boundsIdsArr1 = {33, 34, 35, 36};
        Integer[] boundsIdsArr2 = {33, 34, 35, 36};

        ArrayList<Integer> boundsIds1 = new ArrayList<>();
        ArrayList<Integer> boundsIds2 = new ArrayList<>();

        boundsIds1.addAll(Arrays.asList(boundsIdsArr1));
        boundsIds2.addAll(Arrays.asList(boundsIdsArr2));

        StpAdvancedFace af1 = new StpAdvancedFace(12, "", boundsIds1, 44, true);
        StpAdvancedFace af2 = new StpAdvancedFace(12, "", boundsIds2, 44, false);

        assertFalse(af1.equals(af2));
        assertFalse(af2.equals(af1));
    }

    @Test
    @DisplayName("should return false if the compared instances have different bounds-values")
    void testEqualsNotBounds() {

        Integer[] boundsIdsArr1 = {33, 34, 35, 36};
        Integer[] boundsIdsArr2 = {33, 34, 35, 36};

        ArrayList<Integer> boundsIds1 = new ArrayList<>();
        ArrayList<Integer> boundsIds2 = new ArrayList<>();

        boundsIds1.addAll(Arrays.asList(boundsIdsArr1));
        boundsIds2.addAll(Arrays.asList(boundsIdsArr2));

        StpAdvancedFace af1 = new StpAdvancedFace(12, "", boundsIds1, 44, true);
        StpAdvancedFace af2 = new StpAdvancedFace(12, "", boundsIds2, 44, true);

        ArrayList<StpFaceBound> bounds1 = new ArrayList<>();
        ArrayList<StpFaceBound> bounds2 = new ArrayList<>();

        StpFaceBound[] boundsArr1 = {
                new StpFaceOuterBound(22, "", 33, true),
                new StpFaceOuterBound(23, "", 34, true),
                new StpFaceOuterBound(24, "", 35, false),
        };
        StpFaceBound[] boundsArr2 = {
                new StpFaceOuterBound(22, "", 33, true),
                new StpFaceOuterBound(23, "", 34, true),
                new StpFaceOuterBound(25, "", 36, false),
        };

        bounds1.addAll(Arrays.asList(boundsArr1));
        bounds2.addAll(Arrays.asList(boundsArr2));

        af1.setBounds(bounds1);
        af2.setBounds(bounds2);

        assertFalse(af1.equals(af2));
        assertFalse(af2.equals(af1));
    }

    @Test
    @DisplayName("should return true if the compared instances different faceGeometry-values")
    void testEqualsNotFaceGeometry() {

        Integer[] boundsIdsArr1 = {33, 34, 35, 36};
        Integer[] boundsIdsArr2 = {33, 34, 35, 36};

        ArrayList<Integer> boundsIds1 = new ArrayList<>();
        ArrayList<Integer> boundsIds2 = new ArrayList<>();

        boundsIds1.addAll(Arrays.asList(boundsIdsArr1));
        boundsIds2.addAll(Arrays.asList(boundsIdsArr2));

        StpAdvancedFace af1 = new StpAdvancedFace(12, "", boundsIds1, 44, true);
        StpAdvancedFace af2 = new StpAdvancedFace(12, "", boundsIds2, 44, true);

        StpSurface surface1 = new StpPlane(51, "", 65);
        StpSurface surface2 = new StpPlane(52, "", 66);

        af1.setFaceGeometry(surface1);
        af2.setFaceGeometry(surface2);

        assertFalse(af1.equals(af2));
        assertFalse(af2.equals(af1));
    }

    @Test
    @DisplayName("should apply the correct StpFaceGeometry instance from given List")
    void testCorrectFaceGeometryApplied() {

        advancedFace.convertFromIds(allAvailableEntities);

        StpSurface expected = (StpSurface) allAvailableEntities.get(6);

        StpSurface actual = advancedFace.getFaceGeometry();

        assertTrue(actual.equals(expected));
    }

    @Test
    @DisplayName("should apply the correct number of StpFaceBounds instances from given List")
    void testCorrectNumberOfBoundsApplied() {

        advancedFace.convertFromIds(allAvailableEntities);

        int expectedNumber = 2;

        assertEquals(advancedFace.getBounds().size(), expectedNumber);
    }

    @Test
    @DisplayName("should apply the correct StpFaceBounds instances from given List")
    void testCorrectBoundsApplied() {

        advancedFace.convertFromIds(allAvailableEntities);

        boolean hasCorrectInstances =
                !advancedFace.getBounds().contains(allAvailableEntities.get(0))
                        && advancedFace.getBounds().contains(allAvailableEntities.get(1))
                        && advancedFace.getBounds().contains(allAvailableEntities.get(2))
                        && !advancedFace.getBounds().contains(allAvailableEntities.get(3));

        assertTrue(hasCorrectInstances);
    }

    @Test
    @DisplayName("should be able to figure out the correct type of Face_Geometry")
    void testGetFaceGeometryTypePlane() {

        advancedFace.convertFromIds(allAvailableEntities);

        assertEquals("StpPlane", advancedFace.getType());
    }

    @Test
    @DisplayName("should be able to figure out the correct type of Face_Geometry")
    void testGetFaceGeometryTypeCylinder() {

        advancedFace = new StpAdvancedFace(12, "", boundsIds, 45, true);

        advancedFace.convertFromIds(allAvailableEntities);

        assertEquals("StpCylindricalSurface", advancedFace.getType());
    }

    private StpAdvancedFace createRectangularAdvancedFace() {

        ArrayList<Integer> boundsIds = new ArrayList<>();
        boundsIds.add(15);

        StpAdvancedFace advancedFace = new StpAdvancedFace(101, "", boundsIds, 95, true);

        //the bounds side
        StpFaceOuterBound outerBound = new StpFaceOuterBound(15, "", 21, true);

        ArrayList<Integer> edgesIds = new ArrayList<>();
        edgesIds.add(71);
        edgesIds.add(72);
        edgesIds.add(73);
        edgesIds.add(74);

        StpCartesianPoint cp161 = new StpCartesianPoint(161, "", 0, 15, 0);
        StpCartesianPoint cp162 = new StpCartesianPoint(162, "", 0, 0, 0);
        StpCartesianPoint cp163 = new StpCartesianPoint(163, "", 0, 15, 0);
        StpCartesianPoint cp164 = new StpCartesianPoint(164, "", 0, 0, 30);
        StpCartesianPoint cp165 = new StpCartesianPoint(165, "", 0, 0, 0);
        StpCartesianPoint cp166 = new StpCartesianPoint(166, "", 0, 15, 30);
        StpCartesianPoint cp167 = new StpCartesianPoint(167, "", 0, 15, 30);
        StpCartesianPoint cp168 = new StpCartesianPoint(168, "", 0, 15, 0);

        StpVertexPoint vp51 = new StpVertexPoint(51, "", 161);
        StpVertexPoint vp52 = new StpVertexPoint(52, "", 162);
        StpVertexPoint vp53 = new StpVertexPoint(53, "", 164);
        StpVertexPoint vp54 = new StpVertexPoint(54, "", 166);

        vp51.setVertexGeometry(cp161);
        vp52.setVertexGeometry(cp162);
        vp53.setVertexGeometry(cp164);
        vp54.setVertexGeometry(cp166);

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

        StpEdgeCurve curve59 = new StpEdgeCurve(59, "", 51, 52, 27, true);
        StpEdgeCurve curve60 = new StpEdgeCurve(60, "", 52, 53, 28, true);
        StpEdgeCurve curve61 = new StpEdgeCurve(61, "", 54, 53, 29, true);
        StpEdgeCurve curve62 = new StpEdgeCurve(62, "", 51, 54, 30, true);

        StpLine line27 = new StpLine(27, "", 163, 39);
        StpLine line28 = new StpLine(28, "", 165, 40);
        StpLine line29 = new StpLine(29, "", 167, 41);
        StpLine line30 = new StpLine(30, "", 168, 42);

        line27.setStartingPoint(cp163);
        line28.setStartingPoint(cp165);
        line29.setStartingPoint(cp167);
        line30.setStartingPoint(cp168);

        line27.setDirectionVector(v39);
        line28.setDirectionVector(v40);
        line29.setDirectionVector(v41);
        line30.setDirectionVector(v42);

        curve59.setEdgeGeometry(line27);
        curve60.setEdgeGeometry(line28);
        curve61.setEdgeGeometry(line29);
        curve62.setEdgeGeometry(line30);

        curve59.setEdgeStartVertex(vp51);
        curve60.setEdgeStartVertex(vp52);
        curve61.setEdgeStartVertex(vp54);
        curve62.setEdgeStartVertex(vp51);

        curve59.setEdgeEndVertex(vp52);
        curve60.setEdgeEndVertex(vp53);
        curve61.setEdgeEndVertex(vp53);
        curve62.setEdgeEndVertex(vp54);

        StpOrientedEdge oe71 = new StpOrientedEdge(71, "", 51, 52, 59, true);
        StpOrientedEdge oe72 = new StpOrientedEdge(72, "", 52, 53, 60, true);
        StpOrientedEdge oe73 = new StpOrientedEdge(73, "", 54, 53, 61, false);
        StpOrientedEdge oe74 = new StpOrientedEdge(74, "", 51, 54, 62, false);

        oe71.setEdgeStartVertex(vp51);
        oe72.setEdgeStartVertex(vp52);
        oe73.setEdgeStartVertex(vp54);
        oe74.setEdgeStartVertex(vp51);

        oe71.setEdgeEndVertex(vp52);
        oe72.setEdgeEndVertex(vp53);
        oe73.setEdgeEndVertex(vp53);
        oe74.setEdgeEndVertex(vp54);

        oe71.setEdgeElement(curve59);
        oe72.setEdgeElement(curve60);
        oe73.setEdgeElement(curve61);
        oe74.setEdgeElement(curve62);

        StpEdgeLoop edgeLoop = new StpEdgeLoop(21, "", edgesIds);

        ArrayList<StpOrientedEdge> oeList = new ArrayList<>();
        oeList.add(oe71);
        oeList.add(oe72);
        oeList.add(oe73);
        oeList.add(oe74);

        edgeLoop.setEdgesList(oeList);

        outerBound.setBound(edgeLoop);

        ArrayList<StpFaceBound> bounds = new ArrayList<>();
        bounds.add(outerBound);

        advancedFace.setBounds(bounds);

        //the FaceGeometry side
        StpPlane plane = new StpPlane(95, "", 127);

        StpCartesianPoint location = new StpCartesianPoint(160, "Origin", 0, 15, 0);
        StpDirection axis = new StpDirection(135, "center_axis", -1, 0, 0);
        StpDirection refDirection = new StpDirection(136, "ref_axis", 0, -1, 0);

        StpAxis2Placement3D position = new StpAxis2Placement3D(127, "", 160, 135, 136);

        position.setLocation(location);
        position.setAxis(axis);
        position.setRefDirection(refDirection);

        plane.setPosition(position);

        advancedFace.setFaceGeometry(plane);

        return advancedFace;
    }

    @Test
    void testGetOuterEdgePointsUVW() {

        //setup
        StpAdvancedFace advancedFace = createRectangularAdvancedFace();

        //act
        StpCartesianPoint[] outerEdgePointsUVW = advancedFace.getOuterEdgePointsUVW();

        StpCartesianPoint[] expectedOuterEdgePoints = {
                new StpCartesianPoint(-1, "", 0, 0, 0),
                new StpCartesianPoint(-1, "", 15, 0, 0),
                new StpCartesianPoint(-1, "", 15, 30, 0),
                new StpCartesianPoint(-1, "", 0, 30, 0),

        };

        assertArrayEquals(expectedOuterEdgePoints, outerEdgePointsUVW);

    }

    @Test
    void testMeshPolygonUVW() {

        //setup
        ArrayList<Integer> edgesIds = new ArrayList<>();
        StpAdvancedFace advancedFace = new StpAdvancedFace(12, "", edgesIds, 71, true);

        StpCartesianPoint[] outerEdgePointsUVW = {
                new StpCartesianPoint(-1, "", 0, 0, 0),
                new StpCartesianPoint(-1, "", 15, 0, 0),
                new StpCartesianPoint(-1, "", 15, 30, 0),
                new StpCartesianPoint(-1, "", 0, 30, 0),

        };

        //act
        ArrayList<StpCartesianPoint> actualMesh = advancedFace.mesh2DPolygonUVW(outerEdgePointsUVW, 5.0);

        //assert
        ArrayList<StpCartesianPoint> expectedMesh = new ArrayList<>();

        expectedMesh.add(new StpCartesianPoint(-1, "", 0, 0, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 5, 0, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 10, 0, 0));

        expectedMesh.add(new StpCartesianPoint(-1, "", 15, 0, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 15, 5, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 15, 10, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 15, 15, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 15, 20, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 15, 25, 0));

        expectedMesh.add(new StpCartesianPoint(-1, "", 15, 30, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 10, 30, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 5, 30, 0));

        expectedMesh.add(new StpCartesianPoint(-1, "", 0, 30, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0, 25, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0, 20, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0, 15, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0, 10, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 0, 5, 0));

        expectedMesh.add(new StpCartesianPoint(-1, "", 5, 5, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 10, 5, 0));

        expectedMesh.add(new StpCartesianPoint(-1, "", 5, 10, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 10, 10, 0));

        expectedMesh.add(new StpCartesianPoint(-1, "", 5, 15, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 10, 15, 0));

        expectedMesh.add(new StpCartesianPoint(-1, "", 5, 20, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 10, 20, 0));

        expectedMesh.add(new StpCartesianPoint(-1, "", 5, 25, 0));
        expectedMesh.add(new StpCartesianPoint(-1, "", 10, 25, 0));

        assertEquals(expectedMesh, actualMesh);

    }

    @AfterEach
    void tearDown() {
        allAvailableEntities = null;
        advancedFace = null;
        boundsIds = null;
    }

}