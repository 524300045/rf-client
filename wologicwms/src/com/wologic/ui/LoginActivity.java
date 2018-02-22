package com.wologic.ui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wologic.R;
import com.wologic.application.MyApplication;

import com.wologic.domainnew.CustomerInfo;
import com.wologic.domainnew.PackageAllDetail;
import com.wologic.domainnew.User;
import com.wologic.domainnew.VersionInfo;
import com.wologic.domainnew.WareHouse;
import com.wologic.request.LoginRequest;
import com.wologic.request.PackageDetailRequest;
import com.wologic.request.VersionInfoRequest;
import com.wologic.request.WarehouseInfoRequest;
import com.wologic.util.Common;
import com.wologic.util.Constant;
import com.wologic.util.DownLoadManager;
import com.wologic.util.Toaster;

public class LoginActivity extends Activity {

	private MyApplication app;
	private Button btn_login, btn_logout;
	String code = "";
	String pwd = "";
	private EditText et_userName, et_userPassword;

	private TextView tvVersion;

	List<String> list1 = new ArrayList<String>();

	Dialog dialog;
	ListView mlistView;
	BaseAdapter adapter;

	VersionInfo versionInfo;

	private Spinner spinner;

	private ArrayAdapter<WareHouse> arr_adapter;

	private List<WareHouse> wareList;
	
	private  String wareCode;
	
	private String wareName;
	
	private  String customerCode;
	
	private  String customerName;
	
	List<CustomerInfo> customerList;

