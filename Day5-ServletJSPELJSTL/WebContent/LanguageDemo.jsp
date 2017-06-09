<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<fmt:setBundle basename="Messages" />
	<h1>
		<fmt:message key="languagepage.hello">
			<fmt:param value="Jack" />
		</fmt:message>
	</h1>

	<h2>
		<fmt:message key="languagepage.greeting" />
		<br />
		<fmt:message key="languagepage.message">
			<fmt:param value="TAN RIYAN" />
			<fmt:param value="Tuesday" />
		</fmt:message>
		<br />
		<fmt:message key="languagepage.bye" />
		<br />
		<fmt:message key="languagepage.silentsa43" />
	</h2>
</body>
</html>