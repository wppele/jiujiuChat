package com.wppele.util;

import android.content.Context;
import android.content.Intent;

import com.wppele.jiujiuchat.ChatWindowActivity;
import com.wppele.jiujiuchat.MainActivity;
import com.wppele.jiujiuchat.RegisterActivity;

public class JumpTo {
	public  void jumpToRegisterActivity(Context context) {
		Intent intent = new Intent();
		intent.setClass(context, RegisterActivity.class);
		context.startActivity(intent);
	}

	public void jumpToMainActivity(Context context,String uunumber) {
		Intent intent = new Intent();
		intent.putExtra("uunumber",uunumber);
		intent.setClass(context, MainActivity.class);
		context.startActivity(intent);
	}
	
	public void jumpToChatWindowActivity(Context context,int arg2) {
		Intent intent = new Intent();
		intent.putExtra("number", arg2);
		//intent.putExtra("uunumber",uunumber);
		intent.setClass(context, ChatWindowActivity.class);
		context.startActivity(intent);
	}
}
