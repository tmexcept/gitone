package com.example.listview.viewprovider.bean;

import com.example.listview.viewprovider.IItemBean;
import com.example.listview.viewprovider.IViewProvider;
import com.example.listview.viewprovider.impl.FlightOrderViewProvider;

public class FlightOrder implements IItemBean {
	 
	public String airline;
	public String from;
	public String to ;
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	@Override
	public Class<? extends IViewProvider> getViewProviderClass() {
		 
		return FlightOrderViewProvider.class;
	}
	
	
	
}
