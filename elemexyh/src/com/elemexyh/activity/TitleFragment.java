package com.elemexyh.activity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//继承了OnClickListener
public class TitleFragment extends Fragment implements OnClickListener{
	private TextView login;
	private TextView order;
	private TextView orderlist;
	
	private Fragment orderFragment=new OrderFragment();
	private Fragment orderListFragment=new OrderListFragment();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_title, null);
        
        //这里findviewById必须写明是视图v中的   
        login=(TextView) v.findViewById(R.id.login);
        order=(TextView) v.findViewById(R.id.order); 
        orderlist=(TextView) v.findViewById(R.id.orderList);
        
        //fragment的切换用的监听器好像必须这么写
        login.setOnClickListener(this);
        order.setOnClickListener(this);
        orderlist.setOnClickListener(this);
              
        
        return v;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		   FragmentManager manager = getFragmentManager();		  
	       FragmentTransaction beginTransaction = manager.beginTransaction();			       
	       beginTransaction.hide(this);
	       switch (v.getId()) {
	       case R.id.order:
	    	   beginTransaction.replace(R.id.id_content,  orderFragment); 	
	    	   break;
	       case R.id.orderList:
	    	   beginTransaction.replace(R.id.id_content,  orderListFragment);	
	    	   break;
	       }
	     
	       beginTransaction.commit();	
	}
	
}
