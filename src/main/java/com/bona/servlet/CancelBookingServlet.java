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
 * Servlet for handling booking cancellations.
 */
public class CancelBookingServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CancelBookingServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LOGGER.log(Level.INFO, "CancelBookingServlet execution started.");

            // Retrieve booking ID from the request
            String bookingIdParam = req.getParameter("id");

            if (bookingIdParam == null || bookingIdParam.trim().isEmpty()) {
                LOGGER.log(Level.WARNING, "Booking ID is missing or empty.");
                req.setAttribute("error", "Booking ID is required for cancellation.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
                return;
            }

            int bookingId = Integer.parseInt(bookingIdParam);
            LOGGER.log(Level.INFO, "Attempting to cancel booking with ID: {0}", bookingId);

            // Call DAO to delete the booking
            RiderDAO riderDAO = new RiderDAO();
            boolean isDeleted = riderDAO.deleteRider(bookingId);

            if (isDeleted) {
                LOGGER.log(Level.INFO, "Booking successfully canceled for ID: {0}", bookingId);
                req.setAttribute("message", "Booking successfully canceled!");
                req.getRequestDispatcher("cancelSuccess.jsp").forward(req, resp);
            } else {
                LOGGER.log(Level.WARNING, "Failed to cancel booking for ID: {0}", bookingId);
                req.setAttribute("error", "Failed to cancel the booking. Please try again.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid booking ID format.", e);
            req.setAttribute("error", "Invalid booking ID. Please provide a valid ID.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during booking cancellation.", e);
            req.setAttribute("error", "An unexpected error occurred. Please try again later.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
