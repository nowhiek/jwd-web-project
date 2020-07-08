<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="${pageContext.request.contextPath}/resources/css/sign_in.css" rel="stylesheet">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
				
		<fmt:setLocale value="${sessionScope.local}"/>
		<fmt:setBundle basename="locale/local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.locbutton.registration" var="registration_buton" />
		<fmt:message bundle="${loc}" key="local.loclink.main" var="main_link" />
		<fmt:message bundle="${loc}" key="local.loclabel.login" var="login_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.password" var="password_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.repeat_password" var="repeat_password_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.email" var="email_label" />
		<fmt:message bundle="${loc}" key="local.loch1.registration" var="registration_h1" />
		<fmt:message bundle="${loc}" key="local.message.invalid_login" var="invalid_login_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_password" var="invalid_password_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_email" var="invalid_email_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_repeat_password" var="invalid_repeat_password_message" />
		<fmt:message bundle="${loc}" key="local.message.user_already_exist" var="user_already_exist_message" />
		
	</head>
	<body class="text-center">		
		<div class="form-signin">	
			<c:if test="${not empty user_already_exist}">
				<div class="alert alert-warning" role="alert">
					<c:out value="${user_already_exist_message}"></c:out>
				</div>
			</c:if>	
			<c:if test="${not empty invalid_repeat_password}">
				<div class="alert alert-warning" role="alert">
					<c:out value="${invalid_repeat_password_message}"></c:out>
				</div>
			</c:if>	
			<c:if test="${not empty invalid_login}">
				<div class="alert alert-warning" role="alert">
					<c:out value="${invalid_login_message}"></c:out>
				</div>
			</c:if>	
			<c:if test="${not empty invalid_password}">
				<div class="alert alert-warning" role="alert">
					<c:out value="${invalid_password_message}"></c:out>
				</div>
			</c:if>	
			<c:if test="${not empty invalid_email}">
				<div class="alert alert-warning" role="alert">
					<c:out value="${invalid_email_message}"></c:out>
				</div>
			</c:if>	
			<h1 class="h3 mb-3 font-weight-normal">${registration_h1}</h1>
			<form action="${pageContext.request.contextPath}/Controller" method="POST">
				<label for="inputLogin" class="sr-only">${login_label}:</label>
				<input class="form-control" id="inputLogin" placeholder="${login_label}" name="login"/>
				
				<label for="inputPassword" class="sr-only">${password_label}:</label>
				<input type="password" class="form-control" id="inputPassword" placeholder="${password_label}" name="password"/>
			
				<label for="inputRepeatPassword" class="sr-only">${repeat_password_label}:</label>
				<input type="password" class="form-control" id="inputRepeatPassword" placeholder="${repeat_password_label}" name="repeat_password"/>
			
				<label for="inputEmail" class="sr-only">${email_label}:</label>
				<input type="email" class="form-control" id="inputEmail" placeholder="${email_label}" name="email"/>
					
				<input type="hidden" name="command" value="registr">
				<button class="btn btn-lg btn-primary btn-block" type="submit">${registration_buton}</button>
				<a href="${pageContext.request.contextPath}/index.jsp">${main_link}</a>
			</form>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>