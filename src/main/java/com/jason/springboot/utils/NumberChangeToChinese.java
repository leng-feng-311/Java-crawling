package com.jason.springboot.utils;

/**
 * Created by jiazefeng on 18/07/26.
 */
public class NumberChangeToChinese {
    public  String numberToChinese(int num) {//转化一个阿拉伯数字为中文字符串
        if (num == 0) {
            return "零";
        }
        int unitPos = 0;//节权位标识
        String All = new String();
        String chineseNum = new String();//中文数字字符串
        boolean needZero = false;//下一小结是否需要补零
        String strIns = new String();
        while (num > 0) {
            int section = num % 10000;//取最后面的那一个小节
            if (needZero) {//判断上一小节千位是否为零，为零就要加上零
                All = Tool.chnNumChar[0] + All;
            }
            chineseNum = sectionTOChinese(section, chineseNum);//处理当前小节的数字,然后用chineseNum记录当前小节数字
            if (section != 0) {//此处用if else 选择语句来执行加节权位
                strIns = Tool.chnUnitSection[unitPos];//当小节不为0，就加上节权位
                chineseNum = chineseNum + strIns;
            } else {
                strIns = Tool.chnUnitSection[0];//否则不用加
                chineseNum = strIns + chineseNum;
            }
            All = chineseNum + All;
            chineseNum = "";
            needZero = (section < 1000) && (section > 0);
            num = num / 10000;
            unitPos++;
        }
        return All;
    }

    public String sectionTOChinese(int section, String chineseNum) {
        String setionChinese = new String();//小节部分用独立函数操作
        int unitPos = 0;//小节内部的权值计数器
        boolean zero = true;//小节内部的制零判断，每个小节内只能出现一个零
        while (section > 0) {
            int v = section % 10;//取当前最末位的值
            if (v == 0) {
                if (!zero) {
                    zero = true;//需要补零的操作，确保对连续多个零只是输出一个
                    chineseNum = Tool.chnNumChar[0] + chineseNum;
                }
            } else {
                zero = false;//有非零的数字，就把制零开关打开
                setionChinese = Tool.chnNumChar[v];//对应中文数字位
                setionChinese = setionChinese + Tool.chnUnitChar[unitPos];//对应中文权位
                chineseNum = setionChinese + chineseNum;
            }
            unitPos++;
            section = section / 10;
        }

        return chineseNum;
    }

    /**
     * 处理千万数字方法
     *
     * @param num
     * @return
     */
    public String tranWan(int num) {
        StringBuilder builder = new StringBuilder();
        if (num / 10000 > 0) {//说明
            builder.append(trans(num / 10000)).append("万");
        }
        int temp = num % 10000;
        if (temp > 0) {
            String trans = trans(temp);
            //首先判断是否有万位,
            if (builder.length() > 0) {

                //如果千位为0, 则需要补零
                if (temp / 1000 == 0) {
                    builder.append("零");
                }
            }
            builder.append(trans);

        }
        if (builder.length() == 0) {
            builder.append("零");
        }
        return builder.toString();
    }

    /**
     * 完成4位数转换
     *
     * @param num
     * @return
     */
    private String trans(int num) {
        String[] numeric = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        StringBuilder builder = new StringBuilder();
        builder.append(numeric[num / 1000] + "千").
                append(numeric[num / 100 % 10] + "百").
                append(numeric[num / 10 % 10] + "十").
                append(numeric[num % 10]);
        //去掉了零千....
        int index = -1;
        while ((index = builder.indexOf(numeric[0], index + 1)) != -1) {
            if (index < builder.length() - 1) {
                builder.deleteCharAt(index + 1);
            }
        }
        //去掉双零
        index = 0;
        while ((index = builder.indexOf("零零", index)) != -1) {
            builder.deleteCharAt(index);
        }

        if (builder.length() > 1) {
            //去掉开头的零
            if (builder.indexOf(numeric[0]) == 0) {
                builder.deleteCharAt(0);
            }
            //去掉末尾的零
            if (builder.indexOf(numeric[0]) == builder.length() - 1) {
                builder.deleteCharAt(builder.length() - 1);
            }

        }
        //把开头一十换成十
        if (builder.indexOf("一十") == 0) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }
}
