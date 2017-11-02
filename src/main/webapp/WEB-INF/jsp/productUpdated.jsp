<%--
  Created by IntelliJ IDEA.
  User: Guillaume Gingembre
  Date: 28/09/2017
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="product" scope="request" class="com.app.entities.Product"/>

<html>
<head>
    <title>Product Updated</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>

<p>You have successfully updated the following product: </p>
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
<br/>

<a href="/productmanagement/product/add">Register another product</a>
<a href="/productmanagement/product/showAll">Show all products</a>
<a href="/productmanagement/product/search">Search product</a>

</body>
</html>
