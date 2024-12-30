package com.bona.servlet;

import com.bona.twilio.TwilioVoiceCall;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for handling booking confirmation and sending a notification to the user.
 */
public class BookingConfirmationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set response content type
        resp.setContentType("text/html;charset=UTF-8");

        try {
            // Retrieve the recipient's phone number from the request
            String recipientPhone = req.getParameter("recipientPhone");

            // Validate the phone number
            if (recipientPhone == null || recipientPhone.trim().isEmpty()) {
                throw new IllegalArgumentException("Phone number is required for booking confirmation.");
            }

            // Confirmation message
            String message = "Your booking has been confirmed. Thank you for choosing our service!";

            // Initiate the call using TwilioVoiceCall
            boolean callSuccess = TwilioVoiceCall.makeCall(recipientPhone, message);

            if (callSuccess) {
                // Respond with success message
                resp.getWriter().write("<html><body>");
                resp.getWriter().write("<h2>Booking Confirmation</h2>");
                resp.getWriter().write("<p>Call initiated successfully to " + recipientPhone + "</p>");
                resp.getWriter().write("<a href='home.jsp'>Go Back to Home</a>");
                resp.getWriter().write("</body></html>");
            } else {
                throw new Exception("Failed to initiate call to " + recipientPhone);
            }

        } catch (IllegalArgumentException e) {
            // Handle missing or invalid phone number
            resp.getWriter().write("<html><body>");
            resp.getWriter().write("<h2>Error</h2>");
            resp.getWriter().write("<p>" + e.getMessage() + "</p>");
            resp.getWriter().write("<a href='home.jsp'>Go Back to Home</a>");
            resp.getWriter().write("</body></html>");
        } catch (Exception e) {
            // Handle general errors
            e.printStackTrace();
            resp.getWriter().write("<html><body>");
            resp.getWriter().write("<h2>Error</h2>");
            resp.getWriter().write("<p>An error occurred while processing your request: " + e.getMessage() + "</p>");
            resp.getWriter().write("<a href='home.jsp'>Go Back to Home</a>");
            resp.getWriter().write("</body></html>");
        }
    }
}
