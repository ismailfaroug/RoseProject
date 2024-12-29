package com.bona.utility;

import java.util.HashMap;
import java.util.Map;

public class DistanceCalculator {

    // Dummy data for distances between predefined locations (for demonstration purposes)
    private static final Map<String, Map<String, Double>> distanceMap = new HashMap<>();

    static {
        // Initialize dummy distance data
        Map<String, Double> newYorkDistances = new HashMap<>();
        newYorkDistances.put("Boston, MA", 215.0);
        newYorkDistances.put("Washington, DC", 225.0);

        Map<String, Double> sanFranciscoDistances = new HashMap<>();
        sanFranciscoDistances.put("Los Angeles", 380.0);
        sanFranciscoDistances.put("San Diego", 500.0);

        distanceMap.put("New York", newYorkDistances);
        distanceMap.put("San Francisco", sanFranciscoDistances);
    }

    /**
     * Calculates the distance between two locations.
     * Replace this implementation with an actual API call or logic to compute distance.
     *
     * @param pickupLocation    The starting location.
     * @param dropOffLocation   The destination location.
     * @return The distance in miles.
     * @throws IllegalArgumentException if locations are invalid.
     */
    public static double calculateDistance(String pickupLocation, String dropOffLocation) {
        if (pickupLocation == null || dropOffLocation == null || 
            pickupLocation.trim().isEmpty() || dropOffLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Pickup or drop-off location is invalid.");
        }

        pickupLocation = pickupLocation.trim();
        dropOffLocation = dropOffLocation.trim();

        // Check if the pickup location exists in the map
        if (distanceMap.containsKey(pickupLocation)) {
            Map<String, Double> destinations = distanceMap.get(pickupLocation);
            // Check if the drop-off location exists for the pickup location
            if (destinations.containsKey(dropOffLocation)) {
                return destinations.get(dropOffLocation);
            }
        }

        // Default fallback for unknown locations
        System.err.println("Locations not found in predefined data. Returning default distance.");
        return 100.0; // Default distance in miles
    }
}
