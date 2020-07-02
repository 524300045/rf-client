package com.wologic.ui;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
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
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domainnew.CustomerGoods;
import com.wologic.domainnew.Goods;
import com.wologic.domainnew.GoodsSuitBoxTransferDetail;
import com.wologic.domainnew.WarehouseArea;
import com.wologic.request.GoodsRequest;
import com.wologic.request.GoodsSuitBoxRequest;
import com.wologic.request.GoodsSuitBoxTransferRequest;
import com.wologic.request.WarehouseAreaRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class GoodsSuitInBoundBoxActivity extends Activity {

	private TextView tbBack;
	private EditText etCode;
	private TextView tvmsg;
	private TextView tvGoodsName,tvUnit,tvDate,tvTotalWeight,tvAreaName;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerOk;
	
    private Button btnSure=null;
	
	String skuCode;
	
	List<GoodsSuitBoxTransferDetail> list;
	
	private ListView lv;
	
	CustomerGoods customerGoods;
	
	private  String areaCode;
	private String areaName;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_suit_inbound_box);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		lv=(ListView) findViewById(R.id.lv);
		
		//tvSkuCode = (TextView) findViewById(R.id.tvSkuCode);
		tvGoodsName = (TextView) findViewById(R.id.tvGoodsName);
		tvUnit = (TextView) findViewById(R.id.tvUnit);
		tvTotalWeight = (TextView) findViewById(R.id.tvTotalWeight);
		tvDate = (TextView) findViewById(R.id.tv_date);
		Intent intent = getIntent();
         if (intent != null) {
			skuCode=intent.getStringExtra("skucode");
		}
         tvAreaName=(TextView) findViewById(R.id.tvAreaName);
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etCode = (EditText) findViewById(R.id.etCode);
		btnSure=(Button) findViewById(R.id.btnSure);
		mediaPlayer = MediaPlayer.create(GoodsSuitInBoundBoxActivity.this, R.raw.error);
		mediaPlayerOk = MediaPlayer.create(GoodsSuitInBoundBoxActivity.this, R.raw.ok);
		initEvent();
		etCode.requestFocus();
		getGoods(skuCode);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
	}
	
	private void getGoods(final String skuCode) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/goods/getCustomerGoodsBySkuCode";
					GoodsRequest request = new GoodsRequest();;
					request.setSkuCode(skuCode);
					request.setCustomerCode(Common.CustomerCode);
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
					
						customerGoods = JSON
								.parseObject(
										jsonSearch
												.opt("result")
												.toString(),
												CustomerGoods.class);
						 
						 if(customerGoods!=null)
						 {
							    Message msg = new Message();
								msg.what =4;
								msg.obj =customerGoods;
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
							Toaster.toaster("请输入商品编码或名称!");
							tvmsg.setText("请输入商品编码或名称!");
							tvmsg.setVisibility(View.VISIBLE);
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							return true;
						}
						
						etCode.setEnabled(false);
						getBoxGoods(code,skuCode);
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
			}
		});
		
	}
	

	
	
	
	
	private void sumbit() {

		tvmsg.setText("");
	
		if(etCode.getText().toString().trim()=="")
		{
			Toaster.toaster("请扫描箱码!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("请扫描箱码");
			return;
		}
		
		if(Common.WareHouseCode.equals(""))
		{
			
			tvmsg.setText("获取不到仓库信息，请退出系统重新登陆");
			return;
		}
		
		if(areaName==null||areaName.equals(""))
		{
			Toaster.toaster("库区不能为空!");
			return;
		}
		
       final  String boxCode=etCode.getText().toString().trim();
		
		btnSure.setText("提交中...");
		btnSure.setEnabled(false);
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					 HttpClient client = com.wologic.util.SimpleClient
					 .getHttpClient();
					String searchUrl = Constant.url
							+ "/goodsSuitBoxTransfer/inbound";
					GoodsSuitBoxTransferRequest request = new GoodsSuitBoxTransferRequest();
					 request.setSkuCode(skuCode);
					 request.setWarehouseCode(Common.WareHouseCode);
					 request.setWarehouseName(Common.WareHouseName);
					 request.setCreateUser(Common.UserName);
					 request.setCustomerName(Common.CustomerName);
					 request.setCustomerCode(Common.CustomerCode);
					 request.setBoxCode(boxCode);
					
					 
					String json2 = JSON.toJSONString(request);
					String resultSearch2 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json2);
					JSONObject jsonSearch2 = new JSONObject(resultSearch2);
					if (jsonSearch2.optString("code").toString().equals("200")) {
						Message msg = new Message();
						msg.what =3;
						msg.obj = jsonSearch2.optString("message").toString();
						handler.sendMessage(msg);
						
					} else {
						Message msg = new Message();
						msg.what = 2;
						msg.obj = jsonSearch2.optString("message").toString();
						handler.sendMessage(msg);
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



	private void getBoxGoods(final String boxCode,final String skucode) {

		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					String searchUrl = Constant.url
							+ "/goodsSuitBoxTransfer/getBoxTransferInfo";
					GoodsSuitBoxRequest request = new GoodsSuitBoxRequest();	
					request.setSkuCode(skucode);
					request.setBoxCode(boxCode);
					request.setWarehouseCode(Common.WareHouseCode);
					request.setCustomerCode(Common.CustomerCode);
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
									GoodsSuitBoxTransferDetail.class);
							Message msg = new Message();
							msg.what =1;
							msg.obj =list;
							handler.sendMessage(msg);
						}
						else
						{
							Message msg = new Message();
							msg.what = 2;
							msg.obj = jsonSearch2.optString("message");
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
			for (GoodsSuitBoxTransferDetail goods : list) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("goodsname", goods.getChildGoodsName());
				map.put("weight", goods.getChildWeight());
				mapnoendList.add(map);
			}
		}
		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_goods_suit_transfer_detail, new String[] {
						"goodsname", "weight" }, new int[] {
						 R.id.tvName,
					 R.id.tvWeight });
					 
	
		lv.setAdapter(adp);

	}
	
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
			//	tvSkuCode.setText(list.get(0).getSkuCode());
				tvGoodsName.setText(list.get(0).getGoodsName());
				tvUnit.setText(list.get(0).getGoodsUnit());
				//tvDate.setText(list.get(0).getProductionDate());
				
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				  Date date = new Date(Long.valueOf(list.get(0).getProductionDate()));
				  String productionDateStr=sf.format(date);
				  tvDate.setText(productionDateStr);
				
				tvTotalWeight.setText(list.get(0).getWeight().toString());
				etCode.setEnabled(true);
				etCode.requestFocus();
				etCode.selectAll();
				bindList();
				break;
			case 2:
				etCode.setEnabled(true);
				etCode.selectAll();
				
				btnSure.setEnabled(true);
				btnSure.setText("确定");
				Toaster.toaster(msg.obj.toString());
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				break;
              case 3:
            		btnSure.setEnabled(true);
    				btnSure.setText("确定");
				Toaster.toaster(msg.obj.toString());
				etCode.setText("");
				etCode.requestFocus();
				mediaPlayerOk.setVolume(1.0f, 1.0f);
				mediaPlayerOk.start();
				break;
              case 4:
            	  CustomerGoods customerGoods = (CustomerGoods)msg.obj;
  				
  				areaCode=customerGoods.getAreaCode();
  				areaName=customerGoods.getAreaName();
  				tvAreaName.setText(customerGoods.getAreaName());
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
