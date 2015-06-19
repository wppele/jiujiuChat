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
			//�ҳ�listview��item��layout����
			convertView=LayoutInflater.from(context).inflate(R.layout.listview_message_item, null);
			//�ҳ�item�еĿؼ�
			holder=new ViewHolder();
			holder.messageName=(TextView) convertView.findViewById(R.id.message_item_tv_name);
			holder.messageContent=(TextView) convertView.findViewById(R.id.message_item_tv_content);
			holder.messageTime=(TextView) convertView.findViewById(R.id.message_item_tv_time);
			holder.picture=(ImageView) convertView.findViewById(R.id.message_item_iv_headpicture);
			//��viewholder������Ϊconverview��tag��ʶ
			convertView.setTag(holder);
		}
		else{
			holder=(ViewHolder) convertView.getTag();
		}
		//Ϊ�ؼ�������Դ�е�������
		Messages mes=message.get(position);
		holder.messageName.setText(mes.getName());
		holder.messageContent.setText(mes.getContent());
		holder.messageTime.setText(mes.getTime());
		//ͼƬֱ��ʹ��uri��ַ����
		String path=mes.getPictureUrl();
		Uri uri=Uri.parse(path);
		// ��ImageView����ͼƬ 
		holder.picture.setImageURI(uri);
		return convertView;
	}
	// ����һ��static class������������ListView��һ��item�������еĿؼ�
		static class ViewHolder {
				private TextView messageName; 
				private TextView messageTime; 
				private TextView messageContent; 
				private ImageView picture;
			}
}
