package com.example.listview.viewprovider;

import android.view.LayoutInflater;
import android.view.View;

public interface IViewProvider {
	public abstract View getItemView(View convertView, LayoutInflater inflater, Object data);
}
