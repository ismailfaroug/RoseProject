<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cancellation Successful</title>
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
            text-align: center;
            max-width: 500px;
        }

        h1 {
            color: #f44336;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <% 
            String bookingId = request.getParameter("id");
            try {
                String url = "jdbc:mysql://localhost:3306/yourDatabase";
                String user = "yourUsername";
                String pass = "yourPassword";
                Connection conn = DriverManager.getConnection(url, user, pass);
                String sql = "DELETE FROM bookings WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, bookingId);
                int rows = ps.executeUpdate();
                conn.close();
        %>
            <h1>Cancellation Successful!</h1>
            <p>Your booking has been canceled.</p>
        <% 
            } catch (Exception e) { 
        %>
            <h1>Error!</h1>
            <p>Unable to cancel the booking. Please try again later.</p>
        <% } %>
        <a href="home.jsp">Go Back to Home</a>
    </div>
</body>
</html>