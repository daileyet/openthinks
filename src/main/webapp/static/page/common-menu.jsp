 <!-------------------------------------------------------------------------------------------->
	<!----------------------------------------Navbar Start---------------------------------------->
	<!-------------------------------------------------------------------------------------------->
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="navbar-header">
			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"> Openthinks <span class="label"><small>To
						Do</small></span>
			</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <i class="icon-user fa fa-user"></i><b
						class="caret"></b><span class="label">&nbsp;</span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="${pageContext.request.contextPath }/setting/index.htm"> Setting </a></li>
						<li><a href="#"> Administrator </a></li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath }/logout.htm"> Logout </a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<!--Navbar End-->
