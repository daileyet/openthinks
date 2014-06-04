package com.openthinks.tasks.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.annotation.ResponseReturn;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.easyweb.context.handler.WebAttributers;
import com.openthinks.easyweb.context.handler.WebAttributers.WebScope;
import com.openthinks.tasks.web.bean.AuthorizedUser;
import com.openthinks.tasks.web.bean.json.OperationJson;
import com.openthinks.tasks.web.entity.TaskContents;
import com.openthinks.tasks.web.entity.Tasks;
import com.openthinks.tasks.web.service.TaskContentService;
import com.openthinks.tasks.web.service.TaskService;
import com.openthinks.tasks.web.utils.TaskUtils;

@Controller("/task")
public class TaskController {
	Logger logger = Logger.getLogger(TaskController.class);
	TaskService taskService = WebContexts.get().lookup(TaskService.class);
	TaskContentService contentService = WebContexts.get().lookup(
			TaskContentService.class);

	@Mapping("/main")
	public String index(WebAttributers webAttributes) {
		List<Tasks> tasks = getCurrentUserTask(webAttributes);
		webAttributes.addAttribute("tasks", tasks, WebScope.REQUEST);
		// return "WEB-INF/jsp/main.jsp";
//		return "main.jsp";
		return "open-task-main.jsp";
	}

	private List<Tasks> getCurrentUserTask(WebAttributers webAttributes) {
		AuthorizedUser autnorizedUser = (AuthorizedUser) webAttributes
				.getAttribute(AuthorizedUser.AUTHORIZED_USER_ID,
						WebScope.SESSION);
		List<Tasks> tasks = taskService.listTask(autnorizedUser.getUser()
				.getId());
		return tasks;
	}

	@Mapping("/list")
	public String listTask(WebAttributers webAttributes) {
		List<Tasks> tasks = getCurrentUserTask(webAttributes);
		webAttributes.addAttribute("tasks", tasks, WebScope.REQUEST);
		return "task_list.jsp";
	}

	@Mapping("/list/refresh")
	public String getTaskListContent(WebAttributers webAttributes) {
		List<Tasks> tasks = getCurrentUserTask(webAttributes);
		String focus_id = (String) webAttributes.get("focus_id");

		if (focus_id != null && !"".equals(focus_id.trim())) {
			webAttributes.addError("focus-task-id", focus_id, WebScope.REQUEST);
		}
		webAttributes.addAttribute("tasks", tasks, WebScope.REQUEST);
		return "task_list.jsp";
	}

	@Mapping("/list/section")
	public String getTaskListSection(WebAttributers webAttributes) {
		List<Tasks> tasks = getCurrentUserTask(webAttributes);
		String focus_id = (String) webAttributes.get("focus_id");

		if (focus_id != null && !"".equals(focus_id.trim())) {
			webAttributes.addError("focus-task-id", focus_id, WebScope.REQUEST);
		}
		webAttributes.addAttribute("tasks", tasks, WebScope.REQUEST);
		return "task_list_section.jsp";
	}

	@Mapping("/edit")
	public String edit(WebAttributers webAttributes) {
		String id = (String) webAttributes.get("id");
		if (id != null) {
			Tasks task = taskService.find(id);
			webAttributes.addAttribute("task", task, WebScope.REQUEST);
			String creatorId = null;
			try {
				creatorId = task.getTaskGroup() == null ? null : task
						.getTaskGroup().getCreatorId();
			} catch (Exception e) {
				// ignore
			}
			if (creatorId == null) {
				if (TaskUtils.sessionUser(webAttributes).getId()
						.equals(task.getUid())) {
					webAttributes.addAttribute("isGroupOwner", "true",
							WebScope.REQUEST);
				}
			} else {
				Boolean isGroupOwner = TaskUtils.sessionUser(webAttributes)
						.getId().equals(creatorId);
				webAttributes.addAttribute("isGroupOwner",
						isGroupOwner.toString(), WebScope.REQUEST);
			}
		}
		//return "task_edit.jsp";
		 return "open-task-main.jsp";
	}

