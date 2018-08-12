package com.elemexyh.activity;

import java.util.HashMap;

import com.elemexyh.clientUtil.HttpCallbackListener;
import com.elemexyh.clientUtil.HttpUtil;
import com.elemexyh.entity.User;

import android.app.Activity;
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

public class SignupActivity extends Activity {		
	private ImageView aboveView;
	private Button signupButton;
	
	private EditText signupNameText;
	private EditText signupPwdText;
	private EditText confirmPwdText;
	
	//用于接收Http请求的servlet的URL地址，请自己定义	
		private String originAddress = "http://10.0.2.2:8080/elemexyhServer/userS?mark=add";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		signupNameText=(EditText)findViewById(R.id.signupNameText);
		signupPwdText=(EditText)findViewById(R.id.signupPwdText);
		confirmPwdText=(EditText)findViewById(R.id.confirmPwdText);
		
		aboveView=(ImageView)findViewById(R.id.aboveView);
		signupButton=(Button)findViewById(R.id.signupButton);
		
		
		aboveView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
		signupButton.setOnClickListener(signupButtonListener);		
		
	}
	
		OnClickListener signupButtonListener=new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 /*String name=signupNameText.getText().toString();
					String pwd=signupPwdText.getText().toString();
					String cfmpwd=confirmPwdText.getText().toString();
					if(pwd.equals(cfmpwd)) {
						User user=new User();
						user.name=name;
						user.pwd=pwd;
						long column=userDBAdapter.insert(user);
						if(column==-1) {
							Toast.makeText(SignupActivity.this, "添加过程错误", Toast.LENGTH_LONG).show();
						}
						else {
							Toast.makeText(SignupActivity.this, "成功添加数据", Toast.LENGTH_LONG).show();
							finish();					 
						}
					}
					else {
						Toast.makeText(SignupActivity.this, "两次密码不一致，请重新输入", Toast.LENGTH_LONG).show();
					}*/
				String name=signupNameText.getText().toString();
				String pwd=signupPwdText.getText().toString();
				String cfmpwd=confirmPwdText.getText().toString();
				if(pwd.equals(cfmpwd)) {
					
					//构造HashMap
			        HashMap<String, String> params = new HashMap<String, String>();
			        params.put("userName",signupNameText.getText().toString());
			        params.put("userPwd", signupPwdText.getText().toString());			       
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
				else {
					Toast.makeText(SignupActivity.this, "两次密码不一致，请重新输入", Toast.LENGTH_LONG).show();
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
		            	String result=rec[1]+",注册成功";
		            	Toast.makeText(SignupActivity.this,result, Toast.LENGTH_SHORT).show();		            	
		            	finish();
		            	
		            }
		            else if("Wrong".equals(rec[0])){
		            	Toast.makeText(SignupActivity.this, "注册失败", Toast.LENGTH_SHORT).show();	            	
		            }
		            else {
		            	Toast.makeText(SignupActivity.this,msg.obj.toString(), Toast.LENGTH_SHORT).show();
		            }
		            
		        }
		    };
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
