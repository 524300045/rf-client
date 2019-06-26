package com.wologic.ui;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domainnew.GoodsBarCode;
import com.wologic.domainnew.PackTaskDetail;
import com.wologic.domainnew.PackageAllDetail;
import com.wologic.domainnew.PreprocessInfo;
import com.wologic.domainnew.WarehouseAreaPickProcess;
import com.wologic.request.GoodsQueryRequest;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.request.WmsInventoryQuery;
import com.wologic.response.WmsInventoryTaskResponse;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class InventoryOneActivity extends Activity {

	private TextView tbBack;

	private ListView lvgoods;
	private MediaPlayer mediaPlayer;
	private List<WmsInventoryTaskResponse> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory_one);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		mediaPlayer = MediaPlayer.create(InventoryOneActivity.this,
				R.raw.error);
		
		lvgoods = (ListView) findViewById(R.id.lvgoods);
		initEvent();
		getInventoryList();

	}

	private void bindList() {
		List<Map<String, Object>> mapnoendList = new ArrayList<Map<String, Object>>();
		if (null != list) {
			for (WmsInventoryTaskResponse item : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("wmsInventoryOrderNo",  item.getWmsInventoryOrderNo());
				map.put("areaName", item.getAreaName());
				map.put("process", item.getFinishNum()+"/"+item.getTotalNum());
				//map.put("finishNum", item.getFinishNum());
				map.put("areaCode", item.getAreaCode());
				mapnoendList.add(map);
			}
			
		}
		
		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_inventory, new String[] {"wmsInventoryOrderNo", "areaName", "process","areaCode" },
				new int[] {R.id.tvOrderNo, R.id.tvAreaName, R.id.tvProcess, R.id.tvAreaCode});
		lvgoods.setAdapter(adp);
	}

	private void initEvent() {
	
		lvgoods.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView tvOrderNo = (TextView) arg1.findViewById(R.id.tvOrderNo);
				TextView tvAreaName = (TextView) arg1
						.findViewById(R.id.tvAreaName);
				
				TextView tvAreaCode = (TextView) arg1
						.findViewById(R.id.tvAreaCode);
				
				Intent intent = new Intent(InventoryOneActivity.this,
						InventoryThreeActivity.class);
				intent.putExtra("orderNo", tvOrderNo.getText());// 传递单号
				intent.putExtra("areaName", tvAreaName.getText());// 传递区域
				intent.putExtra("areaCode", tvAreaCode.getText());
				
				startActivityForResult(intent, 1);

			}
		});

	}

	private void getInventoryList() {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/wmsInventory/getInventoryList";
					WmsInventoryQuery  request=new WmsInventoryQuery();
					request.setWarehouseCode(Common.WareHouseCode);
					request.setCustomerCode(Common.CustomerCode);
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						list = JSON
									.parseArray(
											jsonSearch
													.opt("result")
													.toString(),
													WmsInventoryTaskResponse.class);
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
				getInventoryList();
		}
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
