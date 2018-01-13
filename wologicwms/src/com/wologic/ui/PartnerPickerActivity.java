package com.wologic.ui;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domainnew.PackTaskDetail;
import com.wologic.domainnew.PackageAllDetail;
import com.wologic.domainnew.PreprocessInfo;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class PartnerPickerActivity extends Activity {

	private TextView tbBack;

	private EditText etPackageCode;

	private TextView tvmsg, tvGoodsName, tvProcess;

	private ListView lvnoend;

	private List<PackTaskDetail> packTaskDetailList;

	private LinearLayout ll, llprocess;

	private MediaPlayer mediaPlayer;

	private String goodsName;

	private String goodsCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_partnerpicker);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		mediaPlayer = MediaPlayer.create(PartnerPickerActivity.this,
				R.raw.error);
		ll = (LinearLayout) findViewById(R.id.ll);
		llprocess = (LinearLayout) findViewById(R.id.llprocess);
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		tvGoodsName = (TextView) findViewById(R.id.tvGoodsName);
		tvProcess = (TextView) findViewById(R.id.tvProcess);
		etPackageCode = (EditText) findViewById(R.id.etPackageCode);
		lvnoend = (ListView) findViewById(R.id.lvnoend);
		initEvent();
		bindList();
		etPackageCode.requestFocus();

	}

	private void bindList() {
		List<Map<String, Object>> mapnoendList = new ArrayList<Map<String, Object>>();
		int total = 0;
		int finishNum = 0;
		if (null != packTaskDetailList) {
			List<PackTaskDetail> finishTaskList = new ArrayList<PackTaskDetail>();
			List<PackTaskDetail> notFinishTaskList = new ArrayList<PackTaskDetail>();
			for (PackTaskDetail item : packTaskDetailList) {

				int curtotal = 0;
				if (null != item.getWeighed() && item.getWeighed() == 1) {
					double d = Math
							.ceil((item.getPlanNum().doubleValue() / item
									.getModelNum().doubleValue()));
					curtotal = (int) d;
				} else {
					curtotal = item.getPlanNum().intValue();
				}
				if (item.getFinishNum().equals(curtotal)) {
					finishTaskList.add(item);// 完成的任务
				} else {
					notFinishTaskList.add(item);
				}
			}

            int m=1;
			for (PackTaskDetail item : notFinishTaskList) {
				Map<String, Object> map = new HashMap<String, Object>();
				int curtotal = 0;
				if (null != item.getWeighed() && item.getWeighed() == 1) {
					double d = Math
							.ceil((item.getPlanNum().doubleValue() / item
									.getModelNum().doubleValue()));
					curtotal = (int) d;
					if(item.getPackageWeight()==null)
					{
						map.put("weightProcess", "0/"+item.getPlanNum()+"斤");
					}
					else
					{
						map.put("weightProcess", item.getPackageWeight()+"/"+item.getPlanNum()+"斤");
					}
				} else {
					curtotal = item.getPlanNum().intValue();
					if(item.getPackageWeight()==null)
					{
						map.put("weightProcess", "0/"+curtotal+item.getPhysicsUnit());
					}
					else
					{
						map.put("weightProcess", item.getFinishNum()+"/"+curtotal+item.getPhysicsUnit());
					}
				}

				map.put("seq", m);
				map.put("id", item.getId());
				map.put("storeCode", item.getStoredCode());
				map.put("storeName", item.getStoredName());
				map.put("num", item.getFinishNum() + "/" + curtotal);
				map.put("outstockcode", item.getOutboundTaskCode());
				map.put("taskcode", item.getPackTaskCode());
				mapnoendList.add(map);
				total += curtotal;
				finishNum += item.getFinishNum();
				m++;
			}

			for (PackTaskDetail item : finishTaskList) {
				Map<String, Object> map = new HashMap<String, Object>();
				int curtotal = 0;
				if (null != item.getWeighed() && item.getWeighed() == 1) {
					double d = Math
							.ceil((item.getPlanNum().doubleValue() / item
									.getModelNum().doubleValue()));
					curtotal = (int) d;
					map.put("weightProcess", item.getPackageWeight()+"/"+item.getPlanNum()+"斤");
				} else {
					curtotal = item.getPlanNum().intValue();
					map.put("weightProcess", item.getFinishNum()+"/"+curtotal+item.getPhysicsUnit());
				}
				map.put("seq", m);
				map.put("id", item.getId());
				map.put("storeCode", item.getStoredCode());
				map.put("storeName", item.getStoredName());
				map.put("num", item.getFinishNum() + "/" + curtotal);
				map.put("outstockcode", item.getOutboundTaskCode());
				map.put("taskcode", item.getPackTaskCode());
				mapnoendList.add(map);
				total += curtotal;
				finishNum += item.getFinishNum();
				m++;
			}

			llprocess.setVisibility(View.VISIBLE);
		}
		
		SpecialAdapter adp = new SpecialAdapter(this, mapnoendList,
				R.layout.listitemstore, new String[] {"seq", "id", "storeCode",
						"storeName", "num", "outstockcode", "taskcode","weightProcess" },
				new int[] {R.id.tvSeq, R.id.tvId, R.id.tvStoreCode, R.id.tvStoreName,
						R.id.tvnum, R.id.tvoutstockcode, R.id.tvtaskCode,R.id.tvWeightProcess });
		lvnoend.setAdapter(adp);
		tvProcess.setText(finishNum + "/" + total);
	}

	private void initEvent() {
		etPackageCode.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						tvmsg.setText("");
						tvmsg.setVisibility(View.GONE);
						String packageCode = etPackageCode.getText().toString()
								.trim();
						if (packageCode.equals("")) {
							etPackageCode.selectAll();
							Toaster.toaster("请扫描包裹号!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("请扫描包裹号!");

							return true;
						}
						etPackageCode.setEnabled(false);
						getPackageDetail(packageCode);
						break;
					case KeyEvent.ACTION_DOWN:
						break;
					}
					return true;
				}
				return false;
			}
		});

		lvnoend.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView tvid = (TextView) arg1.findViewById(R.id.tvId);
				TextView tvStoreCode = (TextView) arg1
						.findViewById(R.id.tvStoreCode);
				TextView tvStoreName = (TextView) arg1
						.findViewById(R.id.tvStoreName);
				TextView tvOutStockCode = (TextView) arg1
						.findViewById(R.id.tvoutstockcode);
				TextView tvTaskCode = (TextView) arg1
						.findViewById(R.id.tvtaskCode);

				TextView tvWeightProcess=(TextView) arg1
						.findViewById(R.id.tvWeightProcess);
				
				TextView tvProcess = (TextView) arg1.findViewById(R.id.tvnum);

				Intent intent = new Intent(PartnerPickerActivity.this,
						PartnerPreActivity.class);
				intent.putExtra("id", tvid.getText());// 传递入库单号
				intent.putExtra("storeCode", tvStoreCode.getText());// 传递入库单号
				intent.putExtra("storeName", tvStoreName.getText());// 传递入库单号
				intent.putExtra("ousStockCode", tvOutStockCode.getText());// 传递入库单号
				intent.putExtra("packTaskCode", tvTaskCode.getText());// 传递入库单号
				intent.putExtra("lastPackageCode", etPackageCode.getText()
						.toString());
				intent.putExtra("processInfo", tvProcess.getText().toString());
				intent.putExtra("skuCode", goodsCode);
				intent.putExtra("processWeightInfo", tvWeightProcess.getText().toString());
				if (!tvProcess.getText().toString().equals("")) {
					String[] splitArr = tvProcess.getText().toString()
							.split("/");
					if (splitArr.length == 2) {

						int finishNum = Integer.valueOf(splitArr[0]);
						int totalNum = Integer.valueOf(splitArr[1]);
						if (finishNum >= totalNum) {
							Toaster.toaster("当前门店已经装箱完成");
							return;
						}

					}
				}

				startActivityForResult(intent, 1);

			}
		});

	}

	private void getPackageDetail(final String packageCode) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/packageDetail/getPackageDetailByPackageCode";

					PackageDetailRequest packageDetailRequest = new PackageDetailRequest();
					packageDetailRequest.setPackageCode(packageCode);
					packageDetailRequest.setCustomerCode(Common.CustomerCode);
					packageDetailRequest.setWarehouseCode(Common.WareHouseCode);
					packageDetailRequest.setPartnerCode(Common.partnerCode);
					
					String json = JSON.toJSONString(packageDetailRequest);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200")) {
						if (null == jsonSearch.optString("result")
								|| "null".equals(jsonSearch.opt("result")
										.toString())) {
							// 判断预包装表中是否存在, 查询预包装信息
							searchUrl = Constant.url
									+ "/preprocessInfo/getPreprocessInfoByCode";

							PreprocessInfoRequest preprocessInfoRequest = new PreprocessInfoRequest();
							preprocessInfoRequest
									.setPreprocessCode(packageCode);

							preprocessInfoRequest
									.setPartnerCode(Common.partnerCode);

							String json3 = JSON
									.toJSONString(preprocessInfoRequest);
							String resultSearch3 = com.wologic.util.SimpleClient
									.httpPost(searchUrl, json3);
							JSONObject jsonSearch3 = new JSONObject(
									resultSearch3);
							if (jsonSearch3.optString("code").toString()
									.equals("200")) {
								if (null == jsonSearch3.opt("result")
										|| "null".equals(jsonSearch3.opt(
												"result").toString())) {
									Message msg = new Message();
									msg.what = 2;
									msg.obj = "查询不到包裹信息" + "";
									handler.sendMessage(msg);
								} else {

									PreprocessInfo preprocessInfo = JSON
											.parseObject(jsonSearch3
													.optString("result"),
													PreprocessInfo.class);
									if (preprocessInfo.getStatus() == 1) {

										Message msg = new Message();
										msg.what = 2;
										msg.obj = "当前包裹已扫描过";
										handler.sendMessage(msg);
									} else {
										// 加载门店需求信息
										String skuCode = preprocessInfo
												.getSkuCode();
										searchUrl = Constant.url
												+ "/packTaskDetail/getPackTaskDetailListBySkuCodeAndStatus";

										PackTaskDetailRequest packTaskDetailRequest = new PackTaskDetailRequest();
										packTaskDetailRequest
												.setSkuCode(skuCode);
										packTaskDetailRequest
												.setPartnerCode(Common.partnerCode);
										
										packTaskDetailRequest.setCustomerCode(Common.CustomerCode);
										
										packTaskDetailRequest.setWarehouseCode(Common.WareHouseCode);
										
										
										String json4 = JSON
												.toJSONString(packTaskDetailRequest);
										String resultSearch4 = com.wologic.util.SimpleClient
												.httpPost(searchUrl, json4);
										JSONObject jsonSearch4 = new JSONObject(
												resultSearch4);
										if (jsonSearch4.optString("code")
												.toString().equals("200")) {
											if (null == jsonSearch4
													.opt("result")
													|| "null"
															.equals(jsonSearch4
																	.opt("result")
																	.toString())) {
												Message msg = new Message();
												msg.what = 2;
												msg.obj = "获取不到门店需求信息";
												handler.sendMessage(msg);
											} else {
												packTaskDetailList = JSON
														.parseArray(
																jsonSearch4
																		.opt("result")
																		.toString(),
																PackTaskDetail.class);
												if (packTaskDetailList != null
														&& packTaskDetailList
																.size() > 0) {
													goodsCode = packTaskDetailList
															.get(0)
															.getSkuCode();
													goodsName = packTaskDetailList
															.get(0)
															.getGoodsName()
															+ "  "
															+ packTaskDetailList
																	.get(0)
																	.getModelNum()
															+ packTaskDetailList
																	.get(0)
																	.getGoodsUnit()
															+ "/"
															+ packTaskDetailList
																	.get(0)
																	.getPhysicsUnit();
													// 绑定门店列表
													Message msg = new Message();
													msg.what = 4;
													msg.obj = "";
													handler.sendMessage(msg);
												} else {
													Message msg = new Message();
													msg.what = 2;
													msg.obj = "获取不到门店需求信息";
													handler.sendMessage(msg);

												}
											}
										} else {
											Message msg = new Message();
											msg.what = 2;
											msg.obj = jsonSearch3
													.optString("message");
											handler.sendMessage(msg);
										}

									}
								}
							} else {
								Message msg = new Message();
								msg.what = 2;
								msg.obj = jsonSearch3.optString("message");
								handler.sendMessage(msg);
							}

						} else {
							PackageAllDetail detail = JSON.parseObject(
									jsonSearch.optString("result"),
									PackageAllDetail.class);
							if (detail.getStatus() >= 5) {
								Message msg = new Message();
								msg.what = 2;
								msg.obj = "包裹已经装箱过";
								handler.sendMessage(msg);

								// 查询任务明细
								searchUrl = Constant.url
										+ "/preprocessInfo/getPreprocessInfoByCode";

								PreprocessInfoRequest preprocessInfoRequest = new PreprocessInfoRequest();
								preprocessInfoRequest
										.setPreprocessCode(packageCode);

								preprocessInfoRequest
										.setPartnerCode(Common.partnerCode);

								String json3 = JSON
										.toJSONString(preprocessInfoRequest);
								String resultSearch3 = com.wologic.util.SimpleClient
										.httpPost(searchUrl, json3);
								JSONObject jsonSearch3 = new JSONObject(
										resultSearch3);
								if (jsonSearch3.optString("code").toString()
										.equals("200")) {
									if (null == jsonSearch3.opt("result")
											|| "null".equals(jsonSearch3.opt(
													"result").toString())) {

									} else {

										PreprocessInfo preprocessInfo = JSON
												.parseObject(jsonSearch3
														.optString("result"),
														PreprocessInfo.class);

										// 加载门店需求信息
										String skuCode = preprocessInfo
												.getSkuCode();
										searchUrl = Constant.url
												+ "/packTaskDetail/getPackTaskDetailListBySkuCodeAndStatus";

										PackTaskDetailRequest packTaskDetailRequest = new PackTaskDetailRequest();
										packTaskDetailRequest
												.setSkuCode(skuCode);
										packTaskDetailRequest
												.setPartnerCode(Common.partnerCode);
										packTaskDetailRequest.setCustomerCode(Common.CustomerCode);
										packTaskDetailRequest.setWarehouseCode(Common.WareHouseCode);
										
										String json4 = JSON
												.toJSONString(packTaskDetailRequest);
										String resultSearch4 = com.wologic.util.SimpleClient
												.httpPost(searchUrl, json4);
										JSONObject jsonSearch4 = new JSONObject(
												resultSearch4);
										if (jsonSearch4.optString("code")
												.toString().equals("200")) {
											if (null == jsonSearch4
													.opt("result")
													|| "null"
															.equals(jsonSearch4
																	.opt("result")
																	.toString())) {

											} else {
												packTaskDetailList = JSON
														.parseArray(
																jsonSearch4
																		.opt("result")
																		.toString(),
																PackTaskDetail.class);
												if (packTaskDetailList != null
														&& packTaskDetailList
																.size() > 0) {
													goodsCode = packTaskDetailList
															.get(0)
															.getSkuCode();
													goodsName = packTaskDetailList
															.get(0)
															.getGoodsName()
															+ "  "
															+ packTaskDetailList
																	.get(0)
																	.getModelNum()
															+ packTaskDetailList
																	.get(0)
																	.getGoodsUnit()
															+ "/"
															+ packTaskDetailList
																	.get(0)
																	.getPhysicsUnit();
													// 绑定门店列表
													Message msg3 = new Message();
													msg3.what = 4;
													msg3.obj = "";
													handler.sendMessage(msg3);
												} else {

												}
											}
										}

									}
								}

							} else {
								// 跳转到扫描箱号界面
								Message msg = new Message();
								msg.what = 1;
								msg.obj = detail;
								handler.sendMessage(msg);
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
					msg.what = 3;
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
			case 1:
				etPackageCode.setEnabled(true);
				PackageAllDetail detail = (PackageAllDetail) msg.obj;
				// 绑定
				Intent intent = new Intent(PartnerPickerActivity.this,
						PartnerOrderActivity.class);
				intent.putExtra("packagecode", detail.getPackageCode());// 传递入库单号
				intent.putExtra("packageId", detail.getId());
				intent.putExtra("storeCode", detail.getStoredCode());
				intent.putExtra("outBoundTaskCode", detail.getOutboundTaskCode());
				startActivityForResult(intent, 0);
				break;
			case 2:
				etPackageCode.setEnabled(true);
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				Toaster.toaster(msg.obj.toString());
				etPackageCode.selectAll();
				etPackageCode.requestFocus();
				break;
			case 3:
				etPackageCode.setEnabled(true);
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				etPackageCode.selectAll();
				etPackageCode.requestFocus();
				break;
			case 4:
				etPackageCode.setEnabled(true);
				tvGoodsName.setText(goodsName);
				ll.setVisibility(View.VISIBLE);
				bindList();
				break;
			default:
				etPackageCode.setEnabled(true);
				break;
			}
		}
	};

	// 接受页面的返回值
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		etPackageCode.selectAll();
		etPackageCode.requestFocus();
		if (requestCode == 1) {
			if (resultCode == Activity.RESULT_OK) {
				loadData(goodsCode);
			}

		}
	}

	private void loadData(final String skuCode) {

		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					String searchUrl = Constant.url
							+ "/packTaskDetail/getPackTaskDetailListBySkuCodeAndStatus";

					PackTaskDetailRequest packTaskDetailRequest = new PackTaskDetailRequest();
					packTaskDetailRequest.setSkuCode(skuCode);
					packTaskDetailRequest.setPartnerCode(Common.partnerCode);
					packTaskDetailRequest.setCustomerCode(Common.CustomerCode);
					packTaskDetailRequest.setWarehouseCode(Common.WareHouseCode);
					
					String json4 = JSON.toJSONString(packTaskDetailRequest);
					String resultSearch4 = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json4);
					JSONObject jsonSearch4 = new JSONObject(resultSearch4);
					if (jsonSearch4.optString("code").toString().equals("200")) {
						if (null == jsonSearch4.opt("result")
								|| "null".equals(jsonSearch4.opt("result")
										.toString())) {

						} else {
							packTaskDetailList = JSON.parseArray(jsonSearch4
									.opt("result").toString(),
									PackTaskDetail.class);

							Message msg = new Message();
							msg.what = 4;
							msg.obj = "";
							handler.sendMessage(msg);

						}
					} else {

					}

				} catch (Exception e) {

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
