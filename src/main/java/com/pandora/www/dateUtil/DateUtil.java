package com.pandora.www.dateUtil;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 多线程时用 DateTimeFormatter
 * 单线程时可以用 SimpleDateTime（线程不安全，没有锁）
 */

public class DateUtil {
    public static void main(String[] args) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.US);

//        LocalDateTime dateTime = LocalDateTime.parse("Thu Apr 28 17:53:36 CST 2022", formatter);
//
//        String format = dateTime.format(formatter);

        ZonedDateTime dateTime = ZonedDateTime.parse("Thu Apr 28 17:53:36 CST 2022", formatter);
        String format = dateTime.format(formatter);
        System.out.println(format);

        Date date = new Date();
        System.out.println(date + "--");

        LocalDate lastDayOfMonth = getLastDayOfMonth(LocalDate.now());
        System.out.println(lastDayOfMonth);
    }

    /**
     * 获取SimpleDateFormat
     * @param parttern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    public static String getHMS(String datetimeStr) {
        DateTimeFormatter ymdHmsDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter hmsDateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalDateTime.parse(datetimeStr, ymdHmsDateTimeFormatter).format(hmsDateTimeFormatter);
    }

    public static String dateToString(Date date, String pattern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(pattern).format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dateString;
    }

    public static Date stringToDate(String dateString, String parttern) {
        Date date = null;
        if (StringUtils.isNotBlank(dateString)) {
            try {
                date = getDateFormat(parttern).parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static String getYesterdayStr(String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);

        return new SimpleDateFormat(pattern).format(cal.getTime());
    }

    public static boolean isLastDayOfFebruary(LocalDate localDate) {
        Month month = localDate.getMonth();
        if (month != Month.FEBRUARY) {
            return false;
        } else {
            return isLastDayOfMonth(localDate);
        }
    }

    public static boolean isLastDayOfMonth(LocalDate localDate) {
        return localDate.getDayOfMonth() == localDate.lengthOfMonth();
    }

    public static LocalDate getLastDayOfMonth(LocalDate localDate) {
        int lastDay = localDate.lengthOfMonth();
//        return localDate.withMonth(3);
        LocalDate localDateRes = localDate.withDayOfMonth(lastDay);
        return localDateRes;

    }

    public static LocalDate localDateParse(String localdateString, String datePattern) {
        return LocalDate.parse(localdateString, DateTimeFormatter.ofPattern(datePattern));
    }

    public static LocalDateTime localDateTimeParse(String localdatetimeString, String timePattern) {
        return LocalDateTime.parse(localdatetimeString, DateTimeFormatter.ofPattern(timePattern));
    }

    public static String localDateFormat(LocalDate localDate, String datePattern) {
        return localDate.format(DateTimeFormatter.ofPattern(datePattern));
    }

    public static String localDateTimeFormat(LocalDateTime localDateTime, String timePattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(timePattern));
    }

    public static long minusToDaysLocalDate(LocalDate localDate1, LocalDate localDate2) {
        return localDate1.toEpochDay() - localDate2.toEpochDay();
    }

    /**
     * 针对pattern是这种格式的: EEE MMM dd HH:mm:ss z yyyy
     * @param zonedDateTimeString
     * @param pattern
     * @return
     */
    public static ZonedDateTime zonedDateTimeParse(String zonedDateTimeString, String pattern) {
        return ZonedDateTime.parse(zonedDateTimeString, DateTimeFormatter.ofPattern(pattern));
    }

    public static String zonedDateTimeFormat(ZonedDateTime zonedDateTime, String pattern) {
        return zonedDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