	@SuppressWarnings("rawtypes")
	@Mapping("/ajax_save")
	@ResponseReturn(contentType = "text/json")
	public String saveForAjax(WebAttributers webAttributes) {
		String errorMessage = null;
		try {
			save(webAttributes);
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		Map errorMap = (Map) webAttributes.getAttribute(
				WebAttributers.ERROR_NAME, WebScope.REQUEST);
		String errorTem = (String) (errorMap == null ? null : errorMap
				.get("operation-error"));
		errorMessage = (errorMessage == null ? errorTem : errorMessage);
		if (errorMessage != null)
			return OperationJson.build().setType(OperationJson.ERROR_TYEP)
					.setMsg(errorMessage).toString();
		else {
			Tasks tasks = (Tasks) webAttributes.getAttribute("task",
					WebScope.REQUEST);
			Map<String, String> others = new HashMap<String, String>();
			others.put("id", tasks.getId());
			others.put("lock", tasks.getLock());
			String response = OperationJson.build()
					.setType(OperationJson.SUCESS_TYEP).setOther(others)
					.toString();
			return response;
		}
	}

	@Mapping("/group/assigin")
	@ResponseReturn(contentType = "text/json")
	public String assignGroup(WebAttributers webAttributes) {
		String groupId = (String) webAttributes.get("groupId");
		String taskId = (String) webAttributes.get("taskId");

		Tasks task = taskService.find(taskId);
		AuthorizedUser autnorizedUser = (AuthorizedUser) webAttributes
				.getAttribute(AuthorizedUser.AUTHORIZED_USER_ID,
						WebScope.SESSION);
		OperationJson json = OperationJson.build();
		if ("Y".equals(task.getLock())// task has been locked by other person
				&& !autnorizedUser.getUser().getId().equals(task.getLockedBy())) {
			json.setType(OperationJson.ERROR_TYEP)
					.setMsg("This task has been locked by other person first, you could not modify it.");
		} else {
			task.setTaskGroupId(groupId);
			taskService.update(task);
			json.setType(OperationJson.SUCESS_TYEP)
					.setMsg("This task has been assigned to the selected group sucessfully.");
		}

		return json.toString();
	}

	@Mapping("/save")
	public String save(WebAttributers webAttributes) {
		// logger.debug("...saving...");

		String id = (String) webAttributes.get("id");
		String subject = (String) webAttributes.get("subject");
		String content = (String) // WebUtils.decode(
		webAttributes.get("content.content");
		// );

		if (subject == null || "".equals(subject.trim())) {
			webAttributes.addError("operation-error",
					"The subject of task cannot be empty.", WebScope.REQUEST);
			return "task_edit.jsp";
		}

		AuthorizedUser autnorizedUser = (AuthorizedUser) webAttributes
				.getAttribute(AuthorizedUser.AUTHORIZED_USER_ID,
						WebScope.SESSION);
		Tasks tasks = null;
		if (id != null && !"".equals(id.trim())) {// update task
			// check if is lock by other user
			tasks = taskService.find(id);

			if ("Y".equalsIgnoreCase(tasks.getLock())
					&& tasks.getLockedBy() != autnorizedUser.getUser().getId()) {
				webAttributes.addError("operation-error",
						"This task has been locked by user:"
								+ tasks.getLockedUser().getUserName()
								+ " , please wait for or contact him.",
						WebScope.REQUEST);
			} else {
				tasks.setSubject(subject);
				tasks.setUpdateby(autnorizedUser.getUser().getId());
				tasks.setUpdatedate(new Date().toString());

				TaskContents contents = tasks.getContent();
				contents.setContent(content);
				tasks.setContent(contents);

				taskService.update(tasks);
			}

		} else {// create task and task contents
			tasks = new Tasks();
			tasks.setSubject(subject);
			tasks.setUid(autnorizedUser.getUser().getId());
			tasks.setUpdateby(autnorizedUser.getUser().getId());
			tasks.setUpdatedate(new Date().toString());
			TaskContents contents = new TaskContents();
			contents.setContent(content);
			tasks.setContent(contents);
			taskService.add(tasks);
			logger.debug("Create Task[" + tasks.getId() + "]");
		}
		webAttributes.addAttribute("task", tasks, WebScope.REQUEST);
		return "task_edit.jsp";
		// return "WEB-INF/jsp/edit.jsp";
	}

	@Mapping("/lock")
	@ResponseReturn(contentType = "text/json")
	public String lock(WebAttributers webAttributes) {
		AuthorizedUser autnorizedUser = (AuthorizedUser) webAttributes
				.getAttribute(AuthorizedUser.AUTHORIZED_USER_ID,
						WebScope.SESSION);
		String id = (String) webAttributes.get("id");
		Tasks task = taskService.find(id);
		OperationJson json = OperationJson.build();
		if ("Y".equals(task.getLock())// task has been locked by other person
				&& !autnorizedUser.getUser().getId().equals(task.getLockedBy())) {
			json.setType(OperationJson.ERROR_TYEP)
					.setMsg("This task has been locked by other person first, you could not lock it.");
		} else {
			task.setLock("Y");
			task.setLockedBy(autnorizedUser.getUser().getId());
			task.setLockedDate(new Date().toString());

			taskService.update(task);
			json.setType(OperationJson.SUCESS_TYEP).setMsg(
					"This task has been locked by you sucessfully.");
		}
		return json.toString();
	}

	@Mapping("/unlock")
	@ResponseReturn(contentType = "text/json")
	public String unlock(WebAttributers webAttributes) {

		AuthorizedUser autnorizedUser = (AuthorizedUser) webAttributes
				.getAttribute(AuthorizedUser.AUTHORIZED_USER_ID,
						WebScope.SESSION);
		String id = (String) webAttributes.get("id");
		Tasks task = taskService.find(id);
		OperationJson json = OperationJson.build();
		if ("Y".equals(task.getLock())// task was not locked by self
				&& !autnorizedUser.getUser().getId().equals(task.getLockedBy())) {
			json

			.setType(OperationJson.ERROR_TYEP)
					.setMsg("This task has been locked by other person first, you could not unlock it.");
		} else {
			task.setLock("N");
			// task.setLockedBy(autnorizedUser.getUser().getId());
			// task.setLockedDate(new Date().toString());
			task.setUpdateby(autnorizedUser.getUser().getId());
			task.setUpdatedate(new Date().toString());
			taskService.update(task);
			json.setType(OperationJson.SUCESS_TYEP).setMsg(
					"This task has been unlocked by you sucessfully.");
		}
		return json.toString();
	}

	@SuppressWarnings("rawtypes")
	@Mapping("/ajax_del")
	@ResponseReturn(contentType = "text/json")
	public String removeFormAjax(WebAttributers webAttributes) {
		String errorMessage = null;
		remove(webAttributes);
		Map errorMap = (Map) webAttributes.getAttribute(
				WebAttributers.ERROR_NAME, WebScope.REQUEST);
		String errorTemp = (String) (errorMap == null ? null : errorMap
				.get("operation-error"));
		errorMessage = (errorMessage == null ? errorTemp : errorMessage);
		if (errorMessage != null) {
			return OperationJson.build().setType(OperationJson.ERROR_TYEP)
					.setMsg(errorMessage).toString();
		} else {
			return OperationJson.build().setType(OperationJson.SUCESS_TYEP)
					.toString();
		}
	}

	@Mapping("/del")
	public String remove(WebAttributers webAttributes) {
		String id = (String) webAttributes.get("id");
		if (id != null) {
			Tasks task = taskService.find(id);
			if (task == null) {
				webAttributes
						.addError(
								"operation-error",
								"This task can not be removed, it may has been removed.",
								WebScope.REQUEST);
				return "task_edit.jsp";
			}
			try {
				taskService.delete(task);
			} catch (Exception e) {
				webAttributes
						.addError(
								"operation-error",
								"This task can not be removed, it may has been removed or occur some problem.",
								WebScope.REQUEST);
				webAttributes.addAttribute("task", task, WebScope.REQUEST);
				e.printStackTrace();
				return "task_edit.jsp";
			}

		} else {
			webAttributes.addError("operation-error",
					"Please make sure you have selected a task.",
					WebScope.REQUEST);
		}
		// List<Tasks> tasks = taskService.listTask();
		// webAttributes.addAttribute("tasks", tasks, WebScope.REQUEST);
		return "task_edit.jsp";
	}
}
