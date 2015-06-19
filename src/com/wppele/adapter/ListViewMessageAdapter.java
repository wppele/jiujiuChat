package com.wppele.adapter;

import java.util.List;

import com.wppele.entity.Messages;


import com.wppele.jiujiuchat.R;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewMessageAdapter extends BaseAdapter {
	
	private Context context;
	private List<Messages> message;
	
	public ListViewMessageAdapter(Context context, List<Messages> message) {
		super();
		this.context = context;
		this.message = message;
	}

	@Override
	public int getCount() {
		return message.size();
	}

	@Override
	public Object getItem(int arg0) {
		
		return message.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
			//找出listview中item的layout布局
			convertView=LayoutInflater.from(context).inflate(R.layout.listview_message_item, null);
			//找出item中的控件
			holder=new ViewHolder();
			holder.messageName=(TextView) convertView.findViewById(R.id.message_item_tv_name);
			holder.messageContent=(TextView) convertView.findViewById(R.id.message_item_tv_content);
			holder.messageTime=(TextView) convertView.findViewById(R.id.message_item_tv_time);
			holder.picture=(ImageView) convertView.findViewById(R.id.message_item_iv_headpicture);
			//将viewholder对象作为converview的tag标识
			convertView.setTag(holder);
		}
		else{
			holder=(ViewHolder) convertView.getTag();
		}
		//为控件绑定数据源中的数据项
		Messages mes=message.get(position);
		holder.messageName.setText(mes.getName());
		holder.messageContent.setText(mes.getContent());
		holder.messageTime.setText(mes.getTime());
		//图片直接使用uri地址构建
		String path=mes.getPictureUrl();
		Uri uri=Uri.parse(path);
		// 给ImageView设置图片 
		holder.picture.setImageURI(uri);
		return convertView;
	}
	// 声明一个static class，该类内描述ListView的一个item上所具有的控件
		static class ViewHolder {
				private TextView messageName; 
				private TextView messageTime; 
				private TextView messageContent; 
				private ImageView picture;
			}
}
