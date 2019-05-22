<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1><spring:message code="string.editTitle" /></a></h1>

					<form action="editComputer" method="POST">
						<input type="hidden" name="id" value="${computer.id}" id="id" />
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<div class="form-group">
								<label for="computerName"> <spring:message code="string.computerName" /> </label> <input
									name="computerName" type="text" class="form-control" id="computerName"
									placeholder="<spring:message code="string.computerName" />" 
									value="${computer.name}" required>
								<span class="erreur">${erreurs['computerName']}</span>
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="string.introduced" /></label> <input
									name="introduced" type="date" class="form-control" id="introduced"
									value="${computer.introducedDate}"> <span
									class="erreur">${erreurs['introduced']}</span>
							</div>
							<div class="form-group">
								<label for="discontinued"> <spring:message code="string.discontinued" /></label> <input
									name="discontinued" type="date" class="form-control" id="discontinued"
									value="${computer.discontinuedDate}"> <span
									class="erreur">${erreurs['discontinued']}</span>
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="string.companyName" /></label> <select name="companyId"
									class="form-control" id="companyId">
									<option value="0">--</option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}"
											${computer.companyDTO.id==company.id? "selected='selected'":""}>
											${company.name}</option>
									</c:forEach>
								</select> <span class="erreur">${erreurs['companyId']}</span>
							</div>
							<p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Return</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/add.js"></script>
</body>
</html>