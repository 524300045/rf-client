package com.wologic.ui;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.application.MyApplication;
import com.wologic.domainnew.BoxInfo;
import com.wologic.domainnew.PackTaskDetail;
import com.wologic.domainnew.PackageAllDetail;
import com.wologic.domainnew.PreprocessInfo;
import com.wologic.domainnew.WareHouse;
import com.wologic.domainnew.WarehouseArea;
import com.wologic.request.BoxInfoRequest;
import com.wologic.request.GoodsBarcodeRequest;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PmsOrderPurchaseReceiveDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.request.WarehouseAreaRequest;
import com.wologic.request.WarehouseInfoRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class PurchaseAcceptEndActivity extends Activity {

	private TextView tbBack;
	private EditText etNum,etLife;
	private TextView tvmsg, tvSkuCode, tvName,tvRemain,tvReal;
	private Button btnSure;
	
	private String skuCode,goodsName;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerOk;
	private String orderNo;
	private String realNum,remainNum;
	
	private Spinner spinner;
	
	private ArrayAdapter<WarehouseArea> arr_adapter;
	
	private List<WarehouseArea> warehouseAreaList;
	
	private  String areaCode;
	private String areaName;
	
	private DatePicker datePicker;
	
	private String productDate="";
	
	private long detailId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase_accept_end);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				 /* Intent  data= new Intent();   
	              data.putExtra("returnmsg","");  
	              setResult(Activity.RESULT_OK,data);  */
	              finish();  
			}
		});
		
		datePicker = (DatePicker) findViewById(R.id.dpPicker);
		
		datePicker.init(2013, 8, 20, new OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year,
                    int monthOfYear, int dayOfMonth) {
                // 获取一个日历对象，并初始化为当前选中的时间
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy-MM-dd");
                Toast.makeText(PurchaseAcceptEndActivity.this,
                        format.format(calendar.getTime()), Toast.LENGTH_SHORT)
                        .show();
                
                productDate=format.format(calendar.getTime());
            }
        });

		
		tvSkuCode = (TextView) findViewById(R.id.tvSkuCode);
		tvName = (TextView) findViewById(R.id.tvName);
		tvRemain = (TextView) findViewById(R.id.tvRemain);
		tvReal = (TextView) findViewById(R.id.tvReal);
	
		
		Intent intent = getIntent();
		if (intent != null) {
			orderNo = intent.getStringExtra("orderNo");
			skuCode= intent.getStringExtra("skuCode");
			realNum=intent.getStringExtra("realNum");
			remainNum=intent.getStringExtra("remainNum");
			goodsName=intent.getStringExtra("name");
			detailId=Long.valueOf(intent.getStringExtra("detailId")) ;
		
		}
	
		tvSkuCode.setText(skuCode);
		tvName.setText(goodsName);
		tvRemain.setText(String.valueOf(remainNum));
		tvReal.setText(String.valueOf(realNum));
		
		mediaPlayer = MediaPlayer.create(
				PurchaseAcceptEndActivity.this, R.raw.error);
		mediaPlayerOk=MediaPlayer.create(
				PurchaseAcceptEndActivity.this, R.raw.ok);
		
		
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etNum = (EditText) findViewById(R.id.etNum);
		etLife = (EditText) findViewById(R.id.etLife);
		
		btnSure=(Button) findViewById(R.id.btnSure);
		
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				sumbit();
			}});
		getwarearea();
		etNum.requestFocus();

	}
	
	
	private void sumbit() {

		tvmsg.setText("");
		final String num = etNum.getText().toString().trim();
		if (num.equals("")) {
			etNum.selectAll();
			Toaster.toaster("请录入数量!");
			return;
		}
		final String lifeTime = etLife.getText().toString().trim();
		if (num.equals("")) {
			etNum.selectAll();
			Toaster.toaster("请录入保质期!");
			return;
		}
		if(productDate.equals(""))
		{
			Toaster.toaster("请录入生产日期!");
			return;
		}
		if(areaCode.equals(""))
		{
			Toaster.toaster("请选择上架库区!");
			return;
		}
		
		btnSure.setEnabled(false);
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();
					String searchUrl = Constant.url + "/pmsOrderPurchaseReceiveDetail/save";
					PmsOrderPurchaseReceiveDetailRequest request = new PmsOrderPurchaseReceiveDetailRequest();
					request.setDetailId(detailId);
					request.setOrderNo(orderNo);
					request.setSkuCode(skuCode);
					request.setGoodsName(goodsName);
					request.setReceiveNum(new BigDecimal(num));
					
					 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					 try {
							Date dt=sdf.parse(productDate+" 00:00:00");
							request.setProductionDate(dt);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					request.setExpiryDate(Double.valueOf(lifeTime));
					request.setCustomerCode(Common.CustomerCode);
					request.setWarehouseCode(Common.WareHouseCode);
					request.setWarehouseName(Common.WareHouseName);
					request.setAreaCode(areaCode);
					request.setAreaName(areaName);
					request.setOrderState(0);
					
					request.setReceiveUser(Common.UserName);
					
					request.setCreateUser(Common.UserName);
					request.setUpdateUser(Common.UserName);
					
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						Message msg = new Message();
						msg.what = 1;
						msg.obj = "成功";
						handler.sendMessage(msg);
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
				/*etbarcode.setEnabled(true);
				etbarcode.setText("");*/
				etNum.setText("");
				etLife.setText("");
				btnSure.setEnabled(true);
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
				break;
			case 2:
				//etbarcode.setEnabled(true);
				btnSure.setEnabled(true);
				Toaster.toaster(msg.obj.toString());
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				tvmsg.setVisibility(View.VISIBLE);
				tvmsg.setText(msg.obj.toString());
			/*	etbarcode.selectAll();
				etbarcode.requestFocus();*/
				break;
			case 3:
				warehouseAreaList = (List<WarehouseArea>) msg.obj;
				bindwareArea();
				break;
			default:
				//etbarcode.setEnabled(true);
				break;
			}
		}
	};
	
	
	private void bindwareArea() {
		spinner = (Spinner) findViewById(R.id.spinner);
		// 适配器
		arr_adapter = new ArrayAdapter<WarehouseArea>(this,
				android.R.layout.simple_spinner_item, warehouseAreaList);
		// 设置样式
		arr_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 加载适配器
		spinner.setAdapter(arr_adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				 areaCode = ((WarehouseArea) spinner.getSelectedItem())
						.getAreaCode();
				 areaName = ((WarehouseArea) spinner.getSelectedItem())
							.getAreaName();
				
				/*Toast.makeText(getApplicationContext(), String.valueOf(ids),
						Toast.LENGTH_LONG).show();*/
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
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

	
	private void getwarearea() {

		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/warehouseArea/getWarehouseArea";

					WarehouseAreaRequest request = new WarehouseAreaRequest();
					request.setWarehouseCode(Common.WareHouseCode);
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200")) {

						List<WarehouseArea> wareAreaList = JSON.parseArray(
								jsonSearch.optString("result"), WarehouseArea.class);
						Message msg = new Message();
						msg.what = 3;
						msg.obj = wareAreaList;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {

				}
			}
		});
		mThread.start();

	}
}
