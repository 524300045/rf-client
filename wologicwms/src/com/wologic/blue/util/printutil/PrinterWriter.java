package com.wologic.blue.util.printutil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by liuguirong on 8/1/17.
 * 鎵撳嵃鏈哄啓鍏ュ櫒
 */
public abstract class PrinterWriter {

    public static final int HEIGHT_PARTING_DEFAULT = 255;
    private static final String CHARSET = "gb2312";
    private ByteArrayOutputStream bos;
    private int heightParting;

    public PrinterWriter() throws IOException {
        this(HEIGHT_PARTING_DEFAULT);
    }

    public PrinterWriter(int parting) throws IOException {
        if (parting <= 0 || parting > HEIGHT_PARTING_DEFAULT)
            heightParting = HEIGHT_PARTING_DEFAULT;
        else
            heightParting = parting;
        init();
    }

    /**
     * 閲嶇疆
     * 浣跨敤 init 鏇夸唬
     *
     * @throws IOException 寮傚父
     */
    @Deprecated
    public void reset() throws IOException {
        init();
    }

    /**
     * 鍒濆鍖�
     *
     * @throws IOException 寮傚父
     */
    public void init() throws IOException {
        bos = new ByteArrayOutputStream();
        write(PrinterUtils.initPrinter());
    }

    /**
     * 鑾峰彇棰勬墦鍗版暟鎹苟鍏抽棴娴�
     *
     * @return 棰勬墦鍗版暟鎹�
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    @Deprecated
    public byte[] getData() throws IOException {
        return getDataAndClose();
    }

    /**
     * 鑾峰彇棰勬墦鍗版暟鎹苟閲嶇疆娴�
     *
     * @return 棰勬墦鍗版暟鎹�
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public byte[] getDataAndReset() throws IOException {
        byte[] data;
        bos.flush();
        data = bos.toByteArray();
        bos.reset();
        return data;
    }

    /**
     * 鑾峰彇棰勬墦鍗版暟鎹苟鍏抽棴娴�
     *
     * @return 棰勬墦鍗版暟鎹�
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public byte[] getDataAndClose() throws IOException {
        byte[] data;
        bos.flush();
        data = bos.toByteArray();
        bos.close();
        bos = null;
        return data;
    }

    /**
     * 鍐欏叆鏁版嵁
     *
     * @param data 鏁版嵁
     * @throws IOException 寮傚父
     */
    public void write(byte[] data) throws IOException {
        if (bos == null)
            reset();
        bos.write(data);
    }

    /**
     * 璁剧疆灞呬腑
     *
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void setAlignCenter() throws IOException {
        write(PrinterUtils.alignCenter());
    }

    /**
     * 璁剧疆宸﹀榻�
     *
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void setAlignLeft() throws IOException {
        write(PrinterUtils.alignLeft());
    }

    /**
     * 璁剧疆鍙冲榻�
     *
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void setAlignRight() throws IOException {
        write(PrinterUtils.alignRight());
    }

    /**
     * 寮�鍚潃閲�
     *
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void setEmphasizedOn() throws IOException {
        write(PrinterUtils.emphasizedOn());
    }

    /**
     * 鍏抽棴鐫�閲�
     *
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void setEmphasizedOff() throws IOException {
        write(PrinterUtils.emphasizedOff());
    }

    /**
     * 璁剧疆鏂囧瓧澶у皬
     *
     * @param size 鏂囧瓧澶у皬 锛�0锝�7锛夛紙榛樿0锛�
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void setFontSize(int size) throws IOException {
        write(PrinterUtils.fontSizeSetBig(size));
    }

    /**
     * 璁剧疆琛岄珮搴�
     *
     * @param height 琛岄珮搴�
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void setLineHeight(int height) throws IOException {
        if (height >= 0 && height <= 255)
            write(PrinterUtils.printLineHeight((byte) height));
    }

    /**
     * 鍐欏叆瀛楃涓�
     *
     * @param string 瀛楃涓�
     * @throws IOException 寮傚父
     */
    public void print(String string) throws IOException {
        print(string, CHARSET);
    }

