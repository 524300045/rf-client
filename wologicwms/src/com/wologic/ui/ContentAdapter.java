package com.wologic.ui;

import java.util.List;
import java.util.Map;

import com.wologic.R;
import com.wologic.ui.ContentAdapterTwo.ViewHolder;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ContentAdapter extends BaseAdapter implements  OnClickListener {

    private static final String TAG = "ContentAdapter";
    private List<Map<String, Object>> mContentList;
    private LayoutInflater mInflater;
    private Callback mCallback;

    /**
     * 自定义接口，用于回调按钮点击事件到Activity
     * @author Ivan Xu
     * 2014-11-26
     */
    public interface Callback {
        public void click(View v);
    }

    public ContentAdapter(Context context, List<Map<String, Object>> contentList,
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
              convertView = mInflater.inflate(R.layout.listitem_purchasedetail, null);
              holder = new ViewHolder();
              holder.tvSkuCode = (TextView) convertView
                      .findViewById(R.id.tvSkuCode);
              holder.tvName = (TextView) convertView
                      .findViewById(R.id.tvName);
              holder.tvPlanNum = (TextView) convertView
                      .findViewById(R.id.tvPlanNum);
              holder.tvRealNum = (TextView) convertView
                      .findViewById(R.id.tvRealNum);
              holder.tvRemain=(TextView) convertView
                      .findViewById(R.id.tvRemain);
              holder.tvId=(TextView) convertView
                      .findViewById(R.id.tvId);
              
              holder.btnDetail = (Button) convertView.findViewById(R.id.btnDetail);
              holder.btnSure = (Button) convertView.findViewById(R.id.btnSure);
              
              holder.btnReceive = (Button) convertView.findViewById(R.id.btnReceive);
              convertView.setTag(holder);
          } else {
              holder = (ViewHolder) convertView.getTag();
          }
          holder.tvSkuCode.setText(mContentList.get(position).get("skucode").toString());
          holder.tvName.setText(mContentList.get(position).get("goodsName").toString());
          holder.tvPlanNum.setText(mContentList.get(position).get("planNum").toString());
          holder.tvRealNum.setText(mContentList.get(position).get("realNum").toString());
          holder.tvRemain.setText(mContentList.get(position).get("remainNum").toString());
          holder.tvId.setText(mContentList.get(position).get("id").toString());
          
          holder.btnDetail.setOnClickListener(this);
          holder.btnDetail.setTag(position);
          holder.btnSure.setOnClickListener(this);;
          holder.btnSure.setTag(position);
          
          holder.btnReceive.setOnClickListener(this);;
          holder.btnReceive.setTag(position);
          
          return convertView;
    }

    public class ViewHolder {
    	   public TextView tvSkuCode;
           public TextView tvName;
           public TextView tvPlanNum;
           public TextView tvRealNum;
           public TextView tvRemain;
           public Button btnDetail;
           public Button btnSure;
           public Button btnReceive;
           public TextView tvId;
     
    }

    //响应按钮点击事件,调用子定义接口，并传入View
    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }
}