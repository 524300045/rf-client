package com.wologic.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domainnew.Container;
import com.wologic.domainnew.GoodsBarCode;
import com.wologic.domainnew.PackTaskDetail;
import com.wologic.domainnew.PackageAllDetail;
import com.wologic.domainnew.PreprocessInfo;
import com.wologic.domainnew.WarehouseAreaPickProcess;
import com.wologic.request.GoodsQueryRequest;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.request.StandardPickingContainerRequest;
import com.wologic.request.StandardPickingTaskRequest;
import com.wologic.response.StandPickTaskResponse;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class PickerEndActivity extends Activity {

	private TextView tbBack,tvmsg,tvContainer,tvAreaName,tvName,tvPlanNum,tvRealNum,tvStock;
	private EditText etNum;
	
	private MediaPlayer mediaPlayer;

	private String areaCode,areaName,container;
	
	private Button btnSure;
	
	private long id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picker_end);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		mediaPlayer = MediaPlayer.create(PickerEndActivity.this,
				R.raw.error);
		
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etNum = (EditText) findViewById(R.id.etNum);
		tvContainer=(TextView) findViewById(R.id.tvContainer);
		tvAreaName=(TextView) findViewById(R.id.tvAreaName);
		tvName=(TextView) findViewById(R.id.tvName);
		tvPlanNum=(TextView) findViewById(R.id.tvPlanNum);
		tvRealNum=(TextView) findViewById(R.id.tvRealNum);
		tvStock=(TextView) findViewById(R.id.tvStock);
		btnSure=(Button)findViewById(R.id.btnSure);
		Intent intent = getIntent();
		if (intent != null) {
			areaCode = intent.getStringExtra("areaCode");
			areaName= intent.getStringExtra("areaName");
			container= intent.getStringExtra("container");
			id=Long.valueOf(intent.getStringExtra("id"));
		}
		tvContainer.setText(container);
		tvAreaName.setText(areaName);
		initEvent();
		etNum.requestFocus();
		getPickingInfo();
	}

	

	private void initEvent() {
		etNum.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						tvmsg.setText("");
						tvmsg.setVisibility(View.GONE);
						String num = etNum.getText().toString()
								.trim();
						if (num.equals("")) {
							etNum.selectAll();
							Toaster.toaster("请输入拣货数量!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("请输入拣货数量!");
							
							return true;
						}
						etNum.setEnabled(false);
						sumbit();
						break;
					case KeyEvent.ACTION_DOWN:
						break;
					}
					return true;
				}
				return false;
			}
		});
	
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				sumbit();
			}
		});
	}
	
	
	private void getPickingInfo()
	{
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/standardPickingTask/getStandardPickingTask";
					StandardPickingTaskRequest  request=new StandardPickingTaskRequest();
					request.setId(id);
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						    StandPickTaskResponse response = JSON.parseObject(jsonSearch.optString("result"),StandPickTaskResponse.class);
							Message msg = new Message();
							msg.what = 1;
							msg.obj =response;
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
	

	private void sumbit() {
		
		final String num=etNum.getText().toString().trim();
		if (num.equals("")) {
			etNum.selectAll();
			Toaster.toaster("请输入拣货数量!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("请输入拣货数量!");
			
			return ;
		}
		etNum.setEnabled(false);
		btnSure.setEnabled(false);
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/standardPickingTask/savePickingInfo";
					StandardPickingContainerRequest  request=new StandardPickingContainerRequest();
					request.setId(id);
					request.setRealityNum(new BigDecimal(num));
					request.setContainerCode(container);
					request.setRealityUser(Common.UserName);
					request.setCreateUser(Common.UserName);
					request.setUpdateUser(Common.UserName);
					request.setWarehouseCode(Common.WareHouseCode);
					request.setCustomerCode(Common.CustomerCode);
					
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						
							Message msg = new Message();
							msg.what = 4;
							msg.obj = "拣货成功";
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
				StandPickTaskResponse response=(StandPickTaskResponse)msg.obj;
				
				tvName.setText(response.getGoodsName());
				tvPlanNum.setText(response.getPlanNum().toString());
				tvRealNum.setText(response.getRealityNum().toString());
				tvStock.setText(response.getTotalStock().toString());
				break;
			case 2:
				etNum.setEnabled(true);
				btnSure.setEnabled(true);
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				Toaster.toaster(msg.obj.toString());
				etNum.requestFocus();
				etNum.selectAll();
				break;
			case 4:
				etNum.setEnabled(true);
				btnSure.setEnabled(true);
				Toaster.toaster(msg.obj.toString());
				finish();
				break;
			default:
				etNum.setEnabled(true);
				break;
			}
		}
	};

	// 接受页面的返回值
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {

		}
	}



	@Override
	protected void onStart() {
		super.onStart();

	}

	protected void onDestroy() {
		super.onDestroy();
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	};

}
