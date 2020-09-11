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

		<fmt:message bundle="${loc}" key="local.admin_loclabel.subject" var="subject_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.subject_name" var="subject_name_label" />
		<fmt:message bundle="${loc}" key="local.loclink.back" var="back_link" />
		
		<fmt:message bundle="${loc}" key="local.admin_message.success_update_subject" var="success_update_subject_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_update_subject" var="unsuccess_update_subject_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.success_add_subject" var="success_add_subject_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_add_subject" var="unsuccess_add_subject_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.empty_subject" var="empty_subject_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_remove_subject" var="unsuccess_remove_subject_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.success_remove_subject" var="success_remove_subject_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.invalid_subject_name" var="invalid_subject_name_message" />

		
		<fmt:message bundle="${loc}" key="local.admin_locbutton.change_faculty" var="change_faculty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_faculty" var="add_faculty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.delete_faculty" var="delete_faculty_button" />
	</head>
	<body>
		<jsp:include page="part/admin_navbar.jsp"/>
		
		<div class="container">
			
			<div class="row">
				<div class="col-md-8 order-md-1">
					<h2 class="mb-3 mt-3">${subject_label}</h2>
					
					<c:if test="${not empty success_update_subject}">
						<div class="alert alert-success" role="alert">
							<c:out value="${success_update_subject_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_update_subject}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_update_subject_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty success_add_subject}">
						<div class="alert alert-success" role="alert">
							<c:out value="${success_add_subject_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_add_subject}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_add_subject_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty empty_subject}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${empty_subject_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty invalid_subject_name}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${invalid_subject_name_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_remove_subject}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_remove_subject_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty success_remove_subject}">
						<div class="alert alert-success" role="alert">
							<c:out value="${success_remove_subject_message}"></c:out>
						</div>
					</c:if>
					
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="subjectName">${subject_name_label}:</label>
								<input type="text" class="form-control" id="subjectName" value="${c_subject.getSubjectName()}" name="subject_name"/>
								<input type="hidden" name="id_subject" value="${c_subject.getId()}"/>
							</div>
						</div>
						
						<c:choose>
							<c:when test="${empty c_subject}">
								<div class="row">
									<div class="col-md-6 mb-1">
										<input type="hidden" name="command" value="add_subject">
										<button class="btn btn-primary btn-block" type="submit">${add_faculty_button}</button>
									</div>
								</div>	
							</c:when>
							<c:otherwise>
								<div class="row">
									<div class="col-md-6 mb-1">
										<input type="hidden" name="command" value="update_subject">
										<button class="btn btn-primary btn-block" type="submit">${change_faculty_button}</button>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
						
					</form>
					
					<c:choose>
						<c:when test="${not empty c_subject}">
							<form action="${pageContext.request.contextPath}/Controller" method="POST">
								<div class="row">
									<div class="col-md-6 mb-1">
										<input type="hidden" name="command" value="remove_subject">
										<input type="hidden" name="id_subject" value="${c_subject.getId()}"/>
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