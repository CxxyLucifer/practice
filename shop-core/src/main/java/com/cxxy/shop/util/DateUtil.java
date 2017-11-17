package com.cxxy.shop.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by liuhui on 2017/6/6.
 */
public class DateUtil {
    private static final String DEFAULT_DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";


    public static boolean isNull(Object o)
    {
        if (o == null)
        {
            return true;
        }

        if (o instanceof String)
        {
            String s = (String) o;
            if (s.trim().equals(""))
            {
                return true;
            }
            if (s.trim().equals("null"))
            {
                return true;
            }
            return false;
        }
        else if (o instanceof Object[])
        {
            return ((Object[]) o).length == 0;
        }
        else if (o instanceof Collection<?>)
        {
            return ((Collection<?>) o).isEmpty();
        }
        else if (o instanceof Map<?, ?>)
        {
            return ((Map<?, ?>) o).isEmpty();
        }
        return false;
    }




    /**
     * 返回当前日期字符串
     *
     * @param dateFormate
     *            日期格式
     * @return
     */
    public static String getCurrentDateString(String dateFormate)
    {
        String formate = "";
        if (isNull(dateFormate))
        {
            formate = DEFAULT_DATE_FORMATE;
        }
        else
        {
            formate = dateFormate;
        }
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(cal.getTime());
    }

    /**
     * 获取上月份
     *
     * @param dateString
     *            (格式如:2015-02)
     * @return
     * @throws Exception
     */
    public static String getPreMouth(String dateString)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        try
        {
            date = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(date);
        gc.add(2, -1);// 月份减一

        return DateUtil.getDateString(gc.getTime(), "yyyy-MM");
    }

    /**
     * 获取去年今月
     *
     * @param dateString
     *            (格式:yyyy-MM)
     * @return
     */
    public static String getPreYearMouth(String dateString)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        try
        {
            date = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(date);
        gc.add(1, -1);// 年减一

        return DateUtil.getDateString(gc.getTime(), "yyyy-MM");
    }

    /**
     * 获取上个月的今天
     *
     * @param dateString
     *            (格式:yyyy-MM-dd)
     * @return
     */
    public static String getPreMouthDay(String dateString)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try
        {
            date = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(date);
        gc.add(2, -1);// 月份减一

        return DateUtil.getDateString(gc.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获取去年今日
     *
     * @param dateString
     *            (格式:yyyy-MM-dd)
     * @return
     */
    public static String getPreYearDay(String dateString)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try
        {
            date = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(date);
        gc.add(1, -1);// 年减一

        return DateUtil.getDateString(gc.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获取前一天
     *
     * @param dateString
     * @return
     */
    public static String gerPreDay(String dateString)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        try
        {
            date = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(date);
        gc.add(Calendar.DATE, -1);// 减一

        return DateUtil.getDateString(gc.getTime(), "yyyyMMdd");
    }


    /**
     * -m表示  获取前m天，m 表示获取后m天
     *
     * @param dateString
     * @return
     */
    public static String gerPreMDay(String dateString,int m)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        try
        {
            date = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(date);
        gc.add(Calendar.DATE, m);

        return DateUtil.getDateString(gc.getTime(), "yyyyMMdd");
    }


    /**
     * -m表示  获取前m天，m 表示获取后m天
     * @param dateString
     * @param format
     * @param m
     * @return
     */
    public static String gerPreMDay(String dateString,String format,int m)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        try
        {
            date = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(date);
        gc.add(Calendar.DATE, m);

        return DateUtil.getDateString(gc.getTime(), format);
    }

    /**
     * 返回日期字符串
     *
     * @param date
     *            日期
     * @param dateFormate
     *            日期格式
     * @return
     */
    public static String getDateString(Date date, String dateFormate)
    {
        if (date == null)
        {
            return null;
        }
        String formate = "";
        if (isNull(dateFormate))
        {
            formate = DEFAULT_DATE_FORMATE;
        }
        else
        {
            formate = dateFormate;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        return sdf.format(date);
    }


    /**
     * 获取当前季度的第一天的日期，格式：yyyyMMdd
     * @return
     */
    public static String getSeasonFirstDay(){

        Date[] season = getSeasonDate(new Date());

        return getDateString(season[0],"yyyyMMdd");
    }



    /**
     * 取得季度月
     *
     * @param date
     * @return
     */
    public static Date[] getSeasonDate(Date date) {
        Date[] season = new Date[3];

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.DAY_OF_MONTH, 1);

        int nSeason = getSeason(date);
        if (nSeason == 1) {// 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            season[2] = c.getTime();
        } else if (nSeason == 2) {// 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            season[2] = c.getTime();
        } else if (nSeason == 3) {// 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            season[2] = c.getTime();
        } else if (nSeason == 4) {// 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            season[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            season[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            season[2] = c.getTime();
        }
        return season;
    }

    /**
     *
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {
        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }


    /**
     *
     * 1 上半年 2 下半年
     *
     * @param date
     * @return
     */
    public static int getHalfYear(Date date) {
        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 1;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 2;
                break;
            default:
                break;
        }
        return season;
    }


    private static Date getMonthStart(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }

    private static Date getMonthEnd(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }

    private static Date getNext(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 获取月份所有日期的列表
     * @param dateString
     * @return
     */
    public static List<String> getDaysInMouth(String dateString)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        SimpleDateFormat sdf01 = new SimpleDateFormat("yyyy-MM-dd");

        List<String> list = new ArrayList<String>();
        Date d = new Date();
        try
        {
            d = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Date date = getMonthStart(d);
        Date monthEnd = getMonthEnd(d);
        while (!date.after(monthEnd))
        {
            list.add(sdf01.format(date));
            date = getNext(date);
        }
        return list;
    }


    /**
     * 获取最近7日的日期
     * @return
     */
    public static List<String> getSevenDays()
    {
        List<String> list = new ArrayList<String>();
        String today = getCurrentDateString("yyyy-MM-dd");
        for(int i = 6;i >=0; i--){
            String day = gerPreMDay(today,"yyyy-MM-dd",-i);
            list.add(day);
        }
        return list;
    }



    /**
     * 日期比较大小
     * @param DATE1
     * @param DATE2
     * @param format
     * @return DATE1 大于 DATE2 返回 1, DATE1 小于 DATE2 返回 -1,等于 返回 0
     */
    public static int compare(String DATE1, String DATE2,String format)
    {
        DateFormat df = new SimpleDateFormat(format);
        try
        {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime())
            {
                return 1;
            }
            else if (dt1.getTime() < dt2.getTime())
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> list = getSevenDays();

        for(String a:list){
            System.out.println(a);
        }

    }

}


