package com.wologic.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.control.CustomSureDialog;
import com.wologic.domainnew.OutBound;
import com.wologic.domainnew.PmsOrderPurchaseDetail;
import com.wologic.request.OutBoundRequest;
import com.wologic.request.PmsOrderPurchaseDetailRequest;
import com.wologic.request.PmsOrderPurchaseRequest;
import com.wologic.ui.ContentAdapter.Callback;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class Purchase_Accept_Scan_Activity extends Activity implements OnItemClickListener,Callback  {

	private TextView tbBack,tvmsg;
	private EditText etSku;
	private ListView lvgoods;
	private MediaPlayer mediaPlayer;

	private List<PmsOrderPurchaseDetail> detailList;

	List<Map<String, Object>> mapnoendList;
	private String orderNo;
	
	 CustomSureDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase_accept_scan);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		Intent intent = getIntent();
		if (intent != null) {
			orderNo = intent.getStringExtra("orderNo");
		}

		mediaPlayer = MediaPlayer.create(Purchase_Accept_Scan_Activity.this,
				R.raw.error);

		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etSku = (EditText) findViewById(R.id.etSku);
		lvgoods = (ListView) findViewById(R.id.lvgoods);
		initEvent();
		etSku.requestFocus();
		getGoods("");
	}

	private void bindList() {
		 mapnoendList = new ArrayList<Map<String, Object>>();
		if (null != detailList) {
			for (PmsOrderPurchaseDetail item : detailList) {
				if(item.getOrderState()==30||item.getOrderState()==40)
				{
					continue;
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("skucode",  item.getSkuCode());
				map.put("goodsName", item.getSkuCode()+" "+item.getGoodsName());
				map.put("planNum",item.getPlanNum());
				if(null!=item.getRealityNum())
				{
					map.put("realNum", item.getRealityNum());
				}
				else
				{
					map.put("realNum", 0);
				}
				
		
				if(null!=item.getRealityNum())
				{
					map.put("remainNum", item.getPlanNum().subtract(item.getRealityNum()));
				}
				else
				{
					map.put("remainNum", item.getPlanNum());
				}
				map.put("model", "规格:"+item.getGoodsModel()+"     单位:"+item.getGoodsUnit());
				map.put("id", item.getId());
				map.put("status", item.getOrderState());
				
				mapnoendList.add(map);
			}
		}
		
		lvgoods.setAdapter(new ContentAdapter(this, mapnoendList,this));
		lvgoods.setOnItemClickListener(this);
		
	/*	SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_purchasedetail, new String[] {"skucode", "goodsName", "planNum","realNum","remainNum" },
				new int[] {R.id.tvSkuCode, R.id.tvName, R.id.tvPlanNum,R.id.tvRealNum,R.id.tvRemain});
		lvgoods.setAdapter(adp);*/
	}

	private void initEvent() {
		etSku.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						tvmsg.setText("");
						tvmsg.setVisibility(View.GONE);
						String skuname = etSku.getText().toString()
								.trim();
						etSku.setEnabled(false);
						getGoods(skuname);
						break;
					case KeyEvent.ACTION_DOWN:
						break;
					}
					return true;
				}
				return false;
			}
		});

		

	}

	private void getGoods(final String skuCode) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/pmsOrderPurchase/queryPurchaseDetail";
					PmsOrderPurchaseDetailRequest request = new PmsOrderPurchaseDetailRequest();;
					request.setOrderNo(orderNo);
					request.setSkuCode(skuCode);
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
					
							detailList = JSON
									.parseArray(
											jsonSearch
													.opt("result")
													.toString(),
													PmsOrderPurchaseDetail.class);
							Message msg = new Message();
							msg.what = 4;
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
			case 2:
				etSku.setEnabled(true);
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				Toaster.toaster(msg.obj.toString());
				etSku.selectAll();
				etSku.requestFocus();
				break;
			case 3:
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				Toaster.toaster(msg.obj.toString());
				dialog.dismiss();
				break;
			case 4:
				etSku.setEnabled(true);
				bindList();
				//etSku.selectAll();
				//etSku.requestFocus();
				break;
			case 5:
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				Toaster.toaster(msg.obj.toString());
				
				break;
			default:
				etSku.setEnabled(true);
				break;
			}
		}
	};

	// 接受页面的返回值
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		etSku.selectAll();
		etSku.requestFocus();
		//if (requestCode == 1) {	
			getGoods(etSku.getText().toString());
		//}
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
	}

	@Override
	public void click(View v) {
		
		
           if(v.getId()==R.id.btnDetail)
           {
        	
        	   boolean isFinish=false;
        	   
        	   for(PmsOrderPurchaseDetail item:detailList)
        	   {
        		   if(item.getId().toString().equals(mapnoendList.get((Integer) v.getTag()).get(
          				"id").toString()))
        		   {
        			   if(item.getOrderState()>20)
        			   {
        				   isFinish=true;
        			   }
        		   }
        	   }
        	  
        	   if(isFinish)
        	   {
        		   //收货完成
        		   Intent intent = new Intent(Purchase_Accept_Scan_Activity.this,
            			   PurchaseAcceptDetailActivity.class);
            	   intent.putExtra("detailId", mapnoendList.get((Integer) v.getTag()).get(
              				"id").toString());
           		   startActivityForResult(intent, 1);
        	   }
        	   else
        	   {
        		    //收货未完成，可以修改明细
        		   Intent intent = new Intent(Purchase_Accept_Scan_Activity.this,
        				   PurchaseAcceptNotEndDetailActivity.class);
            	   intent.putExtra("detailId", mapnoendList.get((Integer) v.getTag()).get(
              				"id").toString());
           		   startActivityForResult(intent, 1);
        	   }
        	  
           }
           
           if(v.getId()==R.id.btnSure)
           {
        	   Long id=Long.valueOf(mapnoendList.get((Integer) v.getTag()).get(
              				"id").toString());
        	   dialog("确定要收货完成吗?",id);
           }
           
           if(v.getId()==R.id.btnReceive)
           {
        	   boolean isFinish=false;
        	   for(PmsOrderPurchaseDetail item:detailList)
        	   {
        		   if(item.getId().toString().equals(mapnoendList.get((Integer) v.getTag()).get(
          				"id").toString()))
        		   {
        			   if(item.getOrderState()>20)
        			   {
        				   isFinish=true;
        			   }
        		   }
        	   }
        	  
        	   if(isFinish)
        	   {
        		  return; 
        	   }
        	   
        	   String skuCode1 = mapnoendList.get((Integer) v.getTag()).get("skucode")
				.toString();
		
		String name1 = mapnoendList.get((Integer) v.getTag()).get("goodsName")
				.toString();
	
		String realNum1 =  mapnoendList.get((Integer) v.getTag()).get(
				"realNum").toString();
		String remainNum1 =mapnoendList.get((Integer) v.getTag()).get(
				"remainNum").toString();
		
		Intent intent = new Intent(Purchase_Accept_Scan_Activity.this,
				PurchaseAcceptEndActivity.class);
		intent.putExtra("orderNo",orderNo);// 传递入库单号
		intent.putExtra("skuCode",skuCode1);
		intent.putExtra("name", name1);
		intent.putExtra("realNum", realNum1);
		intent.putExtra("remainNum", remainNum1);
		intent.putExtra("detailId", mapnoendList.get((Integer) v.getTag()).get(
				"id").toString());
		startActivityForResult(intent, 1);
		
           }
		
		
	}

	private void dialog(final String title,final Long detailId) {
		 dialog = new CustomSureDialog(Purchase_Accept_Scan_Activity.this);
		TextView textview = (TextView) dialog.findViewById(R.id.title);
		textview.setVisibility(View.VISIBLE);
		textview.setText(title);
		dialog.setTitle(textview);

		dialog.setOnPositiveListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Toast.makeText(Purchase_Accept_Scan_Activity.this, "确定", Toast.LENGTH_SHORT)
						.show();*/
				//
				inboundFinish(detailId);
				//dialog.dismiss();
				

			}
		});
		dialog.setOnNegativeListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Toast.makeText(Purchase_Accept_Scan_Activity.this, "取消", Toast.LENGTH_SHORT)
						.show();*/
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	
	
	private void inboundFinish(final long id)
	{
		
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/pmsOrderPurchase/inbound";

					PmsOrderPurchaseDetailRequest request = new PmsOrderPurchaseDetailRequest();
					request.setId(id);
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200")) {
						List<OutBound> outBoundList = JSON.parseArray(
								jsonSearch.optString("result"), OutBound.class);
						Message msg = new Message();
						msg.what = 3;
						msg.obj = "成功";
						handler.sendMessage(msg);
						
						getGoods(etSku.getText().toString().trim());
					} else {
						Message msg = new Message();
						msg.what =5;
						msg.obj = jsonSearch.optString("message");
						handler.sendMessage(msg);
					}

				} catch (Exception e) {
					System.out.print(e.getMessage());
					Message msg = new Message();
					msg.what = 2;
					msg.obj = "网络异常"+e.getMessage();
					handler.sendMessage(msg);
				}
			}
		});
		mThread.start();
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		TextView tvSkuCode= (TextView) arg1.findViewById(R.id.tvSkuCode);
		TextView tvRealNum= (TextView) arg1.findViewById(R.id.tvRealNum);
		TextView tvRemainNum= (TextView) arg1.findViewById(R.id.tvRemain);
		TextView tvName= (TextView) arg1.findViewById(R.id.tvName);
		
		Intent intent = new Intent(Purchase_Accept_Scan_Activity.this,
				PurchaseAcceptEndActivity.class);
		intent.putExtra("orderNo",orderNo);// 传递入库单号
		intent.putExtra("skuCode", tvSkuCode.getText());
		intent.putExtra("realNum", tvRealNum.getText());
		intent.putExtra("remainNum", tvRemainNum.getText());
		intent.putExtra("name", tvName.getText());
		
		startActivityForResult(intent, 1);

	}


}
