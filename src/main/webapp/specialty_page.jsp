<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="resources/css/specialty.css" rel="stylesheet">	
		<link href="resources/css/style.css" rel="stylesheet">
		<link href="resources/css/header.css" rel="stylesheet">
			
		<fmt:setLocale value="${sessionScope.local}"/>
		<fmt:setBundle basename="locale/local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.loclabel.qualification" var="qualification_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.faculty" var="faculty_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.time_study" var="time_study_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.subject_specialty" var="subject_specialty_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.no_find_subjects" var="no_find_subjects_label" />
		<fmt:message bundle="${loc}" key="local.admin_message.empty_specialty" var="local.admin_message.empty_specialty_message" />
				
		<fmt:message bundle="${loc}" key="local.message.user_already_exist" var="user_already_exist_message" />
		
	</head>
	<body>		
		<jsp:include page="jsp/part/navbar.jsp"/>
		<jsp:include page="jsp/part/header.jsp"/>
		
		<c:set var="count" value="0" scope="page"/>
		
		<main role="main" class="content">
			<div class="specialty">
				<div class="starter-template">
					<c:choose>
						<c:when test="${not empty c_specialty}">
							<h2 style='text-align: center' >${specialties[0].getSpecialtyName()}</h2>
								<h6>${faculty_label}:</h6>
								<p>${c_faculty.getFacultyName()}</p>
								<br>	
								<h6>${qualification_label}:</h6>
								<p>${specialties[0].getQualification()}</p>
								<br>				
								<h6>${time_study_label}:</h6>
								<c:forEach items="${types}" var="type">
									<c:set var="year" value="${specialties[count].getCountYearStudy()}" scope="page"/>
									<p>
										на основе "${type.getStudyName().toLowerCase()}" формы обучения - 
										<i>
											${specialties[count].getCountYearStudy()}
										</i>
										<c:choose>
											<c:when test="${year <= '4'}">
												года
											</c:when>
											<c:otherwise>
												лет
											</c:otherwise>
										</c:choose>			
									</p>
									<br>
									<c:set var="count" value="${count + 1}" scope="page"/>
								</c:forEach>
								<h6>${subject_specialty_label}:</h6>
								<c:choose>
									<c:when test="${not empty subjects}">
										<c:forEach items="${subjects}" var="sub">
											<p>${sub.getSubjectName()}</p>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<p>${no_find_subjects_label}.</p>
									</c:otherwise>
								</c:choose>
						</c:when>
						<c:otherwise>
							<h2>${empty_specialty_message}</h2>
						</c:otherwise>
					</c:choose>
				</div>	
				<div style="background: url(resources/img/books.jpg) no-repeat; background-size: cover; background-position: center; " class="img-container"></div>			
			</div>
			
		</main>
		
		<script>
			setInterval(Check(), 1000);
			
			function Check() {
				if (document.querySelector('.alert') !== null) setTimeout(() => {document.querySelectorAll('.alert').forEach(e => e.parentNode.removeChild(e))}, 4000);
			}
		</script>
		<jsp:include page="jsp/part/footer.jsp"/>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>