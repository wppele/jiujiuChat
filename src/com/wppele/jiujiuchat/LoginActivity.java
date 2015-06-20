package com.wppele.jiujiuchat;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.gson.Gson;
import com.wppele.client.LoginVerify;
import com.wppele.entity.Users;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText et_username;
	private EditText et_password;
	private Users users;
	private Gson gson;
	private String str_json;
	private String userip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		gson = new Gson();
	}

	@Override
	protected void onStart() {
		super.onStart();
		users = new Users();
		userip = getLocalIpAddress(this);
		et_username = (EditText) findViewById(R.id.login_et_username);
		et_password = (EditText) findViewById(R.id.login_et_password);

	}

	public void LoginOnClick(View view) throws InterruptedException,
			ExecutionException {
		switch (view.getId()) {
		case R.id.login_register_link:
			jumpToRegisterActivity();
			// Log.e("btn","ע������");
			break;
		case R.id.login_btn_login:
			// �����½��ť��ȡuser�˻���Ϣ��
			users.setUsername(et_username.getText().toString());
			users.setPassword(et_password.getText().toString());
			users.setOpration("login");
			users.setUserip(userip);
			// �����ť�ж��Ƿ�������wifidd
			if (!isWifiConnected()) {
				Toast.makeText(this, "�뿪��wifi�Ա������糩ͨ", Toast.LENGTH_LONG)
						.show();
			} else {
				if (!users.getUsername().equals("")
						&& !users.getPassword().equals("")) {
					str_json = gson.toJson(users);
					// �����̳߳�
					ExecutorService pool = Executors.newFixedThreadPool(2);
					// ����Ϣ�����߳���
					Callable<String> account_verify = new LoginVerify(str_json);
					// ��ȡ���ؽ��-->�Ƿ���֤�ɹ�
					Future<String> verify_result = pool.submit(account_verify);
					if (verify_result.get().toString().equals("true")) {
						Toast.makeText(this, "��ӭ��" + users.getUsername(),
								Toast.LENGTH_LONG).show();
						jumpToMainActivity();
					} else {
						Toast.makeText(this, "�˺Ż��������", Toast.LENGTH_SHORT)
								.show();
					}
					Log.e("btn", users.getUserip());
				} else {
					Toast.makeText(this, "�뽫�˺Ż����붼����", Toast.LENGTH_SHORT)
							.show();
				}
			}

			break;

		default:
			break;
		}
	}

	private void jumpToRegisterActivity() {
		Intent intent = new Intent();
		intent.setClass(LoginActivity.this, RegisterActivity.class);
		this.startActivity(intent);
		finish();
	}

	private void jumpToMainActivity() {
		Intent intent = new Intent();
		intent.setClass(LoginActivity.this, MainActivity.class);
		this.startActivity(intent);
		finish();
	}

	private boolean isWifiConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			NetworkInfo networkInfo = cm.getActiveNetworkInfo();
			if (networkInfo != null
					&& networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
				return true;
			}
		}
		return false;
	}

	//��ȡip��ַ
	public String getLocalIpAddress(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ipAddress = wifiInfo.getIpAddress();
		String ip = formatIpAddress(ipAddress);
		return ip;
	}

	private static String formatIpAddress(int ipAdress) {
		return (ipAdress & 0xFF) + "." + ((ipAdress >> 8) & 0xFF) + "."
				+ ((ipAdress >> 16) & 0xFF) + "." + (ipAdress >> 24 & 0xFF);
	}

}
