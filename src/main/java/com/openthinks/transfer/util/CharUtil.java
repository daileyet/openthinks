package com.openthinks.transfer.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

/**
 * �ַ�ͨ�ù�����
 * 
 */
public class CharUtil {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "http://apistore.baidu.com/microservice/cityinfo?cityname=�Ͼ�";
		System.out.println();
		System.out.println(CharUtil.escapeChinese(s));

	}

	/**
	 * used for SAE fetch url service (JAVA), need the fetch url can not include chinese char, should encode first 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String escapeChinese(String url) throws UnsupportedEncodingException {
		char[] ch = url.toCharArray();
		String urlEscape = new String(url);
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				String encode = URLEncoder.encode(String.valueOf(c), "UTF-8");
				if (encode != null) {
					urlEscape = urlEscape.replace(c + "", encode);
				}
			}
		}

		return urlEscape;
	}

	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	public static boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isChineseByREG(String str) {
		if (str == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
		return pattern.matcher(str.trim()).find();
	}

	public static boolean isChineseByName(String str) {
		if (str == null) {
			return false;
		}
		String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(str.trim()).find();
	}
}