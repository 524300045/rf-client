package com.wologic.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domainnew.ContainerSkuRel;
import com.wologic.domainnew.Goods;
import com.wologic.domainnew.GoodsBarCode;
import com.wologic.domainnew.GoodsSuit;
import com.wologic.request.ContainerSkuRelRequest;
import com.wologic.request.GoodsBarcodeRequest;
import com.wologic.request.GoodsSuitRequest;
import com.wologic.request.StandSkuTaskRequest;
import com.wologic.request.StandardSortingRequest;
import com.wologic.response.StandPickTaskResponse;
import com.wologic.ui.GoodsSuitActivity.SpecialAdapter;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class GoodsSuitCaiJiActivity extends Activity {

	private TextView tbBack;
	private EditText  etTotalWeight;
	private Button btnSure;
	private TextView tvmsg,  tvSkuCode, tvGoodsName,tvDate,tvPhyUnit,tvUnit;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerOk;
	

	private String skuCode;
	

	 
	private ListView lv;
	
	List<GoodsSuit> goodsSuitlist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_suit_caiji);
		Intent intent = getIntent();
		if (intent != null) {
			
			skuCode=intent.getStringExtra("skucode");
		
		}
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		mediaPlayer = MediaPlayer.create(GoodsSuitCaiJiActivity.this, R.raw.error);
		mediaPlayerOk = MediaPlayer.create(GoodsSuitCaiJiActivity.this, R.raw.ok);
		lv=(ListView) findViewById(R.id.lv);
		tvSkuCode = (TextView) findViewById(R.id.tvSkuCode);
		tvGoodsName = (TextView) findViewById(R.id.tvGoodsName);
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		tvDate= (TextView) findViewById(R.id.tv_date);
		tvUnit=(TextView) findViewById(R.id.tvUnit);
		etTotalWeight = (EditText) findViewById(R.id.etTotalWeight);
		btnSure = (Button) findViewById(R.id.btnSure);
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				sumbit();
			}
		});
		
	
		
		
     	load(skuCode);
		
	}
	
	public void setListViewHeightBasedOnChildren(ListView listView) {
		 
		  ListAdapter listAdapter = listView.getAdapter();
		 
		  if (listAdapter == null) {
		   return;
		  }
		 
		  int totalHeight = 0;
		 
		  for (int i = 0; i < listAdapter.getCount()-1; i++) {
		   View listItem = listAdapter.getView(i, null, listView);
		   listItem.measure(0, 0);
		   totalHeight += listItem.getMeasuredHeight();
		  }
		 
		  ViewGroup.LayoutParams params = listView.getLayoutParams();
		 
		  params.height = totalHeight
		    + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		 
		 
		 
		  listView.setLayoutParams(params);
		 }
	
	 public void saveEditData(int position, String str) {
	        Toast.makeText(this,str+"----"+position,Toast.LENGTH_LONG).show();
	    }

	private void load(final String skuCode) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();
					String searchUrl = Constant.url
							+ "/goodsSuit/getGoodsSuitList";
					GoodsSuitRequest request = new GoodsSuitRequest();
					request.setSkuCode(skuCode);
		
					
					String json2 = JSON.toJSONString(request);
					String resultSearch2 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json2);
					JSONObject jsonSearch2 = new JSONObject(resultSearch2);
					if (jsonSearch2.optString("code").toString().equals("200"))
					{
						if (!"null".equals(jsonSearch2.opt("result").toString()))
						{
							

							goodsSuitlist=JSON.parseArray(
									jsonSearch2.optString("result"),
									GoodsSuit.class);
							
							
								Message msg1 = new Message();
								msg1.what =1;
								msg1.obj = "";
								handler.sendMessage(msg1);
							
							
						} else {
							
							Message msg = new Message();
							msg.what = 2;
							msg.obj = "û�л�ȡ����װ��ϸ";
							handler.sendMessage(msg);
							
							
						}
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



	/**
	 * ����������ʽ�ж��ַ����Ƿ�������
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	
	
	private void bindList() {

		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (null != goodsSuitlist) {
			for (GoodsSuit goodsSuit : goodsSuitlist) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("skucode", goodsSuit.getChildSkuCode());
				map.put("goodsname", goodsSuit.getChildGoodsName());
				mapList.add(map);
			}
		}

		lv.setAdapter(new ListSuitAdapter(this,mapList));
		 
		//setListViewHeightBasedOnChildren(lv);

	}


	private void sumbit() {

		tvmsg.setText("");
		
		
		
		
		if (etTotalWeight.getText().toString().trim().equals("")) {
			Toaster.toaster("������������!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("������������");
			return;
		}
		if (!isNumeric(etTotalWeight.getText().toString().trim())) {
			Toaster.toaster("����������!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("����������");
			return;
		}
	  BigDecimal	sortingNum = new BigDecimal(etTotalWeight.getText().toString().trim());
		int i = sortingNum.compareTo(BigDecimal.ZERO);
		if (i == 0 || i == -1) {
			Toaster.toaster("�����������0!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("�����������0");
			return;
		}
		
		
	
	
		if (tvDate.getText().toString().trim().equals("")) {
			
			Toaster.toaster("��ѡ����������!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("��ѡ����������!");
			return ;
		}
		
		
		btnSure.setText("�ύ��...");
		btnSure.setEnabled(false);
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					 HttpClient client = com.wologic.util.SimpleClient
					 .getHttpClient();
					String searchUrl = Constant.url
							+ "/standPackTask/partnerSumbit";
					StandardSortingRequest request = new StandardSortingRequest();
					// request.setId(id);
					
					 request.setSkuCode(skuCode);
					 request.setBarCode(skuCode);
			
					 request.setWarehouseCode(Common.WareHouseCode);
					 request.setUpdateUser(Common.UserName);
					// request.setContainerCode(containerCode);
					 request.setCustomerName(Common.CustomerName);
					 request.setCustomerCode(Common.CustomerCode);
					 
					String json2 = JSON.toJSONString(request);
					String resultSearch2 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json2);
					JSONObject jsonSearch2 = new JSONObject(resultSearch2);
					if (jsonSearch2.optString("code").toString().equals("200")) {
						Message msg = new Message();
						msg.what =8;
						msg.obj = jsonSearch2.optString("result").toString();
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
			case 1:
				bindList();
				break;
			case 2:
			    //�ύʧ��
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				Toaster.toaster(msg.obj.toString());
				
				break;
			case 3:
				mediaPlayerOk.setVolume(1.0f, 1.0f);
				mediaPlayerOk.start();
				// �ύ�ɹ�
				Toaster.toaster(msg.obj.toString());
				finish();
				break;
			
			case 4:
			
				btnSure.setEnabled(true);
				btnSure.setText("ȷ��");
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				Toaster.toaster(msg.obj.toString());
				break;
			

			default:
				
				break;
			}
		}
	};

	

	
	
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
		if (mediaPlayerOk != null) {
			mediaPlayerOk.stop();
			mediaPlayerOk.release();
		}
	};

}
