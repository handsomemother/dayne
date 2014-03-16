package cn.dayne.gz.platform.util;

public class MStringUtil {
	
	public static Integer[] toIntArray(String ids,String spliter){
		String[]temps = ids.split(spliter);
		Integer[]ia = new Integer[temps.length];
		for(int i = 0 ; i < temps.length ; i++){
			ia[i]=Integer.parseInt(temps[i]);
		}
		return ia;
	}
}
