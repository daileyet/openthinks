package com.openthinks.tasks.web.controller;

import java.io.File;
import java.io.IOException;

import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.annotation.ResponseReturn;
import com.openthinks.easyweb.context.WebContexts;
import com.openthinks.easyweb.context.handler.WebAttributers;
import com.openthinks.tasks.web.utils.applet.UploadFileApplet;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;

@Controller("/upload")
public class UploadController {

	public static final String UPLOAD_PATH = WebContexts.getServletContext()
			.getRealPath("")
			+ File.separator
			+ "static"
			+ File.separator
			+ "upload" + File.separator + "images" + File.separator;
	public static final String DEFAULTIMAGE = "";

	@Mapping("/img")
	@ResponseReturn(contentType = "text/html", charset = "UTF-8")
	public String uploadImage(WebAttributers webAttributers) throws IOException {
		String host = webAttributers.getRequest().getServerName();
		String context = webAttributers.getRequest().getContextPath();
		int port = webAttributers.getRequest().getServerPort();

		String uploadImageUrl = "http://" + host + ":" + port + context
				+ "/static/upload/images/";
		String defaultImageUrl = "http://" + host + ":" + port + context
				+ "/static/image/" + DEFAULTIMAGE;

		MultipartParser mp = new MultipartParser(webAttributers.getRequest(),
				1024 * 1024 * 10);
		mp.setEncoding("UTF-8");

		String customerName = (String) webAttributers
				.get(UploadFileApplet.CUSTOMER_FILE_NAME);
		String imageFinalUrl = defaultImageUrl;
		Part part;
		while ((part = mp.readNextPart()) != null) {

			if (part.isParam()) {
				// nothing to do
			} else if (part.isFile()) {
				FilePart fp = (FilePart) part;
				String filename = fp.getFileName();

				filename = (customerName == null || "".equals(customerName
						.trim())) ? filename : customerName;
				filename = filename + ".png";
				fp.writeTo(new File(UPLOAD_PATH + filename));

				imageFinalUrl = uploadImageUrl + filename;
			} else {
				// nothing to do
			}
		}

		return imageFinalUrl;
	}
}
