/**
 * 
 */
package com.openthinks.notes.web.controller;

import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.annotation.ResponseReturn;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.easyweb.context.handler.WebAttributers;
import com.openthinks.notes.web.bean.AuthorizedUser;
import com.openthinks.notes.web.bean.json.OperationJson;
import com.openthinks.notes.web.entity.Users;
import com.openthinks.notes.web.service.UserService;

/**
 * @author minjdai
 * 
 */
@Controller
public class IndexController {

	UserService userService = WebContexts.get().lookup(UserService.class);

	@Mapping("/index")
	public String index() {

		return "index.jsp";

		// return "WEB-INF/jsp/index.jsp";
	}

	@Mapping("/login")
	@ResponseReturn(contentType = "text/json")
	public String login(WebAttributers attributers) {
		String name = (String) attributers.get("username");
		String pass = (String) attributers.get("password");
		Users users = userService.find(name, pass);
		OperationJson json = OperationJson.build();
		if (users == null) {
			// attributers.addError("login-error",
			// "User name or password not match.", WebScope.REQUEST);
			// return "login.jsp";
			json.error("User name or password not match.");
		} else {
			AuthorizedUser authorizedUser = AuthorizedUser.authorized(users);
			attributers.storeSession(AuthorizedUser.AUTHORIZED_USER_ID, authorizedUser);
			json.sucess();
		}
		// return WebContexts.get().lookup(TaskController.class)
		// .index(attributers);

		return json.toString();
	}

	@Mapping("/register")
	@ResponseReturn(contentType = "text/json")
	public String register(WebAttributers attributers) {
		String name = (String) attributers.get("email");
		String pass = (String) attributers.get("passwd");
		String pass2 = (String) attributers.get("passwd2");
		OperationJson json = OperationJson.build();
		if (name == null || "".equals(name.trim())) {
			json.error("User name or Email cannot be empty.");
			return json.toString();
		}

		if (pass == null || "".equals(pass.trim())) {
			json.error("Password cannot be empty.");
			return json.toString();
		}

		if (pass == null || "".equals(pass.trim()) || !pass.equals(pass2)) {
			json.error("Two entered passwords are not matched.");
			return json.toString();
		}

		Users user = new Users();
		user.setUserName(name);
		user.setUserPassword(pass);

		try {
			userService.add(user);
			json.sucess("Register sucessfully.");
		} catch (Exception e) {
			json.error("Register fail.");
		}
		return json.toString();
	}

	@Mapping("/logout")
	public String logout(WebAttributers attributers) {
		attributers.storeSession(AuthorizedUser.AUTHORIZED_USER_ID, null);
		attributers.getRequest().getSession().invalidate();
		return index();
	}
}
