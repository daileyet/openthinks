<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if HTML5]><![endif]-->
<!--[if lt IE 7 ]> <html class="no-js lt-ie9 lt-ie8 lt-ie7 aLogin" lang="en"> <![endif]-->
<!--[if IE 7 ]>    <html class="no-js lt-ie9 lt-ie8 ie7 aLogin" lang="en"> <![endif]-->
<!--[if IE 8 ]>    <html class="no-js lt-ie9 ie8 aLogin" lang="en"> <![endif]-->
<!--[if IE 9 ]>    <html class="no-js ie9 aLogin" lang="en"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html style="visibility: visible;" class="no-js aLogin" lang="en">
<!--<![endif]-->
<head>
<!--[if !HTML5]>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<![endif]-->
<meta charset="UTF-8">
<title>Open Tasks Login</title>
<link rel="icon" href="https://apex.oraclecorp.com/i/favicon.ico"
	type="image/x-icon">
<link rel="shortcut icon"
	href="https://apex.oraclecorp.com/i/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/apex.css"
	type="text/css">
<!--[if IE]><link rel="stylesheet" href="/i/css/apex_ie.min.css?v=4.2.3.00.07" type="text/css" /><![endif]-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/jquery-ui.css"
	type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/apex_builder.css"
	type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/apex_ui.css"
	type="text/css">
<!--[if IE]><link rel="stylesheet" href="/i/css/apex_builder_ie.min.css?v=4.2.3.00.07" type="text/css" /><![endif]-->
<!--[if IE 6]><link rel="stylesheet" href="/i/css/apex_builder_ie6.min.css?v=4.2.3.00.07" type="text/css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="/i/css/apex_builder_ie7.min.css?v=4.2.3.00.07" type="text/css" /><![endif]-->
<script type="text/javascript">
	var apex_img_dir = "/i/", htmldb_Img_Dir = apex_img_dir;
</script>
<script src="${pageContext.request.contextPath}/static/login/desktop_all.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/login/legacy.js" type="text/javascript"></script>
<style>
html {
	visibility: hidden;
}
</style>
<script type="text/javascript">
	apex.security.framebreaker("D");
