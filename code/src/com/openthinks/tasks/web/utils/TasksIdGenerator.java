package com.openthinks.tasks.web.utils;

import java.util.Date;

import openthinks.easyweb.context.WebContexts;
import openthinks.libs.sql.entity.key.IdGenerator;
import openthinks.libs.sql.lang.DateFormatUtils;

import com.openthinks.tasks.web.entity.Tasks;

public class TasksIdGenerator extends IdGenerator {
	private final TaskCounter taskCounter = WebContexts.get().lookup(TaskCounter.class);
	static {
		IdGenerator.register(Tasks.class, new TasksIdGenerator());
	}

	@Override
	public String generator() {

		String dateString = DateFormatUtils.format(new Date(), "yyyyMMdd");
		Integer no = taskCounter.createNext(dateString);
		String nos = String.valueOf(no);

		while (4 - nos.length() > 0) {
			nos = "0" + nos;
		}
		// nos = String.format("%04d %n", no);
		return dateString + nos;
	}
}