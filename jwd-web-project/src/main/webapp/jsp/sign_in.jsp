<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="${pageContext.request.contextPath}/resources/css/sign_in.css" rel="stylesheet">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="locale/local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.sign_in" var="sign_in_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.registration" var="registration_buton" />
		<fmt:message bundle="${loc}" key="local.loclabel.login" var="login_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.password" var="password_label" />
		<fmt:message bundle="${loc}" key="local.loch1.authorization" var="authorization_h1" />
		
		<fmt:message bundle="${loc}" key="local.message.invalid_not_equals_password" var="invalid_not_equals_password_message" />
		<fmt:message bundle="${loc}" key="local.empty_message.empty_login" var="empty_login_message" />
		<fmt:message bundle="${loc}" key="local.empty_message.empty_password" var="empty_password_message" />
		<fmt:message bundle="${loc}" key="local.message.user_not_exist" var="user_not_exist_message" />
		<fmt:message bundle="${loc}" key="local.message.user_is_blocked" var="user_is_blocked_message" />
	</head>
	
	<body class="text-center">		
		<div class="form-signin">
			<c:if test="${not empty user_not_exist}">
				<div class="alert alert-warning" role="alert">
					<c:out value="${user_not_exist_message}"></c:out>
				</div>
			</c:if>
			
			<c:if test="${not empty empty_login}">
				<div class="alert alert-warning" role="alert">
					<c:out value="${empty_login_message}"></c:out>
				</div>
			</c:if>
			<c:if test="${not empty empty_password}">
				<div class="alert alert-warning" role="alert">
					<c:out value="${empty_password_message}"></c:out>
				</div>
			</c:if>
			
			<h1 class="h3 mb-3 font-weight-normal">${authorization_h1}</h1>
			<form action="${pageContext.request.contextPath}/Controller" method="POST">
				
				<label for="inputEmail" class="sr-only">${login_label}:</label>
				<input type="text" id="inputEmail" class="form-control" placeholder="${login_label}" name="login"/>
			
				<label for="inputPassword" class="sr-only">${password_label}:</label>
				<input type="password" id="inputPassword" class="form-control" placeholder="${password_label}" name="password"/>
				
				<input type="hidden" name="command" value="sign_in">
				<button class="btn btn-lg btn-primary btn-block" type="submit">${sign_in_button}</button>
				<a href="${pageContext.request.contextPath}/jsp/registr_page.jsp">${registration_buton}</a>
			</form>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>
