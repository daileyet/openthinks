package com.openthinks;

import com.openthinks.easyweb.annotation.configure.EasyConfigure;
import com.openthinks.easyweb.annotation.configure.RequestSuffixs;
import com.openthinks.easyweb.annotation.configure.ScanPackages;
import com.openthinks.easyweb.context.Bootstrap;
import com.openthinks.libs.sql.dhibernate.support.SessionFactory;
import com.openthinks.libs.sql.lang.Configurator;
import com.openthinks.libs.sql.lang.ConfiguratorFactory;
import com.openthinks.libs.utilities.logger.ProcessLogger;
import com.openthinks.resource.ResourceManagement;
import com.openthinks.tasks.web.utils.TasksGroupIdGenerator;
import com.openthinks.tasks.web.utils.TasksIdGenerator;

@EasyConfigure
@ScanPackages({ "com.openthinks.tasks.web", "com.openthinks.transfer.web" })
@RequestSuffixs(".do,.htm,.html")
public class OpenThinkWebConfigure implements Bootstrap {

	private void initialSessionFactory() {
		Configurator configuration = ConfiguratorFactory.getInstance(ResourceManagement.getDbResource());
		//		useSaeEnv(configuration);
		SessionFactory.setDefaultConfigurator(configuration);
	}

	@SuppressWarnings("unused")
	private void useSaeEnv(Configurator configuration) {
		//		configuration
		//				.setUrl("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_openthinks?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=gbk");
		//		String accessKey = SaeUserInfo.getAccessKey();// 
		//		String secretKey = SaeUserInfo.getSecretKey();//  

		//		configuration.setUserName(accessKey);
		//		configuration.setUserPwd(secretKey);
	}

	private void initialGenerator() throws ClassNotFoundException {
		Class.forName(TasksIdGenerator.class.getName());
		Class.forName(TasksGroupIdGenerator.class.getName());
	}

	@Override
	public void initial() {
		try {
			initialSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			ProcessLogger.error("Initial SQL Session Factory:" + e.getMessage());
		}
		try {
			initialGenerator();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			ProcessLogger.error("Initial Generator:" + e.getMessage());
		}
		//		initialWebClassDir();
	}

	//	private void initialWebClassDir() {
	//		WebContexts.getServletContext().setAttribute(
	//				WebProcesser.WEB_CLASS_DIR,
	//				"R:\\MyGit\\openthinks\\code\\build\\classes\\");
	//
	//	}

}
