package com.deng.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdUtil {
	
	public static String getId(){
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(date);
	}
	
	/*public static void main(String[] args) {
		System.out.println(getId());
		
		Long id = Long.parseLong(getId());
		System.out.println(id);
	}*/

}
