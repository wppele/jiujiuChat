package com.wppele.jiujiuchat;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jauker.widget.BadgeView;
import com.wppele.adapter.MenuAdapter;
import com.wppele.entity.MenuLeft;
import com.wppele.entity.Users_login;
import com.wppele.fragment.ContactsFragment;
import com.wppele.fragment.MessageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends FragmentActivity {
	
	//�ײ��˵�
	private FragmentTabHost tabHost ;
	private RadioGroup radioGroup ;
	protected String userinfo_json;
	//��Ϣ��ʾ
	private BadgeView badgeview;
	
	//���ݴ���
	private Gson gson;
	Users_login users_login;
	//adapter
	private ListView listview;
	private List<MenuLeft> menu;
	private MenuAdapter commonAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initTabHost();
		initRadioGroup();
		gson=new Gson();
		users_login=new Users_login();
		//listview���������
		initData();//��ʼ������
		initview();//��ʼ��view
	}
	@Override
	protected void onStart() {
		Intent intent=getIntent();
		userinfo_json=intent.getStringExtra("userinfo_json");
		users_login=gson.fromJson(userinfo_json,Users_login.class);
		getIntent().putExtra("infoFromMain", userinfo_json);
		Toast.makeText(this, "��ӭ��" + users_login.getUsername(),

				Toast.LENGTH_LONG).show();
		super.onStart();
	}
	
	
	/**
	 * Ϊ��߲˵�����listview������adapter
	 */
	private void initview() {
		listview=(ListView) findViewById(R.id.leftmenu_lv);
		listview.setAdapter(commonAdapter);
	}
	private void initData() {
		menu=new ArrayList<MenuLeft>();
		MenuLeft message= new MenuLeft("ic_launcher.png", "�ҵ�UU��Ա","ic_launcher.png");
		menu.add(message);
		
		message= new MenuLeft("ic_launcher.png", "UUǮ��","ic_launcher.png");
		menu.add(message);
		
		message= new MenuLeft("ic_launcher.png", "�ҵĸ�������","ic_launcher.png");
		menu.add(message);
		
		message= new MenuLeft("ic_launcher.png", "ע����ǰ�˺�","ic_launcher.png");
		menu.add(message);
		
		
		commonAdapter=new MenuAdapter(this, menu);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Ϊ�ײ��˵����ҳ��֧��
	 */
	private void initTabHost() {
		// ע��id
				tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost) ;
				// ʹ��FragmentTabHost����������FragmentTabHost����setup������Ŀ��ָ��FragmentTabHost�����ݼ��ص�����
				tabHost.setup(this, getSupportFragmentManager(), R.id.main_realTabcontent) ;
				// ���ñ�ǩ��ʾ�ı��Լ���Ӧ���ݲ���
				tabHost.addTab(
						tabHost.newTabSpec("message").setIndicator("��Ϣ", null), 		// ���ñ�ǩ��ʾ
						MessageFragment.class, 		// ��������������һ��Fragment
						null			// ������Fragmentʱ�������ʼ��ʱFragment��Ҫ����������Bundle�д��ݣ��������Ҫ�������˴�Ϊnull
						) ;
				tabHost.addTab(
						tabHost.newTabSpec("contacts").setIndicator("��ϵ��", null), 		// ���ñ�ǩ��ʾ
						ContactsFragment.class, 		// ��������������һ��Fragment
						null			// ������Fragmentʱ�������ʼ��ʱFragment��Ҫ����������Bundle�д��ݣ��������Ҫ�������˴�Ϊnull
						) ;
				// FragmentTabHost �ļ�����
				tabHost.setOnTabChangedListener(new OnTabChangeListener() {			
					// �����ǵ�ǰѡ�б�ǩ�ı�ʶ�ַ���
					public void onTabChanged(String arg0) {
						Toast.makeText(MainActivity.this, arg0, Toast.LENGTH_LONG).show();
					}
				}) ;		
	}
	/**
	 * ��ʼ���ײ��˵�
	 */
	private void initRadioGroup() {
		radioGroup = (RadioGroup) findViewById(R.id.footermenu_rgroup) ;
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				switch(checkedId) {
				case R.id.menu_btn_message :
					/*if(badgeview!=null){
						messageRadioGroup.removeView(badgeview);
					}
					badgeview=new BadgeView(MainActivity.this);
					badgeview.setBadgeCount(7);
					messageRadioGroup.addView(badgeview);*/
					tabHost.setCurrentTab(0) ;
					break;
				case R.id.menu_btn_contacts :
					tabHost.setCurrentTab(1) ;
					break;
				}
			}
		}) ;
	}	
}
