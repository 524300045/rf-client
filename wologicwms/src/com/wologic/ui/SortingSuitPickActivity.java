package com.wologic.ui;

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
import com.wologic.domainnew.ContainerSkuRel;
import com.wologic.domainnew.GoodsBarCode;
import com.wologic.request.ContainerSkuRelRequest;
import com.wologic.request.GoodsBarcodeRequest;
import com.wologic.request.StandSkuTaskRequest;
import com.wologic.request.StandardSortingRequest;
import com.wologic.request.SuitSortingRequest;
import com.wologic.response.StandPickTaskResponse;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class SortingSuitPickActivity extends Activity {

	private TextView tbBack;
	private EditText  etboxcode,etStoreCode;//etBarCode
	private Button btnSure, btnShow,btnNext;
	private TextView tvmsg, tvProcess, tvStoreName, tvGoodsName,tvSortInfo,tvPhyUnit,tvContainerRemainNum;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerOk;
	private List<GoodsBarCode> goodsList;
	List<String> skuCodes = null;
	
	private String lastSkuCode="";

	private String storedCode;
	/** id */
	private Long id;
	
	private List<Long> ids;
	
	//private BigDecimal sortingNum;
	private String skuCode;
	  private BigDecimal planNum;
	  private BigDecimal sortedNum; //�ѷּ���
	private Integer priority;
	
	private Integer sortflag;//�����������־
	
	private int clickStoreFlag=0;
	
	private String containerCode;
	
	private List<String> waveCodeList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sortingsuitpick);
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
			sortflag=intent.getIntExtra("sortflag", 0);
			containerCode=intent.getStringExtra("containerCode");
			waveCodeList=(List<String>)this.getIntent().getSerializableExtra("waveCodeList");
		}
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		mediaPlayer = MediaPlayer.create(SortingSuitPickActivity.this, R.raw.error);
		mediaPlayerOk = MediaPlayer.create(SortingSuitPickActivity.this, R.raw.ok);
		tvProcess = (TextView) findViewById(R.id.tvProcess);
		tvStoreName = (TextView) findViewById(R.id.tvStoreName);
		tvGoodsName = (TextView) findViewById(R.id.tvGoodsName);
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		
		etStoreCode=(EditText) findViewById(R.id.etStoreCode);
		tvPhyUnit= (TextView) findViewById(R.id.tvPhyUnit);
		tvContainerRemainNum=(TextView) findViewById(R.id.tvContainerRemainNum);
		etboxcode = (EditText) findViewById(R.id.etboxcode);
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

				Intent intent = new Intent(SortingSuitPickActivity.this,
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
							+ "/standPackTask/getStandTaskNew";
					StandSkuTaskRequest request = new StandSkuTaskRequest();
					request.setCustomerCode(Common.CustomerCode);
					//request.setPartnerCode(Common.partnerCode);
					request.setWarehouseCode(Common.WareHouseCode);
					request.setSkuCodes(skuCodes);
					request.setPriority(priority);
					request.setStoredCode(storedCode);
					request.setSortflag(sortflag);//�����־
					
					request.setWaveCodeList(waveCodeList);
					String json2 = JSON.toJSONString(request);
					String resultSearch2 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json2);
					JSONObject jsonSearch2 = new JSONObject(resultSearch2);
					if (jsonSearch2.optString("code").toString().equals("200"))
					{
						if ("null".equals(jsonSearch2.opt("result").toString()))
						{
							//�ж��Ƿ��������
							if(null!=storedCode&&!storedCode.equals(""))
							{
								storedCode="";
								String searchUrl2 = Constant.url
										+ "/standPackTask/getStandTaskSuit";
								StandSkuTaskRequest request2 = new StandSkuTaskRequest();
								request2.setCustomerCode(Common.CustomerCode);
								//request.setPartnerCode(Common.partnerCode);
								request2.setWarehouseCode(Common.WareHouseCode);
								request2.setSkuCodes(skuCodes);
								request2.setPriority(priority);
								request2.setStoredCode(storedCode);
								request2.setSortflag(sortflag);//�����־
								request2.setWaveCodeList(waveCodeList);
								
								String json3 = JSON.toJSONString(request2);
								String resultSearch3 = com.wologic.util.SimpleClient
										.httpPost(searchUrl2, json3);
								JSONObject jsonSearch3 = new JSONObject(resultSearch3);
								if (jsonSearch3.optString("code").toString().equals("200"))
								{
									if ("null".equals(jsonSearch3.opt("result").toString()))
									{
										id = 0l;
										Message msg1 = new Message();
										msg1.what = 5;
										msg1.obj = "�ּ����";
										handler.sendMessage(msg1);
									}
									else
									{
										StandPickTaskResponse response = JSON.parseObject(
												jsonSearch3.optString("result"),
												StandPickTaskResponse.class);
										Message msg1 = new Message();
										msg1.what = 1;
										msg1.obj = response;
										handler.sendMessage(msg1);
										//getContainerSku(response.getSkuCode(),containerCode);
									}
								}
							}
							else
							{
								id = 0l;
								Message msg1 = new Message();
								msg1.what = 5;
								msg1.obj = "�ּ����";
								handler.sendMessage(msg1);
							}
							//�Ƿ��������
							
						} else {
							StandPickTaskResponse response = JSON.parseObject(
									jsonSearch2.optString("result"),
									StandPickTaskResponse.class);
							Message msg1 = new Message();
							msg1.what = 1;
							msg1.obj = response;
							handler.sendMessage(msg1);
							
							//getContainerSku(response.getSkuCode(),containerCode);
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
		

		etboxcode.setOnKeyListener(new OnKeyListener() {

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
						etboxcode.requestFocus();
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
		
		
		if (ids == null || ids.size() == 0) {
			Toaster.toaster("û��Ҫ�ּ����Ʒ!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("û��Ҫ�ּ����Ʒ");
			return;
		}
		
		
		if (etboxcode.getText().toString().trim().equals("")) {
			Toaster.toaster("ɨ������!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("ɨ������");
			return;
		}
		
		final String boxCode=etboxcode.getText().toString().trim();
		
		if(skuCode.equals(""))
		{
			Toaster.toaster("û����ƷҪ�ּ�");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("û����ƷҪ�ּ�");
			return;
		}
		
		//�жϵ�ǰ�����ͼƻ���
		if(sortedNum.add(new BigDecimal(1)).compareTo(planNum)==1)
		{
			Toaster.toaster("���������ڼƻ���");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
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
							+ "/standPackTask/sumbitSuit";
					SuitSortingRequest request = new SuitSortingRequest();
					 request.setIds(ids);
					 request.setSkuCode(skuCode);
					 request.setBarCode(skuCode);
					 request.setSortingNum(new BigDecimal(1));
					 request.setWarehouseCode(Common.WareHouseCode);
					 request.setUpdateUser(Common.UserName);
					 request.setContainerCode(containerCode);
					 request.setCustomerName(Common.CustomerName);
					 request.setCustomerCode(Common.CustomerCode);
					 request.setBoxCode(boxCode);
					 
					String json2 = JSON.toJSONString(request);
					String resultSearch2 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json2);
					JSONObject jsonSearch2 = new JSONObject(resultSearch2);
					if (jsonSearch2.optString("code").toString().equals("200")) {
						Message msg = new Message();
						msg.what =8;
						msg.obj = jsonSearch2.optString("result").toString();
						handler.sendMessage(msg);
						
						
						
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
				//etBarCode.setEnabled(true);
				StandPickTaskResponse response = (StandPickTaskResponse) msg.obj;
				tvProcess.setText(response.getFinishNum() + "/"
						+ response.getTotalNum());
				tvStoreName.setText(response.getStoredName());
				tvGoodsName.setText(response.getGoodsName());
				tvPhyUnit.setText(response.getPhysicsUnit());
				id = response.getId();
				ids=response.getIds();
				skuCode = response.getSkuCode();
				//etBarCode.setText("");
				
				etboxcode.setText("");
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
					/*	etBarCode.requestFocus();
						etBarCode.selectAll();*/
						etboxcode.requestFocus();
						etboxcode.selectAll();
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
							/*etBarCode.requestFocus();
							etBarCode.selectAll();*/
							etboxcode.requestFocus();
							etboxcode.selectAll();
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
				getContainerSku(response.getSkuCode(),containerCode);
				break;
			case 2:
				btnSure.setEnabled(true);
				btnSure.setText("ȷ��");
				//etBarCode.setEnabled(true);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				Toaster.toaster(msg.obj.toString());
				//etBarCode.selectAll();
				//etBarCode.requestFocus();
				break;
			case 3:
				mediaPlayerOk.setVolume(1.0f, 1.0f);
				mediaPlayerOk.start();
				//etBarCode.setEnabled(true);
				// �ύ�ɹ�
				Toaster.toaster(msg.obj.toString());
				finish();
				break;
			case 5:
				//etBarCode.setEnabled(true);
				tvProcess.setText("");
				//etBarCode.setText("");
				etboxcode.setText("");
				tvStoreName.setText("");
				tvGoodsName.setText("");
				Toaster.toaster(msg.obj.toString());
				finish();
				break;
			case 6:
				//etBarCode.setEnabled(true);
				List<GoodsBarCode>  goodsList=(List<GoodsBarCode>)msg.obj;
				if(lastSkuCode.equals(""))
				{
					lastSkuCode=goodsList.get(0).getSkuCode();
				}
				if(!lastSkuCode.equals(goodsList.get(0).getSkuCode()))
				{
					lastSkuCode=goodsList.get(0).getSkuCode();
					etboxcode.setText("");
				}
				if(etboxcode.getText().toString().trim().equals(""))
				{
					//etnum.setText("1");
					//etBarCode.selectAll();
				}
				else
				{
//					Integer num=Integer.valueOf(etnum.getText().toString()).intValue()+1;
//					etnum.setText(num.toString());
					//etBarCode.selectAll();
				}
				break;
			case 7:
				//etBarCode.setEnabled(true);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				Toaster.toaster(msg.obj.toString());
				//etBarCode.selectAll();
				//etBarCode.requestFocus();
				etboxcode.requestFocus();
				etboxcode.selectAll();
				etboxcode.setText("");
				break;
			case 8:
				btnSure.setEnabled(true);
				btnSure.setText("ȷ��");
				//etBarCode.setEnabled(true);
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText("�ύ�ɹ�");
				Toaster.toaster("�ύ�ɹ�");
				//etBarCode.setText("");
				etboxcode.setText("");
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
				
			case 9:
				ContainerSkuRel rel=(ContainerSkuRel)msg.obj;
				BigDecimal remainNum=rel.getPickingNum().subtract(rel.getSortingNum());
				tvContainerRemainNum.setText(remainNum.toString());
				break;
			case 10:
				tvContainerRemainNum.setText("0");
				break;
			default:
				//etBarCode.setEnabled(true);
				break;
			}
		}
	};

	
	private void getContainerSku(final String skuCode,String contaierCode) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/containerSkuRel/getContainerSkuRelInfo";
					ContainerSkuRelRequest  request=new ContainerSkuRelRequest();
					request.setContainerCode(containerCode);
					request.setSkuCode(skuCode);
					request.setWarehouseCode(Common.WareHouseCode);
					request.setCustomerCode(Common.CustomerCode);
					
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
							msg.what = 10;
							msg.obj = "��ѯ����������Ʒ��Ϣ";
							handler.sendMessage(msg);
						} 
						else
						{
							ContainerSkuRel  containerSkuRel = JSON
									.parseObject(
											jsonSearch
													.opt("result")
													.toString(),
													ContainerSkuRel.class);
							
						
								Message msg = new Message();
								msg.what = 9;
								msg.obj = containerSkuRel;
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
