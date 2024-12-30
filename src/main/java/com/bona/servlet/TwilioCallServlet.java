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
    public static final String ACCOUNT_SID = "your_account_sid";
    public static final String AUTH_TOKEN = "your_auth_token";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recipientPhone = req.getParameter("recipientPhone");
        String message = "Hello, this is a call from your application. Thank you!";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Call call = Call.creator(
                new PhoneNumber(recipientPhone), // To number
                new PhoneNumber("your_twilio_phone_number"), // From number
                new com.twilio.type.Twiml("<Response><Say>" + message + "</Say></Response>")
        ).create();

        resp.getWriter().write("Call initiated successfully. Call SID: " + call.getSid());
    }
}

