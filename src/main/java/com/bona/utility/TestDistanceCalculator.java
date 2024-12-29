package com.bona.utility;

public class TestDistanceCalculator {
    public static void main(String[] args) {
        try {
            // Replace with valid origin and destination addresses
            String origin = "New York, NY";
            String destination = "Boston, MA";

            // Call the getDistance method
            double distance = DistanceCalculator.calculateDistance(origin, destination);

            // Print the distance
            System.out.println("Calculated Distance: " + distance + " miles");
        } catch (Exception e) {
            // Print the error details
            System.err.println("Error occurred:");
            e.printStackTrace();
        }
    }
}

