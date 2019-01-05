package com.qurich.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	
	public static void saveFile(String filePath,StringBuffer sb) throws IOException,Exception{
		 File file=new File(filePath);
         if(!file.exists()) {
        	 File fileParent = file.getParentFile();
        	 if (!fileParent.exists()) {
        		 fileParent.mkdirs();
        	 }
        	 file.createNewFile();
         }else {
        	 throw new Exception("文件已存在,"+filePath);
         }
         FileOutputStream out=new FileOutputStream(file,true);        
         out.write(sb.toString().getBytes("utf-8"));      
         out.close();
	}
}
