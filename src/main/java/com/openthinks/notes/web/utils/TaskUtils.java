package com.openthinks.notes.web.utils;

import com.openthinks.easyweb.context.handler.WebAttributers;
import com.openthinks.easyweb.context.handler.WebAttributers.WebScope;
import com.openthinks.notes.web.bean.AuthorizedUser;
import com.openthinks.notes.web.entity.Users;

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
