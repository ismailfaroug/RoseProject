package com.bona.deo;

import com.bona.connection.ConnectionFactory;
import com.bona.entity.Rider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Rider operations.
 */
public class RiderDAO {
    private static final Logger LOGGER = Logger.getLogger(RiderDAO.class.getName());

    // Save Rider to the database
    public boolean saveRider(Rider rider) {
        String sql = "INSERT INTO riders (first_name, last_name, email, phone_number, pickup_location, drop_off_location, " +
                "pickup_date, pickup_time, num_passengers, require_wheelchair_van, require_child_seat, payment_type, " +
                "confirmation_request, book_return, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            setRiderParameters(stmt, rider);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        rider.setId(generatedKeys.getInt(1)); // Set the generated ID to the Rider object
                        LOGGER.log(Level.INFO, "Rider saved with ID: {0}", rider.getId());
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving Rider: {0}", e.getMessage());
            e.printStackTrace();
        }
        return false;
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
            LOGGER.log(Level.SEVERE, "Error fetching Rider: {0}", e.getMessage());
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

            setRiderParameters(stmt, rider);
            stmt.setInt(16, rider.getId()); // ID for WHERE clause

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                LOGGER.log(Level.INFO, "Rider with ID {0} updated successfully.", rider.getId());
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating Rider: {0}", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Delete Rider by ID
    public boolean deleteRider(int riderId) {
        String sql = "DELETE FROM riders WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, riderId);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                LOGGER.log(Level.INFO, "Rider with ID {0} deleted successfully.", riderId);
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting Rider: {0}", e.getMessage());
            e.printStackTrace();
        }
        return false;
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
            LOGGER.log(Level.SEVERE, "Error fetching Riders: {0}", e.getMessage());
            e.printStackTrace();
        }
        return riders;
    }

    // Helper method to set parameters for Rider
    private void setRiderParameters(PreparedStatement stmt, Rider rider) throws SQLException {
        stmt.setString(1, rider.getFirstName());
        stmt.setString(2, rider.getLastName());
        stmt.setString(3, rider.getEmail());
        stmt.setString(4, rider.getPhoneNumber());
        stmt.setString(5, rider.getPickupLocation());
        stmt.setString(6, rider.getDropOffLocation());
        stmt.setDate(7, rider.getPickupDate() != null ? Date.valueOf(rider.getPickupDate()) : null);
        stmt.setTime(8, rider.getPickupTime() != null ? Time.valueOf(rider.getPickupTime()) : null);
        stmt.setInt(9, rider.getNumPassengers());
        stmt.setBoolean(10, rider.isRequireWheelchairVan());
        stmt.setString(11, rider.getRequireChildSeat() != null ? rider.getRequireChildSeat() : null);
        stmt.setString(12, rider.getPaymentType() != null ? rider.getPaymentType() : null);
        stmt.setString(13, rider.getConfirmationRequest() != null ? rider.getConfirmationRequest() : null);
        stmt.setBoolean(14, rider.isBookReturn());
        stmt.setDouble(15, rider.getPrice());
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
        rider.setPickupDate(rs.getDate("pickup_date") != null ? rs.getDate("pickup_date").toString() : null);
        rider.setPickupTime(rs.getTime("pickup_time") != null ? rs.getTime("pickup_time").toString() : null);
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


