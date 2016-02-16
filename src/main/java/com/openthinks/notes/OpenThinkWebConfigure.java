package com.openthinks.notes;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import com.openthinks.easyweb.annotation.configure.EasyConfigure;
import com.openthinks.easyweb.annotation.configure.RequestSuffixs;
import com.openthinks.easyweb.annotation.configure.ScanPackages;
import com.openthinks.easyweb.context.Bootstrap;
import com.openthinks.libs.sql.dhibernate.support.SessionFactory;
import com.openthinks.libs.sql.lang.Configurator;
import com.openthinks.libs.sql.lang.ConfiguratorFactory;
import com.openthinks.libs.utilities.logger.ProcessLogger;
import com.openthinks.notes.resource.ResourceManagement;
import com.openthinks.notes.web.utils.TasksGroupIdGenerator;
import com.openthinks.notes.web.utils.TasksIdGenerator;

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

	@Override
	public void cleanUp() {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				ProcessLogger.info(String.format("deregistering jdbc driver: %s", driver));
			} catch (SQLException e) {
				ProcessLogger.error(String.format("Error deregistering driver %s", driver));
			}

		}
	}

	//	private void initialWebClassDir() {
	//		WebContexts.getServletContext().setAttribute(
	//				WebProcesser.WEB_CLASS_DIR,
	//				"R:\\MyGit\\openthinks\\code\\build\\classes\\");
	//
	//	}

}
