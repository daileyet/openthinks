/**
 * 
 */
package com.openthinks.tasks.web.service;

import java.util.List;

import org.apache.log4j.Logger;

import sql.dhibernate.Session;
import sql.dhibernate.support.SessionFactory;
import sql.lang.Condition;

import com.openthinks.tasks.web.entity.Users;

/**
 * @author minjdai
 * 
 */
public class UserService {

	Logger logger = Logger.getLogger(getClass());

	public void add(Users user) {
		Session session = SessionFactory.getSession();
		session.save(user);
		session.close();
	}

	public void update(Users user) {
		Session session = SessionFactory.getSession();
		session.update(user);
		session.close();
	}

	public List<Users> listAll() {
		Session session = SessionFactory.getSession();
		return session.list(Users.class);
	}

	public Users find(String id) {
		Session session = SessionFactory.getSession();
		return session.load(Users.class, id);
	}

	public Users find(String userName, String userPassword) {
		Session session = SessionFactory.getSession();
		Users user = session.get(Users.class, Condition.build(Users.class)
				.getSqlPart() + " where UserName=? and UserPassword=?",
				new String[] { userName, userPassword });
		return user;
	}
}
