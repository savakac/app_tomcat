<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>

<m:Base titulek="Zapisky">
	<h1>Zapisnik</h1>

	<form action="<c:url value='/pridat' />" method="POST">
		<label for="nadpis">Nadpis</label>
		<input type="text" name="nadpis">
		<br>
		<textarea name="obsah" rows="5" cols="40"></textarea>
		<br>
		<input value="Pridat" type="submit" />
	</form>
	
	<c:if test="${param.upozorneni}">
		<span>Musite vyplnit obe pole.<br></span>
	</c:if>
	
	<c:choose>
		<c:when test="${not empty zapisky}">
			<c:forEach items="${zapisky}" var="zapisek">
				<div class="zapisek">
				
					<div class="nadpis"><c:out value="${zapisek.nadpis}"></c:out></div>
				
					<div class="tlacitka">
						<form method="GET" action="<c:url value='/upravit' />">
							<input type="hidden" value="${zapisek.id}" name="id" />
							<input type="submit" value="Upravit" />
						</form>
						<form method="POST" action="<c:url value='/smazat' />">
							<input type="hidden" value="${zapisek.id}" name="id" />
							<input type="submit" value="Smazat" />
						</form>
					</div>
					
					<div class="obsah"><c:out value="${zapisek.obsah}" /> </div>
		
				</div>
			</c:forEach>
		</c:when>
		
		<c:otherwise>
			<span>Doteraz nebol pridany ziadny zapisok.</span>
		</c:otherwise>
	</c:choose>
	
	<form method="GET" action="<c:url value='/logout' />">
		<input type="submit" value="Logout" />
	</form>
</m:Base>