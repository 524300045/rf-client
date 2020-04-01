package com.wologic.ui;

import java.math.BigDecimal;
import java.text.ParseException;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domainnew.GoodsBarCode;
import com.wologic.domainnew.StockExpiryInfo;
import com.wologic.request.GoodsBarcodeRequest;
import com.wologic.request.StandardPickingOperationRequest;
import com.wologic.request.StockExpiryInfoRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.DateUtils;
import com.wologic.util.Toaster;

public class FreeInventoryGoodsActivity extends Activity {

	private TextView tbBack,tvmsg,tvTitle;
	private EditText etSku;
	private ListView lvgoods;
	private MediaPlayer mediaPlayer;

	private List<StockExpiryInfo> stockExpiryInfoList;

	private String areaCode;
	
	private String areaName;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_free_inventory_goods);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		stockExpiryInfoList=new ArrayList<StockExpiryInfo>();
		mediaPlayer = MediaPlayer.create(FreeInventoryGoodsActivity.this,
				R.raw.error);
		
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		tvTitle=(TextView) findViewById(R.id.tvTitle);
		etSku = (EditText) findViewById(R.id.etSku);
		lvgoods = (ListView) findViewById(R.id.lvgoods);
		
		Intent intent = getIntent();
		if (intent != null) {
			areaName = intent.getStringExtra("areaName");
			areaCode=intent.getStringExtra("areaCode");
		}
		tvTitle.setText(areaName);
		
		initEvent();
		etSku.requestFocus();
		getStockGoods("");
	}

	private void bindList() {
		List<Map<String, Object>> mapnoendList = new ArrayList<Map<String, Object>>();
		if (null != stockExpiryInfoList) {
			for (StockExpiryInfo item : stockExpiryInfoList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("skucode",  item.getSkuCode());
				map.put("goodsName", item.getSkuCode()+" "+item.getGoodsName());
				
				BigDecimal realStock=item.getRealStock();
				if(item.getLockStock().compareTo(new BigDecimal(0))==1)
				{
					realStock=realStock.subtract(item.getLockStock());
				}
				if(item.getOccupyStock().compareTo(new BigDecimal(0))==1)
				{
					realStock=realStock.subtract(item.getOccupyStock());
				}
				
				map.put("realStock", realStock);
				map.put("occupyStock", item.getOccupyStock());
				map.put("lockStock", item.getLockStock());
				map.put("totalStock", item.getRealStock());
				map.put("goodsUnit", item.getGoodsUnit());
				map.put("id", item.getId());
				try {
					map.put("productionDate",DateUtils.dateFormat(item.getProductionDate(),DateUtils.DATE_PATTERN));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("productionDate","");
				}
				
				mapnoendList.add(map);
			}
			
		}
		
		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_inventory_stock_info, new String[] {"skucode", "goodsName", "realStock","occupyStock","lockStock","productionDate","goodsUnit","id","totalStock" },
				new int[] {R.id.tbskucode, R.id.tvGoodsName, R.id.tvRealStock,R.id.tvOccupyStock,R.id.tvLockStock,R.id.tvProductionDate,R.id.tvUnit,R.id.tvId,R.id.tvTotalStock});
		lvgoods.setAdapter(adp);
	}

	private void initEvent() {
		
		lvgoods.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView tvSkuCode = (TextView) arg1.findViewById(R.id.tbskucode);
				TextView tvSkuName = (TextView) arg1.findViewById(R.id.tvGoodsName);
				
				TextView tvRealStock = (TextView) arg1.findViewById(R.id.tvRealStock);
				TextView tvOccupyStock = (TextView) arg1.findViewById(R.id.tvOccupyStock);
				TextView tvLockStock = (TextView) arg1.findViewById(R.id.tvLockStock);
				TextView tvUnit = (TextView) arg1.findViewById(R.id.tvUnit);
				TextView tvId = (TextView) arg1.findViewById(R.id.tvId);
				
				TextView tvTotalStock = (TextView) arg1.findViewById(R.id.tvTotalStock);
		
				Intent intent = new Intent(FreeInventoryGoodsActivity.this,
						FreeInventoryInputNumActivity.class);
				intent.putExtra("skuCode", tvSkuCode.getText());
				intent.putExtra("goodsName", tvSkuName.getText());
				intent.putExtra("realStock", tvRealStock.getText());
				intent.putExtra("occupyStock", tvOccupyStock.getText());
				intent.putExtra("lockStock", tvLockStock.getText());
				
				intent.putExtra("totalStock", tvTotalStock.getText());
				
				intent.putExtra("goodsUnit", tvUnit.getText());
				intent.putExtra("areaCode", areaCode);
				intent.putExtra("areaName", areaName);
				intent.putExtra("stockExpirInfoId",Long.valueOf(tvId.getText().toString()));
				
				startActivityForResult(intent, 1);

			}
		});
		
		
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
							Toaster.toaster("请输入商品名称!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("请输入商品名称!");
							return true;
						}
						etSku.setEnabled(false);
						getStockGoods(skuname);
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

	
	
	
	
	private void getStockGoods(final String goodsName) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/stockExpiryInfo/getStockExpiryInfoList";
					StockExpiryInfoRequest  request=new StockExpiryInfoRequest();
					request.setAreaCode(areaCode);
					request.setSkuCode(goodsName);
					
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
							msg.obj = jsonSearch.optString("message");
							handler.sendMessage(msg);
						} 
						else
						{
						    stockExpiryInfoList = JSON
									.parseArray(
											jsonSearch
													.opt("result")
													.toString(),
													StockExpiryInfo.class);
							
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

	// 接受页面的返回值
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		etSku.selectAll();
		etSku.requestFocus();
		if (requestCode == 1) {
			if (resultCode == Activity.RESULT_OK) {
				getStockGoods(etSku.getText().toString());
			}

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
