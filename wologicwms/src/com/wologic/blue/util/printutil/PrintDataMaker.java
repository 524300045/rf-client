package com.wologic.blue.util.printutil;

import java.util.List;

import com.wologic.domainnew.GoodsSuitBox;

/**
 * Print
 * * Created by liugruirong on 2017/8/3.
 */

public interface PrintDataMaker {
    List<byte[]> getPrintData(int type);
    
    List<byte[]> getPrintChaoMaData(int type,GoodsSuitBox goodsSuitBox);
}
