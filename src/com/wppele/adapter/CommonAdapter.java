package com.wppele.adapter;

import java.util.List;

import com.wppele.entity.Messages;
import com.wppele.jiujiuchat.R;
import com.wppele.util.ViewHolder;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

public class CommonAdapter extends BaseAdapter{

	private Context context;
	private List<Messages> mMessage;
	private LayoutInflater mInflater;
	
	public CommonAdapter(Context context, List<Messages> message) {
		this.context=context;
		mInflater=LayoutInflater.from(context);
		mMessage=message;
	}

	@Override
	public int getCount() {
		return mMessage.size();
	}

	@Override
	public Object getItem(int arg0) {
		
		return mMessage.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder=ViewHolder.get(context, convertView, parent, R.layout.listview_message_item, position);
		//为控件绑定数据源中的数据项
		Messages mes=mMessage.get(position);
		
		//ImageView iv=holder.getView(R.id.message_item_iv_headpicture);
		((TextView)holder.getView(R.id.message_item_tv_name)).setText(mes.getName());	
		((TextView)holder.getView(R.id.message_item_tv_content)).setText(mes.getContent());
		((TextView)holder.getView(R.id.message_item_tv_time)).setText(mes.getTime());
		/*((ImageView)holder.getView(R.id.message_item_iv_headpicture)).seti(mes.getTime());
		//图片直接使用uri地址构建
		String path=mes.getPictureUrl();
		Uri uri=Uri.parse(path);
		// 给ImageView设置图片 
		holder.picture.setImageURI(uri);*/
		return holder.getConvertView();
	}

}
