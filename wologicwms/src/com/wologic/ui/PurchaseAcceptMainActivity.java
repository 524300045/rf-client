package com.wologic.ui;



import com.wologic.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

public class PurchaseAcceptMainActivity  extends FragmentActivity {

	
	private static FragmentManager fMgr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase_accept_main);
		//获取FragmentManager实例
		fMgr = getSupportFragmentManager();
		
		initFragment();
		dealBottomButtonsClickEvent();
		
	}
	
	private void initFragment() {
		FragmentTransaction ft = fMgr.beginTransaction();
		PurchaseNotEndFragment notEndFragment = new PurchaseNotEndFragment();
		ft.add(R.id.fragmentRoot, notEndFragment, "notEndFragment");
		ft.addToBackStack("notEndFragment");
		ft.commit();		
	}
	
	
	private void dealBottomButtonsClickEvent() { 
		findViewById(R.id.rbWeiXin).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(fMgr.findFragmentByTag("notEndFragment")!=null && fMgr.findFragmentByTag("notEndFragment").isVisible()) {
					return;
				}
				popAllFragmentsExceptTheBottomOne();

			}
		});
	
		findViewById(R.id.rbAddress).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popAllFragmentsExceptTheBottomOne();
				FragmentTransaction ft = fMgr.beginTransaction();
				ft.hide(fMgr.findFragmentByTag("notEndFragment"));
				PurchaseEndFragment sf = new PurchaseEndFragment();
				ft.add(R.id.fragmentRoot, sf, "PurchaseEndFragment");
				ft.addToBackStack("PurchaseEndFragment");
				ft.commit();
				
			}
		});
	
		
	findViewById(R.id.rbMe).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PurchaseAcceptMainActivity.this.finish();
			}
		});
	}
	
	public static void popAllFragmentsExceptTheBottomOne() {
		for (int i = 0, count = fMgr.getBackStackEntryCount() - 1; i < count; i++) {
			fMgr.popBackStack();
		}
	}
	//点击返回按钮
	@Override
	public void onBackPressed() {
		if(fMgr.findFragmentByTag("notEndFragment")!=null && fMgr.findFragmentByTag("notEndFragment").isVisible()) {
			PurchaseAcceptMainActivity.this.finish();
		} else {
			super.onBackPressed();
		}
	}
}
