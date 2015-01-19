<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>

<m:Base titulek="Zapisky">
	<h1>Zapisnik - prihlasenie</h1>
	
	<form method="POST" action="j_security_check">
		<input type="hidden" name="id" value="${zapisek.id}" />
		<label for="jmeno">Prihlasovacie meno: </label><br>
		<input type="text" name="j_username" id="jmeno" />
		<br>
		<label for="heslo">Heslo: </label><br>
		<input type="password" name="j_password" id="heslo" />
		<br>
		<input value="Prihlasit" type="submit" />
	</form>
	
	<c:if test="${param.upozorneni}">
		<span>Zadane udaje niesu platne.</span>
	</c:if>
</m:Base>