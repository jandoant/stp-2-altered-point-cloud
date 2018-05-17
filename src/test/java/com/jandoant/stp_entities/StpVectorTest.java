package com.jandoant.stp_entities;

import com.jandoant.helper.MathHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StpVectorTest {

    ArrayList<StpRepresentationItem> allAvailableEntities;
    StpVector vector;

    @Test
    void testToStringNoDirectionOnlyDirectionId() {

        StpVector v = new StpVector(13, "Vector1", 12, 3.3);

        String expected = "StpVector{id=13, name='Vector1', directionId=12, direction=null, magnitude=3.3, x=0.0, y=0.0, z=0.0}";

        assertEquals(v.toString(), expected);

    }

    @Test
    void testToStringDirection() {

        StpVector v = new StpVector(13, "Vector1", 12, 3.3);

        StpDirection dir = new StpDirection(12, "", 1.0, 0, 0);

        v.setDirection(dir);

        String expected = "StpVector{id=13, name='Vector1', directionId=12, direction=StpDirection{id=12, name='', xDirection=1.0, yDirection=0.0, zDirection=0.0}, magnitude=3.3, x=3.3, y=0.0, z=0.0}";

        assertEquals(v.toString(), expected);

    }

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals() {

        StpVector vector1 = new StpVector(12, "Vector1", 23, 3.0);
        StpVector vector2 = new StpVector(12, "Vector1", 23, 3.0);

        assertTrue(vector1.equals(vector2));
        assertTrue(vector2.equals(vector1));
    }

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEqualsXYZ() {

        StpVector vector1 = new StpVector(12, "Vector1", 3.4, 3.0, 2.1);
        StpVector vector2 = new StpVector(12, "Vector1", 3.4, 3.0, 2.1);

        assertTrue(vector1.equals(vector2));
        assertTrue(vector2.equals(vector1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different x-values")
    void testEqualsNotX() {

        StpVector vector1 = new StpVector(12, "Vector1", 3.4, 3.0, 2.1);
        StpVector vector2 = new StpVector(12, "Vector1", 3.1, 3.0, 2.1);

        assertFalse(vector1.equals(vector2));
        assertFalse(vector2.equals(vector1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different y-values")
    void testEqualsNotY() {

        StpVector vector1 = new StpVector(12, "Vector1", 3.1, 3.0, 2.1);
        StpVector vector2 = new StpVector(12, "Vector1", 3.1, 3.2, 2.1);

        assertFalse(vector1.equals(vector2));
        assertFalse(vector2.equals(vector1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different z-values")
    void testEqualsNotZ() {

        StpVector vector1 = new StpVector(12, "Vector1", 3.1, 3.0, 2.1);
        StpVector vector2 = new StpVector(12, "Vector1", 3.1, 3.0, 2.5);

        assertFalse(vector1.equals(vector2));
        assertFalse(vector2.equals(vector1));
    }

    @Test
    @DisplayName("should return true if equal vectors get assigned the same direction")
    void testEqualsDirection() {

        StpVector vector1 = new StpVector(12, "Vector1", 23, 3.0);
        StpVector vector2 = new StpVector(12, "Vector1", 23, 3.0);

        StpDirection dir = new StpDirection(23, "", 1.2, 3.5, 4.8);

        vector1.setDirection(dir);
        vector2.setDirection(dir);

        assertTrue(vector1.equals(vector2));
        assertTrue(vector2.equals(vector1));
    }

    @Test
    @DisplayName("should return false if equal vectors get assigned different directions")
    void testEqualsNotDirection() {

        StpVector vector1 = new StpVector(12, "Vector1", 23, 3.0);
        StpVector vector2 = new StpVector(12, "Vector1", 23, 3.0);

        StpDirection dir1 = new StpDirection(23, "", 1.2, 3.5, 4.8);
        StpDirection dir2 = new StpDirection(23, "", 1.2, -3.5, 4.8);

        vector1.setDirection(dir1);
        vector2.setDirection(dir2);

        assertFalse(vector1.equals(vector2));
        assertFalse(vector2.equals(vector1));
    }

    @Test
    @DisplayName("should return true if the two compared instances have different id-values")
    void testEqualsNotId() {

        StpVector vector1 = new StpVector(12, "Vector1", 23, 3.0);
        StpVector vector2 = new StpVector(13, "Vector1", 23, 3.0);

        assertFalse(vector1.equals(vector2));
        assertFalse(vector2.equals(vector1));
    }

    @Test
    @DisplayName("should return true if the two compared instances have different name-values")
    void testEqualsNotName() {

        StpVector vector1 = new StpVector(12, "Vector1", 23, 3.0);
        StpVector vector2 = new StpVector(12, "Vector2", 23, 3.0);

        assertFalse(vector1.equals(vector2));
        assertFalse(vector2.equals(vector1));
    }

    @Test
    @DisplayName("should return true if the two compared instances have different directionId-values")
    void testEqualsNotDirectionId() {

        StpVector vector1 = new StpVector(12, "Vector1", 23, 3.0);
        StpVector vector2 = new StpVector(12, "Vector1", 26, 3.0);

        assertFalse(vector1.equals(vector2));
        assertFalse(vector2.equals(vector1));
    }

    @Test
    @DisplayName("should return true if the two compared instances have different magnitude-values")
    void testEqualsNotMagnitude() {

        StpVector vector1 = new StpVector(12, "Vector1", 23, 3.0);
        StpVector vector2 = new StpVector(12, "Vector1", 23, 3.01);

        assertFalse(vector1.equals(vector2));
        assertFalse(vector2.equals(vector1));
    }

    @BeforeEach
    void setUp() {

        //setUp empty Lists
        allAvailableEntities = new ArrayList<>();

        //Put all available Directions in List
        StpRepresentationItem[] directions = {
                new StpDirection(61, "center_axis", 1.0, 0.0, 0.0),
                new StpDirection(62, "ref_axis", 0.0, 0.0, 1.0),
                new StpDirection(63, "", 0.0, 1.0, 0.0),
                new StpDirection(64, "", 0.0, 0.0, 1.0)
        };
        allAvailableEntities.addAll(Arrays.asList(directions));
        //extracted Object from String-description
        vector = new StpVector(58, "Vector1", 62, 3.3);
    }

    @Test
    @DisplayName("should write correct Direction from given List")
    void testConvertFromIdsLocation() {
        //act
        vector.convertFromIds(allAvailableEntities);
        //expectations
        StpDirection expectedDirection = (StpDirection) allAvailableEntities.get(1);
        //assert
        assertTrue(vector.getDirection().equals(expectedDirection));
    }

    @Test
    @DisplayName("should write the correct x,y,z values if the Dir, Mag Constructor is used")
    void testDirMagConstructorXYZ() {

        StpVector v = new StpVector(3, "", 41, 10.0);

        //directions always have a magnitude of 1 !!!!
        StpDirection dir = new StpDirection(41, "", 0, 0, 1.0);

        v.setDirection(dir);

        assertEquals(0, v.getX());
        assertEquals(0, v.getY());
        assertEquals(10, v.getZ());

    }

    @Test
    @DisplayName("should return the correct XYZ-values")
    void testXYZConstructor() {

        //SetUp
        double x = 4;
        double y = 2;
        double z = 3;

        StpVector v = new StpVector(-1, "", x, y, z);

        //Rundungsfehler
        double delta = 0.00000000001;

        assertEquals(4.0, v.getX(), delta);
        assertEquals(2.0, v.getY(), delta);
        assertEquals(3.0, v.getZ(), delta);

    }

    @Test
    @DisplayName("should return the correct Direction value")
    void testXYZConstructorDirection() {

        //SetUp
        double x = 4;
        double y = 2;
        double z = 3;

        StpVector v = new StpVector(0, "", x, y, z);

        double mag = Math.sqrt(x * x + y * y + z * z);
        StpDirection expectedDirection = new StpDirection(-1, "", x / mag, y / mag, z / mag);

        StpDirection actualDirection = v.getDirection();

        assertEquals(expectedDirection, actualDirection);

    }

    @Test
    @DisplayName("should return a Direction with a magnitude of 1")
    void testXYZConstructorDirectionMag() {

        //SetUp
        double x = 4;
        double y = 2;
        double z = 3;

        double expectedDirectionMagnitude = 1.0;

        StpVector v = new StpVector(0, "", x, y, z);

        StpDirection actualDirection = v.getDirection();

        double actualDirectionMagnitude = Math.sqrt(
                actualDirection.xDirection * actualDirection.xDirection +
                        actualDirection.yDirection * actualDirection.yDirection +
                        actualDirection.zDirection * actualDirection.zDirection
        );

        assertEquals(expectedDirectionMagnitude, actualDirectionMagnitude);

    }

    @Test
    @DisplayName("should return a Direction with a magnitude of 1")
    void testXYZConstructorMagnitude() {

        //SetUp
        double x = 4;
        double y = 2;
        double z = 3;

        double expectedMagnitude = Math.sqrt(x * x + y * y + z * z);

        StpVector v = new StpVector(0, "", x, y, z);

        double actualMagnitude = v.getMagnitude();

        assertEquals(expectedMagnitude, actualMagnitude);

    }

    @Test
    @DisplayName("should be able to add two vectors together and return a new one")
    void testAddingTwoVectorsStatic() {

        StpVector v1 = new StpVector(-1, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        StpVector v12 = StpVector.add(v1, v2);
        StpVector v21 = StpVector.add(v2, v1);

        assertEquals(new StpVector(-1, "", 8, 3, 14), v12);
        assertEquals(new StpVector(-1, "", 8, 3, 14), v21);

        assertEquals(v12, v21);

    }

    @Test
    @DisplayName("should be able to add two vectors together by mutating the first one")
    void testAddingTwoVectors() {

        //setup
        StpVector v1 = new StpVector(-2, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        //act
        v1.add(v2);

        //assert
        StpVector expectedVector = new StpVector(-2, "", 8, 3, 14);

        assertEquals(expectedVector, v1);
        assertEquals(new StpVector(-1, "", 5, 1, 9), v2);

    }

    @Test
    @DisplayName("should be able to statically subtract a vector from another vector by creating a new StpVector")
    void testSubtractingVectorsStatic() {

        StpVector v1 = new StpVector(-1, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        //v1-v2
        StpVector v12 = StpVector.subtract(v1, v2);

        //v2-v1
        StpVector v21 = StpVector.subtract(v2, v1);

        assertEquals(new StpVector(-1, "", -2, 1, -4), v12);
        assertEquals(new StpVector(-1, "", 2, -1, 4), v21);

    }

    @Test
    @DisplayName("should be able to subtract a vector from another vector by mutating the original vector")
    void testSubtractingVectorsNonStatic() {

        StpVector v1 = new StpVector(-1, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        //v1-v2
        v1.subtract(v2);

        assertEquals(new StpVector(-1, "", -2, 1, -4), v1);

    }

    @Test
    @DisplayName("should be able to statically scale a vector by a certain Factor by creating a new vector")
    void testScalingOfVectorStatic() {

        double scaleFactor = 3;

        StpVector v = new StpVector(-1, "", 3, 2, 5);

        StpVector scaledVector = StpVector.scale(v, scaleFactor);

        StpVector expectedVector = new StpVector(-1, "", 9, 6, 15);

        assertEquals(expectedVector, scaledVector);

    }

    @Test
    @DisplayName("should be able to statically scale a vector by a certain Factor by creating a new vector")
    void testScalingOfVectorNonStatic() {

        double scaleFactor = 3;

        StpVector v = new StpVector(-1, "", 3, 2, 5);

        v.scale(scaleFactor);

        StpVector expectedVector = new StpVector(-1, "", 9, 6, 15);

        assertEquals(expectedVector, v);

    }

    @Test
    @DisplayName("should be able to statically build the dot product of two vectors")
    void testDotProductOfTwoVectorsStatic() {

        StpVector v1 = new StpVector(-1, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        double dotProduct = StpVector.dotProduct(v1, v2);

        double expectedDotProduct = 62;

        assertEquals(expectedDotProduct, dotProduct);

    }

    @Test
    @DisplayName("should be able to build the dot product of two vectors")
    void testDotProductOfTwoVectorsNonStatic() {

        StpVector v1 = new StpVector(-1, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        double dotProduct = v1.dotProduct(v2);

        double expectedDotProduct = 62;

        assertEquals(expectedDotProduct, dotProduct);

    }

    @Test
    @DisplayName("should be able to build the cross product of two vectors by instantiating a new StpVector")
    void testCrossProductOfTwoVectorsStatic() {

        StpVector v1 = new StpVector(-1, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        StpVector crossProduct12 = StpVector.crossProduct(v1, v2);
        StpVector crossProduct21 = StpVector.crossProduct(v2, v1);

        StpVector expectedCrossProduct12 = new StpVector(-1, "", 13, -2, -7);
        StpVector expectedCrossProduct21 = new StpVector(-1, "", -13, 2, 7);

        assertEquals(expectedCrossProduct12, crossProduct12);
        assertEquals(expectedCrossProduct21, crossProduct21);

    }

    @Test
    @DisplayName("should be able to build the cross product of two vectors by mutating the original StpVector")
    void testCrossProductOfTwoVectorsNonStatic() {

        StpVector v1 = new StpVector(-1, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        v1.crossProduct(v2);

        StpVector expectedCrossProduct1 = new StpVector(-1, "", 13, -2, -7);

        assertEquals(expectedCrossProduct1, v1);

    }

    @Test
    @DisplayName("should be able to statically build the normalized version of a vector by creating a new StpVector")
    void testNormalizationStatic() {

        StpVector v = new StpVector(-1, "", 3, 2, 5);

        StpVector normalizedVector = StpVector.normalize(v);

        double mag = Math.sqrt(9 + 4 + 25);
        StpVector expectedVector = new StpVector(-1, "", 3 / mag, 2 / mag, 5 / mag);

        assertEquals(expectedVector, normalizedVector);

    }

    @Test
    @DisplayName("should be able to build the normalized version of a vector by mutating the original StpVector")
    void testNormalizationNonStatic() {

        //setup
        StpVector v = new StpVector(-1, "", 3, 2, 5);

        //act
        v.normalize();

        //assert
        double mag = Math.sqrt(9 + 4 + 25);
        StpVector expectedVector = new StpVector(-1, "", 3 / mag, 2 / mag, 5 / mag);

        assertEquals(expectedVector, v);

    }

    @Test
    @DisplayName("should return false  if two vectors are not orthogonal to each other")
    void testIsOrthogonalFalse() {

        StpVector v1 = new StpVector(-1, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        assertFalse(v1.isOrthogonalTo(v2));

    }

    @Test
    @DisplayName("should return false  if two vectors are not orthogonal to each other")
    void testAreOrthogonalFalse() {

        StpVector v1 = new StpVector(-1, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        assertFalse(StpVector.areOrthogonal(v1, v2));

    }

    @Test
    @DisplayName("should return false  if two vectors are orthogonal to each other")
    void testIsOrthogonalTrue() {

        StpVector v1 = new StpVector(-1, "", 0, 0, 5);
        StpVector v2 = new StpVector(-1, "", 5, 0, 0);

        assertTrue(v1.isOrthogonalTo(v2));

    }

    @Test
    @DisplayName("should return false  if two vectors are orthogonal to each other")
    void testAreOrthogonalTrue() {

        StpVector v1 = new StpVector(-1, "", 0, 0, 5);
        StpVector v2 = new StpVector(-1, "", 5, 0, 0);

        assertTrue(StpVector.areOrthogonal(v1, v2));

    }

    @Test
    @DisplayName("should be able to rotate x-unit vector around pivot (0,0,0) and z-axis")
    void testRotateXUnitVectorAroundZAxis90PivotOrigin() {

        StpVector v = new StpVector(-1, "", 1, 0, 0);

        StpVector origin = new StpVector(-1, "", 0, 0, 0);
        StpVector axis = new StpVector(-1, "", 0, 0, 1);

        //act
        v.rotate(90, origin, axis);

        //assert
        StpVector expectedVector = new StpVector(-1, "", 0, 1, 0);

        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate x-unit vector around pivot (0,0,0) and y-axis")
    void testRotateXUnitVectorAroundYAxis90PivotOrigin() {

        StpVector v = new StpVector(-1, "", 1, 0, 0);

        StpVector origin = new StpVector(-1, "", 0, 0, 0);
        StpVector axis = new StpVector(-1, "", 0, 1, 0);

        //act
        v.rotate(90, origin, axis);

        //assert
        StpVector expectedVector = new StpVector(-1, "", 0, 0, -1);

        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate x-unit vector around pivot (0,0,0) and y-axis")
    void testRotateXUnitVectorAroundYAxis180PivotOrigin() {

        StpVector v = new StpVector(-1, "", 1, 0, 0);

        StpVector origin = new StpVector(-1, "", 0, 0, 0);
        StpVector axis = new StpVector(-1, "", 0, 1, 0);

        //act
        v.rotate(180, origin, axis);

        //assert
        StpVector expectedVector = new StpVector(-1, "", -1, 0, 0);

        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate x-unit vector around pivot (0,0,0) and z-axis")
    void testRotateXUnitVectorAroundZAxis180PivotOrigin() {

        StpVector v = new StpVector(-1, "", 1, 0, 0);

        StpVector origin = new StpVector(-1, "", 0, 0, 0);
        StpVector axis = new StpVector(-1, "", 0, 0, 1);

        //act
        v.rotate(180, origin, axis);

        //assert
        StpVector expectedVector = new StpVector(-1, "", -1, 0, 0);

        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate x-unit vector around pivot (0,0,0) and z-axis")
    void testRotateXUnitVectorAroundZAxis45PivotOrigin() {

        StpVector v = new StpVector(-1, "", 1, 0, 0);

        StpVector origin = new StpVector(-1, "", 0, 0, 0);
        StpVector axis = new StpVector(-1, "", 0, 0, 1);

        //act
        v.rotate(45, origin, axis);

        //assert
        double expectedX = Math.sqrt(2) / 2;
        double expectedY = expectedX;
        StpVector expectedVector = new StpVector(-1, "", MathHelper.round(expectedX), MathHelper.round(expectedY), 0);

        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate x-unit vector around pivot (0,0,0) and y-axis")
    void testRotateXUnitVectorAroundYAxis45PivotOrigin() {

        StpVector v = new StpVector(-1, "", 1, 0, 0);

        StpVector origin = new StpVector(-1, "", 0, 0, 0);
        StpVector axis = new StpVector(-1, "", 0, 1, 0);

        //act
        v.rotate(45, origin, axis);

        //assert
        double expectedX = Math.sqrt(2) / 2;
        double expectedZ = -expectedX;
        StpVector expectedVector = new StpVector(-1, "", MathHelper.round(expectedX), 0, MathHelper.round(expectedZ));

        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate x-unit vector around pivot (0,0,3) and z-axis")
    void testRotateXUnitVectorAroundZAxis90() {

        StpVector v = new StpVector(-1, "", 1, 0, 3);

        StpVector pivot = new StpVector(-1, "", 0, 0, 3);
        StpVector axis = new StpVector(-1, "", 0, 0, 1);

        //act
        v.rotate(90, pivot, axis);

        //assert
        StpVector expectedVector = new StpVector(-1, "", 0, 1, 3);

        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate x-unit vector around pivot (1,1,3) and z-axis")
    void testRotateXUnitVectorAroundZAxis45PivotNotOnAxis() {

        StpVector v = new StpVector(-1, "", 2, 1, 3);

        StpVector pivot = new StpVector(-1, "", 1, 1, 3);
        StpVector axis = new StpVector(-1, "", 0, 0, 1);

        //act
        v.rotate(45, pivot, axis);

        //assert

        //assert
        double expectedX = 1 + Math.sqrt(2) / 2;
        double expectedY = expectedX;
        StpVector expectedVector = new StpVector(-1, "", MathHelper.round(expectedX), MathHelper.round(expectedY), 3);

        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate x-unit vector around pivot (-1,4,-2) and y-axis")
    void testRotateXUnitVectorAroundYAxis90PivotNotOnAxis() {

        StpVector v = new StpVector(-1, "", 0, 4, -2);

        StpVector pivot = new StpVector(-1, "", -1, 4, -2);
        StpVector axis = new StpVector(-1, "", 0, 1, 0);

        //act
        v.rotate(90, pivot, axis);

        //assert
        StpVector expectedVector = new StpVector(-1, "", -1, 4, -3);

        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate x-unit vector around pivot (-1,4,-2) and y-axis")
    void testRotateXUnitVectorAroundYAxis180PivotNotOnAxis() {

        StpVector v = new StpVector(-1, "", 0, 4, -2);

        StpVector pivot = new StpVector(-1, "", -1, 4, -2);
        StpVector axis = new StpVector(-1, "", 0, 1, 0);

        //act
        v.rotate(180, pivot, axis);

        //assert
        StpVector expectedVector = new StpVector(-1, "", -2, 4, -2);

        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate x-unit vector around pivot (1,1,3) and z-axis")
    void testRotateXUnitVectorAroundZAxis90PivotNotOnAxis() {

        StpVector v = new StpVector(-1, "", 2, 1, 3);

        StpVector pivot = new StpVector(-1, "", 1, 1, 3);
        StpVector axis = new StpVector(-1, "", 0, 0, 1);

        //act
        v.rotate(90, pivot, axis);

        //assert
        StpVector expectedVector = new StpVector(-1, "", 1, 2, 3);

        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate vector around pivot (1,1,3) and z-axis")
    void testRotateXUnitVectorAroundZAxis180PivotNotOnAxis() {

        StpVector v = new StpVector(-1, "", 2, 1, 3);

        StpVector pivot = new StpVector(-1, "", 1, 1, 3);
        StpVector axis = new StpVector(-1, "", 0, 0, 1);

        //act
        v.rotate(180, pivot, axis);

        //assert
        StpVector expectedVector = new StpVector(-1, "", 0, 1, 3);

        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate vector around pivot (0,0,0) and y-axis ")
    void testRotateXUnitVectorAroundYAxisPivotOrigin() {

        StpVector v = new StpVector(-1, "", 1, 0, 0);

        StpVector pivot = new StpVector(-1, "", 0, 0, 0);
        StpVector axis = new StpVector(-1, "", 0, 1, 0);

        v.rotate(90, pivot, axis);

        StpVector expectedVector = new StpVector(-1, "", 0, 0, -1);

        assertEquals(expectedVector, v);

    }

    @Test
    @DisplayName("should be able to rotate vector around pivot (0,0,0) and x-axis ")
    void testRotateVectorAroundXAxis90PivotOrigin() {

        StpVector v = new StpVector(-1, "", 0, 0, 1);

        StpVector pivot = new StpVector(-1, "", 0, 0, 0);
        StpVector axis = new StpVector(-1, "", 1, 0, 0);

        v.rotate(90, pivot, axis);

        StpVector expectedVector = new StpVector(-1, "", 0, -1, 0);
        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate vector around pivot (0,0,0) and x-axis ")
    void testRotateVectorAroundXAxis180PivotOrigin() {

        StpVector v = new StpVector(-1, "", 0, 0, 1);

        StpVector pivot = new StpVector(-1, "", 0, 0, 0);
        StpVector axis = new StpVector(-1, "", 1, 0, 0);

        v.rotate(180, pivot, axis);

        StpVector expectedVector = new StpVector(-1, "", 0, 0, -1);
        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate x unit vector around pivot (0,0,0) and x-axis ")
    void testRotateXUnitVectorAroundXAxisPivotOrigin() {

        StpVector v = new StpVector(-1, "", 1, 0, 0);

        StpVector pivot = new StpVector(-1, "", 0, 0, 0);
        StpVector axis = new StpVector(-1, "", 1, 0, 0);

        v.rotate(90, pivot, axis);

        StpVector expectedVector = new StpVector(-1, "", 1, 0, 0);
        assertEquals(expectedVector, v);
    }

    @Test
    @DisplayName("should be able to rotate vector around pivot (-3,-3,-3) and x-axis ")
    void testRotateVectorAroundXAxis90PivotNotOnAxis() {

        StpVector v = new StpVector(-1, "", -3, -2, -3);

        StpVector pivot = new StpVector(-1, "", -3, -3, -3);
        StpVector axis = new StpVector(-1, "", 1, 0, 0);

        v.rotate(90, pivot, axis);

        StpVector expectedVector = new StpVector(-1, "", -3, -3, -2);

        assertEquals(expectedVector, v);

    }

    @Test
    @DisplayName("should be able to rotate vector around pivot (-3,-3,-3) and x-axis ")
    void testRotateVectorAroundXAxis180PivotNotOnAxis() {

        StpVector v = new StpVector(-1, "", -3, -2, -3);

        StpVector pivot = new StpVector(-1, "", -3, -3, -3);
        StpVector axis = new StpVector(-1, "", 1, 0, 0);

        v.rotate(180, pivot, axis);

        StpVector expectedVector = new StpVector(-1, "", -3, -4, -3);

        assertEquals(expectedVector, v);

    }

    @Test
    @DisplayName("should be able to rotate vector around pivot (1,1,1) and any axis")
    void testRotateVectorAroundPivotOriginanyAxis90() {

        StpVector v = new StpVector(-1, "", 1.707, 0.293, 1.000);

        StpVector pivot = new StpVector(-1, "", 1, 1, 1);
        StpVector axis = new StpVector(-1, "", 1, 1, 1);

        v.rotate(90, pivot, axis);


        double expectedX = 1.4081866403;
        double expectedY = 1.4081866403;
        double expectedZ = 0.1836267194;

        double allowedErr = Math.pow(10, -10);

        assertEquals(expectedX, v.getX(), allowedErr);
        assertEquals(expectedY, v.getY(), allowedErr);
        assertEquals(expectedZ, v.getZ(), allowedErr);
    }

    @Test
    @DisplayName("should be able to rotate vector around pivot (1,1,1) and any axis")
    void testRotateVectorAroundPivotOriginanyAxis135() {

        StpVector v = new StpVector(-1, "", 1.707, 0.293, 1.000);

        StpVector pivot = new StpVector(-1, "", 1, 1, 1);
        StpVector axis = new StpVector(-1, "", 1, 1, 1);

        v.rotate(135, pivot, axis);


        double expectedX = 0.7887070471;
        double expectedY = 1.7885560357;
        double expectedZ = 0.4227369173;

        double allowedErr = Math.pow(10, -10);

        assertEquals(expectedX, v.getX(), allowedErr);
        assertEquals(expectedY, v.getY(), allowedErr);
        assertEquals(expectedZ, v.getZ(), allowedErr);
    }


    @Test
    @DisplayName("should be able to rotate vector around pivot (1,1,1) and any axis")
    void testRotateVectorAroundPivotNotOriginanyAxis90() {

        StpVector v = new StpVector(-1, "", -2.219, 7.625, 5.000);

        StpVector pivot = new StpVector(-1, "", -3, 7, 5);
        StpVector axis = new StpVector(-1, "", -4, 5, 1);

        v.rotate(90, pivot, axis);


        double expectedX = -3.0965348318;
        double expectedY = 7.1206299639;
        double expectedZ = 4.011710853;

        double allowedErr = Math.pow(10, -10);

        assertEquals(expectedX, v.getX(), allowedErr);
        assertEquals(expectedY, v.getY(), allowedErr);
        assertEquals(expectedZ, v.getZ(), allowedErr);
    }





}