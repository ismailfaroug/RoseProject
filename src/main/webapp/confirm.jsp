<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirm Your Booking</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
            color: #333;
        }

        h2 {
            text-align: center;
            color: #4CAF50;
            margin-bottom: 20px;
        }

        .container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .details {
            margin-bottom: 20px;
            line-height: 1.6;
        }

        .details p {
            margin: 10px 0;
        }

        .details p span {
            font-weight: bold;
            color: #333;
        }

        form {
            text-align: center;
        }

        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Confirm Your Booking</h2>
        <div class="details">
            <p><span>First Name:</span> ${firstName}</p>
            <p><span>Last Name:</span> ${lastName}</p>
            <p><span>Trip Price:</span> $${tripPrice}</p>
        </div>
        <form action="BookOnLineServlet" method="POST">
            <input type="hidden" name="tripPrice" value="${tripPrice}" />
            <button type="submit">Confirm and Submit</button>
        </form>
    </div>
</body>
</html>
