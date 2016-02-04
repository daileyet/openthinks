package com.openthinks.tasks.web.service;

import com.openthinks.libs.sql.dhibernate.Session;
import com.openthinks.libs.sql.dhibernate.support.SessionFactory;
import com.openthinks.libs.sql.lang.Condition;
import com.openthinks.tasks.web.entity.TaskContents;

public class TaskContentService {

	public void add(TaskContents content) {
		Session session = SessionFactory.getSession();
		session.save(content);
		session.close();
	}

	public void update(TaskContents content) {
		Session session = SessionFactory.getSession();
		session.update(content);
		session.close();
	}

	public TaskContents find(String id) {
		Session session = SessionFactory.getSession();
		return session.load(TaskContents.class, id);
	}

	public TaskContents findByTask(String task_id) {
		Session session = SessionFactory.getSession();
		TaskContents contents = session.get(TaskContents.class, Condition.build(TaskContents.class).getSqlPart()
				+ " where TID=?", new String[] { task_id });
		if (contents == null) {
			contents = new TaskContents();
			contents.setTid(task_id);
		}
		return contents;
	}
}
