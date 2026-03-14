<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Operations</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Patient Data Operations</h2>

    <h3>Oldest Alive Patient</h3>
    <%
        Map<String,String> oldest = (Map<String,String>) request.getAttribute("oldestPatient");
        if (oldest != null) {
    %>
    <p><a href="/patientDetail?id=<%= oldest.get("ID") %>"><%= oldest.get("NAME") %></a>
       — Age: <%= oldest.get("AGE") %>, Born: <%= oldest.get("BIRTHDATE") %></p>
    <% } %>

    <h3>Alive vs Deceased</h3>
    <table border="1">
        <tr><th>Status</th><th>Count</th></tr>
        <%
            Map<String,Integer> aliveDeceased = (Map<String,Integer>) request.getAttribute("aliveDeceased");
            if (aliveDeceased != null) {
                for (Map.Entry<String,Integer> entry : aliveDeceased.entrySet()) {
        %>
        <tr><td><%= entry.getKey() %></td><td><%= entry.getValue() %></td></tr>
        <%
                }
            }
        %>
    </table>

    <h3>Count by City</h3>
    <table border="1">
        <tr><th>City</th><th>Count</th></tr>
        <%
            Map<String,Integer> cityCount = (Map<String,Integer>) request.getAttribute("cityCount");
            if (cityCount != null) {
                for (Map.Entry<String,Integer> entry : cityCount.entrySet()) {
        %>
        <tr><td><%= entry.getKey() %></td><td><%= entry.getValue() %></td></tr>
        <%
                }
            }
        %>
    </table>

    <br>
    <a href="/index.html">Home</a>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>