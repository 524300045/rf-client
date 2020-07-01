package com.wologic.ui;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
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
import com.wologic.domainnew.GoodsSuit;
import com.wologic.domainnew.GoodsSuitBoxTransferDetail;
import com.wologic.request.ContainerSkuRelRequest;
import com.wologic.request.GoodsBarcodeRequest;
import com.wologic.request.GoodsSuitBoxTransferRequest;
import com.wologic.request.GoodsSuitRequest;
import com.wologic.request.StandSkuTaskRequest;
import com.wologic.request.StandardSortingRequest;
import com.wologic.response.StandPickTaskResponse;
import com.wologic.ui.GoodsSuitActivity.SpecialAdapter;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class GoodsSuitCaiJiActivity extends Activity {

	private TextView tbBack;
	private EditText  etTotalWeight;
	private Button btnSure,btnDate;
	private TextView tvmsg,  tvSkuCode, tvGoodsName,tvDate,tvUnit;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerOk;
	

	private String skuCode;
	

	 
	private ListView lv;
	
	List<GoodsSuit> goodsSuitlist;
	
	private String productDate="";
	
	int year = 2016;
    int month = 10;
    int day = 8;

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
		btnDate=(Button) findViewById(R.id.btnDate);
		btnSure = (Button) findViewById(R.id.btnSure);
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				sumbit();
			}
		});
		
		btnDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			
				new DatePickerDialog(GoodsSuitCaiJiActivity.this, new DatePickerDialog.OnDateSetListener() {

		            @Override
		            public void onDateSet(DatePicker view, int year, int monthOfYear,
		                    int dayOfMonth) {
		            	GoodsSuitCaiJiActivity.this.year = year;
		                month = monthOfYear+1;
		                day = dayOfMonth;
		                tvDate.setText(year+"-"+month+"-"+day);
		                
		                Calendar calendar = Calendar.getInstance();
		                calendar.set(year, monthOfYear, day);
		                SimpleDateFormat format = new SimpleDateFormat(
		                        "yyyy-MM-dd");
		                productDate=format.format(calendar.getTime());
		            }
		        }, year, month-1, day).show();
				
			}
			
			
		});
		
		
     	load(skuCode);
		
	}
	
	public void setListViewHeightBasedOnChildren(ListView listView) {
		 
		  ListAdapter listAdapter = listView.getAdapter();
		 
		  if (listAdapter == null) {
		   return;
		  }
		 
		  int totalHeight = 150;
		 
		  for (int i = 0; i < listAdapter.getCount()-1; i++) {
		   View listItem = listAdapter.getView(i, null, listView);
		   listItem.measure(0, 0);
		   totalHeight += listItem.getMeasuredHeight();
		  }
		 
		  ViewGroup.LayoutParams params = listView.getLayoutParams();
		 
		  params.height = totalHeight
		    + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		 
		 
		 
		  listView.setLayoutParams(params);
		 }
	
