package com.wologic.ui;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.domainnew.WmsInventoryDetail;
import com.wologic.request.WmsInventoryDetailRequest;
import com.wologic.request.WmsInventoryQuery;
import com.wologic.ui.ContentAdapterInventory.Callback;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.Toaster;

public class InventoryThreeActivity extends Activity implements OnItemClickListener,Callback {

	private TextView tbBack,tvmsg,tvAreaName,tvName;
	private MediaPlayer mediaPlayer;
	private Button btnSure;
	private String orderNo,areaName;
	private List<WmsInventoryDetail> list;
	private ListView lvdetail;
	List<Map<String, Object>> mapnoendList;

	int year = 2016;
    int month = 10;
    int day = 8;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory_three);
		tbBack = (TextView) findViewById(R.id.tvback);
		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		mediaPlayer = MediaPlayer.create(InventoryThreeActivity.this,
				R.raw.error);
		
		tvmsg = (TextView) findViewById(R.id.tvmsg);
		tvAreaName= (TextView) findViewById(R.id.tvAreaName);
		tvName=(TextView) findViewById(R.id.tvName);
		lvdetail = (ListView) findViewById(R.id.lvdetail);
		
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
		
		 Calendar   c   =   Calendar.getInstance();  
         c.setTime(new Date());  
         year= c.get(Calendar.YEAR);  
         month=c.get(Calendar.MONTH)+1;
         day= c.get(Calendar.DAY_OF_MONTH);
         
		getNextDetail();
	}

	private void getNextDetail() {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/wmsInventory/getNextInventorySkuDetail";
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
							//关闭
							Message msg = new Message();
							msg.what =1;
							msg.obj ="";
							handler.sendMessage(msg);
						}
						else
						{
							
							Message msg = new Message();
							msg.what = 4;
							msg.obj = list;
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
				Toaster.toaster(msg.obj.toString());
				getNextDetail();
				break;
			case 4:
				 List<WmsInventoryDetail> skuList=(List<WmsInventoryDetail>)msg.obj;
				 tvName.setText(skuList.get(0).getGoodsName()+"("+skuList.get(0).getGoodsModel()+")");
				  bindList();
				 btnSure.setEnabled(true);
				break;
			default:
				
				break;
			}
		}
	};

	
	private void bindList() {
		 mapnoendList = new ArrayList<Map<String, Object>>();
		if (null != list) {
			for (WmsInventoryDetail item : list) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("skucode",  item.getSkuCode());
				map.put("goodsName",item.getGoodsName());
				map.put("currentStock",item.getCurrentStock()+item.getGoodsUnit());
				map.put("id", item.getId());
				if(item.getProductionDate()!=null)
				{
					SimpleDateFormat format = new SimpleDateFormat(
	                        "yyyy-MM-dd");
	                String  date=format.format(item.getProductionDate());
					map.put("productionDate", date);
				}
				else
				{
					map.put("productionDate", "");
				}
				mapnoendList.add(map);
			}
		}
	    lvdetail.setAdapter(new ContentAdapterInventory(this, mapnoendList,this));
		lvdetail.setOnItemClickListener(this);
	}
	
	
	
	private void sumbit() {
		tvmsg.setText("");
		String msg="";
		final List<WmsInventoryDetailRequest> inventoryList=new ArrayList<WmsInventoryDetailRequest>();
		
        for(int i=0;i<lvdetail.getChildCount();i++)
        {
        	LinearLayout layout = (LinearLayout)lvdetail.getChildAt(i);// 获得子item的layout
        	EditText et = (EditText)layout.findViewById(R.id.etNum);// 从layout中获得控件,根据其id
        	TextView tvId = (TextView)layout.findViewById(R.id.tvId);
        	TextView tvDate = (TextView)layout.findViewById(R.id.dialog_tv_date);
        	String num=et.getText().toString().trim();
        	if(num.equals(""))
        	{
        		msg="请录入数量!";
        		break;
        	}
        	if(tvDate.getText().toString().trim().equals(""))
        	{
        		msg="请选择日期!";
        		break;
        	}
        	WmsInventoryDetailRequest detail=new WmsInventoryDetailRequest();
        	detail.setId(Long.valueOf(tvId.getText().toString()));
        	detail.setInventoryNum(new BigDecimal(num));
        	detail.setInventoryUser(Common.UserName);
        	detail.setStatus(20);
        	
        	SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd");
        	try {
				detail.setInventoryProductionDate(format.parse(tvDate.getText().toString().trim()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	inventoryList.add(detail);
        }
        if(!msg.equals(""))
        {
        	Toaster.toaster(msg);
			return;
        }
        if(inventoryList==null||inventoryList.size()==0)
        {
        	return;
        }
        //提交数据库
        btnSure.setEnabled(false);
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();
					String searchUrl = Constant.url + "/wmsInventory/saveNew";
					String json = JSON.toJSONString(inventoryList);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200"))
					{
							Message msg = new Message();
							msg.what =3;
							msg.obj = "采集成功";
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
	}





	@Override
	public void click(View v) {
		 if(v.getId()==R.id.btnDate)
		 {
			 LinearLayout layout = (LinearLayout)v.getParent();
			 final TextView tv=(TextView)layout.findViewById(R.id.dialog_tv_date);
			 final TextView tvDate=(TextView)((View)v.getParent()).findViewById(R.id.dialog_tv_date);
			 final TextView tvId=(TextView)((View)v.getParent()).findViewById(R.id.tvId);
			 final String str=tvId.getText().toString();
			// final TextView tvDate=(TextView)findViewById(R.id.dialog_tv_date);
			 new DatePickerDialog(InventoryThreeActivity.this, new DatePickerDialog.OnDateSetListener() {
		            @Override
		            public void onDateSet(DatePicker view, int year, int monthOfYear,
		                    int dayOfMonth) {
		            	InventoryThreeActivity.this.year = year;
		                month = monthOfYear+1;
		                day = dayOfMonth;
		                tvDate.setText(year+"-"+month+"-"+day);
		                for(Map<String,Object> map:mapnoendList)
		                {
		                	long id=Long.valueOf(String.valueOf(map.get("id")));
		                	
		                	if(Long.valueOf(str).equals(id))
		                	{
		                		map.put("productionDate",tvDate.getText());
		                	}
		                }
		                
		            }
		        }, year, month-1, day).show();
		 }
	}





	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	};

}
