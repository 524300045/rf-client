package com.wologic.ui;

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
import com.wologic.request.GoodsBarcodeRequest;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class BarCodeScanActivity extends Activity {

	private TextView tbBack;
	private EditText etbarcode;
	private TextView tvmsg, tvSkuCode, tvName;
	private Button btnSure;
	
	private String skuCode,goodsName;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerOk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_barcodescan);
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
				BarCodeScanActivity.this, R.raw.error);
		mediaPlayerOk=MediaPlayer.create(
				BarCodeScanActivity.this, R.raw.ok);
		tvSkuCode = (TextView) findViewById(R.id.tvSkuCode);
		tvName = (TextView) findViewById(R.id.tvName);
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etbarcode = (EditText) findViewById(R.id.etBarCode);
		btnSure=(Button) findViewById(R.id.btnSure);
		Intent intent = getIntent();
		if (intent != null) {
			skuCode = intent.getStringExtra("skucode");//门店编号
			goodsName = intent.getStringExtra("name");
			tvSkuCode.setText(skuCode);
			tvName.setText(goodsName);
		}
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				sumbit();
			}});
			
		etbarcode.requestFocus();

	}
	
	
	private void sumbit() {

		tvmsg.setText("");
		final String barCode = etbarcode.getText().toString().trim();
		if (barCode.equals("")) {
			etbarcode.selectAll();
			Toaster.toaster("请扫描条码!");
			return;
		}
		etbarcode.setEnabled(false);
		btnSure.setEnabled(false);
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();
					String searchUrl = Constant.url + "/goods/addBarCode";
					GoodsBarcodeRequest request = new GoodsBarcodeRequest();
					request.setBarCode(barCode);
					request.setSkuCode(skuCode);
					request.setStatus(1);
					request.setYn(1);
					request.setCreateUser(Common.UserName);
					request.setUpdateUser(Common.UserName);
					
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						if (null == jsonSearch.opt("result")
								|| "".equals(jsonSearch.opt("result")
										.toString()))
						{
							Message msg = new Message();
							msg.what = 1;
							msg.obj = "采集成功";
							handler.sendMessage(msg);
						} 
						else
						{
							Message msg = new Message();
							msg.what = 2;
							msg.obj =jsonSearch.opt("result")
									.toString();
							handler.sendMessage(msg);
						}
					} else {
						Message msg = new Message();
						msg.what = 2;
						msg.obj = jsonSearch.opt("result");
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
				etbarcode.setEnabled(true);
				etbarcode.setText("");
				btnSure.setEnabled(true);
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				break;
			case 2:
				etbarcode.setEnabled(true);
				btnSure.setEnabled(true);
				Toaster.toaster(msg.obj.toString());
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				etbarcode.selectAll();
				etbarcode.requestFocus();
				break;
			default:
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
