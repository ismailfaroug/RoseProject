package com.bona.paypal;

import com.paypal.base.rest.APIContext;

public class PayPalConfig {

    private static final String CLIENT_ID = "Your_Client_ID"; // Replace with your actual Client ID
    private static final String CLIENT_SECRET = "Your_Client_Secret"; // Replace with your actual Client Secret
    private static final String MODE = "sandbox"; // Use "live" for production

    public static APIContext getAPIContext() {
        return new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
    }
}
