package com.wologic.ui;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.wologic.blue.util.print.GPrinterCommand;
import com.wologic.blue.util.print.PrintPic;
import com.wologic.blue.util.print.PrintQueue;
import com.wologic.blue.util.print.PrintUtil;
import com.wologic.blue.util.printutil.PrintOrderDataMaker;
import com.wologic.blue.util.printutil.PrinterWriter;
import com.wologic.blue.util.printutil.PrinterWriter58mm;

/**
 * Created by liuguirong on 8/1/17.
 * <p/>
 * print ticket service
 */
public class BtService extends IntentService {


  

    public BtService() {
        super("BtService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BtService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        if (intent.getAction().equals(PrintUtil.ACTION_PRINT_TEST)) {
            printTest();
        } else if (intent.getAction().equals(PrintUtil.ACTION_PRINT_TEST_TWO)) {
            printTesttwo(3);
        }else if (intent.getAction().equals(PrintUtil.ACTION_PRINT_BITMAP)) {
        	printBitmapTest();
        }
       
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

         int type=intent.getIntExtra("type",0);
        

        return super.onStartCommand(intent, flags, startId);
    }

    private void printTest() {
            PrintOrderDataMaker printOrderDataMaker = new PrintOrderDataMaker(this,"", PrinterWriter58mm.TYPE_58, PrinterWriter.HEIGHT_PARTING_DEFAULT);
            ArrayList<byte[]> printData = (ArrayList<byte[]>) printOrderDataMaker.getPrintData(PrinterWriter58mm.TYPE_58);
            PrintQueue.getQueue(getApplicationContext()).add(printData);

    }








    /**
     * 淇濆瓨鍥剧墖
     * @param
     */
    public Bitmap saveImg() {
        //绗竴姝ワ細鍒涘缓涓�涓┖鐨凚itmap
        int w=100,h=10;
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        //绗簩姝ワ細鎶夿itmap灏佽鎴怌anvas瀵硅薄
        Canvas canvas = new Canvas(bitmap);
        //绗笁姝ワ細璋冪敤Canvas.drawXXX()缁樺埗鍐呭
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setTextSize(80.0F);
        int x = 80,y = 100  ;
        canvas.drawText("绐楀墠鏄庢湀鍏�", x, y, paint);
        //绗洓姝ワ細閫氳繃绗笁姝ュ凡缁忚幏寰椾簡鏈夊唴瀹圭殑Bitmap瀵硅薄锛岄�氳繃璋冪敤Bitmap鐨刢ompress锛堬級鏂规硶淇濆瓨鍥剧墖
        OutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);
        return  bitmap;
    }




    /**
     * 鎵撳嵃鍑犻亶
     * @param num
     */
  private void printTesttwo(int num) {
        try {
            ArrayList<byte[]> bytes = new ArrayList<byte[]>();
            for (int i = 0; i < num; i++) {
                String message = "钃濈墮鎵撳嵃娴嬭瘯\n钃濈墮鎵撳嵃娴嬭瘯\n钃濈墮鎵撳嵃娴嬭瘯\n\n";
                bytes.add(GPrinterCommand.reset);
                bytes.add(message.getBytes("gbk"));
                bytes.add(GPrinterCommand
                        .print);
                bytes.add(GPrinterCommand.print);
                bytes.add(GPrinterCommand.print);
            }
            PrintQueue.getQueue(getApplicationContext()).add(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void print(byte[] byteArrayExtra) {
        if (null == byteArrayExtra || byteArrayExtra.length <= 0) {
            return;
        }
        PrintQueue.getQueue(getApplicationContext()).add(byteArrayExtra);
    }
    
    private void printBitmapTest() {
        BufferedInputStream bis;
        try {
            bis = new BufferedInputStream(getAssets().open(
                    "ic_launcher.png"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(bis);
        PrintPic printPic = PrintPic.getInstance();
        printPic.init(bitmap);
        if (null != bitmap) {
            if (bitmap.isRecycled()) {
                bitmap = null;
            } else {
                bitmap.recycle();
                bitmap = null;
            }
        }
        byte[] bytes = printPic.printDraw();
        ArrayList<byte[]> printBytes = new ArrayList<byte[]>();
        printBytes.add(GPrinterCommand.reset);
        printBytes.add(GPrinterCommand.print);
        printBytes.add(bytes);
     
        printBytes.add(GPrinterCommand.print);
        PrintQueue.getQueue(getApplicationContext()).add(bytes);
    }

   
}