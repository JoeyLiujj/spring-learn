<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="form" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
    <spring:bind path="dataBinderTest.phoneNumber">${status.value}</spring:bind>
    <form:form commandName="dataBinderTest">
        <form:input path="phoneNumber" />
    </form:form>

    <form:errors />
</body>
</html>
