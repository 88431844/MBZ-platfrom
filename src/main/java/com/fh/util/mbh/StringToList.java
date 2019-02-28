package com.fh.util.mbh;

import java.util.ArrayList;
import java.util.List;
/**
 * StringToList是把各自类型的String转换正list的方法集合
 * @author MBHTech
 *
 */

public class StringToList {

	/**
	 * 被转换格式：[{String},{String},{String}...]
	 * @param str
	 * @return
	 */
	public static List<String> stringToList1(String str){
		List<String> result = new ArrayList<String>();
		//出去两端的大括号
		String strNew1 = str.substring(1);
		String strNew = strNew1.substring(0, strNew1.length()-1);
		//第一次分割
		String[] strList = strNew.split("},");
		for (int i = 0; i < strList.length; i++) {
			result.add(strList[i]);
		}
		return result;
	}
	
	/**
	 * 被转换格式：[String,String,String...]
	 * @param str
	 * @return
	 */
	public static List<String> stringToList2(String str){
		List<String> result = new ArrayList<String>();
		//出去两端的大括号
		String strNew1 = str.substring(1);
		String strNew = strNew1.substring(0, strNew1.length()-1);
		//第一次分割
		String[] strList = strNew.split(",");
		for (int i = 0; i < strList.length; i++) {
			result.add(strList[i]);
		}
		return result;
	}
}
