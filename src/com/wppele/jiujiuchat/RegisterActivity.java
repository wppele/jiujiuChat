package com.wppele.jiujiuchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}
	
	public void RegisterOnclick(View view){
		switch (view.getId()) {
		case R.id.register_btn_register:
			RegisterSuccess();
			break;
		default:break;
		}
	}
	private void RegisterSuccess(){
		Intent intent=new Intent();
		intent.setClass(RegisterActivity.this,LoginActivity.class);
		this.startActivity(intent);
		finish();
	}
}

	
