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
		<fmt:message bundle="${loc}" key="local.loclabel.change_password" var="change_password_label" />
		
		<fmt:message bundle="${loc}" key="local.loclink.back" var="back_link" />
		
		<fmt:message bundle="${loc}" key="local.locbutton.change_user_password" var="change_user_password_button" />
		
		<fmt:message bundle="${loc}" key="local.message.invalid_not_equals_password" var="invalid_not_equals_password_message" />
		<fmt:message bundle="${loc}" key="local.message.success_change_password" var="success_change_password_message" />
		<fmt:message bundle="${loc}" key="local.message.unsuccess_change_password" var="unsuccess_change_password_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_password" var="invalid_password_message" />
	</head>
	<body>		
		<jsp:include page="part/navbar.jsp"/>
		<jsp:include page="part/header.jsp"/>
		
		<div class="container">
			<div class="row">
				<div class="col-md-8 order-md-1">
					<h2 class="mb-3 mt-3">${change_password_label}</h2>
					<c:if test="${not empty success_change_password}">
						<div class="alert alert-success" role="alert">
							<c:out value="${success_change_password_message}"></c:out>
						</div>
					</c:if>	
					<c:if test="${not empty unsuccess_change_password}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_change_password_message}"></c:out>
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
					
					<a href="${pageContext.request.contextPath}/Controller?command=show_user_detail&id_user=${id_user}">${back_link}</a>
				</div>
			</div>
		</div>
	
		<script>
			setInterval(Check(), 1000);
			
			function Check() {
				if (document.querySelector('.alert') !== null) setTimeout(() => {document.querySelectorAll('.alert').forEach(e => e.parentNode.removeChild(e))}, 4000);
			}
		</script>	
		<jsp:include page="part/footer.jsp"/>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>