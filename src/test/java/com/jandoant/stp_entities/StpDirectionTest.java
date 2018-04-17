package com.jandoant.stp_entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StpDirectionTest {

    @Test
    @DisplayName("should return true if the two compared instances have the same values")
    void testEquals() {
        StpDirection dir1 = new StpDirection(12, "Direction1", 3.2, 1.2, 17.1);
        StpDirection dir2 = new StpDirection(12, "Direction1", 3.2, 1.2, 17.1);

        assertTrue(dir1.equals(dir2));
        assertTrue(dir2.equals(dir1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different id-values")
    void testEqualsNotId() {
        StpDirection dir1 = new StpDirection(12, "Direction1", 3.2, 1.2, 17.1);
        StpDirection dir2 = new StpDirection(10, "Direction1", 3.2, 1.2, 17.1);

        assertFalse(dir1.equals(dir2));
        assertFalse(dir2.equals(dir1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different name-values")
    void testEqualsNotName() {
        StpDirection dir1 = new StpDirection(12, "Direction1", 3.2, 1.2, 17.1);
        StpDirection dir2 = new StpDirection(12, "Direction2", 3.2, 1.2, 17.1);

        assertFalse(dir1.equals(dir2));
        assertFalse(dir2.equals(dir1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different x-values")
    void testEqualsNotX() {
        StpDirection dir1 = new StpDirection(12, "Direction1", 3.2, 1.2, 17.1);
        StpDirection dir2 = new StpDirection(12, "Direction1", 3.21, 1.2, 17.1);

        assertFalse(dir1.equals(dir2));
        assertFalse(dir2.equals(dir1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different y-values")
    void testEqualsNotY() {
        StpDirection dir1 = new StpDirection(12, "Direction1", 3.2, 1.2, 17.1);
        StpDirection dir2 = new StpDirection(12, "Direction1", 3.2, 1.24, 17.1);

        assertFalse(dir1.equals(dir2));
        assertFalse(dir2.equals(dir1));
    }

    @Test
    @DisplayName("should return false if the two compared instances have different z-values")
    void testEqualsNotZ() {
        StpDirection dir1 = new StpDirection(12, "Direction1", 3.2, 1.2, 17.15);
        StpDirection dir2 = new StpDirection(12, "Direction1", 3.2, 1.2, 17.1);

        assertFalse(dir1.equals(dir2));
        assertFalse(dir2.equals(dir1));
    }

    @Test
    @DisplayName("should display the correct Message to describe Object-Instance")
    void testToString() {
        StpDirection dir = new StpDirection(12, "Direction1", 3.3, 4.4, 5.5);

        String expected = "StpDirection{id=12, name='Direction1', xDirection=3.3, yDirection=4.4, zDirection=5.5}";

        assertEquals(dir.toString(), expected);
    }

}