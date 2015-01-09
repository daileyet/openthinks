/**
 * 
 */
package com.openthinks.transfer.httpclient;

import java.io.IOException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.openthinks.transfer.Transfer;
import com.openthinks.transfer.TransferData;

/**
 * @author minjdai
 * 
 */
public class HttpClientTransfer implements Transfer {

	/**
	 * 
	 */
	public HttpClientTransfer() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.openthinks.transfer.Transfer#request(com.openthinks.transfer.
	 * TransferRequestData)
	 */
	@Override
	public void request(TransferData req) {
		HttpClient client = new DefaultHttpClient();
		if (req.getProxyHost() != null && !"".equals(req.getProxyHost().trim())) {
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					new HttpHost(req.getProxyHost(), req.getProxyPort()));
		}
		HttpGet gets = new HttpGet(req.getRequestUrl());
		Map<String,String> requestHeaders = req.getRequestHeaders();
		for(String key:requestHeaders.keySet()){
			gets.addHeader(key, requestHeaders.get(key));
		}
		Map<String,String> requestParams = req.getRequestParams();
		for(String key : requestParams.keySet()){
			gets.getParams().setParameter(key, requestParams.get(key));
		}
		
		try {
			HttpResponse response = client.execute(gets);
			for (Header h : response.getAllHeaders()) {
				req.addResponseHreader(h.getName(), h.getValue());
			}
			String content = EntityUtils.toString(response.getEntity());
			req.setResponseContent(content);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

		

}
