package com.openthinks.tasks.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.tasks.web.service.TaskContentService;
import com.openthinks.tasks.web.service.TaskGroupService;
import com.openthinks.tasks.web.service.UserService;

@Entity
@Table(name = "tasks")
public class Tasks {
	@Id
	@Column(name = "ID")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	@Column(name = "Subject")
	private String subject;
	@Column(name = "UID")
	private String uid;
	@Column(name = "CreateDate")
	private String createdate;
	@Column(name = "UpdateBy")
	private String updateby;
	@Column(name = "UpdateDate")
	private String updatedate;

	@Column(name = "Lock")
	private String lock;
	@Column(name = "LockedBy")
	private String lockedBy;
	@Column(name = "LockedDate")
	private String lockedDate;

	@Column(name = "TaskGroupID")
	private String taskGroupId;

	private Users user;
	private Users lockedUser;
	private Users updatedUser;
	private TaskGroup taskGroup;

	private final UserService userService = WebContexts.get().lookup(UserService.class);

	public Users getUser() {
		if (this.user == null && this.uid != null) {
			this.user = userService.find(uid);
		}
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Users getLockedUser() {
		if (this.lockedUser == null && this.lockedBy != null) {
			this.lockedUser = userService.find(lockedBy);
		}
		return lockedUser;
	}

	public void setLockedUser(Users lockedUser) {
		this.lockedUser = lockedUser;
	}

	public Users getUpdatedUser() {
		if (this.updatedUser == null && this.updateby != null) {
			this.updatedUser = userService.find(updateby);
		}
		return updatedUser;
	}

	public void setUpdatedUser(Users updatedUser) {
		this.updatedUser = updatedUser;
	}

	private TaskContents content;

	private final TaskContentService contentService = WebContexts.get().lookup(TaskContentService.class);

	public void setContent(TaskContents content) {
		this.content = content;
		if (this.id != null) {
			this.content.setTid(this.id);
		}
	}

	public TaskContents getContent() {
		if (content == null && this.id != null) {
			this.content = contentService.findByTask(id);
		}
		return content;
	}

	public String getLock() {
		if (this.lock == null) {
			this.lock = "N";
		}
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getLockedBy() {
		return lockedBy;
	}

	public void setLockedBy(String lockedBy) {
		this.lockedBy = lockedBy;
	}

	public String getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(String lockedDate) {
		this.lockedDate = lockedDate;
	}

	public String getTaskGroupId() {
		return taskGroupId;
	}

	public void setTaskGroupId(String taskGroupId) {
		this.taskGroupId = taskGroupId;
	}

	public TaskGroup getTaskGroup() {
		if (this.taskGroup == null && this.taskGroupId != null) {
			this.taskGroup = WebContexts.get().lookup(TaskGroupService.class).find(this.taskGroupId);
		}
		return taskGroup;
	}

	public void setTaskGroup(TaskGroup taskGroup) {
		this.taskGroup = taskGroup;
	}

	@Override
	public String toString() {
		return "Tasks [id=" + id + ", subject=" + subject + ", uid=" + uid + ", createdate=" + createdate
				+ ", updateby=" + updateby + ", updatedate=" + updatedate + "]";
	}

}
