package com.example.listview.viewprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MiltilViewListAdapter extends BaseAdapter {
	private List<Class<? extends IViewProvider>> mProviders = new ArrayList<Class<? extends IViewProvider>>();

	private HashMap<String, Object> mProvidersMap = new HashMap<String, Object>();
	private Context mContext;
	private LayoutInflater mInflater;
	private List<? extends IItemBean> mItemBeanList;
	
	 
	public MiltilViewListAdapter(Context c,
			List<? extends IItemBean> itemBeanList, List<Class<? extends IViewProvider>> providers) {
		this.mContext = c;
		mInflater = LayoutInflater.from(c);
		mItemBeanList = itemBeanList;
		if(providers == null || providers.size() < 1){
			throw new IllegalArgumentException(
					"providers must not null or size < 1");
		}
		mProviders = providers;
	}
	
	@Override
	public int getCount() {
		if (mItemBeanList != null) {
			return mItemBeanList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (mItemBeanList != null && position < mItemBeanList.size()
				&& position >= 0) {
			return mItemBeanList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		IItemBean itemBean = mItemBeanList.get(position);
		if (itemBean.getViewProviderClass() == null) {
			throw new IllegalArgumentException(itemBean
					+ " getViewProviderClass() return not null");
		}
		String viewProviderName = itemBean.getViewProviderClass().getName();

		IViewProvider viewProvider = (IViewProvider) mProvidersMap
				.get(viewProviderName);

		if (viewProvider == null) {
			int size = mProviders.size();
			boolean have = false;
			for (int i = 0; i < size; i++) {
				if (viewProviderName.equals(mProviders.get(i).getName())) {
					have = true;
				}
			}
			if (!have) {
				throw new IllegalArgumentException(viewProviderName
						+ "not add this provider");
			}
			try {
				viewProvider = itemBean.getViewProviderClass().newInstance();
			} catch (Exception e) {

			}
			mProvidersMap.put(viewProviderName, viewProvider);
		}

		convertView = viewProvider
				.getItemView(convertView, mInflater, itemBean);

		return convertView;
	}

	@Override
	public int getItemViewType(int position) {
		if (mItemBeanList == null) {
			return 0;
		}

		if (position < 0 || position >= mItemBeanList.size()) {
			return 0;
		}
		IItemBean item = mItemBeanList.get(position);

		if (item.getViewProviderClass() == null) {
			throw new IllegalArgumentException(
					"ItemBin implimention method getViewProvider() return not null");
		}

		Class providerClass = item.getViewProviderClass();
		int size = mProviders.size();

		for (int i = 0; i < size; i++) {
			if (providerClass.getName().equals(mProviders.get(i).getName())) {
				return i;
			}
		}

		return 0;
	}

	@Override
	public int getViewTypeCount() {
		int typeSize = mProviders.size();
		return typeSize <= 0 ? 1 : typeSize;
	}

}
