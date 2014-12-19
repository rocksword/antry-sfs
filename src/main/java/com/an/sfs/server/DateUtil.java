package com.an.sfs.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String TIME_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN_FULL = "yyyy/MM/dd HH:mm:ss.SSS";

    public static final String FORMAT_1 = "yyyyMMdd";
    public static final String FORMAT_2 = "MM-dd";
    public static final SimpleDateFormat FORMATER_1 = new SimpleDateFormat(FORMAT_1);
    public static final SimpleDateFormat FORMATER_2 = new SimpleDateFormat(FORMAT_2);

    public static final SimpleDateFormat FORMATER_DAY = new SimpleDateFormat(TIME_PATTERN);
    public static final SimpleDateFormat FORMATER_FULL = new SimpleDateFormat(TIME_PATTERN_FULL);

    /**
     * @param date
     *            format yyyymmdd
     * @return format MM-dd
     */
    public static String formatDate(String date) {
        long ts = getTimestamp(date);
        String result = DateUtil.FORMATER_2.format(ts);
        return result;
    }

    /**
     * @param date
     *            format yyyymmdd
     * @return
     */
    public static long getTimestamp(String date) {
        try {
            Date d = DateUtil.FORMATER_1.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            long result = calendar.getTimeInMillis();
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static long getNextDayStartTimestamp(String date) {
        Date d = null;
        try {
            d = DateUtil.FORMATER_DAY.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        long result = calendar.getTimeInMillis();
        return result;
    }

    /**
     * @param date
     *            format 2014/02/28
     * @return
     */
    public static String getNextDay(String date) {
        Date d = plusDay(date, 1);
        String result = DateUtil.FORMATER_DAY.format(d);
        return result;
    }

    /**
     * @param date
     *            format 2014/02/28
     * @param day
     * @return
     */
    public static Date plusDay(String date, int day) {
        Date d = null;
        try {
            d = DateUtil.FORMATER_DAY.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        Date result = calendar.getTime();
        return result;
    }
}
