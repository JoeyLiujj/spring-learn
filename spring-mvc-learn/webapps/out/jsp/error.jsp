<%@ page language="Java" contentType="text/html; charset=UTF-8" import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <form:form action="/smvc/bindError">
            <form:errors/>
            <input type="submit" value="提交">
        </form:form>
 这是一个错误页面
</body>
</html>
