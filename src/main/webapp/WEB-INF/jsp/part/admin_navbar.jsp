<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="resources/css/header.css" rel="stylesheet">
		<link href="resources/css/style.css" rel="stylesheet">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="locale/local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.nav_sign_in" var="nav_sign_in_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.nav_sign_up" var="nav_sign_up_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.sign_out" var="sign_out_button" />
		
		<fmt:message bundle="${loc}" key="local.locmenu.about" var="about_label" />
		<fmt:message bundle="${loc}" key="local.admin_label.list" var="list_label" />
		
		<fmt:message bundle="${loc}" key="local.loclink.admin" var="admin_link" />
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="index.jsp">University</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse" id="navbarsExample05">
				<ul class="navbar-nav mr-auto">		      
					<li class="nav-item">
				    	<a class="nav-link" href="#">${about_label}</a>
				    </li>          
				</ul>
				
				<c:set var="user" scope="session" value="${ROLE}"/>
				<div class='secondary-menu_container'>
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<input type="hidden" name="local" value="en" />
						<input type="hidden" name="command" value="change_locale" />
						<button class="btn btn-primary eng" type="submit"></button>
					</form>
					
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<input type	="hidden" name="local" value="ru" />
						<input type="hidden" name="command" value="change_locale" />
						<button class="btn btn-primary rus" type="submit"></button>
					</form>
				
					<c:choose>					     
					    <c:when test="${user == 'Admin'}">
							<form action="${pageContext.request.contextPath}/Controller" method="POST">
								<input type="hidden" name="command" value="show_admin_panel">
								<button class="btn btn-primary" type="submit">${admin_link}</button>
							</form>
							<form action="${pageContext.request.contextPath}/Controller" method="POST">
								<input type="hidden" name="command" value="sign_out">
								<button class="btn btn-primary" type="submit">${sign_out_button}</button>
							</form>
					    </c:when>
					    <c:otherwise>
					        <a href="${pageContext.request.contextPath}/jsp/registr_page.jsp" class="btn btn-primary">${nav_sign_up_button}</a>
							<a href="${pageContext.request.contextPath}/jsp/sign_in.jsp" class="btn btn-primary">${nav_sign_in_button}</a>
					    </c:otherwise>
					</c:choose>
				</div>
			</div>
		</nav>

		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>