package com.bona.servlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import java.io.IOException;
import java.util.logging.Logger;

public class ConfirmBookingServlet extends HttpServlet {

    private static final String GOOGLE_API_KEY = "AIzaSyDqkIUNzS1TeCB-NypkzreXTz5AXX4706U";
    private static final Logger LOGGER = Logger.getLogger(ConfirmBookingServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pickupLocation = request.getParameter("pickupLocation");
        String dropOffLocation = request.getParameter("dropOffLocation");
        int numPassengers = Integer.parseInt(request.getParameter("numPassengers"));

        try {
            int distance = getDistanceFromGoogleMaps(pickupLocation, dropOffLocation);
            double basePrice = 50.0;
            double pricePerKm = 10.0;
            double tripPrice = basePrice + (distance * pricePerKm) + (numPassengers * 5);

            request.setAttribute("pickupLocation", pickupLocation);
            request.setAttribute("dropOffLocation", dropOffLocation);
            request.setAttribute("distance", distance);
            request.setAttribute("tripPrice", tripPrice);

            request.getRequestDispatcher("confirm.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.severe("Error calculating distance or price: " + e.getMessage());
            request.setAttribute("errorMessage", "An error occurred. Please try again later.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private int getDistanceFromGoogleMaps(String pickupLocation, String dropOffLocation) {
        try {
            String origin = pickupLocation.replace(" ", "+");
            String destination = dropOffLocation.replace(" ", "+");
            String urlString = String.format(
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s",
                origin, destination, GOOGLE_API_KEY
            );

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            int distanceInMeters = jsonResponse
                .getJSONArray("rows")
                .getJSONObject(0)
                .getJSONArray("elements")
                .getJSONObject(0)
                .getJSONObject("distance")
                .getInt("value");

            return distanceInMeters / 1000;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get distance from Google Maps API: " + e.getMessage());
        }
    }
}
