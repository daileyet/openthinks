package com.openthinks.tasks.web.controller;

import openthinks.easyweb.annotation.Controller;
import openthinks.easyweb.annotation.Mapping;
import openthinks.easyweb.context.WebContexts;
import openthinks.easyweb.context.handler.WebAttributers;

@Controller("/setting")
public class SettingController {

	@Mapping("/index")
	public String index(WebAttributers webAttributers) {

		WebContexts.get().lookup(TaskGroupController.class).listCurrentUserGroup(webAttributers);

		return "setting.jsp";
	}
}
