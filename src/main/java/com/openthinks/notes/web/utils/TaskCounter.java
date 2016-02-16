package com.openthinks.notes.web.utils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.openthinks.libs.sql.dhibernate.Session;
import com.openthinks.libs.sql.dhibernate.support.SessionFactory;
import com.openthinks.libs.sql.lang.DateFormatUtils;

/**
 * Counter for task, used for generating task id <BR>
 * it may be used for analyzing task creating
 * 
 * @author minjdai
 * 
 */
public final class TaskCounter {

	final Lock lock;
	final Map<String, AtomicInteger> dateCountMap;
	final AtomicBoolean taskCounterReady = new AtomicBoolean(false);

	public TaskCounter() {
		lock = new ReentrantLock();
		dateCountMap = new ConcurrentHashMap<String, AtomicInteger>();
	}

	private void initial() {
		Session session = SessionFactory.getSession();
		String datestr = DateFormatUtils.format(new Date(), "yyyyMMdd");

		TempEntity temp = session.get(TempEntity.class, "select max(ID) as taskCount from tasks where ID like '"
				+ datestr + "%'");
		get(datestr).set(temp.getTaskCount_());
		session.close();
	}

	private AtomicInteger get(String dateStr) {
		AtomicInteger atomic = dateCountMap.get(dateStr);
		if (atomic != null) {
			return atomic;
		} else {
			atomic = new AtomicInteger(0);
			dateCountMap.put(dateStr, atomic);
		}
		return atomic;
	}

	public Integer count(String dateStr) {
		prepared();
		AtomicInteger atomic = dateCountMap.get(dateStr);
		if (atomic != null) {
			return atomic.get();
		}
		return 0;
	}

	public Integer createNext(String dateStr) {
		prepared();
		lock.lock();
		try {
			AtomicInteger atomic = dateCountMap.get(dateStr);
			if (atomic == null) {
				atomic = new AtomicInteger(0);
				dateCountMap.put(dateStr, atomic);
			}
			return atomic.addAndGet(1);
		} finally {
			lock.unlock();
		}
	}

	private void prepared() {
		lock.lock();
		try {
			if (taskCounterReady.get() == false) {
				initial();
				taskCounterReady.set(true);
			}
		} finally {
			lock.unlock();
		}
	}

}
