package com.openthinks.notes.web.utils.applet.support;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class FileTransfer {

	public static HttpResponse transfer(File file, String url, String fileName)
			throws ClientProtocolException, IOException {

		HttpClient client = new DefaultHttpClient();

		HttpPost post = new HttpPost(url);

		MultipartEntity multipartEntity = new MultipartEntity();
		multipartEntity.addPart("fileName", new StringBody(
				fileName != null ? fileName : file.getName()));
		FileBody fileBody = new FileBody(file, "application/octect-stream");
		multipartEntity.addPart("attachment", fileBody);

		post.setEntity(multipartEntity);

		HttpResponse response = client.execute(post);

		return response;
	}
}
