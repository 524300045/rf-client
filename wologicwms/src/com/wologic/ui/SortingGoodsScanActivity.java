package com.wologic.ui;

import java.io.Serializable;
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
import android.widget.Button;
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
import com.wologic.request.GoodsBarcodeRequest;
import com.wologic.request.GoodsQueryRequest;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class SortingGoodsScanActivity extends Activity {

	private TextView tbBack,tvmsg;
	private EditText etSku;
	private ListView lvgoods;
	private MediaPlayer mediaPlayer;
	private LinearLayout llgoods;
    private Button btnSure;
	private List<GoodsBarCode> goodsList;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sortinggoodsscan);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		goodsList=new ArrayList<GoodsBarCode>();
		mediaPlayer = MediaPlayer.create(SortingGoodsScanActivity.this,
				R.raw.error);
		llgoods = (LinearLayout) findViewById(R.id.llgoods);
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etSku = (EditText) findViewById(R.id.etSku);
		lvgoods = (ListView) findViewById(R.id.lvgoods);
		btnSure=(Button) findViewById(R.id.btnSure);
		initEvent();
		etSku.requestFocus();

	}

	private void bindList() {
		List<Map<String, Object>> mapnoendList = new ArrayList<Map<String, Object>>();
		if (null != goodsList) {
			for (GoodsBarCode item : goodsList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("skucode",  item.getSkuCode());
				map.put("goodsName", item.getGoodsName());
				map.put("barcodestr", item.getBarCodeStr());
				mapnoendList.add(map);
			}
			llgoods.setVisibility(View.VISIBLE);
		}
		
		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_goodsbar, new String[] {"skucode", "goodsName", "barcodestr" },
				new int[] {R.id.tbskucode, R.id.tvname, R.id.tbbarcode});
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
							Toaster.toaster("请扫描商品条码!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("请扫描商品条码!");
							return true;
						}
						etSku.setEnabled(false);
						getGoods(skuname);
						break;
					case KeyEvent.ACTION_DOWN:
						break;
					}
					return true;
				}
				return false;
			}
		});
        
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				sumbit();
			}});
	}

	private void sumbit()
	{
		tvmsg.setText("");
		if (goodsList.size()==0) {
			Toaster.toaster("请先扫描商品!");
			tvmsg.setText("请先扫描商品");
			return;
		}
		Intent intent = new Intent(SortingGoodsScanActivity.this,
				SortingGoodsStoreActivity.class);
		intent.putExtra("goodsList", (Serializable)goodsList);
		startActivityForResult(intent, 1);
	}
	private void getGoods(final String barCode) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/goods/getGoodsByBarCode";
					GoodsBarcodeRequest  request=new GoodsBarcodeRequest();
					request.setBarCode(barCode);
					request.setSkuCode(barCode);
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
							msg.obj = "查询不到商品信息";
							handler.sendMessage(msg);
						} 
						else
						{
							List<GoodsBarCode>  curGoodsList = JSON
									.parseArray(
											jsonSearch
													.opt("result")
													.toString(),
													GoodsBarCode.class);
							if(curGoodsList.get(0).getIsFresh().equals(1))
							{
								Message msg = new Message();
								msg.what = 2;
								msg.obj = "只能扫描标品";
								handler.sendMessage(msg);
							}
							else
							{
								if(goodsList.size()==0)
								{
									goodsList.addAll(curGoodsList);
								}
								else
								{
										for(GoodsBarCode barCodeItem:curGoodsList)
										{
											boolean isExist=false;
											for(GoodsBarCode item:goodsList)
											{
											   if(item.getSkuCode().equals(barCodeItem.getSkuCode()))
											   {
												   isExist=true;
												   break;
											   }
											}
											if(!isExist)
											{
												goodsList.add(barCodeItem);
											}
										}
								}
								Message msg = new Message();
								msg.what = 4;
								msg.obj = "";
								handler.sendMessage(msg);
							}
							
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
				llgoods.setVisibility(View.VISIBLE);
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
				getGoods(etSku.getText().toString());
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
