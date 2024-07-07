package com.pandora.www.dateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DatetimeFormatterUtil {
    public static void main(String[] args) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.US);

//        LocalDateTime dateTime = LocalDateTime.parse("Thu Apr 28 17:53:36 CST 2022", formatter);
//
//        String format = dateTime.format(formatter);

        ZonedDateTime dateTime = ZonedDateTime.parse("Thu Apr 28 17:53:36 CST 2022", formatter);
        String format = dateTime.format(formatter);
        System.out.println(format);
    }


}
