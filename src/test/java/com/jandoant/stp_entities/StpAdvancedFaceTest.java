package com.jandoant.stp_entities;

import com.jandoant.deformation.DeformTimesAdapter;
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

    @Test
    void testAddDeformFunction() {

        //Setup
        ArrayList<StpCartesianPoint> mockPointCloud = new ArrayList<>();
        mockPointCloud.add(new StpCartesianPoint(-1, "", 0, 0, 0));
        mockPointCloud.add(new StpCartesianPoint(-1, "", 1, 2, 0));
        mockPointCloud.add(new StpCartesianPoint(-1, "", 5, 6, 0));
        mockPointCloud.add(new StpCartesianPoint(-1, "", 1, -3, 0));

        StpAdvancedFace advancedFace = new StpAdvancedFace(-1, "", boundsIds, 33, true);

        advancedFace.setLocalPointCloud(mockPointCloud);

        //Act
        advancedFace.applyDeformationFunction(new DeformTimesAdapter());

        //Assert
        ArrayList<StpCartesianPoint> expectedPointCloud = new ArrayList<>();
        expectedPointCloud.add(new StpCartesianPoint(-1, "", 0, 0, 0));
        expectedPointCloud.add(new StpCartesianPoint(-1, "", 1, 2, 2));
        expectedPointCloud.add(new StpCartesianPoint(-1, "", 5, 6, 30));
        expectedPointCloud.add(new StpCartesianPoint(-1, "", 1, -3, -3));

        assertEquals(expectedPointCloud, advancedFace.getLocalPointCloud());

    }

    @AfterEach
    void tearDown() {
        allAvailableEntities = null;
        advancedFace = null;
        boundsIds = null;
    }

}