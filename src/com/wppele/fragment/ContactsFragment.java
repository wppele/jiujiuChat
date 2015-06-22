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
 * ��ϵ��ҳ��
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
		initData();//��ʼ������
		initview(view);//��ʼ��view
		final JumpTo jump=new JumpTo();
		//item����¼�
		mlistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getActivity(), "�����˵�"+arg2+"����", Toast.LENGTH_LONG).show();
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
		Users_contact message= new Users_contact("����o0�ֻ�", "������ְ�", "[����]", "pictureUrl");
		contact.add(message);
		
		message= new Users_contact("���ۡؽ^Ӱ", "����˭�ְ֣�", "[����]", "pictureUrl");
		contact.add(message);
		
		message= new Users_contact("ڤ��", "��ð���", "[����]", "pictureUrl");
		contact.add(message);
		
		message= new Users_contact("��ʬ", "����˭��", "[����]", "pictureUrl");
		contact.add(message);
		message= new Users_contact("½�ϰ�", "��������", "[����]", "pictureUrl");
		contact.add(message);
		
		commonAdapter=new ContactAdapter(getActivity(), contact);
	}
}
