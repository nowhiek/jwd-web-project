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
		
		<fmt:message bundle="${loc}" key="local.loclink.back" var="back_link" />
	
		<fmt:message bundle="${loc}" key="local.admin_loclabel.no_information_about_matriculants" var="no_information_about_matriculants_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.all_places_for_specialty" var="all_places_for_specialty_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.students_enrolled_for_specialty" var="students_enrolled_for_specialty_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.remaining_free_seats" var="remaining_free_seats_label" />
		
		<fmt:message bundle="${loc}" key="local.admin_message.empty_specialty" var="empty_specialty_message" />
		
	</head>
	<body>
		<jsp:include page="part/admin_navbar.jsp"/>
		<div class="container">	
			<div class="py-5 text-center"></div>
			
			<h1>${c_specialty.getSpecialtyName()}</h1>
			
			<c:if test="${not empty empty_specialty}">
				<div class="alert alert-danger" role="alert">
					<c:out value="${empty_specialty_message}"></c:out>
				</div>
			</c:if>
		
			<c:set var="count" value="0" scope="page"/>
			
			<div class="table-responsive">	
				<form action="${pageContext.request.contextPath}/Controller" method="POST">
					<table id="table_list" class="table table-striped table-sm">
						<thead>
							<tr>
								<th scope="col">Name</th>
								<th scope="col">Points</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty users_detail}">
									<c:forEach items="${users_detail}" var="detail">
										<tr>
											<td>${detail.getFirstName()} ${detail.getSecondName()}</td>
											<td>${results[count].getPoints()}</td>
										</tr>
										<input type="hidden" name="matriculant_name" value="${detail.getFirstName()} ${detail.getSecondName()}"/>
										<input type="hidden" name="specialty_name" value="${c_specialty.getSpecialtyName()}"/>	
										<input type="hidden" name="id_specialty" value="${c_specialty.getId()}"/>										
										<c:set var="count" value="${count + 1}" scope="page"/>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<th>${no_information_about_matriculants_label}.</th>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					
					<input type="hidden" name="command" value="create_excel_table"/>
					<c:choose>
						<c:when test="${empty users_detail}">
							<button disabled class="btn btn-primary" type="submit">Excel</button>	
						</c:when>
						<c:otherwise>
							<button class="btn btn-primary" type="submit">Excel</button>							
						</c:otherwise>
					</c:choose>
				</form>
				<c:if test="${not empty users_detail}">
					<p>${all_places_for_specialty_label} : ${c_plan.getCountPlaces()}</p>
					<p>${students_enrolled_for_specialty_label} : ${users_detail.size()}</p>
					<p>${remaining_free_seats_label} : ${c_plan.getCountPlaces() - users_detail.size()}</p>
				</c:if>
				<a href="${pageContext.request.contextPath}/Controller?command=show_admin_panel">${back_link}</a>
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