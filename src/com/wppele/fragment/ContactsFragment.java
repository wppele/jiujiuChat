package com.wppele.fragment;


import java.util.ArrayList;
import java.util.List;

import com.wppele.adapter.MessageAdapter;
import com.wppele.adapter.ContactAdapter;
import com.wppele.entity.Messages;
import com.wppele.entity.Users_contact;
import com.wppele.jiujiuchat.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
/**
 * ��ϵ��ҳ��
 * @author yuzheng
 *
 */
public class ContactsFragment extends Fragment{

	private ListView listview;
	private List<Users_contact> contact;
	private ContactAdapter commonAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contacts, null) ;
		initData();//��ʼ������
		initview(view);//��ʼ��view
		
		return view ;
	}
	
	public void Onclick(View view){
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void initview(View view) {
		listview=(ListView) view.findViewById(R.id.fm_contacts_lv);
		listview.setAdapter(commonAdapter);
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
