<!DOCTYPE html>
<!--[if lt IE 7 ]> <html class="ie6"> <![endif]-->
<!--[if IE 7 ]>    <html class="ie7"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Welcome Back</title>
<jsp:include page="static/page/common-head.jsp" />
</head>
<body>
	<input type="hidden" name="APP_BASE_PATH" id="APP_BASE_PATH"
		value="${pageContext.request.contextPath}" />
	<div class="container">

		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-inverse">
				<div class="panel-heading"
					style="border-bottom: 1px solid #888; padding-bottom: 15px; font-size: 85%">
					<div class="panel-title">
						<h4>
							<span class="label ">Open task - Sign In</span>
						</h4>
					</div>
					<div
						style="float: right; font-size: 80%; position: relative; top: -10px">
						<a href="#">Forgot password?</a>
					</div>
				</div>
				<div style="display: none; padding: 5px;" id="login-alert"
					class="alert alert-danger col-sm-12">
					<button type="button" class="close">&times;</button>
					<div></div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<form id="loginform" class="form-horizontal">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="login-username"
								type="text" class="form-control" name="username" value=""
								placeholder="username or email">
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
								type="password" class="form-control" name="password"
								placeholder="password">
						</div>

						<div class="input-group">
							<div class="checkbox">
								<input id="login-remember" type="checkbox" name="remember"
									value="1"> <label>Remember me </label>
							</div>
						</div>

						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->

							<div class="col-sm-12 controls">
								<a id="btn-login" class="btn btn-success"
									href="javascript:void(0)">Login </a>
								<!-- <a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a> -->

							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12 control">
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									Don't have an account! <a href="javascript:void(0)"
										id="login_switcher"> Sign Up Here </a>
								</div>
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
		<!-- ------------------------------------------------------------------------------------ -->
		<div id="signupbox" style="display: none; margin-top: 50px"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-inverse">
				<div class="panel-heading"
					style="border-bottom: 1px solid #888; padding-bottom: 15px; font-size: 85%">
					<div class="panel-title">
						<h4>
							<span class="label ">Open task - Sign Up</span>
						</h4>
					</div>
					<div
						style="float: right; font-size: 85%; position: relative; top: -10px">
						<a href="javascript:void(0)" id="signup_switcher">Sign In</a>
					</div>
				</div>
				<div id="signupalert" style="display: none; padding: 5px"
					class="alert alert-danger">
					<button type="button" class="close">&times;</button>
					<div>Error msg placeholder</div>
				</div>
				<div id="signupsuccess" style="display: none; padding: 5px"
					class="alert alert-success"></div>
				<div class="panel-body">
					<form id="signupform" class="form-horizontal">
						<div class="form-group">
							<label for="email" class="col-md-3 control-label">Name/Email</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="email"
									placeholder="Name/Email Address">
							</div>
						</div>

						<div class="form-group">
							<label for="password" class="col-md-3 control-label">Password</label>
							<div class="col-md-9">
								<input type="password" class="form-control" name="passwd"
									placeholder="Password">
							</div>
						</div>

						<div class="form-group">
							<label for="password" class="col-md-3 control-label">Re
								Password</label>
							<div class="col-md-9">
								<input type="password" class="form-control" name="passwd2"
									placeholder="Re-enter Password">
							</div>
						</div>

						<!--    <div class="form-group">
							<label for="icode" class="col-md-3 control-label">Invitation Code</label>
							<div class="col-md-9">
							<input type="text" class="form-control" name="icode" placeholder="">
							</div>
							</div> -->

						<div class="form-group">
							<!-- Button -->
							<div class="col-md-offset-3 col-md-9">
								<a id="btn-signup" type="button" class="btn btn-success"
									href="javascript:void(0)"> <i class="icon-hand-right"></i>
									&nbsp; Sign Up
								</a>
							</div>
						</div>

					</form>
				</div>
				<!-- end panel body -->


			</div>
			<!-- end box -->
		</div>

	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<script src="static/js/controller/LoginCtrl.js"></script>
	
</body>

</html>