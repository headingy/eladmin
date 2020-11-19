package me.zhengjie.modules.mskj.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTimeUtils {


	public static Date getCurrentTime(){
		return Calendar.getInstance().getTime();
	}

	public static String formatDate(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static String formatData(String dataFormat){
		return formatDate(Calendar.getInstance().getTime(),dataFormat);
	}

	/**
	 * Format the date with "yyyy-MM-dd HH:mm:ss".
	 */
	public static String formatDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**yyyyMMddHHmmss*/
	public static String formatCurrentTime2Second(){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	/**yyyy-MM-dd HH:mm:ss.SSS*/
	public static String formatCurrentTime2(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	/**yyyyMMddHHmmssSSS*/
	public static String formatCurrentTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	public static Date parseDate(String date, String format)
			throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(date);
	}

	/**yyyy-MM-dd*/
	public static String getTodayDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String todayDate = dateFormat.format(date).toString();
		return todayDate;
	}

	/**yyyyMMdd*/
	public static String getTodayDateFormat1() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String todayDate = dateFormat.format(date).toString();
		return todayDate;
	}

	/**yyyy-MM-dd HH:mm:ss*/
	public static String getTodayDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String todayDateTime = dateFormat.format(date).toString();
		return todayDateTime;
	}

	public static String getTodayYear() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String todayYear = dateFormat.format(date).toString();
		return todayYear;
	}

	public long getTimeInMillis() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		// //("Current time is : " + calendar.getTime());
		long tempTime = calendar.getTimeInMillis();
		return tempTime;
	}

	public static long getDateTimeInUTCMilliSecsZ(String time) {
		long timeInMillis = 0;
		// //("time = " + time);
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
		Date date;
		try {
			date = format.parse(time);
			// //("date_Z = " + date.toString());
			timeInMillis = date.getTime();
			// //(date + " " + timeInMillis);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return timeInMillis;
	}

	public static long getDateTimeInUTCMilliSecsz(String time) {
		long timeInMillis = 0;
		// //("time = " + time);
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		Date date;
		try {
			date = format.parse(time);
			timeInMillis = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return timeInMillis;
	}

	public static long getDateTimeInUTCMilliSecsY(String time) {
		time = time.replace("T", " ");
		time = time.replace(".000Z", " +0000");
		long timeInMillis = 0;
		// //("time = " + time);
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss Z");
		Date date;
		try {
			date = format.parse(time);
			// //("date_Y = " + date.toString());
			timeInMillis = date.getTime();
			// //(date + " " + timeInMillis);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return timeInMillis;
	}

	public static long getDateTimeInMilliSecs(String time) {
		long timeInMillis = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = format.parse(time);
			timeInMillis = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return timeInMillis;
	}
	
	public static long getDateTimeInUTCMilliSecsZone(String time) {
		long timeInMillis = 0;
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss Z");
		Date date;
		try {
			date = format.parse(time);
			timeInMillis = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return timeInMillis;
	}

	public static long getDateTimeInUTCMilliSecsX(String time) {
		time = time.replace("T", "");
		time = time.replace(":", "");
		long timeInMillis = 0;
		// //("time = " + time);
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-ddHHmmssZ");
		Date date;
		try {
			date = format.parse(time);
			// //("date_Y = " + date.toString());
			timeInMillis = date.getTime();
			// //(date + " " + timeInMillis);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return timeInMillis;
	}

	public static String getDateOfBirth(String dob) {
		String dob_formatted = "";
		Date dob_unformatted = new Date();
		String oldformat = "yyyyMMdd";
		String newformat = "MMM dd, yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(oldformat);
		try {
			dob_unformatted = sdf.parse(dob);
			// //("date = " + dob_formatted.toString());
			sdf.applyPattern(newformat);
			dob_formatted = sdf.format(dob_unformatted);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dob_formatted;
	}

	public static long getTimeInMillis(String gameStartDate) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyyMMdd hh:mm a z");
		long milliseconds = 0;
		try {
			Date EST = format.parse(gameStartDate);
			milliseconds = EST.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return milliseconds;
	}

	public static long getCurrentTimeInMillis() {
		Date now = new Date();
		return now.getTime();
	}

	public static String getDatefromMSTime(long milliseconds) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);

		return sdf.format(calendar.getTime());
	}

	public static String getHTTPDatefromMSTime(long milliseconds) {
		Date date = new Date(milliseconds);
		System.out.println(date.toString());

		DateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		return sdf.format(date);
	}

	public static Date getSimpleDatefromMSTime(long milliseconds) {
		Date date = new Date(milliseconds);

		return date;
	}

	public static Date getDateObjectFromString(String str_date) {
		Date date = null;
		try {
			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyyMMdd");

			date = (Date) formatter.parse(str_date);
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
		}

		return date;
	}

	public static Date getDateObjectFromStringA(String str_date) {
		Date date = null;
		try {
			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd");

			date = (Date) formatter.parse(str_date);
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
		}

		return date;
	}

	public static long getDateTimeInUTCMilliSecst(String time)
			throws ParseException {
		long timeInMillis = 0;

		SimpleDateFormat format = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss z yyyy", Locale.US);
		Date date;

		date = format.parse(time);
		// //("date_z = " + date.toString());
		timeInMillis = date.getTime();
		// //(date + " " + timeInMillis);

		return timeInMillis;
	}
	
	/** 
	    * 判断当前日期是星期几<br> 
	    * <br> 
	    * @param pTime 修要判断的时间<br> 
	    * @return dayForWeek 判断结果<br> 
	    * @Exception 发生异常<br> 
	    */  
	public static int dayForWeek(String pTime){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar c = Calendar.getInstance();  
		try {
			c.setTime(format.parse(pTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		int dayForWeek = 0;
		if(c.get(Calendar.DAY_OF_WEEK) == 1){  
			dayForWeek = 7;
		}else{  
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;  
		}  
		return dayForWeek;
	}

	/**
	 * 获取当前时间的凌晨时间
	 * @date 2019/4/20 18:57
	 * @return java.lang.String
	 * @author liufengshuang
	 */
	public static String getToday0(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return df.format(cal.getTime());
	}
}
