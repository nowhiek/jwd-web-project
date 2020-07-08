<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="${pageContext.request.contextPath}/resources/css/form-validation.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet">
		
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"
			type="text/javascript"></script>
		<script src="resources/js/script.js" type="text/javascript"></script>
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="locale/local" var="loc" />

		<fmt:message bundle="${loc}" key="local.message.unsucces_update_user_detail" var="unsucces_update_user_detail_message" />
	</head>
	<body>		
		<jsp:include page="part/navbar.jsp"/>
		<jsp:include page="part/header.jsp"/>
		
		<div class="container">
			<div class="row">
				<div class="col-md-8 order-md-1">					
					<c:if test="${not empty invalid_passport}">
						<div class="alert alert-warning" role="alert">
							<c:out value="${invalid_passport_message}"></c:out>
						</div>
					</c:if>	
				</div>
			</div>
			
			
			<form action="Controller" method="POST">
				<div class="row">
					<div class="col-md-8 order-md-1">
						<h2 class="mb-3 mt-3">Заявление</h2>
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="formStudy">Выберите форму обучения:</label>
									<select class="custom-select d-block w-100" id="formStudy" name="type_study">
										<c:forEach items="${types}" var="item">
											<option value="${item.getStudyName()}">${item.getStudyName()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="formStudy">Выберите факультет:</label>
									<select class="custom-select d-block w-100" id="formFaculty" name="faculty">
										<c:forEach items="${faculties}" var="item">
											<option value="${item.getFacultyName()}">${item.getFacultyName()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="formStudy">Выберите специальность:</label>
									<select class="custom-select d-block w-100" id="formSpecialty" name="specialty">
										<c:forEach items="${specialties}" var="item">
											<option value="${item.getSpecialtyName()}">${item.getSpecialtyName()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="formStudy">Введите аттестат:</label>
									<input type="text" class="form-control" id="certificateId" value="${certificate}" name="certificate"/>
								</div>
							</div>
					
						<input type="hidden" name="command" value="add_matriculant">
						<button class="btn btn-primary btn-lg btn-block" type="submit">Подвердить</button>
					</div>
				</div>
			</form>
		</div>
		
		
		<jsp:include page="part/footer.jsp"/>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>