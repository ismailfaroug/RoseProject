<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Online</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f9f9f9;
        }

        form {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        .form-container {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
        }

        .form-container > div {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="date"],
        input[type="time"],
        select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="radio"] {
            margin-right: 10px;
        }

        .radio-group {
            display: flex;
            align-items: center;
        }

        button {
            display: inline-block;
            background-color: #ff00bf;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #e600a8;
        }

        @media (max-width: 768px) {
            .form-container {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
    <form action="BookOnLine" method="post">
        <h2 style="text-align: center;">Book Your Ride</h2>
        
        <div class="form-container">
            <div>
                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" required>
            </div>
            <div>
                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" required>
            </div>
            <div>
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" maxlength="15" required>
            </div>
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div>
                <label for="pickupLocation">Pickup Location:</label>
                <input type="text" id="pickupLocation" name="pickupLocation" required>
            </div>
            <div>
                <label for="dropOffLocation">Drop Off Location:</label>
                <input type="text" id="dropOffLocation" name="dropOffLocation" required>
            </div>
            <div>
                <label for="pickupDate">Pickup Date:</label>
                <input type="date" id="pickupDate" name="pickupDate" required>
            </div>
            <div>
                <label for="pickupTime">Pickup Time:</label>
                <input type="time" id="pickupTime" name="pickupTime" required>
            </div>
            <div>
                <label for="numPassengers">No. of Passengers:</label>
                <select id="numPassengers" name="numPassengers" required>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
            <div>
                <label for="requireChildSeat">Require Child Seat:</label>
                <select id="requireChildSeat" name="requireChildSeat">
                    <option value="None">None</option>
                    <option value="Small">Small</option>
                    <option value="Medium">Medium</option>
                    <option value="Large">Large</option>
                </select>
            </div>
            <div>
                <label for="confirmationRequest">Confirmation Request:</label>
                <select id="confirmationRequest" name="confirmationRequest">
                    <option value="text">Text</option>
                    <option value="email">Email</option>
                </select>
            </div>
            <div>
                <label for="paymentType">Payment Type:</label>
                <select id="paymentType" name="paymentType">
                    <option value="cash">Cash</option>
                    <option value="credit">Credit</option>
                </select>
            </div>
            <div>
                <label>Book Your Return:</label>
                <div class="radio-group">
                    <input type="radio" id="bookReturnYes" name="bookReturn" value="true">
                    <label for="bookReturnYes">Yes</label>
                    <input type="radio" id="bookReturnNo" name="bookReturn" value="false" checked>
                    <label for="bookReturnNo">No</label>
                </div>
            </div>
            <div>
                <label>Require Wheelchair-Van:</label>
                <div class="radio-group">
                    <input type="radio" id="requireWheelchairYes" name="requireWheelchairVan" value="true">
                    <label for="requireWheelchairYes">Yes</label>
                    <input type="radio" id="requireWheelchairNo" name="requireWheelchairVan" value="false" checked>
                    <label for="requireWheelchairNo">No</label>
                </div>
            </div>
        </div>
        
        <div style="text-align: center; margin-top: 20px;">
            <button type="submit">Submit Booking</button>
        </div>
    </form>
</body>
</html>
