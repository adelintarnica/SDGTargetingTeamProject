<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Software developer GO - Targeting Team</title>
</head>
<body>
<h1>View Details</h1>
<h2>Your text: ${atext} </h2>

<c:forEach items="${aWordCountMap}" var="entry">
<h4>Keyword = "${entry.key}", counter = ${entry.value}</h4>
</c:forEach>

<h4>JSON format: ${ajson} </h4>

<form method="get" action="">
<input type="button" name="back" onclick="history.back()" value="Back"/><br>
</form>
</body>
</html>