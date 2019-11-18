<div class="sidebar" data-color="azure" data-background-color="black"
	data-image="/Alejandro_Tidele_TP_Integrador/App/Assets/img/sidebar-1.jpg">
	<div class="logo">
		<a href="http://www.creative-tim.com" class="simple-text logo-mini" style="width: 34px;">
			<div class="user mt-0">
				<div class="photo ml-0">
					<img
						src="/Alejandro_Tidele_TP_Integrador/App/Assets/img/utn.png" />
				</div>
			</div>
		</a> <a href="http://www.creative-tim.com" class="simple-text logo-normal">
			UTN FRGP </a>
	</div>
	<div class="sidebar-wrapper">
		<div class="user">
			<div class="photo">
				<img
					src="/Alejandro_Tidele_TP_Integrador/App/Assets/img/default-avatar.png" />
			</div>
			<div class="user-info">
				<a data-toggle="collapse" href="#collapseExample" class="username">
					<span> ${ name } ${ lastname } <b class="caret"></b>
				</span>
				</a>
				<div class="collapse" id="collapseExample">
					<ul class="nav">
						<li class="nav-item"><a class="nav-link" href="#"> <span
								class="sidebar-mini"> MP </span> <span class="sidebar-normal">
									My Profile </span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <span
								class="sidebar-mini"> EP </span> <span class="sidebar-normal">
									Edit Profile </span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <span
								class="sidebar-mini"> S </span> <span class="sidebar-normal">
									Settings </span>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
		<ul class="nav">
			<jsp:include page="./${ userTypeId == 1 ? \"routes_admin\" : \"routes_teacher\"}.jsp" />
		</ul>
	</div>
</div>
