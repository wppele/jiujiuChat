package com.wppele.util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * 利用泛型传递bean对象。
 * 公共adapter，任何adapter继承本adap即可
 * @author yuzheng
 *
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
	
	protected Context mcontext;
	protected List<T> mdatas;
	protected LayoutInflater minflater;
	
	public CommonAdapter(Context context,List<T> datas) {
		this.mcontext=context;
		this.mdatas=datas;
		minflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mdatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mdatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent) ;

}
