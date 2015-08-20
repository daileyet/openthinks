package com.openthinks.tasks.web.utils;

import openthinks.easyweb.context.handler.WebAttributers;
import openthinks.easyweb.context.handler.WebAttributers.WebScope;

import com.openthinks.tasks.web.bean.AuthorizedUser;
import com.openthinks.tasks.web.entity.Users;

/**
 * some helpful methods
 * 
 * @author minjdai
 * 
 */
public class TaskUtils {

	public static Users sessionUser(WebAttributers webAttributes) {
		AuthorizedUser autnorizedUser = (AuthorizedUser) webAttributes.getAttribute(AuthorizedUser.AUTHORIZED_USER_ID,
				WebScope.SESSION);
		return autnorizedUser.getUser();
	}

}
