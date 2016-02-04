<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.openthinks.tasks.web.bean.AuthorizedUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
AuthorizedUser user=(AuthorizedUser)session.getAttribute(AuthorizedUser.AUTHORIZED_USER_ID);
request.setAttribute("authorize_user", user);
%>
   
<div id="menu_bar" >
<span id="menu_container">
<a href="${pageContext.request.contextPath}/index.htm">Home</a>
<a href="${pageContext.request.contextPath}/task/main.htm">Tasks</a>
</span>
<span id="login_info"  >
<span id="user_label">${authorize_user}</span>
<c:if test="${authorize_user!=null }">
<span id="login_out"><a href="${pageContext.request.contextPath}/logout.htm">Logout</a></span>
</c:if>
<c:if test="${authorize_user==null }">
<span id="login_in"><a href="${pageContext.request.contextPath}/login.jsp">Login</a></span>
</c:if>
</span>
</div>