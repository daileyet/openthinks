/**
 * 
 */
package com.openthinks.transfer.httpclient;

import java.util.Map;

import com.openthinks.transfer.Transfer;
import com.openthinks.transfer.TransferData;
import com.sina.sae.fetchurl.SaeFetchurl;

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
		SaeFetchurl fetchUrl = new SaeFetchurl();
		String content = fetchUrl.fetch(data.getRequestUrl());
		Map<String,String> responseHeaders = fetchUrl.responseHeaders();
		for(String key: responseHeaders.keySet()){
			data.addResponseHreader(key, responseHeaders.get(key));
		}
		data.setResponseContent(content);
	}

}
