<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="css/style.css" rel="stylesheet">
	
		<fmt:setLocale value="${sessionScope.local}"/>
		<fmt:setBundle basename="locale/local" var="loc" />
	
		<fmt:message bundle="${loc}" key="local.message.success_ban_user" var="success_ban_user_message" />
		<fmt:message bundle="${loc}" key="local.message.unsuccess_ban_user" var="unsuccess_ban_user_message" />
	</head>
	<body>
		
		<div class="container">
			<c:if test="${not empty succes_ban_user}">
				<div class="alert alert-warning" role="alert">
					<c:out value="${success_ban_user_message}"></c:out>
				</div>
			</c:if>	
			<c:if test="${not empty unsucces_ban_user}">
				<div class="alert alert-warning" role="alert">
					<c:out value="${unsuccess_ban_user_message}"></c:out>
				</div>
			</c:if>	
			<div class="py-5 text-center"></div>
			<form action="${pageContext.request.contextPath}/Controller" method="POST">
				<div class="row">
					<div class="col-md-4 order-md-2 mb-4">
						<h4 class="mb-3">Пользователь</h4>
						<div class="col-md-8">
							<label for="login">Логин:</label>
							<input type="text" class="form-control" id="login" value="${user.getUserName()}">
						</div>
						
						<div class="col-md-8">
							<label for="password">Пароль:</label>
							<input type="text" class="form-control" id="password" value="${user.getUserPassword()}">
						</div>
						
						<div class="col-md-8">
							<label for="role">Роль:</label>
							<input type="text" class="form-control" id="role" value="${user.getUserRole()}">
						</div>
						
						<div class="col-md-8">
							<label for="ban">Блокировка:</label>
							<input type="text" class="form-control" id="ban" value="${user.getUserIsBanned()}">
						</div>
					</div>
					<div class="col-md-8 order-md-1">
						<h4 class="mb-3">Данные пользователя</h4>
					
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="firstName">Имя:</label>
								<input type="text" class="form-control" id="firstName" value="${user_detail.getFirstName()}">
							</div>
							<div class="col-md-6 mb-3">
								<label for="secondName">Фамилия:</label>
								<input type="text" class="form-control" id="secondName" value="${user_detail.getSecondName()}">
							</div>
						</div>
						
						<div class="mb-3">
							<label for="email">Email:</label>
							<input type="email" class="form-control" id="email" value="${user.getUserEmail()}">
						</div>
						
						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="sex">Пол:</label>
								<input type="text" class="form-control" id="sex" value="${user_detail.getSex()}">
							</div>
							<div class="col-md-6 mb-3">
								<label for="birthday">Дата рождения:</label>
								<input type="text" class="form-control" id="birthday" value="${user_detail.getBirthday()}">
							</div>
						</div>
						
						<h4 class="mb-3">Паспортные данные</h4>
						<div class="row">
							<div class="col-md-3 mb-3">
								<label for="serialPassport">Серия:</label>
								<input type="text" class="form-control" id="serialPassport" value="${user_detail.getSerialPassport()}">
							</div>
							<div class="col-md-9 mb-3">
								<label for="numberPassport">Паспорт:</label>
								<input type="text" class="form-control" id="numberPassport" value="${user_detail.getNumberPassport()}">
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" name="command" value="show_user_data">
				<button class="btn btn-primary " type="submit">Подвердить</button>
			</form>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-4 order-md-2 mb-4">
					<form action="${pageContext.request.contextPath}/Controller" method="POST">
						<input type="hidden" name="command" value="block_user">
						<input type="hidden" name="id_user" value="${user.getId()}">
						<button class="btn btn-primary " type="submit">Заблокировать</button>
					</form>	
				</div>	
			</div>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>