package com.openthinks.tasks.web.controller;

import org.apache.log4j.Logger;

import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.easyweb.context.handler.WebAttributers;

@Controller("/setting")
public class SettingController {
	Logger logger = Logger.getLogger(TaskGroupController.class);

	@Mapping("/index")
	public String index(WebAttributers webAttributers) {

		WebContexts.get().lookup(TaskGroupController.class)
				.listCurrentUserGroup(webAttributers);

		return "setting.jsp";
	}
}
