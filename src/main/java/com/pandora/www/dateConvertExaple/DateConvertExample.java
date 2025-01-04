package com.pandora.www.dateConvertExaple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateConvertExample {
    public static void main(String[] args) throws ParseException {

        /**
         * SimpleDateFormet中的 Locale的使用
         */

        String format = "EEE MMM dd HH:mm:ss zzz yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        // 这里的字符串时US当地的表示方法，Locale的作用就是对应当地风格，这里就用Locale.US，用Locale.CHINA就会报错
        // 有的时候parse转Date的时候也必须提供Locale
        Date parse = sdf.parse("Mon Oct 26 15:14:12 CST 2020");
        System.out.println(parse);

        SimpleDateFormat sdf1 = new SimpleDateFormat(format, Locale.CHINA);
        String format1 = sdf1.format(parse);
        System.out.println(format1);

        String format2 = "yyyy-MM-dd EE:HH:mm:ss";
        // 在将Date 转 一定格式的String时
        // 同样的 format
        // 当 locale.US时：2020-10-01 Thu:15:19:15
        // 当 locale.CHINA时：2020-10-01 星期四:15:19:15
        // 这就是Locale的用法

        SimpleDateFormat sdf2 = new SimpleDateFormat(format2, Locale.CHINA);
        String formatStr = sdf2.format(parse);
        System.out.println(formatStr);

        //如果日期对应的星期几不对应，会报错
        String format3 = "yyyy-MM-dd EE:HH:mm:ss";

        SimpleDateFormat sdf3 = new SimpleDateFormat(format3, Locale.CHINA);
        Date parse1 = sdf3.parse("2020-10-01 星期四:15:19:15"); // 这个对应的Locale.US时也会报错，因为是CHINA当地格式的format
        System.out.println(parse1);

        /**
         * DateTimeFormatter中的 Locale的使用
         */
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format3, Locale.CHINA);
        LocalDateTime parse2 = LocalDateTime.parse("2020-10-01 星期四:15:19:15", dateTimeFormatter);
        System.out.println(parse2);

        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern(format3, Locale.CHINA);
        String formatStr1 = parse2.format(dateTimeFormatter1);
        System.out.println(formatStr1);


        String format4 = "EEE MMM dd HH:mm:ss zzz yyyy";

        SimpleDateFormat sdf4 = new SimpleDateFormat(format4, Locale.US);
        Date parse4 = sdf4.parse("Thu Oct 01 15:19:15 CST 2020");
        System.out.println(parse4);

        /**
         * DateTimeFormatter中的 Locale的使用
         */
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern(format4, Locale.US);
        LocalDateTime parse5 = LocalDateTime.parse("Thu Oct 01 15:19:15 CST 2020", dateTimeFormatter2);
        System.out.println(parse5);


        String format5 = "EEE MMM dd HH:mm:ss zzz yyyy";
        // DateTimeFormatter中 EEE MMM dd HH:mm:ss zzz yyyy 不能format CHINA也不能 Locale.US
        // 去掉zzz就可以了
        DateTimeFormatter dateTimeFormatter5 = DateTimeFormatter.ofPattern(format5, Locale.US);
        String formatStr5 = parse5.format(dateTimeFormatter5);
        System.out.println(formatStr5);

    }
}
