<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h1>THIS PAGE SHOWS VARIOUS SCOPE DATA</h1>
 <h2> ${requestScope.rdata} </h2>
 <h2> ${sessionScope.sdata} </h2>
 <h2> ${applicationScope.adata} </h2>
 
 <a href="/Day5-ServletJSPELJSTL/servlet2"> Link2 </a>
</body>
</html>