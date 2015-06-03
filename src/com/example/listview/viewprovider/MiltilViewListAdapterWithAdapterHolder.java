package com.example.listview.viewprovider;

import java.util.List;

import android.widget.AbsListView;

import com.example.listoptdemo.R;
import com.example.listview.viewprovider.bean.FlightOrder;
import com.example.listview.viewprovider.bean.TicketOrder;
import com.example.utils.widget.AdapterHolder;
import com.example.utils.widget.BaseAdapterMulti;

public class MiltilViewListAdapterWithAdapterHolder extends BaseAdapterMulti<IItemBean> {
	List<IItemBean> mDatas;

	public MiltilViewListAdapterWithAdapterHolder(AbsListView view, List<IItemBean> mDatas) {
		super(view, mDatas);
		// TODO Auto-generated constructor stub
		this.mDatas = mDatas;
	}

	@Override
	public void convert(AdapterHolder helper, IItemBean item) {
		// TODO Auto-generated method stub
		if(item instanceof FlightOrder){
            initFlightOrder( helper,  (FlightOrder)item);
        }else if(item instanceof TicketOrder){
            initTicketOrder( helper, (TicketOrder) item);
        }
	}
	
	private void initFlightOrder(AdapterHolder holder, FlightOrder item){
		holder.setText(R.id.flight_airline_tv, item.airline);
		holder.setText(R.id.flight_from_tv, item.from);
		holder.setText(R.id.flight_to_tv, item.to);
	}
	
	private void initTicketOrder(AdapterHolder holder, TicketOrder item){
		holder.setText(R.id.ticke_expire_date_tv, item.expireDate);
		holder.setText(R.id.ticke_type_tv, item.type);
		holder.setText(R.id.ticket_name_tv, item.title);
	}

	@Override
    public int getItemLayoutId(int position) {
        if(mDatas.get(position) instanceof FlightOrder){
            return R.layout.item_view_flight;
        }else if(mDatas.get(position) instanceof TicketOrder){
            return R.layout.item_view_ticket;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas == null) {
            return 0;
        }
        if(mDatas.get(position) instanceof FlightOrder){
            return 0;
        }else if(mDatas.get(position) instanceof TicketOrder){
            return 1;
        }
        return 0;
    }
    
    @Override
    public int getViewTypeCount() {
    	// TODO Auto-generated method stub
    	return 2;
    }

}
