<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="resources/css/style.css" rel="stylesheet">
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="locale/local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.loclink.back" var="back_link" />
	
		<fmt:message bundle="${loc}" key="local.admin_loclabel.no_information_about_matriculants" var="no_information_about_matriculants_label" />
		
		<fmt:message bundle="${loc}" key="local.admin_message.empty_specialty" var="empty_specialty_message" />
		
	</head>
	<body>
		<jsp:include page="part/admin_navbar.jsp"/>
		<div class="container-fluid">
			<div class="row">
				<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
					<div class="sidebar-sticky pt-3">
						<h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
							<span>Специальности</span>
						</h6>
						<ul class="nav flex-column">
							<c:forEach items="${specialties}" var="spec">
								<li class="nav-item mt-2">
									<button class="btn btn-primary btn-block" type="submit" id="usersTable">${spec.getSpecialtyName()}</button>
								</li>
							</c:forEach>
						</ul>
					</div>
				</nav>
				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
					<h4>${c_specialty.getSpecialtyName()}</h4>
					<div class="table-responsive">
						<table id="table_list" class="table table-striped table-sm">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th>Name</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty users_detail}">
									<c:set var="count" value="1" scope="page"/>
									<c:forEach items="${users_detail}" var="detail">
										<tr>
											<th scope="row">${count}</th>
											<td>${detail.getFirstName()} ${detail.getSecondName()}</td>		
											<td></td>								
										</tr>
										<c:set var="count" value="${count + 1}" scope="page"/>
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
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>