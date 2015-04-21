<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<fmt:setBundle basename='resourceBundles.teksten' />
<!doctype html>
<html lang='nl'>
<head>
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name='title' value='Cookies' />
</c:import>
</head>
<body>
	<c:import url='/WEB-INF/JSP/menu.jsp' />
	<h1>
		<fmt:message key='cookieVoorbeeld' />
	</h1>
	<form method='post'>
		<label><fmt:message key='naam' /> <input name='naam'
			value='${naam}' autofocus required></label> <input type='submit'
			value="<fmt:message key='onthoudMe'/>">
	</form>
	<c:if test='${not empty naam}'>
		<div>
			<fmt:message key='naamLetters'>
				<fmt:param value='${naam.length()}' />
			</fmt:message>
		</div>
	</c:if>
</body>
</html>