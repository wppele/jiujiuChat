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
 * ��Ϣҳ��
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
		initData();//��ʼ������
		initview(view);//��ʼ��view
		
		return view;
	}

	private void initview(View view) {
		listview=(ListView) view.findViewById(R.id.fm_lv);
		listview.setAdapter(commonAdapter);
	}

	private void initData() {
		mess=new ArrayList<Messages>();
		Messages message= new Messages("����o0�ֻ�", "������ְ�", "22:15", "pictureUrl");
		mess.add(message);
		
		message= new Messages("���ۡؽ^Ӱ", "����˭�ְ֣�", "22:15", "pictureUrl");
		mess.add(message);
		
		message= new Messages("ڤ��", "��ð���", "22:15", "pictureUrl");
		mess.add(message);
		
		message= new Messages("��ʬ", "����˭��", "22:15", "pictureUrl");
		mess.add(message);
		message= new Messages("½�ϰ�", "��������", "22:15", "pictureUrl");
		mess.add(message);
		
		commonAdapter=new CommonAdapter(getActivity(), mess);
	}

}
