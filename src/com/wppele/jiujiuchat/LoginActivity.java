package com.wppele.jiujiuchat;

import com.wppele.entity.Users;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText et_username;
	private EditText et_password;
	private Users users;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
	@Override
	protected void onStart() {
		super.onStart();
		users=new Users();
		et_username=(EditText) findViewById(R.id.login_et_username);
		et_password=(EditText) findViewById(R.id.login_et_password);
		
	}
	public void LoginOnClick(View view){
		switch (view.getId()) {
		case R.id.login_register_link:
			jumpToRegisterActivity();
			//Log.e("btn","注册链接");
			break;
		case R.id.login_btn_login:
			//点击登陆按钮获取user账户信息。
//			users.setUsername(et_username.getText().toString());
//			users.setPassword(et_password.getText().toString());
//			Log.e("btn",users.getUsername()+"  "+users.getPassword());
			//跳转到主页面，测试程序使用
			jumpToMainActivity();
			break;

		default:
			break;
		}
	}
	private void jumpToRegisterActivity() {
		Intent intent=new Intent();
		intent.setClass(LoginActivity.this,RegisterActivity.class);
		this.startActivity(intent);
		finish();
	}
	private void jumpToMainActivity(){
		Intent intent=new Intent();
		intent.setClass(LoginActivity.this,MainActivity.class);
		this.startActivity(intent);
		finish();
	}
}
