package com.wologic.ui;

import java.text.SimpleDateFormat;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domainnew.PmsOrderPurchaseReceiveDetail;
import com.wologic.request.PmsOrderPurchaseReceiveDetailRequest;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class PurchaseAcceptNotEndDetailActivity extends Activity   {

	private TextView tbBack;

	private ListView lvgoods;
	private MediaPlayer mediaPlayer;

	private List<PmsOrderPurchaseReceiveDetail> detailList;

	List<Map<String, Object>> mapnoendList;
	 
    private Long detailId; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase_accept_notenddetail);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		Intent intent = getIntent();
		if (intent != null) {
			detailId=Long.valueOf(intent.getStringExtra("detailId"));
		}
		mediaPlayer = MediaPlayer.create(PurchaseAcceptNotEndDetailActivity.this,
				R.raw.error);
		lvgoods = (ListView) findViewById(R.id.lvgoods);
		getGoods("");
	}

	private void bindList() {
		 mapnoendList = new ArrayList<Map<String, Object>>();
		 SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");  
		if (null != detailList) {
			for (PmsOrderPurchaseReceiveDetail item : detailList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("detailId", item.getDetailId());
				map.put("id", item.getId());
				map.put("goodsName",item.getGoodsName());
				map.put("receiveNum",item.getReceiveNum());
				/*if(null!=item.getExpiryDate())
				{*/
					map.put("expireDate",item.getExpiryDate());
				/*}
				else
				{
					map.put("expireDate", "");
				}*/
				if(null!=item.getProductionDate())
				{
					map.put("productDate", format0.format(item.getProductionDate()));
				}
				else
				{
					map.put("productDate","");
				}
				map.put("areaCode", item.getAreaCode());
				map.put("areaName", item.getAreaName());
				mapnoendList.add(map);
			}
		}
		
		MyAdapter adapter = new MyAdapter(this);
		lvgoods.setAdapter(adapter);
	

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



	private void getGoods(final String skuCode) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/pmsOrderPurchaseReceiveDetail/getPmsOrderPurchaseReceiveDetailList";
					PmsOrderPurchaseReceiveDetailRequest request = new PmsOrderPurchaseReceiveDetailRequest();;
					request.setDetailId(detailId);
				
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
					
							detailList = JSON
									.parseArray(
											jsonSearch
													.opt("result")
													.toString(),
													PmsOrderPurchaseReceiveDetail.class);
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
				
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				Toaster.toaster(msg.obj.toString());
				
				break;
			case 4:
				
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
			//if (resultCode == Activity.RESULT_OK) {
				getGoods("");
			//}

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
	}

	
	public final class ViewHolder {
		public TextView tvName;
		public TextView tvNum;
		public TextView tvExpire;
		public TextView tvProductDate;
		public TextView tvAreaName;
		public TextView tvId;
		public TextView tvDetailId;
		public TextView tvAreaCode;
		public Button btn;
	}
	
	public class MyAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mapnoendList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		//****************************************final方法
//注意原本getView方法中的int position变量是非final的，现在改为final
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			 ViewHolder holder = null;
			if (convertView == null) {
				
				holder=new ViewHolder();  
				
				//可以理解为从vlist获取view  之后把view返回给ListView
				
				convertView = mInflater.inflate(R.layout.listitem_purchase_accept_notenddetail, null);
				holder.tvId = (TextView)convertView.findViewById(R.id.tvId);
				holder.tvName = (TextView)convertView.findViewById(R.id.tvName);
				holder.tvNum = (TextView)convertView.findViewById(R.id.tvNum);
				holder.tvExpire = (TextView)convertView.findViewById(R.id.tvExpire);
				holder.tvProductDate = (TextView)convertView.findViewById(R.id.tvProductDate);
				holder.tvAreaName = (TextView)convertView.findViewById(R.id.tvAreaName);
				holder.tvDetailId = (TextView)convertView.findViewById(R.id.tvDetailId);
				holder.tvAreaCode=(TextView)convertView.findViewById(R.id.tvAreaCode);
				holder.btn = (Button)convertView.findViewById(R.id.btnEdit);
				
				convertView.setTag(holder);				
			}else {				
				holder = (ViewHolder)convertView.getTag();
			}		
			
			holder.tvDetailId.setText(mapnoendList.get(position).get("detailId").toString());
			holder.tvId.setText(mapnoendList.get(position).get("id").toString());
			holder.tvName.setText(mapnoendList.get(position).get("goodsName").toString());
			holder.tvNum.setText(mapnoendList.get(position).get("receiveNum").toString());
			holder.tvExpire.setText(mapnoendList.get(position).get("expireDate").toString());
			holder.tvProductDate.setText(mapnoendList.get(position).get("productDate").toString());
			holder.tvAreaName.setText(mapnoendList.get(position).get("areaName").toString());
			holder.tvAreaCode.setText(mapnoendList.get(position).get("areaCode").toString());
			
			holder.btn.setTag(position);
			//给Button添加单击事件  添加Button之后ListView将失去焦点  需要的直接把Button的焦点去掉
			holder.btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					  String detailId=mapnoendList.get((Integer) v.getTag()).get("detailId")
								.toString();
					//打开修改界面
				   String id=mapnoendList.get((Integer) v.getTag()).get("id")
					.toString();
				   String goodsName=mapnoendList.get((Integer) v.getTag()).get("goodsName")
							.toString();
				   
				   String num=mapnoendList.get((Integer) v.getTag()).get("receiveNum")
							.toString();
				   String expireDate=mapnoendList.get((Integer) v.getTag()).get("expireDate")
							.toString();
				   String productDate=mapnoendList.get((Integer) v.getTag()).get("productDate")
							.toString();
				   String areaCode=mapnoendList.get((Integer) v.getTag()).get("areaCode")
							.toString();
				   
				   Intent intent = new Intent(PurchaseAcceptNotEndDetailActivity.this,
						   PurchaseAcceptEditEndActivity.class);
            	   intent.putExtra("id",id);
            	   intent.putExtra("receiveNum",num);
            	   intent.putExtra("expireDate", expireDate);
            	   intent.putExtra("productDate", productDate);
            	   intent.putExtra("goodsName", goodsName);
            	   intent.putExtra("detailId", detailId);
            	   intent.putExtra("areaCode", areaCode);
            	   
           		   startActivityForResult(intent, 1);
				   
				}
			});
			
			return convertView;
		}
	}


}
