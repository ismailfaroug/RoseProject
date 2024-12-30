package com.bona.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

import java.net.URI;

public class TwilioVoiceCall {

    // Twilio Account SID and Auth Token
    private static final String ACCOUNT_SID = "your_account_sid_here"; // Replace with your actual SID
    private static final String AUTH_TOKEN = "your_auth_token_here"; // Replace with your actual Auth Token

    // Twilio Phone Number
    private static final String TWILIO_PHONE_NUMBER = "+1234567890"; // Replace with your Twilio number

    static {
        // Initialize Twilio with your account SID and Auth Token
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    /**
     * Method to make a voice call using Twilio
     *
     * @param toPhoneNumber The recipient's phone number (must be in E.164 format, e.g., +1234567890)
     * @param message       The message to be played during the call
     * @return true if the call was successfully initiated, false otherwise
     */
    public static boolean makeCall(String toPhoneNumber, String message) {
        try {
            // URL to TwiML instructions (you'll need to host this XML file)
            String twimlUrl = "https://handler.twilio.com/twiml/EHXXXXXXXXXXXXXXXXXXXXXXXXXXXX"; // Replace with your TwiML URL

            // Create the call
            Call call = Call.creator(
                    new PhoneNumber(toPhoneNumber),       // To
                    new PhoneNumber(TWILIO_PHONE_NUMBER), // From
                    new URI(twimlUrl))                   // TwiML URL
                    .create();

            System.out.println("Call initiated. SID: " + call.getSid());
            return true;

        } catch (Exception e) {
            System.err.println("Error initiating call: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
