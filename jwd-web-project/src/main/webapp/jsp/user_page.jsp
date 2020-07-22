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
		<title>User page</title>
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="locale/local" var="loc" />

		<fmt:message bundle="${loc}" key="local.loclabel.first_name" var="first_name_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.second_name" var="second_name_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.sex" var="sex_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.birthday" var="birthday_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.passport" var="passport_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.serial" var="serial_passport_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.number" var="number_passport_label" />
		
		<fmt:message bundle="${loc}" key="local.locoption.male" var="male_option" />
		<fmt:message bundle="${loc}" key="local.locoption.female" var="female_option" />
		
		<fmt:message bundle="${loc}" key="local.loclink.back" var="back_link" />
		<fmt:message bundle="${loc}" key="local.loclink.change_user_password" var="change_user_password_link" />
		
		<fmt:message bundle="${loc}" key="local.locbutton.change_user_detail" var="change_user_detail_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.add_matriculant" var="add_matriculant_button" />
		
		<fmt:message bundle="${loc}" key="local.message.invalid_name" var="invalid_name_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_surname" var="invalid_surname_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_birthday" var="invalid_birthday_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_passport" var="invalid_passport_message" />
		<fmt:message bundle="${loc}" key="local.message.succes_update_user_detail" var="succes_update_user_detail_message" />
		<fmt:message bundle="${loc}" key="local.message.unsucces_update_user_detail" var="unsucces_update_user_detail_message" />
	</head>
	<body>		
		<jsp:include page="part/navbar.jsp"/>
		<jsp:include page="part/header.jsp"/>
		<div class="container">
			<form action="${pageContext.request.contextPath}/Controller" method="POST">
				<div class="row">
					<div class="col-md-8 order-md-1">
						<h2 class="mb-3 mt-3">Profile</h2>
							<c:if test="${not empty succes_update_user_detail}">
								<div class="alert alert-warning" role="alert">
									<c:out value="${succes_update_user_detail_message}"></c:out>
								</div>
							</c:if>	
							<c:if test="${not empty unsucces_update_user_detail}">
								<div class="alert alert-warning" role="alert">
									<c:out value="${unsucces_update_user_detail_message}"></c:out>
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
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="firstName">${first_name_label}:</label>
									<input type="text" class="form-control" id="firstName" value="${first_name}" name="first_name"/>				
								</div>
								<div class="col-md-6 mb-3">
									<label for="lastName">${second_name_label}:</label>
									<input type="text" class="form-control" id="lastName" value="${second_name}" name="second_name"/>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="sexSelect">${sex_label}:</label>
									<select class="custom-select d-block w-100" id="sexSelect" name="sex">
										<c:choose>
											<c:when test="${sex == 'Мужской'}">
												<option selected value="${sex}">${sex}</option>
												<option value="Женский">Женский</option>
											</c:when>
											<c:when test="${sex == 'Женский'}">
												<option selected value="${sex}">${sex}</option>
												<option value="Мужской">Мужской</option>
											</c:when>
											<c:otherwise>
												<option value="Мужской">Мужской</option>
												<option value="Мужской">Женский</option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
								<div class="col-md-3 mb-3">
									<input type="hidden">
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-2 mb-3">
									<label for="serialPassport">${serial_passport_label}:</label>
									<input type="text" class="form-control" id="serialPassport" value="${serial_passport}" name="serial_passport">
								</div>
								<div class="col-md-4 mb-3">
									<label for="numberPassport">${number_passport_label}:</label>
									<input type="text" class="form-control" id="number_passport_label" value="${number_passport}" name="number_passport">
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="dateBirthday">${birthday_label}:</label>
									<input type="date" class="form-control" id="dateBirthday" value="${birthday}" name="birthday"/>
								</div>
								<div class="col-md-3 mb-3">
									<input type="hidden">
								</div>
							</div>
							<hr class="mb-4">
					</div>
				</div>
						
				<input type="hidden" name="command" value="change_user_detail">
				<button class="btn btn-primary" type="submit">${change_user_detail_button}</button>
				
			</form>
			<form action="Controller" method="POST">
				<input type="hidden" name="command" value="show_matriculant">
				<button class="btn btn-primary" type="submit">${add_matriculant_button}</button>
			</form>	
			
			<a href="${pageContext.request.contextPath}/jsp/user_password_page.jsp">${change_user_password_link}</a>	
			<a style="float: right;" href="${pageContext.request.contextPath}/jsp/auth_page.jsp">${back_link}</a>
			
		</div>
		
		<jsp:include page="part/footer.jsp"/>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>