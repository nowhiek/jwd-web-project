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
		<script src="resources/js/ajax_script.js" type="text/javascript"></script>
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="locale/local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.admin_loclabel.information" var="information_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.data_base" var="data_base_label" />
		<fmt:message bundle="${loc}" key="local.admin_loclabel.add_data" var="add_data_label" />		
		<fmt:message bundle="${loc}" key="local.admin_label.all_matriculants" var="all_matriculants_label" />
		<fmt:message bundle="${loc}" key="local.admin_label.activate_matriculants" var="active_matriculants_label" />
		<fmt:message bundle="${loc}" key="local.admin_label.not_activate_matriculants" var="not_active_matriculants_label" />
		
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_faculties" var="show_all_faculties_message" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_specialties" var="show_all_specialties_message" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_types" var="show_all_types_message" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_plans" var="show_all_plans_message" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_users" var="show_all_users_message" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_matriculants" var="show_all_matriculants_message" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_subjects" var="show_all_subjects_message" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.show_all_subject_for_specialty" var="show_all_subject_for_specialty_message" />
		
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_new_faculty" var="add_new_faculty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_new_plan" var="add_new_plan_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_new_type" var="add_new_type_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_new_specialty" var="add_new_specialty_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_new_subject" var="add_new_subject_button" />
		<fmt:message bundle="${loc}" key="local.admin_locbutton.add_new_subject_for_specialty" var="add_new_subject_for_specialty_button" />
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
							<li class="nav-item mt-2">
								<button class="btn btn-primary btn-block" type="submit" id="subjetsTable">${show_all_subjects_message}</button>
							</li>
							<li class="nav-item mt-2">
								<button class="btn btn-primary btn-block" type="submit" id="specialtyHasSubjectTable">${show_all_subject_for_specialty_message}</button>
							</li>									
						</ul>
						<h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
							<span>${add_data_label}:</span>
						</h6>
						<ul class="nav flex-column">
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
							<li class="nav-item">
								<form action="${pageContext.request.contextPath}/Controller" method="POST">
									<input type="hidden" name="command" value="show_update_subject">
									<input type="hidden" name="id_subject" value="-1"/>
									<button class="btn btn-primary btn-block" type="submit">${add_new_subject_button}</button>
								</form>
							</li>
							<li class="nav-item">
								<form action="${pageContext.request.contextPath}/Controller" method="POST">
									<input type="hidden" name="command" value="show_update_specialty_has_subject">
									<input type="hidden" name="id_specialty_has_subject" value="-1"/>
									<button class="btn btn-primary btn-block" type="submit">${add_new_subject_for_specialty_button}</button>
								</form>
							</li>
						</ul>
					</div>
				</nav>
				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
					<div class="table-responsive">
						<div class="matriculants-choose none">	
							<input type="radio" id="matriculantsTableAll" value="all" name="app" checked >${all_matriculants_label}
							<input type="radio" id="matriculantsTableActive" value="true" name="app"/>${active_matriculants_label}
							<input type="radio" id="matriculantsTableNotActive" value="false" name="app"/>${not_active_matriculants_label}
						</div>
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
											<td>${user.getUserIsBanned()}</td>
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
		
		<script>
			setInterval(Check(), 1000);
			
			function Check() {
				if (document.querySelector('.alert') !== null) setTimeout(() => {document.querySelectorAll('.alert').forEach(e => e.parentNode.removeChild(e))}, 4000);
			}
		</script>	
		<script type="text/javascript" src="resources/js/matriculant_script.js" charset="utf-8"></script>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>