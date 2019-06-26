package com.wologic.ui;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wologic.R;

public class ContentAdapterInventory extends BaseAdapter implements  OnClickListener {

    private static final String TAG = "ContentAdapterInventory";
    private List<Map<String, Object>> mContentList;
    private LayoutInflater mInflater;
    private Callback mCallback;
    
    int year = 2016;
    int month = 10;
    int day = 8;

    /**
     * 自定义接口，用于回调按钮点击事件到Activity
     * @author Ivan Xu
     * 2014-11-26
     */
    public interface Callback {
        public void click(View v);
    }

    public ContentAdapterInventory(Context context, List<Map<String, Object>> contentList,
            Callback callback) {
        mContentList = contentList;
        mInflater = LayoutInflater.from(context);
        mCallback = callback;
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount");
        return mContentList.size();
    }

    @Override
    public Object getItem(int position) {
        Log.i(TAG, "getItem");
        return mContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.i(TAG, "getItemId");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	  Log.i(TAG, "getView");
           ViewHolder holder = null;
          if (convertView == null) {
              convertView = mInflater.inflate(R.layout.listitem_inventorydetail, null);
              holder = new ViewHolder();
              holder.tvId = (TextView) convertView
                      .findViewById(R.id.tvId);
              
              holder.tvNum = (TextView) convertView
                      .findViewById(R.id.tvNum);
              holder.tvNumss = (TextView) convertView
                      .findViewById(R.id.tvNumss);
             /* holder.tvProductDate = (TextView) convertView
                      .findViewById(R.id.tvProductDate);*/
              holder.dialog_tv_date = (TextView) convertView
                      .findViewById(R.id.dialog_tv_date);
              holder.btnDate = (Button) convertView.findViewById(R.id.btnDate);
              
            
              
              convertView.setTag(holder);
          } else {
              holder = (ViewHolder) convertView.getTag();
              
              
          }
          
          holder.dialog_tv_date.setTag(position);
          holder.tvNumss.setTag(position);
          
         
          holder.tvNum.setText(mContentList.get(position).get("currentStock").toString());
          holder.tvId.setText(Long.toString((Long)mContentList.get(position).get("id")));
          holder.dialog_tv_date.setText(mContentList.get(position).get("productionDate").toString());
          holder.tvNumss.setText(mContentList.get(position).get("inventoryNum").toString());
          

          
          holder.btnDate.setOnClickListener(this);
          holder.btnDate.setTag(position);
          
         
          
          return convertView;
    }

    public class ViewHolder {
    	   public TextView tvNum;
           public TextView tvNumss;
         //  public TextView tvProductDate;
           public TextView dialog_tv_date;
           public Button btnDate;
           public TextView tvId;
           
           
     
    }

    //响应按钮点击事件,调用子定义接口，并传入View
    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }
}