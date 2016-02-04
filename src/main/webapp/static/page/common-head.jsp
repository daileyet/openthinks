<%@ page import="com.openthinks.tasks.web.bean.AuthorizedUser"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/3.3.5/css/bootstrap.min.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<link href="${pageContext.request.contextPath}/static/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/openthinks.task.main.css"> --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/openthinks.common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/openthinks.task.setting.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.ico" />

<script src="${pageContext.request.contextPath}/static/js/htmlutils.js"></script>
<%
AuthorizedUser user=(AuthorizedUser)session.getAttribute(AuthorizedUser.AUTHORIZED_USER_ID);
request.setAttribute("authorize_user", user);
String userId=(user==null?null:user.getUser().getId());
request.setAttribute("userId", userId);
%>



