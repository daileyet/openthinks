<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach var="task" items="${tasks}">
  <a name="collapse_${task.id}_anchor"></a>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
    	
        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse_${task.id}">
          <span name="action-expand" class="icon-expand"></span>
        </a>
    <c:if test="${task.lock=='Y'}"><span class="icon-lock"></span></c:if>
    <c:if test="${task.lock=='N'}"><span class="icon-unlock"></span></c:if>
    &nbsp;&nbsp;<a name="action-edit" href="javascript:tasks.editTask('${task.id}')" style="width:auto;float:right;"><span name="task-subject">${task.subject}</span></a>
    
      </h4>
    </div>
    <div id="collapse_${task.id}" class="panel-collapse collapse">
      <div class="panel-body">
        Createby:${task.user.userName} Status:${task.lock=='N'?"Unlock":"Lock"}
      </div>
    </div>
  </div>
 </c:forEach>
 <input type="hidden" id="focus-task-id" value="${webError['focus-task-id']}"/>
