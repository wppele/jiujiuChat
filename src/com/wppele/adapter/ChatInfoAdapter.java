package com.wppele.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wppele.entity.ChatInfo;
import com.wppele.jiujiuchat.R;
import com.wppele.util.CommonAdapter;
import com.wppele.util.ViewHolder;

public class ChatInfoAdapter extends CommonAdapter<ChatInfo> {

	public ChatInfoAdapter(Context context, List<ChatInfo> datas) {
		super(context, datas);
	}

	public static interface IMsgViewType{
		int IMVT_COM_MSG=0;
		int IMVT_TO_MSG=1;
	}
	
	@Override
	public int getItemViewType(int position) {
		ChatInfo chatinfo=mdatas.get(position);
		if(chatinfo.getComeMesssge()){
			return IMsgViewType.IMVT_COM_MSG;
		}
		return IMsgViewType.IMVT_TO_MSG;
	}
	
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ChatInfo chatinfo=mdatas.get(position);
		boolean iscomemessage=chatinfo.getComeMesssge();
		
		if(iscomemessage){
			ViewHolder holder=ViewHolder.get(mcontext, convertView, parent, R.layout.chating_item_text_get, position);		
			((TextView)holder.getView(R.id.chating_item_get_sendtime)).setText(chatinfo.getDate());
			((TextView)holder.getView(R.id.chating_item_get_chatcontent)).setText(chatinfo.getContent());
//Í·Ïñ**************************************************************************
			return holder.getConvertView();
		}else{
			ViewHolder holder=ViewHolder.get(mcontext, convertView, parent, R.layout.chating_item_text_put, position);
			((TextView)holder.getView(R.id.chating_item_put_sendtime)).setText(chatinfo.getDate());
			((TextView)holder.getView(R.id.chating_item_put_chatcontent)).setText(chatinfo.getContent());
			return holder.getConvertView();
		}

	}

}
