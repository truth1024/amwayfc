package com.richmobi.amwayfc.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Adler32;
import java.util.zip.CRC32;

import richmobi.commons.utils.MD5Util;

public class Utils {

	private static String phoneBasic = "((?:(?:13[0-9])|(?:15[^4,\\D])|(?:18[0,2,5-9]))\\d{8})";
	
	private static final char[] CHAR_TEMPLATE = new char[] { '0', '0', '0',
		'0', '0', '0', '0', '0' };

	public static String calculateCrc32(String source){
		CRC32 crc32 = new CRC32();
		Adler32 adler32 = new Adler32();
		adler32.update(source.getBytes());
		System.out.println(adler32.getValue());
		crc32.update(source.getBytes());
		StringBuilder builder = new StringBuilder(8);
		System.out.println(crc32.getValue());
		System.out.println(Long.toHexString(crc32.getValue()));
		builder.append(Long.toHexString(crc32.getValue()));
		if (builder.length() < 8) {
			builder.append(CHAR_TEMPLATE, 0, 8 - builder.length());
		}
		System.out.println(builder.toString());
		return builder.toString();
	}
	
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
	* @Title: dateFormat
	* @Description: 日期格式化（yyyy-MM-dd）
	* @param @param myDate
	* @param @return
	* @return String
	* @throws
	 */
	public static String dateFormat(Date myDate){
		return basicFormat(myDate, "yyyy-MM-dd");
	}
	
	public static String monthFormat(Date myDate){
		return basicFormat(myDate,"M月dd日");
	}
	
	/** 
	* @Title: basicFormat
	* @Description: 自定义日期格式化
	* @param @param myDate
	* @param @param fromatString
	* @param @return
	* @return String
	* @throws 
	*/ 
	public static String basicFormat(Date myDate,String fromatString){
		if(myDate != null){
			SimpleDateFormat myFormat = new SimpleDateFormat(fromatString);
			return myFormat .format(myDate);
		}else{
			return "";
		}
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
