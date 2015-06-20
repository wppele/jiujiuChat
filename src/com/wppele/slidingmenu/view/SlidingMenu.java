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

	private LinearLayout mWapper; //横向水平滚动条中的LinearLayout
	private ViewGroup mMenu;      //侧滑菜单栏
	private ViewGroup mContent;   //内容区域
	private int mScreenWidth;     //屏幕宽度
	private int mMenuWidth;       //设置menu的宽度
	
	private int mMenuRightPadding=50;//menu 和右侧屏幕的剩余距离 50dp
	
	private boolean once;
	
	/**
	 * 未使用自定义属性时，调用双参构造方法
	 * @param context
	 * @param attrs
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs,-1);	
	}
	
	/**
	 * 当自定义了属性时调用此构造方法
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		//获取自己 自定义的属性
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
		
		a.recycle();//释放
		
		WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics=new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth=outMetrics.widthPixels;//为宽赋值 
		
	}



	public SlidingMenu(Context context) {
		this(context,null);
	}

	/**
	 * 设置子view的宽和高
	 * 设置自己的宽和高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(!once){
			mWapper= (LinearLayout) getChildAt(0);
			mMenu=(ViewGroup) mWapper.getChildAt(0);
			mContent=(ViewGroup) mWapper.getChildAt(1);
			
			mMenuWidth = mMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;//menud的宽度
			mContent.getLayoutParams().width=mScreenWidth;//content的宽度设置为屏幕宽度
			once=true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	/**
	 * 通过设置偏移量将menu隐藏
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
			int scrollX=getScrollX();  //获得隐藏部分的宽度
			//如果隐藏部分宽度大于原来的1/2，隐藏menu
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
	 * 抽屉式动画实现侧滑
	 * 偏移量设置很重要
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		float scale=l*1.0f/mMenuWidth;//1~0
		//调用属性动画，设置TranslationX
		//ViewHelper.setTranslationX(mMenu, mMenuWidth*scale);
		
		/**
		 * QQ式缩放
		 */
		float rightScale=0.7f+0.3f*scale;
		float leftScale = 1.0f - scale * 0.3f;
		float leftAlpha = 0.6f + 0.4f * (1 - scale);

		ViewHelper.setScaleX(mMenu, mMenuWidth*scale*0.8f);
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, leftAlpha);
		// 设置content的缩放的中心点
		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
	}
}
