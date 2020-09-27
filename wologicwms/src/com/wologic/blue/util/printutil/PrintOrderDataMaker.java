package com.wologic.blue.util.printutil;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;



import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.wologic.R;
import com.wologic.domainnew.GoodsSuitBox;
import com.wologic.domainnew.GoodsSuitBoxDetail;
import com.wologic.util.EncodingUtils;


/**
 * 
 * Created by liuguirong on 8/1/17.
 */

public class PrintOrderDataMaker implements PrintDataMaker {


    private String qr;
    private int width;
    private int height;
    Context btService;
    private String remark = "娴嬭瘯";


    public PrintOrderDataMaker(Context btService, String qr, int width, int height) {
        this.qr = qr;
        this.width = width;
        this.height = height;
        this.btService = btService;
    }



    @Override
    public List<byte[]> getPrintData(int type) {
        ArrayList<byte[]> data = new ArrayList<byte[]>();

        try {
            PrinterWriter printer;
            printer = type == PrinterWriter58mm.TYPE_58 ? new PrinterWriter58mm(height, width) : new PrinterWriter80mm(height, width);
            printer.setAlignCenter();
            data.add(printer.getDataAndReset());

//            ArrayList<byte[]> image1 = printer.getImageByte(btService.getResources(), R.drawable.logo1);
//
//            data.addAll(image1);
            
           
            Bitmap codeBitmap = EncodingUtils.createQRCode("000123456789".toString(),500,500,null);
            ArrayList<byte[]> image=printer.getImageByte(codeBitmap);
            data.addAll(image);
            printer.setAlignLeft();
            printer.printLine();
            printer.printLineFeed();
            printer.printLineFeed();
            printer.setAlignCenter();
            printer.setEmphasizedOn();
            printer.setFontSize(1);
            printer.printLineFeed();
            printer.setFontSize(0);
            printer.print("打印测试");
            printer.printLineFeed();
            printer.feedPaperCutPartial();

            data.add(printer.getDataAndClose());
            return data;
        } catch (Exception e) {
            return new ArrayList<byte[]>();
        }
    }



	@Override
	public List<byte[]> getPrintChaoMaData(int type,GoodsSuitBox goodsSuitBox) {
		 ArrayList<byte[]> data = new ArrayList<byte[]>();

	        try {
	            PrinterWriter printer;
	            printer = type == PrinterWriter58mm.TYPE_58 ? new PrinterWriter58mm(height, width) : new PrinterWriter80mm(height, width);
	            printer.setAlignCenter();
	            data.add(printer.getDataAndReset());
	            Bitmap codeBitmap = EncodingUtils.createQRCode(goodsSuitBox.getBoxCode().toString(),400,400,null);
	            ArrayList<byte[]> image=printer.getImageByte(codeBitmap);
	            data.addAll(image); 
	            printer.setAlignLeft();
	            printer.printLineFeed();
	            printer.setEmphasizedOn();
	  
	            printer.printLineFeed();
	            printer.setFontSize(0);
//	            printer.setAlignLeft();
//	            printer.printLine();
//	            printer.printLineFeed();
//	            
	            printer.setAlignLeft();
	            printer.print("箱号:"+goodsSuitBox.getBoxCode());
	            printer.printLineFeed();
	           
	            printer.setAlignLeft();
	            printer.print("名称:"+goodsSuitBox.getGoodsName()+"("+goodsSuitBox.getSkuCode()+")");
	            printer.printLineFeed();
	            
	            printer.setAlignLeft();
	            printer.print("总重量:"+goodsSuitBox.getWeight()+" kg");
	            printer.printLineFeed();
	           
	            printer.setAlignLeft();
	            printer.print("生产日期:"+goodsSuitBox.getProductionDate());
	            printer.printLineFeed();
	            
	            if(goodsSuitBox.getDetailList()!=null&&goodsSuitBox.getDetailList().size()>0)
	            {
	            	for(GoodsSuitBoxDetail goodsSuitBoxDetail:goodsSuitBox.getDetailList())
	            	{
	            		        String goodsName="";
	            		        String goodsUnit=" kg";
	            				if(goodsSuitBoxDetail.getGoodsName()!=null)
	            				{
	            					goodsName=goodsSuitBoxDetail.getGoodsName();
	            				}
	            				if(goodsSuitBoxDetail.getGoodsUnit()!=null)
	            				{
	            					//goodsUnit=goodsSuitBoxDetail.getGoodsUnit();
	            				}
	            		 printer.printInOneLine(goodsSuitBoxDetail.getChildSkuCode(), goodsName , goodsSuitBoxDetail.getChildWeight().toString()+goodsUnit, 0);
	                     printer.printLineFeed();
	            	}
	            }
	           
	            printer.printLineFeed();
	            printer.printLineFeed();
	            
	            printer.feedPaperCutPartial();

	            data.add(printer.getDataAndClose());
	            return data;
	        } catch (Exception e) {
	        	String msg=e.getMessage();
	        	System.out.print(msg);
	            return new ArrayList<byte[]>();
	        }
	}
    
    


}
