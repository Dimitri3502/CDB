<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
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
					<h1><spring:message code="string.addTitle" /></h1>
					<div id="errorMsg-container">
						<c:forEach items="${errorMsgs}" var="errorMsg">
							<div class="alert alert-danger">${errorMsg}</div>
						</c:forEach>
					</div>
					<form:form method="POST" action="addComputer"
						modelAttribute="computerDTOForm">
						<fieldset>
							<div class="form-group">
								<spring:bind path="computerName">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<form:label path="computerName"><spring:message code="string.computerName" /></form:label>
										<spring:message code="string.computerName" var="computerName" />
										<form:input type="text" path="computerName"
											class="form-control" id="computerName" 
											placeholder="${computerName}"
											></form:input>
										<form:errors path="computerName" cssClass="erreur" />
									</div>

								</spring:bind>
							</div>
							<div class="form-group">
								<form:label path="introduced"><spring:message code="string.introduced" /></form:label>
								<form:input type="date" path="introduced" class="form-control"
									id="introduced" placeholder="Introduced date"></form:input>
								<form:errors path="introduced" cssClass="erreur" />
							</div>
							<div class="form-group">
								<form:label path="discontinued"><spring:message code="string.discontinued" /> </form:label>
								<form:input type="date" path="discontinued" class="form-control"
									id="discontinued" placeholder="Discontinued date"></form:input>
								<form:errors path="discontinued" cssClass="erreur" />

							</div>
							<div class="form-group">
								<form:label path="companyId"><spring:message code="string.companyName" /></form:label>
								<form:select path="companyId" class="form-control"
									id="companyId">
									<option value="">--</option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</form:select>
								<form:errors path="companyId" cssClass="erreur" />
							</div>
							<div class="${resultat ? 'text-success' : 'text-danger'}">${message}</div>
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
	<!-- 	<script src="static/js/add.js"></script> -->
</body>
</html>