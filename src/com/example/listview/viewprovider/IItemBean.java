package com.example.listview.viewprovider;

public interface IItemBean {
	public abstract Class<? extends IViewProvider> getViewProviderClass();
}
