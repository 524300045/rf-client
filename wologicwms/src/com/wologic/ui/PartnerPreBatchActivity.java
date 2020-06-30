package com.wologic.ui;

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
import com.wologic.domainnew.PackTaskDetail;
import com.wologic.domainnew.PackageAllDetail;
import com.wologic.domainnew.PreprocessInfo;
import com.wologic.request.BoxInfoRequest;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class PartnerPreBatchActivity extends Activity {

	private TextView tbBack;

	private EditText etbarcode;

	private EditText etBoxCode,etnum;

	private TextView tvmsg, tvProcess, tvStoreName, tvGoodsName, tvModel,
			tvWeight,tvWeightProcess,tvWaveName;

	private String storeCode, storeName,ousStockCode,packTaskCode,skuCode,goodsName;

	private Long taskDetailId;
	
	private String lastPackageCode;
	
	private MediaPlayer mediaPlayer;
	
	private String processInfo="";
	private String processWeightInfo="";
	
	private MediaPlayer mediaPlayerOk;
	
	private String waveName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_partnerprebatch);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				  Intent  data= new Intent();   
	              data.putExtra("returnmsg","");  
	              setResult(Activity.RESULT_OK,data);  
	              finish();  
			}
		});
		mediaPlayer = MediaPlayer.create(
				PartnerPreBatchActivity.this, R.raw.error);
		mediaPlayerOk=MediaPlayer.create(
				PartnerPreBatchActivity.this, R.raw.ok);
		
		tvWeightProcess=(TextView) findViewById(R.id.tvWeightProcess);
		
		Intent intent = getIntent();
		if (intent != null) {
			storeCode = intent.getStringExtra("storeCode");//�ŵ���
			taskDetailId = Long.valueOf(intent.getStringExtra("id"));//��װ������ϸID
			storeName = intent.getStringExtra("storeName");
			ousStockCode=intent.getStringExtra("ousStockCode");
			packTaskCode=intent.getStringExtra("packTaskCode");
			lastPackageCode=intent.getStringExtra("lastPackageCode");
			processInfo=intent.getStringExtra("processInfo");
			skuCode=intent.getStringExtra("skuCode");
			tvWeightProcess.setText(intent.getStringExtra("processWeightInfo"));
			waveName= intent.getStringExtra("waveName");//�ŵ���
		}

		tvProcess = (TextView) findViewById(R.id.tvProcess);
		tvStoreName = (TextView) findViewById(R.id.tvStoreName);
		tvGoodsName = (TextView) findViewById(R.id.tvGoodsName);
		tvModel = (TextView) findViewById(R.id.tvModel);
		tvWeight = (TextView) findViewById(R.id.tvWeight);
		tvWaveName= (TextView) findViewById(R.id.tvWaveName);
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etbarcode = (EditText) findViewById(R.id.etbarcode);
		etBoxCode = (EditText) findViewById(R.id.etBoxCode);
		etnum= (EditText) findViewById(R.id.etnum);
		
		tvStoreName.setText(storeName);
		tvWaveName.setText(waveName);
		tvProcess.setText(processInfo);
		
		tvGoodsName.setText("");
		tvModel.setText("");
		tvWeight.setText("");

		initEvent();
		etBoxCode.requestFocus();
		getProcessInfo(lastPackageCode);
	}
	
	
	private void getProcessInfo(final String lastPackageCode)
	{
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
							//��ѯԤ��װ��Ϣ
					    String searchUrl = Constant.url
									+ "/preprocessInfo/getPreprocessInfoByCode";
							
							PreprocessInfoRequest preprocessInfoRequest=new PreprocessInfoRequest();
							
							preprocessInfoRequest.setPreprocessCode(lastPackageCode);
							preprocessInfoRequest.setPartnerCode(Common.partnerCode);
							preprocessInfoRequest.setWarehouseCode(Common.WareHouseCode);
							preprocessInfoRequest.setCustomerCode(Common.CustomerCode);
							
							String json3=JSON.toJSONString(preprocessInfoRequest);
							String resultSearch3 = com.wologic.util.SimpleClient.httpPost(searchUrl, json3);
							JSONObject jsonSearch3 = new JSONObject(resultSearch3);
							if(jsonSearch3.optString("code").toString().equals("200"))
							{
								if(null==jsonSearch3.opt("result")||"null".equals(jsonSearch3.opt("result").toString()))
								{
									Message msg = new Message();
									msg.what =2;
									msg.obj ="��ѯ����������Ϣ" +
											"";
									handler.sendMessage(msg);
								}
								else
								{
									
									PreprocessInfo preprocessInfo=JSON.parseObject(jsonSearch3.optString("result"),PreprocessInfo.class);
									Message msg = new Message();
									msg.what =5;
									msg.obj =preprocessInfo;

									handler.sendMessage(msg);
								}
							}
						
					
					
				} catch (Exception e) {
					System.out.print(e.getMessage());
					Message msg = new Message();
					msg.what = 3;
					msg.obj =e.getMessage();
					handler.sendMessage(msg);
					
				}
			}
		});
		mThread.start();
		
	}

	private void initEvent() {

		etBoxCode.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						
						tvmsg.setVisibility(View.GONE);
						tvmsg.setText("");
						
						String boxCode = etBoxCode.getText().toString()
								.trim();
						if (boxCode.equals("")) {
							etBoxCode.selectAll();
							Toaster.toaster("��ɨ�����!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("��ɨ�����");
							return true;
						}
						etbarcode.requestFocus();
						break;
					case KeyEvent.ACTION_DOWN:
						break;
					}
					return true;
				}
				return false;
			}
		});

		etbarcode.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						
						tvmsg.setVisibility(View.GONE);
						tvmsg.setText("");
						
						String packageCode = etbarcode.getText().toString()
								.trim();
						if (packageCode.equals("")) {
							etbarcode.selectAll();
							Toaster.toaster("��ɨ�������!");
							
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("��ɨ�������!");
							
							return true;
						}
						
						
						etnum.requestFocus();
						etnum.selectAll();
						//sumbit();
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
						
						tvmsg.setVisibility(View.GONE);
						tvmsg.setText("");
						
						String numStr = etnum.getText().toString()
								.trim();
						if (numStr.equals("")) {
							etnum.selectAll();
							Toaster.toaster("��¼������!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("��¼������!");
							
							return true;
						}
						
						if (!isNumeric(etnum.getText().toString().trim())) {
							Toaster.toaster("����������!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("����������");
							return true;
						}
						Integer num=Integer.valueOf(numStr);
						if(num<=0)
						{
							Toaster.toaster("�����������0!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("�����������0");
							return true;
						}
						
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
	
	private void sumbit() {

		final String boxCode = etBoxCode.getText().toString().trim();

		if (storeCode.equals("")) {
			etBoxCode.selectAll();
			Toaster.toaster("��ɨ�������!");
			return;
		}

		final String packageCode = etbarcode.getText().toString().trim();
		if (packageCode.equals("")) {
			etbarcode.selectAll();
			Toaster.toaster("��ɨ�������!");
			return;
		}
		
		String numStr = etnum.getText().toString()
				.trim();
		final Integer num=Integer.valueOf(numStr);
		
		etnum.setEnabled(false);
		etbarcode.setEnabled(false);
		
		// �ж�����Ƿ������ڵ�ǰ�ŵ��

		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url + "/boxInfo/getBoxInfoCodeTwo";

					BoxInfoRequest boxInfoRequest = new BoxInfoRequest();
					boxInfoRequest.setBoxCode(boxCode);
					String json = JSON.toJSONString(boxInfoRequest);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200")) {
						if (null == jsonSearch.opt("result")
								|| "null" == jsonSearch.opt("result")
										.toString()) {
							Message msg = new Message();
							msg.what = 2;
							msg.obj = "��ѯ���������Ϣ";
							handler.sendMessage(msg);
						} 
						else if (jsonSearch.optString("code").toString().equals("302"))
						{
							Message msg = new Message();
							msg.what = 2;
							msg.obj =jsonSearch.optString("message");
							handler.sendMessage(msg);
						}
						else {
							// �ж�����Ƿ��Ѿ�ʹ��

							BoxInfo boxInfo = JSON.parseObject(
									jsonSearch.optString("result"),
									BoxInfo.class);
							if (!storeCode.equals(boxInfo.getStoredCode())) {
								Message msg = new Message();
								msg.what = 2;
								msg.obj = "��Ų����ڵ�ǰ�ŵ�";
								handler.sendMessage(msg);
							}
							else if(boxInfo.getOutboundTaskCode()!=null&&!ousStockCode.equals(boxInfo.getOutboundTaskCode()))
							{
								Message msg = new Message();
								msg.what = 2;
								msg.obj = "��ǰ�����Ѿ���������ռ��"+ousStockCode;
								handler.sendMessage(msg);
							}
							else {

								// ��ѯ������Ϣ
								searchUrl = Constant.url
										+ "/preprocessInfo/getPreprocessInfoByCode";
								PreprocessInfoRequest preprocessInfoRequest = new PreprocessInfoRequest();
								preprocessInfoRequest
										.setPreprocessCode(packageCode);
								preprocessInfoRequest.setPartnerCode(Common.partnerCode);
								
								preprocessInfoRequest.setWarehouseCode(Common.WareHouseCode);
								preprocessInfoRequest.setCustomerCode(Common.CustomerCode);
								
								
								String json2 = JSON
										.toJSONString(preprocessInfoRequest);
								String resultSearch2 = com.wologic.util.SimpleClient
										.httpPost(searchUrl, json2);

								
								JSONObject jsonSearch2 = new JSONObject(
										resultSearch2);
								if (jsonSearch2.optString("code").toString()
										.equals("200")) {
									if (null == jsonSearch2.opt("result")
											|| "null" == jsonSearch2.opt(
													"result").toString()) {
										Message msg = new Message();
										msg.what = 3;
										msg.obj = "��ѯ����������Ϣ";
										handler.sendMessage(msg);
									} else {
										PreprocessInfo preprocessInfo = JSON
												.parseObject(jsonSearch2
														.optString("result"),
														PreprocessInfo.class);
										if(!preprocessInfo.getSkuCode().equals(skuCode))
										{
											Message msg = new Message();
											msg.what = 3;
											msg.obj = "��װ������ɨ��["+goodsName+"]�İ�װ";
											handler.sendMessage(msg);
										}
										else if (preprocessInfo.getStatus() == 1) {
											Message msg = new Message();
											msg.what = 3;
											msg.obj = "�˰�����װ��";
											handler.sendMessage(msg);
										} else {

											// �ύ

											searchUrl = Constant.url
													+ "/packageDetail/partnerPreInput";
											PackageDetailRequest packageDetailRequest = new PackageDetailRequest();

											packageDetailRequest.setInputNum(num);
											packageDetailRequest.setWarehouseCode(Common.WareHouseCode);
											packageDetailRequest.setOutboundTaskCode(ousStockCode);
											packageDetailRequest.setPackTaskCode(packTaskCode);
											packageDetailRequest.setPackTaskDetailId(taskDetailId);
											packageDetailRequest.setSkuCode(preprocessInfo.getSkuCode());
											packageDetailRequest.setWeight(preprocessInfo.getPackWeight());
											packageDetailRequest.setPackageCode(preprocessInfo.getPreprocessCode());
											packageDetailRequest.setBoxCode(boxCode);
											packageDetailRequest.setProcessUser(Common.RealName);
											packageDetailRequest.setCreateUser(Common.RealName);
											packageDetailRequest.setUpdateUser(Common.RealName);
											packageDetailRequest.setPartnerCode(Common.partnerCode);
											packageDetailRequest.setGoodsName(goodsName);
											String json3 = JSON
													.toJSONString(packageDetailRequest);
											String resultSearch3 = com.wologic.util.SimpleClient
													.httpPost(searchUrl, json3);

											JSONObject jsonSearch3 = new JSONObject(
													resultSearch3);
											if (jsonSearch3.optString("code").toString()
													.equals("200")) 
											{
												if(null!=jsonSearch3.optString("result"))
												{
												    String[] arr=jsonSearch3.optString("result").split("@");
												    if(arr!=null&&arr.length==2)
												    {
												    	processInfo=arr[0];
														processWeightInfo=arr[1];
												    }
													
												}
												
												Message msg = new Message();
												msg.what = 4;
												msg.obj = "װ��ɹ�";
												handler.sendMessage(msg);
											}
											else if (jsonSearch3.optString("code").toString()
													.equals("100")) 
											{
											
												Message msg = new Message();
												msg.what = 6;
												msg.obj = "װ������Ѵﵽ�ŵ�����";
												handler.sendMessage(msg);
												
											}
											else if(jsonSearch3.optString("code").toString()
													.equals("301"))
											{
												Message msg = new Message();
												msg.what = 3;
												msg.obj = jsonSearch3.optString("message");
												handler.sendMessage(msg);
											}
											else
											{
												Message msg = new Message();
												msg.what = 3;
												msg.obj = jsonSearch3.optString("message");
												handler.sendMessage(msg);
											}
											
										}

									}
								} else {
									Message msg = new Message();
									msg.what = 2;
									msg.obj = jsonSearch2.optString("message");
									handler.sendMessage(msg);
								}

							}

						}

					} else {

						Message msg = new Message();
						msg.what = 2;
						msg.obj = jsonSearch.optString("message");
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

		// �жϰ������Ƿ�ʹ��

	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				etnum.setEnabled(true);
				etbarcode.setEnabled(true);
				etBoxCode.requestFocus();
				break;
			case 2:
				etnum.setEnabled(true);
				etbarcode.setEnabled(true);
				etBoxCode.selectAll();
				etBoxCode.requestFocus();
				Toaster.toaster(msg.obj.toString());
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				break;
			case 3:
				etnum.setEnabled(true);
				etbarcode.setEnabled(true);
				etbarcode.selectAll();
				etbarcode.requestFocus();
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				break;
			case 4:
				etnum.setEnabled(true);
				etbarcode.setEnabled(true);
				tvProcess.setText(processInfo);
				
				tvWeightProcess.setText(processWeightInfo);
				etbarcode.setText("");
				etbarcode.selectAll();
				etbarcode.requestFocus();
				Toaster.toaster(msg.obj.toString());
				mediaPlayerOk.setVolume(1.0f, 1.0f);
				mediaPlayerOk.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				break;
			case 5:
				etnum.setEnabled(true);
				etbarcode.setEnabled(true);
				PreprocessInfo preprocessInfo=(PreprocessInfo)msg.obj;
				tvGoodsName.setText(preprocessInfo.getGoodsName());
				goodsName=preprocessInfo.getGoodsName();
				tvModel.setText(preprocessInfo.getModelNum().toString());
				tvWeight.setText(preprocessInfo.getPackWeight().toString());
				break;
			case 6:
				etnum.setEnabled(true);
				etbarcode.setEnabled(true);
				  Intent  data= new Intent();   
	              data.putExtra("returnmsg","");  
	              setResult(Activity.RESULT_OK,data);  
	              finish();  
	              
				break;
			default:
				etnum.setEnabled(true);
				etbarcode.setEnabled(true);
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
