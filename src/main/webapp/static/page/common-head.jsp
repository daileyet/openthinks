<%@ page import="com.openthinks.notes.web.bean.AuthorizedUser"%>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.ico" />
<link href="http://fonts.googleapis.com/css?family=Inconsolata" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">

<link href="${pageContext.request.contextPath}/static/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/openthinks.task.main.css"> --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/openthinks.common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/openthinks.task.setting.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.ico" />

<%
AuthorizedUser user=(AuthorizedUser)session.getAttribute(AuthorizedUser.AUTHORIZED_USER_ID);
request.setAttribute("authorize_user", user);
String userId=(user==null?null:user.getUser().getId());
request.setAttribute("userId", userId);
%>



