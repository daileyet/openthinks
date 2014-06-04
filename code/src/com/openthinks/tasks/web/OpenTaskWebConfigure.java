package com.openthinks.tasks.web;

import org.apache.log4j.Logger;

import sql.dhibernate.support.SessionFactory;
import sql.lang.Configurator;
import sql.lang.ConfiguratorFactory;

import com.openthinks.easyweb.annotation.configure.EasyConfigure;
import com.openthinks.easyweb.annotation.configure.RequestSuffixs;
import com.openthinks.easyweb.annotation.configure.ScanPackages;
import com.openthinks.easyweb.context.Bootstrap;
import com.openthinks.tasks.resource.ResourceManagement;
import com.openthinks.tasks.web.utils.TasksGroupIdGenerator;
import com.openthinks.tasks.web.utils.TasksIdGenerator;
import com.sina.sae.util.SaeUserInfo;

@EasyConfigure
@ScanPackages({ "com.openthinks.tasks.web" })
@RequestSuffixs(".do,.htm,.html")
public class OpenTaskWebConfigure implements Bootstrap {
	private final Logger logger = Logger.getLogger(getClass());

	private void initialSessionFactory() {
		Configurator configuration = ConfiguratorFactory
				.getInstance(ResourceManagement.getDbResource());
//		configuration
//				.setUrl("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_openthinks?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=gbk");
//		String accessKey = SaeUserInfo.getAccessKey();// "m34k5553x4"
//		String secretKey = SaeUserInfo.getSecretKey();// hmi1x23i141lji5kmxm5yy5w15345zxw152zk30h
//
//		configuration.setUserName(accessKey);
//		configuration.setUserPwd(secretKey);

		SessionFactory.setDefaultConfigurator(configuration);
	}

	private void initialGenerator() throws ClassNotFoundException {
		Class.forName(TasksIdGenerator.class.getName());
		Class.forName(TasksGroupIdGenerator.class.getName());
	}

	@Override
	public void initial() {
		initialSessionFactory();
		try {
			initialGenerator();
		} catch (ClassNotFoundException e) {
			logger.error(e);
		}
		// initialWebClassDir();
	}

	// private void initialWebClassDir() {
	// WebContexts.getServletContext().setAttribute(
	// WebProcesser.WEB_CLASS_DIR,
	// "E:\\Workspace\\eclipse\\openthinks\\build\\classes\\");
	//
	// }

}
