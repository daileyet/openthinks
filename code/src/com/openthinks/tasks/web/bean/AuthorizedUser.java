/**
 * 
 */
package com.openthinks.tasks.web.bean;

import com.openthinks.tasks.web.entity.Users;

/**
 * @author minjdai
 * 
 */
public class AuthorizedUser {
	public static final String AUTHORIZED_USER_ID = "$$%%^&*()$%{+=}|.>//<!22@@#~~";
	private Users user;
	private String authorized = "N";

	public AuthorizedUser() {
	}

	public AuthorizedUser(Users user, String authorized) {
		super();
		this.user = user;
		this.authorized = authorized;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getAuthorized() {
		return authorized;
	}

	public void setAuthorized(String authorized) {
		this.authorized = authorized;
	}

	public static AuthorizedUser authorized(Users users) {
		return new AuthorizedUser(users, "Y");
	}

	@Override
	public String toString() {
		return user.getUserName();
	}

}
