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
		
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"
			type="text/javascript"></script>
		<script src="resources/js/script.js" type="text/javascript"></script>
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="locale/local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.loclabel.matriculant" var="matriculant_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.choose_study" var="choose_study_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.choose_faculty" var="choose_faculty_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.choose_specialty" var="choose_specialty_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.choose_certificate" var="choose_certificate_label" />	
		
		<fmt:message bundle="${loc}" key="local.locbutton.accept" var="accept_button" />	
		<fmt:message bundle="${loc}" key="local.locbutton.delete" var="delete_button" />
		
		<fmt:message bundle="${loc}" key="local.message.success_add_matricuant" var="success_add_matriculant_message" />
		<fmt:message bundle="${loc}" key="local.message.unsuccess_add_matricuant" var="unsuccess_add_matriculant_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_certificate" var="invalid_certificate_message" />
		<fmt:message bundle="${loc}" key="local.message.success_remove_matriculant" var="success_remove_matriculant_message" />
		<fmt:message bundle="${loc}" key="local.message.unsuccess_remove_matriculant" var="unsuccess_remove_matriculant_message" />
		<fmt:message bundle="${loc}" key="local.message.invalid_specialty" var="invalid_specialty_message" />
		
	</head>
	<body>		
		<jsp:include page="part/navbar.jsp"/>
		<jsp:include page="part/header.jsp"/>
		
		<div class="container">	
			<div class="py-5 text-center"></div>
			<div class="row">
				<div class="col-md-8 order-md-1">
					<h2 class="mb-3 mt-3">${matriculant_label}</h2>
							
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<div class="row">
							<div class="col-md-6 mb-3">				
								<c:if test="${not empty success_add_matriculant}">
									<div class="alert alert-warning" role="alert">
										<c:out value="${success_add_matriculant_message}"></c:out>
									</div>
								</c:if>	
								<c:if test="${not empty unsuccess_add_matriculant}">
									<div class="alert alert-warning" role="alert">
										<c:out value="${unsuccess_add_matriculant_message}"></c:out>
									</div>
								</c:if>	
								<c:if test="${not empty invalid_certificate}">
									<div class="alert alert-warning" role="alert">
										<c:out value="${invalid_certificate_message}"></c:out>
									</div>
								</c:if>	
								<c:if test="${not empty invalid_specialty}">
									<div class="alert alert-warning" role="alert">
										<c:out value="${invalid_specialty_message}"></c:out>
									</div>
								</c:if>	
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="formStudy">${choose_study_label}:</label>
								<select class="custom-select d-block w-100" id="formStudy" name="type_study">
									<c:forEach items="${types}" var="item">
										<option value="${item.getStudyName()}">${item.getStudyName()}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="formStudy">${choose_faculty_label}:</label>
								<select class="custom-select d-block w-100" id="formFaculty" name="faculty">
									<c:forEach items="${faculties}" var="item">
										<option value="${item.getFacultyName()}">${item.getFacultyName()}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="formStudy">${choose_specialty_label}:</label>
								<select class="custom-select d-block w-100" id="formSpecialty" name="specialty">	
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="formStudy">${choose_certificate_label}:</label>
								<input type="text" class="form-control" id="certificateId" name="certificate"/>
							</div>
						</div>
						<c:if test="${empty certificate}">
							<div class="row">
								<div class="col-md-6 mb-3">
									<input type="hidden" name="command" value="add_matriculant">
									<button class="btn btn-primary btn-lg btn-block" type="submit">${accept_button}</button>
								</div>
							</div>
						</c:if>
					</form>
				</div>
				
				<div class="col-md-4 order-md-2 mb-4">
					<h2 class="mb-3 mt-3">Ваше заявление</h2>
					<div class="row">
						<div class="col-md-12 mb-3">			
							<c:if test="${not empty success_remove_matriculant}">
								<div class="alert alert-warning" role="alert">
									<c:out value="${success_remove_matriculant_message}"></c:out>
								</div>
							</c:if>	
							<c:if test="${not empty unsuccess_remove_matriculant}">
								<div class="alert alert-warning" role="alert">
									<c:out value="${unsuccess_remove_matriculant_message}"></c:out>
								</div>
							</c:if>
						</div>	
					</div>
					<c:choose>
						<c:when test="${not empty certificate}">
							<div class="row">
								<div class="col-md-12 mb-3">
									<label for="formStudy">${choose_certificate_label}:</label>
									<input readonly type="text" class="form-control" id="formStudy" value="${type_study}"/>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 mb-3">
									<label for="formStudy">${choose_certificate_label}:</label>
									<input readonly type="text" class="form-control" id="certificateId" value="${faculty}"/>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 mb-3">
									<label for="formStudy">${choose_certificate_label}:</label>
									<input readonly type="text" class="form-control" id="certificateId" value="${specialty}"/>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 mb-3">
									<label for="formStudy">${choose_certificate_label}:</label>
									<input readonly type="text" class="form-control" id="certificateId" value="${certificate}"/>
								</div>
							</div>
							
							<form action="${pageContext.request.contextPath}/Controller" method="POST">
								<div class="row">
									<div class="col-md-12 mb-3">
										<input type="hidden" name="command" value="remove_matriculant">
										<button class="btn btn-primary btn-lg btn-block" type="submit">${delete_button}</button>
									</div>
								</div>
							</form>
						</c:when>
						<c:otherwise>
							<h4>У вас нет активной заявки!</h4>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		
		<jsp:include page="part/footer.jsp"/>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>