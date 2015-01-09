package com.openthinks.transfer;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransferData implements Serializable {
	private static final long serialVersionUID = 5124400335840273838L;
	private String requestUrl;
	private Map<String, String> requestHeaders = new ConcurrentHashMap<String, String>();
	private Map<String, String> requestParams = new ConcurrentHashMap<String, String>();
	private Map<String, String> responseHeaders = new ConcurrentHashMap<String, String>();
	private String responseContent;
	private String proxyHost;
	private int proxyPort=80;
	
	
	public static TransferData init(String reqUrl){
		return new TransferData(reqUrl);
	}
	
	public TransferData() {
	}
	
	public TransferData(String reqUrl){
		this.requestUrl = reqUrl;
	}


	
	public TransferData addRequestHreader(String headerName, String headerValue){
		requestHeaders.put(headerName, headerValue);
		return this;
	}
	
	
	public TransferData addRequestParam(String paramName, String paramValue){
		requestParams.put(paramName, paramValue);
		return this;
	}
	
	public TransferData addResponseHreader(String headerName, String headerValue){
		responseHeaders.put(headerName, headerValue);
		return this;
	}
	
	
	public String getRequestUrl() {
		return requestUrl;
	}


	public TransferData setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
		return this;
	}


	public String getResponseContent() {
		return responseContent;
	}


	public TransferData setResponseContent(String responseContent) {
		this.responseContent = responseContent;
		return this;
	}


	public String getProxyHost() {
		return proxyHost;
	}


	public TransferData setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
		return this;
	}


	public int getProxyPort() {
		return proxyPort;
	}


	public TransferData setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
		return this;
	}

	public Map<String, String> getRequestHeaders() {
		return Collections.unmodifiableMap(requestHeaders);
	}

	public Map<String, String> getRequestParams() {
		return Collections.unmodifiableMap(requestParams);
	}


	public Map<String, String> getResponseHeaders() {
		return Collections.unmodifiableMap(responseHeaders);
	}


	
	
}