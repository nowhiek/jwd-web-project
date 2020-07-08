<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="${pageContext.request.contextPath}/resources/css/form-validation.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet">
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="locale/local" var="loc" />

		<fmt:message bundle="${loc}" key="local.loclabel.old_password" var="old_password_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.new_password" var="new_password_label" />
		
		<fmt:message bundle="${loc}" key="local.loclink.back" var="back_link" />
		
		<fmt:message bundle="${loc}" key="local.locbutton.change_user_password" var="change_user_password_button" />
		
		<fmt:message bundle="${loc}" key="local.message.invalid_not_equals_password" var="invalid_not_equals_password_message" />
		<fmt:message bundle="${loc}" key="local.message.succes_change_password" var="succes_change_password_message" />
		<fmt:message bundle="${loc}" key="local.message.unsucces_change_password" var="unsucces_change_password_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_password" var="invalid_password_message" />
	</head>
	<body>		
		<jsp:include page="part/navbar.jsp"/>
		<jsp:include page="part/header.jsp"/>
		
		<div class="container">
			<div class="row">
				<div class="col-md-8 order-md-1">
					<h2 class="mb-3 mt-3">Смена пароля</h2>
					<c:if test="${not empty succes_change_password}">
					<div class="alert alert-warning" role="alert">
						<c:out value="${succes_change_password_message}"></c:out>
					</div>
					</c:if>	
					<c:if test="${not empty unsucces_change_password}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${unsucces_change_password_message}"></c:out>
						</div>
					</c:if>	
					<c:if test="${not empty invalid_not_equals_password}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${invalid_not_equals_password_message}"></c:out>
						</div>
					</c:if>	
					<c:if test="${not empty invalid_password}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${invalid_password_message}"></c:out>
						</div>
					</c:if>	
			
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="formGroupExampleInput">${old_password_label}:</label>
								<input type="password" class="form-control" id="formGroupExampleInput" value="${old_password}" name="old_password"/>
							</div>
						</div>
						<div class="row">
								<div class="col-md-6 mb-3">
							<label for="formGroupExampleInput">${new_password_label}:</label>
							<input type="password" class="form-control" id="formGroupExampleInput" value="${new_password}" name="new_password"/>
							</div>
						</div>
						
						<input type="hidden" name="command" value="change_user_password">
						<button class="btn btn-primary" type="submit">${change_user_password_button}</button>
					</form>
					
					<a href="${pageContext.request.contextPath}/jsp/user_page.jsp">${back_link}</a>
				</div>
			</div>
		</div>
	
			
		<jsp:include page="part/footer.jsp"/>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>