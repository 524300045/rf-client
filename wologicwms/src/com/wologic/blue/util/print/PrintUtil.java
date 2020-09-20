package com.wologic.blue.util.print;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;



import java.util.Set;

import com.wologic.application.AppInfo;
import com.wologic.blue.util.bt.BtUtil;


/**
 * Created by liuguirong on 2017/8/3.
 *   printer util
 */
public class PrintUtil {

    private static final String FILENAME = "bt";
    private static final String DEFAULT_BLUETOOTH_DEVICE_ADDRESS = "default_bluetooth_device_address";//閽冩繄澧拋鎯ь槵閸︽澘娼�
    private static final String DEFAULT_BLUETOOTH_DEVICE_NAME = "default_bluetooth_device_name";//閽冩繄澧拋鎯ь槵閸氬秶袨


    private static final String BIAOQIAN_BLUETOOTH_DEVICE_ADDRESS = "biaoqian_bluetooth_device_address";//閽冩繄澧拋鎯ь槵閸︽澘娼�
    private static final String BIAOQIAN_BLUETOOTH_DEVICE_NAME = "biaoqian_bluetooth_device_name";//閽冩繄澧拋鎯ь槵閸氬秶袨

    
    public static final String ACTION_PRINT_CHAOMA = "action_print_chaoma";

    public static final String ACTION_PRINT_TEST = "action_print_test";
    public static final String ACTION_PRINT_TEST_TWO = "action_print_test_two";
    public static final String ACTION_PRINT = "action_print";
    public static final String ACTION_PRINT_TICKET = "action_print_ticket";
    public static final String ACTION_PRINT_BITMAP = "action_print_bitmap";
    public static final String ACTION_PRINT_PAINTING = "action_print_painting";

    public static final String PRINT_EXTRA = "print_extra";

    public static final String ACTION_PRINT_LINGDAN = "action_print_lingdan";

    public static final String ACTION_PRINT_FULLCAR = "action_print_fullcar";

    public static void setDefaultBluetoothDeviceAddress(Context mContext, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DEFAULT_BLUETOOTH_DEVICE_ADDRESS, value);
        editor.apply();
        AppInfo.btAddress = value;
    }

    public static void setDefaultBluetoothDeviceName(Context mContext, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DEFAULT_BLUETOOTH_DEVICE_NAME, value);
        editor.apply();
        AppInfo.btName = value;
    }

    //鐠佸墽鐤嗛弽鍥╊劮閹垫挸宓冮崥宥囆�
    public static void setBiaoQianBluetoothDeviceAddress(Context mContext, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BIAOQIAN_BLUETOOTH_DEVICE_ADDRESS, value);
        editor.apply();
        AppInfo.btAddress = value;
    }

    //鐠佸墽鐤嗛弽鍥╊劮閹垫挸宓冮崷鏉挎絻
    public static void setBiaoQianBluetoothDeviceName(Context mContext, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BIAOQIAN_BLUETOOTH_DEVICE_NAME, value);
        editor.apply();
        AppInfo.btName = value;
    }

    //閺勵垰鎯佺紒鎴濈暰娴滃棙澧﹂崡鐗堟簚鐠佹儳顦�
    public static boolean isBondPrinter(Context mContext, BluetoothAdapter bluetoothAdapter) {
        if (!BtUtil.isOpen(bluetoothAdapter)) {
            return false;
        }
        String defaultBluetoothDeviceAddress = getDefaultBluethoothDeviceAddress(mContext);
        if (TextUtils.isEmpty(defaultBluetoothDeviceAddress)) {
            return false;
        }
        Set<BluetoothDevice> deviceSet = bluetoothAdapter.getBondedDevices();
        if (deviceSet == null || deviceSet.isEmpty()) {
            return false;
        }
        for (BluetoothDevice bluetoothDevice : deviceSet) {
            if (bluetoothDevice.getAddress().equals(defaultBluetoothDeviceAddress)) {
                return true;
            }
        }
        return false;

    }

    public static String getDefaultBluethoothDeviceAddress(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DEFAULT_BLUETOOTH_DEVICE_ADDRESS, "");
    }

    public static boolean isBondPrinterIgnoreBluetooth(Context mContext) {
        String defaultBluetoothDeviceAddress = getDefaultBluethoothDeviceAddress(mContext);
        return !(TextUtils.isEmpty(defaultBluetoothDeviceAddress)
                || TextUtils.isEmpty(getDefaultBluetoothDeviceName(mContext)));
    }
    //缂佹垵鐣剧拋鎯ь槵閻ㄥ嫯鎽戦悧娆忔倳缁夛拷
    public static String getDefaultBluetoothDeviceName(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DEFAULT_BLUETOOTH_DEVICE_NAME, "");
    }

    public static String getBiaoQianBluethoothDeviceAddress(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(BIAOQIAN_BLUETOOTH_DEVICE_ADDRESS, "");
    }

    public static String getBiaoQianBluetoothDeviceName(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(BIAOQIAN_BLUETOOTH_DEVICE_NAME, "");
    }

    /**
     * use new api to reduce file operate
     *
     * @param editor editor
     */
    public static void apply(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        } else {
            editor.commit();
        }
    }
}
