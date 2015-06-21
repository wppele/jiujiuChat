package com.wppele.util;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewHolder {
	
	private SparseArray<View> mViews;
	private int mposition;
	private View mConvertView;
	
	public ViewHolder(Context context,ViewGroup parent,int layoutID,int position){
		this.mposition=position;
		this.mViews=new SparseArray<View>();
		mConvertView=LayoutInflater.from(context).inflate(layoutID, parent,false);
		mConvertView.setTag(this);
	}
	/**
	 * 入口方法
	 */
	public static ViewHolder get(Context context,View convertview,ViewGroup parent,int layoutID,int position){
		if(convertview==null){
			return new ViewHolder(context, parent, layoutID, position);
		}else{
			ViewHolder holder= (ViewHolder) convertview.getTag();
			holder.mposition=position;//更新一下
			return holder;
		}
	}
	/**
	 * 泛型
	 * 通过viewid获取控件
	 */
	public <T extends View> T getView(int viewId){
		View view=mViews.get(viewId);
		if(view==null){
			view=mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T)view;
	}
	/**
	 * 返回convertView
	 * @return
	 */
	public View getConvertView(){
		return mConvertView;
	}
}

