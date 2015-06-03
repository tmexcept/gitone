/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.utils.widget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

/**
 * 如果用于多个不同的Item的，需要重写方法getItemViewType(int position)和getViewTypeCount()
 *
 * @param <T>
 */
public abstract class BaseAdapterMulti<T> extends BaseAdapter {

    protected LayoutInflater mInflater;
    protected Collection<T> mDatas;
    protected AbsListView mList;

    public BaseAdapterMulti(AbsListView view, Collection<T> mDatas) {
        this.mInflater = LayoutInflater.from(view.getContext());
        if (mDatas == null) {
            mDatas = new ArrayList<T>(0);
        }
        this.mDatas = mDatas;
        this.mList = view;
    }

    public void refresh(Collection<T> datas) {
        if (datas == null) {
            datas = new ArrayList<T>(0);
        }
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        if (mDatas instanceof List) {
            return ((List<T>) mDatas).get(position);
        } else if (mDatas instanceof Set) {
            return new ArrayList<T>(mDatas).get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final AdapterHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();

    }

    private AdapterHolder getViewHolder(int position, View convertView,
            ViewGroup parent) {
        return AdapterHolder.get(convertView, parent, getItemLayoutId(position), position);
    }

    public abstract void convert(AdapterHolder helper, T item);

    public abstract  int getItemLayoutId(int position);
}
