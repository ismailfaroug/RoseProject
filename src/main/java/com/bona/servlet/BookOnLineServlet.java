package com.bona.servlet;

import com.bona.deo.RiderDAO;
import com.bona.entity.Rider;
import com.bona.utility.DistanceCalculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookOnLineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve and validate parameters
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String pickupLocation = request.getParameter("pickupLocation");
            String dropOffLocation = request.getParameter("dropOffLocation");
            String pickupDate = request.getParameter("pickupDate");
            String pickupTime = request.getParameter("pickupTime");
            int numPassengers = Integer.parseInt(request.getParameter("numPassengers"));
            boolean requireWheelchairVan = Boolean.parseBoolean(request.getParameter("requireWheelchairVan"));
            String requireChildSeat = request.getParameter("requireChildSeat");
            String paymentType = request.getParameter("paymentType");
            String confirmationRequest = request.getParameter("confirmationRequest");
            boolean bookReturn = Boolean.parseBoolean(request.getParameter("bookReturn"));

            // Validate phone number length
            if (phoneNumber.length() > 15) {
                throw new IllegalArgumentException("Phone number exceeds the maximum allowed length of 15 characters.");
            }

            // Calculate the distance
            double miles = DistanceCalculator.calculateDistance(pickupLocation, dropOffLocation);
            if (miles <= 0) {
                throw new IllegalArgumentException("Invalid distance calculated for the provided locations.");
            }

            // Calculate the trip price
            double tripPrice = miles * 2; // Base price: $2 per mile
            if (pickupTime.compareTo("12:00") > 0) {
                tripPrice = miles * 3.5; // Higher price after 12 PM
            }
            if ("Small".equalsIgnoreCase(requireChildSeat) || "Medium".equalsIgnoreCase(requireChildSeat) || "Large".equalsIgnoreCase(requireChildSeat)) {
                tripPrice += 5; // Add $5 for child seat
            }

            // Create and save the rider
            Rider rider = new Rider();
            rider.setFirstName(firstName);
            rider.setLastName(lastName);
            rider.setPhoneNumber(phoneNumber);
            rider.setEmail(email);
            rider.setPickupLocation(pickupLocation);
            rider.setDropOffLocation(dropOffLocation);
            rider.setPickupDate(pickupDate);
            rider.setPickupTime(pickupTime);
            rider.setNumPassengers(numPassengers);
            rider.setRequireWheelchairVan(requireWheelchairVan);
            rider.setRequireChildSeat(requireChildSeat);
            rider.setPaymentType(paymentType);
            rider.setConfirmationRequest(confirmationRequest);
            rider.setBookReturn(bookReturn);
            rider.setPrice(tripPrice);

            RiderDAO riderDAO = new RiderDAO();
            if (!riderDAO.saveRider(rider)) {
                throw new Exception("Failed to save booking information.");
            }

            // Redirect to success page
            response.sendRedirect("success.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // Redirect to an error page with a detailed message
            response.sendRedirect("error.jsp?errorMessage=" + e.getMessage());
        }
    }
}
