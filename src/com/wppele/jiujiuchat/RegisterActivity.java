package com.wppele.jiujiuchat;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.gson.Gson;
import com.wppele.client.LoginVerify;
import com.wppele.client.RegisterSubmit;
import com.wppele.entity.Users_register;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.gson.Gson;
import com.wppele.client.LoginVerify;
import com.wppele.client.RegisterSubmit;
import com.wppele.entity.Users_register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity{
	
	//声明控件
	private EditText et_nickname;
	private EditText et_password;
	private EditText et_realname;
	private EditText et_idnumber;
	private EditText et_telphone;
	private EditText et_repassword;
	private Users_register users;
	private String nickname;
	private String password;
	private String repassword;
	private String realname;
	private String idnumber;
	private String telphone;
	private Gson gson;
	private String str_json;
	//在 onstart（）中找到控件
	
	//在注册按钮添加点击事件
	//点击事件中判断值不为空
	//if不为空获取空间中的值
	//返回bean对象
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		et_nickname=(EditText) findViewById(R.id.register_et_nickName);
		et_password=(EditText) findViewById(R.id.register_et_password);
		et_realname=(EditText) findViewById(R.id.register_et_realName);
		et_idnumber=(EditText) findViewById(R.id.register_et_idCard);
		et_telphone=(EditText) findViewById(R.id.register_et_phone);
		et_repassword=(EditText) findViewById(R.id.register_et_password2);
		gson = new Gson();
		users=new Users_register();
	}
	public void RegisterOnclick(View view) throws InterruptedException, ExecutionException{

		switch (view.getId()) {
		case R.id.register_btn_register:
			nickname=et_nickname.getText().toString();
			password=et_password.getText().toString();
			repassword=et_repassword.getText().toString();
			realname=et_realname.getText().toString();
			idnumber=et_idnumber.getText().toString();
			telphone=et_telphone.getText().toString();
			if(!nickname.equals("")&&!password.equals("")&&!repassword.equals("")&&!realname.equals("")&&!idnumber.equals("")&&!telphone.equals("")){
				if(!password.equals(repassword)){
					Toast.makeText(this, "两次密码输入不匹配，请重新输入", Toast.LENGTH_SHORT).show();
					et_password.setText("");
					et_repassword.setText("");
				}else{
					users.setNickname(nickname);
					users.setPassword(password);
					users.setRealname(realname);
					users.setIdnumber(idnumber);
					users.setTelphone(telphone);
					users.setOpration("register");
					str_json = gson.toJson(users);
					// 创建线程池
					ExecutorService pool = Executors.newFixedThreadPool(2);
					// 将信息传入线程中
					Callable<String> account_verify = new RegisterSubmit(str_json);
					// 获取返回结果-->是否验证成功
					Future<String> verify_result = pool.submit(account_verify);
					String uunumber=verify_result.get().toString();
					Log.e("login", uunumber);
					RegisterSuccess(uunumber,password);
//					if(!uunumber.equals("false")){
//						RegisterSuccess(uunumber,password);
//					}else{
//						Toast.makeText(this, "注册失败，请稍后再试", Toast.LENGTH_SHORT).show();
//					}
				}
			}else{
				Toast.makeText(this, "请填写完整信息", Toast.LENGTH_SHORT).show();
			}
			break;
		default:break;
		}
	}
	public void BackToAgoPageonclick(View view){
		Intent intent=new Intent();
		intent.setClass(RegisterActivity.this,LoginActivity.class);
		this.startActivity(intent);
		finish();
	}
	/**
	 * 如果注册成功
	 * 开启RegisterSuccessActivity页面
	 */
	private void RegisterSuccess(String uunumber,String password){
		Intent intent=new Intent();
		intent.putExtra("uunumber", uunumber);
		intent.putExtra("password", password);
		intent.setClass(RegisterActivity.this,RegisterSuccessActivity.class);
		this.startActivity(intent);
		finish();
	}
}

	
