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
		
		<fmt:message bundle="${loc}" key="local.locbutton.list" var="list_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.table" var="table_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.more" var="more_button" />
		
		
		<fmt:message bundle="${loc}" key="local.empty_message.empty_specialties" var="empty_specialties_message" />		
		
		<fmt:message bundle="${loc}" key="local.loclabel.type_study_specialty" var="type_study_specialty_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.terms_study_specialty" var="terms_study_specialty_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.count_years" var="count_years_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.count_years_v2" var="count_years_v2_label" />
		
	</head>
	<body>		
		<jsp:include page="jsp/part/navbar.jsp"/>
		<jsp:include page="jsp/part/header.jsp"/>
		
		<c:set var="count" value="0" scope="page"/>
		
		<div class="container" style="margin-top: 20px">
			<c:choose>
				<c:when test="${not empty specialties}">
					<c:forEach items="${specialties}" var="spec">
						<div class="item-specialty">
							<div style="background: url(resources/img/books.jpg) no-repeat; background-size: cover; background-position: center; " class="img-specialty"></div>
							<div class="discription-specialty">
								<div class="text-discription">
								<h5 class="mb-5">${spec.getSpecialtyName()}</h5>
								</div>
								<div class="content-discription">
									<h6>Факультет: ${faculties[count].getFacultyName()}</h6>
									<p>${type_study_specialty_label} : ${types[count].getStudyName()}</p>
									<p>${terms_study_specialty_label} : ${spec.getCountYearStudy()} 
										<c:choose>
											<c:when test="${spec.getCountYearStudy() > 4}">
												<c:if test="${count_years_label == 'years'}">
													years
												</c:if>
												<c:if test="${count_years_label != 'years'}">
													${count_years_v2_label}
												</c:if>
											</c:when>
											<c:otherwise>
												${count_years_label}
											</c:otherwise>	
										</c:choose>
									</p>
									<form action="${pageContext.request.contextPath}/Controller" method="POST">
										<input type="hidden" name="id_specialty" value="${spec.getId()}">
										<input type="hidden" name="id_faculty" value="${faculties[count].getId()}">
										<input type="hidden" name="command" value="show_specialty">
										<div class="discription-button">
											<button class="btn btn-primary" type="submit">${more_button}</button>
										</div>										
									</form>
								</div>
							</div>
						</div>
						<c:set var="count" value="${count + 1}" scope="page"/>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:if test="${not empty empty_specialties}">
						<h2>${empty_specialties_message}!</h2>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
		
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