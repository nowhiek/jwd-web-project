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
		
		<fmt:message bundle="${loc}" key="local.message.user_already_exist" var="user_already_exist_message" />
		
	</head>
	<body>		
		<jsp:include page="jsp/part/navbar.jsp"/>
		<jsp:include page="jsp/part/header.jsp"/>
		
		<c:set var="count" value="0" scope="page"/>
		
		<main role="main" class="content">
			<div class="specialty">
				<div class="starter-template">
					<h2 style='text-align: center' >${specialties[0].getSpecialtyName()}</h2>

					<h6>Квалификация:</h6>
					<p>#{specialty.getSpecialtyProfession - добавить в базу какую профессию получишь}</p>
					<br>
					<h6>Сроки обучения:</h6>
					<c:forEach items="${type_studies}" var="type">
						<p>на основе "${type.getStudyName().toLowerCase()}" формы обучения - <i>${specialties[count].getCountYearStudy()}</i></p>
						<br>
						<c:set var="count" value="${count + 1}" scope="page"/>
					</c:forEach>
					
				</div>	
				<div style="background: url(resources/img/books.jpg) no-repeat; background-size: cover; background-position: center; " class="img-container"></div>			
			</div>
			
		</main>
		
		<jsp:include page="jsp/part/footer.jsp"/>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>