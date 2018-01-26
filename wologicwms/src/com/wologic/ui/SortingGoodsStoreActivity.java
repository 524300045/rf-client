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
import com.wologic.domainnew.StandStoreInfo;
import com.wologic.domainnew.StoreInfoProcess;
import com.wologic.request.GoodsQueryRequest;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.request.StandPackTaskCodesRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class SortingGoodsStoreActivity extends Activity {

	private TextView tbBack,tvmsg;
	private ListView lvgoods;
	private MediaPlayer mediaPlayer;
	private LinearLayout llgoods;
	
	private List<GoodsBarCode> goodsList;
	
	private List<StoreInfoProcess> storeList;
	
	private Button btnSure;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sortingstore);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		List<String> skuCodes=new ArrayList<String>();
		Intent intent = getIntent();
		if (intent != null) {
			goodsList = (List<GoodsBarCode>)getIntent().getSerializableExtra("goodsList");//
			for(GoodsBarCode item:goodsList)
			{
				skuCodes.add(item.getSkuCode());
			}
		}
		mediaPlayer = MediaPlayer.create(SortingGoodsStoreActivity.this,
				R.raw.error);
		llgoods = (LinearLayout) findViewById(R.id.llgoods);
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		lvgoods = (ListView) findViewById(R.id.lvgoods);
		if(skuCodes.size()>0)
		{
			getStoreList(skuCodes);
		}
		btnSure=(Button) findViewById(R.id.btnSure);
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				startPickSumbit();
			}});
		
		lvgoods.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView tvpriority = (TextView) arg1.findViewById(R.id.tvpriority);
				Intent intent = new Intent(SortingGoodsStoreActivity.this,
						SortingPickActivity.class);
				intent.putExtra("goodsList", (Serializable)goodsList);
				intent.putExtra("priority", tvpriority.getText());
				startActivityForResult(intent, 1);

			}
		});
	}
	
	
	private void startPickSumbit()
	{
		tvmsg.setText("");
		if (storeList==null||storeList.size()==0) {
			Toaster.toaster("û��Ҫ�ּ���ŵ�!");
			tvmsg.setText("û��Ҫ�ּ���ŵ�");
			return;
		}
		Intent intent = new Intent(SortingGoodsStoreActivity.this,
				SortingPickActivity.class);
		intent.putExtra("goodsList", (Serializable)goodsList);
		intent.putExtra("priority", "0");
		startActivityForResult(intent, 1);
	}

	private void bindList() {
		List<Map<String, Object>> mapnoendList = new ArrayList<Map<String, Object>>();
		if (null != storeList) {
			for (StoreInfoProcess item : storeList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("storeCode",  item.getStoredCode());
				map.put("storeName", item.getStoredName());
				map.put("process",item.getProcess());
				map.put("priority", item.getPriority());
				mapnoendList.add(map);
			}
			llgoods.setVisibility(View.VISIBLE);
		}
		
		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_sortstore, new String[] {"storeCode", "storeName", "process","priority" },
				new int[] {R.id.tvStoreCode, R.id.tvStoreName, R.id.tvprocess,R.id.tvpriority});
		lvgoods.setAdapter(adp);
	}

	private void getStoreList(final List<String> skuCodes) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();
					String searchUrl = Constant.url
							+ "/standPackTask/getStandTaskStoreListBySku";
					StandPackTaskCodesRequest  request=new StandPackTaskCodesRequest();
					request.setSkuCodes(skuCodes);
					request.setWarehouseCode(Common.WareHouseCode);
					request.setCustomerCode(Common.CustomerCode);
					request.setPartnerCode(Common.partnerCode);
					
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
							msg.obj = "û��Ҫ�ּ���ŵ�";
							handler.sendMessage(msg);
						} 
						else
						{
							storeList = JSON
									.parseArray(
											jsonSearch
													.opt("result")
													.toString(),
													StoreInfoProcess.class);
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
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				Toaster.toaster(msg.obj.toString());
				break;
			case 4:
				llgoods.setVisibility(View.VISIBLE);
				bindList();
				break;
			default:
				break;
			}
		}
	};

	// ����ҳ��ķ���ֵ
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (requestCode == 1) {
			/*if (resultCode == Activity.RESULT_OK) {*/
				List<String> skuCodes=new ArrayList<String>();
				for(GoodsBarCode item:goodsList)
				{
					skuCodes.add(item.getSkuCode());
				}
				if(skuCodes.size()>0)
				{
					getStoreList(skuCodes);
				}
			//}
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