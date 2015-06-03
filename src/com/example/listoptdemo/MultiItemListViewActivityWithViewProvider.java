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
		//��֮ͬ�����ڶ���һ��provider���ϣ��ṩ����������ʾ���͵�provider class
		//getView��ʵ����provider��ʵ�֣�����adapter���÷�һ��
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
				f.airline = "��������-BS" + i;
				f.from = "����";
				f.to = "����";
				mList.add(f);
			}else{
				TicketOrder t = new TicketOrder();
				t.expireDate = "��Чʱ�䣺2014-1-1��2014-5-5";
				t.type = "���ͣ�����Ʊ";
				t.title = "�����" + i;
				mList.add(t);
			}
		}
	}
}

 