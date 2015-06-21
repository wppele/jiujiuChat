package com.wppele.adapter;

import java.util.List;

import com.wppele.entity.Messages;
import com.wppele.entity.Users_contact;
import com.wppele.jiujiuchat.R;
import com.wppele.util.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {

	private Context context;
	private List<Users_contact> mcontact;
	private LayoutInflater mInflater;
	
	public ContactAdapter(Context context, List<Users_contact> contact) {
		this.context=context;
		mInflater=LayoutInflater.from(context);
		mcontact=contact;
	}
	
	@Override
	public int getCount() {
		
		return mcontact.size();
	}

	@Override
	public Object getItem(int arg0) {

		return mcontact.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {

		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=ViewHolder.get(context, convertView, parent, R.layout.listview_contacts_item, position);
		//为控件绑定数据源中的数据项
		Users_contact contact=mcontact.get(position);

		((TextView)holder.getView(R.id.contacts_item_tv_friendName)).setText(contact.getNickname());
		((TextView)holder.getView(R.id.contacts_item_tv_mind)).setText(contact.getMood());
		((TextView)holder.getView(R.id.contacts_item_tv_state)).setText(contact.getIsonline());
		return holder.getConvertView();
	}

}
