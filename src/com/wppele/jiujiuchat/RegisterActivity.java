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
	
	//�����ؼ�
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
	//�� onstart�������ҵ��ؼ�
	
	//��ע�ᰴť��ӵ���¼�
	//����¼����ж�ֵ��Ϊ��
	//if��Ϊ�ջ�ȡ�ռ��е�ֵ
	//����bean����
	
	
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
					Toast.makeText(this, "�����������벻ƥ�䣬����������", Toast.LENGTH_SHORT).show();
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
					// �����̳߳�
					ExecutorService pool = Executors.newFixedThreadPool(2);
					// ����Ϣ�����߳���
					Callable<String> account_verify = new RegisterSubmit(str_json);
					// ��ȡ���ؽ��-->�Ƿ���֤�ɹ�
					Future<String> verify_result = pool.submit(account_verify);
					String uunumber=verify_result.get().toString();
					Log.e("login", uunumber);
					RegisterSuccess(uunumber,password);
//					if(!uunumber.equals("false")){
//						RegisterSuccess(uunumber,password);
//					}else{
//						Toast.makeText(this, "ע��ʧ�ܣ����Ժ�����", Toast.LENGTH_SHORT).show();
//					}
				}
			}else{
				Toast.makeText(this, "����д������Ϣ", Toast.LENGTH_SHORT).show();
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
	 * ���ע��ɹ�
	 * ����RegisterSuccessActivityҳ��
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

	
