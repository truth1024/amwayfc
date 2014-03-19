package com.richmobi.amwayfc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import richmobi.commons.utils.MD5Util;

public class Utils {

	private static String phoneBasic = "((?:(?:13[0-9])|(?:15[^4,\\D])|(?:18[0,2,5-9]))\\d{8})";
	/**
	 * 
		 * 功能: 去空白符，制表符，回车符
		 * 作者: Xuehan.Li
		 * 创建日期:2012-10-30
		 * 修改者: mender
		 * 修改日期: modifydate
		 * @param value
		 * @return
	 */
	public static String clearSpaceCharacter(String value){
		String regex = "(?i)[^a-zA-Z0-9\u4E00-\u9FA5]";
		return value.replaceAll(regex, "");
	}
	
	/**
	 * 
		 * 功能: 是否匹配
		 * 作者: Xuehan.Li
		 * 创建日期:2012-10-30
		 * 修改者: mender
		 * 修改日期: modifydate
		 * @param value
		 * @param basicRegex
		 * @return
	 */
	public static boolean isMatch(String value,String basicRegex){
		Pattern p = Pattern.compile(basicRegex);
		Matcher m = p.matcher(value);
		return m.matches();
	}
	public static boolean isMobile(String value){
		String phoneRegex = "^"+phoneBasic+"$";
		return isMatch(value,phoneRegex);
	}
	
	public static String MD5Encryption(String password){
		try {
			return MD5Util.encryptByMD5(password);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static void main(String[] args) {
		System.out.println(clearSpaceCharacter("      王 冉　" ));
		System.out.println(isMobile(clearSpaceCharacter("     13434543434     ")));
	}
}
