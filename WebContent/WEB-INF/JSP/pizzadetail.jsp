<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<vdab:head title="${pizza.naam}"/>
</head>
<body>
	<vdab:menu/>
	<c:if test="${not empty pizza}">
		<h1>${pizza.naam}</h1>
		<dl>
			<dt>Nummer</dt>
			<dd>${pizza.id}</dd>
			<dt>Naam</dt>
			<dd>${pizza.naam}</dd>
			<dt>Prijs</dt>
			<dd>${pizza.prijs}</dd>
			<dt>Pikant</dt>
			<dd>${pizza.pikant ? 'ja' : 'nee'}</dd>
		</dl>
	</c:if>
	<c:if test='${not empty fout}'>
		<div class='fout'>${fout}</div>
	</c:if>
</body>
</html>