<%--
  Created by IntelliJ IDEA.
  User: Guillaume Gingembre
  Date: 24/09/2017
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id = "product" class = "com.app.entities.Product" scope = "request" />
<html>
<head>
    <title>Project Search</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <style>
        body { background-color: #eee;}
        #container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
        .green { font-weight: bold; color: green; }
        .message { margin-bottom: 10px; }
        label {width:250px; display:inline-block;}
        form {line-height: 160%; }
        .hide { display: none; }
    </style>
</head>
<body>
<div id="container">

    <h2>Please enter your search criteria</h2>
    <p>Leave blank fields that you do not want to use as criteria</p>

    <form:form method="POST" action="/productmanagement/product/search" modelAttribute="productSearch"> <%--commandName="command"--%>

        <label for="productName">Product Name: </label>
        <form:input path="name" id="productName" />
        <br/>

        <label for="productProducer">Product Producer: </label>
        <form:input path="producer" id="productProducer" />
        <br/>

        <label for="productDescription">Description: </label>
        <form:input path="description" name="productDescription" />
        <br/>

        <label for="productPrice">Product Price: </label>
        <form:input path="price" name="productPrice" />
        <br/>
        <br/>
        <input type="submit" value="Search" />

    </form:form>
</div>

</body>
</html>

