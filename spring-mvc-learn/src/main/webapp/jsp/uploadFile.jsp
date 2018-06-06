<%@ page language="Java" contentType="text/html; charset=UTF-8" import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/smvc/upload" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="file" />&nbsp;&nbsp;
        <input type="submit" value="上传文件"/>
    </form>
</body>
</html>
