<%@ page import="com.app.util.SessionInfo"%>
<%--
  Created by IntelliJ IDEA.
  User: Guillaume Gingembre
  Date: 15/09/2017
  Time: 23:01
  To change this template use File | Settings | File Templates.
  <jsp:useBean id = "project" class = "ua.goit.entity.Project" scope = "session" />
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="product" scope="request" class="com.app.entities.Product"/>

<html>
<head>
    <title>Product Details</title>
</head>
<body>
<p>Product Details:</p>
    <table>
            <tr>
                <td class="tb1" style="width:30%">Product Name :</td>
                <td class="tb1" style="width:60%">${product.name}</td>
            </tr>
            <tr>
                <td class="tb1" style="width:30%">Producer :</td>
                <td class="tb1" style="width:60%">${product.producer}</td>
            </tr>
            <tr>
                <td class="tb1" style="width:30%">Description :</td>
                <td class="tb1" style="width:60%">${product.description}</td>
            </tr>
            <tr>
                <td class="tb1" style="width:30%">Price :</td>
                <td class="tb1" style="width:60%">${product.price}</td>
            </tr>

    <c:if test="${fn:contains((SessionInfo.currentUserDetails().getAuthorities()), 'ADMIN')}">
        <tr>
            <td class="tb2" style="width:50%"> <a href="<c:url value="/product/${product.id}/delete" />">Delete</a> </td>
        </tr>

        <tr>
            <td class="tb2" style="width:50%"> <a href="<c:url value="/product/${product.id}/update" />">Edit</a> </td>
        </tr>
    </c:if>
        </table>

<table>
    <tr>
        <td class="tb2" style="width:95%"> <a href="/productmanagement/product/search">Search a product</a> </td>
    </tr>
</table>
<br>

</body>
</html>
