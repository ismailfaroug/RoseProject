package com.bona.utility;

import com.bona.entity.Rider;
import java.util.regex.Pattern;

public class RiderUtility {

    // Email validation regex pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    // Phone number validation regex pattern
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\+?[0-9]{10,15}$"
    );

    // Validate Rider Data
    public static boolean validateRider(Rider rider) {
        if (rider == null) {
            System.err.println("Rider object is null.");
            return false;
        }

        if (!isNonEmpty(rider.getFirstName())) {
            System.err.println("First name is invalid: " + rider.getFirstName());
            return false;
        }

        if (!isNonEmpty(rider.getLastName())) {
            System.err.println("Last name is invalid: " + rider.getLastName());
            return false;
        }

        if (!isValidEmail(rider.getEmail())) {
            System.err.println("Email is invalid: " + rider.getEmail());
            return false;
        }

        if (!isValidPhoneNumber(rider.getPhoneNumber())) {
            System.err.println("Phone number is invalid: " + rider.getPhoneNumber());
            return false;
        }

        return true;
    }

    // Check if a string is non-empty
    private static boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Validate email format
    private static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    // Validate phone number format
    private static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }
}


