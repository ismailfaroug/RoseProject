package com.bona.servlet;

import com.bona.paypal.PayPalConfig;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentStatusServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PaymentStatusServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paymentId = req.getParameter("paymentId");
        String payerId = req.getParameter("PayerID");
        String cancelParam = req.getParameter("cancel");

        try {
            if (cancelParam != null) {
                LOGGER.log(Level.INFO, "Payment cancelled by the user.");
                req.setAttribute("message", "Payment was cancelled by the user.");
                req.getRequestDispatcher("cancel.jsp").forward(req, resp);
                return;
            }

            if (paymentId == null || payerId == null) {
                LOGGER.log(Level.WARNING, "Invalid payment or payer details.");
                req.setAttribute("error", "Invalid payment details provided.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
                return;
            }

            // Execute the payment
            APIContext apiContext = PayPalConfig.getAPIContext();
            Payment payment = new Payment();
            payment.setId(paymentId);

            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(payerId);

            Payment executedPayment = payment.execute(apiContext, paymentExecution);

            LOGGER.log(Level.INFO, "Payment executed successfully. Details: {0}", executedPayment.toJSON());
            
            // Forward to success page with payment details
            req.setAttribute("paymentDetails", executedPayment);
            req.getRequestDispatcher("success.jsp").forward(req, resp);
        } catch (PayPalRESTException e) {
            LOGGER.log(Level.SEVERE, "Error occurred during payment execution", e);
            req.setAttribute("error", "An error occurred while executing the payment: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
