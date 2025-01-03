package com.bona.servlet;

import com.bona.twilio.TwilioVoiceCall;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet for handling booking confirmation and sending a notification to the user.
 */
public class BookingConfirmationServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BookingConfirmationServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set response content type
        resp.setContentType("text/html;charset=UTF-8");

        try {
            LOGGER.log(Level.INFO, "BookingConfirmationServlet started processing");

            // Retrieve parameters from the request
            String recipientPhone = req.getParameter("phoneNumber");
            String tripPrice = req.getParameter("tripPrice");
            String pickupLocation = req.getParameter("pickupLocation");
            String dropOffLocation = req.getParameter("dropOffLocation");

            // Validate required parameters
            validateParameters(recipientPhone, tripPrice, pickupLocation, dropOffLocation);

            // Construct the confirmation message
            String message = String.format(
                "Your booking has been confirmed!\nTrip Details:\nPrice: $%s\nPickup: %s\nDrop-Off: %s\nThank you for choosing our service!",
                tripPrice, pickupLocation, dropOffLocation
            );

            // Initiate the call using TwilioVoiceCall
            if (TwilioVoiceCall.makeCall(recipientPhone, message)) {
                LOGGER.log(Level.INFO, "Call initiated successfully to {0}", recipientPhone);
                sendSuccessResponse(resp, recipientPhone, tripPrice, pickupLocation, dropOffLocation);
            } else {
                LOGGER.log(Level.SEVERE, "Failed to initiate call to {0}", recipientPhone);
                throw new Exception("Failed to initiate call to " + recipientPhone);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Validation error: {0}", e.getMessage());
            sendErrorResponse(resp, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred during booking confirmation", e);
            sendErrorResponse(resp, "An error occurred while processing your request: " + e.getMessage());
        }
    }

    /**
     * Validates the required parameters.
     *
     * @param recipientPhone   Phone number of the recipient
     * @param tripPrice        Trip price
     * @param pickupLocation   Pickup location
     * @param dropOffLocation  Drop-off location
     */
    private void validateParameters(String recipientPhone, String tripPrice, String pickupLocation, String dropOffLocation) {
        if (recipientPhone == null || recipientPhone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required for booking confirmation.");
        }
        if (tripPrice == null || tripPrice.trim().isEmpty() ||
            pickupLocation == null || pickupLocation.trim().isEmpty() ||
            dropOffLocation == null || dropOffLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Trip details are missing. Please ensure all required fields are provided.");
        }
    }

    /**
     * Sends a success response to the client.
     *
     * @param resp             HttpServletResponse object
     * @param recipientPhone   Recipient's phone number
     * @param tripPrice        Trip price
     * @param pickupLocation   Pickup location
     * @param dropOffLocation  Drop-off location
     */
    private void sendSuccessResponse(HttpServletResponse resp, String recipientPhone, String tripPrice,
                                     String pickupLocation, String dropOffLocation) throws IOException {
        resp.getWriter().write("<html><body>");
        resp.getWriter().write("<h2>Booking Confirmation</h2>");
        resp.getWriter().write("<p>Call initiated successfully to " + recipientPhone + "</p>");
        resp.getWriter().write("<p>Trip Details:</p>");
        resp.getWriter().write("<p>Price: $" + tripPrice + "</p>");
        resp.getWriter().write("<p>Pickup Location: " + pickupLocation + "</p>");
        resp.getWriter().write("<p>Drop-Off Location: " + dropOffLocation + "</p>");
        resp.getWriter().write("<a href='home.jsp'>Go Back to Home</a>");
        resp.getWriter().write("</body></html>");
    }

    /**
     * Sends an error response to the client.
     *
     * @param resp      HttpServletResponse object
     * @param errorMessage  Error message to display
     */
    private void sendErrorResponse(HttpServletResponse resp, String errorMessage) throws IOException {
        resp.getWriter().write("<html><body>");
        resp.getWriter().write("<h2>Error</h2>");
        resp.getWriter().write("<p>" + errorMessage + "</p>");
        resp.getWriter().write("<a href='home.jsp'>Go Back to Home</a>");
        resp.getWriter().write("</body></html>");
    }
}

