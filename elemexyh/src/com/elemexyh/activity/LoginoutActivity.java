package com.elemexyh.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class LoginoutActivity extends Activity {
	private TextView loginoutView;
	
	public static final String PREFERENCE_NAME = "SaveSetting";
	public static int MODE = Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginout);
		
		loginoutView=(TextView)findViewById(R.id.loginoutView);
		
		loginoutView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 boolean flag=false;
				 saveSharedPreferences(flag);
				finish();
			}
			
		});
	}
	
	
	private void saveSharedPreferences(boolean flag) {
    	//将登录的名字保存到sharepreferences，方便主界面使用
		SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);
		SharedPreferences.Editor editor=sharedPreferences.edit();	
		//代表进行了退出登录行为，设flag为false
		editor.putBoolean("Flag", flag);
		editor.commit();
    }
}
