package com.yu.hang.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	private static String FINAL_CN = "yyyy/MM/dd HH:mm:ss";

	public static final String PATTERN_1 = "yyyy-MM-dd";
	public static final String PATTERN_2 = "yyyy-MM-dd HH:mm";
	public static final String PATTERN_3 = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";

	/**
	 * 
	 * description:按指定格式输出日期字符串 params:TODO author:NieBin date:2016年4月15日
	 * 下午5:04:58
	 */
	public static String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 
	 * description:按指定中文 params:TODO author:NieBin date:2016年4月15日 下午5:06:19
	 */
	public static String formatDateCN(Date date) {
		return new SimpleDateFormat(FINAL_CN).format(date);
	}

	/**
	 * 
	 * description:默认格式解析 params:日期字符串 author:NieBin date:2016年4月29日 下午2:31:20
	 */
	public static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 获取当前日期之前N天的具体日期
	 * 
	 * @param ts
	 *            ：天数
	 * @return
	 */
	public static Date dayToDate(long ts) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long hm = ts * 24 * 60 * 60 * 1000;
		long nowTime = new Date().getTime();
		long nowTimeS = nowTime - hm;
		Date date = new Date(nowTimeS);
		String nowDateS = DateHelper.formatDate(date, "yyyy-MM-dd");
		try {
			date = sdf.parse(nowDateS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取年月日的时间戳
	 * 
	 * @param date
	 * @return
	 */
	public static long dateTimes(Date date) {
		String dateS = DateHelper.formatDate(date, "yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateTime = null;
		long times = 0;
		if (date != null) {
			try {
				dateTime = sdf.parse(dateS);
				times = dateTime.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return times;
	}

	/**
	 * 
	 * @Date 2016年7月21日
	 * @desc 获取当前日期上个月
	 */
	public static Date getLastMonthDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 
	 * @Date 2016年7月21日
	 * @desc 获取当前时间的当月第一天
	 */
	public static Date getMonthFirst(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 
	 * @Date 2016年7月21日
	 * @desc 获取当前时间的当月最后天
	 */
	public static Date getMonthLast(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	/**
	 * 
	 * @Date 2016年7月27日
	 * @desc
	 */
	public static Date addTime(Date date, int time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, time);
		return cal.getTime();
	}

	public static boolean parseDate1(String str) {
		try {
			new SimpleDateFormat("yyyy-MM-dd").parse(str);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static int getDay() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);// 获取日
		return day;
	}

	public static Date getBeforeMonthDate(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -month);
		return cal.getTime();
	}

	public static Date getLasSec(Date date, int sec) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, sec);
		return cal.getTime();
	}

	/**
	 * 在指定日期加或减指定类型数量
	 * 
	 * @param date
	 * @param type
	 *            Calendar.MONTH Calendar.DAY_OF_MONTH Calendar.DAY_OF_WEEK
	 * @param count
	 * @return
	 */
	public static Date plusDate(Date date, int type, int count) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, count);
		return calendar.getTime();
	}

	/**
	 * 
	 * @Date 2016年11月18日
	 * @desc
	 */
	public static Date parseDateZZ(String date) {
		try {
			return new SimpleDateFormat("yyyyMMddhhmmssSSSZZZ").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 获取上月第一天
	 * 
	 * @return
	 */
	public static Date getLasMonthFirst() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取上月最后一天
	 * 
	 * @return
	 */
	public static Date getLasMonthLast() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 
	 * @return
	 */
	public static String getNowDay() {
		Date date = new Date();
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 取上一天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getLasDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDatePattern(String date, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取间隔时间
	 * @param startTime
	 * @param endTime
	 * @return
	 * int
	 */
	public static int getGap(Date startTime, Date endTime) {
		long num = endTime.getTime() - startTime.getTime();
		return (int) (num / (1000 * 60 * 60));
	}

}
