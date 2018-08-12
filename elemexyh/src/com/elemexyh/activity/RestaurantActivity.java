package com.elemexyh.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantActivity extends Activity {
	private ListView rsmenuListView;
	private TextView food_bag_count_money;
	private TextView food_bag_buy;
	
	private TextView above_title;
	
	private static int bill;
	
	private String originAddress = "http://10.0.2.2:8080/elemexyhServer/userS?mark=query";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant);
		
		Bundle bundle=getIntent().getExtras();
		String rsName=bundle.getString("restaurantName");
		above_title=(TextView)findViewById(R.id.above_title);
		above_title.setText(rsName);
		
		rsmenuListView=(ListView)findViewById(R.id.rsmenuListView);
		food_bag_count_money=(TextView)findViewById(R.id.food_bag_count_money);
		food_bag_buy=(TextView)findViewById(R.id.food_bag_buy);
		
		bill=0;
		
		
		//模拟后台店铺的菜单数据源
        ArrayList<HashMap<String,Object>> menulist=new ArrayList<HashMap<String,Object>>();
        HashMap<String,Object> hashMap1=new HashMap<String,Object>();
        HashMap<String,Object> hashMap2=new HashMap<String,Object>();
        HashMap<String,Object> hashMap3=new HashMap<String,Object>();
        HashMap<String,Object> hashMap4=new HashMap<String,Object>();
        HashMap<String,Object> hashMap5=new HashMap<String,Object>();
        HashMap<String,Object> hashMap6=new HashMap<String,Object>();
        HashMap<String,Object> hashMap7=new HashMap<String,Object>();
        hashMap1.put("rsmenuName", "蒸饺");
        hashMap1.put("rsmenuPrice", 5);
        hashMap2.put("rsmenuName", "炒面");
        hashMap2.put("rsmenuPrice", 10);
        hashMap3.put("rsmenuName", "茶叶蛋");
        hashMap3.put("rsmenuPrice", 3);
        hashMap4.put("rsmenuName", "起什么名字好呢");
        hashMap4.put("rsmenuPrice", 666);
        hashMap5.put("rsmenuName", "我也不知道是什么");
        hashMap5.put("rsmenuPrice", 999);
        hashMap6.put("rsmenuName", "大概就这样好了");
        hashMap6.put("rsmenuPrice", 1036);
        hashMap7.put("rsmenuName", "还要不要加点啥");
        hashMap7.put("rsmenuPrice", 2072);
        menulist.add(hashMap1);
        menulist.add(hashMap2);
        menulist.add(hashMap3);
        menulist.add(hashMap4);
        menulist.add(hashMap5);
        menulist.add(hashMap6);
        menulist.add(hashMap7);
        
        
        SimpleAdapter rsmenuAdapter=new SimpleAdapter(RestaurantActivity.this,menulist,R.layout.rsmenu,new String[] {"rsmenuName","rsmenuPrice"},
																										new int[] {R.id.rsmenuNameView,R.id.rsmenuPriceView});
        rsmenuListView.setAdapter(rsmenuAdapter);
        rsmenuListView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				TextView rsmenuPriceView=(TextView)view.findViewById(R.id.rsmenuPriceView);
				int rsmenuPrice=Integer.parseInt(rsmenuPriceView.getText().toString());
				
				bill=bill+rsmenuPrice;
				food_bag_count_money.setText(String.valueOf(bill));
				
			}
		});
        
        //结算按钮的监听事件
        food_bag_buy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
        	
        });
	}

	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurant, menu);
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
