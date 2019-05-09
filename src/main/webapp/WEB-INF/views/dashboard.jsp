<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
			<h1 id="homeTitle">${totalNumber} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET"
						class="form-inline">
						<input type="hidden" name="order_by" value="${orderBy}" />
						<input type="hidden" name="order_direction" value="${orderDirection}" />
						<input type="search" id="searchbox" name="search" class="form-control" value="${search}" placeholder="Search name" /> 
						<input type="submit" id="searchsubmit" value="Filter by name" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="dashboard?pageid=${currentPageNumber}&numberPerPage=${numberPerPage}&search=${search}&order_by=${orderBy}&order_direction=${orderDirection}'" method="POST">
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
						<th>Computer name ${orderDirection}
							<c:choose>
								<c:when test="${orderBy == 'name'}">
									<c:choose>
										<c:when test="${orderDirection == 'asc'}">
											<a
												href="?pageid=0&numberPerPage=${numberPerPage}&search=${search}&order_by=name&order_direction=desc"><i
												class="fa fa-sort-down"></i></a>
										</c:when>
										<c:otherwise>
											<a
												href="?pageid=0&numberPerPage=${numberPerPage}&search=${search}&order_by=name&order_direction=asc"><i
												class="fa fa-sort-up"></i></a>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<a
										href="?pageid=0&numberPerPage=${numberPerPage}&search=${search}&order_by=name&order_direction=asc"><i
										class="fa fa-sort-up"></i></a>
								</c:otherwise>
							</c:choose>
						</th>
						<th>id</th>
						<th>Introduced date
						<c:choose>
							<c:when test="${orderBy == 'introduced'}">
									<c:choose>
										<c:when test="${orderDirection == 'asc'}">
											<a
												href="?pageid=0&numberPerPage=${numberPerPage}&search=${search}&order_by=introduced&order_direction=desc"><i
												class="fa fa-sort-down"></i></a>
										</c:when>
										<c:otherwise>
											<a
												href="?pageid=0&numberPerPage=${numberPerPage}&search=${search}&order_by=introduced&order_direction=asc"><i
												class="fa fa-sort-up"></i></a>
										</c:otherwise>
									</c:choose>
							</c:when>
							<c:otherwise>
								<a
									href="?pageid=0&numberPerPage=${numberPerPage}&search=${search}&order_by=introduced&order_direction=desc"><i
									class="fa fa-sort-down"></i></a>
							</c:otherwise>
						</c:choose>
						</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date
						<c:choose>
								<c:when test="${orderBy == 'discontinued'}">
									<c:choose>
										<c:when test="${orderDirection == 'asc'}">
											<a
												href="?pageid=0&numberPerPage=${numberPerPage}&search=${search}&order_by=discontinued&order_direction=desc"><i
												class="fa fa-sort-down"></i></a>
										</c:when>
										<c:otherwise>
											<a
												href="?pageid=0&numberPerPage=${numberPerPage}&search=${search}&order_by=discontinued&order_direction=asc"><i
												class="fa fa-sort-up"></i></a>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<a
										href="?pageid=0&numberPerPage=${numberPerPage}&search=${search}&order_by=discontinued&order_direction=desc"><i
										class="fa fa-sort-down"></i></a>
								</c:otherwise>
							</c:choose>
						</th>
						<!-- Table header for Company -->
						<th>Company
						<c:choose>
								<c:when test="${orderBy == 'company_name'}">
									<c:choose>
										<c:when test="${orderDirection == 'asc'}">
											<a
												href="?pageid=0&numberPerPage=${numberPerPage}&search=${search}&order_by=company_name&order_direction=desc"><i
												class="fa fa-sort-down"></i></a>
										</c:when>
										<c:otherwise>
											<a
												href="?pageid=0&numberPerPage=${numberPerPage}&search=${search}&order_by=company_name&order_direction=asc"><i
												class="fa fa-sort-up"></i></a>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<a
										href="?pageid=0&numberPerPage=${numberPerPage}&search=${search}&order_by=company_name&order_direction=desc"><i
										class="fa fa-sort-down"></i></a>
								</c:otherwise>
							</c:choose>
						</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="computer" items="${computers}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="editComputer?id=${computer.id}" onclick="">${computer.name}</a></td>
							<td>${computer.id}</td>
							<td>${computer.introducedDate}</td>
							<td>${computer.discontinuedDate}</td>
							<td>${computer.companyDTO.name}</td>

						</tr>
					</c:forEach>
				</tbody>

			</table>
			Page ${currentPageNumber} / ${nbPage}
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a
					href="?pageid=${currentPageNumber-10}&numberPerPage=${numberPerPage}&search=${search}&order_by=${orderBy}&order_direction=${orderDirection}""
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach var="page" items="${pageIds}">
					<li><a href="?pageid=${page}&&numberPerPage=${numberPerPage}&search=${search}&order_by=${orderBy}&order_direction=${orderDirection}"">${page}</a></li>
				</c:forEach>
				<li><a
					href="?pageid=${currentPageNumber+10}&numberPerPage=${numberPerPage}&search=${search}&order_by=${orderBy}&order_direction=${orderDirection}""
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<button
					onclick="location.href='?pageid=${currentPageNumber}&numberPerPage=10&search=${search}&order_by=${orderBy}&order_direction=${orderDirection}'"
					type="button" class="btn btn-default">10</button>
				<button
					onclick="location.href='?pageid=${currentPageNumber}&numberPerPage=50&search=${search}&order_by=${orderBy}&order_direction=${orderDirection}'"
					type="button" class="btn btn-default">50</button>
				<button
					onclick="location.href='?pageid=${currentPageNumber}&numberPerPage=100&search=${search}&order_by=${orderBy}&order_direction=${orderDirection}'"
					type="button" class="btn btn-default">100</button>
			</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>