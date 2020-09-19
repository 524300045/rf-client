package com.wologic.ui;

import java.lang.reflect.Method;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wologic.R;
import com.wologic.application.AppInfo;

import com.wologic.blue.util.bt.BluetoothActivity;
import com.wologic.blue.util.bt.BtInterface;
import com.wologic.blue.util.bt.BtUtil;
import com.wologic.blue.util.print.PrintQueue;
import com.wologic.blue.util.print.PrintUtil;
import com.wologic.util.EncodingUtils;
import com.wologic.util.Toaster;



public class BlueSearchActivity extends BluetoothActivity

            implements BtInterface,
            AdapterView.OnItemClickListener,
        View.OnClickListener
{
    //杩斿洖
    private View backView;

    private ListView lv_searchblt;
    private BluetoothAdapter bluetoothAdapter;
    private SearchBleAdapter searchBleAdapter;

    private TextView tv_title;
    private TextView tv_summary;

    TextView tv_bluename;

    int PERMISSION_REQUEST_COARSE_LOCATION=2;
    
    BluetoothAdapter mAdapter;

    private Button btnSure;
    
    private ImageView enCodeImage1,enCodeImage2;//展示生成的二维码
    

    @Override
    public void onCreate(Bundle savedInstanceState ) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluesearch);
        lv_searchblt = (ListView) findViewById(R.id.lv_searchblt);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_summary = (TextView) findViewById(R.id.tv_summary);
        //鍒濆鍖栬摑鐗欓�傞厤鍣�
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        searchBleAdapter = new SearchBleAdapter(BlueSearchActivity.this, null);
        lv_searchblt.setAdapter(searchBleAdapter);
        tv_bluename =(TextView)findViewById(R.id.tv_bluename);
        btnSure=(Button)findViewById(R.id.btnSure);
        backView=(TextView)findViewById(R.id.tvback);
        init();
        searchDeviceOrOpenBluetooth();
        lv_searchblt.setOnItemClickListener(this);
        tv_title.setOnClickListener(this);
        tv_summary.setOnClickListener(this);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
