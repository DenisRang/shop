<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="java-classes" uri="/WEB-INF/tags.tld" %>

<html>
<head>
    <title>Different tags</title>
</head>
<body>
<java-classes:forEach var="item">
    ${item}<br>
</java-classes:forEach>
<br>
<java-classes:switch value="s1">
    <java-classes:case value="s0">s0</java-classes:case>
    <java-classes:case value="s1">s1</java-classes:case>
    <java-classes:case value="s2">s2</java-classes:case>
    <java-classes:default>default</java-classes:default>
</java-classes:switch>

<java-classes:attr condition="${3>1}"/>

</body>
</html>
