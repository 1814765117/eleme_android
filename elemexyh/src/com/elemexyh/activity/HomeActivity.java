package com.elemexyh.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 

public class HomeActivity extends Activity {
	private Fragment orderFragment=new OrderFragment();
	
	private ImageView aboveView;
	private TextView login;
	
	public static final String PREFERENCE_NAME = "SaveSetting";
	public static int MODE = Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		 //是可以直接findViewById()<include>的布局里面的控件的
		 //above用来设置那个点击显示/隐藏id_fragment_title的
		aboveView=(ImageView)findViewById(R.id.aboveView);
		aboveView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				   FragmentManager manager = getFragmentManager();
				   Fragment  id_fragment_title=(Fragment) manager.findFragmentById(R.id.id_fragment_title); 
			       FragmentTransaction beginTransaction = manager.beginTransaction();	
			       if(id_fragment_title.isVisible()) {
			       beginTransaction.hide(id_fragment_title);	
			       }
			       else {
			    	   beginTransaction.show(id_fragment_title);
			       }		   
			       beginTransaction.commit();
			}		
		});
		
		//同样也可以直接findViewById()嵌入的fragment里面的控件的
		login=(TextView)findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(login.getText().equals("登录")) {
					Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
					startActivity(intent);
				}
				//退出登录功能
				else {
					Intent intent = new Intent(HomeActivity.this, LoginoutActivity.class);
					startActivity(intent);
				}
				
			}
			
		});
		
		//设置第一次载入页面时显示的Fragment
		setDefaultFragment(); 
	}

	private void setDefaultFragment()  
    {  
        FragmentManager manager = getFragmentManager();  
        FragmentTransaction beginTransaction = manager.beginTransaction();  
        Fragment id_fragment_title=(Fragment) manager.findFragmentById(R.id.id_fragment_title); 
        beginTransaction.hide(id_fragment_title);
        beginTransaction.replace(R.id.id_content, orderFragment);	
        beginTransaction.commit();  
    }
	
	//每次载入页面检测是否进行了登录行为，并且如果登录了则显示用户名
	@Override
	public void onStart() {
		super.onStart();
		
		loadSharedPreferences();					//onStart()的loadSharedPreferences();方法不会影响loginButton的逻辑的（分别处于start与create）
	}
	
	private void loadSharedPreferences() {
		SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);
		String namehome=sharedPreferences.getString("Name", "登录");
		boolean flaghome=sharedPreferences.getBoolean("Flag",false);
		if(flaghome==true) {
			login.setText(namehome);
		}		
		else if(flaghome==false) {
			login.setText("登录");
		}
	}
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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
