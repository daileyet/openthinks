package com.openthinks.tasks.web.utils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import sql.dhibernate.Session;
import sql.dhibernate.support.SessionFactory;
import sql.entity.Entity;
import sql.entity.key.IdGenerator;

import com.openthinks.tasks.web.entity.TaskGroup;

public class TasksGroupIdGenerator extends IdGenerator {
	final Lock lock;

	static {
		IdGenerator.register(TaskGroup.class, new TasksGroupIdGenerator());
	}

	public TasksGroupIdGenerator() {
		lock = new ReentrantLock();
	}

	@Override
	public String generator() {
		lock.lock();
		try {
			Session session = SessionFactory.getSession();
			String query = "select max(ID) as maxId from tasks_group";
			Entity temp = session.get(new Entity() {
				@SuppressWarnings("unused")
				int maxId;
			}, query);
			int ret = 1;
			if (temp != null) {
				ret = Integer.valueOf(temp.get("maxId").toString()) + 1;
			}
			return String.valueOf(ret);
		} finally {
			lock.unlock();
		}
	}

}
