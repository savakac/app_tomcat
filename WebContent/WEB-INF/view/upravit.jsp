<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>

<m:Base titulek="Zapisky">
	<h1>Zapisnik</h1>

	<form action="<c:url value='/ulozitupravy' />" method="POST">
		<input type="hidden" name="id" value="${zapisek.id}" />
		<label for="nadpis">Nadpis</label>
		<input type="text" name="nadpis" value="<c:out value='${zapisek.nadpis}' />" />
		<br>
		<textarea name="obsah" rows="5" cols="40"><c:out value='${zapisek.obsah}' /></textarea>
		<br>
		<input value="Upravit" type="submit" />
	</form>
	
	<c:if test="${param.upozorneni}">
		<span>Musite vyplnit obe polia.</span>
	</c:if>
</m:Base>