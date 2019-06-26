package com.wologic.ui;

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

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domainnew.WarehouseArea;
import com.wologic.request.WarehouseAreaRequest;
import com.wologic.response.WarehouseAreaResponse;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class FreeInventoryAreaListActivity extends Activity {

	private TextView tbBack,tvmsg;
	private ListView lvArea;
	private MediaPlayer mediaPlayer;
	private EditText etAreaName;
	
	private List<WarehouseAreaResponse> areaList;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_free_inventory_area);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		mediaPlayer = MediaPlayer.create(FreeInventoryAreaListActivity.this,
				R.raw.error);
		
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		lvArea = (ListView) findViewById(R.id.lvArea);
		etAreaName = (EditText) findViewById(R.id.etAreaName);
	
		lvArea.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView tvAreaCode = (TextView) arg1.findViewById(R.id.tvAreaCode);
				TextView tvAreaName = (TextView) arg1.findViewById(R.id.tvAreaName);
				
				Intent intent = new Intent(FreeInventoryAreaListActivity.this,
						FreeInventoryGoodsActivity.class);
				intent.putExtra("areaCode", tvAreaCode.getText());
				intent.putExtra("areaName", tvAreaName.getText());
				startActivityForResult(intent, 1);

			}
		});
		
		etAreaName.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						tvmsg.setText("");
						tvmsg.setVisibility(View.GONE);
						String areaname = etAreaName.getText().toString()
								.trim();
						if (areaname.equals("")) {
							etAreaName.selectAll();
							Toaster.toaster("请输入库区名称!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("请输入商品名称!");
							return true;
						}
						etAreaName.setEnabled(false);
						getAreaList(areaname);
						break;
					case KeyEvent.ACTION_DOWN:
						break;
					}
					return true;
				}
				return false;
			}
		});
	
		getAreaList("");
	}
	
	
	

	private void bindList() {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (null != areaList) {
			for (WarehouseAreaResponse item : areaList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("areaCode",  item.getAreaCode());
				map.put("areaName", item.getAreaName());
				mapList.add(map);
			}
		}
		
		SpecialAdapter adp = new SpecialAdapter(this, mapList,
				R.layout.listitem_free_inventory_area, new String[] {"areaCode", "areaName" },
				new int[] {R.id.tvAreaCode, R.id.tvAreaName});
		lvArea.setAdapter(adp);
	}

	private void getAreaList(final String areaName) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();
					String searchUrl = Constant.url
							+ "/warehouseArea/getWarehouseArea";
					WarehouseAreaRequest  request=new WarehouseAreaRequest();
					request.setWarehouseCode(Common.WareHouseCode);
					request.setAreaName(areaName);
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						if (null == jsonSearch.optString("result")||jsonSearch.optString("result").toString().equals("null")
								) 
						{
							Message msg = new Message();
							msg.what = 2;
							msg.obj = "没有库区";
							handler.sendMessage(msg);
						} 
						else
						{
							areaList = JSON
									.parseArray(
											jsonSearch
													.opt("result")
													.toString(),
													WarehouseAreaResponse.class);
							Message msg = new Message();
							msg.what = 4;
							msg.obj = "";
							handler.sendMessage(msg);
						}
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
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
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
