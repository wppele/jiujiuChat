package com.wppele.jiujiuchat;



import com.wppele.fragment.ContactsFragment;
import com.wppele.fragment.MessageFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends FragmentActivity {
	//底部菜单
	private FragmentTabHost tabHost ;
	private RadioGroup radioGroup ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initTabHost();
		initRadioGroup();

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
