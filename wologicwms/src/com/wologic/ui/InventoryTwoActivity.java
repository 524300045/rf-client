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
	private DatePicker datePicker;
	private String productDate="";
	private Button btnSure;
	private String orderNo,areaName;
	
	private TextView tvName,tvNum,tvProductDate,tvId;
	
	private List<WmsInventoryDetail> list;
	
	private Long id;

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
		tvProductDate=(TextView) findViewById(R.id.tvProductDate);
		tvId=(TextView) findViewById(R.id.tvId);
		
		initEvent();
		etNum.requestFocus();
		
	     datePicker = (DatePicker) findViewById(R.id.dpPicker);
		datePicker.init(2013, 8, 20, new OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year,
                    int monthOfYear, int dayOfMonth) {
                // ��ȡһ���������󣬲���ʼ��Ϊ��ǰѡ�е�ʱ��
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy-MM-dd");
                Toast.makeText(InventoryTwoActivity.this,
                        format.format(calendar.getTime()), Toast.LENGTH_SHORT)
                        .show();
                
                productDate=format.format(calendar.getTime());
            }
        });

		Intent intent = getIntent();
		if (intent != null) {
			orderNo = intent.getStringExtra("orderNo");
			areaName= intent.getStringExtra("areaName");
			
		}
		tvAreaName.setText(areaName);
		btnSure=(Button) findViewById(R.id.btnSure);
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				sumbit();
			}});
		getNextDetail();
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

	private void getNextDetail() {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/wmsInventory/getNextInventoryDetail";
					WmsInventoryQuery  request=new WmsInventoryQuery();
					request.setWmsInventoryOrderNo(orderNo);

					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
						list = JSON
								.parseArray(
										jsonSearch
												.opt("result")
												.toString(),
												WmsInventoryDetail.class);
						if(list==null||list.size()==0)
						{
							//�ر�
							Message msg = new Message();
							msg.what =1;
							msg.obj ="";
							handler.sendMessage(msg);
						}
						else
						{
							
							Message msg = new Message();
							msg.what = 4;
							msg.obj = list.get(0);
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

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
            case 1:
				Toaster.toaster("�̵����");
				finish();
				break;
			case 2:
				
				tvmsg.setText(msg.obj.toString());
				tvmsg.setVisibility(View.VISIBLE);
				mediaPlayer.setVolume(1.0f, 1.0f);
				mediaPlayer.start();
				Toaster.toaster(msg.obj.toString());
				
				break;
             case 3:
				
				Toaster.toaster(msg.obj.toString());
				getNextDetail();
				break;
			case 4:
				etNum.setText("");
				WmsInventoryDetail item=(WmsInventoryDetail)msg.obj;
				tvName.setText(item.getGoodsName()+"("+item.getGoodsModel()+")");
				tvNum.setText(item.getCurrentStock()+item.getGoodsUnit());
				tvProductDate.setText("");
				if(item.getProductionDate()!=null)
				{
					  SimpleDateFormat format = new SimpleDateFormat(
		                        "yyyy-MM-dd");
		               String  date=format.format(item.getProductionDate());
					   tvProductDate.setText(date);
				}
				id=item.getId();
				
				break;
			default:
				
				break;
			}
		}
	};

	
	private void sumbit() {

		tvmsg.setText("");
		final String num = etNum.getText().toString().trim();
		if (num.equals("")) {
			etNum.selectAll();
			Toaster.toaster("��¼������!");
			return;
		}
		
		if(productDate.equals(""))
		{
			Toaster.toaster("��ѡ������!");
			return;
		}
	
		
		btnSure.setEnabled(false);
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();
					String searchUrl = Constant.url + "/wmsInventory/save";
					WmsInventoryDetailRequest request = new WmsInventoryDetailRequest();
					
					request.setStatus(20);
					request.setId(id);
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
					
							Message msg = new Message();
							msg.what =3;
							msg.obj = "�ɼ��ɹ�";
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
	};

}