package com.wppele.slidingmenu.view;

import com.wppele.jiujiuchat.R;
import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {

	private LinearLayout mWapper; //����ˮƽ�������е�LinearLayout
	private ViewGroup mMenu;      //�໬�˵���
	private ViewGroup mContent;   //��������
	private int mScreenWidth;     //��Ļ���
	private int mMenuWidth;       //����menu�Ŀ��
	
	private int mMenuRightPadding=50;//menu ���Ҳ���Ļ��ʣ����� 50dp
	
	private boolean once;
	
	/**
	 * δʹ���Զ�������ʱ������˫�ι��췽��
	 * @param context
	 * @param attrs
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs,-1);	
	}
	
	/**
	 * ���Զ���������ʱ���ô˹��췽��
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		//��ȡ�Լ� �Զ��������
		TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.slidingMenu, defStyle, 0);
		int n= a.getIndexCount();
		for(int i=0;i<n;i++){
			int attr=a.getIndex(i);
			switch (attr) {
			case R.styleable.slidingMenu_rightPadding:
				mMenuRightPadding=a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
				break; 
			}
		}
		
		a.recycle();//�ͷ�
		
		WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics=new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth=outMetrics.widthPixels;//Ϊ��ֵ 
		
	}



	public SlidingMenu(Context context) {
		this(context,null);
	}

	/**
	 * ������view�Ŀ�͸�
	 * �����Լ��Ŀ�͸�
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(!once){
			mWapper= (LinearLayout) getChildAt(0);
			mMenu=(ViewGroup) mWapper.getChildAt(0);
			mContent=(ViewGroup) mWapper.getChildAt(1);
			
			mMenuWidth = mMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;//menud�Ŀ��
			mContent.getLayoutParams().width=mScreenWidth;//content�Ŀ������Ϊ��Ļ���
			once=true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	/**
	 * ͨ������ƫ������menu����
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		
		super.onLayout(changed, l, t, r, b);
		if(changed){
			this.scrollTo(mMenuWidth, 0);
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			int scrollX=getScrollX();  //������ز��ֵĿ��
			//������ز��ֿ�ȴ���ԭ����1/2������menu
			if(scrollX>=mMenuWidth/2){
				this.smoothScrollTo(mMenuWidth, 0);
			}
			else{
				this.smoothScrollTo(0, 0);
			}
			return true;

		}
		return super.onTouchEvent(ev);
	}
	
	/**
	 * ����ʽ����ʵ�ֲ໬
	 * ƫ�������ú���Ҫ
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		float scale=l*1.0f/mMenuWidth;//1~0
		//�������Զ���������TranslationX
		//ViewHelper.setTranslationX(mMenu, mMenuWidth*scale);
		
		/**
		 * QQʽ����
		 */
		float rightScale=0.7f+0.3f*scale;
		float leftScale = 1.0f - scale * 0.3f;
		float leftAlpha = 0.6f + 0.4f * (1 - scale);

		ViewHelper.setScaleX(mMenu, mMenuWidth*scale*0.8f);
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, leftAlpha);
		// ����content�����ŵ����ĵ�
		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
	}
}
