/**
 * 
 */
package com.openthinks.notes.web.bean.json;

import com.google.gson.Gson;

/**
 * @author minjdai
 *
 */
public class LockJson {
	private String sucess;
	private String info;

	public static LockJson build() {
		return new LockJson();
	}

	public String getSucess() {
		return sucess;
	}

	public LockJson setSucess(String sucess) {
		this.sucess = sucess;
		return this;
	}

	public String getInfo() {
		return info;
	}

	public LockJson setInfo(String info) {
		this.info = info;
		return this;
	}

	@Override
	public String toString() {
		return toJson(this);
	}

	public static String toJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

}
