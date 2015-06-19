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

	//侧滑菜单
	//屏幕宽度 
	private int screenWidth;
	private int leftEdge;  
	//menu侧滑最多可滑到的侧边值
	private int rightEdge = 0;
	//侧滑预留宽度
	private int menuPadding = 150; 
	//主内容布局
	private View content; 
	//菜单布局
	private View menu;  
	//menu布局的参数，通过此参数来更改leftMargin的值。 
	private LinearLayout.LayoutParams menuParams;  
	//记录手指按下时的横坐标
	private float xDowm;
	private float xMove;
	private float xUp;
	//menu当前是显示还是隐藏。只有完全显示或隐藏menu时才会更改此值，滑动过程中此值无效。 
	private boolean isMenuVisible;
	//计算手指滑动速度
	private VelocityTracker VelocityTracker;

	
	
	
	
	
	//底部菜单
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
	 * 为底部菜单添加页面支持
	 */
	private void initTabHost() {
		// 注意id
				tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost) ;
				// 使用FragmentTabHost，必须先让FragmentTabHost调用setup方法，目的指明FragmentTabHost的内容加载到哪里
				tabHost.setup(this, getSupportFragmentManager(), R.id.main_realTabcontent) ;
				// 设置标签显示文本以及相应内容部分
				tabHost.addTab(
						tabHost.newTabSpec("message").setIndicator("消息", null), 		// 设置标签显示
						MessageFragment.class, 		// 设置内容来自哪一个Fragment
						null			// 当加载Fragment时，如果初始化时Fragment需要参数，放在Bundle中传递；如果不需要参数，此处为null
						) ;
				tabHost.addTab(
						tabHost.newTabSpec("contacts").setIndicator("联系人", null), 		// 设置标签显示
						ContactsFragment.class, 		// 设置内容来自哪一个Fragment
						null			// 当加载Fragment时，如果初始化时Fragment需要参数，放在Bundle中传递；如果不需要参数，此处为null
						) ;
				
				
				// FragmentTabHost 的监听器
				tabHost.setOnTabChangedListener(new OnTabChangeListener() {			
					// 参数是当前选中标签的标识字符串
					public void onTabChanged(String arg0) {
						Toast.makeText(MainActivity.this, arg0, Toast.LENGTH_LONG).show();
					}
				}) ;
				
	}

	/**
	 * 初始化底部菜单
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
	 * 侧边菜单部分
	 * 初始化一些关键性数据。包括获取屏幕的宽度，给content布局重新设置宽度，给menu布局重新设置宽度和偏移距离等。 
	 */
	private void initValues() {
		WindowManager window=(WindowManager) getSystemService(Context.WINDOW_SERVICE);
		screenWidth=window.getDefaultDisplay().getWidth();
		content=findViewById(R.id.content);
		menu=findViewById(R.id.menu);
		menuParams=(LayoutParams) menu.getLayoutParams();
		//将menu的宽度设置为屏幕宽度减去menupadding
		menuParams.width=screenWidth=menuPadding;
		//左边缘的值赋值为menu宽度的负数
		leftEdge=-menuParams.width;
		//menu的leftmargin设置为左边缘的值，这样初始化时menu就变味不可见
		menuParams.leftMargin=leftEdge;
		//将content的宽度设置为屏幕宽度，初始化时让其可见
		content.getLayoutParams().width=screenWidth;
		
	}
	@Override
	public boolean onTouch(View v, MotionEvent event){
		createVelocityRracker(event);
		switch(event.getAction()){
		//手指按下时记录按下坐标
		case MotionEvent.ACTION_DOWN:
			xDowm=event.getRawX();
			break;
		//手指移动时，对比按下时的横坐标计算移动距离，调整leftmargin值，确定显示还是隐藏menu
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
			//手指抬起时，进行判断当前手势的意图，来决定显示menu还是content界面
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
	
	
	//判断手势意图是不是想显示content，如果手指移动距离是负数，且当前menu可见，则认为当前意图为显示content
	//return 当前手势意图显示content返回true，否则返回false
	private boolean wantToContent(){
		return xUp-xDowm<0&&isMenuVisible;
	}
	//判断手势意图是不是想显示menu，如果手指移动距离是正数，且当前menu不可见，则认为当前意图为显示menu
	//return 当前手势意图显示menu返回true，否则返回false
	private boolean wantToMenu(){
		return xUp-xDowm>0&&!isMenuVisible;
	}
	//判断是否应该滚动将menu展示出来，如果移动距离move大于屏幕的1/2，就认为应该滚动将menu展示出来。 
	//return如果应该滚动将menu展示出来，返回true，否则返回false
	private boolean toMenu(){
		return xUp-xDowm>screenWidth/2;
	}
	//判断是否应该滚动将content展示出来，如果手指移动距离加上menuPadding大于屏幕的1/2， ，就认为应该滚动将content展示出来。 
	//return如果应该滚动将content展示出来，返回true，否则返回false
	private boolean toContent(){
		return xUp-xDowm+menuPadding>screenWidth/2;
	}
	//将屏幕滚动到menu，速度设置为30
	private void scrollToMenu(){
		new ScrollTask().execute(30);
	}
	//将屏幕滚动到content，速度设置为-30
	private void scrollToContent(){
		new ScrollTask().execute(-30);
	}
	//创建VelocityTracker对象，并将触摸content界面的滑动事件加入到VelocityTracker当
    private void createVelocityTracker(MotionEvent event) {  
        if (VelocityTracker == null) {  
            VelocityTracker = VelocityTracker.obtain();  
        }  
        VelocityTracker.addMovement(event);  
    }  
    
    //回收VelocityTracker对象
  	private void recycleVelocityTracker() {
  		VelocityTracker.recycle();
  		VelocityTracker=null;
  	}
    
    
    
	class ScrollTask extends AsyncTask<Integer, Integer,Integer>{

		@Override
		protected Integer doInBackground(Integer... speed) {
			int leftMatgin=menuParams.leftMargin;
			//根据传入的速度来滚动界面，当滚动到达左边界或右边界，退出循环
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
				//为了要有滚动的效果产生，每次循环使县城睡眠20毫秒，是肉眼可见
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
		
		//使当前线程睡眠
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
