package com.elemexyh.activity;

import java.util.HashMap;

import com.elemexyh.clientUtil.HttpCallbackListener;
import com.elemexyh.clientUtil.HttpUtil;
import com.elemexyh.entity.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {		
	private EditText loginNameText;
	private EditText loginPwdText;
	
	private ImageView aboveView;
	private TextView above_title;
	private TextView urlView;
	private Button loginButton;
	private TextView signupView;
	
	public static final String PREFERENCE_NAME = "SaveSetting";
	public static int MODE = Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE;
	
	//用于接收Http请求的servlet的URL地址，请自己定义	
	private String originAddress = "http://10.0.2.2:8080/elemexyhServer/userS?mark=query";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
				
		loginNameText=(EditText)findViewById(R.id.loginNameText);
		loginPwdText=(EditText)findViewById(R.id.loginPwdText);
		
		aboveView=(ImageView)findViewById(R.id.aboveView);
		//显示登录后的用户名
		above_title=(TextView)findViewById(R.id.above_title);
		//显示url
		urlView=(TextView)findViewById(R.id.urlView);
		loginButton=(Button)findViewById(R.id.loginButton);
		signupView=(TextView)findViewById(R.id.signupView); 
		
		//点击小图标回到主界面
		aboveView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
		//登录按钮
		loginButton.setOnClickListener(loginButtonListener);
		
		//点击跳转到注册页面
		signupView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
				startActivity(intent);
			}
			
		});
				
	}
	
	
	OnClickListener loginButtonListener=new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//构造HashMap
	        HashMap<String, String> params = new HashMap<String, String>();
	        params.put("userName",loginNameText.getText().toString());
	        params.put("userPwd", loginPwdText.getText().toString());
	        try {
	        	
	          //发送请求
	            HttpUtil.sendHttpRequest(originAddress,params, new HttpCallbackListener() {
	                @Override
	                public void onFinish(String response) {
	                    Message message = new Message();
	                    message.obj = response;
	                    mHandler.sendMessage(message);
	                }

	                @Override
	                public void onError(Exception e) {
	                    Message message = new Message();
	                    message.obj = e.toString();
	                    mHandler.sendMessage(message);
	                }
	            });
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        
		}
		
	};
	
	
	 //用于处理消息的Handler
	 Handler mHandler = new Handler(){
	        @Override
	        public void handleMessage(Message msg) {
	            super.handleMessage(msg);
	            
	            /*if ("OK".equals(msg.obj.toString())){
	                result = "登录成功";
	                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
	            }else if ("Wrong".equals(msg.obj.toString())){
	                result = "用户名或密码不正确";
	            }else {
	            	result = msg.obj.toString();                
	            }
	            Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();*/	            	    	       
	            
	            String rec[]=msg.obj.toString().split("&");
	            if("OK".equals(rec[0])) {
	            	above_title.setText(rec[1]);
	            	Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
	            	boolean flag=true;
	            	saveSharedPreferences(rec[1],flag);
	            	finish();
	            	
	            }
	            else if("Wrong".equals(rec[0])){
	            	Toast.makeText(LoginActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();	            	
	            }
	            else {
	            	Toast.makeText(LoginActivity.this,msg.obj.toString(), Toast.LENGTH_SHORT).show();
	            }
	            
	        }
	    };
	    
	    
	    private void saveSharedPreferences(String rec,boolean flag) {
	    	//将登录的名字保存到sharepreferences，方便主界面使用
			SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);
			SharedPreferences.Editor editor=sharedPreferences.edit();
			editor.putString("Name", rec);
			//代表进行了登录行为，设flag为true
			editor.putBoolean("Flag", flag);
			editor.commit();
	    }
	
	
	
	
}

