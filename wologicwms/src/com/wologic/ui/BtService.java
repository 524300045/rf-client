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
import com.wologic.domainnew.GoodsSuitBox;

/**
 * 
 * <p/>
 * print ticket service
 */
public class BtService extends IntentService {


    private GoodsSuitBox goodsSuitBox;
    

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
        else if (intent.getAction().equals(PrintUtil.ACTION_PRINT_CHAOMA)) {
        	printChaoMa();
        }
       
    }


    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

         int type=intent.getIntExtra("type",0);
         
         goodsSuitBox=(GoodsSuitBox)intent.getSerializableExtra("boxinfo");
        

        return super.onStartCommand(intent, flags, startId);
    }

    private void printTest() {
            PrintOrderDataMaker printOrderDataMaker = new PrintOrderDataMaker(this,"", PrinterWriter58mm.TYPE_58, PrinterWriter.HEIGHT_PARTING_DEFAULT);
            ArrayList<byte[]> printData = (ArrayList<byte[]>) printOrderDataMaker.getPrintData(PrinterWriter58mm.TYPE_58);
            PrintQueue.getQueue(getApplicationContext()).add(printData);
    }

    private void printChaoMa() {
        PrintOrderDataMaker printOrderDataMaker = new PrintOrderDataMaker(this,"", PrinterWriter58mm.TYPE_58, PrinterWriter.HEIGHT_PARTING_DEFAULT);
        ArrayList<byte[]> printData = (ArrayList<byte[]>) printOrderDataMaker.getPrintChaoMaData(PrinterWriter58mm.TYPE_58,goodsSuitBox);
        PrintQueue.getQueue(getApplicationContext()).add(printData);
}






    /**
     * 娣囨繂鐡ㄩ崶鍓у
     * @param
     */
    public Bitmap saveImg() {
        //缁楊兛绔村銉窗閸掓稑缂撴稉锟芥稉顏嗏敄閻ㄥ嚉itmap
        int w=100,h=10;
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        //缁楊兛绨╁銉窗閹跺たitmap鐏忎浇顥婇幋鎬宎nvas鐎电钖�
        Canvas canvas = new Canvas(bitmap);
        //缁楊兛绗佸銉窗鐠嬪啰鏁anvas.drawXXX()缂佹ê鍩楅崘鍛啇
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setTextSize(80.0F);
        int x = 80,y = 100  ;
        canvas.drawText("缁愭澧犻弰搴㈡箑閸忥拷", x, y, paint);
        //缁楊剙娲撳銉窗闁俺绻冪粭顑跨瑏濮濄儱鍑＄紒蹇氬箯瀵版ぞ绨￠張澶婂敶鐎瑰湱娈態itmap鐎电钖勯敍宀勶拷姘崇箖鐠嬪啰鏁itmap閻ㄥ垻ompress閿涘牞绱氶弬瑙勭《娣囨繂鐡ㄩ崶鍓у
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
     * 閹垫挸宓冮崙鐘讳憾
     * @param num
     */
  private void printTesttwo(int num) {
        try {
            ArrayList<byte[]> bytes = new ArrayList<byte[]>();
            for (int i = 0; i < num; i++) {
                String message = "閽冩繄澧幍鎾冲祪濞村鐦痋n閽冩繄澧幍鎾冲祪濞村鐦痋n閽冩繄澧幍鎾冲祪濞村鐦痋n\n";
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