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
	
	//底部菜单
	private FragmentTabHost tabHost ;
	private RadioGroup radioGroup ;
	protected String userinfo_json;
	//消息提示
	private BadgeView badgeview;
	
	//数据处理
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
		//listview适配器相关
		initData();//初始化数据
		initview();//初始化view
	}
	@Override
	protected void onStart() {
		Intent intent=getIntent();
		userinfo_json=intent.getStringExtra("userinfo_json");
		users_login=gson.fromJson(userinfo_json,Users_login.class);
		getIntent().putExtra("infoFromMain", userinfo_json);
		Toast.makeText(this, "欢迎您" + users_login.getUsername(),

				Toast.LENGTH_LONG).show();
		super.onStart();
	}
	
	
	/**
	 * 为侧边菜单设置listview和配置adapter
	 */
	private void initview() {
		listview=(ListView) findViewById(R.id.leftmenu_lv);
		listview.setAdapter(commonAdapter);
	}
	private void initData() {
		menu=new ArrayList<MenuLeft>();
		MenuLeft message= new MenuLeft("ic_launcher.png", "我的UU会员","ic_launcher.png");
		menu.add(message);
		
		message= new MenuLeft("ic_launcher.png", "UU钱包","ic_launcher.png");
		menu.add(message);
		
		message= new MenuLeft("ic_launcher.png", "我的个人设置","ic_launcher.png");
		menu.add(message);
		
		message= new MenuLeft("ic_launcher.png", "注销当前账号","ic_launcher.png");
		menu.add(message);
		
		
		commonAdapter=new MenuAdapter(this, menu);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 为底部菜单添加页面支持
	 */
	private void initTabHost() {
		// 注意id
				tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost) ;
				// 使用FragmentTabHost，必须先让FragmentTabHost调用setup方法，目的指明FragmentTabHost的内容加载到哪里
				tabHost.setup(this, getSupportFragmentManager(), R.id.main_realTabcontent) ;
				// 设置标签显示文本以及相应内容部分
				tabHost.addTab(
						tabHost.newTabSpec("message").setIndicator("消息", null), 		// 设置标签显示
						MessageFragment.class, 		// 设置内容来自哪一个Fragment
						null			// 当加载Fragment时，如果初始化时Fragment需要参数，放在Bundle中传递；如果不需要参数，此处为null
						) ;
				tabHost.addTab(
						tabHost.newTabSpec("contacts").setIndicator("联系人", null), 		// 设置标签显示
						ContactsFragment.class, 		// 设置内容来自哪一个Fragment
						null			// 当加载Fragment时，如果初始化时Fragment需要参数，放在Bundle中传递；如果不需要参数，此处为null
						) ;
				// FragmentTabHost 的监听器
				tabHost.setOnTabChangedListener(new OnTabChangeListener() {			
					// 参数是当前选中标签的标识字符串
					public void onTabChanged(String arg0) {
						Toast.makeText(MainActivity.this, arg0, Toast.LENGTH_LONG).show();
					}
				}) ;		
	}
	/**
	 * 初始化底部菜单
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
