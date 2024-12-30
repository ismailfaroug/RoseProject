package com.bona.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CallNowServlet - Handles dynamic phone number redirection for calls.
 */
public class CallNowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the phone number from the query parameter
        String phone = request.getParameter("phone");

        // Validate the phone number
        if (phone != null && phone.matches("\\d{10}")) { // Ensures the phone number is 10 digits
            // Generate a clickable link with the phone number
            response.setContentType("text/html");
            response.getWriter().write("<html><body>");
            response.getWriter().write("<h1>Call Redirection</h1>");
            response.getWriter().write("<p>Click the link below to initiate the call:</p>");
            response.getWriter().write("<a href=\"tel:" + phone + "\">Call " + phone + "</a>");
            response.getWriter().write("</body></html>");
        } else {
            // Display an error message if the phone number is invalid
            response.setContentType("text/html");
            response.getWriter().write("<html><body>");
            response.getWriter().write("<h1>Invalid Phone Number</h1>");
            response.getWriter().write("<p>Please provide a valid 10-digit phone number.</p>");
            response.getWriter().write("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests by delegating to the doGet method
        doGet(request, response);
    }
}
