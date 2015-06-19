package com.wppele.fragment;

import com.wppele.jiujiuchat.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ÏûÏ¢Ò³Ãæ
 * @author yuzheng
 *
 */
public class MessageFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_message, null);
		return view;
	}

}
