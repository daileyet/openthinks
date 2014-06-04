/**
 * 
 */
package com.openthinks.tasks.web.service;

import java.util.List;

import sql.dhibernate.Session;
import sql.dhibernate.support.SessionFactory;
import sql.exception.TransactionException;
import sql.lang.Condition;
import sql.lang.Configurator;
import sql.lang.ConfiguratorFactory;

import com.openthinks.tasks.resource.ResourceManagement;
import com.openthinks.tasks.web.entity.TaskGroup;
import com.openthinks.tasks.web.entity.TaskGroupUserRelation;
import com.openthinks.tasks.web.entity.Users;
import com.openthinks.tasks.web.utils.TasksGroupIdGenerator;

/**
 * @author minjdai
 * 
 */
public class TaskGroupService {

	public void addTaskGroup(TaskGroup group, Users users)
			throws TransactionException {
		Session session = SessionFactory.getSession();
		session.beginTransaction();
		session.disableAutoClose();
		session.save(group);

		TaskGroupUserRelation groupUserRelation = new TaskGroupUserRelation();
		groupUserRelation.setTaskGroupId(group.getId());
		groupUserRelation.setUserId(users.getId());
		session.save(groupUserRelation);
		session.commit();
		session.enableAutoClose();
		session.close();
	}

	public TaskGroup find(String groupId) {
		Session session = SessionFactory.getSession();
		return session.load(TaskGroup.class, groupId);
	}

	/**
	 * retrive task groups which creator id is the param userId
	 * 
	 * @param userId
	 * @return
	 */
	public List<TaskGroup> list(String userId) {
		Session session = SessionFactory.getSession();
		List<TaskGroup> taskGroups = session.list(TaskGroup.class, Condition
				.build(TaskGroup.class).getSqlPart() + " where CreatorID=?",
				new String[] { userId });

		return taskGroups;
	}

	public static void main(String[] args) throws ClassNotFoundException,
			TransactionException {
		Configurator configuration = ConfiguratorFactory
				.getInstance(ResourceManagement.getDbResource());
		SessionFactory.setDefaultConfigurator(configuration);
		Class.forName(TasksGroupIdGenerator.class.getName());

		// TaskGroupService groupService = new TaskGroupService();
		TaskGroup group = new TaskGroup();
		Users users = new Users();
		// group.setName("CX");
		// group.setDescription("HCM CX");
		// group.setCreatorId("1");
		// users.setId("1");
		// groupService.addTaskGroup(group, users);

		group.setName("CA");
		group.setDescription("HCM CA");
		group.setCreatorId("2");
		users.setId("2");
		// groupService.addTaskGroup(group, users);

	}
}
