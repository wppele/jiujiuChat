package com.wppele.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wppele.adapter.MessageAdapter;
import com.wppele.adapter.ContactAdapter;
import com.wppele.client.LoginVerify;
import com.wppele.entity.Messages;
import com.wppele.entity.UserInfo_FriendList;
import com.wppele.entity.Users_contact;
import com.wppele.entity.Users_login;
import com.wppele.jiujiuchat.R;
import com.wppele.util.JumpTo;




import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
 * 
 * @author yuzheng
 *
 */
public class ContactsFragment extends Fragment implements OnClickListener {

	private ListView mlistview;
	private List<UserInfo_FriendList> friendList;// private List<Users_contact>
													// contact;
	private Gson gson;
	private String infotemp;
	private ContactAdapter commonAdapter;
	private Users_login myinfoFromMain;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contacts, null);
		gson = new Gson();
		friendList = new ArrayList<UserInfo_FriendList>();
		myinfoFromMain = new Users_login();
		// ��MainActivity�л�ȡ��½��Ϣjson������
		infotemp = getActivity().getIntent().getStringExtra("infoFromMain");
		myinfoFromMain = gson.fromJson(infotemp, Users_login.class);
		// ��������Ϊ��ȡ�б��������Ϊjson����onstart�д���������
		myinfoFromMain.setOpration("getfriendlist");
		infotemp = gson.toJson(myinfoFromMain);
		Log.e("contactfragment", myinfoFromMain.getOpration());
		onStart();
		//initData();// ��ʼ������
		initview(view);// ��ʼ��view
		final JumpTo jump = new JumpTo();
		// item����¼�
		mlistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getActivity(), "�����˵�" + arg2 + "����",
						Toast.LENGTH_LONG).show();
				jump.jumpToChatWindowActivity(getActivity(), arg2);
				;
			}
		});

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		// �����̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(2);
		// ����Ϣ�����߳���
		Callable<String> friendList_ctrol = new LoginVerify(infotemp);
		// ��ȡ���ؽ��-->����ֵ�Ƿ�Ϊfriendlist
		Future<String> friendlist_json = pool.submit(friendList_ctrol);
		
		try {
			if (friendlist_json.get().toString().equals("false")) {
				Toast.makeText(getActivity(),"�޷��ؽ��", Toast.LENGTH_SHORT).show();
			}else {
				//�ڶ����������ã���jsonת��Ϊlist<UserInfo_FriendList>����
				friendList=gson.fromJson(friendlist_json.get().toString(),new TypeToken<List<UserInfo_FriendList>>(){}.getType());
				initData(friendList);// ��ʼ������
//				Log.e("contactfragment",friendList.get(0).getUUnumber());
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	private void initview(View view) {
		mlistview = (ListView) view.findViewById(R.id.fm_contacts_lv);
		mlistview.setAdapter(commonAdapter);
	}
	
	private void initData(List<UserInfo_FriendList> friendList) {
		//����ȡ�������б�
		UserInfo_FriendList friendlist_temp=new UserInfo_FriendList();
		commonAdapter = new ContactAdapter(getActivity(), friendList);
	}

/*	private void initData() {
		contact = new ArrayList<Users_contact>();
		Users_contact message = new Users_contact("����o0�ֻ�", "������ְ�", "[����]",
				"pictureUrl");
		contact.add(message);

		message = new Users_contact("���ۡؽ^Ӱ", "����˭�ְ֣�", "[����]", "pictureUrl");
		contact.add(message);

		message = new Users_contact("ڤ��", "��ð���", "[����]", "pictureUrl");
		contact.add(message);

		message = new Users_contact("��ʬ", "����˭��", "[����]", "pictureUrl");
		contact.add(message);
		message = new Users_contact("½�ϰ�", "��������", "[����]", "pictureUrl");
		contact.add(message);

		commonAdapter = new ContactAdapter(getActivity(), contact);
	}*/
}