</script>
<script src="${pageContext.request.contextPath}/static/login/widget.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/login/builder.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Cache-Control" content="no-cache">
<!-- http_user_agent: Mozilla&#x2F;5.0 (Windows NT 6.1; WOW64; rv:22.0) Gecko&#x2F;20100101 Firefox&#x2F;22.0 -->
</head>
<body class="aLogin">
	<noscript></noscript>
	<div id="error_info" style="color:red;font-size:12px;padding:2px;">${webError['login-error'] }${webError['authorize-error'] }</div>
	<form action="${pageContext.request.contextPath}/login.htm" method="post" name="wwv_flow"
		id="wwvFlowForm" novalidate="" autocomplete="off">
		<input name="p_flow_id" value="4550" id="pFlowId" type="hidden"><input
			name="p_flow_step_id" value="1" id="pFlowStepId" type="hidden"><input
			name="p_instance" value="1088509888618" id="pInstance" type="hidden"><input
			name="p_page_submission_id" value="12704282649223"
			id="pPageSubmissionId" type="hidden"><input name="p_request"
			value="" id="pRequest" type="hidden">
		<div class="aHeader">
			<a href="#" style="text-decoration:none;color:white">Open-thinks - Open Tasks</a>
		</div>
		<div class="aLoginOuter">
			<div class="aLoginInner">
				<div class="aLoginContainer">

					<div class="aLogin aRegion">
						<div class="aLoginHeader">
							<h1>Open Tasks Login</h1>
						</div>
						<div class="aLoginBody">
							<div class="aLoginIcon">
								<img src="${pageContext.request.contextPath}/static/login/apex-db-apps.png" alt=""
									height="200" border="0" width="240">
								<p>
									Open-thinks Open Tasks. <a href="#">Learn how to get
										started.</a>
								</p>
							</div>
							<div class="aLoginForm">
								<input name="p_arg_names" value="232291604030150875"
									type="hidden"><input name="p_t01"
									id="P1_RESET_PASSWORD_LABEL" value="Reset Password"
									type="hidden"><input name="p_arg_checksums"
									value="232291604030150875_A5703F5C0DB258C83023EDCF63AAC889"
									type="hidden">
								<table id="apex_layout_30251320176465048" class="formlayout"
									summary="" role="presentation" border="0">
									<tbody>
										<tr>
											<td align="left"><label for="F4550_P1_USERNAME"
												class="aLabel aOptional"><a
													href="javascript:popupFieldHelp('30251012844458645','1088509888618','Close')"
													tabindex="999">Username</a></label><br> <input
												name="p_arg_names" value="30251012844458645" type="hidden"><input
												id="F4550_P1_USERNAME" name="p_t03" class="login" value=""
												size="40" maxlength="2000" type="text"></td>
										</tr>
										<tr>
											<td align="left"><label for="F4550_P1_PASSWORD"
												class="aLabel aOptional"><a
													href="javascript:popupFieldHelp('30251520608467092','1088509888618','Close')"
													tabindex="999">Password</a></label><br> <input
												name="p_arg_names" value="30251520608467092" type="hidden"><input
												name="p_t04" size="40" maxlength="2000" value=""
												id="F4550_P1_PASSWORD" autocomplete="off" class="login"
												onkeypress="return apex.submit({request:'F4550_P1_PASSWORD',submitIfEnter:event})"
												type="password"><small><a
													href="https://apex.oraclecorp.com/pls/apex/f?p=4550:7:1088509888618"
													tabindex="999">Reset Password</a></small></td>
										</tr>
										<input name="p_arg_names" value="1778434620188603210"
											type="hidden">
										<input name="p_t05" id="P1_NEXT_APP" value="" type="hidden">
										<input name="p_arg_names" value="1778434823687603211"
											type="hidden">
										<input name="p_t06" id="P1_NEXT_PAGE" value="" type="hidden">
										<input name="p_arg_names" value="1778435028760603211"
											type="hidden">
										<input name="p_t07" id="P1_NEXT_ITEMS" value="" type="hidden">
										<input name="p_arg_names" value="1778435208234603211"
											type="hidden">
										<input name="p_t08" id="P1_NEXT_VALUES" value="" type="hidden">
									</tbody>
								</table>
								<div class="aLoginButtonContainer">
									<button class="aButton hotButton"
										onclick="javascript:apex.submit('LOGIN_BUTTON');"
										id="B232005500580944564" type="button">
										<span>Login to Open Task</span>
									</button>
								</div>
							</div>
						</div>
						<div class="aLoginSubBody socialNetworking"
							id="R327446700231264735">
						</div>
					</div>
				</div>
			</div>
		</div>
		<input name="p_md5_checksum" value="" type="hidden"><input
			name="p_page_checksum" value="8602A401D26CD30FD7B20AC021340E20"
			id="pPageChecksum" type="hidden">
	</form>


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/login/widget_002.js"></script>
	<script type="text/javascript">
		apex
				.jQuery(document)
				.ready(
						function() {
							(function() {
								var lTimeoutField = document
										.getElementById("apex_login_throttle_sec"), lTimeout = lTimeoutField ? +lTimeoutField.innerHTML
										: 0;
								if (lTimeout) {
									var lTimer = window
											.setInterval(
													function() {
														if (lTimeout > 0) {
															lTimeoutField.innerHTML = lTimeout;
															lTimeout--;
														} else {
															window
																	.clearInterval(lTimer);
															var lDiv = document
																	.getElementById("apex_login_throttle_div");
															if (lDiv) {
																lDiv.parentNode
																		.removeChild(lDiv);
																return true;
															}
														}
													}, 1000);
								}
							})();
							(function() {
								apex.widget.report.init("R59842604093647546", {
									"styleChecked" : "#dddddd",
									"internalRegionId" : "59842604093647546"
								});
							})();

							apex.item('F4550_P1_COMPANY').setFocus();
						});
	</script>

</body>
</html>