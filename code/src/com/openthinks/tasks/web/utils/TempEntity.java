package com.openthinks.tasks.web.utils;

import sql.entity.Entity;

public class TempEntity extends Entity {
	String taskCount;

	public String getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(String taskCount) {
		this.taskCount = taskCount;
	}

	public Integer getTaskCount_() {
		if (taskCount != null) {
			return Integer.valueOf(taskCount.substring(8));
		}
		return 0;
	}

}