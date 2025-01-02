package com.bona.paypal;

import java.util.ArrayList;
import java.util.List;
import com.paypal.api.payments.Links;
import com.paypal.base.rest.PayPalRESTException;
 
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;

public class PayPalPaymentService {
    public String createPayment(Double total, String currency, String method, String intent, String cancelUrl, String successUrl) throws PayPalRESTException {
        // Set up the payment amount
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total)); // Ensure 2 decimal places

        // Set up the transaction
        Transaction transaction = new Transaction();
        transaction.setDescription("Ride Booking Payment");
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        // Set up the payer
        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        // Set up the payment
        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        // Set up redirect URLs
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        // Execute the payment
        APIContext apiContext = PayPalConfig.getAPIContext();
        Payment createdPayment = payment.create(apiContext);

        // Return the approval URL
        for (com.paypal.api.payments.Links link : createdPayment.getLinks()) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                return link.getHref();
            }
        }
        return null;
    }
}

