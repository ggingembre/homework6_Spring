<%--
  Created by IntelliJ IDEA.
  User: Guillaume Gingembre
  Date: 28/09/2017
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id = "product" class = "com.app.entities.Product" scope = "request" />

<html>
<head>
    <title>Project Update Form</title>
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

    <h2>Please update product</h2>

    <form:form method="POST" action="/productmanagement/product/updated" modelAttribute="product" commandName="command"> <%--  --%>

        <form:hidden path="id"/>

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

        <%--  <c:url value='/project/update/${project.projectId}' var='editProjectObjectURL'/>
          <a href="${editProjectObjectURL}"><input type="submit" value="Update"></a> --%>

          <input type="submit" value="Submit" />

    </form:form>
</div>

</body>
</html>

