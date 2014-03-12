package com.richmobi.amwayfc.util;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;


import sun.misc.BASE64Decoder;

public class Base64ToImage {
	/**
	 * 
		 * 功能: Base64编码转图片
		 * 作者: Xuehan.Li
		 * 创建日期:2012-7-23
		 * @param imgStr Base64编码
		 * @param imageName 图片名称
		 * @return boolean
	 */
	public static boolean GenerateImage(String imgStr,String imageName){   //对字节数组字符串进行Base64解码并生成图片   
        if (imgStr == null){//图像数据为空   
            return false;  
        }
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码   
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i){  
                if(b[i]<0){
                	//调整异常数据   
                    b[i]+=256;  
                }  
            }  
            
            //生成jpeg图片   
            String imgFilePath = ServletActionContext.getServletContext().getRealPath("/postcard/images/upload/photo/")+"/"+imageName+".jpg";//新生成的图片  
            System.out.println(imgFilePath);
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }
        catch (Exception e)   
        {  
            return false;  
        }  
    } 
}
