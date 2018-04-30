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
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals() {

        StpVector vector1 = new StpVector(12, "Vector1", 23, 3.0);
        StpVector vector2 = new StpVector(12, "Vector1", 23, 3.0);

        assertTrue(vector1.equals(vector2));
        assertTrue(vector2.equals(vector1));
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
    void testToString() {

        StpVector v = new StpVector(13, "Vector1", 12, 3.3);

        String expected = "StpVector{id=13, name='Vector1', directionId=12, direction=null, magnitude=3.3}";

        assertEquals(v.toString(), expected);

    }

}