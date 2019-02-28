package com.fh.util.mbh;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * 获取txt中文件进行
 * 输出List数组
 * @author MBHTech
 *
 */
public class ReadText {

	public static List readTxtToList(String filePath){
		
	  FileInputStream fis = null;
	  InputStreamReader isr = null;
	  //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
	  BufferedReader br = null;
	  //存放数据的List
	  List fileValueList = new ArrayList();
		
	  //
//	  String filePath = "E:\\jsonTest\\29993.txt";
	  try {
		   String str = "";
		   String str1 = "";
		   fis = new FileInputStream(filePath);// FileInputStream
		   // 从文件系统中的某个文件中获取字节
		    isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
		    br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
		   while ((str = br.readLine()) != null) {
//			   str1 += str + "\n";
			   fileValueList.add(str);
		   }
		   // 当读取的一行不为空时,把读到的str的值赋给str1
//		   System.out.println(str1);// 打印出str1
		  } catch (FileNotFoundException e) {
		   System.out.println("找不到指定文件");
		  } catch (IOException e) {
		   System.out.println("读取文件失败");
		  } finally {
		   try {
		     br.close();
		     isr.close();
		     fis.close();
		    // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
		   } catch (IOException e) {
		    e.printStackTrace();
		   }
		  }
	  return fileValueList;
	}
	
	public static void main(String[] arg){
		String filePath = "E:\\txt\\moji_weather_news_data20180820";
		List textValueList = readTxtToList(filePath);
		for (int i = 0; i < textValueList.size(); i++) {
			System.out.println(textValueList.get(i));
			System.out.println(i);
		}
		
	}
}
