<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
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
					<h1>Edit Computer</h1>

					<form action="editComputer" method="POST">
						<input type="hidden" name="id" value="${computer.id}" id="id" />
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									name="computerName" type="text" class="form-control" id="computerName"
									placeholder="Computer name" value="${computer.name}" required>
								<span class="erreur">${erreurs['computerName']}</span>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									name="introduced" type="date" class="form-control" id="introduced"
									placeholder="Introduced date"
									value="${computer.introducedDate}"> <span
									class="erreur">${erreurs['introduced']}</span>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									name="discontinued" type="date" class="form-control" id="discontinued"
									placeholder="Discontinued date"
									value="${computer.discontinuedDate}"> <span
									class="erreur">${erreurs['discontinued']}</span>
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select name="companyId"
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
	<script src="js/jquery.min.js"></script>
	<script src="js/add.js"></script>
</body>
</html>