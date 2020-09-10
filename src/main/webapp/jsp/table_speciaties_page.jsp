<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="${pageContext.request.contextPath}/resources/css/specialty.css" rel="stylesheet">	
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet">
		
		<fmt:setLocale value="${sessionScope.local}"/>
		<fmt:setBundle basename="locale/local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.empty_message.empty_specialties" var="empty_specialties_message" />		
	</head>
	<body>		
		<jsp:include page="part/navbar.jsp"/>
		<jsp:include page="part/header.jsp"/>
				
		<c:set var="count" value="0" scope="page"/>
				
		<div class="container" style="margin-top: 20px">
			<form action="${pageContext.request.contextPath}/Controller" method="POST">
			    <input type="hidden" name="command" value="show_all_specialties">
			    <button class="btn btn btn-primary" type="submit">Список</button>
		    </form>
			<form action="${pageContext.request.contextPath}/Controller" method="POST">
			    <input type="hidden" name="command" value="show_table_specialties">
			    <button class="btn btn btn-primary" type="submit">Таблица</button>
		    </form>
		    
			<c:choose>
				<c:when test="${not empty faculties}">
					<c:forEach items="${faculties}" var="f">
						<h1>${f.getFacultyName()}</h1>
						<table class="table">
							<thead class="thead-dark">
								<tr>
									<th scope="col">Специальность</th>
									<th scope="col">Квалификация</th>
									<c:forEach items="${t}" var="t">
										<th scope="col">${t.getStudyName()}</th>
										
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${specialties}" var="s">
									<c:set var="faculty_id" scope="page" value="${f.getId()}"/>
									<c:set var="plan_id" scope="page" value="${s.getIdPlan()}"/>
									<tr>
										<c:if test="${faculty_id == s.getIdFaculty()}">
											<td>${s.getSpecialtyName()}</td>
											<td>Добавлю</td>
											
											<c:choose>
												<c:when test="${plan_id != p[count].getId()}">
												<td>-</td>
													
												</c:when>
												<c:otherwise>
													<td>${p[count].getCountPlaces()}</td>													
												</c:otherwise>
											</c:choose>											
										</c:if>										
									</tr>
									<c:set var="count" value="${count + 1}" scope="page"/>
								</c:forEach>
							</tbody>
						</table>
						<c:set var="count" value="${count = 0}" scope="page"/>
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
		
		<script>
			setInterval(Check(), 1000);
			
			function Check() {
				if (document.querySelector('.alert') !== null) setTimeout(() => {document.querySelectorAll('.alert').forEach(e => e.parentNode.removeChild(e))}, 4000);
			}
		</script>
		<jsp:include page="part/footer.jsp"/>
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</body>
</html>