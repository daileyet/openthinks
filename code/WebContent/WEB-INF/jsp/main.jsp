<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/task.js"></script>
</head>
<body>
	<jsp:include page="current_user.jsp"></jsp:include>
	<P></P>
	<div>
		<div id="task_bar">
			<input type="button" name="btn_new" value="New Task"
				onclick="newTask('${pageContext.request.contextPath}/task/edit.htm')" />
		</div>
		<table id="task_list">

			<tr id="head">
				<td>编号</td>
				<td align='center'>标题</td>
				<td>创建者</td>
				<td>状态</td>
			</tr>


			<c:forEach var="task" items="${tasks}">
				<tr>
					<td>${task.id}</td>
					<td name="subject"><a
						href="${pageContext.request.contextPath}/task/edit.htm?id=${task.id }">&nbsp;${task.subject}&nbsp;</a></td>
					<td>${task.user.userName}</td>
					<td>${task.lock=='N'?"Unlock":"Lock"}</td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>

</html>