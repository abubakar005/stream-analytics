package com.real.time.streamanalytics.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public final class Utility {

    public static String HEADER_AUTHORIZATION = "Authorization";
    public static String FIRST_NAME_SYTAC = "Sytac";
    public static int RELEASE_YEAR_2020 = 2020;
    public static int INDEX_ZERO = 0;
    public static int INDEX_ONE = 1;
    public static int SYTAC_NAME_MAX_VALUE = 3;
    public static String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";
    public static String AMSTERDAM_ZONE_ID = "Europe/Amsterdam";
    public static String REGEX_COMMA = ",";
    public static String STREAM_STARTED = "stream-started";
    public static String STREAM_FINISHED = "stream-finished";
    public static String PLATFORM_SYTFLIX = "Sytflix";
    public static String PLATFORM_SYTAZON = "Sytazon";
    public static String PLATFORM_SYSNEY = "Sysney";
    public static String AUTH_USER = "sytac";
    public static String AUTH_PASSWORD = "4p9g-Dv7T-u8fe-iz6y-SRW2";

    public static String getBasicAuthHeader() {
        String credentials = AUTH_USER + ":" + AUTH_PASSWORD;
        byte[] encodedAuth = Base64.getEncoder().encode(credentials.getBytes());
        return "Basic " + new String(encodedAuth);
    }

    public static int calculateAge(String dateOfBirth) {
        LocalDate birthDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate currentDate = LocalDate.now();

        Period age = Period.between(birthDate, currentDate);
        return age.getYears();
    }

    public static double calculatePercentage(int numerator, int denominator) {
        if (denominator == 0) {
            return 0;
        }

        double percentage = ((double) numerator / denominator) * 100;
        return Math.round(percentage * 100.0) / 100.0; // Round to two decimal places
    }
}
