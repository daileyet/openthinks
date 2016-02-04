<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class='panel panel-default'>
	<div class="panel-body">
    	
    	
    	<ul class="nav nav-tabs">
		  <li class="active"><a href="#my_groups" data-toggle="tab">My Owned Groups</a></li>
		  <li><a href="#my_join_groups" data-toggle="tab">My Joined Groups</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
		  <div class="tab-pane active" id="my_groups">
		  	<div class="panel-group" id="group_accordion">
		  		<c:forEach var="group" items="${mygroups}">
				  <div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a class="accordion-toggle" data-toggle="collapse" data-parent="#group_accordion" href="#mygroup_${group.id}">
				          <span >${group.name }</span>
				        </a>
				      </h4>
				    </div>
				    <div id="#mygroup_${group.id}" class="panel-collapse collapse in">
				      <div class="panel-body">
				        	${group.description }
				      </div>
				    </div>
				  </div>
				 </c:forEach>
				</div>
		  		
		  </div>
		  <div class="tab-pane" id="my_join_groups">...</div>
		</div>
    	
    	
    	
    	
    </div>
</div>