//	 public void saveEditData(int position, String str) {
//	        Toast.makeText(this,str+"----"+position,Toast.LENGTH_LONG).show();
//	    }

	private void load(final String skuCode) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();
					String searchUrl = Constant.url
							+ "/goodsSuit/getGoodsSuitList";
					GoodsSuitRequest request = new GoodsSuitRequest();
					request.setSkuCode(skuCode);
		
					
					String json2 = JSON.toJSONString(request);
					String resultSearch2 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json2);
					JSONObject jsonSearch2 = new JSONObject(resultSearch2);
					if (jsonSearch2.optString("code").toString().equals("200"))
					{
						if (!"null".equals(jsonSearch2.opt("result").toString()))
						{
							

							goodsSuitlist=JSON.parseArray(
									jsonSearch2.optString("result"),
									GoodsSuit.class);
							
							
								Message msg1 = new Message();
								msg1.what =1;
								msg1.obj = "";
								handler.sendMessage(msg1);
							
							
						} else {
							
							Message msg = new Message();
							msg.what = 2;
							msg.obj = "没有获取到套装明细";
							handler.sendMessage(msg);
							
							
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
	
	List<Map<String, Object>> mapList=null;
	
	private void bindList() {

		 mapList = new ArrayList<Map<String, Object>>();
		if (null != goodsSuitlist) {
			for (GoodsSuit goodsSuit : goodsSuitlist) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("skucode", goodsSuit.getChildSkuCode());
				map.put("goodsname", goodsSuit.getChildGoodsName());
				map.put("weight","");
				mapList.add(map);
			}
		}

		lv.setAdapter(new ListSuitAdapter(this,mapList));
		 
		setListViewHeightBasedOnChildren(lv);

	}


	private void sumbit() {

		tvmsg.setText("");
		System.out.print(mapList.size());
		if(productDate=="")
		{
			Toaster.toaster("请选择生产日期!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("请选择生产日期");
			return;
		}
		
		if (etTotalWeight.getText().toString().trim().equals("")) {
			Toaster.toaster("请输入总重量!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("请输入总重量");
			return;
		}
		if (!isNumeric(etTotalWeight.getText().toString().trim())) {
			Toaster.toaster("总重量只能输入数字!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("总重量只能输入数字");
			return;
		}
		
	    final  BigDecimal	totalWeight = new BigDecimal(etTotalWeight.getText().toString().trim());
		int i = totalWeight.compareTo(BigDecimal.ZERO);
		if (i == 0 || i == -1) {
			Toaster.toaster("总重量必须大于0!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("总重量必须大于0");
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
		
	 final	List<GoodsSuitBoxTransferDetail> detailList=new ArrayList<GoodsSuitBoxTransferDetail>();
		String msg="";
		for(Map<String, Object> map:mapList)
		{
			String childSkuCode=map.get("skucode").toString();
			String childGoodsName=map.get("goodsname").toString();
			String childweight=map.get("weight").toString();
			if(childweight==null||childweight=="")
			{
				msg=childGoodsName+"没有输入重量";
				break;
			}
			
			if (!isNumeric(childweight.trim())) {
				msg=childGoodsName+"重量只能输入数字";
				break;
			}
			  BigDecimal curWeight = new BigDecimal(childweight.trim());
			  if(curWeight.compareTo(new BigDecimal(0))<=0)
			  {
				  msg=childGoodsName+"重量必须大于0";
					break;
			  }
			  
			  GoodsSuitBoxTransferDetail goodsSuitBoxTransferDetail=new GoodsSuitBoxTransferDetail();
			  goodsSuitBoxTransferDetail.setWarehouseCode(Common.WareHouseCode);
			  goodsSuitBoxTransferDetail.setWarehouseName(Common.WareHouseName);
			  goodsSuitBoxTransferDetail.setCustomerCode(Common.CustomerCode);
			  goodsSuitBoxTransferDetail.setCustomerName(Common.CustomerName);
			  goodsSuitBoxTransferDetail.setStatus(0);
			  goodsSuitBoxTransferDetail.setSource(0);
			  goodsSuitBoxTransferDetail.setProductionDate(productDate);
			  goodsSuitBoxTransferDetail.setSkuCode(skuCode);
			  goodsSuitBoxTransferDetail.setChildSkuCode(childSkuCode);
			  goodsSuitBoxTransferDetail.setCreateUser(Common.UserName);
			  goodsSuitBoxTransferDetail.setWeight(curWeight);
			  detailList.add(goodsSuitBoxTransferDetail);
		}
		
		if(msg!="")
		{
			Toaster.toaster(msg);
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText(msg);
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
							+ "/goodsSuitBoxTransfer/add";
					GoodsSuitBoxTransferRequest request = new GoodsSuitBoxTransferRequest();
					 request.setSkuCode(skuCode);
					 request.setWarehouseCode(Common.WareHouseCode);
					 request.setWarehouseName(Common.WareHouseName);
					 request.setCreateUser(Common.UserName);
					 request.setCustomerName(Common.CustomerName);
					 request.setCustomerCode(Common.CustomerCode);
					 request.setSkuCode(skuCode);
					 request.setProductionDate(productDate+" 00:00:00");
					 request.setWeight(totalWeight);
					 request.setDetail(detailList);
					 
					String json2 = JSON.toJSONString(request);
					String resultSearch2 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json2);
					JSONObject jsonSearch2 = new JSONObject(resultSearch2);
					if (jsonSearch2.optString("code").toString().equals("200")) {
						Message msg = new Message();
						msg.what =3;
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
				if(goodsSuitlist!=null&&goodsSuitlist.size()>0)
				{
					tvSkuCode.setText(goodsSuitlist.get(0).getSkuCode());
					tvGoodsName.setText(goodsSuitlist.get(0).getGoodsName());
					tvUnit.setText(goodsSuitlist.get(0).getGoodsUnit());
				}
				bindList();
				break;
			case 2:
			    //提交失败
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				Toaster.toaster(msg.obj.toString());
				btnSure.setEnabled(true);
				btnSure.setText("确定");
				break;
			case 3:
				mediaPlayerOk.setVolume(1.0f, 1.0f);
				mediaPlayerOk.start();
				// 提交成功
				Toaster.toaster(msg.obj.toString());
				finish();
				break;
			
			case 4:
			
				btnSure.setEnabled(true);
				btnSure.setText("确定");
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				Toaster.toaster(msg.obj.toString());
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
