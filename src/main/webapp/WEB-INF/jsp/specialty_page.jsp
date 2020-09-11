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

		<fmt:message bundle="${loc}" key="local.admin_loclabel.specialty" var="specialty_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.specialty_name" var="specialty_name_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.type_name" var="type_name_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.faculty_name" var="faculty_name_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.qualification_name" var="qualification_name_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.plan_name" var="plan_name_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.count_year_study_name" var="count_year_study_name_label" />
		<fmt:message bundle="${loc}" key="local.loclink.back" var="back_link" />
		
		<fmt:message bundle="${loc}" key="local.admin_message.success_update_specialty" var="success_update_specialty_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_update_specialty" var="unsuccess_update_specialty_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.success_add_specialty" var="success_add_specialty_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_add_specialty" var="unsuccess_add_specialty_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.empty_specialty" var="empty_specialty_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.empty_subject" var="empty_subject_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.empty_faculty" var="empty_faculty_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.empty_plan" var="empty_plan_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.empty_type" var="empty_type_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_remove_specialty" var="unsuccess_remove_specialty_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.success_remove_specialty" var="success_remove_specialty_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.invalid_faculty_name" var="invalid_faculty_name_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.invalid_type_study_name " var="invalid_type_study_name_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.invalid_count_places" var="invalid_count_places_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.invalid_specialty_name" var="invalid_specialty_name_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.invalid_qualification" var="invalid_qualification_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.invalid_count_year_study" var="invalid_count_year_study_message" />
		
		<fmt:message bundle="${loc}" key="local.admin_locbutton.change_faculty" var="change_faculty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_faculty" var="add_faculty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.delete_faculty" var="delete_faculty_button" />
	</head>
	<body>
		<jsp:include page="part/admin_navbar.jsp"/>
		
		<div class="container">
			<div class="row">
				<div class="col-md-8 order-md-1">
					<h2 class="mb-3 mt-3">${specialty_label}</h2>
					
					<c:if test="${not empty success_update_specialty}">
						<div class="alert alert-success" role="alert">
							<c:out value="${success_update_specialty_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_update_specialty}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_update_specialty_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty success_add_specialty}">
						<div class="alert alert-success" role="alert">
							<c:out value="${success_add_specialty_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_add_specialty}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_add_specialty_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty empty_specialty}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${empty_specialty_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty empty_subject}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${empty_subject_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty empty_faculty}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${empty_faculty_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty empty_plan}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${empty_plan_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty empty_type}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${empty_type_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_type_study_name}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${invalid_type_study_name_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_faculty_name}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${invalid_faculty_name_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_specialty_name}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${invalid_specialty_name_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_qualification}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${invalid_qualification_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_count_places}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${invalid_count_places_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_count_year_study}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${invalid_count_year_study_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_remove_specialty}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_remove_specialty_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty success_remove_specialty}">
						<div class="alert alert-success" role="alert">
							<c:out value="${success_remove_specialty_message}"></c:out>
						</div>
					</c:if>
					
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="formStudy">${type_name_label}:</label>
								<input type="hidden" name="id_specialty" value="${c_specialty.getId()}"/>
								<select class="custom-select d-block w-100" id="formStudy" name="type_name">
									<c:if test="${not empty c_type}">
										<option selected value="${c_type.getStudyName()}">${c_type.getStudyName()}</option>
									</c:if>
									<c:forEach items="${types}" var="item">
										<c:if test="${c_type.getStudyName() != item.getStudyName()}">
											<option value="${item.getStudyName()}">${item.getStudyName()}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="formStudy">${faculty_name_label}:</label>
								<select class="custom-select d-block w-100" id="formFaculty" name="faculty_name">
									<c:if test="${not empty c_faculty}">
										<option selected value="${c_faculty.getFacultyName()}">${c_faculty.getFacultyName()}</option>
									</c:if>
									<c:forEach items="${faculties}" var="item">
										<c:if test="${c_faculty.getFacultyName() != item.getFacultyName()}">
											<option value="${item.getFacultyName()}">${item.getFacultyName()}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="formSpecialty">${specialty_name_label}:</label>
								<input type="text" class="form-control" id="formSpecialty" value="${c_specialty.getSpecialtyName()}" name="specialty_name"/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="formQualification">${qualification_name_label}:</label>
								<input type="text" class="form-control" id="formQualification" value="${c_specialty.getQualification()}" name="qualification_name"/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="formStudy">${plan_name_label}:</label>
								<select class="custom-select d-block w-100" id="formPlan" name="count_places">
									<c:if test="${not empty c_plan}">
										<option selected value="${c_plan.getCountPlaces()}">${c_plan.getCountPlaces()}</option>
									</c:if>
									<c:forEach items="${plans}" var="item">
										<c:if test="${c_plan.getCountPlaces() != item.getCountPlaces()}">
											<option value="${item.getCountPlaces()}">${item.getCountPlaces()}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="formCountYear">${count_year_study_name_label}:</label>
								<input type="number" min="1" max="5" class="form-control" id="formCountYear" value="${c_specialty.getCountYearStudy()}" name="count_year"/>
							</div>
						</div>
						
						<c:choose>
							<c:when test="${empty c_specialty}">
								<div class="row">
									<div class="col-md-6 mb-1">
										<input type="hidden" name="command" value="add_specialty">
										<button class="btn btn-primary btn-block" type="submit">${add_faculty_button}</button>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="row">
									<div class="col-md-6 mb-1">
										<input type="hidden" name="command" value="update_specialty">
										<button class="btn btn-primary btn-block" type="submit">${change_faculty_button}</button>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</form>
					
					<c:choose>
						<c:when test="${not empty c_specialty}">
							<form action="${pageContext.request.contextPath}/Controller" method="POST">
								<div class="row">
									<div class="col-md-6 mb-1">
										<input type="hidden" name="command" value="remove_specialty">
										<input type="hidden" name="id_specialty" value="${c_specialty.getId()}"/>
										<button class="btn btn-primary btn-block" type="submit">${delete_faculty_button}</button>
									</div>
								</div>
							</form>
						</c:when>
					</c:choose>	
					<a href="${pageContext.request.contextPath}/Controller?command=show_admin_panel">${back_link}</a>
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