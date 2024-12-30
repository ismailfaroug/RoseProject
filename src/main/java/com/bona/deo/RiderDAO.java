package com.bona.deo;

import com.bona.connection.ConnectionFactory;
import com.bona.entity.Rider;

import java.sql.*;

/**
 * Data Access Object for Rider operations.
 */
public class RiderDAO {

    // Save Rider to the database
    public boolean saveRider(Rider rider) {
        String sql = "INSERT INTO riders (first_name, last_name, email, phone_number, pickup_location, drop_off_location, " +
                "pickup_date, pickup_time, num_passengers, require_wheelchair_van, require_child_seat, payment_type, " +
                "confirmation_request, book_return, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters
            stmt.setString(1, rider.getFirstName());
            stmt.setString(2, rider.getLastName());
            stmt.setString(3, rider.getEmail());
            stmt.setString(4, rider.getPhoneNumber());
            stmt.setString(5, rider.getPickupLocation());
            stmt.setString(6, rider.getDropOffLocation());
            stmt.setString(7, rider.getPickupDate());
            stmt.setString(8, rider.getPickupTime());
            stmt.setInt(9, rider.getNumPassengers());
            stmt.setBoolean(10, rider.isRequireWheelchairVan());
            stmt.setString(11, rider.getRequireChildSeat());
            stmt.setString(12, rider.getPaymentType());
            stmt.setString(13, rider.getConfirmationRequest());
            stmt.setBoolean(14, rider.isBookReturn());
            stmt.setDouble(15, rider.getPrice());

            // Execute update and retrieve the generated ID
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        rider.setId(generatedKeys.getInt(1)); // Set the generated ID to the Rider object
                    }
                }
                return true;
            } else {
                System.err.println("No rows were inserted.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error saving rider: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
