<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="en:locale" content="en" />
<meta name="gwt:property" content="locale=en" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/static/image/favicon.ico" />
<title>Setting</title>
<jsp:include page="static/page/common-head.jsp" />
<script>
	
</script>
</head>
<body>
 <jsp:include page="static/page/common-menu.jsp">
	<jsp:param name="menuActive" value="1"/>
 </jsp:include>
<div id="setting-main" class="">

	<div id="setting-left">
	<ul class="nav nav-pills nav-stacked">
	  <li ><a href="#profile" data-toggle="tab" >Profile</a></li>
	  <li class="active"><a href="#taskgroup" data-toggle="tab" data-url="${pageContext.request.contextPath }/task/group/setting/list.htm">Task Group</a></li>
	</ul>
	</div>
	
	<div id="setting-right">
		<div class="tab-content">
		  <div class="tab-pane fade" id="profile">This is Profile setting panel, will coming soon...</div>
		  <div class="tab-pane fade in active" id="taskgroup" >
		  	 <jsp:include page="task_group.jsp">
		  	 	<jsp:param value="${mygroups }" name="mygroups"/>
		  	 </jsp:include>
		  </div>
		</div>
	</div>
</div>
<jsp:include page="static/page/common-footer.jsp" />
<script>
$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	var $tab=$(e.target);
	//$($tab.attr("href")).load($tab.data("url"));
});

</script>

</body>

</html>