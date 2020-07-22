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
	</head>
	<body>		
		<jsp:include page="jsp/part/navbar.jsp"/>
		<jsp:include page="jsp/part/header.jsp"/>
		
		<c:set var="count" value="0" scope="page"/>
		
		<div class="container" style="margin-top: 20px">
			<div class="row">
				<div class="col-md-6 mb-3">		
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
					    <input type="hidden" name="command" value="show_all_specialties">
					    <button class="btn btn btn-primary" type="submit">${list_button}</button>
				    </form>
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
					    <input type="hidden" name="command" value="show_table_specialties">
					    <button class="btn btn btn-primary" type="submit">${table_button}</button>
				    </form>
				</div>
			</div>
			
			<c:choose>
				<c:when test="${not empty specialties}">
					<c:forEach items="${specialties}" var="spec">
						<div class="item-specialty">
							<div style="background: url(resources/img/books.jpg) no-repeat; background-size: cover; background-position: center; " class="img-specialty"></div>
							<div class="discription-specialty">
								<div class="text-discription">
								<h5>${spec.getSpecialtyName()}</h5>
								</div>
								<div class="content-discription">
									<p>Форма обучения : ${types[count].getStudyName()}</p>
									<p>Сроки обучения : ${spec.getCountYearStudy()} года/лет</p>
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
						<div class="alert alert-warning" role="alert">
							<c:out value="${empty_specialties_message}"></c:out>
						</div>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
		
		<jsp:include page="jsp/part/footer.jsp"/>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>