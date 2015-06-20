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
	//跳转到登陆界面
	private void jumpToMusicList(){
		Intent intent=new Intent();
		intent.setClass(WelcomeActivity.this,LoginActivity.class);
		this.startActivity(intent);
	}
	//5秒后跳进登陆界面
	private void setTimerTask(){
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				jumpToMusicList();
			}
		}, 5000);
	}
	//按钮点击进入登陆界面
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
	//重写onpause方法
	@Override
	protected void onPause() {
		//如果通过按钮已经进入音乐列表界面则停止计时进入，随后终止欢迎窗体
		timer.cancel();
		finish();
		super.onPause();
	}
}
