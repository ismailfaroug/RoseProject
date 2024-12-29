package com.bona.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmBookingServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Retrieve rider details and price calculation from the request
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    double tripPrice = Double.parseDouble(request.getParameter("tripPrice"));

	    // Set attributes for confirmation page
	    request.setAttribute("firstName", firstName);
	    request.setAttribute("lastName", lastName);
	    request.setAttribute("tripPrice", tripPrice);
	    request.getRequestDispatcher("confirm.jsp").forward(request, response);
	}


}
