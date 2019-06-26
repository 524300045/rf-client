package com.wologic.ui;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domainnew.GoodsBarCode;
import com.wologic.domainnew.PackTaskDetail;
import com.wologic.domainnew.PackageAllDetail;
import com.wologic.domainnew.PreprocessInfo;
import com.wologic.domainnew.WarehouseAreaPickProcess;
import com.wologic.domainnew.WmsInventoryDetail;
import com.wologic.request.GoodsBarcodeRequest;
import com.wologic.request.GoodsQueryRequest;
import com.wologic.request.PackTaskDetailRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.PreprocessInfoRequest;
import com.wologic.request.WmsInventoryDetailRequest;
import com.wologic.request.WmsInventoryQuery;
import com.wologic.response.WmsInventoryTaskResponse;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class InventoryTwoActivity extends Activity {

	private TextView tbBack,tvmsg,tvAreaName;
	private EditText etNum;
	private MediaPlayer mediaPlayer;
	//private DatePicker datePicker;
	private String productDate="";
	private Button btnSure;
	private String areaName;
	
	private String areaCode;
	
	private String skuCode;
	
	
	private TextView tvName,tvNum,tvId;
	

	
	private Long id;
	
	private Button btnDate;
	
	private TextView dialog_tv_date;
	
	private String orderNo;

	private String goodsName;
	
	int year = 2016;
    int month = 10;
    int day = 8;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory_two);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		mediaPlayer = MediaPlayer.create(InventoryTwoActivity.this,
				R.raw.error);
		
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		etNum = (EditText) findViewById(R.id.etNum);
		tvAreaName= (TextView) findViewById(R.id.tvAreaName);
		tvName=(TextView) findViewById(R.id.tvName);
		tvNum=(TextView) findViewById(R.id.tvNum);
		//tvProductDate=(TextView) findViewById(R.id.tvProductDate);
		tvId=(TextView) findViewById(R.id.tvId);
		
		dialog_tv_date= (TextView) findViewById(R.id.dialog_tv_date);
		btnDate= (Button) findViewById(R.id.btnDate);
		
		
		initEvent();
		etNum.requestFocus();
		
		Intent intent = getIntent();
		if (intent != null) {;
			areaName= intent.getStringExtra("areaName");
			skuCode= intent.getStringExtra("skuCode");
			areaCode= intent.getStringExtra("areaCode");
			orderNo=intent.getStringExtra("orderNo");
			goodsName=intent.getStringExtra("goodsName");
		}
		tvName.setText(goodsName);
		tvAreaName.setText(areaName);
		btnSure=(Button) findViewById(R.id.btnSure);
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				sumbit();
			}});
		
		btnDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			
				new DatePickerDialog(InventoryTwoActivity.this, new DatePickerDialog.OnDateSetListener() {

		            @Override
		            public void onDateSet(DatePicker view, int year, int monthOfYear,
		                    int dayOfMonth) {
		            	InventoryTwoActivity.this.year = year;
		                month = monthOfYear+1;
		                day = dayOfMonth;
		                dialog_tv_date.setText(year+"-"+month+"-"+day);
		                
		                Calendar calendar = Calendar.getInstance();
		                calendar.set(year, monthOfYear, day);
		                SimpleDateFormat format = new SimpleDateFormat(
		                        "yyyy-MM-dd");
		                productDate=format.format(calendar.getTime());
		            }
		        }, year, month-1, day).show();
				
			}
			
			
		});
		
		
		// getNextDetail();
	}



	private void initEvent() {
		etNum.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
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


	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
            case 1:
				Toaster.toaster("盘点完成");
				finish();
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
            	btnSure.setEnabled(true);
				Toaster.toaster("新增成功");
				
				Intent  data= new Intent(); 
	              data.putExtra("productdate",productDate);
	              data.putExtra("num",etNum.getText().toString().trim());
	              data.putExtra("id",msg.obj.toString());
				setResult(Activity.RESULT_OK,data);
				finish();
				break;
			case 4:
				etNum.setText("");
				WmsInventoryDetail item=(WmsInventoryDetail)msg.obj;
				tvName.setText(item.getGoodsName()+"("+item.getGoodsModel()+")");
				tvNum.setText(item.getCurrentStock()+item.getGoodsUnit());
				//tvProductDate.setText("");
				if(item.getProductionDate()!=null)
				{
					  SimpleDateFormat format = new SimpleDateFormat(
		                        "yyyy-MM-dd");
		               String  date=format.format(item.getProductionDate());
					 //  tvProductDate.setText(date);
					   dialog_tv_date.setText(date);
					   productDate=date;
				}
				id=item.getId();
				 btnSure.setEnabled(true);
				break;
			default:
				
				break;
			}
		}
	};

	boolean isProcess=false;
	private void sumbit() {

		tvmsg.setText("");
		final String num = etNum.getText().toString().trim();
		if (num.equals("")) {
			etNum.selectAll();
			Toaster.toaster("请录入数量!");
			return;
		}
		
		if(productDate.equals(""))
		{
			Toaster.toaster("请选择日期!");
			return;
		}
	
		if(isProcess)
		{
			return;
		}
		isProcess=true;
		
		btnSure.setEnabled(false);
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();
					String searchUrl = Constant.url + "/wmsInventory/saveSingDetil";
					WmsInventoryDetailRequest request = new WmsInventoryDetailRequest();
					request.setInventoryOrderNo(orderNo);
					request.setSkuCode(skuCode);
					request.setStatus(10);
					BigDecimal bd=new BigDecimal(num);   
					request.setInventoryNum(bd);
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					request.setInventoryProductionDate(sdf.parse(productDate));
					request.setInventoryUser(Common.UserName);
					
					
					
					
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						isProcess=false;
							Message msg = new Message();
							msg.what =3;
							msg.obj =jsonSearch.opt("message");
							handler.sendMessage(msg);
						
					} else {
						isProcess=false;
						Message msg = new Message();
						msg.what = 2;
						msg.obj = jsonSearch.opt("message");
						handler.sendMessage(msg);
					}

				} catch (Exception e) {
					isProcess=false;
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

	protected void onDestroy() {
		super.onDestroy();
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	};

}
