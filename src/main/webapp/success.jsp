<%@ page import="com.bona.entity.Rider" %>
<%
    Rider booking = (Rider) request.getAttribute("booking");
    Double originalPrice = (Double) request.getAttribute("originalPrice");
    Double discount = (Double) request.getAttribute("discount");
    Double finalPrice = (Double) request.getAttribute("finalPrice");

    if (booking == null || originalPrice == null || discount == null || finalPrice == null) {
%>
    <h1>Error</h1>
    <p>Booking details not available.</p>
<%
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Successful</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f3f4f6;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            padding: 20px;
        }

        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 800px;
        }

        h1 {
            text-align: center;
            color: #4CAF50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            text-align: left;
            padding: 10px;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f3f4f6;
            color: #333;
        }

        .actions {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .actions form {
            margin: 0;
        }

        .actions button {
            padding: 10px 20px;
            margin: 5px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.3s ease;
        }

        .pay-btn {
            background-color: #4CAF50;
            color: white;
        }

        .pay-btn:hover {
            background-color: #45a049;
        }

        .cancel-btn {
            background-color: #f44336;
            color: white;
        }

        .cancel-btn:hover {
            background-color: #e53935;
        }

        .update-btn {
            background-color: #2196F3;
            color: white;
        }

        .update-btn:hover {
            background-color: #1976D2;
        }

        .home-btn {
            background-color: #FF9800;
            color: white;
        }

        .home-btn:hover {
            background-color: #FB8C00;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Booking Successful!</h1>
        <p>Thank you for booking with us! Below are your booking details:</p>

        <table>
            <tr>
                <th>First Name</th>
                <td><%= booking.getFirstName() %></td>
            </tr>
            <tr>
                <th>Last Name</th>
                <td><%= booking.getLastName() %></td>
            </tr>
            <tr>
                <th>Pickup Location</th>
                <td><%= booking.getPickupLocation() %></td>
            </tr>
            <tr>
                <th>Drop-off Location</th>
                <td><%= booking.getDropOffLocation() %></td>
            </tr>
            <tr>
                <th>Pickup Date</th>
                <td><%= booking.getPickupDate() %></td>
            </tr>
            <tr>
                <th>Pickup Time</th>
                <td><%= booking.getPickupTime() %></td>
            </tr>
            <tr>
                <th>Original Price</th>
                <td>$<%= originalPrice %></td>
            </tr>
            <tr>
                <th>Discount</th>
                <td>$<%= discount %></td>
            </tr>
            <tr>
                <th>Final Price</th>
                <td>$<%= finalPrice %></td>
            </tr>
        </table>

        <div class="actions">
            <form action="paymentAPI" method="POST">
                <input type="hidden" name="bookingId" value="<%= booking.getId() %>">
                <input type="hidden" name="amount" value="<%= finalPrice %>">
                <button type="submit" class="pay-btn">Make Payment</button>
            </form>

            <form action="CancelBooking" method="POST">
                <input type="hidden" name="id" value="<%= booking.getId() %>">
                <button type="submit" class="cancel-btn">Cancel Booking</button>
            </form>

            <form action="BookOnLine.jsp" method="GET">
                <button type="submit" class="update-btn">Update</button>
            </form>

            <form action="index.jsp" method="GET">
                <button type="submit" class="home-btn">Go Back to Home</button>
            </form>
        </div>
    </div>
</body>
</html>
