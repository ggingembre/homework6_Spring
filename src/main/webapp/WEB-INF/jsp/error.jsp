<%--
  Created by IntelliJ IDEA.
  User: Guillaume Gingembre
  Date: 28/09/2017
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Error Page</title>
</head>
<body>

<h1>Error Page</h1>
<p>Application has encountered an error. Please right click and choose "view page source" or contact support</p>

<!--
    Failed URL: ${url}
    Exception:  ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste">    ${ste}
    </c:forEach>
  -->

</body>
</html>
