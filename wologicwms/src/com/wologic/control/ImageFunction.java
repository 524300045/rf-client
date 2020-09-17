package com.wologic.control;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wologic.R;
import com.wologic.ui.CancelPickerActivity;
import com.wologic.ui.ExecActivity;
import com.wologic.ui.FreeInventoryAreaListActivity;
import com.wologic.ui.GoodsBarCodeActivity;
import com.wologic.ui.GoodsSuitActivity;
import com.wologic.ui.GoodsSuitInBoundActivity;
import com.wologic.ui.GoodsSuitTuiKuActivity;
import com.wologic.ui.InventoryOneActivity;
import com.wologic.ui.OutSuitGoodsActivity;
import com.wologic.ui.PartnerPickerQueryActivity;
import com.wologic.ui.PartnerSortingActivity;
import com.wologic.ui.PickerMainActivity;
import com.wologic.ui.PickerScanContainerActivity;
import com.wologic.ui.PurchaseAcceptActivity;
import com.wologic.ui.SortingGoodsScanActivity;
import com.wologic.ui.StandSortMainActivity;
import com.wologic.ui.SuitSortMainActivity;
import com.wologic.ui.ZhuangBoxMainActivity;

import com.wologic.ui.PartnerPickerActivity;
import com.wologic.ui.PickerActivity;

public class ImageFunction extends LinearLayout {

