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
		<fmt:message bundle="${loc}" key="local.loclink.back" var="back_link" />
		
		<fmt:message bundle="${loc}" key="local.admin_loclabel.matriculant_by" var="matriculant_by_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.exams" var="exams_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.id_user" var="id_user_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.certificate" var="certificate_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.specialty" var="specialty_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.subject_for_specialty" var="subject_for_specialty_label" />
		
		<fmt:message bundle="${loc}" key="local.admin_loclabel.subject_add" var="subject_add_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.mark_add" var="mark_add_label" />
		
		<fmt:message bundle="${loc}" key="local.admin_message.cant_add_marks" var="cant_add_marks_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.empty_subjects" var="empty_subjects_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.not_valid_count_subjects" var="not_valid_count_subjects_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.empty_subject_in_this_specialty" var="empty_subject_in_this_specialty_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.empty_matriculant" var="empty_matriculant_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.success_activate_matriculant" var="success_activate_matriculant_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_activate_matriculant" var="unsuccess_activate_matriculant_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_add_exam" var="unsuccess_add_exam_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.success_update_matriculant" var="success_update_matriculant_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_update_matriculant" var="unsuccess_update_matriculant_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_remove_matriculant" var="unsuccess_remove_matriculant_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.invalid_certificate" var="invalid_certificate_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.invalid_specialty_name" var="invalid_specialty_name_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.invalid_subject_name" var="invalid_subject_name_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.invalid_mark" var="invalid_mark_message" />
		
		<fmt:message bundle="${loc}" key="local.admin_locbutton.change_faculty" var="change_faculty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.delete_faculty" var="delete_faculty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.activate" var="activate_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.deactivate" var="deactivate_button" />	
	</head>
	<body>
		<jsp:include page="part/admin_navbar.jsp"/>
		<div class="container">	
			<div class="py-5 text-center"></div>
			
			<div class="row">
				<div class="col-md-4 order-md-2 mb-4 exam-container">
					<h4 class="mb-3">${exams_label}</h4>
					<c:if test="${not empty success_activate_matriculant}">
						<div class="alert alert-success" role="alert">
							<c:out value="${success_activate_matriculant_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_activate_matriculant}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_activate_matriculant_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty empty_subjects}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${empty_subjects_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty not_valid_count_subjects}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${not_valid_count_subjects_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_mark}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${invalid_mark_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_subject_name}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${invalid_subject_name_message}"></c:out>
						</div>
					</c:if>
					
					<input type="hidden" name="count_sub" value="${count_subjects}"/>
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<c:choose>
							<c:when test="${not empty exams}">
								<div class="row mt-3">
									<div class="col-md-6">
									
										<c:set var="count" value="0" scope="page"/>
										
										<c:forEach items="${exams}" var="item">
											<ul>
												<li>${subjects[count].getSubjectName()} : ${item.getResultMark()}</li>
											</ul>
											<c:set var="count" value="${count + 1}" scope="page"/>
										</c:forEach>
										<input type="hidden" name="command" value="deactivate_matriculant">
										<input type="hidden" name="id_user" value="${user_data.getId()}">
										<input type="hidden" name="id_matriculant" value="${matriculant.getId()}">
										<input type="hidden" name="count_subjects" value="${count_subjects}">
										<button class="btn btn-primary btn-block" type="submit">${deactivate_button}</button>
									</div>
								</div>
							</c:when>
							<c:when test="${not empty empty_has}">
								<div class="alert alert-danger" role="alert">
									${cant_add_marks_message}
								</div>
								
								<div class="exam-container"></div>
								
								<div style="visibility: hidden; display: none;" class="add-btn btn btn-primary mt-3">+</div>
								<div style="visibility: hidden; display: none;" class="del-btn btn btn-primary	mt-3">-</div>
								
								<div class="col-md-8"></div>							
								<div class="row mt-3">
									<div class="col-md-6">
										<input type="hidden" name="command" value="activate_matriculant">
										<input type="hidden" name="id_user" value="${user_data.getId()}">
										<input type="hidden" name="id_matriculant" value="${matriculant.getId()}">
										
										<button class="btn btn-primary btn-block" type="submit" disabled>${activate_button}</button>
									</div>
								</div>
							</c:when>
							<c:when test="${empty empty_has}">
								<div class="add-btn btn btn-primary mt-3">+</div>
								<div class="del-btn btn btn-primary	mt-3">-</div>
								<div class="col-md-8"></div>
								<div class="row mt-3">
									<div class="col-md-6">
										<input type="hidden" name="command" value="activate_matriculant">
										<input type="hidden" name="id_user" value="${user_data.getId()}">
										<input type="hidden" name="id_matriculant" value="${matriculant.getId()}">
										<input type="hidden" name="count_subjects" value="${count_subjects}">
										<button class="btn btn-primary btn-block" type="submit">${activate_button}</button>
									</div>
								</div>
							</c:when>
						</c:choose>
					</form>	
				</div>
				
				
				<div class="col-md-8 order-md-1">
					<h4 class="mb-3">${matriculant_by_label} - ${user_data.getUserName()}</h4>
					<c:if test="${not empty empty_matriculant}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${empty_matriculant_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_add_exam}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_add_exam_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty success_update_matriculant}">
						<div class="alert alert-success" role="alert">
							<c:out value="${success_update_matriculant_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_update_matriculant}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_update_matriculant_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_remove_matriculant}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_remove_matriculant_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_certificate}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${invalid_certificate_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_specialty_name}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${invalid_specialty_name_message}"></c:out>
						</div>
					</c:if>
					
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<div class="row">
							<div class="col-md-9 mb-3">
								<label for="idUser">${id_user_label}:</label>
								<input disabled type="text" class="form-control" id="idUser" name="id_user" value="${user_data.getId()}">
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-9 mb-3">
								<label for="specialtyName">${specialty_label}:</label>
								<select class="custom-select d-block w-100" id="specialtyName" name="specialty_name">
									<option selected value="${c_specialty.getSpecialtyName()}">${c_specialty.getSpecialtyName()}</option>
									<c:forEach items="${specialties}" var="item">
										<c:if test="${c_specialty.getSpecialtyName() != item.getSpecialtyName()}">
											<option value="${item.getSpecialtyName()}">${item.getSpecialtyName()}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-9 mb-3">
								<label for="certificateName">${certificate_label}:</label>
								<input type="number" min="0" max="100" class="form-control" id="certificateName" name="certificate" value="${matriculant.getCertificate()}">
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-9 mb-3">
								<label for="certificateName">${subject_for_specialty_label}:</label>
								<ul>
									<c:choose>
										<c:when test="${not empty empty_has}">																						
											<li>${empty_subject_in_this_specialty_message}</li>											
										</c:when>
										<c:otherwise>
											<c:forEach items="${subjects}" var="item">
												<li>${item.getSubjectName()}</li>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</ul>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<input type="hidden" name="command" value="update_matriculant">
								<input type="hidden" name="id_matriculant" value="${matriculant.getId()}">
								<button class="btn btn-primary btn-block" type="submit">${change_faculty_button}</button>
							</div>
						</div>
					</form>
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<div class="row">
							<div class="col-md-4">
								<input type="hidden" name="command" value="remove_application">
								<input type="hidden" name="id_matriculant" value="${matriculant.getId()}">
								<button class="btn btn-primary btn-block" type="submit">${delete_faculty_button}</button>
							</div>
						</div>
					</form>
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
		
		<script>
		const EXAM_CONT = document.querySelector('.exam-container');
		let count_exams = document.getElementsByClassName('div_count');
		const COUNT_SUBJECTS = document.getElementsByName('count_sub')[0].value;
		const ADD_BTN = document.querySelector('.add-btn');
		const DEL_BTN = document.querySelector('.del-btn');

		function AddElement() {

			if (count_exams.length == COUNT_SUBJECTS) return false;
			else {
				div = document.createElement('div');
				div.classList.add('col-md-8', 'div_count');
				ADD_BTN.before(div);
				
				forSubject = document.createElement('label');
				forSubject.htmlFor  = 'forSubject';
				forSubject.innerHTML = '${subject_add_label}: ';
				div.append(forSubject);
				
				select = document.createElement('select');
				select.classList.add('custom-select', 'd-block', 'w-100');
				select.id = 'forSubject';
				select.name = 'subject_name';
				select.innerHTML = '<c:forEach items="${subjects}" var="item">' +
				'<option value="${item.getSubjectName()}">${item.getSubjectName()}</option>' +
				'</c:forEach>';
				div.append(select);
				
				forExamMark = document.createElement('label');
				forExamMark.htmlFor  = 'forExamMark';
				forExamMark.innerHTML = '${mark_add_label}: ';
				div.append(forExamMark);
				
				input = document.createElement('input');
				input.classList.add('form-control');
				input.type = 'number';
				input.min = '0';
				input.max = '10';
				input.id = 'forExamMark';
				input.name = 'exam_mark';
				div.append(input);
			}
		}

		function DeleteElement() {
			let list = document.querySelectorAll('.div_count');
			list[list.length - 1].remove();
		}

		EXAM_CONT.addEventListener('click', (e) => {
			if (e.target === ADD_BTN) AddElement();
			if (e.target === DEL_BTN) DeleteElement();
		});
		</script>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>