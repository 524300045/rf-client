package com.wologic.blue.util.printutil;

import android.content.Context;
import android.graphics.Bitmap;



import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.wologic.R;
import com.wologic.util.EncodingUtils;


/**
 * 娴嬭瘯鏁版嵁鐢熸垚鍣�
 * Created by liuguirong on 8/1/17.
 */

public class PrintOrderDataMaker implements PrintDataMaker {


    private String qr;
    private int width;
    private int height;
    Context btService;
    private String remark = "测试";


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

            ArrayList<byte[]> image1 = printer.getImageByte(btService.getResources(), R.drawable.logo1);

            data.addAll(image1);
            
           
//            Bitmap codeBitmap = EncodingUtils.createQRCode("000123456789".toString(),500,500,null);
//            
//            ArrayList<byte[]> image=printer.getImageByte(codeBitmap);
//            data.addAll(image);

            printer.setAlignLeft();
            printer.printLine();
            printer.printLineFeed();

            printer.printLineFeed();
            printer.setAlignCenter();
            printer.setEmphasizedOn();
            printer.setFontSize(1);
            printer.print("111");
            printer.printLineFeed();
            printer.setEmphasizedOff();
            printer.printLineFeed();

            printer.printLineFeed();
            printer.setFontSize(0);
            printer.setAlignCenter();
            printer.print("张响" + "546545645465456454");
            printer.printLineFeed();

            printer.setAlignCenter();
            printer.print(new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
                    .format(new Date(System.currentTimeMillis())));
            printer.printLineFeed();
            printer.printLine();

            printer.printLineFeed();
            printer.setAlignLeft();
            printer.print("张响 " + "张响");
            printer.printLineFeed();
            printer.print("张响 " +"张响");
            printer.printLineFeed();
            printer.print("张响" + "张响");
            printer.printLineFeed();
            printer.print("张响" + "A3" + "张响");
            printer.printLineFeed();
            printer.print("张响" + "2017-10-1 ");
            printer.printLineFeed();
            printer.print("张响");
            printer.printLineFeed();
            printer.print("张响" + "18094111545454");
            printer.printLineFeed();
            printer.printLine();
            printer.printLineFeed();

            printer.setAlignLeft();
            printer.print("张响" + "张响");
            printer.printLineFeed();
            printer.printLine();

            printer.printLineFeed();

                printer.setAlignCenter();
                printer.print("张响");
                printer.printLineFeed();
                printer.setAlignCenter();
                printer.printInOneLine("张响", "张响", "张响", 0);
                printer.printLineFeed();
//                for (int i = 0; i < 3; i++) {
//
//                    printer.printInOneLine("张响", "X" + 3, "张响" + 30, 0);
//                    printer.printLineFeed();
//                }
//                printer.printLineFeed();
//                printer.printLine();
//                printer.printLineFeed();
//                printer.setAlignLeft();
//                printer.printInOneLine("张响", "张响" + 100, 0);

//
//            printer.setAlignLeft();
//            printer.printInOneLine("张响", "张响" +"0.00"
//                    , 0);
//            printer.printLineFeed();
//
//            printer.setAlignLeft();
//            printer.printInOneLine("张响", "张响" + "0.00"
//                          , 0);
//            printer.printLineFeed();


            printer.setAlignLeft();
            printer.printInOneLine("张响", "张响" +90, 0);
            printer.printLineFeed();

            printer.printLine();
            printer.printLineFeed();
            printer.setAlignCenter();
            printer.print("张响");
            printer.printLineFeed();
            printer.printLineFeed();
            printer.printLineFeed();
            printer.feedPaperCutPartial();

            data.add(printer.getDataAndClose());
            return data;
        } catch (Exception e) {
            return new ArrayList<byte[]>();
        }
    }


}
