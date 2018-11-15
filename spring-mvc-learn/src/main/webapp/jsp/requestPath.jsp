<%@ page language="Java" contentType="text/html; charset=UTF-8" import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    contextPath   ---->${contextPath}<br/>
    servletPath   ---->${servletPath}<br/>
    method        ---->${method}<br/>
    pathTranslated---->${pathTranslated}<br/>
    serverName    ---->${serverName}<br/>
    requestURI    ---->${requestURI}<br/>
    requestURL    ---->${requestURL}<br/>
    contentType   ---->${contentType}<br/>
    pathInfo      ---->${pathInfo}<br/>
    localAddr     ---->${localAddr}<br/>
    localName     ---->${localName}<br/>
    localPort     ---->${localPort}<br/>
    scheme        ---->${scheme}<br/>
    remoteAddr    ---->${remoteAddr}<br/>
    remoteHost    ---->${remoteHost}<br/>
</body>
</html>
