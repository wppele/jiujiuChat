package com.wppele.jiujiuchat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.wppele.adapter.ChatInfoAdapter;
import com.wppele.entity.ChatInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChatWindowActivity extends Activity implements OnClickListener{
	
	//发送数据相关属性
	private Button mbtn_send;
	private EditText medittext; 
	private TextView tv_friendname;
	
	private String uunumber;
	
	//适配器相关属性
	private ListView mlistview;
	private ChatInfoAdapter madapter;
	private List<ChatInfo> datas = new ArrayList<ChatInfo>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chatwindow);
		initData();//初始化数据
		inntView();//初始化view
		Intent intent=getIntent();
		uunumber=intent.getStringExtra("uunumber");
	}
	//初始化视图
	private void inntView() {
		mlistview=(ListView) findViewById(R.id.chatwindow_lv);
		mbtn_send=(Button) findViewById(R.id.chatwindow_btn_send);
		mbtn_send.setOnClickListener(this);
		medittext=(EditText) findViewById(R.id.chatwindow_et_write);
		mlistview.setAdapter(madapter);
		tv_friendname=(TextView) findViewById(R.id.chatwindow_tv_friendname);
	}

//测试数据**********************************************************************************************
	
	private String[] msgArray = new String[] { "测试啦", "测试啦", "测试啦",
			"测试啦", "测试啦", "你妹", "测你妹", "测你妹",
			"测试啦" };

	private String[] dataArray = new String[] { "2012-12-12 12:00",
			"2012-12-12 12:10", "2012-12-12 12:11", "2012-12-12 12:20",
			"2012-12-12 12:30", "2012-12-12 12:35", "2012-12-12 12:40",
			"2012-12-12 12:50", "2012-12-12 12:50" };
	
//测试数据**********************************************************************************************
	
	private final static int COUNT = 8;
	
	private void initData() {
		for (int i = 0; i < COUNT; i++) {
			ChatInfo info = new ChatInfo();
			info.setDate(dataArray[i]);
			if (i % 2 == 0) {
				info.setNickname("守莣");
				info.setComeMesssge(true);
			} else {
				info.setNickname("冥寒");
				info.setComeMesssge(false);
			}

			info.setContent(msgArray[i]);
			datas.add(info);
		}
		madapter=new ChatInfoAdapter(this, datas);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.chatwindow_btn_send:
			send();
			break;
		}
	}
	//发送内容
	private void send() {
		String sendContent=medittext.getText().toString();
		if(sendContent.length()>0){
			ChatInfo chatinfo = new ChatInfo();
			chatinfo.setDate(getDate());
			chatinfo.setContent(sendContent);
			chatinfo.setComeMesssge(false);
			
			datas.add(chatinfo);
			madapter.notifyDataSetChanged();
			medittext.setText("");
			mlistview.setSelection(mlistview.getCount() - 1);
		}else{
			Toast.makeText(this, "发送内容不能为空！",  Toast.LENGTH_LONG).show();
		}
	}
	//获取系统时间
	private String getDate() {
		Calendar c = Calendar.getInstance();

		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH));
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":"
				+ mins);

		return sbBuffer.toString();
	}
}
