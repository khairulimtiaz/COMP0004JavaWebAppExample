<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Search Results</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Search Results</h2>
  <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
  %>
    <p style="color: red;"><%= errorMessage %></p>
  <%
    }
    List<Map<String,String>> patients = (List<Map<String,String>>) request.getAttribute("result");
    if (patients != null && !patients.isEmpty()) {
  %>
  <%
    String keyword = (String) request.getAttribute("searchKeyword");
    Integer count = (Integer) request.getAttribute("resultCount");
    if (keyword != null) {
  %>
    <p>Showing <%= count %> result(s) for: <strong><%= keyword %></strong></p>
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
    } else if (errorMessage == null) {
    %>
      <p>No patients found matching your search.</p>
    <%
    }
    %>
  </table>
  <br>
  <a href="/search.html">Search Again</a> | <a href="/index.html">Home</a>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>