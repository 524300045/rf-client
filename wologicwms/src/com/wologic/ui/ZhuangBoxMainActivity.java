package com.wologic.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.control.ImageFunction;
import com.wologic.dao.ItemDataDao;
import com.wologic.dao.RuKuDao;
import com.wologic.dao.VersionInfoDao;
import com.wologic.dao.WFunctionDao;
import com.wologic.domain.VersionInfo;
import com.wologic.domain.WFuction;
import com.wologic.domain.WorkItem;
import com.wologic.domainnew.Menu;
import com.wologic.domainnew.SendWave;
import com.wologic.domainnew.SubMenu;
import com.wologic.domainnew.WaveCustomerStore;
import com.wologic.request.StandardPickingTaskRequest;
import com.wologic.request.WaveCustomerStoreRequest;
import com.wologic.response.AreaPickerInfoResponse;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
//主界面显示设置,table1存放显示的图标信息
public class ZhuangBoxMainActivity extends Activity {


	private TextView tbBack,tvTip;
	
	private Button btnAll;

	private TableLayout tl;
	
	private List<WaveCustomerStore> waveCustomerStoreList;
	
	private List<SendWave> sendWaveList;
	
	private Button btnSure;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
			 {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zhuangbox_main);
		
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btnAll=(Button) findViewById(R.id.btnAll);
		btnSure=(Button) findViewById(R.id.btnSure);
		tl = (TableLayout)findViewById(R.id.table1);
		tvTip= (TextView) findViewById(R.id.tvTip);
		btnAll.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(ZhuangBoxMainActivity.this,
						PartnerPickerActivity.class);
				
				startActivityForResult(intent, 1);
			}
		});
		
		btnSure.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				if(sendWaveList==null||sendWaveList.size()==0)
				{
					Toaster.toaster("请选择批次");
					return;
				}
				
				Intent intent = new Intent(ZhuangBoxMainActivity.this,
						PartnerPickerActivity.class);
				intent.putExtra("sendWaveList",(Serializable)sendWaveList);// 传递入库单号
				startActivityForResult(intent, 1);
			}
		});
		
		sendWaveList=new ArrayList<SendWave>();
		
		getWaveInfo();
	}
	
	
	private void getWaveInfo() {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/waveCustomerStore/getCustomerWave";
					WaveCustomerStoreRequest  request=new WaveCustomerStoreRequest();
					request.setWarehouseCode(Common.WareHouseCode);
					request.setCustomerCode(Common.CustomerCode);
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						
						waveCustomerStoreList = JSON
									.parseArray(
											jsonSearch
													.opt("result")
													.toString(),
													WaveCustomerStore.class);
							Message msg = new Message();
							msg.what =1;
							msg.obj = "";
							handler.sendMessage(msg);
					
					} 
					else
					{
						
						Message msg = new Message();
						msg.what = 2;
						msg.obj = jsonSearch.optString("message");
						handler.sendMessage(msg);
					}

				} catch (Exception e) {
					System.out.print(e.getMessage());
					Message msg = new Message();
					msg.what =2;
					msg.obj = "网络异常,请检查网络连接";
					handler.sendMessage(msg);

				}
			}
		});
		mThread.start();
	}
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				initWave();
				break;
			case 2:
				Toaster.toaster(msg.obj.toString());
				break;
		
			default:
				
				break;
			}
		}
	};
	
	

	@SuppressLint("NewApi") private void initWave() {
		
		List<SendWave> waveList=new ArrayList<SendWave>(); 
		if(waveCustomerStoreList!=null&&waveCustomerStoreList.size()>0)
		{
			for(WaveCustomerStore waveCustomerStore:waveCustomerStoreList)
			{
				SendWave sendWave=new SendWave();
				sendWave.setWaveCode(waveCustomerStore.getWaveCode());
				sendWave.setWaveName(waveCustomerStore.getWaveName());
				waveList.add(sendWave);
			}
		}
		
		if (waveList == null || waveList.size()==0)
		{
			btnSure.setVisibility(View.GONE);
		}
			
		
		
		if (waveList != null && waveList.size() > 0) {
			
			for(SendWave sendWave:waveList)
			{
				TableRow tableRow1 = new TableRow(ZhuangBoxMainActivity.this);
				tableRow1.setPadding(5, 10, 5, 5);
				tl.addView(tableRow1);
				
				CheckBox cb=new CheckBox(ZhuangBoxMainActivity.this);
				cb.setTextColor(Color.BLACK);
				cb.setText(sendWave.getWaveName());
				cb.setPadding(10, 20, 10, 20);
				cb.setTag(sendWave);
				cb.setTextSize(16);
				
				cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
	                @Override 
	                public void onCheckedChanged(CompoundButton buttonView, 
	                        boolean isChecked) { 
	                    
	                    if(isChecked){ 
	                    	SendWave sendWave=(SendWave)buttonView.getTag();
	                       
	                    	sendWaveList.add(sendWave);
	                    }else{ 
	                    	SendWave sendWave=(SendWave)buttonView.getTag();
	                    	sendWaveList.remove(sendWave);
	                    } 
	                    String tipMsg="";
	                    for(SendWave sendWave:sendWaveList)
	                    {
	                    	tipMsg+=sendWave.getWaveName()+",";
	                    }
	                    tvTip.setText(tipMsg.trim());
	                } 
	            }); 
				tableRow1.addView(cb);
				
				
//				Button btn=new Button(ZhuangBoxMainActivity.this);
//		       // btn.setId(Integer.parseInt(sendWave.getWaveCode()));
//				btn.setText(sendWave.getWaveName());
//				btn.setPadding(10, 20, 10, 20);
//				btn.setTag(sendWave);
//				btn.setTextSize(16);
//				btn.setBackground(getResources().getDrawable(R.drawable.shape_button));
//				btn.setOnClickListener(new OnClickListener(){
//
//					@Override
//					public void onClick(View arg0) {
//						
//						Button view=(Button)arg0;
//						SendWave sendWave=(SendWave)view.getTag();
//						Intent intent = new Intent(ZhuangBoxMainActivity.this,
//								PartnerPickerActivity.class);
//						intent.putExtra("waveCode", sendWave.getWaveCode());// 传递入库单号
//						intent.putExtra("waveName", sendWave.getWaveName());
//						startActivityForResult(intent, 1);
//						
//					}
//					
//				});
//				tableRow1.addView(btn);
				
			}
			
		}
	}


}
