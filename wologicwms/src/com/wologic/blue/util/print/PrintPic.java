package com.wologic.blue.util.print;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class PrintPic {
    public Canvas canvas = null;

    public Paint paint = null;

    public Bitmap bm = null;
    public int width;
    public float length = 0.0F;

    public byte[] bitbuf = null;

    private PrintPic() {
    }

    private static PrintPic instance = new PrintPic();

    public static PrintPic getInstance() {
        return instance;
    }

    public int getLength() {
        return (int) this.length + 20;
    }

    public void init(Bitmap bitmap) {
        if (null != bitmap) {
            initCanvas(bitmap.getWidth());
        }
        if (null == paint) {
            initPaint();
        }
        if (null != bitmap) {
            drawImage(0, 0, bitmap);
        }
    }

    public void initCanvas(int w) {
        int h = 10 * w;

        this.bm = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        this.canvas = new Canvas(this.bm);

        this.canvas.drawColor(-1);
        this.width = w;
        this.bitbuf = new byte[this.width / 8];
    }

    public void initPaint() {
        this.paint = new Paint();// 鏂板缓涓�涓敾绗�

        this.paint.setAntiAlias(true);//

        this.paint.setColor(-16777216);

        this.paint.setStyle(Paint.Style.STROKE);
    }

    /**
     * draw bitmap
     */
    public void drawImage(float x, float y, Bitmap btm) {
        try {
            // Bitmap btm = BitmapFactory.decodeFile(path);
            this.canvas.drawBitmap(btm, x, y, null);
            if (this.length < y + btm.getHeight())
                this.length = (y + btm.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != btm) {
                btm.recycle();
            }
        }
    }

    /**
     * 浣跨敤鍏夋爡浣嶅浘鎵撳嵃
     *
     * @return 瀛楄妭
     */
    public byte[] printDraw() {
        Bitmap nbm = Bitmap
                .createBitmap(this.bm, 0, 0, this.width, getLength());

        byte[] imgbuf = new byte[this.width / 8 * getLength() + 8];

        int s = 0;

        // 鎵撳嵃鍏夋爡浣嶅浘鐨勬寚浠�
        imgbuf[0] = 29;// 鍗佸叚杩涘埗0x1D
        imgbuf[1] = 118;// 鍗佸叚杩涘埗0x76
        imgbuf[2] = 48;// 30
        imgbuf[3] = 0;// 浣嶅浘妯″紡 0,1,2,3
        // 琛ㄧず姘村钩鏂瑰悜浣嶅浘瀛楄妭鏁帮紙xL+xH 脳 256锛�
        imgbuf[4] = (byte) (this.width / 8);
        imgbuf[5] = 0;
        // 琛ㄧず鍨傜洿鏂瑰悜浣嶅浘鐐规暟锛� yL+ yH 脳 256锛�
        imgbuf[6] = (byte) (getLength() % 256);//
        imgbuf[7] = (byte) (getLength() / 256);

        s = 7;
        for (int i = 0; i < getLength(); i++) {// 寰幆浣嶅浘鐨勯珮搴�
            for (int k = 0; k < this.width / 8; k++) {// 寰幆浣嶅浘鐨勫搴�
                int c0 = nbm.getPixel(k * 8 + 0, i);// 杩斿洖鎸囧畾鍧愭爣鐨勯鑹�
                int p0;
                if (c0 == -1)// 鍒ゆ柇棰滆壊鏄笉鏄櫧鑹�
                    p0 = 0;// 0,涓嶆墦鍗拌鐐�
                else {
                    p0 = 1;// 1,鎵撳嵃璇ョ偣
                }
                int c1 = nbm.getPixel(k * 8 + 1, i);
                int p1;
                if (c1 == -1)
                    p1 = 0;
                else {
                    p1 = 1;
                }
                int c2 = nbm.getPixel(k * 8 + 2, i);
                int p2;
                if (c2 == -1)
                    p2 = 0;
                else {
                    p2 = 1;
                }
                int c3 = nbm.getPixel(k * 8 + 3, i);
                int p3;
                if (c3 == -1)
                    p3 = 0;
                else {
                    p3 = 1;
                }
                int c4 = nbm.getPixel(k * 8 + 4, i);
                int p4;
                if (c4 == -1)
                    p4 = 0;
                else {
                    p4 = 1;
                }
                int c5 = nbm.getPixel(k * 8 + 5, i);
                int p5;
                if (c5 == -1)
                    p5 = 0;
                else {
                    p5 = 1;
                }
                int c6 = nbm.getPixel(k * 8 + 6, i);
                int p6;
                if (c6 == -1)
                    p6 = 0;
                else {
                    p6 = 1;
                }
                int c7 = nbm.getPixel(k * 8 + 7, i);
                int p7;
                if (c7 == -1)
                    p7 = 0;
                else {
                    p7 = 1;
                }
                int value = p0 * 128 + p1 * 64 + p2 * 32 + p3 * 16 + p4 * 8
                        + p5 * 4 + p6 * 2 + p7;
                this.bitbuf[k] = (byte) value;
            }

            for (int t = 0; t < this.width / 8; t++) {
                s++;
                imgbuf[s] = this.bitbuf[t];
            }
        }
        if (null != this.bm) {
            this.bm.recycle();
            this.bm = null;
        }
        return imgbuf;
    }
}