<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="resources/css/style.css" rel="stylesheet">
		
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"
			type="text/javascript"></script>
		<script src="resources/js/script.js" type="text/javascript"></script>
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="locale/local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.admin_loclabel.information" var="information_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.data_base" var="data_base_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.add_data" var="add_data_label" />
		
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_faculties" var="show_all_faculties_message" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_specialties" var="show_all_specialties_message" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_types" var="show_all_types_message" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_plans" var="show_all_plans_message" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_users" var="show_all_users_message" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_matriculants" var="show_all_matriculants_message" />
		
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_new_faculty" var="add_new_faculty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_new_plan" var="add_new_plan_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_new_type" var="add_new_type_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_new_specialty" var="add_new_specialty_button" />
	</head>
	<body>
		<jsp:include page="part/admin_navbar.jsp"/>
		<div class="container-fluid">
			<div class="row">
				<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
					<div class="sidebar-sticky pt-3">
						<h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
							<span>${information_label}:</span>
						</h6>
						<ul class="nav flex-column">
							<li class="nav-item mt-2">
								<button class="btn btn-primary btn-block" type="submit" id="usersTable">${show_all_users_message}</button>
							</li>
							<li class="nav-item mt-2">
								<button class="btn btn-primary btn-block" type="submit" id="matriculantsTable">${show_all_matriculants_message}</button>
							</li>
						</ul>
						<h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
							<span>${data_base_label}:</span>
						</h6>
						<ul class="nav flex-column mb-2">
							<li class="nav-item mt-2">
								<button class="btn btn-primary btn-block" type="submit" id="facultiesTable">${show_all_faculties_message}</button>
							</li>
							<li class="nav-item mt-2">
								<button class="btn btn-primary btn-block" type="submit" id="specialtiesTable">${show_all_specialties_message}</button>
							</li>
							<li class="nav-item mt-2">
								<button class="btn btn-primary btn-block" type="submit" id="countPlacesTable">${show_all_plans_message}</button>
							</li>
							<li class="nav-item mt-2">
								<button class="btn btn-primary btn-block" type="submit" id="typesTable">${show_all_types_message}</button>
							</li>						
						</ul>
						<h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
							<span>${add_data_label}:</span>
						</h6>
						<ul class="nav flex-column mb">
							<li class="nav-item mt-2">
								<form action="${pageContext.request.contextPath}/Controller" method="POST">
									<input type="hidden" name="command" value="show_update_faculty">
									<input type="hidden" name="id_faculty" value="-1"/>
									<button class="btn btn-primary btn-block" type="submit">${add_new_faculty_button}</button>
								</form>
							</li>
							<li class="nav-item">
								<form action="${pageContext.request.contextPath}/Controller" method="POST">
									<input type="hidden" name="command" value="show_update_specialty">
									<input type="hidden" name="id_specialty" value="-1"/>
									<button class="btn btn-primary btn-block" type="submit">${add_new_specialty_button}</button>
								</form>								
							</li>
							<li class="nav-item">
								<form action="${pageContext.request.contextPath}/Controller" method="POST">
									<input type="hidden" name="command" value="show_update_plan">
									<input type="hidden" name="id_plan" value="-1"/>
									<button class="btn btn-primary btn-block" type="submit">${add_new_plan_button}</button>
								</form>								
							</li>
							<li class="nav-item">
								<form action="${pageContext.request.contextPath}/Controller" method="POST">
									<input type="hidden" name="command" value="show_update_type">
									<input type="hidden" name="id_type" value="-1"/>
									<button class="btn btn-primary btn-block" type="submit">${add_new_type_button}</button>
								</form>
							</li>
						</ul>
					</div>
				</nav>
				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
					<div class="table-responsive">
						<table id="table_list" class="table table-striped table-sm">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Login</th>
									<th scope="col">Password</th>
									<th scope="col">Email</th>
									<th scope="col">Role</th>
									<th scope="col">Ban</th>
									<th scope="col">Edit</th>
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
											<td>
												<c:choose>
													<c:when test="${user.getUserRole() == 'USER'}">
														User
													</c:when>
													<c:otherwise>
														Admin
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${user.getUserIsBanned() == true}">
														Blocked
													</c:when>
													<c:otherwise>
														Unblocked
													</c:otherwise>
												</c:choose>	
											</td>
											<td>
												<a href="${pageContext.request.contextPath}/Controller?command=show_user_data&id_user=${user.getId()}">Edit</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>	
							</tbody>
						</table>
					</div>
				</main>
			</div>
		</div>	

		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>