package com.openthinks.transfer.web.controller;

import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.annotation.ResponseReturn;
import com.openthinks.easyweb.context.handler.WebAttributers;
import com.openthinks.transfer.Transfer;
import com.openthinks.transfer.TransferData;
import com.openthinks.transfer.httpclient.HttpClientTransfer;



@Controller("/transfer")
public class TransferController {

	@Mapping("/jsonp")
	@ResponseReturn(contentType = "text/html")
	public String transfer(WebAttributers ws){
		String transferURL = (String) ws.get("TU");
		System.out.println(transferURL);
		Transfer transfer = new HttpClientTransfer(); 
		TransferData data= TransferData.init(transferURL).setProxyHost("cn-proxy.jp.oracle.com");
		transfer.request(data);
		return data.getResponseContent();
	}
	
}
