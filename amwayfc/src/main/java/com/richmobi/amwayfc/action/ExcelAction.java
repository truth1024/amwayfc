/**   
* @Title: ExcelAction.java
* @Package com.richmobi.amwayfc.action
* @Description: 
* @author Xuehan.Li
* @date 2014年3月17日 下午4:25:18
* @version V1.0
*/ 
package com.richmobi.amwayfc.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import richmobi.commons.utils.Configer;

import com.richmobi.amwayfc.domain.Journey;
import com.richmobi.amwayfc.domain.Login;
import com.richmobi.amwayfc.domain.PageResult;
import com.richmobi.amwayfc.domain.Sms;
import com.richmobi.amwayfc.domain.User;
import com.richmobi.amwayfc.service.LoginService;
import com.richmobi.amwayfc.service.UserService;
import com.richmobi.amwayfc.util.ExcelUtil;
import com.richmobi.amwayfc.util.Utils;


/** 
 * @ClassName: ExcelAction
 * @Description: 
 * @author Xuehan.Li
 * @date 2014年3月17日 下午4:25:18
 * 
 */
public class ExcelAction extends BasicAction {

	private static final long serialVersionUID = 8121849939553481144L;
	private static final Logger log = Logger.getLogger(ExcelAction.class);
	
	private String tip;
	private File excelFile; 			//上传的文件
    private String excelFileFileName; 	//保存原始文件名     
    private User user;
    private String fileName;			//文件名
    
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;
    
    
    //判断文件类型
    public Workbook createWorkBook(InputStream is) throws IOException{  
        if(excelFileFileName.toLowerCase().endsWith("xls")){//excel2003
            return new HSSFWorkbook(is);  
        }else if(excelFileFileName.toLowerCase().endsWith("xlsx")){//excel2007
            return new XSSFWorkbook(is);
        }else{
        	return null;
        }
    }
    
