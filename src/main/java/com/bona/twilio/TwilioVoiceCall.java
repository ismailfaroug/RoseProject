package com.bona.twilio;
import com.bona.twilio.TwilioVoiceCall;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

public class TwilioVoiceCall {
    public static final String ACCOUNT_SID = "your_account_sid";
    public static final String AUTH_TOKEN = "your_auth_token";

    public static void makeCall(String recipientPhoneNumber, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Call call = Call.creator(
                new PhoneNumber(recipientPhoneNumber), // To number
                new PhoneNumber("your_twilio_phone_number"), // From number
                new com.twilio.type.Twiml("<Response><Say>" + message + "</Say></Response>") // Custom message
        ).create();

        System.out.println("Call SID: " + call.getSid());
    }
}