//        }
  
         backView.setOnClickListener(this);
         btnSure.setOnClickListener(this);
         
        if (null == bluetoothAdapter) {

        	Toaster.toaster("该设备没有蓝牙模块");
       
            return;
        }
        if (bluetoothAdapter.getState()==BluetoothAdapter.STATE_OFF )
        {
           	Toaster.toaster("蓝牙未打开");
            return;
        }

        String address = PrintUtil.getDefaultBluethoothDeviceAddress(getApplicationContext());
        if (TextUtils.isEmpty(address)) {
           
         	Toaster.toaster("尚未绑定蓝牙设备");
            return;
        }
        String name = PrintUtil.getDefaultBluetoothDeviceName(getApplicationContext());
        tv_bluename.setText("绑定蓝牙名称" + name+"  地址:"+address);
        
        enCodeImage1 = (ImageView) findViewById(R.id.code_image1);
        enCodeImage2 = (ImageView) findViewById(R.id.code_image2);
    }



    
    private void printTest()
    {
    	
    	
    	 if (null ==mAdapter) {
             mAdapter = BluetoothAdapter.getDefaultAdapter();
         }
         if (null ==mAdapter) {
             Toaster.toaster("该设备没有蓝牙模块!");
             return;
         }
         String address = PrintUtil.getDefaultBluethoothDeviceAddress(getApplicationContext());
         if (TextUtils.isEmpty(address)) {
             //activity.tv_bluename.setText("尚未绑定蓝牙设备");
             Toaster.toaster("尚未绑定蓝牙设备!");
             return;
         }
         String name = PrintUtil.getDefaultBluetoothDeviceName(getApplicationContext());
         
        if (TextUtils.isEmpty(AppInfo.btAddress)){
            Toaster.toaster("请连接蓝牙...");
            
        }else {
            if (mAdapter.getState()==BluetoothAdapter.STATE_OFF ){//蓝牙被关闭时强制打开
                mAdapter.enable();
     
                Toaster.toaster("蓝牙被关闭请打开...");
            }else {

            	  Toaster.toaster("打印测试...");
          
                Intent intent = new Intent(getApplicationContext(), BtService.class);
                
                 intent.setAction(PrintUtil.ACTION_PRINT_TEST);
                //intent.setAction(PrintUtil.ACTION_PRINT_BITMAP);
                startService(intent);
            }
        }
    }



    public void init() {

        if (!BtUtil.isOpen(bluetoothAdapter)) {
            tv_title.setText("未连接蓝牙打印机");
            tv_summary.setText("系统蓝牙已关闭,点击开启");

        } else {
            if (!PrintUtil.isBondPrinter(this, bluetoothAdapter)) {
                //鏈粦瀹氳摑鐗欐墦鍗版満鍣�
                tv_title.setText("未连接蓝牙打印机");
                tv_summary.setText("点击后搜索蓝牙打印机");

            } else {
                //宸茬粦瀹氳摑鐗欒澶�
                tv_title.setText(getPrinterName() + "已连接");
                String blueAddress = PrintUtil.getDefaultBluethoothDeviceAddress(this);
                if (TextUtils.isEmpty(blueAddress)) {
                    blueAddress = "点击后搜索蓝牙打印机";
                }
                tv_summary.setText(blueAddress);
            }
        }

    }


    @Override
    public void btStatusChanged(Intent intent) {

        if ( bluetoothAdapter.getState()==BluetoothAdapter.STATE_OFF ){//钃濈墮琚叧闂椂寮哄埗鎵撳紑
            bluetoothAdapter.enable();
        }
        if ( bluetoothAdapter.getState()==BluetoothAdapter.STATE_ON ){//钃濈墮鎵撳紑鏃舵悳绱㈣摑鐗�
            searchDeviceOrOpenBluetooth();
        }
        String address = PrintUtil.getDefaultBluethoothDeviceAddress(getApplicationContext());
        if (TextUtils.isEmpty(address)) {

          	Toaster.toaster("尚未绑定蓝牙设备");
            return;
        }
        String name = PrintUtil.getDefaultBluetoothDeviceName(getApplicationContext());
        tv_bluename.setText("绑定蓝牙名称：" + name+"  地址:"+address);

    }
    private String getPrinterName(){
        String dName = PrintUtil.getDefaultBluetoothDeviceName(this);
        if (TextUtils.isEmpty(dName)) {
        	 dName = "未知设备";
        }
        return dName;
    }
    private String getPrinterName(String dName) {
        if (TextUtils.isEmpty(dName)) {
        	dName = "未知设备";
        }
        return dName;
    }

    /**
     * 寮�濮嬫悳绱�
     * search device
     */
    private void searchDeviceOrOpenBluetooth() {
        if (BtUtil.isOpen(bluetoothAdapter)) {
            BtUtil.searchDevices(bluetoothAdapter);
        }
    }

    /**
     * 鍏抽棴鎼滅储
     * cancel search
     */
    @Override
    protected void onStop() {
        super.onStop();
        BtUtil.cancelDiscovery(bluetoothAdapter);
    }
    @Override
    public void btStartDiscovery(Intent intent) {
    	 tv_title.setText("正在搜索蓝牙设备…");
        tv_summary.setText("");
    }

    @Override
    public void btFinishDiscovery(Intent intent) {
    	   tv_title.setText("搜索完成");
           tv_summary.setText("点击重新搜索");
    }
    @Override
    public void btFoundDevice(Intent intent) {
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
     
        if (null != bluetoothAdapter && device != null) {
            searchBleAdapter.addDevices(device);
            String dName = device.getName() == null ? "未知设备" : device.getName();
           
        }
    }

    @Override
    public void btBondStatusChange(Intent intent) {
        super.btBondStatusChange(intent);
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        switch (device.getBondState()) {
            case BluetoothDevice.BOND_BONDING://姝ｅ湪閰嶅
                
                break;
            case BluetoothDevice.BOND_BONDED://閰嶅缁撴潫
                
                connectBlt(device);
                break;
            case BluetoothDevice.BOND_NONE://鍙栨秷閰嶅/鏈厤瀵�
             
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if (null == searchBleAdapter) {
            return;
        }
        final BluetoothDevice bluetoothDevice = searchBleAdapter.getItem(position);
        if (null == bluetoothDevice) {
            return;
        }
        new AlertDialog.Builder(this)
        .setTitle("绑定" + getPrinterName(bluetoothDevice.getName()) + "?")
        .setMessage("点击确认绑定蓝牙设备")
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    BtUtil.cancelDiscovery(bluetoothAdapter);


                    if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                        connectBlt(bluetoothDevice);
                    } else {
                        Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
                        createBondMethod.invoke(bluetoothDevice);
                    }
                    PrintQueue.getQueue(getApplicationContext()).disconnect();
                    String name = bluetoothDevice.getName();
                } catch (Exception e) {
                    e.printStackTrace();
                    PrintUtil.setDefaultBluetoothDeviceAddress(getApplicationContext(), "");
                    PrintUtil.setDefaultBluetoothDeviceName(getApplicationContext(), "");

                    Toaster.toaster("蓝牙绑定失败,请重试");
                }
            }
        })
        .create()
        .show();





    }

    /***
     * 閰嶅鎴愬姛杩炴帴钃濈墮
     * @param bluetoothDevice
     */

    private void connectBlt(BluetoothDevice bluetoothDevice) {
        if (null != searchBleAdapter) {
            searchBleAdapter.setConnectedDeviceAddress(bluetoothDevice.getAddress());
        }
        init();
        searchBleAdapter.notifyDataSetChanged();
        PrintUtil.setDefaultBluetoothDeviceAddress(getApplicationContext(), bluetoothDevice.getAddress());
        PrintUtil.setDefaultBluetoothDeviceName(getApplicationContext(), bluetoothDevice.getName());
    }
    
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvback:
                 finish();
                break;
            case R.id.btnSure:
            	//qrCode1(null);
            	//qrCode2(null);
            	printTest();
               break;
            case R.id.tv_summary:
                searchDeviceOrOpenBluetooth();
                break;
        }
    }
    
    public void qrCode1(View view){
//        if ("".equals(editText.getText().toString())){
//            Toast.makeText(this, "请在输入框中输入内容", Toast.LENGTH_SHORT).show();
//            return;
//        }
        //生成二维码
        Bitmap codeBitmap = EncodingUtils.createQRCode("123456789".toString(),500,500,null);
        enCodeImage1.setImageBitmap(codeBitmap);//显示二维码
    }
    /**
     * 生成带Logo的二维码
     * @param view
     */
    public void qrCode2 (View view){
//        if ("".equals(editText.getText().toString())){
//            Toast.makeText(this, "请在输入框中输入内容", Toast.LENGTH_SHORT).show();
//            return;
//        }
        //获取logo资源,
        //R.drawable.logo为logo图片
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        //生成二维码
        Bitmap codeBitmap = EncodingUtils.createQRCode("zhangxiang".toString(),500,500,logoBitmap);
        enCodeImage2.setImageBitmap(codeBitmap);//显示二维码
    }


}
