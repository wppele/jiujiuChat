package com.wppele.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

import com.wppele.util.StaticConfig;

import android.util.Log;

public class RegisterSubmit implements Callable<String> {

	private String user;
	private ObjectOutputStream out;
	private BufferedReader br;
	private StaticConfig sc;
	
	public RegisterSubmit() {
		// TODO Auto-generated constructor stub
	}

	public RegisterSubmit(String user) {
		this.user = user;
	}
	
	@Override
	public String call() throws Exception {
		try {
			//�����������������..
			Socket socket = new Socket(sc.SERVER_IP,8898);
			out=new ObjectOutputStream(socket.getOutputStream());
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//���˺���Ϣ���͵���������
			out.writeObject(user);
			out.flush();
			//��ȡ���������ؽ��-->�˺������Ƿ���ȷ
//			account_state=br.readLine();
			Log.e("login", user);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "false";
		}
		//��������ص�LoginActivity
		return "true";
	}

}
