package com.openthinks.tasks.web.service;

import java.util.List;

import sql.dhibernate.Session;
import sql.dhibernate.support.SessionFactory;
import sql.lang.Condition;

import com.openthinks.tasks.web.entity.TaskContents;
import com.openthinks.tasks.web.entity.Tasks;

public class TaskService {

	public void add(Tasks tasks) {
		Session session = SessionFactory.getSession();
		session.disableAutoClose();
		session.save(tasks);
		TaskContents content = tasks.getContent();
		if (content != null) {
			content.setTid(tasks.getId());
			session.save(content);
		}
		session.enableAutoClose();
		session.close();
	}

	public Tasks find(String id) {
		Session session = SessionFactory.getSession();
		return session.load(Tasks.class, id);
	}

	public List<Tasks> getGroupTasks(String groupId) {
		Session session = SessionFactory.getSession();
		List<Tasks> ret = session.list(Tasks.class, Condition
				.build(Tasks.class).getSqlPart() + " where TaskGroupID=?",
				new String[] { groupId });
		return ret;
	}

	public void update(Tasks tasks) {
		Session session = SessionFactory.getSession();
		session.disableAutoClose();
		// session.beginTransaction();
		session.update(tasks);
		if (tasks.getContent() != null) {
			if (tasks.getContent().getId() == null) {
				session.save(tasks.getContent());
			} else {
				session.update(tasks.getContent());
			}
		}
		session.enableAutoClose();
		session.close();
	}

	public void delete(Tasks tasks) {
		Session session = SessionFactory.getSession();
		session.disableAutoClose();
		if (tasks.getContent() != null) {
			session.delete(tasks.getContent());
		}
		session.delete(tasks);
		session.enableAutoClose();
		session.close();
	}

	public List<Tasks> listTask(String userId) {
		Session session = SessionFactory.getSession();
		String query = "select * from tasks where TaskGroupID in("
				+ " select tg.ID from users u left join tasks_group_user tgu on u.ID=tgu.UserID "
				+ " left join tasks_group tg on tgu.TaskGroupID=tg.ID where u.ID=?) "
				+ " union select * from tasks where UID=?";
		return session
				.list(Tasks.class, query, new String[] { userId, userId });

	}
}
