package com.openthinks.tasks.web.utils.applet;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JApplet;
import javax.swing.JLabel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.openthinks.tasks.web.utils.applet.support.AppletLogger;
import com.openthinks.tasks.web.utils.applet.support.FileTransfer;

public class UploadFileApplet extends JApplet {
	private static final long serialVersionUID = 1277735357141555795L;

	private JLabel path;
	public static final String PARAM_URL = "postUrl";
	public static final String CUSTOMER_FILE_NAME = "customerFileName";

	public String sendFile(String filePath, String fileName) {
		String postUrl = getParameter(PARAM_URL);
		return sendFile_(postUrl, filePath, fileName);
	}

	public String sendFile_(String url, String filePath, String fileName) {
		String postUrl = url;
		if (fileName != null && !"".equals(fileName.trim()))
			postUrl = postUrl + "?" + CUSTOMER_FILE_NAME + "=" + fileName;
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			try {
				AppletLogger.log("uploading file:" + file);
				path.setText(file.getAbsolutePath());
				HttpResponse response = FileTransfer.transfer(file, postUrl,
						fileName);
				if (response != null
						&& response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						return EntityUtils.toString(entity);
					}
				}
			} catch (Exception e) {
				AppletLogger.log(e);
			}
		}
		return "error";
	}

	void initComplement() {
		path = new JLabel();
		this.setLayout(new BorderLayout());
		this.add(path, BorderLayout.CENTER);
	}

	public UploadFileApplet() {
		initComplement();
	}

}
