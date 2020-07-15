package com.wologic.ui;

import java.util.List;
import java.util.Map;

import com.wologic.R;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ListSuitAdapter extends BaseAdapter {

	
	Context context;
    LayoutInflater inflater;
   // String[] strings=new String[]{"商品1","商品2","商品3","商品3","商品3","商品3","商品9"};
    private List<Map<String, Object>> mContentList;
    public ListSuitAdapter(Context context, List<Map<String, Object>> contentList) {
        this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.mContentList=contentList;
    }

    @Override
    public int getCount() {
    	   return mContentList.size();
    }

    @Override
    public Object getItem(int position) {
          return mContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView){
            convertView = inflater.inflate(R.layout.item_caiji,null);
            holder =new ViewHolder(convertView,position);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        String skuCode=mContentList.get(position).get("skucode").toString();
        String goodsName=mContentList.get(position).get("goodsname").toString();
        
        // holder.tv_name.setText(getItem(position).toString());
        holder.tv_name.setText(goodsName);
        return convertView;
    }

    class ViewHolder{
        TextView tv_name;
        EditText editText;
        public ViewHolder(View view,int pisition){
            tv_name = (TextView) view.findViewById(R.id.fill_name);
            editText= (EditText) view.findViewById(R.id.fill_order_ltext);
            editText.setTag(pisition);//存tag值
            editText.addTextChangedListener(new TextSwitcher(this));
        }
    }

    class TextSwitcher implements TextWatcher {
        private ViewHolder mHolder;

        public TextSwitcher(ViewHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            int position =Integer.valueOf(mHolder.editText.getTag().toString());//取tag值
//            ((GoodsSuitCaiJiActivity)context).saveEditData(position, s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
        	 if (s != null && !"".equals(s.toString())) {
        		
                 int position = (Integer) mHolder.editText.getTag();
                 mContentList.get(position).put("weight",
                         s.toString());// 当EditText数据发生改变的时候存到data变量中
             }

        }
    }
}
