<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="resources/css/style.css" rel="stylesheet">
	
		<fmt:setLocale value="${sessionScope.local}"/>
		<fmt:setBundle basename="locale/local" var="loc" />
	
		<fmt:message bundle="${loc}" key="local.message.success_change_user_data" var="success_change_user_data_message" />
		<fmt:message bundle="${loc}" key="local.message.unsuccess_change_user_data" var="unsuccess_change_user_data_message" />
		<fmt:message bundle="${loc}" key="local.message.empty_user" var="empty_user_message" />
		
		<fmt:message bundle="${loc}" key="local.loclabel.user" var="user_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.user_detail" var="user_detail_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.user_passport" var="user_passport_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.admin_login" var="admin_login_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.admin_password" var="admin_password_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.admin_role" var="admin_role_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.admin_ban" var="admin_ban_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.admin_firstname" var="admin_firstname_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.admin_secondname" var="admin_secondname_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.admin_email" var="admin_email_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.admin_sex" var="admin_sex_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.admin_birthday" var="admin_birthday_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.admin_serial" var="admin_serial_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.admin_number" var="admin_number_label" />
		
		<fmt:message bundle="${loc}" key="local.locbutton.accept" var="accept_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.ban_user" var="ban_user_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.unban_user" var="unban_user_button" />
	</head>
	<body>
		
		<div class="container">	
			<div class="py-5 text-center"></div>
			<form action="${pageContext.request.contextPath}/Controller" method="POST">
				<div class="row">
					<div class="col-md-4 order-md-2 mb-4">
						<h4 class="mb-3">${user_label}</h4>
						<div class="col-md-8">
							<label for="login">${admin_login_label}:</label>
							<input type="text" class="form-control" name="login" value="${user.getUserName()}">
						</div>
						
						<div class="col-md-8">
							<label for="password">${admin_password_label}:</label>
							<input type="text" class="form-control" name="password" value="${user.getUserPassword()}">
						</div>
						
						<div class="col-md-8">
							<label for="role">${admin_role_label}:</label>
							<input type="text" class="form-control" name="role" value="${user.getUserRole()}">
						</div>
						
						<div class="col-md-8">
							<label for="ban">${admin_ban_label}:</label>
							<input type="text" class="form-control" name="ban" value="${user.getUserIsBanned()}">
						</div>
					</div>
					<div class="col-md-8 order-md-1">
						<h4 class="mb-3">${user_detail_label}</h4>
						<c:if test="${not empty empty_user}">
							<div class="alert alert-warning" role="alert">
								<c:out value="${empty_user_message}"></c:out>
							</div>
						</c:if>
						<c:if test="${not empty success_change_user_data}">
							<div class="alert alert-warning" role="alert">
								<c:out value="${success_change_user_data_message}"></c:out>
							</div>
						</c:if>
						<c:if test="${not empty unsuccess_change_user_data}">
							<div class="alert alert-warning" role="alert">
								<c:out value="${unsuccess_change_user_data_message}"></c:out>
							</div>
						</c:if>				
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="firstName">${admin_firstname_label}:</label>
								<input type="text" class="form-control" name="first_name" value="${user_detail.getFirstName()}">
							</div>
							<div class="col-md-6 mb-3">
								<label for="secondName">${admin_secondname_label}:</label>
								<input type="text" class="form-control" name="second_name" value="${user_detail.getSecondName()}">
							</div>
						</div>
						
						<div class="mb-3">
							<label for="email">${admin_email_label}:</label>
							<input type="email" class="form-control" name="email" value="${user.getUserEmail()}">
						</div>
						
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="sex">${admin_sex_label}:</label>
								<input type="text" class="form-control" name="sex" value="${user_detail.getSex()}">
							</div>
							<div class="col-md-6 mb-3">
								<label for="birthday">${admin_birthday_label}:</label>
								<input type="text" class="form-control" name="birthday" value="${user_detail.getBirthday()}">
							</div>
						</div>
						
						<h4 class="mb-3">${user_passport_label}</h4>
						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="serialPassport">${admin_serial_label}</label>
								<input type="text" class="form-control" name="serial_passport" value="${user_detail.getSerialPassport()}">
							</div>
							<div class="col-md-9 mb-3">
								<label for="numberPassport">${admin_number_label}:</label>
								<input type="text" class="form-control" name="number_passport" value="${user_detail.getNumberPassport()}">
							</div>
						</div>
						<input type="hidden" name="id_user" value="${user.getId()}">
						<input type="hidden" name="command" value="change_user">
						<button class="btn btn-primary btn-block" type="submit">${accept_button}</button>
					</div>
				</div>	
			</form>
		
			<div class="row">
				<div class="col-md-8 order-md-1">
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<input type="hidden" name="command" value="block_user">
						<input type="hidden" name="id_user" value="${user.getId()}">
						<c:choose>
							<c:when test="${user.getUserIsBanned() == true}">	
								<button class="btn btn-primary btn-block" type="submit">${unban_user_button}</button>
							</c:when>
							<c:otherwise>
								<button class="btn btn-primary btn-block" type="submit">${ban_user_button}</button>
							</c:otherwise>
						</c:choose>
					</form>	
				</div>	
			</div>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>