    /**
     * 鍐欏叆瀛楃涓�
     *
     * @param string      瀛楃涓�
     * @param charsetName 缂栫爜鏂瑰紡
     * @throws IOException 寮傚父
     */
    public void print(String string, String charsetName) throws IOException {
        if (string == null)
            return;
        write(string.getBytes(charsetName));
    }

    /**
     * 鍐欏叆涓�鏉℃í绾�
     *
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void printLine() throws IOException {
        int length = getLineWidth();
        String line = "";
        while (length > 0) {
            line += "---";
            length--;
        }
        print(line);
    }

    /**
     * 鑾峰彇妯嚎绾垮
     *
     * @return 妯嚎绾垮
     */
    protected abstract int getLineWidth();

    /**
     * 涓�琛岃緭鍑�
     *
     * @param str1     瀛楃涓�
     * @param str2     瀛楃涓�
     * @param textSize 鏂囧瓧澶у皬
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void printInOneLine(String str1, String str2, int textSize) throws IOException {
        printInOneLine(str1, str2, textSize, CHARSET);
    }

    public void printInOneLine(String str1, String str2, String str3, int textSize) throws IOException {
        printInOneLine(str1, str2, str3, CHARSET);
    }

    /**
     * 涓�琛岃緭鍑�
     *
     * @param str1        瀛楃涓�
     * @param str2        瀛楃涓�
     * @param textSize    鏂囧瓧澶у皬
     * @param charsetName 缂栫爜鏂瑰紡
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void printInOneLine(String str1, String str2, int textSize, String charsetName) throws IOException {
        int lineLength = getLineStringWidth(textSize);
        int needEmpty = lineLength - (getStringWidth(str1) + getStringWidth(str2)) % lineLength;
        String empty = "";
        while (needEmpty > 0) {
            empty += " ";
            needEmpty--;
        }
        print(str1 + empty + str2, charsetName);
    }

    /**
     * 涓�琛岃緭鍑�
     *
     * @param str1        瀛楃涓�
     * @param str2        瀛楃涓�
     * @param charsetName 缂栫爜鏂瑰紡
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void printInOneLine(String str1, String str2, String str3, String charsetName) throws IOException {
        int lineLength = getLineStringWidth(0);
        int needEmpty = (lineLength - (getStringWidth(str1) + getStringWidth(str2) + getStringWidth(str3)) % lineLength) / 2;
        String empty = "";
        while (needEmpty > 0) {
            empty += " ";
            needEmpty--;
        }
        print(str1 + empty + str2 + empty + str3, charsetName);
    }


    /**
     * 鑾峰彇涓�琛屽瓧绗︿覆闀垮害
     *
     * @param textSize 鏂囧瓧澶у皬
     * @return 涓�琛屽瓧绗︿覆闀垮害
     */
    protected abstract int getLineStringWidth(int textSize);

    private int getStringWidth(String str) {
        int width = 0;
        for (char c : str.toCharArray()) {
            width += isChinese(c) ? 2 : 1;
        }
        return width;
    }

    /**
     * 鎵撳嵃 Drawable 鍥剧墖
     *
     * @param res Resources
     * @param id  璧勬簮ID
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    @Deprecated
    public void printDrawable(Resources res, int id) throws IOException {
        int maxWidth = getDrawableMaxWidth();
        Bitmap image = scalingBitmap(res, id, maxWidth);
        if (image == null)
            return;
        byte[] command = PrinterUtils.decodeBitmap(image, heightParting);
        image.recycle();
        try {
            if (command != null) {
                write(command);
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * 鑾峰彇鍥剧墖鏁版嵁娴�
     *
     * @param res Resources
     * @param id  璧勬簮ID
     * @return 鏁版嵁娴�
     */
    public ArrayList<byte[]>

    getImageByte(Resources res, int id) {
        int maxWidth = getDrawableMaxWidth();
        Bitmap image = scalingBitmap(res, id, maxWidth);
        if (image == null)
            return null;
        ArrayList<byte[]> data = PrinterUtils.decodeBitmapToDataList(image, heightParting);
        image.recycle();
        return data;
    }

    /**
     * 鑾峰彇鍥剧墖鏈�澶у搴�
     *
     * @return 鍥剧墖鏈�澶у搴�
     */
    protected abstract int getDrawableMaxWidth();

