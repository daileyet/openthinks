/**
 * 
 */
package com.openthinks.tasks.web.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author minjdai
 * 
 */
@Entity
@Table(name = "tasks_group")
public class TaskGroup {
	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "GroupName")
	private String name;
	@Column(name = "Description")
	private String description;
	@Column(name = "CreatorID")
	private String creatorId;

	private List<Users> groupUsers;
	private List<Tasks> groupTasks;

	/**
	 * 
	 */
	public TaskGroup() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public List<Users> getGroupUsers() {
		return groupUsers;
	}

	public void setGroupUsers(List<Users> groupUsers) {
		this.groupUsers = groupUsers;
	}

	public List<Tasks> getGroupTasks() {
		if (this.groupTasks == null && this.id != null) {

		}
		return groupTasks;
	}

	public void setGroupTasks(List<Tasks> groupTasks) {
		this.groupTasks = groupTasks;
	}

}
