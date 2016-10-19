package com.yoga.china.util;

import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * @author 孙思远  2015年11月24日00:44:45
 * 时间格式化util
 */
public class TimeUtil{

	/**
	 * @author 孙思远  2015年11月24日00:44:45
	 * 传入String时间戳，返回制定pattern的时间字段
	 */
	public static String format(String timeStamp,String pattern){
		if(isNull(timeStamp)){
			System.out.println("时间戳为空");
			return "";
		}
		if (isNull(pattern)){
			System.out.println("pattern为空");
			return "";
		}
		return format(Long.parseLong(timeStamp),pattern);
	}

	/**
	 * @author 孙思远  2015年11月24日00:44:45
	 * 根据传入的时间戳，获取指定的时间格式
	 */
	public static String format(long timeStamp,String pattern){
		return format(new Date(timeStamp),pattern);
	}

	/**
	 * @author 孙思远  2015年11月24日00:44:45
	 * 根据传入的时间时间，获取指定的时间格式
	 */
	public static String format(Date date ,String pattern){
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * @author 孙思远 2015年11月24日00:56:28
	 * 根据传入的时间，按照指定的格式进行转换为date
	 */
	public static Date parse(String time,String pattern){
		if(isNull(time)){
			System.out.println("时间为空");
			return null;
		}
		if(isNull(pattern)){
			System.out.println("时间格式为空");
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		Date date=null;
		try{
			date=sdf.parse(time);
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}


	/**
	 *@author 孙思远 2015年11月24日01:08:12
	 * 根据传入的时间，按照指定的格式进行转换为时间戳
	 */
	public static long parseTimeStamp(String time,String pattern){
		Date date=parse(time,pattern);
		if(isNull(date)){
			return new Date().getTime();
		}
		return date.getTime();
	}


	/**
	 * 是否是空
	 */
	public static boolean isNull(Object o){
		return Tools.isNull(o);
	}

}