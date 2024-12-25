package com.aozhou.code.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtils 提供日期格式化、解析等功能
 * @Author: Aozhou
 * @Date: 2024/12/3
 */
public abstract class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * 日期格式模式常量
     */
    public static final String DAY_PATTERN = "yyyy-MM-dd";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final Date DEFAULT_DATE = DateUtils.parseByDayPattern("1970-01-01");

    /**
     * 根据 'yyyy-MM-dd' 格式解析日期字符串
     *
     * @param str 日期字符串
     * @return 解析后的日期
     */
    public static Date parseByDayPattern(String str) {
        return parseDate(str, DAY_PATTERN);
    }

    /**
     * 根据 'yyyy-MM-dd HH:mm:ss' 格式解析日期字符串
     *
     * @param str 日期字符串
     * @return 解析后的日期
     */
    public static Date parseByDateTimePattern(String str) {
        return parseDate(str, DATETIME_PATTERN);
    }

    /**
     * 无需检查异常的日期解析方法
     *
     * @param str 日期字符串
     * @param pattern 日期格式
     * @return 解析后的日期
     * @throws RuntimeException 如果解析过程中发生 ParseException 异常
     */
    public static Date parseDate(String str, String pattern) {
        try {
            return parseDate(str, new String[]{pattern});
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将日期格式化为字符串
     *
     * @param date 日期对象
     * @param pattern 格式化模式
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 根据 'yyyy-MM-dd' 格式格式化日期
     *
     * @param date 日期对象
     * @return 格式化后的字符串
     */
    public static String formatByDayPattern(Date date) {
        if (date != null) {
            return DateFormatUtils.format(date, DAY_PATTERN);
        } else {
            return null;
        }
    }

    /**
     * 根据 'yyyy-MM-dd HH:mm:ss' 格式格式化日期
     *
     * @param date 日期对象
     * @return 格式化后的字符串
     */
    public static String formatByDateTimePattern(Date date) {
        return DateFormatUtils.format(date, DATETIME_PATTERN);
    }

    /**
     * 获取当前日期，并按 'yyyy-MM-dd' 格式返回
     *
     * @return 当前日期的字符串表示
     * @author yebo
     */
    public static String getCurrentDayByDayPattern() {
        Calendar cal = Calendar.getInstance();
        return formatByDayPattern(cal.getTime());
    }


}
