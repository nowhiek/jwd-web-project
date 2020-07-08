<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="resources/css/style.css" rel="stylesheet">
	</head>
	<body>
		<div class="row">
			<div class="col-md-8 table-container">
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">#</th>
							<th scope="col">Логин</th>
							<th scope="col">Пароль</th>
							<th scope="col">Email</th>
							<th scope="col">Роль</th>
							<th scope="col">Бан</th>
							<th scope="col">Ред.</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty users}">
							<c:forEach items="${users}" var="user">
								<tr>
									<th scope="row">${user.getId()}</th>
									<td>${user.getUserName()}</td>
									<td>${user.getUserPassword()}</td>
									<td>${user.getUserEmail()}</td>
									<td>${user.getUserRole()}</td>
									<td>${user.getUserIsBanned()}</td>
									<td>
										<form action="${pageContext.request.contextPath}/Controller" method="POST">
											<input type="hidden" name="id_user" value="${user.getId()}">
											<input type="hidden" name="command" value="show_user_data">
											<button class="" type="submit">✓</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</c:if>	
					</tbody>
				</table>
			</div>
			
			<div class="col-md-3 li-container">
				<ul> 
					<li>Пользователи</li>
					<li>Специальности</li>
					<li>Факультеты</li>
					<li>План набора</li>
					<li>Тип обучения</li>
				</ul>
			</div>
		</div>
		
		
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>