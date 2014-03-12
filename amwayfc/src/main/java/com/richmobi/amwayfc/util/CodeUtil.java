package com.richmobi.amwayfc.util;

import java.util.zip.*;

/**
 * @author Li Shuqing Aug 27, 2010
 */
public class CodeUtil {
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
	
	public static void main(String[] args){
		calculateCrc32("t");
	}
}
