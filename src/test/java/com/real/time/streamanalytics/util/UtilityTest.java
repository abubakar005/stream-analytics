package com.real.time.streamanalytics.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    @DisplayName("Get Header Basic Authentication")
    void testGetBasicAuthHeader() {
        assertEquals("Basic c3l0YWM6NHA5Zy1EdjdULXU4ZmUtaXo2eS1TUlcy", Utility.getBasicAuthHeader());
    }

    @Test
    @DisplayName("Calculate User's Age")
    void testCalculateUserAge() {
       assertEquals(34, Utility.calculateAge("01/06/1989"));
    }

    @Test
    @DisplayName("Calculate Started Stream Events Percentage On Sytflix Platform")
    void testCalculateStartedStreamEventsPercentageOnSytflix() {
        assertEquals(25.0, Utility.calculatePercentage(1, 4));
    }

    @Test
    @DisplayName("Calculate Started Stream Events Percentage With Zero Records On Sytflix Platform")
    void testCalculateStartedStreamEventsPercentageWithZeroRecordsOnSytflix() {
        assertEquals(0, Utility.calculatePercentage(0, 0));
    }
}