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
	//�ײ��˵�
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
