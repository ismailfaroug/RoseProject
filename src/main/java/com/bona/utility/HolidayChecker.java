package com.bona.utility;

import java.util.HashSet;
import java.util.Set;

public class HolidayChecker {

    // Predefined list of holidays
    private static final Set<String> predefinedHolidays = new HashSet<>();

    static {
        // Add predefined holidays in "yyyy-MM-dd" format
        predefinedHolidays.add("2024-01-01"); // New Year's Day
        predefinedHolidays.add("2024-12-25"); // Christmas Day
        predefinedHolidays.add("2024-07-04"); // Independence Day
        // Add more holidays as needed
    }

    public static boolean isHoliday(String date) {
        // Check if the date is a holiday
        return predefinedHolidays.contains(date);
    }
}


