<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!--[if lt IE 7 ]> <html class="ie6"> <![endif]-->
<!--[if IE 7 ]>    <html class="ie7"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if !IE]><!-->



<html>
<!--<![endif]-->
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="en:locale" content="en" />
<meta name="gwt:property" content="locale=en" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/static/image/favicon.ico" />
<title>Openthinks-Tasks</title>
<jsp:include page="static/page/common-head.jsp" />
</head>
<body class="" style="min-height: 660px;">

	<c:if test="${authorize_user==null }">
		<c:redirect url="/login.jsp"></c:redirect>
	</c:if>

	<jsp:include page="static/page/common-menu.jsp">
		<jsp:param name="menuActive" value="1" />
	</jsp:include>
		<div id="main-container" class="row" >
			<div id="main-left" class="col-md-3">
				<iframe id="task-list-container"
					src="${pageContext.request.contextPath}/task/list/refresh.htm"></iframe>
			</div>
			<div id="main-right" class="col-md-9">
				<iframe id="task-editor-container"
					src="${pageContext.request.contextPath}/task/edit.htm"></iframe>
			</div>
		</div>
	<jsp:include page="static/page/common-footer.jsp" />

	<script>
	/*
		$(document).ready(
				function() {
					var win_height = $(window).height();

					var topbar_height = $("#top-container").height();
					var main_height = win_height - topbar_height - 30;
					$("#main-container").css("height", main_height + "px");
					var right_width = $("#main-container").width()
							- $("#main-left").width()
							- $("#main-divider").width() - 5;
					$("#main-right").css("width", right_width + "px");
				});

		$(window).resize(
				function() {
					var win_height = $(window).height();
					var topbar_height = $("#top-container").height();
					var main_height = win_height - topbar_height - 30;
					$("#main-container").css("height", main_height + "px");
					var right_width = $("#main-container").width()
							- $("#main-left").width()
							- $("#main-divider").width() - 5;
					$("#main-right").css("width", right_width + "px");
				});
		*/
		
		
		$(document).ready(
				function() {
					var win_height = $(window).height();

					var topbar_height = $("#top-container").height();
					var main_height = win_height - topbar_height - 30;
					$("#task-list-container").css("height", main_height + "px");
					$("#task-editor-container").css("height", main_height + "px");
				});

		$(window).resize(
				function() {
					var win_height = $(window).height();

					var topbar_height = $("#top-container").height();
					var main_height = win_height - topbar_height - 30;
					$("#task-list-container").css("height", main_height + "px");
					$("#task-editor-container").css("height", main_height + "px");
				});
	</script>
</body>
</html>