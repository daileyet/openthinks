<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task Editor</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/main.css"
	type="text/css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/task.js"></script>
<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/static/js/image_uploader.js"></script>
</head>
<body>
	<jsp:include page="current_user.jsp"></jsp:include>

	<div id="error_info">${webError['operation-error']}</div>
	<div id="tip_info"></div>

	<form name="taskForm"
		action="${pageContext.request.contextPath}/task/save.htm"
		method="POST">
		<div style="width: 100%">
			<div style="width: 100%">
				<span style="width: 80%"> <input type="hidden"
					value="${task.id }" id="task_id" /> <input type="text"
					value="${task.subject }" name="subject" /> <c:if
						test="${task.lock=='N' }">
						<input type="hidden" value="Lock" name="task.lock"
							onClick="lock_task('${pageContext.request.contextPath}/task/lock.htm','${task.id}')" />
						<span name="lock_by" style="display: none">Locked
							by:${task.lockedUser.userName}</span>
					</c:if> <c:if test="${task.lock=='Y' }">
						<input type="hidden" value="Unlock" name="task.lock"
							onClick="unlock_task('${pageContext.request.contextPath}/task/unlock.htm','${task.id}')" />
						<span name="lock_by">Locked by:${task.lockedUser.userName}</span>
					</c:if> &nbsp;&nbsp;
				</span> <span> <c:if
						test="${task.user.id==authorize_user.user.id }">
						<a
							href="javascript:delTask('${pageContext.request.contextPath}/task/del.htm?id=','${task.id }')">Remove</a>
					</c:if>
				</span>
			</div>
			<div id="task-container">
				<textarea id="task-content" rows="50" cols="50" 
					name="content.content">${task.content.content}</textarea>
			</div>
		</div>
		<script type="text/javascript">
			var editor = CKEDITOR.replace('task-content');
			//构建图片上传地址
			var sUrl = 'http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.servletContext.contextPath}/upload/img.htm';
			//构建应用名称，如本系统名称为‘wordimg’，如果为根应用，请写为空字符串''        
			var appName = '${pageContext.servletContext.contextPath}';
			//创建WordImageUploader对象
			var uploader = new WordImageUploader(sUrl, appName);
			//当ckeditor内容改变时，自动检测并上传内容中的本地图片
			CKEDITOR.instances["task-content"].on("change", function() {
				//uploader.uploadWordImagesFromCKEditor(
						//CKEDITOR.instances["task-content"], '');
			});
		</script>
		<script>
			function lock(){
				lock_task('${pageContext.request.contextPath}/task/lock.htm','${task.id}');
			}
			function unlock(){
				unlock_task('${pageContext.request.contextPath}/task/unlock.htm','${task.id}');
			}
		</script>
	</form>

</body>

</html>