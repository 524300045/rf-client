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
import com.wologic.domainnew.PackTaskDetail;
import com.wologic.domainnew.PackageAllDetail;
import com.wologic.domainnew.PreprocessInfo;
import com.wologic.request.OutBoundProcessQuery;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.response.OutBoundResponse;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class PartnerPickerQueryActivity extends Activity {

	private TextView tbBack;

	private EditText etStoreCode;

	private Button btnSure;

	private TextView tvmsg;

	private ListView lvnoend;

	List<OutBoundResponse> outBoundList;

	private LinearLayout ll, llprocess;

	private MediaPlayer mediaPlayer;

	int mYear, mMonth, mDay;

	TextView dateDisplay;
	final int DATE_DIALOG = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_partnerpickerquery);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		dateDisplay = (TextView) findViewById(R.id.dateDisplay);
		btnSure = (Button) findViewById(R.id.btnSure);
		mediaPlayer = MediaPlayer.create(PartnerPickerQueryActivity.this,
				R.raw.error);
		ll = (LinearLayout) findViewById(R.id.ll);

		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etStoreCode = (EditText) findViewById(R.id.etStoreCode);
		lvnoend = (ListView) findViewById(R.id.lvnoend);
		initEvent();
		//bindList();
		final Calendar ca = Calendar.getInstance();
		ca.add(Calendar.MONTH,1);
		ca.add(Calendar.DATE,1);
		mYear = ca.get(Calendar.YEAR);
		mMonth = ca.get(Calendar.MONTH);
		mDay = ca.get(Calendar.DAY_OF_MONTH);
		
	
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				showDialog(DATE_DIALOG);
			}

		});
		etStoreCode.requestFocus();

		dateDisplay.setText(mYear+"-"+mMonth+"-"+mDay);
		/*String beginTime =mYear+"-"+mMonth+"-"+mDay+" 00:00:00";
		String endTime = mYear+"-"+mMonth+"-"+mDay+" 23:59:59";*/
		
		String beginTime =dateDisplay.getText()+" 00:00:00";
		String endTime = dateDisplay.getText()+" 23:59:59";
		
		getPackageDetail(etStoreCode.getText().toString(),beginTime,endTime);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG:
			return new DatePickerDialog(this, 4, mdateListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	/**
	 * 设置日期 利用StringBuffer追加
	 */
	public void display() {
		dateDisplay.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-")
				.append(mDay));
		
		/*String beginTime =mYear+"-"+mMonth+"-"+mDay+" 00:00:00";
		String endTime = mYear+"-"+mMonth+"-"+mDay+" 23:59:59";*/
		
		String beginTime =dateDisplay.getText()+" 00:00:00";
		String endTime = dateDisplay.getText()+" 23:59:59";
		
		getPackageDetail(etStoreCode.getText().toString(),beginTime,endTime);
		
	}

	private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			display();
		}
	};

	private void bindList() {

		List<Map<String, Object>> mapnoendList = new ArrayList<Map<String, Object>>();
		if (null != outBoundList) {
			for (OutBoundResponse item : outBoundList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("outstockcode", item.getOutboundTaskCode());
				map.put("storeCode", item.getStoredCode());
				map.put("storeName", item.getStoredName());
				map.put("process",
						item.getFinishNum() + "/" + item.getTotalNum());
				mapnoendList.add(map);
			}
		}

		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitemstorequery, new String[] { "outstockcode",
						"storeCode", "storeName", "process" }, new int[] {
						R.id.tvoutstockcode, R.id.tvStoreCode,
						R.id.tvStoreName, R.id.tvProcess });
		lvnoend.setAdapter(adp);

	}

	private void initEvent() {
		etStoreCode.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						tvmsg.setText("");
						tvmsg.setVisibility(View.GONE);
						String storeCode = etStoreCode.getText().toString()
								.trim();

						String beginTime =dateDisplay.getText().toString()+" 00:00:00";
						String endTime = dateDisplay.getText().toString()+" 23:59:59";
						getPackageDetail(storeCode, beginTime, endTime);
						break;
					case KeyEvent.ACTION_DOWN:
						break;
					}
					return true;
				}
				return false;
			}
		});

		lvnoend.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView tvid = (TextView) arg1.findViewById(R.id.tvId);
				TextView tvStoreCode = (TextView) arg1
						.findViewById(R.id.tvStoreCode);
				TextView tvStoreName = (TextView) arg1
						.findViewById(R.id.tvStoreName);
				TextView tvOutStockCode = (TextView) arg1
						.findViewById(R.id.tvoutstockcode);
				


				Intent intent = new Intent(PartnerPickerQueryActivity.this,
						PartnerPickerQueryDetailActivity.class);
				
				intent.putExtra("storeCode", tvStoreCode.getText());// 传递入库单号
				intent.putExtra("storeName", tvStoreName.getText());// 传递入库单号
				intent.putExtra("ousStockCode", tvOutStockCode.getText());// 传递入库单号
				intent.putExtra("date", dateDisplay.getText());
				
				startActivityForResult(intent, 1);

			}
		});

	}

	private void getPackageDetail(final String storedCode,
			final String beginTime, final String endTime) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/outBound/getOutBoundListProcess";

					OutBoundProcessQuery outBoundProcessQuery = new OutBoundProcessQuery();
					outBoundProcessQuery.setStoredCode(storedCode);
					outBoundProcessQuery.setStartTime(beginTime);
					outBoundProcessQuery.setEndTime(endTime);
					String json = JSON.toJSONString(outBoundProcessQuery);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200")) {
						outBoundList = JSON.parseArray(
								jsonSearch.optString("result"),
								OutBoundResponse.class);
						Message msg = new Message();
						msg.what = 4;
						msg.obj = "";
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					System.out.print(e.getMessage());
					Message msg = new Message();
					msg.what = 3;
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
				etStoreCode.setEnabled(true);
				PackageAllDetail detail = (PackageAllDetail) msg.obj;
				// 绑定
				Intent intent = new Intent(PartnerPickerQueryActivity.this,
						PartnerOrderActivity.class);
				intent.putExtra("packagecode", detail.getPackageCode());// 传递入库单号
				intent.putExtra("packageId", detail.getId());
				intent.putExtra("storeCode", detail.getStoredCode());
				startActivityForResult(intent, 0);
				break;
			case 4:
				etStoreCode.requestFocus();
				bindList();
				break;
			default:
				etStoreCode.setEnabled(true);
				break;
			}
		}
	};

	// 接受页面的返回值
	@Override
	// requestCode请求标识 //resultCode 返回标识
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		etStoreCode.selectAll();
		etStoreCode.requestFocus();

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
