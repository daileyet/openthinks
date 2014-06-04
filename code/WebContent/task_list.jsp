<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Task List</title>
<jsp:include page="static/page/common-head.jsp"/>

</head>
<body>


<div class="panel-group" id="accordion">
<div class="panel panel-default">
	<div class="panel-heading" >
      <div class="panel-title" >		
		<span class="btn-group btn-group-sm" style="margin-left:-10px;">
			<button type="button" class="accordion-toggle btn btn-primary" data-toggle="collapse" data-parent="#accordion" href="[id^='collapse_']">
				  <span class="icon-expand"></span>
			</button>
			<button type="button" class="btn btn-primary" onclick="tasks.refresh()"><i class="icon-refresh"></i></button>
			<button type="button" class="btn btn-primary" onclick="tasks.addTask()"><i class="icon-plus"></i></button>
		</span>
	  </div>
	</div>
</div>
<div class="task-section">
  <jsp:include page="task_list_section.jsp"/>
</div>
</div>

<jsp:include page="static/page/common-footer.jsp" />
<script>

Tasks=function(){
	this.editTask=function(task_id){	    	    
		//window.parent.document.getElementById("task-editor-container").src=url;
		var edit_url="${pageContext.request.contextPath}/task/edit.htm?id="+task_id;
		//alert(window.parent.document.getElementById("task-editor-container"));
		var srcVal=$('#task-editor-container', window.parent.document).attr("src",edit_url);		
	};
	this.test=function(){
		alert('test');
	};
	this.addTask=function(){
	    $("div[class~='panel-primary']").removeClass("panel-primary").addClass("panel-default");
		var add_url="${pageContext.request.contextPath}/task/edit.htm";
		//alert(window.parent.document.getElementById("task-editor-container"));
		var srcVal=$('#task-editor-container', window.parent.document).attr("src",add_url);	
	};
	
	this.refresh=function(taskid){
		var _this=this;
		var remember_div=$("div[class~='panel-primary'] div:nth-child(2)");
		console.log(remember_div);
		var parameter="";
		if(remember_div.length>0){
			var remember_id=remember_div.attr("id");	
			parameter="?focus_id="+remember_id;
			//"#anchor_"+remember_id.split('-')[1];
		}else if(taskid){
			parameter="?focus_id=collapse_"+taskid;
		}
		//window.location="${pageContext.request.contextPath}/task/list/refresh.htm"+parameter;
		var url="${pageContext.request.contextPath}/task/list/section.htm"+parameter;
		$.get( url, function( data ) {
      		$("div.task-section").empty().html(data);
      		_this.initialize();_this.setFocusTask();
    	});
	};
	
	this.unescape=function(){
		$("span[name='task-subject']").each(function(){
			$(this).text(unescape($(this).text()));
		});
	};
	
	
	this.setFocusTask=function(focusid){
		
		var focus_task_id=$('#focus-task-id').val();
		if(focusid){
			focus_task_id=focusid;
		}
		if(focus_task_id==""){	
			$("div[class~='panel-primary']").removeClass("panel-primary").addClass("panel-default");
		}else{
			$("#"+focus_task_id).parent().removeClass("panel-default").addClass("panel-primary");
			
		}
	};
	
	this.initialize=function(){
		$("div[id^='collapse_']").on('hide.bs.collapse', function () {
			var _this=this;
			$(_this).prev().find("span[name='action-expand']").removeClass("icon-collapse").addClass("icon-expand");
		});
		$("div[id^='collapse_']").on('show.bs.collapse', function () {
			var _this=this;
			$(_this).prev().find("span[name='action-expand']").removeClass("icon-expand").addClass("icon-collapse");
		});
		
		$("a[name='action-edit']").click(function(){
			$("div[class~='panel-primary']").removeClass("panel-primary").addClass("panel-default");
			$(this).parent().parent().parent().removeClass("panel-default").addClass("panel-primary");	
			var remember_div=$("div[class~='panel-primary'] div:nth-child(2)");
			if(remember_div.length>0){
				var remember_id=remember_div.attr("id");	
				$("#focus-task-id").attr("value",remember_id);
			}
		});
		
		this.unescape();
		var _this=this;
		$(document).ready(function(){
			_this.setFocusTask();
		});
		
	};
};

var tasks=new Tasks();
tasks.initialize();

</script>



</body>

</html>