<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="resources/css/style.css" rel="stylesheet">
		<link href="header.css" rel="stylesheet">
		<title>Start page</title>
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="locale/local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.locbutton.sign_in" var="sign_in_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.registration" var="registration_buton" />
		<fmt:message bundle="${loc}" key="local.loclabel.login" var="login_label" />
		<fmt:message bundle="${loc}" key="local.loclabel.password" var="password_label" />
		
	</head>
	<body>
		<jsp:include page="jsp/part/navbar.jsp"/>
		<jsp:include page="jsp/part/header.jsp"/>
		
		<main class="main main-content">
			<div class="container">
				<div class="row">
					<div class="col-3">
						<div class="card">
						  <div class="card-body">
						    <h5 class="card-title">Card title</h5>
						    <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
						    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						    <a href="#" class="card-link">Card link</a>
						    <a href="#" class="card-link">Another link</a>
						  </div>
						</div>
					</div>
					<div class="col-6">
						<div class="card text-center">
						  <div class="card-header">
						    <ul class="nav nav-tabs card-header-tabs">
						      <li class="nav-item">
						        <a class="nav-link active" href="#">Active</a>
						      </li>
						      <li class="nav-item">
						        <a class="nav-link" href="#">Link</a>
						      </li>
						      <li class="nav-item">
						        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
						      </li>
						    </ul>
						  </div>
						  <div class="card-body">
						    <h5 class="card-title">Special title treatment</h5>
						    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
						    <a href="#" class="btn btn-primary">Go somewhere</a>
						  </div>
						</div>
					</div>
					<div class="col-3">
						<div class="card" style="width: 18rem;">
						  <img src="..." class="card-img-top" alt="...">
						  <div class="card-body">
						    <h5 class="card-title">Card title</h5>
						    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
						  </div>
						  <ul class="list-group list-group-flush">
						    <li class="list-group-item">Cras justo odio</li>
						    <li class="list-group-item">Dapibus ac facilisis in</li>
						    <li class="list-group-item">Vestibulum at eros</li>
						  </ul>
						  <div class="card-body">
						    <a href="#" class="card-link">Card link</a>
						    <a href="#" class="card-link">Another link</a>
						  </div>
						</div>
					</div>
				</div>
			</div>
		</main>
		
		<jsp:include page="jsp/part/footer.jsp"/>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>
