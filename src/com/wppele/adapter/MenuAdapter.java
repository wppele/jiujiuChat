package com.wppele.adapter;

import java.util.List;

import com.wppele.entity.MenuLeft;
import com.wppele.jiujiuchat.R;
import com.wppele.util.CommonAdapter;
import com.wppele.util.ViewHolder;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends CommonAdapter<MenuLeft>{


	public MenuAdapter(Context context, List<MenuLeft> datas) {
		super(context, datas);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=ViewHolder.get(mcontext, convertView, parent, R.layout.listview_leftmenu_item, position);
		MenuLeft mes=mdatas.get(position);
	
		//ImageView iv=holder.getView(R.id.message_item_iv_headpicture);
		//((ImageView)holder.getView(R.id.leftmenu_item_iv_icon)).setImageResource(mes.getIconurl());;
		((TextView)holder.getView(R.id.leftmenu_item_tv_menuname)).setText(mes.getName());
		//((ImageView)holder.getView(R.id.leftmenu_item_iv_Arrow)).setText(mes.getArrowurl());

		return holder.getConvertView();
	}
}
