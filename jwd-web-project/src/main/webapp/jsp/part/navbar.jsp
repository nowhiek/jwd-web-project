<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="resources/css/header.css" rel="stylesheet">
		<link href="resources/css/style.css" rel="stylesheet">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="locale/local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.nav_sign_in" var="nav_sign_in_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.nav_sign_up" var="nav_sign_up_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.sign_out" var="sign_out_button" />
		
		<fmt:message bundle="${loc}" key="local.locmenu.about" var="about_label" />
		<fmt:message bundle="${loc}" key="local.locmenu.matriculant" var="matriculant_label" />
		<fmt:message bundle="${loc}" key="local.locmenu.student" var="student_label" />
		<fmt:message bundle="${loc}" key="local.locmenu.specialties_and_faculties" var="specialties_and_faculties_label" />
		
		<fmt:message bundle="${loc}" key="local.locsubmenu.for_matriculant" var="for_matriculant_label" />
		<fmt:message bundle="${loc}" key="local.locsubmenu.university_admission_procedure" var="university_admission_procedure_label" />
		<fmt:message bundle="${loc}" key="local.locsubmenu.full_time_education" var="full_time_education_label" />
		<fmt:message bundle="${loc}" key="local.locsubmenu.extramural_studies" var="extramural_studies_label" />
		<fmt:message bundle="${loc}" key="local.locsubmenu.tuition_payment" var="tuition_payment_label" />
		<fmt:message bundle="${loc}" key="local.locsubmenu.student_rules" var="student_rules_label" />
		<fmt:message bundle="${loc}" key="local.locsubmenu.benefits" var="benefits_label" />
		<fmt:message bundle="${loc}" key="local.locsubmenu.faculties" var="faculties_label" />
		<fmt:message bundle="${loc}" key="local.locsubmenu.specialties" var="specialties_label" />
		
		<fmt:message bundle="${loc}" key="local.loclink.change_user_detail" var="user_detail_link" />
		<fmt:message bundle="${loc}" key="local.loclink.admin" var="admin_link" />
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="index.jsp">University</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
			  <span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse" id="navbarsExample05">
				<ul class="navbar-nav mr-auto">		      
				      <li class="nav-item">
				        <a class="nav-link" href="#">${about_label}</a>
				      </li>
				      <li class="nav-item dropdown">
				        <a class="nav-link dropdown-toggle" href="#" id="dropdown07XL" data-toggle="dropdown" aria-expanded="false">${matriculant_label}</a>
				        <div class="dropdown-menu" aria-labelledby="dropdown07XL">
				          <a class="dropdown-item" href="#">${for_matriculant_label}</a>
				          <a class="dropdown-item" href="#">${university_admission_procedure_label}</a>
				        </div>
				      </li>
				      <li class="nav-item dropdown">
				        <a class="nav-link dropdown-toggle" href="#" id="dropdown07XL" data-toggle="dropdown" aria-expanded="false">${student_label}</a>
				        <div class="dropdown-menu" aria-labelledby="dropdown07XL">
				          <a class="dropdown-item" href="#">${full_time_education_label}</a>
				          <a class="dropdown-item" href="#">${extramural_studies_label}</a>
				          <a class="dropdown-item" href="#">${tuition_payment_label}</a>
				          <a class="dropdown-item" href="#">${student_rules_label}</a>
				        </div>
				      </li>	      
				      <li class="nav-item dropdown">
				        <a class="nav-link dropdown-toggle" href="#" id="dropdown07XL" data-toggle="dropdown" aria-expanded="false">${specialties_and_faculties_label}</a>
				        <div class="dropdown-menu" aria-labelledby="dropdown07XL">
				          <a class="dropdown-item" href="#">${benefits_label}</a>
				          <form action="${pageContext.request.contextPath}/Controller" method="POST">
							  <input type="hidden" name="command" value="show_all_specialties">
							  <input class="dropdown-item" type="submit" value="${faculties_label}">
						  </form>
				          <form action="${pageContext.request.contextPath}/Controller" method="POST">
							  <input type="hidden" name="command" value="show_all_specialties">
							  <input class="dropdown-item" type="submit" value="${specialties_label}">
						  </form>
				        </div>
				    </li>
				</ul>
				
				<c:set var="user" scope="session" value="${ROLE}"/>
				<div class='secondary-menu_container'>
					<form action="Controller" method="POST">
						<input type="hidden" name="local" value="en" />
						<input type="hidden" name="command" value="change_locale" />
						<button class="btn btn-primary eng" type="submit"></button>
					</form>
					
					<form action="Controller" method="POST">
						<input type	="hidden" name="local" value="ru" />
						<input type="hidden" name="command" value="change_locale" />
						<button class="btn btn-primary rus" type="submit"></button>
					</form>
				
					<c:choose>
					    <c:when test="${user == 'User'}">
					    	<form action="Controller" method="POST">
								<input type="hidden" name="command" value="show_user_detail">
								<button class="btn btn-primary" type="submit">${user_detail_link}</button>
							</form>
					        <form action="Controller" method="POST">
								<input type="hidden" name="command" value="sign_out">
								<button class="btn btn-primary" type="submit">${sign_out_button}</button>
							</form>
					    </c:when>    
					    <c:when test="${user == 'Admin'}">
							<form action="Controller" method="POST">
								<input type="hidden" name="command" value="show_admin_panel">
								<button class="btn btn-primary" type="submit">${admin_link}</button>
							</form>
							<form action="Controller" method="POST">
								<input type="hidden" name="command" value="sign_out">
								<button class="btn btn-primary" type="submit">${sign_out_button}</button>
							</form>
					    </c:when>
					    <c:otherwise>
					        <a href="${pageContext.request.contextPath}/jsp/registr_page.jsp" class="btn btn-primary">${nav_sign_up_button}</a>
							<a href="${pageContext.request.contextPath}/jsp/sign_in.jsp" class="btn btn-primary">${nav_sign_in_button}</a>
					    </c:otherwise>
					</c:choose>
				</div>
			</div>
		</nav>

		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>