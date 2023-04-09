<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dz App</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Trirong">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>

<style type="text/css">
div {
	margin: 15px;
}

body {
	font-family: "Sofia", sans-serif;
}
</style>

<!-- <script type="text/javascript">

/* loadEmp(); */

function loadPage(){
	document.getElementById('pg').click();
}

function loadEmp(){
    location.href = "./employee?currentPage=1";
}
</script> -->
</head>
<body>
	<div class="container-fluid">

		<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark" style="border-left: 116px solid; border-left-color: darkslategray;height: 70px ">
			<div class="container-fluid">
				<a class="navbar-brand" href="#" style="margin-left: -110px; font-family: cursive; font-size: 24px; color: white;">DZ APP</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					    <li class="nav-item"><a class="nav-link" aria-current="page" href="addEmployee.jsp">Add Employee</a></li>
						<li class="nav-item"><a class="nav-link" href="">Upload Employee Census </a></li>
<!-- 						<li class="nav-item"><a class="nav-link" aria-current="page" href="addEmployee.jsp">Add Employee</a></li> -->
						<li hidden="" id="pg" class="nav-item"><a class="nav-link" href="employee?currentPage=1">test</a></li>
					</ul>
					<!-- <form class="d-flex">
						<input class="form-control me-2" type="search"
							placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</form> -->
				</div>
			</div>
		</nav>

	</div>
</body>
</html>
