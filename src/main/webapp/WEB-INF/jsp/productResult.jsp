<%--
  Created by IntelliJ IDEA.
  User: Guillaume Gingembre
  Date: 21/09/2017
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="product" scope="request" class="com.app.entities.Product"/>
<html>
<head>
    <title>Product Details</title>
</head>
<body>

<p>You have successfully registered the following product: </p>
    <table>
            <tr>
                <td>Product Id :</td>
                <td>${product.id}</td>
            </tr>
            <tr>
                <td>Produce Name :</td>
                <td>${product.name}</td>
            </tr>
            <tr>
                <td>Producer :</td>
                <td>${product.producer}</td>
            </tr>
            <tr>
                <td>Description :</td>
                <td>${product.description}</td>
            </tr>
            <tr>
                <td>Price :</td>
                <td>${product.price}</td>
            </tr>
        </table>

<a href="/productmanagement/product/add">Register another product</a>

</body>
</html>
