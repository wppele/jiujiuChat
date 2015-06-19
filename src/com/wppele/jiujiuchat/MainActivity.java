package com.wppele.jiujiuchat;



import com.wppele.fragment.ContactsFragment;
import com.wppele.fragment.MessageFragment;














import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends FragmentActivity implements OnTouchListener{

	//�໬�˵�
	//��Ļ��� 
	private int screenWidth;
	private int leftEdge;  
	//menu�໬���ɻ����Ĳ��ֵ
	private int rightEdge = 0;
	//�໬Ԥ�����
	private int menuPadding = 150; 
	//�����ݲ���
	private View content; 
	//�˵�����
	private View menu;  
	//menu���ֵĲ�����ͨ���˲���������leftMargin��ֵ�� 
	private LinearLayout.LayoutParams menuParams;  
	//��¼��ָ����ʱ�ĺ�����
	private float xDowm;
	private float xMove;
	private float xUp;
	//menu��ǰ����ʾ�������ء�ֻ����ȫ��ʾ������menuʱ�Ż���Ĵ�ֵ�����������д�ֵ��Ч�� 
	private boolean isMenuVisible;
	//������ָ�����ٶ�
	private VelocityTracker VelocityTracker;

	
	
	
	
	
	//�ײ��˵�
	private FragmentTabHost tabHost ;
	private RadioGroup radioGroup ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initTabHost();
		initRadioGroup();
		initValues();  
		content.setOnTouchListener(this);  

	}
	
	

	/**
	 * Ϊ�ײ��˵����ҳ��֧��
	 */
	private void initTabHost() {
		// ע��id
				tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost) ;
				// ʹ��FragmentTabHost����������FragmentTabHost����setup������Ŀ��ָ��FragmentTabHost�����ݼ��ص�����
				tabHost.setup(this, getSupportFragmentManager(), R.id.main_realTabcontent) ;
				// ���ñ�ǩ��ʾ�ı��Լ���Ӧ���ݲ���
				tabHost.addTab(
						tabHost.newTabSpec("message").setIndicator("��Ϣ", null), 		// ���ñ�ǩ��ʾ
						MessageFragment.class, 		// ��������������һ��Fragment
						null			// ������Fragmentʱ�������ʼ��ʱFragment��Ҫ����������Bundle�д��ݣ��������Ҫ�������˴�Ϊnull
						) ;
				tabHost.addTab(
						tabHost.newTabSpec("contacts").setIndicator("��ϵ��", null), 		// ���ñ�ǩ��ʾ
						ContactsFragment.class, 		// ��������������һ��Fragment
						null			// ������Fragmentʱ�������ʼ��ʱFragment��Ҫ����������Bundle�д��ݣ��������Ҫ�������˴�Ϊnull
						) ;
				
				
				// FragmentTabHost �ļ�����
				tabHost.setOnTabChangedListener(new OnTabChangeListener() {			
					// �����ǵ�ǰѡ�б�ǩ�ı�ʶ�ַ���
					public void onTabChanged(String arg0) {
						Toast.makeText(MainActivity.this, arg0, Toast.LENGTH_LONG).show();
					}
				}) ;
				
	}

	/**
	 * ��ʼ���ײ��˵�
	 */
	private void initRadioGroup() {
		radioGroup = (RadioGroup) findViewById(R.id.footermenu_rgroup) ;
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				switch(checkedId) {
				case R.id.menu_btn_message :
					tabHost.setCurrentTab(0) ;
					break;
				case R.id.menu_btn_contacts :
					tabHost.setCurrentTab(1) ;
					break;
				}
			}
		}) ;
	}
	
	/**
	 * ��߲˵�����
	 * ��ʼ��һЩ�ؼ������ݡ�������ȡ��Ļ�Ŀ�ȣ���content�����������ÿ�ȣ���menu�����������ÿ�Ⱥ�ƫ�ƾ���ȡ� 
	 */
	private void initValues() {
		WindowManager window=(WindowManager) getSystemService(Context.WINDOW_SERVICE);
		screenWidth=window.getDefaultDisplay().getWidth();
		content=findViewById(R.id.content);
		menu=findViewById(R.id.menu);
		menuParams=(LayoutParams) menu.getLayoutParams();
		//��menu�Ŀ������Ϊ��Ļ��ȼ�ȥmenupadding
		menuParams.width=screenWidth=menuPadding;
		//���Ե��ֵ��ֵΪmenu��ȵĸ���
		leftEdge=-menuParams.width;
		//menu��leftmargin����Ϊ���Ե��ֵ��������ʼ��ʱmenu�ͱ�ζ���ɼ�
		menuParams.leftMargin=leftEdge;
		//��content�Ŀ������Ϊ��Ļ��ȣ���ʼ��ʱ����ɼ�
		content.getLayoutParams().width=screenWidth;
		
	}
	@Override
	public boolean onTouch(View v, MotionEvent event){
		createVelocityRracker(event);
		switch(event.getAction()){
		//��ָ����ʱ��¼��������
		case MotionEvent.ACTION_DOWN:
			xDowm=event.getRawX();
			break;
		//��ָ�ƶ�ʱ���ԱȰ���ʱ�ĺ���������ƶ����룬����leftmarginֵ��ȷ����ʾ��������menu
		case MotionEvent.ACTION_MOVE:
			xMove=event.getRawX();
			int distanceX=(int)(xMove-xDowm);
			if(isMenuVisible){
				menuParams.leftMargin=distanceX;
			}
			else{
				menuParams.leftMargin=leftEdge+distanceX;//*********************************************************
			}
			if(menuParams.leftMargin<leftEdge){
				menuParams.leftMargin=leftEdge;
			}
			else if(menuParams.leftMargin>rightEdge){
				menuParams.leftMargin=rightEdge;
			}
			menu.setLayoutParams(menuParams);
			break;
		case MotionEvent.ACTION_UP:
			//��ָ̧��ʱ�������жϵ�ǰ���Ƶ���ͼ����������ʾmenu����content����
			xUp=event.getRawX();
			if(wantToMenu()){
				if(toMenu()){
					scrollToMenu();
				}
				else{
					scrollToContent();
				}
			}
			else if(wantToContent()){
				if(toContent()){
					scrollToContent();
				}
				else{
					scrollToMenu();
				}
			}
			recycleVelocityTracker();
			break;
		}
		return true;
	}
	
	
	//�ж�������ͼ�ǲ�������ʾcontent�������ָ�ƶ������Ǹ������ҵ�ǰmenu�ɼ�������Ϊ��ǰ��ͼΪ��ʾcontent
	//return ��ǰ������ͼ��ʾcontent����true�����򷵻�false
	private boolean wantToContent(){
		return xUp-xDowm<0&&isMenuVisible;
	}
	//�ж�������ͼ�ǲ�������ʾmenu�������ָ�ƶ��������������ҵ�ǰmenu���ɼ�������Ϊ��ǰ��ͼΪ��ʾmenu
	//return ��ǰ������ͼ��ʾmenu����true�����򷵻�false
	private boolean wantToMenu(){
		return xUp-xDowm>0&&!isMenuVisible;
	}
	//�ж��Ƿ�Ӧ�ù�����menuչʾ����������ƶ�����move������Ļ��1/2������ΪӦ�ù�����menuչʾ������ 
	//return���Ӧ�ù�����menuչʾ����������true�����򷵻�false
	private boolean toMenu(){
		return xUp-xDowm>screenWidth/2;
	}
	//�ж��Ƿ�Ӧ�ù�����contentչʾ�����������ָ�ƶ��������menuPadding������Ļ��1/2�� ������ΪӦ�ù�����contentչʾ������ 
	//return���Ӧ�ù�����contentչʾ����������true�����򷵻�false
	private boolean toContent(){
		return xUp-xDowm+menuPadding>screenWidth/2;
	}
	//����Ļ������menu���ٶ�����Ϊ30
	private void scrollToMenu(){
		new ScrollTask().execute(30);
	}
	//����Ļ������content���ٶ�����Ϊ-30
	private void scrollToContent(){
		new ScrollTask().execute(-30);
	}
	//����VelocityTracker���󣬲�������content����Ļ����¼����뵽VelocityTracker��
    private void createVelocityTracker(MotionEvent event) {  
        if (VelocityTracker == null) {  
            VelocityTracker = VelocityTracker.obtain();  
        }  
        VelocityTracker.addMovement(event);  
    }  
    
    //����VelocityTracker����
  	private void recycleVelocityTracker() {
  		VelocityTracker.recycle();
  		VelocityTracker=null;
  	}
    
    
    
	class ScrollTask extends AsyncTask<Integer, Integer,Integer>{

		@Override
		protected Integer doInBackground(Integer... speed) {
			int leftMatgin=menuParams.leftMargin;
			//���ݴ�����ٶ����������棬������������߽���ұ߽磬�˳�ѭ��
			while(true){
				leftMatgin=leftMatgin+speed[0];
				if(leftMatgin>rightEdge){
					leftMatgin=rightEdge;
					break;
				}
				if(leftMatgin<leftMatgin){
					leftMatgin=leftEdge;
					break;
				}
				publishProgress(leftMatgin);
				//Ϊ��Ҫ�й�����Ч��������ÿ��ѭ��ʹ�س�˯��20���룬�����ۿɼ�
				sleep(20);
			}
			if(speed[0]>0){
				isMenuVisible=true;
			}
			else{
				isMenuVisible=false;
			}
			return leftMatgin;
		}
		
		@Override
		protected void onProgressUpdate(Integer... leftMargin) {
			menuParams.leftMargin=leftMargin[0];
			menu.setLayoutParams(menuParams);
		}

		@Override
		protected void onPostExecute(Integer leftMargin) {
			menuParams.leftMargin = leftMargin;  
		    menu.setLayoutParams(menuParams);  
		}
		
		//ʹ��ǰ�߳�˯��
		private void sleep(int i) {
			try {
				Thread.sleep(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
	private void createVelocityRracker(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}



	
	
	
}
