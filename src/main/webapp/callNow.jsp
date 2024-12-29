<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Call Now Buttons</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f3f4f6;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
            padding: 20px;
        }

        .call-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;  
            width: 100%;
            max-width: 500px;
        }

        .call-container h1 {
            font-size: 24px;
            color: #333;
            margin-bottom: 20px;
        }

        .call-container p {
            font-size: 18px;
            color: #555;
            margin-bottom: 10px;
        }

        .call-container .button-group {
            display: flex;
            flex-direction: column;
            gap: 15px;
            margin-top: 20px;
        }

        .call-container .button-group a {
            text-decoration: none;
            padding: 15px;
            border-radius: 5px;
            font-size: 18px;
            font-weight: bold;
            color: white;
            background-color: #ff00bf;
            transition: background-color 0.3s ease, transform 0.3s ease;
            text-align: center;
        }

        .call-container .button-group a:hover {
            background-color: #e600a8;
            transform: scale(1.05);
        }

        @media (max-width: 600px) {
            .call-container h1 {
                font-size: 20px;
            }

            .call-container .button-group a {
                font-size: 16px;
                padding: 12px;
            }
        }
    </style>
</head>
<body>
    <div class="call-container">
        <h1>Contact Your Local Area</h1>
        <p>Select your region to place a call:</p>
        <div class="button-group">
            <a href="tel:8176666321" title="Call Dallas Area">Call Dallas Area</a>
            <a href="tel:8175036237" title="Call Fort Worth Area">Call Fort Worth Area</a>
        </div>
    </div>
</body>
</html>


