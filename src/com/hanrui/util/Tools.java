package com.hanrui.util;

public class Tools {

	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 * @param str	ÒªÅÐ¶ÏµÄ×Ö·û´®
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null||str.equals("")){
			return true;
		}
		return false;
	}
}
