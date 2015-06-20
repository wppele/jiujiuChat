package com.wppele.jiujiuchat;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends Activity {
	private Timer timer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		timer=new Timer();
		setTimerTask();
	}
	//��ת����½����
	private void jumpToMusicList(){
		Intent intent=new Intent();
		intent.setClass(WelcomeActivity.this,LoginActivity.class);
		this.startActivity(intent);
	}
	//5���������½����
	private void setTimerTask(){
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				jumpToMusicList();
			}
		}, 5000);
	}
	//��ť��������½����
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.btn_welcome:
			jumpToMusicList();
			break;
		case R.id.login_btn_login:
			System.out.println();
		default:
			break;
		}
	}
	//��дonpause����
	@Override
	protected void onPause() {
		//���ͨ����ť�Ѿ����������б������ֹͣ��ʱ���룬�����ֹ��ӭ����
		timer.cancel();
		finish();
		super.onPause();
	}
}
