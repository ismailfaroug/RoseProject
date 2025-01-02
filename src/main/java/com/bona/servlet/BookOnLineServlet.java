package com.bona.servlet;

import com.bona.deo.RiderDAO;
import com.bona.entity.Rider;
import com.bona.paypal.PayPalConfig;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookOnLineServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BookOnLineServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LOGGER.log(Level.INFO, "BookOnLineServlet execution started");

            // Retrieve and validate booking parameters
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            String phoneNumber = req.getParameter("phoneNumber");
            String pickupLocation = req.getParameter("pickupLocation");
            String dropOffLocation = req.getParameter("dropOffLocation");
            String numPassengersParam = req.getParameter("numPassengers");

            if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                pickupLocation == null || pickupLocation.trim().isEmpty() ||
                dropOffLocation == null || dropOffLocation.trim().isEmpty()) {
                LOGGER.log(Level.WARNING, "Missing required fields");
                req.setAttribute("error", "Required fields are missing. Please provide all necessary information.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
                return;
            }

            // Parse and calculate pricing
            int numPassengers = (numPassengersParam != null && !numPassengersParam.trim().isEmpty())
                    ? Integer.parseInt(numPassengersParam) : 1;
            double basePrice = 20.0;
            double perPassengerCost = 5.0;
            double originalPrice = basePrice + (perPassengerCost * numPassengers);
            double discount = calculateDiscount(numPassengers, originalPrice);
            double finalPrice = originalPrice - discount;

            // Create a Rider object
            Rider rider = new Rider();
            rider.setFirstName(firstName);
            rider.setLastName(lastName);
            rider.setEmail(email);
            rider.setPhoneNumber(phoneNumber);
            rider.setPickupLocation(pickupLocation);
            rider.setDropOffLocation(dropOffLocation);
            rider.setNumPassengers(numPassengers);
            rider.setPrice(finalPrice);

            // Save the Rider in the database
            RiderDAO riderDAO = new RiderDAO();
            boolean success = riderDAO.saveRider(rider);

            if (success) {
                LOGGER.log(Level.INFO, "Rider saved successfully. Redirecting to payment...");
                
                // Create PayPal payment
                String approvalUrl = createPayment(finalPrice, req, resp);
                resp.sendRedirect(approvalUrl); // Redirect the rider to the payment approval URL
            } else {
                LOGGER.log(Level.WARNING, "Failed to save Rider.");
                req.setAttribute("error", "Failed to save booking. Please try again.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing booking", e);
            req.setAttribute("error", "An error occurred while processing your request: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    /**
     * Calculates discount based on the number of passengers and original price.
     */
    private double calculateDiscount(int numPassengers, double originalPrice) {
        if (numPassengers >= 5) {
            return originalPrice * 0.1;
        }
        return 0.0;
    }

    /**
     * Creates a PayPal payment and returns the approval URL.
     */
    private String createPayment(double price, HttpServletRequest req, HttpServletResponse resp) throws PayPalRESTException {
        // Create payment details
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.format("%.2f", price)); // Set the total amount

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Ride booking payment");

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        // Set redirect URLs
        String cancelUrl = req.getRequestURL().toString() + "?cancel=true";
        String successUrl = req.getRequestURL().toString() + "?success=true";
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        payment.setRedirectUrls(redirectUrls);

        // Create payment using PayPal API
        APIContext apiContext = PayPalConfig.getAPIContext();
        Payment createdPayment = payment.create(apiContext);

        // Retrieve approval URL
        for (Links link : createdPayment.getLinks()) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                return link.getHref();
            }
        }

        throw new PayPalRESTException("Approval URL not found in PayPal response.");
    }
}