    /**
     * 缂╂斁鍥剧墖
     *
     * @param res      璧勬簮
     * @param id       ID
     * @param maxWidth 鏈�澶у
     * @return 缂╂斁鍚庣殑鍥剧墖
     */
    private Bitmap scalingBitmap(Resources res, int id, int maxWidth) {
        if (res == null)
            return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;// 璁剧疆鍙噺鍙栧楂�
        BitmapFactory.decodeResource(res, id, options);// 閲忓彇瀹介珮
        options.inJustDecodeBounds = false;
        // 绮楃暐缂╂斁
        if (maxWidth > 0 && options.outWidth > maxWidth) {
            // 瓒呰繃闄愬畾瀹�
            double ratio = options.outWidth / (double) maxWidth;// 璁＄畻缂╂斁姣�
            int sampleSize = (int) Math.floor(ratio);// 鍚戜笅鍙栨暣锛屼繚璇佺缉鏀惧悗涓嶄細浣庝簬鏈�澶у楂�
            if (sampleSize > 1) {
                options.inSampleSize = sampleSize;// 璁剧疆缂╂斁姣旓紝鍘熷浘鐨勫嚑鍒嗕箣涓�
            }
        }
        try {
            Bitmap image = BitmapFactory.decodeResource(res, id, options);
            final int width = image.getWidth();
            final int height = image.getHeight();
            // 绮剧‘缂╂斁
            if (maxWidth <= 0 || width <= maxWidth) {
                return image;
            }
            final float scale = maxWidth / (float) width;
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            Bitmap resizeImage = Bitmap.createBitmap(image, 0, 0, width, height, matrix, true);
            image.recycle();
            return resizeImage;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    /**
     * 鎵撳嵃 Drawable 鍥剧墖
     *
     * @param drawable 鍥剧墖
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    @Deprecated
    public void printDrawable(Drawable drawable) throws IOException {
        int maxWidth = getDrawableMaxWidth();
        Bitmap image = scalingDrawable(drawable, maxWidth);
        if (image == null)
            return;
        byte[] command = PrinterUtils.decodeBitmap(image, heightParting);
        image.recycle();
        try {
            if (command != null) {
                write(command);
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * 鑾峰彇鍥剧墖鏁版嵁娴�
     *
     * @param drawable 鍥剧墖
     * @return 鏁版嵁娴�
     */
    public ArrayList<byte[]> getImageByte(Drawable drawable) {
        int maxWidth = getDrawableMaxWidth();
        Bitmap image = scalingDrawable(drawable, maxWidth);
        if (image == null)
            return null;
        ArrayList<byte[]> data = PrinterUtils.decodeBitmapToDataList(image, heightParting);
        image.recycle();
        return data;
    }

    /**
     * 缂╂斁鍥剧墖
     *
     * @param drawable 鍥剧墖
     * @param maxWidth 鏈�澶у
     * @return 缂╂斁鍚庣殑鍥剧墖
     */
    private Bitmap scalingDrawable(Drawable drawable, int maxWidth) {
        if (drawable == null || drawable.getIntrinsicWidth() == 0
                || drawable.getIntrinsicHeight() == 0)
            return null;
        final int width = drawable.getIntrinsicWidth();
        final int height = drawable.getIntrinsicHeight();
        try {
            Bitmap image = Bitmap.createBitmap(width, height,
                    drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                            : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(image);
            drawable.setBounds(0, 0, width, height);
            drawable.draw(canvas);
            // 绮剧‘缂╂斁
            if (maxWidth <= 0 || width <= maxWidth) {
                return image;
            }
            final float scale = maxWidth / (float) width;
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            Bitmap resizeImage = Bitmap.createBitmap(image, 0, 0, width, height, matrix, true);
            image.recycle();
            return resizeImage;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    /**
     * 鎵撳嵃 Bitmap 鍥剧墖
     *
     * @param image 鍥剧墖
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    @Deprecated
    public void printBitmap(Bitmap image) throws IOException {
        int maxWidth = getDrawableMaxWidth();
        Bitmap scalingImage = scalingBitmap(image, maxWidth);
        if (scalingImage == null)
            return;
        byte[] command = PrinterUtils.decodeBitmap(scalingImage, heightParting);
        scalingImage.recycle();
        try {
            if (command != null) {
                write(command);
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * 鑾峰彇鍥剧墖鏁版嵁娴�
     *
     * @param image 鍥剧墖
     * @return 鏁版嵁娴�
     */
    public ArrayList<byte[]> getImageByte(Bitmap image) {
        int maxWidth = getDrawableMaxWidth();
        Bitmap scalingImage = scalingBitmap(image, maxWidth);
        if (scalingImage == null)
            return null;
        ArrayList<byte[]> data = PrinterUtils.decodeBitmapToDataList(image, heightParting);
        image.recycle();
        return data;
    }

    /**
     * 缂╂斁鍥剧墖
     *
     * @param image    鍥剧墖
     * @param maxWidth 鏈�澶у
     * @return 缂╂斁鍚庣殑鍥剧墖
     */
    private Bitmap scalingBitmap(Bitmap image, int maxWidth) {
        if (image == null || image.getWidth() <= 0 || image.getHeight() <= 0)
            return null;
        try {
            final int width = image.getWidth();
            final int height = image.getHeight();
            // 绮剧‘缂╂斁
            float scale = 1;
            if (maxWidth <= 0 || width <= maxWidth) {
                scale = maxWidth / (float) width;
            }
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            return Bitmap.createBitmap(image, 0, 0, width, height, matrix, true);
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    /**
     * 鎵撳嵃鍥剧墖鏂囦欢
     *
     * @param filePath 鍥剧墖
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    @Deprecated
    public void printImageFile(String filePath) throws IOException {
        Bitmap image;
        try {
            int width;
            int height;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            width = options.outWidth;
            height = options.outHeight;
            if (width <= 0 || height <= 0)
                return;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            image = BitmapFactory.decodeFile(filePath, options);
        } catch (Exception e) {
            return;
        }
        printBitmap(image);
    }

    /**
     * 鑾峰彇鍥剧墖鏁版嵁娴�
     *
     * @param filePath 鍥剧墖璺緞
     * @return 鏁版嵁娴�
     */
    public ArrayList<byte[]> getImageByte(String filePath) {
        Bitmap image;
        try {
            int width;
            int height;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            width = options.outWidth;
            height = options.outHeight;
            if (width <= 0 || height <= 0)
                return null;
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            image = BitmapFactory.decodeFile(filePath, options);
        } catch (Exception e) {
            return null;
        }
        return getImageByte(image);
    }

    /**
     * 杈撳嚭骞舵崲琛�
     *
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void printLineFeed() throws IOException {
        write(PrinterUtils.printLineFeed());
    }

    /**
     * 杩涚焊鍒囧壊
     *
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void feedPaperCut() throws IOException {
        write(PrinterUtils.feedPaperCut());
    }

    /**
     * 杩涚焊鍒囧壊锛堢暀閮ㄥ垎锛�
     *
     * @throws IOException 寮傚父
     */
    @SuppressWarnings("unused")
    public void feedPaperCutPartial() throws IOException {
        write(PrinterUtils.feedPaperCutPartial());
    }

    /**
     * 璁剧疆鍥剧墖鎵撳嵃楂樺害鍒嗗壊鍊�
     * 鏈�澶у厑璁�255鍍忕礌
     *
     * @param parting 楂樺害鍒嗗壊鍊�
     */
    @SuppressWarnings("unused")
    public void setHeightParting(int parting) {
        if (parting <= 0 || parting > HEIGHT_PARTING_DEFAULT)
            return;
        heightParting = parting;
    }

    /**
     * 鑾峰彇鍥剧墖鎵撳嵃楂樺害鍒嗗壊鍊�
     *
     * @return 楂樺害鍒嗗壊鍊�
     */
    @SuppressWarnings("unused")
    public int getHeightParting() {
        return heightParting;
    }

    /**
     * 鍒ゆ柇鏄惁涓枃
     * GENERAL_PUNCTUATION 鍒ゆ柇涓枃鐨勨�滃彿
     * CJK_SYMBOLS_AND_PUNCTUATION 鍒ゆ柇涓枃鐨勩�傚彿
     * HALFWIDTH_AND_FULLWIDTH_FORMS 鍒ゆ柇涓枃鐨勶紝鍙�
     *
     * @param c 瀛楃
     * @return 鏄惁涓枃
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }
}
