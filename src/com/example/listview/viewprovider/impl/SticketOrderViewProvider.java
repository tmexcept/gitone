package com.example.listview.viewprovider.impl;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.listoptdemo.R;
import com.example.listview.viewprovider.IViewProvider;
import com.example.listview.viewprovider.bean.TicketOrder;

public class SticketOrderViewProvider implements IViewProvider {

	@Override
	public View getItemView(View convertView, LayoutInflater inflater,
			Object data) {
		TicketOrder item = (TicketOrder) data;
		
		ViewHolderTicket holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_view_ticket, null);
		 
			holder = new ViewHolderTicket();
			holder.expireDataTv = (TextView) convertView.findViewById(R.id.ticke_expire_date_tv);
			holder.tickeyTypeTv = (TextView) convertView.findViewById(R.id.ticke_type_tv);
			holder.titleTv = (TextView) convertView.findViewById(R.id.ticket_name_tv);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolderTicket)convertView.getTag();
		}
		
		holder.expireDataTv.setText(item.expireDate);
		holder.tickeyTypeTv.setText(item.type);
		holder.titleTv.setText(item.title);
		
		return convertView;
	}
	
	class ViewHolderTicket{
		public TextView expireDataTv;
		public TextView tickeyTypeTv;
		public TextView titleTv;
	}
}
