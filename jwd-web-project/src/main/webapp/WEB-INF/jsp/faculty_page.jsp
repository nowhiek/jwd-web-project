<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="resources/css/style.css" rel="stylesheet">
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="locale/local" var="loc" />

		<fmt:message bundle="${loc}" key="local.admin_loclabel.faculty" var="faculty_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.faculty_name" var="faculty_name_label" />
		
		<fmt:message bundle="${loc}" key="local.admin_message.success_update_faculty" var="success_update_faculty_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_update_faculty" var="unsuccess_update_faculty_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.success_add_faculty" var="success_add_faculty_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_add_faculty" var="unsuccess_add_faculty_message" />
		
		<fmt:message bundle="${loc}" key="local.admin_locbutton.change_faculty" var="change_faculty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_faculty" var="add_faculty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.delete_faculty" var="delete_faculty_button" />
	</head>
	<body>
		<jsp:include page="part/admin_navbar.jsp"/>
		
		<div class="container">
			<div class="row">
				<div class="col-md-8 order-md-1">
					<h2 class="mb-3 mt-3">${faculty_label}</h2>
					
					<c:if test="${not empty success_update_faculty}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${success_update_faculty_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_update_faculty}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${unsuccess_update_faculty_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty success_add_faculty}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${success_add_faculty_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_add_faculty}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${unsuccess_add_faculty_message}"></c:out>
						</div>
					</c:if>
					
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="lastName">${faculty_name_label}:</label>
								<input type="text" class="form-control" id="lastName" value="${c_faculty.getFacultyName()}" name="faculty_name"/>
								<input type="hidden" name="id_faculty" value="${c_faculty.getId()}"/>
							</div>
						</div>
						<c:choose>
							<c:when test="${empty c_faculty}">
								<div class="row">
									<div class="col-md-6 mb-1">
										<input type="hidden" name="command" value="add_faculty">
										<button class="btn btn-primary btn-block" type="submit">${add_faculty_button}</button>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="row">
									<div class="col-md-6 mb-1">
										<input type="hidden" name="command" value="update_faculty">
										<button class="btn btn-primary btn-block" type="submit">${change_faculty_button}</button>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</form>
					
					<c:choose>
						<c:when test="${not empty c_faculty}">
							<form action="${pageContext.request.contextPath}/Controller" method="POST">
								<div class="row">
									<div class="col-md-6 mb-1">
										<input type="hidden" name="command" value="remove_faculty">
										<input type="hidden" name="id_faculty" value="${c_faculty.getId()}"/>
										<button class="btn btn-primary btn-block" type="submit">${delete_faculty_button}</button>
									</div>
								</div>
							</form>
						</c:when>
					</c:choose>	
				</div>
			</div>
		</div>

		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>