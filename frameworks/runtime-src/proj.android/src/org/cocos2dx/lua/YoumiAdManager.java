package org.cocos2dx.lua;

import x.i.p.Uwaf;
import x.i.p.br.Uwak;
import x.i.p.br.Uwan;
import x.i.p.onlineconfig.Uwbn;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class YoumiAdManager implements IAdManager{
	private String TAG = YoumiAdManager.class.getSimpleName();
	private AppActivity mContext;
	private FrameLayout mLayout;
	private String appId = "4d4f1aa0735739cd";
	private String appSecret = "b4ad2897d78bd557";
	private boolean mShowAD = false;
	
	private FrameLayout mContainer;

	public YoumiAdManager(AppActivity context, FrameLayout layout) {
		mContext = context;
		mLayout = layout;
	}

	@Override
	public boolean init() {
		Uwaf.getInstance(mContext).init(appId, appSecret, false);
		Uwaf.getInstance(mContext).pft(true);
		Uwaf.getInstance(mContext).pcx("ShowAD", new Uwbn() {
		    @Override
		    public void pev(String key, String value) {
		        // 获取在线参数成功
		    	if (value.equals("true")) {
		    		Log.d(TAG,"showAD = " + key);
		    		mShowAD = true;
		    	} else {
		    		mShowAD = false;
		    	}
		    }

		    @Override
		    public void peu(String key) {
		        // 获取在线参数失败，可能原因有：键值未设置或为空、网络异常、服务器异常
		    	Log.d(TAG, "获取在线参数失败");
		    }
		});
		return true;
	}

	@Override
	public void showBannerAd() {
		if (mContainer != null) {
			mLayout.removeView(mContainer);
		}
		if (mShowAD) {
			mContainer = new FrameLayout(mContext);
			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, 160);
			params.gravity = Gravity.BOTTOM | Gravity.CENTER;
			mLayout.addView(mContainer, params);
			Uwan adView = new Uwan(mContext, Uwak.FIT_SCREEN);
			mContainer.addView(adView);
		}
//		Uwaf.getInstance(mContext).pcx("ShowAD", new Uwbn() {
//		    @Override
//		    public void pev(String key, String value) {
//		        // 获取在线参数成功
//		    	if (value.equals("true")) {
//		    		Log.d(TAG,"showAD = " + key);
//		    		mContainer = new FrameLayout(mContext);
//					FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, 160);
//					params.gravity = Gravity.BOTTOM | Gravity.CENTER;
//					mLayout.addView(mContainer, params);
//					Uwan adView = new Uwan(mContext, Uwak.FIT_SCREEN);
//					mContainer.addView(adView);
//		    	}
//		    }
//
//		    @Override
//		    public void peu(String key) {
//		        // 获取在线参数失败，可能原因有：键值未设置或为空、网络异常、服务器异常
//		    	Log.d(TAG, "获取在线参数失败");
//		    }
//		});

	}

	@Override
	public void hideBannerAd() {
		if (mContainer != null) {
			mLayout.removeView(mContainer);
			mContainer = null;
		}
	}

}
