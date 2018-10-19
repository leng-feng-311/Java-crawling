package com.jason.springboot;

import com.jason.springboot.utils.NumberChangeToChinese;
import com.jason.springboot.utils.Tool;

import java.util.Scanner;

public class Main {
//    public static void main(String[] args) {
//        Main ma = new Main();
//        Tool to = new Tool();
//        ma.initMain();
//    }
//    public void initMain(){
//        testNumberToChinese();
//        System.out.println("————————————————————————————————————————");
//    }
//    public void testNumberToChinese(){
//        NumberChangeToChinese numToChinese = new NumberChangeToChinese();
////        System.out.println("0:"+numToChinese.numberToChinese(0));
////        System.out.println("1:"+numToChinese.numberToChinese(1));
////        System.out.println("2:"+numToChinese.numberToChinese(2));
////        System.out.println("3:"+numToChinese.numberToChinese(3));
////        System.out.println("4:"+numToChinese.numberToChinese(4));
////        System.out.println("5:"+numToChinese.numberToChinese(5));
////        System.out.println("6:"+numToChinese.numberToChinese(6));
////        System.out.println("7:"+numToChinese.numberToChinese(7));
////        System.out.println("8:"+numToChinese.numberToChinese(8));
////        System.out.println("9:"+numToChinese.numberToChinese(9));
//        System.out.println("10:"+numToChinese.numberToChinese(10));
////        System.out.println("11:"+numToChinese.numberToChinese(11));
////        System.out.println("110:"+numToChinese.numberToChinese(110));
////        System.out.println("111:"+numToChinese.numberToChinese(111));
////        System.out.println("100:"+numToChinese.numberToChinese(100));
////        System.out.println("102:"+numToChinese.numberToChinese(102));
////        System.out.println("1020:"+numToChinese.numberToChinese(1020));
////        System.out.println("1001:"+numToChinese.numberToChinese(1001));
////        System.out.println("1015:"+numToChinese.numberToChinese(1015));
////        System.out.println("1000:"+numToChinese.numberToChinese(1000));
//////        System.out.println("10000:"+numToChinese.numberToChinese(10000));
//////        System.out.println("20010"+numToChinese.numberToChinese(20010));
//////        System.out.println("20001"+numToChinese.numberToChinese(20001));
//////        System.out.println("100000:"+numToChinese.numberToChinese(100000));
//////        System.out.println("1000000:"+numToChinese.numberToChinese(1000000));
//////        System.out.println("10000000"+numToChinese.numberToChinese(10000000));
//////        System.out.println("100000000:"+numToChinese.numberToChinese(100000000));
//////        System.out.println("1000000000"+numToChinese.numberToChinese(1000000000));
//////        System.out.println("2000105"+numToChinese.numberToChinese(2000105));
//////        System.out.println("20001007:"+numToChinese.numberToChinese(20001007));
////        System.out.println("2005010010:"+numToChinese.numberToChinese(2005010010));
//    }
    private String toChinese(String string) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };

        String result = "";

        int n = string.length();
        for (int i = 0; i < n; i++) {

            int num = string.charAt(i) - '0';

            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
            System.out.println("  "+result);
        }

        System.out.println("----------------");
        System.out.println(result);
        return result;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入字符串：");
        String str = scanner.next();
        // 将字符串数字转化为汉字
        Main main1 = new Main();
        main1.toChinese(str);

    }
}
