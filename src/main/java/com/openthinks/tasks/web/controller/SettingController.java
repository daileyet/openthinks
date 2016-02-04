package com.openthinks.tasks.web.controller;

import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.easyweb.context.handler.WebAttributers;

@Controller("/setting")
public class SettingController {

	@Mapping("/index")
	public String index(WebAttributers webAttributers) {

		WebContexts.get().lookup(TaskGroupController.class).listCurrentUserGroup(webAttributers);

		return "setting.jsp";
	}
}
