package com.openthinks.transfer.httpclient;

import java.util.Map;

import com.openthinks.transfer.TransferData;

public class SaeTransferTest {

	public SaeTransferTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		SaeTransfer transfer = new SaeTransfer();
		TransferData data = TransferData
				.init("https://apex.oracle.com/pls/apex/open-thinks/youdao/music/api/list");
		transfer.request(data);
		Map<String, String> repHeaders = data.getResponseHeaders();
		for (String key : repHeaders.keySet()) {
			System.out.println(key + " = " + repHeaders.get(key));
		}
		System.out.println(data.getResponseContent());

	}

}
