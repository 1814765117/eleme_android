package com.elemexyh.clientUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

public class HttpUtil {
	//封装的发送请求函数
    public static void sendHttpRequest(final String address,final HashMap<String,String> params, final HttpCallbackListener listener) throws UnsupportedEncodingException {
       
    	if (!HttpUtil.isNetworkAvailable()){
            //这里写相应的网络设置处理
            return;
        }
      
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                String requestHeader = null;//请求头
                byte[] requestBody = null;//请求体
                
                try{               	
                	//获得请求体
                	requestBody = new String(getRequestBody(params)).getBytes("UTF-8");
                	//调试日志
                	Log.v("address", address);
                    URL url = new URL(address);                 
                    //使用HttpURLConnection
                    connection = (HttpURLConnection) url.openConnection();
                    //设置请求方法和参数
                    connection.setRequestMethod("POST");   
                    connection.setUseCaches(false);               //使用Post方式不能使用缓存
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);  
                    
                    //设置请求头，post方式必须要写，写哪些视情况而定
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");                
                    connection.setRequestProperty("Charset", "utf-8");
                    
                    connection.connect();
                    
                    //获取conn的输出流
                    OutputStream os=connection.getOutputStream();                                            
                    //将请求体写入到conn的输出流中
                    os.write(requestBody);
                    //记得调用输出流的flush方法
                    os.flush();
                    //关闭输出流
                    os.close();
                   
                    //获取返回结果              
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    //成功则回调onFinish
                    if (listener != null){
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //出现异常则回调onError
                    if (listener != null){
                        listener.onError(e);
                    }
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
        
    
    //判断当前网络是否可用
    public static boolean isNetworkAvailable(){
        return true;
    }
    
    //组装出请求体，eg:"name=孙群&age=27"
    public static String getRequestBody(HashMap<String,String> params) throws UnsupportedEncodingException {
    	//将请求体组成一个字符串
        StringBuilder bodyBuilder=new StringBuilder("");
        for(Map.Entry<String, String> entry:params.entrySet())
        {	
        	
            bodyBuilder.append(entry.getKey()).append("=");
            bodyBuilder.append(URLEncoder.encode(entry.getValue(), "utf-8"));
            bodyBuilder.append("&");
        }
        bodyBuilder.deleteCharAt(bodyBuilder.length() - 1);
        String requestBody=bodyBuilder.toString();
        return requestBody;
    }
	
	
	
	
}
