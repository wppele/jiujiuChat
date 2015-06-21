package com.wppele.jiujiuchat;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import com.wppele.util.JumpTo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class RegisterSuccessActivity extends Activity {
	
	private TextView tv_uunumber;
	private Intent intent;
	private JumpTo jump;
	private String uunumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_success);
		tv_uunumber=(TextView) findViewById(R.id.register_success_tv_uunumber);
		intent=getIntent();
		uunumber=intent.getStringExtra("uunumber");
		String password=intent.getStringExtra("password");
		tv_uunumber.setText(uunumber);
		jump=new JumpTo();
		
	}
	
	
	public void Onclick(View view){
		switch (view.getId()) {
		case R.id.register_success_btn_login:
			jump.jumpToMainActivity(RegisterSuccessActivity.this,uunumber);
			this.finish();
			break;

		default:
			break;
		}
	}
}


