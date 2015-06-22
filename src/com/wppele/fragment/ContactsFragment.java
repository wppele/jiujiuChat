package com.wppele.fragment;


import java.util.ArrayList;
import java.util.List;

import com.wppele.adapter.MessageAdapter;
import com.wppele.adapter.ContactAdapter;
import com.wppele.entity.Messages;
import com.wppele.entity.Users_contact;
import com.wppele.jiujiuchat.R;
import com.wppele.util.JumpTo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
/**
 * 联系人页面
 * @author yuzheng
 *
 */
public class ContactsFragment extends Fragment implements OnClickListener{

	private ListView mlistview;
	private List<Users_contact> contact;
	private ContactAdapter commonAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contacts, null) ;
		initData();//初始化数据
		initview(view);//初始化view
		final JumpTo jump=new JumpTo();
		//item点击事件
		mlistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getActivity(), "按下了第"+arg2+"个项", Toast.LENGTH_LONG).show();
				jump.jumpToChatWindowActivity(getActivity(),arg2);;
			}
		});
		
		return view ;
	}
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void initview(View view) {
		mlistview=(ListView) view.findViewById(R.id.fm_contacts_lv);
		mlistview.setAdapter(commonAdapter);
	}

	private void initData() {
		contact=new ArrayList<Users_contact>();
		Users_contact message= new Users_contact("守望o0轮回", "我是你爸爸", "[在线]", "pictureUrl");
		contact.add(message);
		
		message= new Users_contact("枭雄∝絕影", "你是谁爸爸？", "[在线]", "pictureUrl");
		contact.add(message);
		
		message= new Users_contact("冥寒", "你好啊！", "[在线]", "pictureUrl");
		contact.add(message);
		
		message= new Users_contact("僵尸", "你是谁！", "[在线]", "pictureUrl");
		contact.add(message);
		message= new Users_contact("陆老板", "真厉害啊", "[在线]", "pictureUrl");
		contact.add(message);
		
		commonAdapter=new ContactAdapter(getActivity(), contact);
	}
}
