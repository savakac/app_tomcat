<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="titulek" rtexprvalue="true" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content=Type" content="text/html; cahrset=UTF-8">
		<title>${titulek}</title>
		<link rel='stylesheet' href='<c:url value="/css/style.css"/>' />
	</head>
	<body>
		<h1>Zapisnik</h1>
		
		<jsp:doBody></jsp:doBody>
	</body>	
</html>
