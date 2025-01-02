package com.bona.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

import java.net.URI;

public class TwilioVoiceCall {
    // Twilio Account SID and Auth Token (replace with actual credentials)
    private static final String ACCOUNT_SID = "your_account_sid";
    private static final String AUTH_TOKEN = "your_auth_token";
    private static final String TWILIO_PHONE_NUMBER = "+1234567890"; // Replace with your Twilio number

    static {
        // Initialize Twilio once
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    /**
     * Makes a voice call using Twilio.
     *
     * @param toPhoneNumber The recipient's phone number (E.164 format, e.g., +1234567890)
     * @param message       The message to be played during the call
     * @return true if the call was successfully initiated, false otherwise
     */
    public static boolean makeCall(String toPhoneNumber, String message) {
        try {
            if (toPhoneNumber == null || toPhoneNumber.isEmpty()) {
                throw new IllegalArgumentException("Recipient phone number is required.");
            }

            if (message == null || message.isEmpty()) {
                message = "Hello, this is a call from your application. Thank you!";
            }

            // Generate TwiML instructions dynamically (example hosted TwiML URL)
            String twimlUrl = "https://handler.twilio.com/twiml/EHXXXXXXXXXXXXXXXXXXXXXXXXXXXX"; // Replace with your actual TwiML URL

            // Create the call
            Call call = Call.creator(
                    new PhoneNumber(toPhoneNumber),       // To
                    new PhoneNumber(TWILIO_PHONE_NUMBER), // From
                    new URI(twimlUrl))                   // TwiML URL
                    .create();

            System.out.println("Call initiated successfully. SID: " + call.getSid());
            return true;

        } catch (Exception e) {
            System.err.println("Error initiating call: " + e.getMessage());
            return false;
        }
    }
}
