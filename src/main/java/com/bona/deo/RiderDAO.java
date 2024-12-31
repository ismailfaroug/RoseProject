package com.bona.deo;

import com.bona.connection.ConnectionFactory;
import com.bona.entity.Rider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    // Delete Rider by ID
    public boolean deleteRider(int riderId) {
        String sql = "DELETE FROM riders WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, riderId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting rider: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Fetch Rider by ID
    public Rider getRiderById(int riderId) {
        String sql = "SELECT * FROM riders WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, riderId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToRider(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching rider: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // Update Rider
    public boolean updateRider(Rider rider) {
        String sql = "UPDATE riders SET first_name = ?, last_name = ?, email = ?, phone_number = ?, pickup_location = ?, " +
                "drop_off_location = ?, pickup_date = ?, pickup_time = ?, num_passengers = ?, require_wheelchair_van = ?, " +
                "require_child_seat = ?, payment_type = ?, confirmation_request = ?, book_return = ?, price = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

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
            stmt.setInt(16, rider.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Error updating rider: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Fetch all Riders
    public List<Rider> getAllRiders() {
        List<Rider> riders = new ArrayList<>();
        String sql = "SELECT * FROM riders";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                riders.add(mapRowToRider(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching riders: " + e.getMessage());
            e.printStackTrace();
        }

        return riders;
    }

    // Map ResultSet row to Rider object
    private Rider mapRowToRider(ResultSet rs) throws SQLException {
        Rider rider = new Rider();
        rider.setId(rs.getInt("id"));
        rider.setFirstName(rs.getString("first_name"));
        rider.setLastName(rs.getString("last_name"));
        rider.setEmail(rs.getString("email"));
        rider.setPhoneNumber(rs.getString("phone_number"));
        rider.setPickupLocation(rs.getString("pickup_location"));
        rider.setDropOffLocation(rs.getString("drop_off_location"));
        rider.setPickupDate(rs.getString("pickup_date"));
        rider.setPickupTime(rs.getString("pickup_time"));
        rider.setNumPassengers(rs.getInt("num_passengers"));
        rider.setRequireWheelchairVan(rs.getBoolean("require_wheelchair_van"));
        rider.setRequireChildSeat(rs.getString("require_child_seat"));
        rider.setPaymentType(rs.getString("payment_type"));
        rider.setConfirmationRequest(rs.getString("confirmation_request"));
        rider.setBookReturn(rs.getBoolean("book_return"));
        rider.setPrice(rs.getDouble("price"));
        return rider;
    }
}
