<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Task Group Assign</h4>
      </div>
      <div class="modal-body">
        <div class="list-group">
		 <c:forEach var="group" items="${groups}">
		  	<span class="list-group-item" data-groupid="${group.id}" >${group.name }</span>
		 </c:forEach>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
        <input type="hidden" class="active-group-holder" data-active=""/>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
  
  <script>
  $(document).ready(function(){
	  
	  var group_initial=function(){
		  $(".modal-dialog span.list-group-item").filter(function(){
			  var current_task_id=taskEditor?taskEditor.formData.task_group_id:"";
			  return $(this).data("groupid")==current_task_id;	  		  
		  }).append("<span class='badge'>current</span>");
	  };
	  
	   $('#myModal').on('show.bs.modal', function (e) {
		   current_task_id=taskEditor?taskEditor.formData.task_group_id:"";
		   $(".modal-dialog span.list-group-item span.badge").remove();
		   $(".modal-dialog span.list-group-item").removeClass("active");
		   group_initial();
		});

	  
	  	$(".modal-dialog span.list-group-item").unbind().click(function(){
	  		//console.log($(this).data("groupid"));
	  		$(".modal-dialog span.list-group-item").removeClass("active");
	  		$(this).addClass("active");
	  		$(".modal-dialog input.active-group-holder").data("active",$(this).data("groupid"));
	  	});
	  	
	  	$(".modal-dialog button.btn-primary").click(function(){
	  		if(taskEditor && taskEditor.assignGroup){
	  			var groupId=$(".modal-dialog input.active-group-holder").data("active");
	  			taskEditor.assignGroup(groupId);
	  		}
	  	});
	  	
	  	group_initial();
  	}
  );
  </script>