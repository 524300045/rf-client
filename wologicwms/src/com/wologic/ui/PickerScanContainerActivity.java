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
import com.wologic.domainnew.Container;
import com.wologic.domainnew.GoodsBarCode;
import com.wologic.domainnew.PackTaskDetail;
import com.wologic.domainnew.PackageAllDetail;
import com.wologic.domainnew.PreprocessInfo;
import com.wologic.domainnew.WarehouseAreaPickProcess;
import com.wologic.request.ContainerQuery;
import com.wologic.request.GoodsQueryRequest;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.request.StandardPickingTaskRequest;
import com.wologic.response.AreaPickerInfoResponse;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class PickerScanContainerActivity extends Activity {

	private TextView tbBack,tvmsg;
	private EditText etContainer;
	private ListView lvgoods;
	private MediaPlayer mediaPlayer;

	private List<AreaPickerInfoResponse> areaPickerInfoList;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picker_scan_container);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		mediaPlayer = MediaPlayer.create(PickerScanContainerActivity.this,
				R.raw.error);
		
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etContainer = (EditText) findViewById(R.id.etContainer);
		lvgoods = (ListView) findViewById(R.id.lvgoods);
		initEvent();
		etContainer.requestFocus();
		getPickingInfo();

	}

	private void bindList() {
		List<Map<String, Object>> mapnoendList = new ArrayList<Map<String, Object>>();
		if (null != areaPickerInfoList) {
			for (AreaPickerInfoResponse item : areaPickerInfoList) {
				if(item.getFinishCount()==item.getTotalCount())
				{
					continue;
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("areaCode",  item.getAreaCode());
				map.put("areaName",  item.getAreaName());
				map.put("process", item.getFinishCount()+"/"+item.getTotalCount());
				mapnoendList.add(map);
			}
		}
		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_areapicking, new String[] {"areaCode", "areaName", "process" },
				new int[] {R.id.tvAreaCode, R.id.tbAreaName, R.id.tvProcess});
		lvgoods.setAdapter(adp);
	}

	private void initEvent() {
		etContainer.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						tvmsg.setText("");
						tvmsg.setVisibility(View.GONE);
						String skuname = etContainer.getText().toString()
								.trim();
						if (skuname.equals("")) {
							etContainer.selectAll();
							Toaster.toaster("«Î…®√ËÕ–≈Ã!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("«Î…®√ËÕ–≈Ã!");

							return true;
						}
						etContainer.setEnabled(false);
						getContainerInfo(skuname);
						break;
					case KeyEvent.ACTION_DOWN:
						break;
					}
					return true;
				}
				return false;
			}
		});

		lvgoods.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
                 if(etContainer.getText().toString().trim().equals(""))
                 {
                		tvmsg.setText("«Î…®√ËÕ–≈Ã");
        				tvmsg.setVisibility(View.VISIBLE);
        				mediaPlayer.setVolume(1.0f, 1.0f);
        				mediaPlayer.start();
        				Toaster.toaster("«Î…®√ËÕ–≈Ã");
        				etContainer.selectAll();
        				etContainer.requestFocus();
        				return;
                 }
				
				TextView tvAreaCode = (TextView) arg1.findViewById(R.id.tvAreaCode);
				TextView tvAreaName = (TextView) arg1.findViewById(R.id.tbAreaName);
				
				Intent intent = new Intent(PickerScanContainerActivity.this,
						PickerListActivity.class);
				intent.putExtra("areaCode", tvAreaCode.getText());// ¥´µ›»Îø‚µ•∫≈
				intent.putExtra("areaName", tvAreaName.getText());// ¥´µ›»Îø‚µ•∫≈
				intent.putExtra("container", etContainer.getText().toString());// ¥´µ›»Îø‚µ•∫≈
				startActivityForResult(intent, 1);

			}
		});

	}

	private void getPickingInfo() {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/standardPickingTask/getAreaPickingInfo";
					StandardPickingTaskRequest  request=new StandardPickingTaskRequest();
					request.setWarehouseCode(Common.WareHouseCode);
					
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						
							areaPickerInfoList = JSON
									.parseArray(
											jsonSearch
													.opt("result")
													.toString(),
													AreaPickerInfoResponse.class);
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
					msg.obj = "Õ¯¬Á“Ï≥£,«ÎºÏ≤ÈÕ¯¬Á¡¨Ω”";
					handler.sendMessage(msg);

				}
			}
		});
		mThread.start();
	}
	
	
	private void getContainerInfo(final String container) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/container/getContainerByBarCode";
					ContainerQuery  request=new ContainerQuery();
					request.setWarehouseCode(Common.WareHouseCode);
					request.setBarCode(container);
					
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						    Container container = JSON.parseObject(jsonSearch.optString("result"),Container.class);
							Message msg = new Message();
							msg.what = 5;
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
					msg.obj = "Õ¯¬Á“Ï≥£,«ÎºÏ≤ÈÕ¯¬Á¡¨Ω”";
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
				etContainer.setEnabled(true);
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				Toaster.toaster(msg.obj.toString());
				etContainer.selectAll();
				etContainer.requestFocus();
				break;
			case 4:
				etContainer.setEnabled(true);
				
				bindList();
				etContainer.selectAll();
				etContainer.requestFocus();
				break;
			case 5:
				etContainer.setEnabled(true);
				etContainer.selectAll();
				etContainer.requestFocus();
				break;
			default:
				etContainer.setEnabled(true);
				break;
			}
		}
	};

	// Ω” ‹“≥√Êµƒ∑µªÿ÷µ
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		etContainer.selectAll();
		etContainer.requestFocus();
		if (requestCode == 1) {
			
				getPickingInfo();
			

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
