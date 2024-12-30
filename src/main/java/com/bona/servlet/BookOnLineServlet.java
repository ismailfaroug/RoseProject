package com.bona.servlet;

import com.bona.deo.RiderDAO;
import com.bona.entity.Rider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet for handling online booking requests.
 */
public class BookOnLineServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BookOnLineServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Debug log to track servlet execution
            LOGGER.log(Level.INFO, "BookOnLineServlet execution started");

            // Retrieve and validate booking parameters
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            String phoneNumber = req.getParameter("phoneNumber");
            String pickupLocation = req.getParameter("pickupLocation");
            String dropOffLocation = req.getParameter("dropOffLocation");
            String pickupDate = req.getParameter("pickupDate");
            String pickupTime = req.getParameter("pickupTime");
            String numPassengersParam = req.getParameter("numPassengers");
            String requireWheelchairVanParam = req.getParameter("requireWheelchairVan");
            String requireChildSeat = req.getParameter("requireChildSeat");
            String paymentType = req.getParameter("paymentType");
            String confirmationRequest = req.getParameter("confirmationRequest");
            String bookReturnParam = req.getParameter("bookReturn");
            String priceParam = req.getParameter("price");

            // Handle missing or invalid parameters
            if (firstName == null || lastName == null || pickupLocation == null || dropOffLocation == null) {
                LOGGER.log(Level.WARNING, "Missing required fields: firstName, lastName, pickupLocation, dropOffLocation");
                req.setAttribute("error", "Required fields are missing. Please provide all necessary information.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
                return;
            }

            // Map parameters to a Rider entity
            Rider rider = new Rider();
            rider.setFirstName(firstName);
            rider.setLastName(lastName);
            rider.setEmail(email);
            rider.setPhoneNumber(phoneNumber);
            rider.setPickupLocation(pickupLocation);
            rider.setDropOffLocation(dropOffLocation);
            rider.setPickupDate(pickupDate);
            rider.setPickupTime(pickupTime);
            rider.setNumPassengers(numPassengersParam != null ? Integer.parseInt(numPassengersParam) : 0);
            rider.setRequireWheelchairVan(requireWheelchairVanParam != null && Boolean.parseBoolean(requireWheelchairVanParam));
            rider.setRequireChildSeat(requireChildSeat);
            rider.setPaymentType(paymentType);
            rider.setConfirmationRequest(confirmationRequest);
            rider.setBookReturn(bookReturnParam != null && Boolean.parseBoolean(bookReturnParam));
            rider.setPrice(priceParam != null ? Double.parseDouble(priceParam) : 0.0);

            // Save rider using DAO
            RiderDAO riderDAO = new RiderDAO();
            boolean success = riderDAO.saveRider(rider);

            // Debug log to confirm saving
            LOGGER.log(Level.INFO, "Rider saved status: " + success);

            // Redirect based on success or failure
            if (success) {
                LOGGER.log(Level.INFO, "Rider saved successfully. Redirecting to success.jsp");
                req.setAttribute("booking", rider); // Pass Rider object to JSP
                req.getRequestDispatcher("success.jsp").forward(req, resp);
            } else {
                LOGGER.log(Level.WARNING, "Failed to save rider. Redirecting to error.jsp");
                req.setAttribute("error", "Failed to save booking. Please try again.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing booking", e);
            req.setAttribute("error", "An error occurred while processing your request: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
