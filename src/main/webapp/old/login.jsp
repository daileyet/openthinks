<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css"
	rel="stylesheet">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/static/image/favicon.ico" />


</head>
<body>

	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading">
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
				<div style="display: none" id="login-alert"
					class="alert alert-danger col-sm-12"></div>
				<div style="padding-top: 30px" class="panel-body">
					<form id="loginform" role="form" class="form-horizontal">

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
								<label> <input id="login-remember" type="checkbox"
									name="remember" value="1"> Remember me
								</label>
							</div>
						</div>


						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->

							<div class="col-sm-12 controls">
								<a id="btn-login" class="btn btn-success" href="javascript:void(0)">Login </a>
								<!-- <a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a> -->

							</div>
						</div>


						<div class="form-group">
							<div class="col-md-12 control">
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									Don't have an account! <a href="javascript:void(0)"
										onClick="$('#loginbox').hide(); $('#signupbox').show()">
										Sign Up Here </a>
								</div>
							</div>
						</div>
					</form>



				</div>
			</div>
		</div>
		<div id="signupbox" style="display: none; margin-top: 50px"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">
						<h4>
							<span class="label ">Open task - Sign Up</span>
						</h4>
					</div>
					<div
						style="float: right; font-size: 85%; position: relative; top: -10px">
						<a id="signinlink" href="javascript:void(0)"
							onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign
							In</a>
					</div>
				</div>
				<div class="panel-body">
					<form id="signupform" class="form-horizontal" role="form">

						<div id="signupalert" style="display: none"
							class="alert alert-danger">
						</div>
					 <div id="signupsuccess" style="display: none"
							class="alert alert-success">
						</div>


						<div class="form-group">
							<label for="email" class="col-md-3 control-label">Name/Email</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="email"
									placeholder="Name/Email Address">
							</div>
						</div>

						<!--   <div class="form-group">
                                    <label for="firstname" class="col-md-3 control-label">First Name</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="firstname" placeholder="First Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lastname" class="col-md-3 control-label">Last Name</label>
                                    <div class="col-md-9">
                                        <input type="text" class="form-control" name="lastname" placeholder="Last Name">
                                    </div>
                                </div> -->
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
								<a id="btn-signup" type="button" class="btn btn-success" href="javascript:void(0)"> <i
									class="icon-hand-right"></i> &nbsp Sign Up
								</a>
								<!--  <span style="margin-left:8px;">or</span>   -->
							</div>
						</div>

						<!--    <div style="border-top: 1px solid #999; padding-top:20px"  class="form-group">
                                    
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-fbsignup" type="button" class="btn btn-primary"><i class="icon-facebook"></i>   Sign Up with Facebook</button>
                                    </div>              -->
				</div>



				</form>
			</div>
		</div>




	</div>
	</div>


	<jsp:include page="static/page/common-footer.jsp" />
	<script>
		$(document)
				.ready(
						function() {

							$("#btn-login")
									.click(
											function() {
												var $loginForm = $("#loginform");
												var processResp = function(data) {
													if (data.type == "ERROR") {
								
														$("#login-alert").text(
																data.msg).css(
																"display",
																"block");
													} else if (data.type == "SUCESS") {
														$("#login-alert").css(
																"display",
																"none");
														window.location="${pageContext.request.contextPath}/index.htm";
													} else {
														console
																.log('unsupport type');
													}
												};
												$
														.post(
																"${pageContext.request.contextPath}/login.htm",
																$loginForm
																		.serialize(),
																processResp,
																"json");

											});

							$("#btn-signup")
									.click(
											function() {
												var $signForm = $("#signupform");
												var processResp = function(data) {
													if (data.type == "ERROR") {
														$("#signupalert").text(
																data.msg)
																.show();
													} else if (data.type == "SUCESS") {
														$("#signupsuccess")
																.text(data.msg)
																.show()
																.fadeOut(
																		3000,
																		function() {
																			$(
																					'#loginbox')
																					.show();
																			$(
																					'#signupbox')
																					.hide();
																		});

													} else {
													}
												};
												$
														.post(
																"${pageContext.request.contextPath}/register.htm",
																$signForm
																		.serialize(),
																processResp

																, "json");
											});

						});
	</script>
</body>

</html>