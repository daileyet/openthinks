package com.openthinks.tasks.web.controller;

import java.util.List;

import openthinks.easyweb.annotation.Controller;
import openthinks.easyweb.annotation.Mapping;
import openthinks.easyweb.context.WebContexts;
import openthinks.easyweb.context.handler.WebAttributers;
import openthinks.easyweb.context.handler.WebAttributers.WebScope;

import com.openthinks.tasks.web.entity.TaskGroup;
import com.openthinks.tasks.web.entity.Users;
import com.openthinks.tasks.web.service.TaskGroupService;
import com.openthinks.tasks.web.utils.TaskUtils;

@Controller("/task/group")
public class TaskGroupController {

	TaskGroupService taskGroupService = WebContexts.get().lookup(TaskGroupService.class);

	@Mapping("/list")
	public String listGroup(WebAttributers webAttribute) {
		String userId = TaskUtils.sessionUser(webAttribute).getId();
		List<TaskGroup> groups = taskGroupService.list(userId);
		webAttribute.addAttribute("groups", groups, WebScope.REQUEST);
		return "task_groups_selector.jsp";
	}

	@Mapping("/setting/list")
	public String listCurrentUserGroup(WebAttributers webAttribute) {
		Users currentUser = TaskUtils.sessionUser(webAttribute);
		List<TaskGroup> groups = taskGroupService.list(currentUser.getId());
		webAttribute.addAttribute("mygroups", groups, WebScope.REQUEST);
		return "task_group.jsp";
	}

}
