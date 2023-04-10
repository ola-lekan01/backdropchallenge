package com.backdrop.utils;

import org.junit.jupiter.api.Test;

import static com.backdrop.utils.StringUtils.getLevenshteinDistance;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testGetLevenshteinDistanceSameStringsReturnsZero() {
        assertEquals(0, getLevenshteinDistance("hello", "hello"));
    }

    @Test
    public void testGetLevenshteinDistanceOnEmptyStringReturnsTheLengthOfString() {
        assertEquals(5, getLevenshteinDistance("", "hello"));
    }

    @Test
    public void testGetLevenshteinDistanceLongerStringReturnsTheDifferenceInString() {
        assertEquals(2, getLevenshteinDistance("flower", "flow"));
    }

    @Test
    public void testGetLevenshteinDistanceDifferentStringsReturnsTheDifferenceInStrings() {
        assertEquals(3, getLevenshteinDistance("kitten", "sitting"));
    }
}