package com.jandoant.stp_entities;

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
    @DisplayName("should be able to build the dot product of two vectors")
    void testDotProductOfTwoVectors() {

        StpVector v1 = new StpVector(-1, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        double dotProduct = v1.dotProduct(v2);

        double expectedDotProduct = 62;

        assertEquals(expectedDotProduct, dotProduct);

    }

    @Test
    @DisplayName("should be able to build the dot product of two vectors")
    void testCrossProductOfTwoVectors() {

        StpVector v1 = new StpVector(-1, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        StpVector crossProduct12 = v1.crossProduct(v2);
        StpVector crossProduct21 = v2.crossProduct(v1);

        StpVector expectedCrossProduct12 = new StpVector(-1, "", 13, -2, -7);
        StpVector expectedCrossProduct21 = new StpVector(-1, "", -13, 2, 7);

        assertEquals(expectedCrossProduct12, crossProduct12);
        assertEquals(expectedCrossProduct21, crossProduct21);

    }

    @Test
    @DisplayName("should be able to build the nprmalized version of the vector")
    void testNormalization() {

        StpVector v = new StpVector(-1, "", 3, 2, 5);

        StpVector normalizedVector = v.normalize();

        double mag = Math.sqrt(9 + 4 + 25);
        StpVector expectedVector = new StpVector(-1, "", 3 / mag, 2 / mag, 5 / mag);

        assertEquals(expectedVector, normalizedVector);

    }

    @Test
    @DisplayName("should return false  if two vectors are not orthogonal to each other")
    void testAreOrthogonalFalse() {

        StpVector v1 = new StpVector(-1, "", 3, 2, 5);
        StpVector v2 = new StpVector(-1, "", 5, 1, 9);

        assertFalse(v1.isOrthogonalTo(v2));

    }

    @Test
    @DisplayName("should return false  if two vectors are orthogonal to each other")
    void testAreOrthogonalTrue() {

        StpVector v1 = new StpVector(-1, "", 0, 0, 5);
        StpVector v2 = new StpVector(-1, "", 5, 0, 0);

        assertTrue(v1.isOrthogonalTo(v2));

    }
}