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

		<fmt:message bundle="${loc}" key="local.admin_loclabel.type" var="type_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.type_name" var="type_name_label" />
		<fmt:message bundle="${loc}" key="local.loclink.back" var="back_link" />
		
		<fmt:message bundle="${loc}" key="local.admin_message.success_update_type" var="success_update_type_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_update_type" var="unsuccess_update_type_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.success_add_type" var="success_add_type_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_add_type" var="unsuccess_add_type_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.empty_type" var="empty_type_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.unsuccess_remove_type" var="unsuccess_remove_type_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.success_remove_type" var="success_remove_type_message" />
		<fmt:message bundle="${loc}" key="local.admin_message.invalid_type_study_name" var="invalid_type_study_name_message" />
		
		<fmt:message bundle="${loc}" key="local.admin_locbutton.change_faculty" var="change_faculty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_faculty" var="add_faculty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.delete_faculty" var="delete_faculty_button" />
	</head>
	<body>
		<jsp:include page="part/admin_navbar.jsp"/>
		
		<div class="container">
			
			<div class="row">
				<div class="col-md-8 order-md-1">
					<h2 class="mb-3 mt-3">${type_label}</h2>
					
					<c:if test="${not empty success_update_type}">
						<div class="alert alert-success" role="alert">
							<c:out value="${success_update_type_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_update_type}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_update_type_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty success_add_type}">
						<div class="alert alert-success" role="alert">
							<c:out value="${success_add_type_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty unsuccess_add_type}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_add_type_message}"></c:out>
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
					<c:if test="${not empty unsuccess_remove_type}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${unsuccess_remove_type_message}"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty success_remove_type}">
						<div class="alert alert-success" role="alert">
							<c:out value="${success_remove_type_message}"></c:out>
						</div>
					</c:if>
					
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="typeName">${type_name_label}:</label>
								<input type="text" class="form-control" id="typeName" value="${c_type.getStudyName()}" name="type_name"/>
								<input type="hidden" name="id_type" value="${c_type.getId()}"/>
							</div>
						</div>
						
						<c:choose>
							<c:when test="${empty c_type}">
								<div class="row">
									<div class="col-md-6 mb-1">
										<input type="hidden" name="command" value="add_type_study">
										<button class="btn btn-primary btn-block" type="submit">${add_faculty_button}</button>
									</div>
								</div>	
							</c:when>
							<c:otherwise>
								<div class="row">
									<div class="col-md-6 mb-1">
										<input type="hidden" name="command" value="update_type">
										<button class="btn btn-primary btn-block" type="submit">${change_faculty_button}</button>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
						
					</form>
					
					<c:choose>
						<c:when test="${not empty c_type}">
							<form action="${pageContext.request.contextPath}/Controller" method="POST">
								<div class="row">
									<div class="col-md-6 mb-1">
										<input type="hidden" name="command" value="remove_type">
										<input type="hidden" name="id_type" value="${c_type.getId()}"/>
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