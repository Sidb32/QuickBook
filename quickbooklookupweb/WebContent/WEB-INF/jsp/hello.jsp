<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quick Book Page</title>
</head>
<body>
	<strong>QUICK BOOK INTEGRATION</strong>
 	<h2>${hello}</h2>
 	
<form action="http://localhost:8080/quickbooklookupweb/connectquickbook">
	<input type="Submit" value="Connect to QuickBook">
</form>

</body>
</html>