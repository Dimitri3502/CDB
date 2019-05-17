<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<a class="navbar-brand" href="dashboard"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form:form method="POST"
					 action="addComputer"
					 modelAttribute="computerDTOForm">
						<fieldset>
							<div class="form-group">
								<form:label path="computerName">Computer name</form:label>
								 <form:input type="text" path="computerName" class="form-control" id="computerName"
									 placeholder="Computer name" ></form:input>
									<form:errors path="computerName" />
							</div>
							<div class="form-group">
								<form:label path="introduced">Introduced date</form:label> <form:input
									type="date" path="introduced" class="form-control" id="introduced"
									placeholder="Introduced date"></form:input>
							</div>
							<div class="form-group">
								<form:label path="discontinued">Discontinued date</form:label> <form:input
									type="date" path="discontinued" class="form-control" id="discontinued"
									placeholder="Discontinued date"></form:input>
							</div>
							<div class="form-group">
								<form:label path="companyId">Company</form:label> <form:select
									path="companyId" class="form-control" id="companyId">
									<option value="">--</option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</form:select>
							</div>
							<p class="${status.error ? 'succes' : 'erreur'}">${resultat}</p>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary"></input>
							or <a href="dashboard" class="btn btn-default">Return</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/add.js"></script>
</body>
</html>