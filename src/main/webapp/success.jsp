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
    </style>
</head>
<body>
    <div class="container">
        <h1>Booking Successful!</h1>
        <p>Thank you for booking with us! Below are your booking details:</p>

        <table>
            <tr>
                <th>First Name</th>
                <td>${firstName}</td>
            </tr>
            <tr>
                <th>Last Name</th>
                <td>${lastName}</td>
            </tr>
            <tr>
                <th>Pickup Location</th>
                <td>${pickupLocation}</td>
            </tr>
            <tr>
                <th>Drop-off Location</th>
                <td>${dropOffLocation}</td>
            </tr>
            <tr>
                <th>Pickup Date</th>
                <td>${pickupDate}</td>
            </tr>
            <tr>
                <th>Pickup Time</th>
                <td>${pickupTime}</td>
            </tr>
            <tr>
                <th>Price</th>
                <td>$${price}</td>
            </tr>
        </table>

        <div class="actions">
            <!-- Make Payment Button -->
            <form action="paymentAPI" method="POST" style="margin: 0;">
                <input type="hidden" name="bookingId" value="${id}">
                <input type="hidden" name="amount" value="${price}">
                <button type="submit" class="pay-btn">Make Payment</button>
            </form>

            <!-- Cancel Booking Button -->
            <form action="cancelBooking.jsp" method="POST" style="margin: 0;">
                <input type="hidden" name="id" value="${id}">
                <button type="submit" class="cancel-btn">Cancel Booking</button>
            </form>
        </div>

        <p style="text-align: center; margin-top: 20px;">
            <a href="home.jsp">Go to Home Page</a>
        </p>
    </div>
</body>
</html>
