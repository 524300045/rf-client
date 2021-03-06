package com.wologic.ui;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
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
import com.wologic.domainnew.PackageAllDetail;
import com.wologic.request.PackageDetailRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class CancelPickerActivity extends Activity {

	private TextView tbBack;

	private MediaPlayer mediaPlayer;

	private EditText etbarcode;

	private TextView tvmsg, tvProcess, tvStoreName;;

	private Button btnSure;
	
	private MediaPlayer mediaPlayerOk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cancelpicker);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		mediaPlayer = MediaPlayer
				.create(CancelPickerActivity.this, R.raw.error);

		mediaPlayerOk = MediaPlayer.create(CancelPickerActivity.this, R.raw.ok);
		
		etbarcode = (EditText) findViewById(R.id.etbarcode);
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		mediaPlayer = MediaPlayer
				.create(CancelPickerActivity.this, R.raw.error);

		tvProcess = (TextView) findViewById(R.id.tvProcess);
		tvStoreName = (TextView) findViewById(R.id.tvStoreName);

		btnSure = (Button) findViewById(R.id.btnSure);
		initEvent();
	}

	private void initEvent() {
		etbarcode.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						tvmsg.setText("");
						tvmsg.setVisibility(View.GONE);
						String packageCode = etbarcode.getText().toString()
								.trim();
						if (packageCode.equals("")) {
							etbarcode.selectAll();
							Toaster.toaster("请扫描包裹号!");
							tvmsg.setText("请扫描包裹号!");
							tvmsg.setVisibility(View.VISIBLE);

							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();

							return true;
						}

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

		btnSure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				tvmsg.setText("");
				tvmsg.setVisibility(View.GONE);
				String packageCode = etbarcode.getText().toString().trim();
				if (packageCode.equals("")) {
					etbarcode.selectAll();
					Toaster.toaster("请扫描包裹号!");
					tvmsg.setText("请扫描包裹号!");
					tvmsg.setVisibility(View.VISIBLE);

					mediaPlayer.setVolume(1.0f, 1.0f);
					mediaPlayer.start();

					return;
				}
				sumbitNew(packageCode);
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

						/*
						 * List<PackageAllDetail> packageDetailList = JSON
						 * .parseArray(jsonSearch.optString("result"),
						 * PackageAllDetail.class);
						 */
						PackageAllDetail detail = JSON.parseObject(
								jsonSearch.optString("result"),
								PackageAllDetail.class);
						// storeCode=packageDetailList.get(0).getStoredCode();
                        if(detail==null)
                        {
                        	Message msg = new Message();
							msg.what = 2;
							msg.obj = "查询不打包裹的装箱信息";
							handler.sendMessage(msg);
                        }
                        else if (!detail.getStatus().equals(5)) {
							Message msg = new Message();
							msg.what = 2;
							msg.obj = "当前包裹还未包装";
							handler.sendMessage(msg);
						} else {
							Message msg = new Message();
							msg.what = 5;
							msg.obj = detail;
							handler.sendMessage(msg);
						}

					} else if (jsonSearch.optString("code").toString()
							.equals("302")) {
						Message msg = new Message();
						msg.what = 2;
						msg.obj = jsonSearch.optString("message");
						handler.sendMessage(msg);
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
					msg.obj = "网络异常,请检查网络连接";
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

	/**
	 * 包裹拆箱
	 */
	private void sumbitNew(final String packageCode) {
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
					packageDetailRequest.setPartnerCode(Common.partnerCode);
					packageDetailRequest.setWarehouseCode(Common.WareHouseCode);
					packageDetailRequest.setCustomerCode(Common.CustomerCode);
					
					
					String json = JSON.toJSONString(packageDetailRequest);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200")) {
						if (null == jsonSearch.optString("result")
								|| "null".equals(jsonSearch.opt("result")
										.toString())) {

							Message msg = new Message();
							msg.what = 2;
							msg.obj = "查询不到包裹信息";
							handler.sendMessage(msg);
						} else {

							PackageAllDetail detail = JSON.parseObject(
									jsonSearch.optString("result"),
									PackageAllDetail.class);
							// storeCode=packageDetailList.get(0).getStoredCode();

							if (!detail.getStatus().equals(5)) {
								Message msg = new Message();
								msg.what = 2;
								msg.obj = "当前包裹还未包装";
								handler.sendMessage(msg);
							} else {

								searchUrl = Constant.url
										+ "/packageDetail/cancelPickNew";

								PackageDetailRequest packageDetailRequest2 = new PackageDetailRequest();
								packageDetailRequest2
										.setPackageCode(packageCode);
								packageDetailRequest2
										.setCreateUser(Common.RealName);
								packageDetailRequest2.setPartnerCode(Common.partnerCode);
								packageDetailRequest2.setCustomerCode(Common.CustomerCode);
								packageDetailRequest2.setWarehouseCode(Common.WareHouseCode);
								
								String json2 = JSON
										.toJSONString(packageDetailRequest2);
								String resultSearch2 = com.wologic.util.SimpleClient
										.httpPost(searchUrl, json2);

								JSONObject jsonSearch2 = new JSONObject(
										resultSearch2);

								if (jsonSearch2.optString("code").toString()
										.equals("200")) {
									Message msg = new Message();
									msg.what = 1;
									msg.obj = "拆箱成功";
									handler.sendMessage(msg);

									// getProcess(packageCode);
								} else {
									Message msg = new Message();
									msg.what = 2;
									msg.obj = jsonSearch.optString("message");
									;
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
					msg.obj = "网络异常,请检查网络连接";
					handler.sendMessage(msg);
				}
			}
		});
		mThread.start();

	}

	private void sumbit(final String packageCode) {

		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/packageDetail/getPackageDetailByBoxCode";

					PackageDetailRequest packageDetailRequest = new PackageDetailRequest();
					packageDetailRequest.setBoxCode(packageCode);
					String json = JSON.toJSONString(packageDetailRequest);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200")) {
						// 提交
						searchUrl = Constant.url + "/packageDetail/cancelPickNew";
						PackageDetailRequest cancelPackageDetailRequest = new PackageDetailRequest();
						cancelPackageDetailRequest.setBoxCode(packageCode);
						cancelPackageDetailRequest.setPartnerCode(Common.partnerCode);
						cancelPackageDetailRequest.setCustomerCode(Common.CustomerCode);
						cancelPackageDetailRequest.setWarehouseCode(Common.WareHouseCode);
						
						
						String json2 = JSON
								.toJSONString(cancelPackageDetailRequest);
						String resultSearch2 = com.wologic.util.SimpleClient
								.httpPost(searchUrl, json2);
						JSONObject jsonSearch2 = new JSONObject(resultSearch2);
						if (jsonSearch2.optString("code").toString()
								.equals("200")) {

							Message msg = new Message();
							msg.what = 1;
							msg.obj = jsonSearch2.optString("message");
							handler.sendMessage(msg);
						} else {
							Message msg = new Message();
							msg.what = 2;
							msg.obj = jsonSearch2.optString("message");
							handler.sendMessage(msg);

						}

					} else if (jsonSearch.optString("code").toString()
							.equals("302")) {
						Message msg = new Message();
						msg.what = 2;
						msg.obj = jsonSearch.optString("message");
						handler.sendMessage(msg);
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

				mediaPlayerOk.setVolume(1.0f, 1.0f);
				mediaPlayerOk.start();
				etbarcode.setText("");
				etbarcode.selectAll();
				etbarcode.requestFocus();

				Toaster.toaster("取消分拣成功");
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);

				break;
			case 2:
				etbarcode.selectAll();
				etbarcode.requestFocus();

				Toaster.toaster(msg.obj.toString());
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				break;
			case 3:

				Toaster.toaster(msg.obj.toString());
				tvmsg.setText(msg.obj.toString());

				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				break;
			case 4:

				etbarcode.setText("");
				etbarcode.requestFocus();
				Toaster.toaster(msg.obj.toString());
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				break;
			case 5:
				/*
				 * List<PackageAllDetail> packageDetailList =
				 * (List<PackageAllDetail>) msg.obj; int totalNum = 0; int
				 * finishNum = 0;
				 * 
				 * if (packageDetailList != null) { totalNum =
				 * packageDetailList.size(); for (PackageAllDetail item :
				 * packageDetailList) { if (item.getStatus().equals(10)) {
				 * finishNum += 1; } }
				 * tvStoreName.setText(packageDetailList.get(0)
				 * .getStoredName()); }
				 */
				PackageAllDetail detail = (PackageAllDetail) msg.obj;
				etbarcode.selectAll();
				etbarcode.requestFocus();
				tvStoreName.setText(detail.getStoredName());
			case 6:
				tvProcess.setText(msg.obj.toString());
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

}
