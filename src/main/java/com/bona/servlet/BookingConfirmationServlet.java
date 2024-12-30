package com.bona.servlet;

import com.bona.twilio.TwilioVoiceCall;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookingConfirmationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recipientPhone = req.getParameter("recipientPhone");
        String message = "Your booking has been confirmed. Thank you for choosing our service!";

        // Trigger the call
       // TwilioVoiceCall.makeCall(recipientPhone, message);
        TwilioVoiceCall.makeCall(recipientPhone, message);

        resp.getWriter().write("Call initiated to " + recipientPhone);
    }
}
