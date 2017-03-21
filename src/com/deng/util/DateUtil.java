package com.deng.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDate(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	/*public static void main(String[] args) {
		System.out.println(getDate());
	}*/
	
}
