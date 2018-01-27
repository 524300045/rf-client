package com.wologic.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domainnew.OutBound;
import com.wologic.domainnew.OutBoundDetail;
import com.wologic.domainnew.PackTaskDetail;
import com.wologic.domainnew.PackageAllDetail;
import com.wologic.domainnew.PreprocessInfo;
import com.wologic.domainnew.StandardPackTaskDetail;
import com.wologic.request.OutBoundProcessQuery;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.request.StandardPackTaskDetailRequest;
import com.wologic.response.OutBoundResponse;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class StoreSortProcessActivity extends Activity {

	private TextView tbBack,tvName;

	private ListView lvprocess;

	List<StandardPackTaskDetail> detailList;
	
	String storeCode,storeName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_storesortprocess);
		tvName=(TextView) findViewById(R.id.tvStoreName);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		lvprocess = (ListView) findViewById(R.id.lvprocess);
		Intent intent = getIntent();
		if (intent != null) {
			storeCode = intent.getStringExtra("storedCode");
			storeName = intent.getStringExtra("storedName");
		}
		tvName.setText(storeName);
		getDetail("");
	}
	
	private void getDetail(final String outCode)
	{
		
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/standPackTask/getStoreSortingProcess";

					StandardPackTaskDetailRequest request=new StandardPackTaskDetailRequest();
					request.setCustomerCode(Common.CustomerCode);
					request.setPartnerCode(Common.partnerCode);
					request.setWarehouseCode(Common.WareHouseCode);
					request.setStoredCode(storeCode);
					
					String json = JSON.toJSONString(request);
					
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200")) {
						detailList = JSON.parseArray(
								jsonSearch.optString("result"),
								StandardPackTaskDetail.class);
						Message msg = new Message();
						msg.what = 4;
						msg.obj = "";
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					System.out.print(e.getMessage());
					Message msg = new Message();
					msg.what = 3;
					msg.obj = "Õ¯¬Á“Ï≥£,«ÎºÏ≤ÈÕ¯¬Á¡¨Ω”";
					handler.sendMessage(msg);

				}
			}
		});
		mThread.start();
	}

	private void bindList() {

		List<Map<String, Object>> mapnoendList = new ArrayList<Map<String, Object>>();
		if (null != detailList) {
			for (StandardPackTaskDetail item : detailList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("skuCode", item.getSkuCode());
				map.put("skuName", item.getGoodsName());
					map.put("process",
							item.getSortingNum() + "/" + item.getPlanNum());
				
				mapnoendList.add(map);
			}
		}

		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_storesortprocess, new String[] { "skuCode",
						"skuName", "process" }, new int[] {
						R.id.tvSkuCode, R.id.tvSkuName,
					 R.id.tvProcess });
		lvprocess.setAdapter(adp);

	}

	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:

				break;
			case 4:

				bindList();
				break;
			default:

				break;
			}
		}
	};

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

}
