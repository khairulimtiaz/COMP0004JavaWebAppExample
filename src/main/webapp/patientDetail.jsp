<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Detail</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Patient Details</h2>
    <%
        Map<String,String> patient = (Map<String,String>) request.getAttribute("patient");
        if (patient != null) {
    %>
    <table border="1">
        <tr><th>Field</th><th>Value</th></tr>
        <tr><td>Name</td><td><%= patient.get("PREFIX") + " " + patient.get("FIRST") + " " + patient.get("LAST") %></td></tr>
        <tr><td>Date of Birth</td><td><%= patient.get("BIRTHDATE") %></td></tr>
        <tr><td>Gender</td><td><%= patient.get("GENDER") %></td></tr>
        <tr><td>Address</td><td><%= patient.get("ADDRESS") + ", " + patient.get("CITY") + ", " + patient.get("STATE") %></td></tr>
        <tr><td>Birth Place</td><td><%= patient.get("BIRTHPLACE") %></td></tr>
        <tr><td>Marital Status</td><td><%= patient.get("MARITAL") %></td></tr>
        <tr><td>Race</td><td><%= patient.get("RACE") %></td></tr>
        <tr><td>Ethnicity</td><td><%= patient.get("ETHNICITY") %></td></tr>
        <tr><td>Death Date</td><td><%= patient.get("DEATHDATE") %></td></tr>
    </table>
    <%
        }
    %>
    <br>
    <a href="/patientList">Back to Patient List</a>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>