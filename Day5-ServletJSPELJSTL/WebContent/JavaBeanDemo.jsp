<%@ page import="controller.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h1> Java Bean Action Tag Demo  </h1>
  <jsp:useBean id="TimeBean" class="controller.Customer" scope="session"/>
  <h2> <jsp:getProperty property="time" name="TimeBean"/>
  </h2>
  <h2> <jsp:getProperty property="customerName" name="TimeBean"/>
  </h2>
</body>
</html>