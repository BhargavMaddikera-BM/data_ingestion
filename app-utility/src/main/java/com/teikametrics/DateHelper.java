package com.teikametrics;

import java.util.Date;

public class DateHelper {
	
	private static DateHelper dateHelper;
	
	private DateHelper(){
		
	}
	
	public static DateHelper getInstance(){
		if(dateHelper==null){
			dateHelper=new DateHelper();
		}
		return dateHelper;
	}
	
	@SuppressWarnings("deprecation")
	public String getCurrentDateTillHour() {
		// TODO Auto-generated method stub
		Date date=new Date(System.currentTimeMillis());
		int month=date.getMonth()+1;
		int day=date.getDate();
		int year=date.getYear()+1900;
		int hours=date.getHours();
		String finalDate=""+day+"-"+month+"-"+year+"-"+hours+":00";
		return finalDate;		
	}


}
