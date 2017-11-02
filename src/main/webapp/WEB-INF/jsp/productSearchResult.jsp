<%--
  Created by IntelliJ IDEA.
  User: Guillaume Gingembre
  Date: 25/09/2017
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="product" scope="request" class="com.app.entities.Product"/>

<html>
<head>
    <title>Search Results</title>
    <link href="../../resources/theme1/style1.css" type="text/css" rel="stylesheet">
</head>

<body>

<c:forEach var="product" items="${products}">
<div class="first" style="float: left; width:27%; margin:0.5%; box-shadow: 10px 10px 5px grey; background-color: #f1f1f1">
    <table>
        <div class="second" style="height:80px" >
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
            <tr>
                <td class="tb2" style="width:50%"> <a href="/productmanagement/product/${product.id}">Learn more about product</a> </td>
            </tr>
        </div>
    </table>
    <br>

</div>

</c:forEach>

<table>
    <tr>
        <td class="tb2" style="width:95%"> <a href="javascript:history.go(-1)">Edit your criteria</a> </td>
    </tr>
    <tr>
        <td class="tb2" style="width:95%"> <a href="/productmanagement/product/search">New Search</a> </td>
    </tr>
    <tr>
        <td class="tb2" style="width:95%"> <a href="/productmanagement/product/showAll">Show all products</a> </td>
    </tr>
</table>
<br>

</body>
</html>
