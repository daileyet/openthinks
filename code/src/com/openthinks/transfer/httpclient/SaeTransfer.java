/**
 * 
 */
package com.openthinks.transfer.httpclient;

import java.util.Map;

import com.openthinks.transfer.Transfer;
import com.openthinks.transfer.TransferData;
import com.sina.sae.fetchurl.SaeFetchurl;
import com.sina.sae.util.SaeUserInfo;

/**
 * @author minjdai
 *
 */
public class SaeTransfer implements Transfer {

	/**
	 * 
	 */
	public SaeTransfer() {
	}

	/* (non-Javadoc)
	 * @see com.openthinks.transfer.Transfer#request(com.openthinks.transfer.TransferData)
	 */
	@Override
	public void request(TransferData data) {
		SaeFetchurl fetchUrl = new SaeFetchurl(SaeUserInfo.getAccessKey(), SaeUserInfo.getSecretKey());
		System.out.println("DEBUG: SaeTransfer >> "+data.getRequestUrl());
		System.out.println("DEBUG: SaeTransfer >> "+SaeUserInfo.getAccessKey() + "," + SaeUserInfo.getSecretKey());
		String content = fetchUrl.fetch(data.getRequestUrl());
		if(fetchUrl.getErrno()!=0){
			System.out.println(fetchUrl.getErrno()+" = "+fetchUrl.getErrmsg());
		}
		Map<String,String> responseHeaders = fetchUrl.responseHeaders();
		for(String key: responseHeaders.keySet()){
			data.addResponseHreader(key, responseHeaders.get(key));
		}
		data.setResponseContent(content);
	}

}
