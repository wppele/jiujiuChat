package com.wppele.fragment;

import java.util.ArrayList;
import java.util.List;

import com.wppele.adapter.CommonAdapter;
import com.wppele.entity.Messages;
import com.wppele.jiujiuchat.MainActivity;
import com.wppele.jiujiuchat.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListView;

/**
 * 消息页面
 * @author yuzheng
 *
 */
public class MessageFragment extends Fragment {
	
	private ListView listview;
	private List<Messages> mess;
	private CommonAdapter commonAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_message, null);
		initData();//初始化数据
		initview(view);//初始化view
		
		return view;
	}

	private void initview(View view) {
		listview=(ListView) view.findViewById(R.id.fm_lv);
		listview.setAdapter(commonAdapter);
	}

	private void initData() {
		mess=new ArrayList<Messages>();
		Messages message= new Messages("守望o0轮回", "我是你爸爸", "22:15", "pictureUrl");
		mess.add(message);
		
		message= new Messages("枭雄∝絕影", "你是谁爸爸？", "22:15", "pictureUrl");
		mess.add(message);
		
		message= new Messages("冥寒", "你好啊！", "22:15", "pictureUrl");
		mess.add(message);
		
		message= new Messages("僵尸", "你是谁！", "22:15", "pictureUrl");
		mess.add(message);
		message= new Messages("陆老板", "真厉害啊", "22:15", "pictureUrl");
		mess.add(message);
		
		commonAdapter=new CommonAdapter(getActivity(), mess);
	}

}
