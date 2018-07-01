package com.wologic.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domain.PmsOrderPurchase;
import com.wologic.request.PmsOrderPurchaseRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class PurchaseAcceptActivity extends Activity {

	private TextView tbBack;
	private EditText etCode;
	private TextView tvmsg;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerOk;
	
	List<PmsOrderPurchase> list;
	
	private ListView lvnoend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase_accept);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		lvnoend=(ListView) findViewById(R.id.lvnoend);
		
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etCode = (EditText) findViewById(R.id.etCode);;
		mediaPlayer = MediaPlayer.create(PurchaseAcceptActivity.this, R.raw.error);
		mediaPlayerOk = MediaPlayer.create(PurchaseAcceptActivity.this, R.raw.ok);
		initEvent();
		etCode.requestFocus();
		getPurchase("");
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		getPurchase("");
	}

	private void initEvent() {
		etCode.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						tvmsg.setText("");
						tvmsg.setVisibility(View.GONE);
						String code = etCode.getText().toString()
								.trim();
						if (code.equals("")) {
							etCode.selectAll();
							Toaster.toaster("请扫描采购单号或供应商编码!");
							tvmsg.setText("请扫描采购单号或供应商编码!");
							tvmsg.setVisibility(View.VISIBLE);
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							return true;
						}
						
						etCode.setEnabled(false);
						getPurchase(code);
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

				TextView tvOrderNo= (TextView) arg1.findViewById(R.id.tvOrderNo);
				

				Intent intent = new Intent(PurchaseAcceptActivity.this,
						Purchase_Accept_Scan_Activity.class);
				intent.putExtra("orderNo", tvOrderNo.getText());// 传递入库单号
				startActivityForResult(intent, 1);

			}
		});
	}


	private void getPurchase(final String code) {

		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					String searchUrl = Constant.url
							+ "/pmsOrderPurchase/queryPurchase";
					PmsOrderPurchaseRequest request = new PmsOrderPurchaseRequest();
					request.setWarehouseCode(Common.WareHouseCode);
					request.setCustomerCode(Common.CustomerCode);
					request.setOrderNo(code);
					String json2 = JSON.toJSONString(request);
					String resultSearch2 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json2);
					JSONObject jsonSearch2;
					try {
						jsonSearch2 = new JSONObject(resultSearch2);
						if (jsonSearch2.optString("code").toString()
								.equals("200")) {
							
							list=JSON.parseArray(
									jsonSearch2.optString("result"),
									PmsOrderPurchase.class);
							Message msg = new Message();
							msg.what =1;
							msg.obj ="";
							handler.sendMessage(msg);
						}

					} catch (JSONException e) {
		
						e.printStackTrace();
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


	private void bindList() {

		List<Map<String, Object>> mapnoendList = new ArrayList<Map<String, Object>>();
		if (null != list) {
			for (PmsOrderPurchase item : list) {
				/*if(item.getStatus()==30||item.getStatus()==40)
				{
					continue;
				}*/
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("orderNo", item.getOrderNo());
				map.put("partnerName", item.getPartnerName());
				SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
				if(null!=item.getCreateTime())
				{
					map.put("createTime", time.format(item.getCreateTime()));
				}
				mapnoendList.add(map);
			}
		}

		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_purchase_accept_one, new String[] { "orderNo",
						"partnerName", "createTime" }, new int[] {
						R.id.tvOrderNo, R.id.tvPartnerName,
					 R.id.tvTime });
					 
	
		lvnoend.setAdapter(adp);

	}
	
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				etCode.setEnabled(true);
				
				etCode.requestFocus();
				etCode.selectAll();
				bindList();
				break;
			case 2:
				
				Toaster.toaster(msg.obj.toString());
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				break;
			case 3:
				
				Toaster.toaster(msg.obj.toString());
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				break;
			case 4:
			
				Toaster.toaster(msg.obj.toString());
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayerOk.setVolume(1.0f, 1.0f);
				mediaPlayerOk.start();
				break;
			default:
				
				break;
			}
		}
	};

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

	@Override
	protected void onStart() {
		super.onStart();

	}

}
