<%@tag import="App.Services.SessionService"%>
<%@tag description="Authorized Layout" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="scripts" fragment="true"%>

<%
	try {
		SessionService.getParameter("name");
		if (request.getDispatcherType().toString().equals("REQUEST")) throw new Exception();
	} catch (Exception e) {
		response.setHeader("Location", request.getContextPath() + "/login");
		response.setHeader("Cache-Control", "no-cache");
		response.setStatus(301);
	}
%>

<html>
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76" href="/App/Assets/img/apple-icon.png">
<link rel="icon" type="image/png" href="${ assetsPath }/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>UTN | FRGP</title>
<meta name="description" content="Sistema de alumnos y profesores">
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<link href="${ assetsPath }/css/app.min.css" rel="stylesheet" />
<link href="${ assetsPath }/css/app.css" rel="stylesheet" />
<jsp:invoke fragment="head" />
</head>
<body>
	<div id="app" class="wrapper">
		<jsp:include page="/App/Views/Authorized/Components/sidebar.jsp" />
		<div class="main-panel">
			<jsp:include page="/App/Views/Authorized/Components/navbar.jsp" />
			<div class="content">
				<div class="content">
					<div class="container-fluid">
						<jsp:doBody />
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ assetsPath }/js/core/jquery.min.js"></script>
	<script src="${ assetsPath }/js/core/popper.min.js"></script>
	<script src="${ assetsPath }/js/core/bootstrap-material-design.min.js"></script>
	<script src="${ assetsPath }/js/plugins/perfect-scrollbar.jquery.min.js"></script>
	<script src="${ assetsPath }/js/plugins/moment.min.js"></script>
	<script src="${ assetsPath }/js/plugins/sweetalert2.js"></script>
	<script src="${ assetsPath }/js/plugins/bootstrap-selectpicker.js"></script>
	<script src="${ assetsPath }/js/plugins/bootstrap-datetimepicker.min.js"></script>
	<script src="${ assetsPath }/js/plugins/fullcalendar.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>
	<script src="${ assetsPath }/js/plugins/bootstrap-notify.js"></script>
	<script src="${ assetsPath }/js/app.min.js"></script>
	<script src="${ assetsPath }/js/components/sidebar.js"></script>
	<script src="${ assetsPath }/js/plugins/chartist.min.js"></script>
	<script src="${ assetsPath }/js/plugins/jquery.dataTables.min.js"></script>
	
  <script>
    $(document).ready(function() {
      $('#datatables').DataTable({
        "pagingType": "full_numbers",
        "lengthMenu": [
          [10, 25, 50, -1],
          [10, 25, 50, "All"]
        ],
        responsive: true,
        language: {
          search: "_INPUT_",
          searchPlaceholder: "Buscar",
        }
      });

      var table = $('#datatable').DataTable();

      // Edit record
      table.on('click', '.edit', function() {
        $tr = $(this).closest('tr');
        var data = table.row($tr).data();
        alert('You press on Row: ' + data[0] + ' ' + data[1] + ' ' + data[2] + '\'s row.');
      });

      // Delete a record
      table.on('click', '.remove', function(e) {
        $tr = $(this).closest('tr');
        table.row($tr).remove().draw();
        e.preventDefault();
      });

      //Like record
      table.on('click', '.like', function() {
        alert('You clicked on Like button');
      });
    });
  </script>
</body>
</html>