package com.wologic.ui;

import java.io.Serializable;
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
import com.wologic.domainnew.PartnerGoods;
import com.wologic.request.PartnerSkuGoodsRequest;
import com.wologic.request.PmsOrderPurchaseRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class PartnerSortingActivity extends Activity {

	private TextView tbBack;
	private EditText etCode;
	private TextView tvmsg;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerOk;
	
	List<PartnerGoods> list;
	
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_partner_sorting);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		lv=(ListView) findViewById(R.id.lv);
		
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etCode = (EditText) findViewById(R.id.etCode);;
		mediaPlayer = MediaPlayer.create(PartnerSortingActivity.this, R.raw.error);
		mediaPlayerOk = MediaPlayer.create(PartnerSortingActivity.this, R.raw.ok);
		initEvent();
		etCode.requestFocus();
		getPartnerGoods("");
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		getPartnerGoods("");
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
							Toaster.toaster("��������Ʒ���������!");
							tvmsg.setText("��������Ʒ���������!");
							tvmsg.setVisibility(View.VISIBLE);
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							return true;
						}
						
						etCode.setEnabled(false);
						getPartnerGoods(code);
						break;
					case KeyEvent.ACTION_DOWN:
						break;
					}
					return true;
				}
				return false;
			}
		});
	
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView tvSkuCode= (TextView) arg1.findViewById(R.id.tvSkuCode);
				
				List<String> skuCodes=new ArrayList<String>();
				skuCodes.add(tvSkuCode.getText().toString());
				
				Intent intent = new Intent(PartnerSortingActivity.this,
						PartnerSortingGoodsStoreActivity.class);
				intent.putExtra("skuCodes", (Serializable)skuCodes);
				startActivityForResult(intent, 1);

			}
		});
	}


	private void getPartnerGoods(final String goodsName) {

		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					String searchUrl = Constant.url
							+ "/partnerGoods/getPartnerGoodsStandList";
					PartnerSkuGoodsRequest request = new PartnerSkuGoodsRequest();
					request.setWarehouseCode(Common.WareHouseCode);
					request.setCustomerCode(Common.CustomerCode);
					request.setGoodsName(goodsName);
					request.setPartnerCode(Common.partnerCode);
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
									PartnerGoods.class);
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
					msg.obj = "�����쳣,������������";
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
			for (PartnerGoods goods : list) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("skucode", goods.getSkuCode());
				map.put("goodsname", goods.getGoodsName());
				map.put("unit", goods.getGoodsUnit());
				map.put("process", goods.getSortingNum().toString()+"/"+goods.getPlanNum().toString());
				mapnoendList.add(map);
			}
		}
		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_partner_sorting, new String[] { "skucode",
						"goodsname", "unit" , "process"}, new int[] {
						R.id.tvSkuCode, R.id.tvName,
					 R.id.tvUnit,R.id.tvProcess });
					 
	
		lv.setAdapter(adp);

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