	private Context context;
	private ImageView img;
	public ImageFunction(Context context,String functionId) {
		super(context);
		this.context = context;
		 // 导入布局  
       LayoutInflater.from(context).inflate(R.layout.imgfunction, this, true);  
       img=(ImageView) findViewById(R.id.img);
       img.setTag(functionId);
       if(functionId.equals("fj"))
       {
    	   img.setImageResource(R.drawable.pandian);
       }
       if(functionId.equals("qxfj"))
       {
    	   img.setImageResource(R.drawable.tuihuo);
       }
       if(functionId.equals("ex"))
       {
    	   img.setImageResource(R.drawable.chuhuo);
       }
       if(functionId.equals("ck"))
       {
    	   img.setImageResource(R.drawable.shouhuo);
       }
       if(functionId.equals("exit"))
       {
    	   img.setImageResource(R.drawable.fahuo);
       }
       
       if(functionId.equals("partnersorting"))
       {
    	   img.setImageResource(R.drawable.geduo);
       }
       
       if(functionId.equals("goodbarcode"))
       {
    	   img.setImageResource(R.drawable.ruku);
       }
       if(functionId.equals("po"))
       {
    	   img.setImageResource(R.drawable.dihuo);
       }
       if(functionId.equals("gd"))
       {
    	   img.setImageResource(R.drawable.geduo);
       }
       
       if(functionId.equals("standsort"))
       {
    	   img.setImageResource(R.drawable.geduo);
       }
       
       if(functionId.equals("fjquery"))
       {
    	   img.setImageResource(R.drawable.geduo);
       }
       
       if(functionId.equals("accept"))
       {
    	   img.setImageResource(R.drawable.dihuo);
       }
       
       if(functionId.equals("inventory"))
       {
    	   img.setImageResource(R.drawable.dihuo);
       }
       
       if(functionId.equals("picker"))
       {
    	   img.setImageResource(R.drawable.dihuo);
       }
       
       if(functionId.equals("freeinventory"))
       {
    	   img.setImageResource(R.drawable.fahuo);
       }
       
       
       if(functionId.equals("wsuitcaiji"))
       {
    	   img.setImageResource(R.drawable.fahuo);
       }
       if(functionId.equals("wsuitruku"))
       {
    	   img.setImageResource(R.drawable.geduo);
       }
       
       if(functionId.equals("wsuitfenjian"))
       {
    	   img.setImageResource(R.drawable.geduo);
       }
       
       if(functionId.equals("wsuittuigong"))
       {
    	   img.setImageResource(R.drawable.fahuo);
       }
       
       if(functionId.equals("wsuitketui"))
       {
    	   img.setImageResource(R.drawable.fahuo);
       }
       
       if(functionId.equals("wsuithuiku"))
       {
    	   img.setImageResource(R.drawable.fahuo);
       }
       
       // outsuitbiaoqian ,出库打印标签
       if(functionId.equals("outsuitbiaoqian"))
       {
    	   img.setImageResource(R.drawable.fahuo);
       }
       
       img.setOnClickListener(mListener);
	}
	

 	
   	OnClickListener  mListener = new OnClickListener() {  
           @Override  
           public void onClick(View v) {  
        	   
        	  String functionId=v.getTag().toString();
        	  
        	  if(functionId.equals("freeinventory"))
        	  {
        		  context.startActivity(new Intent(getContext(), FreeInventoryAreaListActivity.class));  
        	  } 
        	
        	  if(functionId.equals("fj"))
              {
        		  context.startActivity(new Intent(getContext(), PickerActivity.class));
              }
              if(functionId.equals("qxfj"))
              {
            	  context.startActivity(new Intent(getContext(), CancelPickerActivity.class));
              }
              //标品分拣
              if(functionId.equals("standsort"))
              {
            	  //StandSortMainActivity
            	 // context.startActivity(new Intent(getContext(), SortingGoodsScanActivity.class));
            	  context.startActivity(new Intent(getContext(), StandSortMainActivity.class));
              }
              if(functionId.equals("wsuitfenjian"))
              {
            	  //套装分拣
            	  context.startActivity(new Intent(getContext(), SuitSortMainActivity.class));
              }
              if(functionId.equals("wsuittuigong"))
              {
            	  //套装退供
            	  context.startActivity(new Intent(getContext(), SuitSortMainActivity.class));
              }
              
              if(functionId.equals("wsuitketui"))
              {
            	  //套装客退
            	  context.startActivity(new Intent(getContext(), SuitSortMainActivity.class));
              }
              //wsuithuiku
              if(functionId.equals("wsuithuiku"))
              {
            	  //套装客退
            	  context.startActivity(new Intent(getContext(), GoodsSuitTuiKuActivity.class));
              }
              
              if(functionId.equals("partnersorting"))
              {
            	 // 标品供应商分拣
            	  context.startActivity(new Intent(getContext(), PartnerSortingActivity.class));
              }
              if(functionId.equals("ex"))
              {
            	  context.startActivity(new Intent(getContext(), ExecActivity.class));
              }
              if(functionId.equals("fjquery"))
              {
            	  context.startActivity(new Intent(getContext(), PartnerPickerQueryActivity.class));
              }
              
              if(functionId.equals("wsuitcaiji"))
              {
            	  //套装采集 wsuitruku
            	  context.startActivity(new Intent(getContext(), GoodsSuitActivity.class));
              }
              
              if(functionId.equals("outsuitbiaoqian"))
              {
            	  //出库采集打印标签
            	  context.startActivity(new Intent(getContext(), OutSuitGoodsActivity.class));
              }
              if(functionId.equals("wsuitruku"))
              {
            	  //套装入库
            	  context.startActivity(new Intent(getContext(), GoodsSuitInBoundActivity.class));
              }
              
              if(functionId.equals("goodbarcode"))
              {
            	  context.startActivity(new Intent(getContext(), GoodsBarCodeActivity.class));
              }
              
              if(functionId.equals("exit"))
              {
            	  Activity activity = (Activity) context;
            	  activity.finish();
            	  final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  context.startActivity(intent);
                  
           	   //img.setImageResource(R.drawable.shouhuo);
              }
              if(functionId.equals("pp"))
              {
            	  //包装装箱
            	 // context.startActivity(new Intent(getContext(), PartnerPickerActivity.class));
            	  context.startActivity(new Intent(getContext(), ZhuangBoxMainActivity.class));
              }
              if(functionId.equals("po"))
              {
  				 context.startActivity(new Intent(getContext(), PartnerPickerActivity.class));
              }
              if(functionId.equals("dh"))
              {
           	   img.setImageResource(R.drawable.dihuo);
              }
             
              if(functionId.equals("accept"))
              {
            	  context.startActivity(new Intent(getContext(), PurchaseAcceptActivity.class));
              }
              if(functionId.equals("inventory"))
              {
            	  context.startActivity(new Intent(getContext(), InventoryOneActivity.class));
              }
              if(functionId.equals("picker"))
              {
            	//  context.startActivity(new Intent(getContext(), PickerScanContainerActivity.class));
            	  //标品拣货
            	  
            	  context.startActivity(new Intent(getContext(), PickerMainActivity.class));
              }
           }  
       };  

}
