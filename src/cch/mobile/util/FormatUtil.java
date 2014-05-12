package cch.mobile.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.text.format.DateFormat;

public class FormatUtil {
	public static String dateFormat(String formatStr,Date d){
		DateFormat df = new DateFormat();
		return df.format(formatStr, d).toString();
	}
	
	public static Date string2Date(String value){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Double parseDouble(String value){
		return Double.parseDouble(value);
	}
	public static String double2String(double d){
		return Double.toString(d);
	}
}
