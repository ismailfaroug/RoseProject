package com.bona.servlet;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.auth.oauth2.GoogleCredentials;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet for handling online booking.
 */
public class BookOnLineServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BookOnLineServlet.class.getName());
    private DatabaseReference databaseReference;

    @Override
    public void init() throws ServletException {
        try {
            String firebaseConfigPath = getServletContext().getInitParameter("FIREBASE_CONFIG_PATH");
            InputStream serviceAccount = getServletContext().getResourceAsStream(firebaseConfigPath);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://rose-7d608-default-rtdb.firebaseio.com/") // Updated URL
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            databaseReference = FirebaseDatabase.getInstance().getReference("bookings");
            LOGGER.log(Level.INFO, "Firebase initialized successfully");
        } catch (IOException e) {
            throw new ServletException("Failed to initialize Firebase Admin SDK", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LOGGER.log(Level.INFO, "BookOnLineServlet execution started");

            // Retrieve and validate booking parameters
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            String phoneNumber = req.getParameter("phoneNumber");
            String pickupLocation = req.getParameter("pickupLocation");
            String dropOffLocation = req.getParameter("dropOffLocation");
            String numPassengersParam = req.getParameter("numPassengers");

            if (!validateBookingDetails(firstName, lastName, phoneNumber, pickupLocation, dropOffLocation)) {
                LOGGER.log(Level.WARNING, "Missing required fields");
                req.setAttribute("errorMessage", "Required fields are missing. Please provide all necessary information.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
                return;
            }

            // Parse and calculate pricing
            int numPassengers = parseNumberOfPassengers(numPassengersParam);
            double tripPrice = calculateTripPrice(numPassengers);
            int distance = 10; // Example static value; replace with a real distance calculation.

            // Save booking details to Firebase
            String bookingId = databaseReference.push().getKey();
            Map<String, Object> bookingData = new HashMap<>();
            bookingData.put("firstName", firstName);
            bookingData.put("lastName", lastName);
            bookingData.put("email", email);
            bookingData.put("phoneNumber", phoneNumber);
            bookingData.put("pickupLocation", pickupLocation);
            bookingData.put("dropOffLocation", dropOffLocation);
            bookingData.put("numPassengers", numPassengers);
            bookingData.put("tripPrice", tripPrice);
            bookingData.put("distance", distance);

            if (bookingId != null) {
                databaseReference.child(bookingId).setValueAsync(bookingData);
            }

            // Set attributes for confirmation
            req.setAttribute("firstName", firstName);
            req.setAttribute("lastName", lastName);
            req.setAttribute("phoneNumber", phoneNumber);
            req.setAttribute("pickupLocation", pickupLocation);
            req.setAttribute("dropOffLocation", dropOffLocation);
            req.setAttribute("numPassengers", numPassengers);
            req.setAttribute("tripPrice", tripPrice);
            req.setAttribute("distance", distance);

            // Forward to confirm.jsp
            req.getRequestDispatcher("confirm.jsp").forward(req, resp);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing booking", e);
            req.setAttribute("errorMessage", "An error occurred while processing your request: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    private boolean validateBookingDetails(String firstName, String lastName, String phoneNumber,
                                           String pickupLocation, String dropOffLocation) {
        return firstName != null && !firstName.trim().isEmpty() &&
               lastName != null && !lastName.trim().isEmpty() &&
               phoneNumber != null && !phoneNumber.trim().isEmpty() &&
               pickupLocation != null && !pickupLocation.trim().isEmpty() &&
               dropOffLocation != null && !dropOffLocation.trim().isEmpty();
    }

    private int parseNumberOfPassengers(String numPassengersParam) {
        try {
            return (numPassengersParam != null && !numPassengersParam.trim().isEmpty())
                    ? Integer.parseInt(numPassengersParam) : 1; // Default to 1 passenger
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Invalid number of passengers provided. Defaulting to 1.", e);
            return 1;
        }
    }

    private double calculateTripPrice(int numPassengers) {
        double basePrice = 50.0; // Base price for the trip
        double pricePerPassenger = 10.0; // Additional cost per passenger
        return basePrice + (pricePerPassenger * numPassengers);
    }
}
