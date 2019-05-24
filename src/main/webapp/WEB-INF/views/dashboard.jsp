<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.excilys.training.pagination.Page" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
				<div class="pull-right">
				<a class="navbar-brand" href="?lang=us">US</a>|<a class="navbar-brand" href="?lang=fr">FR</a>
				</div>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${totalNumber}
				<spring:message code="string.computersFound" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET"
						class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control" value="${page.search}"
							placeholder="<spring:message code="string.chooseHere" />" /> <input
							type="submit" id="searchsubmit"
							value="<spring:message code="string.filter" />"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">
						<spring:message code="string.add" />
					</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"> <spring:message
							code="string.edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="dashboard" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th class="th-sm"><spring:message code="string.computerName" />
							<a title="Order by name ascendant"
							<c:url value="/dashboard" var="url">
 								<c:param name="orderBy" value="computerName"/>
 								<c:param name="orderDirection" value="asc"/>
							</c:url>
							href="${url}"> <em class="fa fa-arrow-down"></em></a> <a
							title="Order by name descendant"
							<c:url value="/dashboard" var="url">
 								<c:param name="orderBy" value="computerName"/>
 								<c:param name="orderDirection" value="desc"/>
							</c:url>
							href="${url}"> <em class="fa fa-arrow-up"></em></a></th>
						<th class="th-sm">id <a title="Order by id ascendant"
							<c:url value="/dashboard" var="url">
 								<c:param name="orderBy" value="id"/>
 								<c:param name="orderDirection" value="asc"/>
							</c:url>
							href="${url}"> <em class="fa fa-arrow-down"></em></a> <a
							title="Order by id descendant"
							<c:url value="/dashboard" var="url">
 								<c:param name="orderBy" value="id"/>
 								<c:param name="orderDirection" value="desc"/>
							</c:url>
							href="${url}"> <em class="fa fa-arrow-up"></em></a>
						</th>
						<th class="th-sm"><spring:message code="string.introduced" />
							<a title="Order by introduced ascendant"
							<c:url value="/dashboard" var="url">
 								<c:param name="orderBy" value="introduced"/>
 								<c:param name="orderDirection" value="asc"/>
							</c:url>
							href="${url}"> <em class="fa fa-arrow-down"></em></a> <a
							title="Order by introduced descendant"
							<c:url value="/dashboard" var="url">
 								<c:param name="orderBy" value="introduced"/>
 								<c:param name="orderDirection" value="desc"/>
							</c:url>
							href="${url}"> <em class="fa fa-arrow-up"></em></a></th>
						<!-- Table header for Discontinued Date -->
						<th class="th-sm"><spring:message code="string.discontinued" />
							<a title="Order by discontinued ascendant"
							<c:url value="/dashboard" var="url">
 								<c:param name="orderBy" value="discontinued"/>
 								<c:param name="orderDirection" value="asc"/>
							</c:url>
							href="${url}"> <em class="fa fa-arrow-down"></em></a> <a
							title="Order by discontinued descendant"
							<c:url value="/dashboard" var="url">
 								<c:param name="orderBy" value="discontinued"/>
 								<c:param name="orderDirection" value="desc"/>
							</c:url>
							href="${url}"> <em class="fa fa-arrow-up"></em></a></th>

						<!-- Table header for Company -->
						<th class="th-sm"><spring:message code="string.companyName" />
							<a title="Order by company ascendant"
							<c:url value="/dashboard" var="url">
 								<c:param name="orderBy" value="companyName"/>
 								<c:param name="orderDirection" value="asc"/>
							</c:url>
							href="${url}"> <em class="fa fa-arrow-down"></em></a> <a
							title="Order by company descendant"
							<c:url value="/dashboard" var="url">
 								<c:param name="orderBy" value="companyName"/>
 								<c:param name="orderDirection" value="desc"/>
							</c:url>
							href="${url}"> <em class="fa fa-arrow-up"></em></a></th>
					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="computer" items="${computers}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a 
							<c:url value="/editComputer" var="url">
 								<c:param name="id" value="${computer.id}"/>
							</c:url>
							href="${url}">${computer.name}</a></td>
							<td>${computer.id}</td>
							<td>${computer.introducedDate}</td>
							<td>${computer.discontinuedDate}</td>
							<td>${computer.companyDTO.name}</td>

						</tr>
					</c:forEach>
				</tbody>

			</table>
			Page ${page.currentPageNumber} / ${page.nbPage}
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a
					<c:url value="/dashboard" var="url">
						<c:param name="pageNumberRequest" value="${page.currentPageNumber - Page.MAX_PAGE_CHOICE}"/>
					</c:url>
					href="${url}" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach var="page" items="${page.pageIds}">
					<li><a
						<c:url value="/dashboard" var="url">
								<c:param name="pageNumberRequest" value="${page}"/>
							</c:url>
						href="${url}">${page} </a></li>
				</c:forEach>
				<li><a
					<c:url value="/dashboard" var="url">
						<c:param name="pageNumberRequest" value="${page.currentPageNumber + Page.MAX_PAGE_CHOICE}"/>
					</c:url>
					href="${url}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>

			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a class="btn btn-default"
					<c:url value="/dashboard" var="url">
						<c:param name="limit" value="10"/>
					</c:url>
					href="${url}">10</a> <a class="btn btn-default"
					<c:url value="/dashboard" var="url">
						<c:param name="limit" value="50"/>
					</c:url>
					href="${url}">50</a> <a class="btn btn-default"
					<c:url value="/dashboard" var="url">
						<c:param name="limit" value="100"/>
					</c:url>
					href="${url}">100</a>
			</div>
		</div>

	</footer>
		<script type="text/javascript">
		var edit = "<spring:message code="string.js.edit"/>";
		var view = "<spring:message code="string.js.view"/>";
		var alertMsg = "<spring:message code="string.js.alertMsg"/>";
	</script>
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/dashboard.js"></script>

</body>
</html>