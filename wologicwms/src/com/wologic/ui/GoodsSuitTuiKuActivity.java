package com.wologic.ui;

import org.apache.http.client.HttpClient;
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
import com.wologic.domainnew.OutBoundSuitDetailVo;
import com.wologic.request.GoodsSuitBoxRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class GoodsSuitTuiKuActivity extends Activity {

	private TextView tbBack,tvmsg;
	private EditText etBox;
	private MediaPlayer mediaPlayer,mediaPlayerOk;
	
	private Button btnSure;

	private TextView tvStoreName,tvDeliveryDate,tvOutBoundTaskCode;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goodssuit_tuiku);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		mediaPlayer = MediaPlayer.create(GoodsSuitTuiKuActivity.this,
				R.raw.error);
		mediaPlayerOk=MediaPlayer.create(GoodsSuitTuiKuActivity.this,
				R.raw.ok);
		
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etBox = (EditText) findViewById(R.id.etboxcode);
		
    	tvStoreName=(TextView) findViewById(R.id.tvStoreName);
		tvDeliveryDate=(TextView) findViewById(R.id.tv_DeliveryDate);
		tvOutBoundTaskCode= (TextView) findViewById(R.id.tvOutBoundTaskCode);
		
		etBox.requestFocus();
		initEvent();
		
		btnSure=(Button) findViewById(R.id.btnSure);
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				sumbit();
			}});
		
		
	}

	private void initEvent() {

		etBox.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						
						tvmsg.setVisibility(View.GONE);
						tvmsg.setText("");
						
						String boxCode = etBox.getText().toString()
								.trim();
						if (boxCode.equals("")) {
							etBox.selectAll();
							Toaster.toaster("«Î…®√Ëœ‰¬Î!");
							mediaPlayer.setVolume(1.0f, 1.0f);
							mediaPlayer.start();
							tvmsg.setVisibility(View.VISIBLE);
							tvmsg.setText("«Î…®√Ëœ‰¬Î");
							return true;
						}
						getBoxInfo(boxCode);
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
	
	
	private void getBoxInfo(final String boxCode) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/goodsSuitBox/getgoodssuitboxoutinfo";
					GoodsSuitBoxRequest request = new GoodsSuitBoxRequest();;
					request.setBoxCode(boxCode);
					request.setCustomerCode(Common.CustomerCode);
					request.setWarehouseCode(Common.WareHouseCode);
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						OutBoundSuitDetailVo vo=JSON.parseObject(jsonSearch
								.opt("result").toString(), OutBoundSuitDetailVo.class);
							Message msg = new Message();
							msg.what =1;
							msg.obj =vo;
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
					msg.obj = "Õ¯¬Á“Ï≥£,«ÎºÏ≤ÈÕ¯¬Á¡¨Ω”";
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
            	OutBoundSuitDetailVo item=(OutBoundSuitDetailVo)msg.obj;
            	tvStoreName.setText(item.getStoredName());
            	tvDeliveryDate.setText(item.getDeliveryDate().toString());
            	tvOutBoundTaskCode.setText(item.getOutboundTaskCode());
				break;
			case 2:
				
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				Toaster.toaster(msg.obj.toString());
				 btnSure.setEnabled(true);
				break;
				
			  case 3:
				  mediaPlayerOk.setVolume(1.0f, 1.0f);
				  mediaPlayerOk.start();
				  
				  tvmsg.setText("≥…π¶");
	            	etBox.selectAll();
	            	tvStoreName.setText("");
	            	tvDeliveryDate.setText("");
	            	tvOutBoundTaskCode.setText("");
					break;
           
			default:
				
				break;
			}
		}
	};

	
	private void sumbit() {

		tvmsg.setText("");
		
		final String boxCode = etBox.getText().toString()
				.trim();
		if (boxCode.equals("")) {
			etBox.selectAll();
			Toaster.toaster("«Î…®√Ëœ‰¬Î!");
			mediaPlayer.setVolume(1.0f, 1.0f);
			mediaPlayer.start();
			tvmsg.setVisibility(View.VISIBLE);
			tvmsg.setText("«Î…®√Ëœ‰¬Î");
			return ;
		}
		
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/goodsSuitBox/sumbithuiku";
					GoodsSuitBoxRequest request = new GoodsSuitBoxRequest();;
					request.setBoxCode(boxCode);
					request.setCustomerCode(Common.CustomerCode);
					request.setWarehouseCode(Common.WareHouseCode);
					request.setCreateUser(Common.UserName);
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						
							Message msg = new Message();
							msg.what =3;
							msg.obj ="";
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
					msg.obj = "Õ¯¬Á“Ï≥£,«ÎºÏ≤ÈÕ¯¬Á¡¨Ω”";
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
