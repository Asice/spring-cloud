package com.qurich.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;


public class IpAddressUtil {
   
	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
	
	public static String ip2City(String ip) {
		/*try {
			String url="http://ip.taobao.com/service/getIpInfo.php?ip="+ip;
			JSONObject json=httpRequest(url, "GET", "");
			if(json.containsKey("code")&&json.getInt("code")==0) {
				JSONObject msg=json.getJSONObject("data");
				if(msg.containsKey("region")) {
					return msg.getString("region")+msg.getString("city");
				}else {
					return msg.getString("city");
				}
			}
		}catch(Exception e) {
			System.out.println("淘宝IP获取错误");
		}*/
		return "["+ip+"]api无法获取地址";
	}
	
	
	/**  
     * 发起http请求并获取结果  
     *   
     * @param requestUrl 请求地址  
     * @param requestMethod 请求方式（GET、POST）  
     * @param outputStr 提交的数据  
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)  
     */    
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {    
        JSONObject jsonObject =new JSONObject(); 
        StringBuffer buffer = new StringBuffer();  
        InputStream inputStream=null;  
        try {  
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();   
            httpUrlConn.setReadTimeout(10*1000);//10s超时
            httpUrlConn.setDoOutput(true);    
            httpUrlConn.setDoInput(true);    
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）    
            httpUrlConn.setRequestMethod(requestMethod);    
            if ("GET".equalsIgnoreCase(requestMethod))    
                httpUrlConn.connect();    
    
            // 当有数据需要提交时    
            if (null != outputStr) {    
                OutputStream outputStream = httpUrlConn.getOutputStream();    
                // 注意编码格式，防止中文乱码    
                outputStream.write(outputStr.getBytes("UTF-8"));    
                outputStream.close();    
            }  
            //将返回的输入流转换成字符串    
            inputStream = httpUrlConn.getInputStream();    
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
    
            String str = null;    
            while ((str = bufferedReader.readLine()) != null) {    
                buffer.append(str);    
            }    
            bufferedReader.close();    
            inputStreamReader.close();    
            // 释放资源    
            inputStream.close();    
            inputStream = null;    
            httpUrlConn.disconnect(); 
            jsonObject=JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {    
              ce.printStackTrace();  
        } catch (Exception e) {    
               e.printStackTrace();  
        }finally{  
            try {  
                if(inputStream!=null){  
                    inputStream.close();  
                }  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }   
        return jsonObject;    
    } 
	
}
