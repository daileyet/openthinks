package com.openthinks.notes.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.easyweb.context.handler.WebAttributers;
import com.openthinks.easyweb.context.handler.WebAttributers.WebScope;
import com.openthinks.notes.web.bean.AuthorizedUser;

/**
 * Servlet Filter implementation class AuthorFilter
 */
// @WebFilter(initParams = { @WebInitParam(name = "exclude", value =
// "login.,/index.,/static/,img.") }, servletNames = { "easyweb" })
public class AuthorizeFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthorizeFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		// TODO Auto-generated method stub
		// place your code here
		WebAttributers webAttributers = new WebAttributers((HttpServletRequest) request, (HttpServletResponse) response);

		// ///////////////////////////////////////
		//Users users = new Users();
		//users.setId("1");
		//.setUserName("dailey");
		//users.setUserPassword("1234");

		// users.setId("2");
		// users.setUserName("jack");
		// users.setUserPassword("123456");

		//AuthorizedUser authorizedUser = new AuthorizedUser(users, "Y");
		//webAttributers.storeSession(AuthorizedUser.AUTHORIZED_USER_ID, authorizedUser);
		// ////////////////////////////////////////

		AuthorizedUser user = (AuthorizedUser) webAttributers.getAttribute(AuthorizedUser.AUTHORIZED_USER_ID,
				WebScope.SESSION);
		if (user == null) {
			if (!notExclude(webAttributers)) {
				webAttributers.addError("authorize-error", "The page need login first, please go to login.",
						WebScope.REQUEST);
				webAttributers.getRequest().getRequestDispatcher("/login.jsp").forward(request, response);
				// webAttributers.getResponse().sendRedirect("login.jsp");
				return;
			}
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	private boolean notExclude(WebAttributers webAttributers) {
		String requestURI = webAttributers.getRequest().getRequestURI();

		String[] excluePatterns = this.excluedPattern.split(",");
		for (String pattern : excluePatterns) {
			if (pattern.endsWith(".")) {// suffix

				for (String suffix : WebContexts.get().getWebConfigure().getRequestSuffix().options()) {
					String excluedSuffix = pattern + suffix.substring(1);
					if (requestURI.indexOf(excluedSuffix) > 0) {
						return true;
					}
				}

			} else {
				//fix bug exclude path
				if (requestURI.indexOf(pattern) >= 0) {
					return true;
				}
			}
		}

		return false;
	}

	private String excluedPattern;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.excluedPattern = fConfig.getInitParameter("exclude");
		if (this.excluedPattern == null) {
			this.excluedPattern = "";
		}
	}

}
