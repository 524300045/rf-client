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
import com.wologic.domainnew.SendWave;
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

public class PartnerSortingGoodsStoreActivity extends Activity {

	private TextView tbBack,tvmsg;
	private ListView lvgoods;
	private MediaPlayer mediaPlayer;
	private LinearLayout llgoods;
	
	//private List<GoodsBarCode> goodsList;
	
	private List<StoreInfoProcess> storeList;
	
	private Button btnSure,btnDescSure;
	
	//private String containerCode;

	private List<SendWave> sendWaveList;
	
	private List<String> waveCodeList;
	
	private List<String> skuCodes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_partner_sortingstore);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		 skuCodes=new ArrayList<String>();
		Intent intent = getIntent();
		if (intent != null) {
			skuCodes = (List<String>)getIntent().getSerializableExtra("skuCodes");//
			
			//containerCode=intent.getStringExtra("containerCode");
			
			sendWaveList=(List<SendWave>)this.getIntent().getSerializableExtra("sendWaveList");
//			if(sendWaveList!=null)
//			{
//				waveCodeList=new ArrayList<String>();
//				for(SendWave sendWave:sendWaveList)
//				{
//					waveCodeList.add(sendWave.getWaveCode());
//				}
//			}
		}
		mediaPlayer = MediaPlayer.create(PartnerSortingGoodsStoreActivity.this,
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
				
				startPickSumbit(0);
			}});
		
		btnDescSure=(Button) findViewById(R.id.btnDescSure);
		btnDescSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				startPickSumbit(1);
			}});
		
		lvgoods.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView tvpriority = (TextView) arg1.findViewById(R.id.tvpriority);
				TextView tvStoreCode = (TextView) arg1.findViewById(R.id.tvStoreCode);
				
				Intent intent = new Intent(PartnerSortingGoodsStoreActivity.this,
						PartnerSortingPickActivity.class);
				//intent.putExtra("goodsList", (Serializable)goodsList);
				intent.putExtra("skuCodes", (Serializable)skuCodes);
				intent.putExtra("priority", tvpriority.getText());
				intent.putExtra("priority", tvpriority.getText());
				intent.putExtra("waveCodeList", (Serializable)waveCodeList);
				intent.putExtra("storeCode", tvStoreCode.getText());
				intent.putExtra("clickStoreFlag",0);
				//intent.putExtra("containerCode", containerCode);
				startActivityForResult(intent, 1);

			}
		});
	}
	
	
	private void startPickSumbit(int sortflag)
	{
		tvmsg.setText("");
		if (storeList==null||storeList.size()==0) {
			Toaster.toaster("没有要分拣的门店!");
			tvmsg.setText("没有要分拣的门店");
			return;
		}
		Intent intent = new Intent(PartnerSortingGoodsStoreActivity.this,
				PartnerSortingPickActivity.class);
		//intent.putExtra("goodsList", (Serializable)goodsList);
		intent.putExtra("skuCodes", (Serializable)skuCodes);
		intent.putExtra("priority", "0");
		intent.putExtra("clickStoreFlag",2);
		intent.putExtra("sortflag",sortflag);
		//intent.putExtra("containerCode", containerCode);
		intent.putExtra("waveCodeList", (Serializable)waveCodeList);
		startActivityForResult(intent, 1);
	}

	private void bindList() {
		List<Map<String, Object>> mapnoendList = new ArrayList<Map<String, Object>>();
		if (null != storeList) {
			
			List<StoreInfoProcess> finishStoreList=new ArrayList<StoreInfoProcess>();
			List<StoreInfoProcess> notFinishList=new ArrayList<StoreInfoProcess>();
			for (StoreInfoProcess item : storeList)
			{
				if(item.getFinishNum().equals(item.getTotalNum()))
				{
					finishStoreList.add(item);
				}
				else
				{
					notFinishList.add(item);
				}
			}
			
			Collections.sort(notFinishList, new Comparator(){
		        @Override
		        public int compare(Object o1, Object o2) {
		        	StoreInfoProcess stu1=(StoreInfoProcess)o1;
		        	StoreInfoProcess stu2=(StoreInfoProcess)o2;
		            if(stu1.getPriority()>stu2.getPriority()){
		                return 1;
		            }else if(stu1.getPriority()==stu2.getPriority()){
		                return 0;
		            }else{
		                return -1;
		            }
		        }       
		  });
			for (StoreInfoProcess item : notFinishList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("storeCode",  item.getStoredCode());
				map.put("storeName", item.getStoredName());
				map.put("process",item.getProcess());
				map.put("priority", item.getPriority());
				map.put("waveName", item.getWaveName());
				mapnoendList.add(map);
			}
			
			
			for (StoreInfoProcess item : finishStoreList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("storeCode",  item.getStoredCode());
				map.put("storeName", item.getStoredName());
				map.put("process",item.getProcess());
				map.put("priority", item.getPriority());
				map.put("waveName", item.getWaveName());
				mapnoendList.add(map);
			}
			llgoods.setVisibility(View.VISIBLE);
		}
		
		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitem_sortstore, new String[] {"storeCode", "storeName", "process","priority","waveName" },
				new int[] {R.id.tvStoreCode, R.id.tvStoreName, R.id.tvprocess,R.id.tvpriority,R.id.tvWaveName});
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
					request.setWaveCodeList(waveCodeList);
					
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
							msg.obj = "没有要分拣的门店";
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

	// 接受页面的返回值
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (requestCode == 1) {
			/*if (resultCode == Activity.RESULT_OK) {*/
			//	List<String> skuCodes=new ArrayList<String>();
//				for(GoodsBarCode item:sk)
//				{
//					skuCodes.add(item.getSkuCode());
//				}
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
