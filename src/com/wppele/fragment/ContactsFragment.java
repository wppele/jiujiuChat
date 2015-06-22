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
 * 联系人页面
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
		// 从MainActivity中获取登陆信息json并解析
		infotemp = getActivity().getIntent().getStringExtra("infoFromMain");
		myinfoFromMain = gson.fromJson(infotemp, Users_login.class);
		// 操作更换为获取列表，将对象变为json并在onstart中传至服务器
		myinfoFromMain.setOpration("getfriendlist");
		infotemp = gson.toJson(myinfoFromMain);
		Log.e("contactfragment", myinfoFromMain.getOpration());
		onStart();
		//initData();// 初始化数据
		initview(view);// 初始化view
		final JumpTo jump = new JumpTo();
		// item点击事件
		mlistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getActivity(), "按下了第" + arg2 + "个项",
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
		// 创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(2);
		// 将信息传入线程中
		Callable<String> friendList_ctrol = new LoginVerify(infotemp);
		// 获取返回结果-->返回值是否为friendlist
		Future<String> friendlist_json = pool.submit(friendList_ctrol);
		
		try {
			if (friendlist_json.get().toString().equals("false")) {
				Toast.makeText(getActivity(),"无返回结果", Toast.LENGTH_SHORT).show();
			}else {
				//第二个参数作用：将json转换为list<UserInfo_FriendList>类型
				friendList=gson.fromJson(friendlist_json.get().toString(),new TypeToken<List<UserInfo_FriendList>>(){}.getType());
				initData(friendList);// 初始化数据
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
		//单独取出好友列表
		UserInfo_FriendList friendlist_temp=new UserInfo_FriendList();
		commonAdapter = new ContactAdapter(getActivity(), friendList);
	}

/*	private void initData() {
		contact = new ArrayList<Users_contact>();
		Users_contact message = new Users_contact("守望o0轮回", "我是你爸爸", "[在线]",
				"pictureUrl");
		contact.add(message);

		message = new Users_contact("枭雄∝絕影", "你是谁爸爸？", "[在线]", "pictureUrl");
		contact.add(message);

		message = new Users_contact("冥寒", "你好啊！", "[在线]", "pictureUrl");
		contact.add(message);

		message = new Users_contact("僵尸", "你是谁！", "[在线]", "pictureUrl");
		contact.add(message);
		message = new Users_contact("陆老板", "真厉害啊", "[在线]", "pictureUrl");
		contact.add(message);

		commonAdapter = new ContactAdapter(getActivity(), contact);
	}*/
}