    public InputStream getExeclStream(){
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = ExcelUtil.createSheet(workbook, 1);
        switch(1){
        case 1:
        	fileName = "users.xls";
        	sheet = users(sheet);
        	break;
        }
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] ba = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        return bais;
	}
    
    public HSSFSheet users(HSSFSheet sheet){
		PageResult<User> upr = userService.getUsersByCondition(null, 0, 0);
		List<User> us = upr.getData();
		if(us != null){
			User u = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			int index = 1;
			for(int i = 0,len = us.size();i<len;i++){
				u = us.get(i);
				row = sheet.createRow(index);
				cell = row.createCell(0);
				cell.setCellValue(u.getAreacode());
				cell = row.createCell(1);
				cell.setCellValue(u.getAreaname());
				cell = row.createCell(2);
				cell.setCellValue(u.getProvince());
				cell = row.createCell(3);
				cell.setCellValue(u.getCity());
				cell = row.createCell(4);
				cell.setCellValue(u.getStore());
				cell = row.createCell(5);
				cell.setCellValue(u.getLogincode());
				cell = row.createCell(6);
				cell.setCellValue(u.getNature());
				cell = row.createCell(7);
				cell.setCellValue(u.getName());
				cell = row.createCell(8);
				cell.setCellValue(u.getRelation());
				cell = row.createCell(9);
				cell.setCellValue(u.getSex());
				cell = row.createCell(10);
				cell.setCellValue(Utils.dateFormat(u.getBirthdate()));
				cell = row.createCell(11);
				cell.setCellValue(u.getAge());
				cell = row.createCell(12);
				cell.setCellValue(ExcelUtil.toYesOrNo(u.getIsjoin()));
				cell = row.createCell(13);
				cell.setCellValue(u.getVisa());
				cell = row.createCell(14);
				cell.setCellValue(u.getAirticket());
				cell = row.createCell(15);
				cell.setCellValue(u.getDiet());
				cell = row.createCell(16);
				cell.setCellValue(u.getPhone());
				u = us.get(i);
				index++;
				List<Journey> js = u.getJs();
				if(js != null && js.size() > 0){
					row = sheet.createRow(index);
					cell = row.createCell(0);
					cell.setCellValue("日期");
					cell = row.createCell(1);
					cell.setCellValue("行程");
					index++;
					for(Journey j : js){
						row = sheet.createRow(index);
						cell = row.createCell(0);
						cell.setCellValue(Utils.monthFormat(j.getStart()));
						cell = row.createCell(1);
						cell.setCellValue(j.getTitle());
						index++;
					}
				}
			}
		}
		return sheet;
	}
    
    @Override
	public String execute() throws Exception {
		return SUCCESS;
	}
    
    public String importExcel() throws Exception{
        Workbook book = createWorkBook(new FileInputStream(excelFile));
        if(book == null){
        	tip = "请选择excel格式文件";
        	return SUCCESS;
        }
        //book.getNumberOfSheets();  判断Excel文件有多少个sheet
        log.debug(book.getNumberOfSheets());
        //获取激活的Sheet
        log.debug(book.getActiveSheetIndex());
        Sheet sheet = book.getSheetAt(book.getActiveSheetIndex());
        int sum = sheet.getLastRowNum();
        int successNum = 0;
        StringBuilder failedPhone = new StringBuilder();
        List<User> us = new ArrayList<User>();
        List<Login> ls = new ArrayList<Login>();
        Map<String,Sms> sMap = new HashMap<String, Sms>();
        //默认密码
        String password = Configer.get("default.password");
        String md5Password = Utils.MD5Encryption(password);
        Sms s = new Sms();
    	//遍历用户信息
    	for (int i = 1;i <= sum;i++){
    		Row ros = sheet.getRow(i);
			try {
				User u = new User();
				u.setAreacode(ExcelUtil.typeCast(ros.getCell(0)).toString());
				u.setAreaname(ExcelUtil.typeCast(ros.getCell(1)).toString());
				u.setProvince(ExcelUtil.typeCast(ros.getCell(2)).toString());
				u.setCity(ExcelUtil.typeCast(ros.getCell(3)).toString());
				String logincode = ExcelUtil.subLogincode(ros.getCell(5));
				u.setLogincode(logincode);
				u.setStore(ExcelUtil.typeCast(ros.getCell(4)).toString());
				String nature = ExcelUtil.typeCast(ros.getCell(6)).toString();
				u.setNature(nature);
				u.setIstake(ExcelUtil.isTakeChildren(nature));
				String name = ExcelUtil.typeCast(ros.getCell(7)).toString();
				u.setName(name);
				u.setRelation(ExcelUtil.typeCast(ros.getCell(8)).toString());
				u.setSex(ExcelUtil.typeCast(ros.getCell(9)).toString());
				Date birthDate = null;
				try {
					birthDate = ros.getCell(10).getDateCellValue();
				} catch (Exception e) {
//					e.printStackTrace();
					log.debug(name+" birthdate is error!");
				}
				u.setBirthdate(birthDate);
				String hasAge = ExcelUtil.typeCast(ros.getCell(11)).toString();
				int age = 0;
				if(hasAge != null && !hasAge.equals("")){
					age = Integer.parseInt(hasAge);
				}
				u.setAge(age);
				u.setIsadult(ExcelUtil.isAdult(age));
				u.setIsjoin(ExcelUtil.toYesOrNo(ros.getCell(12)));
				u.setVisa(ExcelUtil.typeCast(ros.getCell(13)).toString());
				u.setAirticket(ExcelUtil.typeCast(ros.getCell(14)).toString());
				u.setDiet(ExcelUtil.typeCast(ros.getCell(15)).toString());
				String phone = ExcelUtil.typeCast(ros.getCell(16)).toString();
				u.setPhone(phone);
				us.add(u);
				
				if(sMap.get(logincode) == null){
					Login l = new Login();
					l.setLogincode(logincode);
//					String password = ExcelUtil.calculateCrc32(logincode+name);
//					String md5Password = Utils.MD5Encryption(password);
					l.setPassword(password);
					l.setMd5password(md5Password);
					ls.add(l);
//					Sms s = new Sms();
//					s.setName(name);
//					s.setPhone(phone);
//					s.setPassword(password);
					sMap.put(logincode, s);
				}
				successNum++;
			} catch (Exception e){
				e.printStackTrace();
			}
    	}
    		try{
//	    		if(user != null){
//	    			String pho = user.getPhone();
//	    			if(StringUtils.isNotBlank(pho)){
////		    			smsService.sendSuccessSMS(user);
//	    				successNum++;
//	    			}
//	    		}
    			loginService.batchInsert(ls);
    			userService.batchInsertUser(us);
    		}catch(Exception e){
    			log.error(e.getMessage());
    			if(e.getMessage().indexOf("Duplicate") > -1){
    				tip = "Duplicate";
    				return ERROR;
    			}
    		}
    	log.debug(" us size : "+us.size()+" ; ls size : "+ls.size());
    	tip = "总条数："+sum+"，成功导入："+successNum+"。\n"+failedPhone.toString();
        return SUCCESS;
    }
    
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public File getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}
	public String getExcelFileFileName() {
		return excelFileFileName;
	}
	public void setExcelFileFileName(String excelFileFileName) {
		this.excelFileFileName = excelFileFileName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
