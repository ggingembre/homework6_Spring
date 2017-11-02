<%--
  Created by IntelliJ IDEA.
  User: Guillaume Gingembre
  Date: 21/09/2017
  Time: 09:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id = "product" class = "com.app.entities.Product" scope = "request" />

<html>
<head>
    <title>Product Registration</title>
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

    <h2>Please enter your product details</h2>

    <form:form method="POST" action="/productmanagement/product/add" modelAttribute="product"> <%--commandName="command"--%>

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
        <input type="submit" value="Submit" />

    </form:form>
</div>

</body>
</html>
