package com.example.listoptdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.listview.viewprovider.IItemBean;
import com.example.listview.viewprovider.IViewProvider;
import com.example.listview.viewprovider.MiltilViewListAdapter;
import com.example.listview.viewprovider.bean.FlightOrder;
import com.example.listview.viewprovider.bean.TicketOrder;
import com.example.listview.viewprovider.impl.FlightOrderViewProvider;
import com.example.listview.viewprovider.impl.SticketOrderViewProvider;

public class MultiItemListViewActivityWithViewProvider extends Activity {
	private ListView mListView;
	private List<IItemBean> mList = new ArrayList<IItemBean>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		createData();
		mListView = (ListView) findViewById(R.id.my_listview);
		//不同之处在于多了一个provider集合，提供所有期望显示类型的provider class
		//getView的实现在provider中实现，和在adapter中用法一样
		List<Class<? extends IViewProvider>> providers = new ArrayList<Class<? extends IViewProvider>>();
		providers.add(FlightOrderViewProvider.class);
		providers.add(SticketOrderViewProvider.class);
		
		MiltilViewListAdapter adpater = new MiltilViewListAdapter(getApplication(), mList, providers);
		mListView.setAdapter(adpater);
	}
	 
	 
	private void createData(){
		Random random = new Random();
		
		for(int i=0; i < 150; i++){
			if(i%2 == 0 ){
				FlightOrder f = new FlightOrder();
				f.airline = "东方航空-BS" + i;
				f.from = "北京";
				f.to = "杭州";
				mList.add(f);
			}else{
				TicketOrder t = new TicketOrder();
				t.expireDate = "有效时间：2014-1-1至2014-5-5";
				t.type = "类型：电子票";
				t.title = "大裤衩" + i;
				mList.add(t);
			}
		}
	}
}

 