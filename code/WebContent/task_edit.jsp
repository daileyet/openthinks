<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.openthinks.tasks.web.bean.AuthorizedUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
 <head>
 <meta charset="utf-8">
 <title>Task Editor</title> 
 <jsp:include page="static/page/common-head.jsp"/>

  <style>

			#editor,#editor-source{
			    resize:vertical; 
				min-height:200px;
				max-height:545px;
				overflow:auto; 
				border:1px solid silver; 
				border-radius:3px; 				
				box-shadow: inset 0 0 10px silver;
				padding:1em;
				
			}
		</style>   
 </head>
 <body style="padding-right:5px;">  
 <div class="alert alert-danger" style="display:none;"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><div id="operation-error"></div></div>
 <div class="alert alert-success" style="display:none;"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><div id="operation-success"></div></div>
  <div class="content"> 
   <div class="container-fluid"> 
    <div id="pad-wrapper"> 
     <div id="editparent"> 
      <div id="editControls" class="span12" style="text-align:left; padding:5px;"> 
	  <div class="btn-group"> 
        <a class="btn" data-role="save" href="#"><i class="icon-save"></i></a>
		<a class="btn" data-role="delete" href="#"><i class="icon-remove"></i></a>	
		
       </div>
       <div class="btn-group"> 
        <a class="btn" data-role="undo" href="#"><i class="icon-undo"></i></a> 
        <a class="btn" data-role="redo" href="#"><i class="icon-repeat"></i></a> 
       </div> 
       <div class="btn-group"> 
        <a class="btn" data-role="bold" href="#"><b>Bold</b></a> 
        <a class="btn" data-role="italic" href="#"><em>Italic</em></a> 
        <a class="btn" data-role="underline" href="#"><u><b>U</b></u></a> 
        <a class="btn" data-role="strikeThrough" href="#">
         <strike>
          abc
         </strike></a> 
       </div> 
       <div class="btn-group"> 
        <a class="btn" data-role="justifyLeft" href="#"><i class="icon-align-left"></i></a> 
        <a class="btn" data-role="justifyCenter" href="#"><i class="icon-align-center"></i></a> 
        <a class="btn" data-role="justifyRight" href="#"><i class="icon-align-right"></i></a> 
        <a class="btn" data-role="justifyFull" href="#"><i class="icon-align-justify"></i></a> 
       </div> 
       <div class="btn-group"> 
        <a class="btn" data-role="indent" href="#"><i class="icon-indent-right"></i></a> 
        <a class="btn" data-role="outdent" href="#"><i class="icon-indent-left"></i></a> 
       </div> 
       <div class="btn-group"> 
        <a class="btn" data-role="insertUnorderedList" href="#"><i class="icon-list-ul"></i></a> 
        <a class="btn" data-role="insertOrderedList" href="#"><i class="icon-list-ol"></i></a> 
       </div> 
       <div class="btn-group"> 
        <a class="btn" data-role="h1" href="#">h<sup>1</sup></a> 
        <a class="btn" data-role="h2" href="#">h<sup>2</sup></a> 
        <a class="btn" data-role="p" href="#">p</a> 
       </div> 
       <div class="btn-group"> 
        <a class="btn" data-role="subscript" href="#"><i class="icon-subscript"></i></a> 
        <a class="btn" data-role="superscript" href="#"><i class="icon-superscript"></i></a> 
       </div> 
     
	   <div class="btn-group"> 
		<a class="btn" data-role="assignGroup" 
		href="${pageContext.request.contextPath}/task/group/list.htm" data-toggle="modal" data-target="#myModal">
			<i class="icon-group"></i>
		</a>
		
		<a class="btn" data-role="unlock" href="#"><i class="icon-unlock"></i></a>
		<a class="btn" data-role="lock" href="#"><i class="icon-lock"></i></a> 
	   </div>
	   
      </div> 
	  <div class="panel panel-default">
		  <div class="panel-heading">			 
			 <span id="task-subject" type="text" class="form-control" placeholder="Subject" contenteditable="true"></span>
		  </div>
		 
		  <div id="editor"  contenteditable="true"></div><div id="editor-source" style="display:none" contenteditable="true"></div>
		  <div class="panel-footer">
			<ul class="nav nav-pills">
			  <li class="active"><a href="#" data-role="editor" data-toggle="editor-source">Editor</a></li>
			  <li><a href="#" data-role="editor-source" data-toggle="editor">Source</a></li>
			</ul>
				<div id="hidden-region" style="display:none"><div id="task-id">${task.id}</div>
			  </div>	  
		  </div>
	  </div>
     </div> 
    </div> 
   </div> 
  </div> 
  
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ></div>
  
  
  <jsp:include page="static/page/common-footer.jsp" />
  
   <script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/business/task_editor.js"></script>
  
  <script>
			
			var taskEditor={};
			$(function() {															
				taskEditor=new TASK_EDITOR({
				parent_node:'hidden-region'				
				,task_id:'${task.id}'
				,task_subject:'${task.subject}'
				,task_content:'${task.content.content}'
				,task_lock:'${task.lock}'
				,task_group_id:'${task.taskGroupId}'
				,is_group_owner:'${isGroupOwner}'
				,actions:{
					save:'${pageContext.request.contextPath}/task/ajax_save.htm'
					,list:'${pageContext.request.contextPath}/task/list/refresh.htm'
					,remove:'${pageContext.request.contextPath}/task/ajax_del.htm'
					,lock:'${pageContext.request.contextPath}/task/lock.htm'
					,unlock:'${pageContext.request.contextPath}/task/unlock.htm'
					,assign:'${pageContext.request.contextPath}/task/group/assigin.htm'
					}				
				},{
				subject_id:'task-subject'
				,content_id:'editor'
				,source_id:'editor-source'
				,control_id:'editControls'
				},{
				id:'operation-error'
				,message:'${webError["operation-error"]}'
				},{
				id:'operation-success'
				,message:''	
				});
				
				taskEditor.initial();
				
			});
			
			
		</script>  
		
		
		
 </body>
</html>