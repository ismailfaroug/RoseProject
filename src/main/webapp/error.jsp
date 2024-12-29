<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Occurred</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
            color: #333;
        }
        h1 {
            color: #dc3545; /* Bootstrap Danger Red */
        }
        .container {
            margin-top: 50px;
        }
        .alert {
            padding: 20px;
            border: 1px solid #f5c6cb;
            border-radius: 4px;
        }
        a {
            text-decoration: none;
            color: white;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container text-center">
        <div class="alert alert-danger" role="alert">
            <h1 class="display-4">Error Occurred!</h1>
            <p class="lead">Something went wrong. Please try again later or contact support if the problem persists.</p>

            <!-- Example of dynamic message with JSTL -->
            <c:if test="${not empty errorMessage}">
                <p class="text-danger">${errorMessage}</p>
            </c:if>

            <a href="home.jsp" class="btn btn-primary mt-3">Go to Home Page</a>
        </div>
    </div>

    <!-- Toast Notification -->
    <div class="toast-container position-fixed bottom-0 end-0 p-3">
        <div id="errorToast" class="toast bg-danger text-white" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header bg-danger text-white">
                <strong class="me-auto">Error</strong>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                An error occurred during the process. Please try again.
            </div>
        </div>
    </div>

    <!-- Bootstrap JavaScript for Toast -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        var toast = new bootstrap.Toast(document.getElementById('errorToast'));
        toast.show();
    </script>
</body>
</html>

