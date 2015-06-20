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
			//建立与服务器的链接..
			Socket socket = new Socket(sc.SERVER_IP,8898);
			out=new ObjectOutputStream(socket.getOutputStream());
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//将账号信息传送到服务器端
			out.writeObject(user);
			out.flush();
			//读取服务器返回结果-->账号密码是否正确
//			account_state=br.readLine();
			Log.e("login", user);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "false";
		}
		//将结果返回到LoginActivity
		return "true";
	}

}
