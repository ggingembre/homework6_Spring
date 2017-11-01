<%--
  Created by IntelliJ IDEA.
  User: Guillaume Gingembre
  Date: 15/09/2017
  Time: 07:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="product" scope="request" class="com.app.entities.Product"/>

<html>
<head>
    <title>Products list</title>
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
                        <td class="tb1" style="width:30%">Project Producer :</td>
                        <td class="tb1" style="width:60%">${product.producer}</td>
                    </tr>
                    <tr>
                        <td class="tb1" style="width:30%">Description :</td>
                        <td class="tb1" style="width:60%">${project.projectDescription}</td>
                    </tr>
                    <tr>
                        <td class="tb1" style="width:30%">Price :</td>
                        <td class="tb1" style="width:60%">${product.price}</td>
                    </tr>
            </div>
        </table>

        <br>
        <br>

    </div>

    </c:forEach>

 </body>
 </html>
