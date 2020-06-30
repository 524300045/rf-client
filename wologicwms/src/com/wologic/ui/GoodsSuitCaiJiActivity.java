package com.wologic.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domainnew.ContainerSkuRel;
import com.wologic.domainnew.Goods;
import com.wologic.domainnew.GoodsBarCode;
import com.wologic.request.ContainerSkuRelRequest;
import com.wologic.request.GoodsBarcodeRequest;
import com.wologic.request.StandSkuTaskRequest;
import com.wologic.request.StandardSortingRequest;
import com.wologic.response.StandPickTaskResponse;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class GoodsSuitCaiJiActivity extends Activity {

	private TextView tbBack;
	private EditText  etTotalWeight;
	private Button btnSure;
	private TextView tvmsg,  tvSkuCode, tvGoodsName,tvDate,tvPhyUnit,tvUnit;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerOk;
	

	private String skuCode;
	
	private List<Goods> goodsList=null;
	 
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_suit_caiji);
		Intent intent = getIntent();
		if (intent != null) {
			
			skuCode=intent.getStringExtra("skucode");
		
		}
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		mediaPlayer = MediaPlayer.create(GoodsSuitCaiJiActivity.this, R.raw.error);
		mediaPlayerOk = MediaPlayer.create(GoodsSuitCaiJiActivity.this, R.raw.ok);
		lv=(ListView) findViewById(R.id.lv);
		tvSkuCode = (TextView) findViewById(R.id.tvSkuCode);
		tvGoodsName = (TextView) findViewById(R.id.tvGoodsName);
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		tvDate= (TextView) findViewById(R.id.tv_date);
		tvUnit=(TextView) findViewById(R.id.tvUnit);
		etTotalWeight = (EditText) findViewById(R.id.etTotalWeight);
		btnSure = (Button) findViewById(R.id.btnSure);
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				sumbit();
			}
		});
		
		lv.setAdapter(new ListSuitAdapter(this));
		 
		setListViewHeightBasedOnChildren(lv);
		goodsList=new ArrayList<Goods>();
		for(int i=1;i<6;i++)
		{
			Goods goods=new Goods();
			goods.setSkuCode("1");
			goods.setGoodsName("2");
			goodsList.add(goods);
		}
	//	load(skuCode);
		
	}
	
	public void setListViewHeightBasedOnChildren(ListView listView) {
		 
		  ListAdapter listAdapter = listView.getAdapter();
		 
		  if (listAdapter == null) {
		   return;
		  }
		 
		  int totalHeight = 0;
		 
		  for (int i = 0; i < listAdapter.getCount(); i++) {
		   View listItem = listAdapter.getView(i, null, listView);
		   listItem.measure(0, 0);
		   totalHeight += listItem.getMeasuredHeight();
		  }
		 
		  ViewGroup.LayoutParams params = listView.getLayoutParams();
		 
		  params.height = totalHeight
		    + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		 
		 
		 
		  listView.setLayoutParams(params);
		 }
	
	 public void saveEditData(int position, String str) {
	        Toast.makeText(this,str+"----"+position,Toast.LENGTH_LONG).show();
	    }

	private void load(final String skuCode) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();
					String searchUrl = Constant.url
							+ "/standPackTask/getStandTaskNew";
					StandSkuTaskRequest request = new StandSkuTaskRequest();
					request.setCustomerCode(Common.CustomerCode);
		
					request.setWarehouseCode(Common.WareHouseCode);
					
					
					
				
					String json2 = JSON.toJSONString(request);
					String resultSearch2 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json2);
					JSONObject jsonSearch2 = new JSONObject(resultSearch2);
					if (jsonSearch2.optString("code").toString().equals("200"))
					{
						if ("null".equals(jsonSearch2.opt("result").toString()))
						{
							
							
								Message msg1 = new Message();
								msg1.what = 5;
								msg1.obj = "分拣完成";
								handler.sendMessage(msg1);
							
							
						} else {
							
							
							
							
						}
					}

				} catch (Exception e) {
					System.out.print(e.getMessage());
					Message msg = new Message();
					msg.what = 2;
					msg.obj = "网络异常,请检查网络连接";
					handler.sendMessage(msg);
				}
			}
		});
		mThread.start();

	}



	/**
	 * 利用正则表达式判断字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	


	private void sumbit() {

		tvmsg.setText("");
		
		
		
		
		if (etTotalWeight.getText().toString().trim().equals("")) {
			Toaster.toaster("请输入总重量!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("请输入总重量");
			return;
		}
		if (!isNumeric(etTotalWeight.getText().toString().trim())) {
			Toaster.toaster("请输入数字!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("请输入数字");
			return;
		}
	  BigDecimal	sortingNum = new BigDecimal(etTotalWeight.getText().toString().trim());
		int i = sortingNum.compareTo(BigDecimal.ZERO);
		if (i == 0 || i == -1) {
			Toaster.toaster("数量必须大于0!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("数量必须大于0");
			return;
		}
		
		
	
	
		if (tvDate.getText().toString().trim().equals("")) {
			
			Toaster.toaster("请选择生产日期!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("请选择生产日期!");
			return ;
		}
		
		
		btnSure.setText("提交中...");
		btnSure.setEnabled(false);
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					 HttpClient client = com.wologic.util.SimpleClient
					 .getHttpClient();
					String searchUrl = Constant.url
							+ "/standPackTask/partnerSumbit";
					StandardSortingRequest request = new StandardSortingRequest();
					// request.setId(id);
					
					 request.setSkuCode(skuCode);
					 request.setBarCode(skuCode);
			
					 request.setWarehouseCode(Common.WareHouseCode);
					 request.setUpdateUser(Common.UserName);
					// request.setContainerCode(containerCode);
					 request.setCustomerName(Common.CustomerName);
					 request.setCustomerCode(Common.CustomerCode);
					 
					String json2 = JSON.toJSONString(request);
					String resultSearch2 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json2);
					JSONObject jsonSearch2 = new JSONObject(resultSearch2);
					if (jsonSearch2.optString("code").toString().equals("200")) {
						Message msg = new Message();
						msg.what =8;
						msg.obj = jsonSearch2.optString("result").toString();
						handler.sendMessage(msg);
						
						
						
						
					} else {
						Message msg = new Message();
						msg.what = 2;
						msg.obj = jsonSearch2.optString("message").toString();
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					System.out.print(e.getMessage());
					Message msg = new Message();
					msg.what = 2;
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
				btnSure.setEnabled(true);
				btnSure.setText("确定");
				break;
			case 2:
				btnSure.setEnabled(true);
				btnSure.setText("确定");
			
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				Toaster.toaster(msg.obj.toString());
				
				break;
			case 3:
				mediaPlayerOk.setVolume(1.0f, 1.0f);
				mediaPlayerOk.start();
				
				// 提交成功
				Toaster.toaster(msg.obj.toString());
				finish();
				break;
			
			case 7:
			
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				Toaster.toaster(msg.obj.toString());
				break;
			case 8:
				btnSure.setEnabled(true);
				btnSure.setText("确定");
				
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText("提交成功");
				Toaster.toaster("提交成功");
				
				break;

			default:
				
				break;
			}
		}
	};

	

	
	
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
		if (mediaPlayerOk != null) {
			mediaPlayerOk.stop();
			mediaPlayerOk.release();
		}
	};

}
