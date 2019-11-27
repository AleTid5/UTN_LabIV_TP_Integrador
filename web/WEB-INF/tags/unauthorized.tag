<%@tag description="Unauthorized Layout" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="scripts" fragment="true"%>

<% try {
	if (request.getDispatcherType().toString().equals("REQUEST")) throw new Exception();
} catch (Exception e) {
	response.setHeader("Location", request.getContextPath() + "/login");
	response.setHeader("Cache-Control", "no-cache");
	response.setStatus(301);
} %>

<html>
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76"
		  href="/App/Assets/img/apple-icon.png">
	<link rel="icon" type="image/png"
		  href="/Alejandro_Tidele_TP_Integrador/App/Assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>UTN | FRGP</title>
	<meta name="description" content="Sistema de alumnos y profesores">
	<link rel="stylesheet" type="text/css"
		  href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
	<link rel="stylesheet"
		  href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
	<link href="/Alejandro_Tidele_TP_Integrador/App/Assets/css/app.min.css"
		  rel="stylesheet" />
	<link href="/Alejandro_Tidele_TP_Integrador/App/Assets/css/app.css"
		  rel="stylesheet" />
</head>
<body class="off-canvas-sidebar">
<div id="app" class="wrapper wrapper-full-page">
	<div class="page-header login-page header-filter" filter-color="black"
		 style="background-image: url('/Alejandro_Tidele_TP_Integrador/App/Assets/img/login.jpg'); background-size: cover; background-position: top center;">
		<!--   you can change the color of the filter page using: data-color="blue | purple | green | orange | red | rose " -->
		<div class="container">
			<c:if test="${ message }">
				<div class="alert alert-${ message.getClassName() }">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<i class="material-icons">close</i>
					</button>
					<span>
						<b>${ message.getTitle() }</b><br>
						${ message.getBody() }
					</span>
				</div>
			</c:if>
			<div class="row">
				<div class="col-lg-4 col-md-6 col-sm-8 ml-auto mr-auto">
					<jsp:doBody />
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/Alejandro_Tidele_TP_Integrador/App/Assets/js/core/jquery.min.js"></script>
<script src="/Alejandro_Tidele_TP_Integrador/App/Assets/js/core/bootstrap-material-design.min.js"></script>
<script src="/Alejandro_Tidele_TP_Integrador/App/Assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
<script src="/Alejandro_Tidele_TP_Integrador/App/Assets/js/plugins/moment.min.js"></script>
<script src="/Alejandro_Tidele_TP_Integrador/App/Assets/js/plugins/sweetalert2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>
<script src="/Alejandro_Tidele_TP_Integrador/App/Assets/js/plugins/bootstrap-notify.js"></script>
<script src="/Alejandro_Tidele_TP_Integrador/App/Assets/js/app.min.js"></script>
<script src="/Alejandro_Tidele_TP_Integrador/App/Assets/js/components/login.js"></script>
</body>
</html>