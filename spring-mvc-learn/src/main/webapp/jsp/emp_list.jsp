<%@ page language="Java" contentType="text/html; charset=UTF-8" import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <c:forEach var="emp" items="${emps}">
            <tr>
                <td>${emp.calCode}</td>
                <td>${emp.riskCode}</td>
                <td>${emp.accType}</td>
                <td>${emp.state}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
