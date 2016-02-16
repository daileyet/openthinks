package com.openthinks.notes.web.utils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.openthinks.libs.sql.dhibernate.Session;
import com.openthinks.libs.sql.dhibernate.support.SessionFactory;
import com.openthinks.libs.sql.entity.Entity;
import com.openthinks.libs.sql.entity.key.IdGenerator;
import com.openthinks.notes.web.entity.TaskGroup;

public class TasksGroupIdGenerator extends IdGenerator {
	final Lock lock;

	static {
		IdGenerator.register(TaskGroup.class, new TasksGroupIdGenerator());
	}

	public TasksGroupIdGenerator() {
		lock = new ReentrantLock();
	}

	class MaxEntity extends Entity {
		int maxId;
	}

	@Override
	public String generator() {
		lock.lock();
		try {
			Session session = SessionFactory.getSession();
			String query = "select max(ID) as maxId from tasks_group";
			Entity temp = session.get(MaxEntity.class, query);
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
