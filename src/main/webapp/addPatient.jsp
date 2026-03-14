<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Add Patient</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Add New Patient</h2>
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
    <p style="color: red;"><%= errorMessage %></p>
    <% } %>

    <form method="POST" action="/addPatient">
        <table border="1">
            <tr><td>First Name *</td><td><input type="text" name="FIRST" /></td></tr>
            <tr><td>Last Name *</td><td><input type="text" name="LAST" /></td></tr>
            <tr><td>Birth Date *</td><td><input type="date" name="BIRTHDATE" /></td></tr>
            <tr><td>Gender *</td>
                <td>
                    <select name="GENDER">
                        <option value="">-- Select --</option>
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                    </select>
                </td>
            </tr>
           <tr><td>Prefix</td>
               <td>
                   <select name="PREFIX">
                       <option value="">-- Select --</option>
                       <option value="Mr.">Mr.</option>
                       <option value="Mrs.">Mrs.</option>
                       <option value="Ms.">Ms.</option>
                       <option value="Dr.">Dr.</option>
                   </select>
               </td>
           </tr>
            <tr><td>Suffix</td><td><input type="text" name="SUFFIX"/></td></tr>
            <tr><td>Maiden Name</td><td><input type="text" name="MAIDEN"/></td></tr>
            <tr><td>SSN</td><td><input type="text" name="SSN"/></td></tr>
            <tr><td>Drivers License</td><td><input type="text" name="DRIVERS"/></td></tr>
            <tr><td>Passport</td><td><input type="text" name="PASSPORT"/></td></tr>
            <tr><td>Marital Status</td>
                <td>
                    <select name="MARITAL">
                        <option value="">-- Select --</option>
                        <option value="S">Single</option>
                        <option value="M">Married</option>
                    </select>
                </td>
            </tr>
            <tr><td>Race</td><td><input type="text" name="RACE"/></td></tr>
            <tr><td>Ethnicity</td><td><input type="text" name="ETHNICITY"/></td></tr>
            <tr><td>Birth Place</td><td><input type="text" name="BIRTHPLACE"/></td></tr>
            <tr><td>Address</td><td><input type="text" name="ADDRESS"/></td></tr>
            <tr><td>City</td><td><input type="text" name="CITY"/></td></tr>
            <tr><td>State</td><td><input type="text" name="STATE"/></td></tr>
            <tr><td>ZIP</td><td><input type="text" name="ZIP"/></td></tr>
            <tr><td>Death Date</td><td><input type="date" name="DEATHDATE"/></td></tr>
        </table>
        <br>
        <input type="submit" value="Add Patient"/>
    </form>
    <br>
    <a href="/patientList">Back to Patient List</a>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>