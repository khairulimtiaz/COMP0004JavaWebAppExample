<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient List</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Patient List</h2>
  <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
  %>
    <p style="color: red;"><%= errorMessage %></p>
  <%
    }
  %>
  <table border="1">
    <tr>
      <th>Name</th>
      <th>Date of Birth</th>
      <th>Gender</th>
      <th>Status</th>
    </tr>
    <%
      List<Map<String,String>> patients = (List<Map<String,String>>) request.getAttribute("patientsSummary");
      if (patients != null) {
        for (Map<String,String> patient : patients) {
    %>
    <tr>
      <td><a href="/patientDetail?id=<%= patient.get("ID") %>"><%= patient.get("NAME") %></a></td>
      <td><%= patient.get("BIRTHDATE") %></td>
      <td><%= patient.get("GENDER") %></td>
      <td><%= patient.get("STATUS") %></td>
     </tr>
    <%
        }
      }
    %>
  </table>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>