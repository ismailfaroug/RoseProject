package com.bona.servlet;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TwilioCallServlet extends HttpServlet {
    // Twilio credentials (replace with your actual details)
    public static final String ACCOUNT_SID = "your_account_sid";
    public static final String AUTH_TOKEN = "your_auth_token";
    public static final String TWILIO_PHONE_NUMBER = "+1234567890"; // Replace with your Twilio number

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get recipient's phone number and custom message
        String recipientPhone = req.getParameter("recipientPhone");
        String message = req.getParameter("message");

        if (recipientPhone == null || recipientPhone.isEmpty()) {
            resp.getWriter().write("Error: Recipient phone number is required.");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (message == null || message.isEmpty()) {
            message = "Hello, this is a call from your application. Thank you!";
        }

        try {
            // Initialize Twilio
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            // Create the call with TwiML response
            Call call = Call.creator(
                    new PhoneNumber(recipientPhone),          // To number
                    new PhoneNumber(TWILIO_PHONE_NUMBER),     // From number
                    new com.twilio.type.Twiml("<Response><Say>" + message + "</Say></Response>")
            ).create();

            // Respond with success message
            resp.getWriter().write("Call initiated successfully. Call SID: " + call.getSid());
        } catch (Exception e) {
            // Handle errors
            resp.getWriter().write("Error initiating call: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
