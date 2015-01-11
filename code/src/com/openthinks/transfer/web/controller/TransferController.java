package com.openthinks.transfer.web.controller;

import java.io.UnsupportedEncodingException;

import com.openthinks.easyweb.annotation.Controller;
import com.openthinks.easyweb.annotation.Mapping;
import com.openthinks.easyweb.annotation.ResponseReturn;
import com.openthinks.easyweb.context.handler.WebAttributers;
import com.openthinks.transfer.Transfer;
import com.openthinks.transfer.TransferData;
import com.openthinks.transfer.httpclient.SaeTransfer;
import com.openthinks.transfer.util.CharUtil;



@Controller("/transfer")
public class TransferController {

	@Mapping("/jsonp")
	@ResponseReturn(contentType = "text/html")
	public String transfer(WebAttributers ws){
		String transferURL = (String) ws.get("TU");
		String callbackMethod = (String) ws.get("TC");
		System.out.println(transferURL);
		try {
			transferURL= CharUtil.escapeChinese(transferURL);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Transfer transfer = 
				//new HttpClientTransfer(); 
		new SaeTransfer();
		
		TransferData data= TransferData.init(transferURL);
//				.setProxyHost("cn-proxy.jp.oracle.com");
		transfer.request(data);
		return callbackMethod + "(" +data.getResponseContent()+")";
	}
	
}
