package com.bona.utility;

import com.bona.entity.Rider;

public class RiderUtility {

    // Validate Rider Data
    public static boolean validateRider(Rider rider) {
        return rider.getFirstName() != null && !rider.getFirstName().isEmpty()
                && rider.getLastName() != null && !rider.getLastName().isEmpty()
                && rider.getEmail() != null && !rider.getEmail().isEmpty()
                && rider.getPhoneNumber() != null && !rider.getPhoneNumber().isEmpty();
    }
}

