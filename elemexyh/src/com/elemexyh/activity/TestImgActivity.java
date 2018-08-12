package com.elemexyh.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class TestImgActivity extends Activity {
 
	Button downloadPic;
	ImageView image;
	Bitmap bitmap = null;
	private static final String SERVER_IP = "http://192.168.1.105:8080";
	private static final int REQUEST_TIMEOUT = 5 * 1000;    			// 设置请求超时5秒钟
	private static final int SO_TIMEOUT = 10 * 1000;        			// 设置等待数据超时时间10秒钟
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_img);
		
		image = (ImageView)findViewById(R.id.image);
//		downloadPic = (Button)findViewById(R.id.btn_downloadPic);
//		downloadPic.setOnClickListener(new OnClickListener() {	
//			@Override
//			public void onClick(View v) {
//				new DownloadPicTask().execute("GET_PIC", "shaxian");
//			}
//		});
		new DownloadPicTask().execute("GET_PIC", "shaxian");
	}
	
	private class DownloadPicTask extends AsyncTask<String, Void, String>{
 
		@Override
		protected String doInBackground(String... params) {
			String result = "";
			// 创建HttpPost
			HttpPost httpRequest = new HttpPost(SERVER_IP + "/elemexyhServer/" + "GetPicServlet");
			// 创建请求参数
			List<NameValuePair> reqParams = new ArrayList<NameValuePair>();
			reqParams.add(new BasicNameValuePair("request", params[0]));
			reqParams.add(new BasicNameValuePair("restaurantname", params[1]));
			try{
				// 设置请求参数
				httpRequest.setEntity(new UrlEncodedFormEntity(reqParams, HTTP.UTF_8));
				BasicHttpParams httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
				HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
				HttpClient client = new DefaultHttpClient(httpParams);
				// 发送post请求
				HttpResponse httpResponse = client.execute(httpRequest);
				if(httpResponse.getStatusLine().getStatusCode() == 200){
					result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
					result = SERVER_IP + "/elemexyhServer/" + result;
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		@Override
		protected void onPostExecute(final String result) {
			if(!result.equals(""))
			new Thread(){
				@Override
				public void run() {
					bitmap = getPic(result);
					Message msg = new Message();
					msg.what = 0x001;
					msg.obj = bitmap;
					handler.sendMessage(msg);
				};
			}.start();
		}
	}
	
	/**
	 * 从服务器获取图片
	 * @param uriPic 图片地址
	 * @return 返回Bitmap
	 */
	private Bitmap getPic(String uriPic){
		URL imageUrl = null;
		Bitmap bitmap = null;
		try {
			imageUrl = new URL(uriPic);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
			InputStream in = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bitmap;
	}
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0x001){
				image.setImageBitmap((Bitmap)msg.obj);
			}
		};
	};
}