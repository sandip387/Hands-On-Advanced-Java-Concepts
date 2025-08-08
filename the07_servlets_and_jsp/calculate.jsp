<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Simple Interest Result</title>
</head>
<body>
    <h2>Calculation Result</h2>

    <%-- This is a Scriptlet tag. Java logic goes here. --%>
    <%
        // JSP provides "implicit objects" like 'request'. We don't need to declare it.
        String principalStr = request.getParameter("principal");
        String timeStr = request.getParameter("time");
        String rateStr = request.getParameter("rate");

        // We use a try-catch to handle bad input, just like in a servlet.
        try {
            double principal = Double.parseDouble(principalStr);
            double time = Double.parseDouble(timeStr);
            double rate = Double.parseDouble(rateStr);

            double simpleInterest = (principal * time * rate) / 100;
    %>

    <%-- We are now back in normal HTML mode --%>
    <table border="1" cellpadding="5">
        <tr>
            <td>Principal</td>
            <%-- This is an Expression tag. It's a shortcut for out.println() --%>
            <td><%= principal %></td>
        </tr>
        <tr>
            <td>Time (years)</td>
            <td><%= time %></td>
        </tr>
        <tr>
            <td>Rate (%)</td>
            <td><%= rate %></td>
        </tr>
        <tr>
            <td><strong>Simple Interest</strong></td>
            <td><strong><%= simpleInterest %></strong></td>
        </tr>
    </table>

    <%-- Back to the scriptlet to close the try block --%>
    <% 
        } catch (NumberFormatException e) {
            // The 'out' object is also an implicit object in JSP
            out.println("<p style='color:red'>Error: Invalid input format!</p>");
        }
    %>

    <br>
    <a href="input.html">Calculate Again</a>
</body>
</html>