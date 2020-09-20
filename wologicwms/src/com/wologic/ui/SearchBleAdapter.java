package com.wologic.ui;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;




import java.util.ArrayList;

import com.wologic.R;
import com.wologic.application.AppInfo;
import com.wologic.blue.util.print.PrintUtil;


/**
 * Created by yefeng on 6/2/15.
 * github:yefengfreedom
 */
public class SearchBleAdapter extends BaseAdapter {

    private ArrayList<BluetoothDevice> mDevices;
    private LayoutInflater mInflater;
    private String mConnectedDeviceAddress;

    private Context mContext;

    public SearchBleAdapter(Context mContext, ArrayList<BluetoothDevice> mDevices) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mDevices = null == mDevices ? new ArrayList<BluetoothDevice>() : mDevices;
        mConnectedDeviceAddress = PrintUtil.getDefaultBluethoothDeviceAddress(mContext);
        this.mContext=mContext;
    }

    public ArrayList<BluetoothDevice> getDevices() {
        return mDevices;
    }


    public void setDevices(ArrayList<BluetoothDevice> mDevices) {
        if (null == mDevices) {
            mDevices = new ArrayList<BluetoothDevice>();
        }
        this.mDevices = mDevices;
        this.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        if (null != this.mDevices) {
            this.mDevices = sortByBond(this.mDevices);
        }
        super.notifyDataSetChanged();
    }

    private ArrayList<BluetoothDevice> sortByBond(ArrayList<BluetoothDevice> mDevices) {
        if (null == mDevices) {
            return null;
        }
        if (mDevices.size() < 2) {
            return mDevices;
        }
        ArrayList<BluetoothDevice> bondDevices = new ArrayList<BluetoothDevice>();
        ArrayList<BluetoothDevice> unBondDevices = new ArrayList<BluetoothDevice>();
        int size = mDevices.size();
        for (int i = 0; i < size; i++) {
            BluetoothDevice bluetoothDevice = mDevices.get(i);
            if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                bondDevices.add(bluetoothDevice);
            } else {
                unBondDevices.add(bluetoothDevice);
            }
        }
        mDevices.clear();
        mDevices.addAll(bondDevices);
        mDevices.addAll(unBondDevices);
        bondDevices.clear();
        bondDevices = null;
        unBondDevices.clear();
        unBondDevices = null;
        return mDevices;
    }

    public void setConnectedDeviceAddress(String macAddress) {
        this.mConnectedDeviceAddress = macAddress;
    }

    public void addDevices(ArrayList<BluetoothDevice> mDevices) {
        if (null == mDevices) {
            return;
        }
        for (BluetoothDevice bluetoothDevice : mDevices) {
            addDevices(bluetoothDevice);
        }
    }

    public void addDevices(BluetoothDevice mDevice) {
        if (null == mDevice) {
            return;
        }
        if (!this.mDevices.contains(mDevice)) {
            this.mDevices.add(mDevice);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mDevices.size();
    }

    @Override
    public BluetoothDevice getItem(int position) {
        return mDevices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.adapter_bt_device, parent, false);
            holder = new ViewHolder();
            if (null != convertView) {
                convertView.setTag(holder);
            }
        }
        holder.name = (TextView) convertView.findViewById(R.id.txt_adapter_bt_name);
        holder.address = (TextView) convertView.findViewById(R.id.txt_adapter_bt_address);
        holder.bond = (TextView) convertView.findViewById(R.id.btn_adapter_bt_has_bond);

        BluetoothDevice bluetoothDevice = mDevices.get(position);
        String dName = bluetoothDevice.getName() == null ? "δ֪�豸" : bluetoothDevice.getName();
        if (TextUtils.isEmpty(dName)) {
            dName = "δ֪�豸";
        }


        holder.name.setText(dName);
        String dAddress = bluetoothDevice.getAddress() == null ? "δ֪��ַ" : bluetoothDevice.getAddress();
        if (TextUtils.isEmpty(dAddress)) {
            dAddress = "δ֪��ַ";
        }
        holder.address.setText(dAddress);
        int paddingVertical = 8;
        int paddingHorizontal = 16;


        if (AppInfo.density!= 0) {
            paddingVertical = (int) (paddingVertical * AppInfo.density);
            paddingHorizontal = (int) (paddingHorizontal * AppInfo.density);
        }
        holder.bond.setTextColor(holder.name.getCurrentTextColor());
        if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
            if (dAddress.equals(mConnectedDeviceAddress)) {
                holder.bond.setText("������");
                holder.bond.setTextColor(Color.RED);
                holder.bond.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
            } else {
                holder.bond.setText("�����");
                holder.bond.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
            }
        } else {

            holder.bond.setText("δ���");
            holder.bond.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
        }
        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView address;
        TextView bond;
    }
}