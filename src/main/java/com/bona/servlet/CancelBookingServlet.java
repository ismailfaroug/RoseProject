package com.bona.servlet;

import com.bona.deo.RiderDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet to handle booking cancellation.
 */
public class CancelBookingServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CancelBookingServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Retrieve booking ID from request
            String bookingIdParam = req.getParameter("id");

            if (bookingIdParam == null || bookingIdParam.trim().isEmpty()) {
                throw new IllegalArgumentException("Booking ID is required for cancellation.");
            }

            int bookingId = Integer.parseInt(bookingIdParam);

            // Call DAO to delete the booking
            RiderDAO riderDAO = new RiderDAO();
            boolean isDeleted = riderDAO.deleteRider(bookingId);

            if (isDeleted) {
                // Redirect to cancellation success page
                req.setAttribute("message", "Booking successfully canceled!");
                req.getRequestDispatcher("cancelSuccess.jsp").forward(req, resp);
            } else {
                throw new Exception("Failed to delete the booking. Please try again.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during booking cancellation", e);
            req.setAttribute("error", "Unable to cancel the booking. Please try again later.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
