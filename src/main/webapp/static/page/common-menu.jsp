 <div id="top-container"><nav class="navbar navbar-default" role="navigation" >
		  <!-- Brand and toggle get grouped for better mobile display -->
		  <div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			  <span class="sr-only">Toggle navigation</span>
			  <span class="icon-bar"></span>
			  <span class="icon-bar"></span>
			  <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath }/index.htm">OpenThinks</a>
		  </div>

		  <!-- Collect the nav links, forms, and other content for toggling -->
		  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
			  <li class='${param.menuActive==1?"active":""}' data-number="1"><a href="${pageContext.request.contextPath}/task/main.htm">Task</a></li>
			  <li data-number="2" class='${param.menuActive==2?"active":""}'><a href="${pageContext.request.contextPath}/blog/main.htm">Blog</a></li>
			  <li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
				<ul class="dropdown-menu">
				  <li><a href="#">Action</a></li>
				  <li><a href="#">Another action</a></li>
				  <li><a href="#">Something else here</a></li>
				  <li class="divider"></li>
				  <li><a href="#">Separated link</a></li>
				  <li class="divider"></li>
				  <li><a href="#">One more separated link</a></li>
				</ul>
			  </li>
			</ul>
			<form class="navbar-form navbar-left" role="search">
			  <div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			  </div>
			  <button type="submit" class="btn btn-default">Submit</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
			  <li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-user"></i>&nbsp;${authorize_user} <b class="caret"></b></a>
				<ul class="dropdown-menu">
				  <li><a href="${pageContext.request.contextPath }/setting/index.htm">Setting</a></li>
				  <li><a href="#">Administrator</a></li>
				  <li class="divider"></li>
				  <li><a href="${pageContext.request.contextPath }/logout.htm">Logout</a></li>
				</ul>
			  </li>
			</ul>
		  </div><!-- /.navbar-collapse -->
	</nav></div> 