<%@ page import="model.ShoppingCartItem" %>
<%@ page import="model.ShoppingCart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping cart</title>
</head>
<body>
Total count = ${shoppingCart.totalCount}<br>
Products:<br>
<% for (ShoppingCartItem сartItem : ((ShoppingCart) request.getAttribute("shoppingCart")).getItems()) {%>
<%=сartItem%><br>
<% } %><br>

</body>
</html>
