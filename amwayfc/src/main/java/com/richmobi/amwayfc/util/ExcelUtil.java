package com.richmobi.amwayfc.util;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.zip.Adler32;
import java.util.zip.CRC32;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtil {

	private static final char[] CHAR_TEMPLATE = new char[] { '0', '0', '0',
		'0', '0', '0', '0', '0' };
	private static final int version2003 = 2003;
	private static final int version2007 = 2007;

	// excel版本号
	private int version = version2003;

	// 开始入库的行
	private int begin = 0;

	// 入库成功的行
	private int succNum;

	private final List<String> failNums = new ArrayList<String>();
	// 将被表示成1.3922433397E10的手机号转化为13922433397,不一定是最好的转换方法
	private final DecimalFormat df = new DecimalFormat("#");

	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	
	/**
	 * 
	 * 读取excel，遍历各个小格获取其中信息
	 * 
	 * 
	 * 注意： 1.sheet， 以0开始，以workbook.getNumberOfSheets()-1结束
	 * 2.row，以0开始(getFirstRowNum)，以getLastRowNum结束
	 * 3.cell，以0开始(getFirstCellNum)，以getLastCellNum结束, 结束的数目不知什么原因与显示的长度不同，可能会偏长
	 * 
	 */
	public void readExcel(String filePath) {
		version = getVersion(filePath);
		try {
			// 创建对Excel工作簿文件的引用
			Workbook workbook = createWorkbook(filePath);

			// 获取sheet数
			for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
				succNum = 0;
				failNums.clear();
				if (workbook.getSheetAt(numSheets) != null) {
					// 获得一个sheet
					Sheet aSheet = workbook.getSheetAt(numSheets);
					for (int currRow = 0; currRow <= aSheet.getLastRowNum(); currRow++) {
						if (currRow < begin) {
							// 跳过表头
							continue;
						}

						System.out.println();

						// TODO 初始化user对象

						boolean nullRow = true;
						if (aSheet.getRow(currRow) != null) {
							// 获得一行
							Row aRow = aSheet.getRow(currRow);
							for (short cellNumOfRow = 0; cellNumOfRow <= aRow
									.getLastCellNum(); cellNumOfRow++) {
								// 是否为空行
								if (aRow.getCell(cellNumOfRow) != null) {
									// 获得一个单元格
									Cell aCell = aRow.getCell(cellNumOfRow);
									int cellType = aCell.getCellType();
									String strCell = "";
									switch (cellType) {
									// Numeric
									case HSSFCell.CELL_TYPE_NUMERIC:
										strCell = df.format(aCell
												.getNumericCellValue());
										nullRow = false;
										break;
									// String
									case HSSFCell.CELL_TYPE_STRING:
										strCell = aCell.getStringCellValue();
										nullRow = false;
										break;
									default:
										// 其它格式的数据
									}

									System.out.print(strCell + " | ");

									// 设置user对象信息
									switch (cellNumOfRow) {
									case 0:
										break;
									case 1:
										break;
									case 2:
										break;
									case 3:
										break;
									case 4:
										break;
									case 5:
										break;
									case 6:
										break;
									}
								}
							}
						}
						try {
							// TODO 入库
							if (!nullRow) {
								succNum++;
							}

						} catch (Exception e) {
							failNums.add(currRow + "");
							e.printStackTrace();
						}
					}
					System.out.println("sheet: " + numSheets + " allrow: "
							+ (aSheet.getLastRowNum() + 1) + " succnum "
							+ succNum + " skip : " + begin + " fail : "
							+ (aSheet.getLastRowNum() + 1 - succNum - begin)
							+ failNums);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Workbook createWorkbook(String filePath)
			throws FileNotFoundException, IOException {
		Workbook workbook = null;
		FileInputStream inp = null;
		if (version == version2003) {
			inp = new FileInputStream(filePath);
			workbook = new HSSFWorkbook(new POIFSFileSystem(inp));
		} else if (version == version2007) {
			workbook = new XSSFWorkbook(filePath);
		}
		return workbook;
	}

	private int getVersion(String filePath) {
		if (filePath.endsWith(".xls")) {
			version = version2003;
		} else if (filePath.endsWith(".xlsx")) {
			version = version2007;
		}
		return version;
	}

	/**
	 * 
		 * 功能: 判断类型
		 * 作者: Xuehan.Li
		 * 创建日期:2012-9-9
		 * 修改者: mender
		 * 修改日期: modifydate
		 * @param cell
		 * @return
	 */
	public static Object typeCast(Cell cell) {
		Object value = new Object();
		if(cell != null){
			switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_STRING:
					//去除空格符
//					value = Utils.clearSpaceCharacter(cell.getStringCellValue().toString());
					value = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						Date date = cell.getDateCellValue();
						if (date != null) {
							value = new SimpleDateFormat("yyyy-MM-dd").format(date);
						} else {
							value = "";
						}
					} else {
						value = new DecimalFormat("#").format(cell.getNumericCellValue());
//						System.out.println("value:"+value);
					}
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					// 导入时如果为公式生成的数据则无值
					if (!cell.getStringCellValue().equals("")) {
						value = cell.getStringCellValue();
					} else {
						value = cell.getNumericCellValue() + "";
					}
					break;
				case HSSFCell.CELL_TYPE_BLANK:
					value = "";
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					value = "";
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					value = (cell.getBooleanCellValue() == true ? "Y" : "N");
					break;
				default:
					value = "";
			}
		}else{
			value = "";
		}
		return value;
	}

	public static int toYesOrNo(Cell cell){
		String str = typeCast(cell).toString();
		return str.equals("否") || str.equals("") ? 2 : 1;
	}
	
	public static int isAdult(int age){
		return age < 18 ? 2 : 1;
	}

	public static String calculateCrc32(String source){
		CRC32 crc32 = new CRC32();
		Adler32 adler32 = new Adler32();
		adler32.update(source.getBytes());
		crc32.update(source.getBytes());
		StringBuilder builder = new StringBuilder(8);
		builder.append(Long.toHexString(crc32.getValue()));
		if (builder.length() < 8) {
			builder.append(CHAR_TEMPLATE, 0, 8 - builder.length());
		}
		System.out.println("crc32 : "+builder.toString());
		return builder.toString();
	}
	
	public static String subLogincode(Cell cell){
		String logincode = typeCast(cell).toString();
		if(logincode.indexOf("/") > -1){
			logincode = logincode.split("/")[0];
		}
		return logincode;
	}

	public static int isTakeChildren(String nature){
		int isTake = 2;
		if(nature.equals("FC") || nature.equals("特邀配偶")){
			isTake = 1;
		}
		return isTake;
	}
}