	private void getwareInfo() {

		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/warehouseInfo/getAllWarehouse";

					WarehouseInfoRequest request = new WarehouseInfoRequest();
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200")) {

						List<WareHouse> wareList = JSON.parseArray(
								jsonSearch.optString("result"), WareHouse.class);
						Message msg = new Message();
						msg.what = 3;
						msg.obj = wareList;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {

				}
			}
		});
		mThread.start();

	}

	private void bindware() {
		spinner = (Spinner) findViewById(R.id.spinner);
		// ������
		arr_adapter = new ArrayAdapter<WareHouse>(this,
				android.R.layout.simple_spinner_item, wareList);
		// ������ʽ
		arr_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ����������
		spinner.setAdapter(arr_adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				 wareCode = ((WareHouse) spinner.getSelectedItem())
						.getWarehouseCode();
				 wareName = ((WareHouse) spinner.getSelectedItem())
							.getWarehouseName();
				
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		app = (MyApplication) getApplication();
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_logout = (Button) findViewById(R.id.btn_logout);
		et_userName = (EditText) findViewById(R.id.et_userName);
		et_userPassword = (EditText) findViewById(R.id.et_userPassword);
		tvVersion = (TextView) findViewById(R.id.tvVersion);
		/* et_userName.setText("admin");
		 et_userPassword.setText("1qaz2wsx");*/
		try {
			tvVersion.setText("Version " + getVersionName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				login();
			}
		});

		btn_logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		initEvents();

		// ��ȡ����˵İ汾��
		getServerVersion();

		getwareInfo();
	}
	
	private void getServerVersion() {

		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					String searchUrl = Constant.url
							+ "/versionInfo/getVersionInfoByCode";
					VersionInfoRequest request = new VersionInfoRequest();
					request.setSystemCode("2");
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);
					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200")) {
						versionInfo = JSON.parseObject(
								jsonSearch.optString("result"),
								VersionInfo.class);
						if (!versionInfo.getVersionCode().equals(
								getVersionName())) {
							// ������������

							Message msg = new Message();
							msg.what = 0;
							handler2.sendMessage(msg);

						} else {
							/*
							 * Message msg = new Message(); msg.what = 3;
							 * handler2.sendMessage(msg);
							 */
						}
					}

				} catch (Exception e) {

					Message msg = new Message();
					msg.what = 2;
					handler2.sendMessage(msg);
					e.printStackTrace();

				}
			}
		});
		mThread.start();
	}

	private void initEvents() {
		et_userPassword.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					switch (event.getAction()) {
					case KeyEvent.ACTION_UP:
						login();
					case KeyEvent.ACTION_DOWN:
						break;
					}
					return true;

				}
				return false;
			}
		});

	}

	private void login() {
		code = et_userName.getText().toString();
		pwd = et_userPassword.getText().toString();

		if (code.equals("")) {
			com.wologic.util.Toaster.toaster("�û�������Ϊ��!");
			return;
		}
		if (pwd.equals("")) {
			Toaster.toaster("���벻��Ϊ��!");
			return;
		}
		if(wareCode.equals(""))
		{
			Toaster.toaster("��ѡ��ֿ�!");
			return;
		}
		loginSync(code, pwd);

	}

	private void loginSync(final String code, final String pwd) {
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					HttpClient client = com.wologic.util.SimpleClient
							.getHttpClient();

					 String searchUrl =
					 "http://www.bjkalf.net:8090/services/user/checkAndGetUserResource";
					// String searchUrl = "http://test.api.portal.bjshengeng.com/services/user/checkAndGetUserResource";

					LoginRequest request = new LoginRequest();
					request.setName(code);
					request.setPassword(pwd);
					request.setWarehouseCode(wareCode);
					
					String json = JSON.toJSONString(request);
					String resultSearch = com.wologic.util.SimpleClient
							.httpPost(searchUrl, json);

					JSONObject jsonSearch = new JSONObject(resultSearch);
					if (jsonSearch.optString("code").toString().equals("200")) {
						User user = JSON.parseObject(
								jsonSearch.optString("result"), User.class);
						if (user == null) {
							Message msg = new Message();
							msg.what = 2;
							msg.obj = "��ѯ�����ͻ���˾��Ϣ";
							handler.sendMessage(msg);
						} else {
							if(user.getCustomerMap()==null||user.getCustomerMap().size()==0)
							{
								Message msg = new Message();
								msg.what = 2;
								msg.obj = "��ǰ�û���δ�󶨻���";
								handler.sendMessage(msg);
							}
							else
							{
								customerList=new ArrayList<CustomerInfo>();
								for (Map.Entry<String, String> entry : user.getCustomerMap().entrySet()) 
								{
									CustomerInfo cinfo=new CustomerInfo();
									cinfo.setCustomerCode(entry.getKey());
									cinfo.setCustomerName(entry.getValue());
									customerList.add(cinfo);
								}
								
								Message msg = new Message();
								msg.what = 1;
								msg.obj = user;
								handler.sendMessage(msg);
							}
							
						}

					} else {
						Message msg = new Message();
						msg.what = 2;
						msg.obj = "�û������������";
						handler.sendMessage(msg);
					}

				} catch (Exception e) {
					System.out.print(e.getMessage());
					Message msg = new Message();
					msg.what = 2;
					msg.obj = "�����쳣,���鵥���Ƿ����";
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
				User user = (User) msg.obj;
				customerDialog(user);
				

				
				break;
			case 2:

				Toaster.toaster(msg.obj.toString());
				break;
			case 3:
				wareList = (List<WareHouse>) msg.obj;
				bindware();
				break;
			default:
				break;
			}
		}
	};

	/*
	 * ��ȡ��ǰ����İ汾��
	 */
	private String getVersionName() throws Exception {
		// ��ȡpackagemanager��ʵ��
		PackageManager packageManager = getPackageManager();
		// getPackageName()���㵱ǰ��İ�����0�����ǻ�ȡ�汾��Ϣ
		PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),
				0);
		return packInfo.versionName;
	}

	Handler handler2 = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				// �Ի���֪ͨ�û���������
				showUpdataDialog();
				break;
			case 1:
				// ��������ʱ
				Toast.makeText(getApplicationContext(), "��ȡ������������Ϣʧ��", 1)
						.show();
				// LoginMain();
				break;
			case 2:
				// ����apkʧ��
				Toast.makeText(getApplicationContext(), "�����°汾ʧ��", 1).show();
				// LoginMain();
				break;
			case 3:
				// ����apkʧ��

				LoginMain();
				break;
			}
		}
	};

	/*
	 * 
	 * �����Ի���֪ͨ�û����³���
	 * 
	 * �����Ի���Ĳ��裺 1.����alertDialog��builder. 2.Ҫ��builder��������, �Ի��������,��ʽ,��ť
	 * 3.ͨ��builder ����һ���Ի��� 4.�Ի���show()����
	 */
	protected void showUpdataDialog() {
		AlertDialog.Builder builer = new Builder(LoginActivity.this);
		builer.setTitle("�汾����");
		builer.setMessage("�����°汾,������");
		// ����ȷ����ťʱ�ӷ����������� �µ�apk Ȼ��װ
		builer.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				downLoadApk();
			}
		});

		// ����ȡ����ťʱ���е�¼
		/*builer.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// LoginMain();
			}
		});*/
		builer.setCancelable(false);


		AlertDialog dialog = builer.create();
		dialog.show();
	}

	/*
	 * �ӷ�����������APK
	 */
	protected void downLoadApk() {
		final ProgressDialog pd; // �������Ի���
		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMessage("�������ظ���");
		pd.show();
		new Thread() {
			@Override
			public void run() {
				try {
					File file = DownLoadManager.getFileFromServer(
							versionInfo.getUrl(), pd);
					sleep(3000);
					installApk(file);
					pd.dismiss(); // �������������Ի���
				} catch (Exception e) {

					Message msg = new Message();
					msg.what = 2;
					handler2.sendMessage(msg);
					pd.dismiss(); // �������������Ի���
					e.printStackTrace();
				}
			}
		}.start();
	}

	// ��װapk
	protected void installApk(File file) {
		Intent intent = new Intent();
		// ִ�ж���
		intent.setAction(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.parse("file://" + file.getAbsolutePath()),
				"application/vnd.android.package-archive");

		// ִ�е���������
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		startActivity(intent);
	}

	/*
	 * ��������������
	 */
	private void LoginMain() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		// ��������ǰ��activity
		this.finish();
	}

   private void customerDialog(final User user)
   {
	   
	   AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
       builder.setIcon(R.drawable.ic_launcher);
       builder.setTitle("����ѡ��");
       //    ͨ��LayoutInflater������һ��xml�Ĳ����ļ���Ϊһ��View����
       View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.customerspinner, null);
       //    ���������Լ�����Ĳ����ļ���Ϊ�������Content
       builder.setView(view);
       
       final Spinner cusspinner = (Spinner)view.findViewById(R.id.customerspinner);
		// ������
       ArrayAdapter<CustomerInfo> cus_adapter = new ArrayAdapter<CustomerInfo>(this,
				android.R.layout.simple_spinner_item, customerList);
		// ������ʽ
       cus_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ����������
       cusspinner.setAdapter(cus_adapter);
       
       int k= cus_adapter.getCount(); 
       for(int i=0;i<k;i++){ 
           if(user.getDefaultCustomerCode().equals(((CustomerInfo)cus_adapter.getItem(i)).getCustomerCode())){ 
        	   cusspinner.setSelection(i,true);// Ĭ��ѡ���� 
        	   customerCode=((CustomerInfo)cus_adapter.getItem(i)).getCustomerCode();
        	   customerName=((CustomerInfo)cus_adapter.getItem(i)).getCustomerName();
               break; 
           } 

       } 
       
 
       cusspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
			   	 customerCode = ((CustomerInfo) cusspinner.getSelectedItem())
						.getCustomerCode();
			   	 customerName = ((CustomerInfo) cusspinner.getSelectedItem())
							.getCustomerName();
				
				/*Toast.makeText(getApplicationContext(), String.valueOf(customerName),
						Toast.LENGTH_LONG).show();*/
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
      
       
       builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
       {
           @Override
           public void onClick(DialogInterface dialog, int which)
           {
        	   
        	   SharedPreferences.Editor editor = getSharedPreferences("data",
						MODE_PRIVATE).edit();
				editor.putString("username", code);
				editor.commit();
				app.setUsername(code);
				Common.userID = code;
				Common.partnerCode = user.getCompanyCode();
				Common.partnerName = user.getCompanyName();
				Common.RealName = user.getCnName();
				Common.UserName = user.getName();
				Common.menuDtos = user.getMenuDtos();
				Common.WareHouseCode=wareCode;
				Common.WareHouseName=wareName;
				Common.CustomerCode=customerCode;
				Common.CustomerName=customerName;
				
				Intent intent = new Intent(LoginActivity.this,
						MainTwoActivity.class);
				startActivity(intent);
				finish();
				
               //    ��������û����������ӡ����
              // Toast.makeText(MainActivity.this, "�û���: " + a + ", ����: " + b, Toast.LENGTH_SHORT).show();
           } 
       });
       builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener()
       {
           @Override
           public void onClick(DialogInterface dialog, int which)
           {
               
           }
       });
       builder.show();

   }
}
