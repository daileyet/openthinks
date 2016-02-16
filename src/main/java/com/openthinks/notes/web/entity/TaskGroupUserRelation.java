/**
 * 
 */
package com.openthinks.notes.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author minjdai
 * 
 */
@Entity
@Table(name = "tasks_group_user")
public class TaskGroupUserRelation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private String Id;
	@Column(name = "TaskgroupID")
	private String taskGroupId;
	@Column(name = "UserID")
	private String userId;

	/**
	 * 
	 */
	public TaskGroupUserRelation() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getTaskGroupId() {
		return taskGroupId;
	}

	public void setTaskGroupId(String taskGroupId) {
		this.taskGroupId = taskGroupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
