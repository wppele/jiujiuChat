package com.wppele.adapter;

import java.util.List;

import com.wppele.entity.Messages;
import com.wppele.entity.Users_contact;
import com.wppele.jiujiuchat.R;
import com.wppele.util.CommonAdapter;
import com.wppele.util.ViewHolder;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessageAdapter extends CommonAdapter<Messages>{

	public MessageAdapter(Context context, List<Messages> datas) {
		super(context, datas);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder=ViewHolder.get(mcontext, convertView, parent, R.layout.listview_message_item, position);
		//为控件绑定数据源中的数据项
		Messages mes=mdatas.get(position);
		
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
