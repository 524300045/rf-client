package com.wologic.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.application.MyApplication;
import com.wologic.domainnew.BoxInfo;
import com.wologic.domainnew.GoodsBarCode;
import com.wologic.domainnew.PackageAllDetail;
import com.wologic.domainnew.PreprocessInfo;
import com.wologic.request.BoxInfoRequest;
import com.wologic.request.GoodsBarcodeRequest;
import com.wologic.request.OutBoundRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.request.StandSkuTaskRequest;
import com.wologic.request.StandardSortingRequest;
import com.wologic.response.StandPickTaskResponse;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class SortingPickActivity extends Activity {

	private TextView tbBack;
	private EditText etBarCode, etnum,etStoreCode;
	private Button btnSure, btnShow,btnNext;
	private TextView tvmsg, tvProcess, tvStoreName, tvGoodsName,tvSortInfo;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerOk;
	private List<GoodsBarCode> goodsList;
	List<String> skuCodes = null;
	
	private String lastSkuCode="";

	private String storedCode;
	/** id */
	private Long id;
	
	private BigDecimal sortingNum;
	private String skuCode;
	  private BigDecimal planNum;
	  private BigDecimal sortedNum; //�ѷּ���
	private Integer priority;
	
	private int clickStoreFlag=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sortingpick);
		Intent intent = getIntent();
		skuCodes = new ArrayList<String>();
		if (intent != null) {
			goodsList = (List<GoodsBarCode>) getIntent().getSerializableExtra(
					"goodsList");//
			for (GoodsBarCode item : goodsList) {
				skuCodes.add(item.getSkuCode());
			}
			priority=intent.getIntExtra("priority", 0);
			storedCode=intent.getStringExtra("storeCode");
			clickStoreFlag=intent.getIntExtra("clickStoreFlag",0);
		}
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		mediaPlayer = MediaPlayer.create(SortingPickActivity.this, R.raw.error);
		mediaPlayerOk = MediaPlayer.create(SortingPickActivity.this, R.raw.ok);
		tvProcess = (TextView) findViewById(R.id.tvProcess);
		tvStoreName = (TextView) findViewById(R.id.tvStoreName);
		tvGoodsName = (TextView) findViewById(R.id.tvGoodsName);
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etBarCode = (EditText) findViewById(R.id.etBarCode);
		etStoreCode=(EditText) findViewById(R.id.etStoreCode);
		
		etnum = (EditText) findViewById(R.id.etnum);
		btnSure = (Button) findViewById(R.id.btnSure);
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				sumbit();
			}
		});
		
		btnNext=(Button) findViewById(R.id.btnNext);
		btnNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				if(skuCodes.size()==0||skuCodes.size()==1)
				{
					tvmsg.setVisibility(View.VISIBLE);
					tvmsg.setText("�Ѿ������һ����Ʒ");
					Toaster.toaster("�Ѿ������һ����Ʒ");
					return;
				}
				Iterator<String> iter = skuCodes.iterator();
				while(iter.hasNext()){
		            String str= iter.next();
		            if(skuCode.equals(str)){
		                iter.remove();
		            }
		        }
				load(skuCodes);
			}
		});
		tvSortInfo=(TextView) findViewById(R.id.tvSortInfo);
		tvSortInfo.setText("");
		btnShow = (Button) findViewById(R.id.btnShow);
		btnShow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(SortingPickActivity.this,
						StoreSortProcessActivity.class);
				intent.putExtra("storedCode", storedCode);
				intent.putExtra("storedName", tvStoreName.getText());
				startActivityForResult(intent, 1);
			}
		});
		tvProcess.setText("");
		tvStoreName.setText("");
		tvGoodsName.setText("");
		initEvent();
		load(skuCodes);
		//etBarCode.requestFocus();
	}

	private void load(final List<String> skuCodes) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();
					String searchUrl = Constant.url
							+ "/standPackTask/getStandTask";
					StandSkuTaskRequest request = new StandSkuTaskRequest();
					request.setCustomerCode(Common.CustomerCode);
					//request.setPartnerCode(Common.partnerCode);
					request.setWarehouseCode(Common.WareHouseCode);
					request.setSkuCodes(skuCodes);
					request.setPriority(priority);
					request.setStoredCode(storedCode);
					String json2 = JSON.toJSONString(request);
					String resultSearch2 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json2);
					JSONObject jsonSearch2 = new JSONObject(resultSearch2);
					if (jsonSearch2.optString("code").toString().equals("200")) {
						if ("null".equals(jsonSearch2.opt("result").toString())) {
							id = 0l;
							Message msg1 = new Message();
							msg1.what = 5;
							msg1.obj = "�ּ����";
							handler.sendMessage(msg1);
						} else {
							StandPickTaskResponse response = JSON.parseObject(
									jsonSearch2.optString("result"),
									StandPickTaskResponse.class);
							Message msg1 = new Message();
							msg1.what = 1;
							msg1.obj = response;
							handler.sendMessage(msg1);
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

	private void initEvent() {
		etBarCode.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						tvmsg.setText("");

						String code = etBarCode.getText().toString().trim();
						if (code.equals("")) {
							etBarCode.selectAll();
							Toaster.toaster("��ɨ������!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("��ɨ������!");
							return true;
						}
						etBarCode.setEnabled(false);
						getGoods(code);
						
						/*etnum.requestFocus();
						etnum.selectAll();*/
						break;
					case KeyEvent.ACTION_DOWN:
						break;
					}
					return true;
				}
				return false;
			}
		});

		etnum.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						sumbit();
						break;
					case KeyEvent.ACTION_DOWN:
						break;
					}
					return true;
				}
				return false;
			}
		});
	
		etStoreCode.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						
						tvmsg.setText("");
						String code = etStoreCode.getText().toString().trim();
						if (code.equals("")) {
							etStoreCode.selectAll();
							Toaster.toaster("��ɨ���ŵ�!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("��ɨ���ŵ�!");
							return true;
						}
						if(!storedCode.equals(""))
						{
							if(!storedCode.equals(code))
							{
								etStoreCode.requestFocus();
								etStoreCode.selectAll();
								
								Toaster.toaster("�ŵ�ɨ�����!");
								mediaPlayer.setVolume(1.0f, 1.0f);
								mediaPlayer.start();
								tvmsg.setVisibility(View.VISIBLE);
								tvmsg.setText("���ŵ�ɨ�����!");
								return true;
							}
						}
						etBarCode.requestFocus();
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
							msg.what = 7;
							msg.obj = "��ѯ������Ʒ��Ϣ";
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
							
							if(!skuCode.equals(curGoodsList.get(0).getSkuCode()))
							{
								Message msg = new Message();
								msg.what = 7;
								msg.obj = "��Ʒ����ɨ�����";
								handler.sendMessage(msg);
							}
							else
							{
								Message msg = new Message();
								msg.what = 6;
								msg.obj = curGoodsList;
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
					msg.obj = "�����쳣,������������";
					handler.sendMessage(msg);

				}
			}
		});
		mThread.start();
	}


	private void sumbit() {

		tvmsg.setText("");
		if (id == null || id == 0) {
			Toaster.toaster("û��Ҫ�ּ����Ʒ!");
			tvmsg.setText("û��Ҫ�ּ����Ʒ");
			return;
		}
		if (etBarCode.getText().toString().trim().equals("")) {
			Toaster.toaster("����ɨ����Ʒ!");
			tvmsg.setText("����ɨ����Ʒ");
			return;
		}
		if (etnum.getText().toString().trim().equals("")) {
			Toaster.toaster("����������!");
			tvmsg.setText("����������");
			return;
		}
		if (!isNumeric(etnum.getText().toString().trim())) {
			Toaster.toaster("����������!");
			tvmsg.setText("����������");
			return;
		}
		sortingNum = new BigDecimal(etnum.getText().toString().trim());
		int i = sortingNum.compareTo(BigDecimal.ZERO);
		if (i == 0 || i == -1) {
			Toaster.toaster("�����������0!");
			tvmsg.setText("�����������0");
			return;
		}
		if(skuCode.equals(""))
		{
			Toaster.toaster("û����ƷҪ�ּ�");
			tvmsg.setText("û����ƷҪ�ּ�");
			return;
		}
		
		//�жϵ�ǰ�����ͼƻ���
		if(sortedNum.add(sortingNum).compareTo(planNum)==1)
		{
			Toaster.toaster("���������ڼƻ���");
			tvmsg.setText("���������ڼƻ���");
			return;
		}
		
	
		if (etStoreCode.getText().toString().trim().equals("")) {
			etStoreCode.selectAll();
			Toaster.toaster("��ɨ���ŵ�!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("��ɨ���ŵ�!");
			return ;
		}
		if(storedCode!=null&&!storedCode.equals(""))
		{
			if(!storedCode.equals(etStoreCode.getText().toString().trim()))
			{
				etStoreCode.requestFocus();
				etStoreCode.selectAll();
				return ;
			}
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
							+ "/standPackTask/sumbitaaa";
					StandardSortingRequest request = new StandardSortingRequest();
					 request.setId(id);
					 request.setSkuCode(skuCode);
					 request.setBarCode(etBarCode.getText().toString().trim());
					 request.setSortingNum(sortingNum);
					String json2 = JSON.toJSONString(request);
					String resultSearch2 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json2);
					JSONObject jsonSearch2 = new JSONObject(resultSearch2);
					if (jsonSearch2.optString("code").toString().equals("200")) {
						Message msg = new Message();
						msg.what =8;
						msg.obj = jsonSearch2.optString("result").toString();
						handler.sendMessage(msg);
						
						/*Iterator<String> iter = skuCodes.iterator();
						while(iter.hasNext()){
				            String str= iter.next();
				            if(skuCode.equals(str)){
				                iter.remove();
				            }
				        }*/
						
						load(skuCodes);
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
				btnSure.setEnabled(true);
				btnSure.setText("ȷ��");
				etBarCode.setEnabled(true);
				StandPickTaskResponse response = (StandPickTaskResponse) msg.obj;
				tvProcess.setText(response.getFinishNum() + "/"
						+ response.getTotalNum());
				tvStoreName.setText(response.getStoredName());
				tvGoodsName.setText(response.getGoodsName());
				id = response.getId();
				skuCode = response.getSkuCode();
				etBarCode.setText("");
				etnum.setText("");
				planNum=response.getPlanNum();
				
				if(clickStoreFlag==0)
				{
					//����ŵ��һ�ν���
					etStoreCode.setText("");
					etStoreCode.requestFocus();
					etStoreCode.selectAll();
					clickStoreFlag=1;
				}
				else if(clickStoreFlag==1)
				{
					//����ŵ�ڶ��ν���
					
					if(storedCode.equals(response.getStoredCode()))
					{
						etBarCode.requestFocus();
						etBarCode.selectAll();
						etStoreCode.setText(storedCode);
					}
					else
					{
						etStoreCode.setText("");
						etStoreCode.requestFocus();
						etStoreCode.selectAll();
					}
					
				}
				else if(clickStoreFlag==2)
				{
					//�����ʼ�ּ�ť����
					if(storedCode==null||storedCode.equals(""))
					{
						etStoreCode.setText("");
						etStoreCode.requestFocus();
						etStoreCode.selectAll();
					}
					else
					{
						if(storedCode.equals(response.getStoredCode()))
						{
							etBarCode.requestFocus();
							etBarCode.selectAll();
							etStoreCode.setText(storedCode);
						}
						else
						{
							etStoreCode.setText("");
							etStoreCode.requestFocus();
							etStoreCode.selectAll();
						}
					}
				}
				storedCode=response.getStoredCode();
				sortedNum=response.getSortingNum();
				tvSortInfo.setText(response.getSortingNum()+"/"+response.getPlanNum().intValue());
				
				break;
			case 2:
				btnSure.setEnabled(true);
				btnSure.setText("ȷ��");
				etBarCode.setEnabled(true);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				Toaster.toaster(msg.obj.toString());
				etBarCode.selectAll();
				etBarCode.requestFocus();
				break;
			case 3:
				mediaPlayerOk.setVolume(1.0f, 1.0f);
				mediaPlayerOk.start();
				etBarCode.setEnabled(true);
				// �ύ�ɹ�
				Toaster.toaster(msg.obj.toString());
				finish();
				break;
			case 5:
				etBarCode.setEnabled(true);
				tvProcess.setText("");
				etBarCode.setText("");
				etnum.setText("");
				tvStoreName.setText("");
				tvGoodsName.setText("");
				Toaster.toaster(msg.obj.toString());
				finish();
				break;
			case 6:
				etBarCode.setEnabled(true);
				List<GoodsBarCode>  goodsList=(List<GoodsBarCode>)msg.obj;
				if(lastSkuCode.equals(""))
				{
					lastSkuCode=goodsList.get(0).getSkuCode();
				}
				if(!lastSkuCode.equals(goodsList.get(0).getSkuCode()))
				{
					lastSkuCode=goodsList.get(0).getSkuCode();
					etnum.setText("");
				}
				if(etnum.getText().toString().trim().equals(""))
				{
					etnum.setText("1");
					etBarCode.selectAll();
				}
				else
				{
					Integer num=Integer.valueOf(etnum.getText().toString()).intValue()+1;
					etnum.setText(num.toString());
					etBarCode.selectAll();
				}
				break;
			case 7:
				etBarCode.setEnabled(true);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				Toaster.toaster(msg.obj.toString());
				etBarCode.selectAll();
				etBarCode.requestFocus();
				etnum.setText("");
				break;
			case 8:
				btnSure.setEnabled(true);
				btnSure.setText("ȷ��");
				etBarCode.setEnabled(true);
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText("�ύ�ɹ�");
				Toaster.toaster("�ύ�ɹ�");
				etBarCode.setText("");
				etnum.setText("");
				tvSortInfo.setText(msg.obj.toString());
				
			/*	if(skuCodes.size()==0||skuCodes.size()==1)
				{
					tvmsg.setVisibility(View.VISIBLE);
					tvmsg.setText("�Ѿ������һ����Ʒ");
					Toaster.toaster("�Ѿ������һ����Ʒ");
					return;
				}*/
				etStoreCode.setText("");
				
				break;
			default:
				etBarCode.setEnabled(true);
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
