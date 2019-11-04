package com.pay.util;

import java.util.Date;
import java.util.Random;

public class OrderNoGenerator {
    public static String getOrderNo(Integer bsnsType) {
        String ss = bsnsType.toString();
        ss += new Date().getTime();
        ss += new Random().nextInt(90) + 10;
        return ss;
    }


    public static void main(String[] args) throws Exception {
        System.out.println("ffff");
    }

}
