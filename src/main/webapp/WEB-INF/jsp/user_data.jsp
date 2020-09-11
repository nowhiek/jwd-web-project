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
		<fmt:message bundle="${loc}" key="local.admin_message.empty_user_detail" var="empty_user_detail_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_name" var="invalid_name_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_surname" var="invalid_surname_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_birthday" var="invalid_birthday_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_passport" var="invalid_passport_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_login" var="invalid_login_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_password" var="invalid_password_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_email" var="invalid_email_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_sex" var="invalid_sex_message" />
		
		<fmt:message bundle="${loc}" key="local.loclink.back" var="back_link" />
			
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
		<jsp:include page="part/admin_navbar.jsp"/>
		<div class="container">	
			<div class="py-5 text-center"></div>
			<form action="${pageContext.request.contextPath}/Controller" method="POST">
				<div class="row">
					<div class="col-md-4 order-md-2 mb-4">
						<h4 class="mb-3">${user_label}</h4>
						<div class="col-md-8">
							<label for="login">${admin_login_label}:</label>
							<input type="text" class="form-control" name="login" value="${user_data.getUserName()}">
						</div>
						
						<div class="col-md-8">
							<label for="password">${admin_password_label}:</label>
							<input disabled type="text" class="form-control" name="password" value="${user_data.getUserPassword()}">
							<input type="hidden" name="password" value="${user_data.getUserPassword()}"/>
						</div>
						
						<div class="col-md-8">
							<label for="role">${admin_role_label}:</label>
							<c:choose>
								<c:when test="${user_data.getUserRole() != ADMIN}">
									<input disabled type="text" class="form-control" name="role" value="Пользователь">
								</c:when>
								<c:otherwise>
									<input disabled type="text" class="form-control" name="role" value="Администратор">
								</c:otherwise>
							</c:choose>
							<input type="hidden" name="role" value="${user_data.getUserRole()}"/>
						</div>
						
						<div class="col-md-8">
							<label for="ban">${admin_ban_label}:</label>
							<c:choose>
								<c:when test="${user_data.getUserIsBanned() == true}">
									<input disabled type="text" class="form-control" name="ban" value="Заблокирован">
								</c:when>
								<c:otherwise>
									<input disabled type="text" class="form-control" name="ban" value="Разблокирован">
								</c:otherwise>
							</c:choose>
							<input type="hidden" name="ban" value="${user_data.getUserIsBanned()}"/>
						</div>
					</div>
					<div class="col-md-8 order-md-1">
						<h4 class="mb-3">${user_detail_label}</h4>
						<c:if test="${not empty empty_user}">
							<div class="alert alert-danger" role="alert">
								<c:out value="${empty_user_message}"></c:out>
							</div>
						</c:if>
						<c:if test="${not empty success_change_user_data}">
							<div class="alert alert-success" role="alert">
								<c:out value="${success_change_user_data_message}"></c:out>
							</div>
						</c:if>
						<c:if test="${not empty unsuccess_change_user_data}">
							<div class="alert alert-danger" role="alert">
								<c:out value="${unsuccess_change_user_data_message}"></c:out>
							</div>
						</c:if>		
						<c:if test="${not empty empty_user_detail}">
							<div class="alert alert-danger" role="alert">
								<c:out value="${empty_user_detail_message}"></c:out>
							</div>
						</c:if>
						
						
						<c:if test="${not empty invalid_name}">
							<div class="alert alert-warning" role="alert">
								<c:out value="${invalid_name_message}"></c:out>
							</div>
						</c:if>	
						<c:if test="${not empty invalid_surname}">
							<div class="alert alert-warning" role="alert">
								<c:out value="${invalid_surname_message}"></c:out>
							</div>
						</c:if>	
						<c:if test="${not empty invalid_birthday}">
							<div class="alert alert-warning" role="alert">
								<c:out value="${invalid_birthday_message}"></c:out>
							</div>
						</c:if>	
						<c:if test="${not empty invalid_passport}">
							<div class="alert alert-warning" role="alert">
								<c:out value="${invalid_passport_message}"></c:out>
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
						<c:if test="${not empty invalid_sex}">
							<div class="alert alert-warning" role="alert">
								<c:out value="${invalid_sex_message}"></c:out>
							</div>
						</c:if>		
						<c:if test="${not empty invalid_email}">
							<div class="alert alert-warning" role="alert">
								<c:out value="${invalid_email_message}"></c:out>
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
							<input type="email" class="form-control" name="email" value="${user_data.getUserEmail()}">
						</div>
						
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="sex">${admin_sex_label}:</label>
								<select class="custom-select d-block w-100" id="sexSelect" name="sex">
									<c:choose>
										<c:when test="${user_detail.getSex() == 'Мужской'}">
											<option selected value="${user_detail.getSex()}">${user_detail.getSex()}</option>
											<option value="Женский">Женский</option>
										</c:when>
										<c:when test="${user_detail.getSex() == 'Женский'}">
											<option selected value="${user_detail.getSex()}">${user_detail.getSex()}</option>
											<option value="Мужской">Мужской</option>
										</c:when>
										<c:otherwise>
											<option value="Мужской">Мужской</option>
											<option value="Женский">Женский</option>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
							<div class="col-md-6 mb-3">
								<label for="birthday">${admin_birthday_label}:</label>
								<input type="date" class="form-control" name="birthday" value="${user_detail.getBirthday()}">
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
								<input type="number" min="0" max="9999999" class="form-control" name="number_passport" value="${user_detail.getNumberPassport()}">
							</div>
						</div>
						<input type="hidden" name="id_user" value="${user_data.getId()}">
						<input type="hidden" name="command" value="change_user">
						<!-- <button class="btn btn-primary btn-block" type="submit">${accept_button}</button>  -->
					</div>
				</div>	
			</form>
		
			<div class="row">
				<div class="col-md-8 order-md-1">
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<input type="hidden" name="command" value="block_user">
						<input type="hidden" name="id_user" value="${user_data.getId()}">
						<c:choose>
							<c:when test="${user_data.getUserIsBanned() == true}">	
								<button class="btn btn-primary btn-block" type="submit">${unban_user_button}</button>
							</c:when>
							<c:otherwise>
								<button class="btn btn-primary btn-block" type="submit">${ban_user_button}</button>
							</c:otherwise>
						</c:choose>
						<a href="${pageContext.request.contextPath}/Controller?command=show_admin_panel">${back_link}</a>
					</form>	
					
				</div>	
			</div>
		</div>
		
		<script>
			setInterval(Check(), 1000);
			
			function Check() {
				if (document.querySelector('.alert') !== null) setTimeout(() => {document.querySelectorAll('.alert').forEach(e => e.parentNode.removeChild(e))}, 4000);			
			}
		</script>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>