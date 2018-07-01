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
import com.wologic.domainnew.StandardPickingTask;
import com.wologic.domainnew.WarehouseAreaPickProcess;
import com.wologic.request.GoodsQueryRequest;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.request.StandardPickingTaskRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class PickerListActivity extends Activity {

	private TextView tbBack,tvmsg;
	private EditText etSku;
	private ListView lvgoods;
	private MediaPlayer mediaPlayer;

	private TextView  tvContainer,tvAreaName;
	
	private List<StandardPickingTask> taskList;

	private String areaCode,areaName,container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picker_list);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		mediaPlayer = MediaPlayer.create(PickerListActivity.this,
				R.raw.error);
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etSku = (EditText) findViewById(R.id.etSku);
		tvContainer= (TextView) findViewById(R.id.tvContainer);
		tvAreaName= (TextView) findViewById(R.id.tvAreaName);
		
		lvgoods = (ListView) findViewById(R.id.lvgoods);
		Intent intent = getIntent();
		if (intent != null) {
			areaCode = intent.getStringExtra("areaCode");
			areaName= intent.getStringExtra("areaName");
			container= intent.getStringExtra("container");
		}
		tvContainer.setText(container);
		tvAreaName.setText(areaName);
		initEvent();
		etSku.requestFocus();
		getTaskList("");
	}

	private void bindList() {
		List<Map<String, Object>> mapnoendList = new ArrayList<Map<String, Object>>();
		if (null != taskList) {
			
			List<StandardPickingTask> notEndFinishList=new ArrayList<StandardPickingTask>();
			List<StandardPickingTask> finishList=new ArrayList<StandardPickingTask>();
			
			for (StandardPickingTask item : taskList)
			{
				if(item.getPlanNum().compareTo(item.getRealityNum())==0)
				{
					finishList.add(item);
				}
				else
				{
					notEndFinishList.add(item);
				}
			}
			
			for (StandardPickingTask item : notEndFinishList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", item.getId());
				map.put("skucode",  item.getSkuCode());
				map.put("goodsName", item.getGoodsName()+"("+item.getModelNum()+")");
				map.put("planNum", item.getPlanNum());
				map.put("unit", item.getGoodsUnit());
				map.put("realNum", item.getRealityNum());
				mapnoendList.add(map);
			}
			
			for (StandardPickingTask item : finishList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", item.getId());
				map.put("skucode",  item.getSkuCode());
				map.put("goodsName", item.getGoodsName()+"("+item.getModelNum()+")");
				map.put("planNum", item.getPlanNum());
				map.put("unit", item.getGoodsUnit());
				map.put("realNum", item.getRealityNum());
				mapnoendList.add(map);
			}
			
			
			
		}
		
		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_pickerlist, new String[] {"id","skucode", "goodsName", "planNum","unit","realNum" },
				new int[] {R.id.tvId, R.id.tvSkuCode, R.id.tvname,R.id.tvPlanNum, R.id.tvUnit,R.id.tvRealNum});
		lvgoods.setAdapter(adp);
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
						if (skuname.equals("")) {
							etSku.selectAll();
							Toaster.toaster("��������Ʒ���ƻ����!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("��������Ʒ���ƻ����!");

							return true;
						}
						etSku.setEnabled(false);
						getTaskList(skuname);
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

				TextView tvId = (TextView) arg1.findViewById(R.id.tvId);
				TextView tvskucode = (TextView) arg1.findViewById(R.id.tvSkuCode);
				
				Intent intent = new Intent(PickerListActivity.this,
						PickerEndActivity.class);
				intent.putExtra("id", tvId.getText());// ������ⵥ��
				intent.putExtra("areaName",areaName);// ������ⵥ��
				intent.putExtra("areaCode",areaCode);// ������ⵥ��
				intent.putExtra("container",container);
				startActivityForResult(intent, 1);

			}
		});

	}

	private void getTaskList(final String skuCode) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/standardPickingTask/getStandardPickingTaskList";
					StandardPickingTaskRequest  request=new StandardPickingTaskRequest();
					request.setWarehouseCode(Common.WareHouseCode);
					request.setAreaCode(areaCode);
					request.setSkuCode(skuCode);
					request.setCustomerCode(Common.CustomerCode);
					request.setStatus(0);
					
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
							taskList = JSON
									.parseArray(
											jsonSearch
													.opt("result")
													.toString(),
													StandardPickingTask.class);
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
					msg.obj = "�����쳣,������������";
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
			case 4:
				etSku.setEnabled(true);
				bindList();
				etSku.selectAll();
				etSku.requestFocus();
				break;
			default:
				etSku.setEnabled(true);
				break;
			}
		}
	};

	// ����ҳ��ķ���ֵ
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		etSku.selectAll();
		etSku.requestFocus();
		getTaskList(etSku.getText().toString());

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
