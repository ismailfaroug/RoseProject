<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rose Web Site</title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        html, body {
            font-family: Arial, sans-serif;
            height: 100%;
        }

        body {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            min-height: 100vh;
        }

        /* Navbar */
        nav {
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 100px; /* Consistent navbar height */
            padding: 0 20px;
            background-color: #ffffff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        nav .header-images {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        nav .header-images img {
            height: 70px; /* Adjusted for logo */
            width: auto;
        }

        nav .nav-photo img {
            height: 100px; /* Adjusted for header image */
            width: auto;
        }

        nav ul {
            list-style: none;
            display: flex;
            gap: 15px;
            align-items: center; /* Align links vertically with the images */
            height: 100%; /* Matches navbar height */
            margin: 0;
        }

        nav ul li a {
            text-decoration: none;
            color: #333;
            font-weight: 500;
            padding: 6px 10px;
            transition: background-color 0.3s ease;
        }

        nav ul li a:hover {
            background-color: #ff00bf;
            color: #fff;
            border-radius: 5px;
        }

        /* Hero Section */
        .hero {
            text-align: center;
            margin: 0;
            padding: 20px;
        }

        .hero h1 {
            font-size: 2.5rem;
            margin-bottom: 20px;
            color: #pick;
        }

        .hero .buttons {
            display: flex;
            justify-content: center;
            gap: 15px;
        }

        .hero button {
            padding: 10px 20px;
            background-color: #ff00bf;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1rem;
        }

        .hero button:hover {
            background-color: #e600a8;
            transform: translateY(-3px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

			@keyframes color-cycle {
            0% { color: green; }
            33% { color: goldenrod; }
            66% { color: red; }
            100% { color: green; }
        }

        /* Dynamic Photo Section */
        .body-photo-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            padding: 20px;
            text-align: center;
        }

        .body-photo {
            width: 100%;
            max-width: 300px;
            border-radius: 10px;
        }

        .flashing-text {
            font-size: 1.5rem;
            color: #ff00bf;
            animation: flash 1.5s infinite;
        }

        @keyframes flash {
            0%, 100% {
                opacity: 1;
            }
            50% {
                opacity: 0.5;
            }
        }

        /* Footer */
        footer {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            align-items: flex-start;
            padding: 20px;
            background-color: #333;
            color: white;
        }

        footer .column {
            flex: 1;
            margin: 0 20px;
        }

        footer .column h4 {
            margin-bottom: 15px;
        }

        footer .column ul {
            list-style: none;
        }

        footer .column ul li a {
            text-decoration: none;
            color: white;
            font-size: 0.9rem;
            display: block;
            margin: 5px 0;
        }

        footer .column ul li a:hover {
            text-decoration: underline;
        }

        footer .social-icons {
            display: flex;
            gap: 15px;
        }

        footer .social-icons a {
            color: purple;
            font-size: 1.5rem;
            transition: transform 0.3s ease;
        }

        footer .social-icons a:hover {
            transform: scale(1.2);
        }

        @media (max-width: 768px) {
            footer {
                flex-direction: column;
                align-items: center;
            }

            footer .column {
                margin-bottom: 20px;
                text-align: center;
            }
        }
    </style>
    <script>
        // Redirect to respective pages when buttons are clicked
        document.addEventListener('DOMContentLoaded', function () {
            document.getElementById('BookOnLineBtn').addEventListener('click', function () {
                window.location.href = 'BookOnLine.jsp';
            });

            document.getElementById('CallNowBtn').addEventListener('click', function () {
                window.location.href = 'callNow.jsp';
            });
        });
    </script>
</head>
<body>
    <!-- Navbar -->
    <nav>
        <div class="header-images">
            <img src="images/logo1.png" alt="Rose Logo">
        </div>
        <div class="nav-photo">
            <img src="images/Header1.jpg" alt="Header Image">
        </div>
        <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#about">About Us</a></li>
            <li><a href="#contact">Contact Us</a></li>
        </ul>
    </nav>

    <!-- Hero Section -->
    <div class="hero">
        <h1>Book today and get %10 off</h1>
        <div class="buttons">
            <button id="CallNowBtn">Call NOW</button>
            <button id="BookOnLineBtn">Book Online</button>
        </div>
    </div>

    <!-- Dynamic Photo Section -->
    <div class="body-photo-container">
        
        <img class="body-photo" src="images/Body6.jpg" alt="Body Photo 6">
        <img class="body-photo" src="images/Body7.jpg" alt="Body Photo 7">
        <img class="body-photo" src="images/Body8.jpg" alt="Body Photo 8">
        <img class="body-photo" src="images/Body9.jpg" alt="Body Photo 9">
        
        
    </div>
    <div>
    <img class="body-photo" src="images/Body1.jpg" alt="Body Photo 1">
        <img class="body-photo" src="images/Body2.jpg" alt="Body Photo 2">
        <img class="body-photo" src="images/Body10.jpg" alt="Body Photo 10">
        <img class="body-photo" src="images/Body5.jpg" alt="Body Photo 5">
        
    </div>
    

    <!-- Footer -->
    <footer>
        <div class="column">
            <h4>Driver</h4>
            <ul>
                <li><a href="#">Become a Driver</a></li>
                <li><a href="#">Earnings</a></li>
                <li><a href="#">Insurance</a></li>
                <li><a href="#">Track Your Rides</a></li>
                <li><a href="#">Driver Dashboard</a></li>
                <li><a href="#">Performance Metrics</a></li>
            </ul>
        </div>
        <div class="column">
            <h4>Rider</h4>
            <ul>
                <li><a href="#">LOG IN</a></li>
                <li><a href="#">Child Seat</a></li>
                <li><a href="#">Safety</a></li>
                <li><a href="#">Track Your Ride</a></li>
                <li><a href="#">Upcoming Trips</a></li>
                <li><a href="#">Past Rides</a></li>
            </ul>
        </div>
        <div class="column">
            <h4>Social Media</h4>
            <div class="social-icons">
                <a href="https://facebook.com" target="_blank" rel="noopener noreferrer"><i class="fab fa-facebook"></i></a>
                <a href="https://twitter.com" target="_blank" rel="noopener noreferrer"><i class="fab fa-twitter"></i></a>
                <a href="https://instagram.com" target="_blank" rel="noopener noreferrer"><i class="fab fa-instagram"></i></a>
                <a href="https://linkedin.com" target="_blank" rel="noopener noreferrer"><i class="fab fa-linkedin"></i></a>
            </div>
        </div>
    </footer>
</body>
</html>
