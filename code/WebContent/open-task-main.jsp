<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>My To Do</title>
<jsp:include page="static/page/common-head.jsp" />
<!-- Bootstrap -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css"> -->
<link
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/openthinks.task.main.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		<script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
		<script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
		<![endif]-->

</head>
<body>
	<!-------------------------------------------------------------------------------------------->
	<!----------------------------------------Navbar Start---------------------------------------->
	<!-------------------------------------------------------------------------------------------->
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="navbar-header">
			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"> Open-thinks <span class="label"><small>To
						Do</small></span>
			</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <i class="icon-user"></i><b
						class="caret"></b><span class="label">&nbsp;</span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#"> Setting </a></li>
						<li><a href="#"> Administrator </a></li>
						<li class="divider"></li>
						<li><a href="#"> Logout </a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	<!--Navbar End-->


	<div class="container-fluid" id="task-main-content">
		<div class="row-fluid">
			<!-------------------------------------------------------------------------------------------->
			<!----------------------------------------Left list Start------------------------------------->
			<!-------------------------------------------------------------------------------------------->
			<div class="col-md-3">
				<div class="row-fluid" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading" id="lister-heading">
							<!--Left list header tool-->

							<div class="btn-group pull-left">
								<button type="button" class="accordion-toggle btn btn-default"
									data-toggle="collapse" data-parent="#accordion"
									href="[id^='collapse_']">
									<span class="icon-expand"></span>
								</button>
								<button type="button" class="btn btn-default">
									<i class="icon-refresh"></i>
								</button>
								<button type="button" class="btn btn-default">
									<i class="icon-plus"></i>
								</button>
							</div>

							<div class="btn-group pull-right">
								<button type="button" class="btn btn-default">
									<i class="icon-chevron-left"></i>
								</button>
								<button type="button" class="btn btn-default">
									<i class="icon-chevron-right"></i>
								</button>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="panel-body row" id="lister-container">
							<!-- iterator start here -->
							<c:forEach var="task" items="${tasks}">
								<a name="collapse_${task.id}_anchor"></a>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<span class="pull-left"> <a class="accordion-toggle"
												data-toggle="collapse" data-parent="#accordion"
												href="#collapse_${task.id}"> <span name="action-expand"
													class="icon-expand"></span>
											</a> <span class="icon-lock"></span> <span class="icon-unlock"></span>
											</span> <a name="action-edit" href="#" class="pull-right"><span
												name="task-subject">${task.subject}</span></a> <span
												class="clearfix"></span>
										</h4>
									</div>
									<div id="collapse_${task.id}" class="panel-collapse collapse">
										<div class="panel-body">Createby:${task.user.userName}
											Status:${task.lock=='N'?"Unlock":"Lock"}</div>
									</div>
								</div>
							</c:forEach>
							<!-- iterator end here -->
						</div>
					</div>
				</div>
			</div>
			<!--Left list End-->
			<!-------------------------------------------------------------------------------------------->
			<!----------------------------------------Right Editor Start---------------------------------->
			<!-------------------------------------------------------------------------------------------->
			<div class="col-md-9">
				<div class="content row" id="editparent">
					<div class="btn-toolbar" id="editControls" role="toolbar"
						data-role="editor-toolbar">
						<div class="btn-group">
							<a class="btn btn-default" data-role-customer="save 1 2" href="#">
								<i class="icon-save"></i>
							</a> <a class="btn btn-default" data-role-customer="delete" href="#">
								<i class="icon-remove"></i>
							</a>

						</div>
						<div class="btn-group">
							<a class="btn btn-default" data-role="undo" href="#"> <i
								class="icon-undo"></i>
							</a> <a class="btn btn-default" data-role="redo" href="#"> <i
								class="icon-repeat"></i>
							</a>
						</div>
						<div class="btn-group">
							<a class="btn btn-default" data-role="bold" href="#"> <b>Bold</b>
							</a> <a class="btn btn-default" data-role="italic" href="#"> <em>Italic</em>
							</a> <a class="btn btn-default" data-role="underline" href="#"> <u><b>U</b></u>
							</a> <a class="btn btn-default" data-role="strikeThrough" href="#">
								<strike> abc </strike>
							</a>
						</div>
						<div class="btn-group">
							<a class="btn btn-default" data-role="justifyLeft" href="#">
								<i class="icon-align-left"></i>
							</a> <a class="btn btn-default" data-role="justifyCenter" href="#">
								<i class="icon-align-center"></i>
							</a> <a class="btn btn-default" data-role="justifyRight" href="#">
								<i class="icon-align-right"></i>
							</a> <a class="btn btn-default" data-role="justifyFull" href="#">
								<i class="icon-align-justify"></i>
							</a>
						</div>
						<div class="btn-group">
							<a class="btn btn-default" data-role="indent" href="#"> <i
								class="icon-indent-right"></i>
							</a> <a class="btn btn-default" data-role="outdent" href="#"> <i
								class="icon-indent-left"></i>
							</a>
						</div>
						<div class="btn-group">
							<a class="btn btn-default" data-role="insertunorderedlist"
								href="#"> <i class="icon-list-ul"></i>
							</a> <a class="btn btn-default" data-role="insertorderedlist"
								href="#"> <i class="icon-list-ol"></i>
							</a>
						</div>
						<div class="btn-group">
						
						<a class="btn btn-default" data-role="removeFormat" href="#" title="Clean">
								<i class="icon-eraser"></i>
							</a>
							<a class="btn btn-default" data-role="formatBlock h1" href="#">
								h<sup>1</sup>
							</a> <a class="btn btn-default" data-role="formatBlock h2" href="#">
								h<sup>2</sup>
							</a> <a class="btn btn-default" data-role="formatBlock p" href="#">
								p </a>
						</div>
						<div class="btn-group">
							<a class="btn btn-default" data-role="subscript" href="#"> <i
								class="icon-subscript"></i>
							</a> <a class="btn btn-default" data-role="superscript" href="#">
								<i class="icon-superscript"></i>
							</a>
						</div>

						<div class="btn-group">
							<a class="btn btn-default" data-role-customer="assignGroup"
								data-toggle="modal" data-target="#myModal"> <i
								class="icon-group"></i>
							</a> <a class="btn btn-default" data-role-customer="unlock" href="#">
								<i class="icon-unlock"></i>
							</a> <a class="btn btn-default" data-role-customer="lock" href="#">
								<i class="icon-lock"></i>
							</a>
						</div>

					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<span id="task-subject" type="text" class="form-control"
								placeholder="Subject" contenteditable="true"></span>
						</div>
						<!-- Editor -->
						<div class="panel-body">
							<div id="editor" contenteditable="true"></div>
							<div id="editor-source" style="display: none"
								contenteditable="true"></div>
						</div>
						<!-- -->
						<div class="panel-footer">

							<ul class="nav nav-pills">
								<li class="active"><a href="#" data-role="editor"
									data-toggle="editor-source"> Editor </a></li>
								<li><a href="#" data-role="editor-source"
									data-toggle="editor"> Source </a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!--Right Editor End-->
		</div>

	</div>


	<div id="footer">
		<div class="container">
			<p class="text-muted">&copy; open-thinks 2013~2014</p>
		</div>
	</div>


	<!-------------------------------------------------------------------------------------------->
	<!------------------------------------------Modal Dialog-------------------------------------->
	<!-------------------------------------------------------------------------------------------->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Task Group Assign</h4>
				</div>
				<div class="modal-body">
					<div class="list-group">
						<span class="list-group-item" data-groupid="${group.id}">Group
							Name1</span> <span class="list-group-item" data-groupid="${group.id}">Group
							Name2</span> <span class="list-group-item" data-groupid="${group.id}">Group
							Name3<span class='badge'>current</span>
						</span>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
					<input type="hidden" class="active-group-holder" data-active="" />
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/plugin/jquery.hotkeys.js"></script>

	<script
		src="${pageContext.request.contextPath}/static/js/plugin/bootstrap-wysiwyg.js"></script>

	<script
		src="${pageContext.request.contextPath}/static/js/plugin/open-todo.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/controller/TodoCtrl.js"></script>
</body>
</html>