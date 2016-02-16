package com.openthinks.notes.web.controller;

import java.util.List;

import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.easyweb.context.handler.WebAttributers;
import com.openthinks.easyweb.context.handler.WebAttributers.WebScope;
import com.openthinks.notes.web.entity.TaskGroup;
import com.openthinks.notes.web.entity.Users;
import com.openthinks.notes.web.service.TaskGroupService;
import com.openthinks.notes.web.utils.TaskUtils;

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
