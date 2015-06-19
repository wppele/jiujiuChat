package com.wppele.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;






import android.os.AsyncTask;
import android.util.Log;

public class LoginClient implements Runnable {
	private String user;
	public LoginClient() {
		// TODO Auto-generated constructor stub
	}
	public LoginClient(String user) {
		this.user=user;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket("192.16.137.1",8898);
			Log.e("login",user);
			ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(user);
			out.flush();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
