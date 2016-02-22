<%--
  Created by IntelliJ IDEA.
  User: joway
  Date: 16/2/22
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p><a href="${pageContext.request.contextPath}/add">add</a></p>
<p><a href="${pageContext.request.contextPath}/get?id=7">get</a></p>
<p><a href="${pageContext.request.contextPath}/getAll">getAll</a></p>
<p><a href="${pageContext.request.contextPath}/delete?id=7">delete</a></p>
<p><a href="${pageContext.request.contextPath}/valid">valid</a></p>
<p><a href="${pageContext.request.contextPath}/deleteCache">deleteCache</a></p>
</body>
</html>
