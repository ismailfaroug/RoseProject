package com.bona.deo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bona.connection.ConnectionFactory;
import com.bona.entity.Rider;

public class RiderDAO {

    // Save Rider to the database
    public boolean saveRider(Rider rider) {
        String sql = "INSERT INTO riders (first_name, last_name, email, phone_number, pickup_location, drop_off_location, " +
                "pickup_date, pickup_time, num_passengers, require_wheelchair_van, require_child_seat, payment_type, " +
                "confirmation_request, book_return, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            setRiderParameters(stmt, rider);
            stmt.setDouble(15, rider.getPrice()); // Set the price as the 15th parameter

            // Execute the update and return success status
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error saving rider: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Fetch a Rider by ID
    public Rider getRiderById(int id) {
        String sql = "SELECT * FROM riders WHERE id = ?";
        Rider rider = null;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id); // Set the ID parameter
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    rider = mapResultSetToRider(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching rider by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return rider;
    }

    // Fetch all Riders from the database
    public List<Rider> getAllRiders() {
        String sql = "SELECT * FROM riders ORDER BY id DESC"; // Fetch latest first
        List<Rider> riders = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                riders.add(mapResultSetToRider(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all riders: " + e.getMessage());
            e.printStackTrace();
        }

        return riders;
    }

    // Update an existing Rider
    public boolean updateRider(Rider rider) {
        String sql = "UPDATE riders SET first_name = ?, last_name = ?, email = ?, phone_number = ?, pickup_location = ?, " +
                "drop_off_location = ?, pickup_date = ?, pickup_time = ?, num_passengers = ?, " +
                "require_wheelchair_van = ?, require_child_seat = ?, payment_type = ?, confirmation_request = ?, " +
                "book_return = ?, price = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            setRiderParameters(stmt, rider);
            stmt.setDouble(15, rider.getPrice()); // Set the price
            stmt.setInt(16, rider.getId()); // Set the ID for the WHERE clause

            // Execute the update and return success status
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating rider: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Delete a Rider by ID
    public boolean deleteRider(int id) {
        String sql = "DELETE FROM riders WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id); // Set the ID parameter
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting rider: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Private helper to map ResultSet to Rider object
    private Rider mapResultSetToRider(ResultSet rs) throws SQLException {
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

    // Private helper to set PreparedStatement parameters
    private void setRiderParameters(PreparedStatement stmt, Rider rider) throws SQLException {
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
    }
}
