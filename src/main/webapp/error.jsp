<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Error</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Something went wrong</h2>
    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
    <a href="/patientList">Back to Patient List</a>
    <a href="/index.html">Home</a>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>