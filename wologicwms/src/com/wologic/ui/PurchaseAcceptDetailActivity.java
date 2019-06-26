package com.wologic.ui;

import java.text.SimpleDateFormat;
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
import com.wologic.domainnew.PmsOrderPurchaseDetail;
import com.wologic.domainnew.PmsOrderPurchaseReceiveDetail;
import com.wologic.request.PmsOrderPurchaseDetailRequest;
import com.wologic.request.PmsOrderPurchaseReceiveDetailRequest;
import com.wologic.request.PmsOrderPurchaseRequest;
import com.wologic.ui.ContentAdapter.Callback;
import com.wologic.ui.PurchaseAcceptActivity.SpecialAdapter;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class PurchaseAcceptDetailActivity extends Activity   {

	private TextView tbBack;

	private ListView lvgoods;
	private MediaPlayer mediaPlayer;

	private List<PmsOrderPurchaseReceiveDetail> detailList;

	List<Map<String, Object>> mapnoendList;
	 
    private Long detailId; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase_accept_detail);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		Intent intent = getIntent();
		if (intent != null) {
			detailId=Long.valueOf(intent.getStringExtra("detailId"));
		}
		mediaPlayer = MediaPlayer.create(PurchaseAcceptDetailActivity.this,
				R.raw.error);
		lvgoods = (ListView) findViewById(R.id.lvgoods);
		getGoods("");
	}

	private void bindList() {
		 mapnoendList = new ArrayList<Map<String, Object>>();
		 SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");  
		if (null != detailList) {
			for (PmsOrderPurchaseReceiveDetail item : detailList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("goodsName",item.getGoodsName());
				map.put("receiveNum",item.getReceiveNum());
				map.put("expireDate", item.getExpiryDate());
				map.put("unit", item.getGoodsUnit());
				if(null!=item.getProductionDate())
				{
					map.put("productDate",format0.format(item.getProductionDate()));
				}
				else
				{
					map.put("productDate","");
				}
				map.put("areaName", item.getAreaName());
				mapnoendList.add(map);
			}
		}
		
	
		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_purchase_accept_detail, new String[] { "goodsName",
						"receiveNum", "expireDate","productDate","areaName","unit" }, new int[] {
						R.id.tvName, R.id.tvNum,
					 R.id.tvExpire,R.id.tvProductDate, R.id.tvAreaName,R.id.tvUnit });
					 
	
		lvgoods.setAdapter(adp);
	}

	
	public class SpecialAdapter extends SimpleAdapter {
		private int[] colors = new int[] { 0xFFFFF, 0x300000FF, 0x300000FF };
		private List<? extends Map<String, ?>> list;
		private Map<String, ?> Map = new HashMap<String, Object>();

		@SuppressWarnings("unchecked")
		public SpecialAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
				String[] from, int[] to) {
			super(context, data, resource, from, to);
			this.list = data;
			// TODO Auto-generated constructor stub
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.SimpleAdapter#getView(int, android.view.View,
		 * android.view.ViewGroup)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = super.getView(position, convertView, parent);
			Iterator<? extends Map<String, ?>> it = list.iterator();
			int colorPos = 0;
			int index = 0;
			while (it.hasNext()) {
				Map = (java.util.Map<String, ?>) it.next();
				Iterator<?> iter = Map.entrySet().iterator();

				while (iter.hasNext()) {
					@SuppressWarnings("rawtypes")
					Map.Entry entry = (Map.Entry) iter.next();
					Object key = entry.getKey();
					Object val = entry.getValue();

				}

			}

			return view;
		}
	}



	private void getGoods(final String skuCode) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/pmsOrderPurchaseReceiveDetail/getPmsOrderPurchaseReceiveDetailList";
					PmsOrderPurchaseReceiveDetailRequest request = new PmsOrderPurchaseReceiveDetailRequest();;
					request.setDetailId(detailId);
				
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
													PmsOrderPurchaseReceiveDetail.class);
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
				
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				Toaster.toaster(msg.obj.toString());
				
				break;
			case 4:
				
				bindList();
			
				break;
			default:
				
				break;
			}
		}
	};

	// 接受页面的返回值
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
		if (requestCode == 1) {
			if (resultCode == Activity.RESULT_OK) {
				
			}

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
	}



}
