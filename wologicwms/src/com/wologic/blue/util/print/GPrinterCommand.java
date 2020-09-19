package com.wologic.blue.util.print;

/**
 * Created by liuguirong on 8/1/17.
 * <p/>
 * printer command
 */
public class GPrinterCommand {

    public static final byte[] left = new byte[]{0x1b, 0x61, 0x00};// 闈犲乏
    public static final byte[] center = new byte[]{0x1b, 0x61, 0x01};// 灞呬腑
    public static final byte[] right = new byte[]{0x1b, 0x61, 0x02};// 闈犲彸
    public static final byte[] bold = new byte[]{0x1b, 0x45, 0x01};// 閫夋嫨鍔犵矖妯″紡
    public static final byte[] bold_cancel = new byte[]{0x1b, 0x45, 0x00};// 鍙栨秷鍔犵矖妯″紡
    public static final byte[] text_normal_size = new byte[]{0x1d, 0x21, 0x00};// 瀛椾綋涓嶆斁澶�
    public static final byte[] text_big_height = new byte[]{0x1b, 0x21, 0x10};// 楂樺姞鍊�
    public static final byte[] text_big_size = new byte[]{0x1d, 0x21, 0x11};// 瀹介珮鍔犲��
    public static final byte[] reset = new byte[]{0x1b, 0x40};//澶嶄綅鎵撳嵃鏈�
    public static final byte[] print = new byte[]{0x0a};//鎵撳嵃骞舵崲琛�
    public static final byte[] under_line = new byte[]{0x1b, 0x2d, 2};//涓嬪垝绾�
    public static final byte[] under_line_cancel = new byte[]{0x1b, 0x2d, 0};//涓嬪垝绾�

    /**
     * 璧扮焊
     *
     * @param n 琛屾暟
     * @return 鍛戒护
     */
    public static byte[] walkPaper(byte n) {
        return new byte[]{0x1b, 0x64, n};
    }

    /**
     * 璁剧疆妯悜鍜岀旱鍚戠Щ鍔ㄥ崟浣�
     *
     * @param x 妯悜绉诲姩
     * @param y 绾靛悜绉诲姩
     * @return 鍛戒护
     */
    public static byte[] move(byte x, byte y) {
        return new byte[]{0x1d, 0x50, x, y};
    }

}
