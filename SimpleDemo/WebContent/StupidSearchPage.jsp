<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<body>
<form action="/demo/stupid" method=post>
<input type="text" name="name" value="${param['name']}"
			size=15 maxlength=20> <br/>
			<input type="text" name="description" value="${param['name']}"
			size=15 maxlength=20> <br/>
<input type="submit" value="Submit"> 			
</form>
<c:if test="${! empty  stupidlist}" >
<table class="borderAll">
	<tr>
		<th><fmt:message key="label.hero.name" /></th>
		<th><fmt:message key="label.hero.description" /></th>
		<th><fmt:message key="label.hero.play" /></th>
	</tr>
	<c:forEach var="hero" items="${stupidlist}" varStatus="status">
		<tr class="${status.index%2==0?'even':'odd'}">
			<td class="nowrap">${hero.name}</td>
			<td class="nowrap">${hero.description}</td>
			<td class="nowrap">${hero.play}</td>
		</tr>
	</c:forEach>
</table>
</c:if>
</body>
</html>