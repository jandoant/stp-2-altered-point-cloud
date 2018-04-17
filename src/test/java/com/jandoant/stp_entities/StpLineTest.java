package com.jandoant.stp_entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StpLineTest {

    ArrayList<StpCartesianPoint> possibleStartingPoints;
    ArrayList<StpVector> possibleDirectionVectors;
    StpLine line;

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals() {

        StpLine line1 = new StpLine(12, "Line1", 23, 12);
        StpLine line2 = new StpLine(12, "Line1", 23, 12);

        assertTrue(line1.equals(line2));
        assertTrue(line2.equals(line1));
    }

    @Test
    @DisplayName("should return true if equal lines get assigned the same startingPoint")
    void testEqualsStartingPoint() {

        StpLine line1 = new StpLine(12, "Line1", 23, 12);
        StpLine line2 = new StpLine(12, "Line1", 23, 12);

        StpCartesianPoint pnt = new StpCartesianPoint(23, "", 1.2, 3.5, 4.8);

        line1.setStartingPoint(pnt);
        line2.setStartingPoint(pnt);

        assertTrue(line1.equals(line2));
        assertTrue(line2.equals(line1));

    }

    @Test
    @DisplayName("should return false if equal lines get assigned different startingPoints")
    void testEqualsNotStartingPoint() {

        StpLine line1 = new StpLine(12, "Line1", 23, 12);
        StpLine line2 = new StpLine(12, "Line1", 23, 12);

        StpCartesianPoint pnt1 = new StpCartesianPoint(23, "", 1.2, 3.5, 4.8);
        StpCartesianPoint pnt2 = new StpCartesianPoint(23, "", 1.3, 3.5, 4.8);

        line1.setStartingPoint(pnt1);
        line2.setStartingPoint(pnt2);

        assertFalse(line1.equals(line2));
        assertFalse(line2.equals(line1));

    }

    @Test
    @DisplayName("should return true if equal lines get assigned the same directionVector")
    void testEqualsDirectionVector() {

        StpLine line1 = new StpLine(12, "Line1", 23, 12);
        StpLine line2 = new StpLine(12, "Line1", 23, 12);

        StpVector directionVector = new StpVector(23, "", 12, 3.5);

        line1.setDirectionVector(directionVector);
        line2.setDirectionVector(directionVector);

        assertTrue(line1.equals(line2));
        assertTrue(line2.equals(line1));

    }

    @Test
    @DisplayName("should return false if equal lines get assigned different directionVectors")
    void testEqualsNotDirectionVector() {

        StpLine line1 = new StpLine(12, "Line1", 23, 12);
        StpLine line2 = new StpLine(12, "Line1", 23, 12);

        StpVector directionVector1 = new StpVector(23, "", 12, 3.5);
        StpVector directionVector2 = new StpVector(24, "", 12, 3.5);

        line1.setDirectionVector(directionVector1);
        line2.setDirectionVector(directionVector2);

        assertFalse(line1.equals(line2));
        assertFalse(line2.equals(line1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different id-values")
    void testEqualsNotId() {

        StpLine line1 = new StpLine(12, "Line1", 23, 12);
        StpLine line2 = new StpLine(13, "Line1", 23, 12);

        assertFalse(line1.equals(line2));
        assertFalse(line2.equals(line1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different name-values")
    void testEqualsNotName() {

        StpLine line1 = new StpLine(12, "Line1", 23, 12);
        StpLine line2 = new StpLine(12, "Line2", 23, 12);

        assertFalse(line1.equals(line2));
        assertFalse(line2.equals(line1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different startingPointId-values")
    void testEqualsNotStartingPointId() {

        StpLine line1 = new StpLine(12, "Line1", 23, 12);
        StpLine line2 = new StpLine(12, "Line1", 24, 12);

        assertFalse(line1.equals(line2));
        assertFalse(line2.equals(line1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different directionVectorId-values")
    void testEqualsNotDirectionVectorId() {

        StpLine line1 = new StpLine(12, "Line1", 23, 12);
        StpLine line2 = new StpLine(12, "Line1", 23, 13);

        assertFalse(line1.equals(line2));
        assertFalse(line2.equals(line1));
    }

    @BeforeEach
    void setUp() {

        //setUp empty Lists
        possibleStartingPoints = new ArrayList<>();
        possibleDirectionVectors = new ArrayList<>();

        //Put all available Cartesian Points in List
        StpCartesianPoint[] points = {
                new StpCartesianPoint(71, "", 0.0, 0.0, 0.0),
                new StpCartesianPoint(73, "", 0.0, 20.0, 0.0),
                new StpCartesianPoint(75, "", 0.0, 20.0, 30.0),
                new StpCartesianPoint(76, "", 0.0, 0.0, 30.0)
        };
        possibleStartingPoints.addAll(Arrays.asList(points));

        //Put all available Directions in List
        StpVector[] vectors = {
                new StpVector(19, "", 63, 10.0),
                new StpVector(20, "", 64, 10.0),
                new StpVector(21, "", 65, 10.0),
                new StpVector(22, "", 66, 10.0)
        };
        possibleDirectionVectors.addAll(Arrays.asList(vectors));

        //extracted Object from String-description
        line = new StpLine(18, "", 76, 22);
    }

    @Test
    @DisplayName("should write correct StartingPoint from given Lists")
    void testConvertFromIdsStartingPoint() {

        //act -- fill the fields
        line.convertFromIds(possibleStartingPoints, possibleDirectionVectors);
        //expectations
        StpCartesianPoint expectedStartingPoint = possibleStartingPoints.get(3);
        //assert
        assertTrue(line.getStartingPoint().equals(expectedStartingPoint));
    }

    @Test
    @DisplayName("should write correct DirectionVector from given Lists")
    void testConvertFromIdsDirectionVector() {

        //act -- fill the fields
        line.convertFromIds(possibleStartingPoints, possibleDirectionVectors);
        //expectations
        StpVector expectedDirectionVector = possibleDirectionVectors.get(3);
        //assert
        assertTrue(line.getDirectionVector().equals(expectedDirectionVector));
    }

    @AfterEach
    void tearDown() {
        possibleStartingPoints = null;
        possibleDirectionVectors = null;
        line = null;
    }

}