<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Edit Patient</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Edit Patient</h2>
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
    <p style="color: red;"><%= errorMessage %></p>
    <%
        }
        Map<String,String> patient = (Map<String,String>) request.getAttribute("patient");
        if (patient != null) {
    %>

    <form method="POST" action="/editPatient">
        <input type="hidden" name="ID" value="<%= patient.get("ID") %>"/>
        <table border="1">
            <tr><td>ID</td><td><%= patient.get("ID") %></td></tr>
            <tr><td>First Name *</td><td><input type="text" name="FIRST" value="<%= patient.get("FIRST") %>" /></td></tr>
            <tr><td>Last Name *</td><td><input type="text" name="LAST" value="<%= patient.get("LAST") %>" /></td></tr>
            <tr><td>Birth Date *</td><td><input type="date" name="BIRTHDATE" value="<%= patient.get("BIRTHDATE") %>" /></td></tr>
            <tr><td>Gender *</td>
                <td>
                    <select name="GENDER">
                        <option value="">-- Select --</option>
                        <option value="M" <%= "M".equals(patient.get("GENDER")) ? "selected" : "" %>>Male</option>
                        <option value="F" <%= "F".equals(patient.get("GENDER")) ? "selected" : "" %>>Female</option>
                    </select>
                </td>
            </tr>
            <tr><td>Prefix</td>
                <td>
                    <select name="PREFIX">
                        <option value="">-- Select --</option>
                        <option value="Mr." <%= "Mr.".equals(patient.get("PREFIX")) ? "selected" : "" %>>Mr.</option>
                        <option value="Mrs." <%= "Mrs.".equals(patient.get("PREFIX")) ? "selected" : "" %>>Mrs.</option>
                        <option value="Ms." <%= "Ms.".equals(patient.get("PREFIX")) ? "selected" : "" %>>Ms.</option>
                        <option value="Dr." <%= "Dr.".equals(patient.get("PREFIX")) ? "selected" : "" %>>Dr.</option>
                    </select>
                </td>
            </tr>
            <tr><td>Suffix</td><td><input type="text" name="SUFFIX" value="<%= patient.get("SUFFIX") %>"/></td></tr>
            <tr><td>Maiden Name</td><td><input type="text" name="MAIDEN" value="<%= patient.get("MAIDEN") %>"/></td></tr>
            <tr><td>SSN</td><td><input type="text" name="SSN" value="<%= patient.get("SSN") %>"/></td></tr>
            <tr><td>Drivers License</td><td><input type="text" name="DRIVERS" value="<%= patient.get("DRIVERS") %>"/></td></tr>
            <tr><td>Passport</td><td><input type="text" name="PASSPORT" value="<%= patient.get("PASSPORT") %>"/></td></tr>
            <tr><td>Marital Status</td>
                <td>
                    <select name="MARITAL">
                        <option value="">-- Select --</option>
                        <option value="S" <%= "S".equals(patient.get("MARITAL")) ? "selected" : "" %>>Single</option>
                        <option value="M" <%= "M".equals(patient.get("MARITAL")) ? "selected" : "" %>>Married</option>
                    </select>
                </td>
            </tr>
            <tr><td>Race</td><td><input type="text" name="RACE" value="<%= patient.get("RACE") %>"/></td></tr>
            <tr><td>Ethnicity</td><td><input type="text" name="ETHNICITY" value="<%= patient.get("ETHNICITY") %>"/></td></tr>
            <tr><td>Birth Place</td><td><input type="text" name="BIRTHPLACE" value="<%= patient.get("BIRTHPLACE") %>"/></td></tr>
            <tr><td>Address</td><td><input type="text" name="ADDRESS" value="<%= patient.get("ADDRESS") %>"/></td></tr>
            <tr><td>City</td><td><input type="text" name="CITY" value="<%= patient.get("CITY") %>"/></td></tr>
            <tr><td>State</td><td><input type="text" name="STATE" value="<%= patient.get("STATE") %>"/></td></tr>
            <tr><td>ZIP</td><td><input type="text" name="ZIP" value="<%= patient.get("ZIP") %>"/></td></tr>
            <tr><td>Death Date</td><td><input type="date" name="DEATHDATE" value="<%= patient.get("DEATHDATE") %>"/></td></tr>
        </table>
        <br>
        <input type="submit" value="Save Changes"/>
    </form>
    <% } %>
    <br>
    <a href="/patientDetail?id=<%= patient.get("ID") %>">Cancel</a>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>