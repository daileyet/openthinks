package com.openthinks.transfer.httpclient;

import java.util.Map;

import com.openthinks.transfer.Transfer;
import com.openthinks.transfer.TransferData;

public class HttpClientTransferTest {


	public static void main(String[] args) {
		Transfer name = new HttpClientTransfer();
		TransferData data = TransferData
				.init("https://apex.oracle.com/pls/apex/open-thinks/youdao/music/api/list");
//				.setProxyHost("cn-proxy.jp.oracle.com");
		name.request(data);
		Map<String, String> repHeaders = data.getResponseHeaders();
		for (String key : repHeaders.keySet()) {
			System.out.println(key + " = " + repHeaders.get(key));
		}
		System.out.println(data.getResponseContent());
	}

}
