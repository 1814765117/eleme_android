package com.elemexyh.activity;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class OrderFragment extends Fragment {
	public ListView restaurantsListView;
	
	public Button buttonTest;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_order, null);
        
        //模拟后台店铺数据源
        ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
        HashMap<String,Object> hashMap1=new HashMap<String,Object>();
        hashMap1.put("restaurantName", "沙县小吃");
        hashMap1.put("restaurantIcon", R.drawable.ic_launcher);
        list.add(hashMap1);
        
        restaurantsListView=(ListView)v.findViewById(R.id.restaurantsListView);
        SimpleAdapter restaurantsAdapter=new SimpleAdapter(getActivity(),list,R.layout.restaurant,new String[] {"restaurantName","restaurantIcon"},
        																					new int[] {R.id.restaurantName,R.id.restaurantIcon});
        
        restaurantsListView.setAdapter(restaurantsAdapter);
        restaurantsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				//获取listView的item的子控件
				TextView restaurantName=(TextView)view.findViewById(R.id.restaurantName);
				String rsName=restaurantName.getText().toString();
//				Toast.makeText(getActivity(), rsName, Toast.LENGTH_LONG).show();
				
				Intent intent=new Intent(getActivity().getApplicationContext(),RestaurantActivity.class);		
				intent.putExtra("restaurantName",rsName);
				startActivity(intent);
			}
        	
        });
        
        buttonTest=(Button)v.findViewById(R.id.buttonTest);
        buttonTest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intentTest=new Intent(getActivity().getApplicationContext(),TestImgActivity.class);
				startActivity(intentTest);
			}
        	
        });
        
        return v;
    }
}
