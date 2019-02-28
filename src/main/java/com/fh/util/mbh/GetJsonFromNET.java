package com.fh.util.mbh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fh.util.DateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetJsonFromNET {
	private static String filePath = "F:\\学习资料\\爬虫练习\\芒果在线_0927_23.txt";
	public static void main(String[] args) {
		String url = null;
		List sum = new ArrayList();
		int count = 0;
		int areaCount = 0;
		int pageCount = 0;
		
		//获取商圈的url
		String shangquan = HttpsUtils.sendPost("https://www.517.cn/website/mapi/Filtersashx/filterashx.ashx", "action=getshangquan");
		JSONObject shangquanJson = JSONObject.fromObject(shangquan);
		JSONObject shangquanJsonDataList = JSONObject.fromObject(shangquanJson.get("data"));
		JSONArray shangquanJsonDataArrayList = shangquanJsonDataList.getJSONArray("Data");
		for (int e = 0; e < shangquanJsonDataArrayList.size(); e++) {
			String shangquanString = shangquanJsonDataArrayList.getString(e);
			JSONObject shangquanStringJson = JSONObject.fromObject(shangquanString);
			//获取页数
			String pageJson = loadJSON("https://www.517.cn/website/Ashx/HouseHandler.ashx?action=newzufang&pagename=houselist&url=/zufang/"+shangquanStringJson.getString("url")+"/area/pg1/&radius=3000&pageIndex=1&pageSize=20&DealType=78","https://www.517.cn/zufang/"+shangquanStringJson.getString("url")+"/area/pg1");
			JSONObject pageJsonObj = JSONObject.fromObject(pageJson);
			String flag1 = pageJsonObj.getString("flag");
	         if(flag1 != "-301" && !"-301".equals(flag1)){
	        	 String pageObj = pageJsonObj.getString("obj");
	 			JSONObject pageJsonCount = JSONObject.fromObject(pageObj);
	 			pageCount = pageJsonCount.getInt("count");
	         }
			
			String aa = null;
			for (int j = 1; j < pageCount; j++) {
				if(j == 1){
					url = "https://www.517.cn/website/Ashx/HouseHandler.ashx?action=newzufang&pagename=houselist&url=/zufang/"+shangquanStringJson.getString("url")+"/area/pg1/&radius=3000&pageIndex=1&pageSize=20&DealType=78";
					aa = "https://www.517.cn/zufang/"+shangquanStringJson.getString("url")+"/area/pg1";
				}else {
					url = "https://www.517.cn/website/Ashx/HouseHandler.ashx?action=newzufang&pagename=houselist&url=/zufang/"+shangquanStringJson.getString("url")+"/area/pg"+j+"/&radius=3000&pageIndex="+(j+1)+"&pageSize=20&DealType=78";
					aa = "https://www.517.cn/zufang/"+shangquanStringJson.getString("url")+"/area/pg"+j+"/";
				}
				System.out.println("爬取url:"+url);
				 String json = loadJSON(url,aa);
		         JSONObject dataJson = JSONObject.fromObject(json);
		         String flag = dataJson.getString("flag");
		         if(flag != "-301" && !"-301".equals(flag)){
		        	 String obj = dataJson.getString("obj");
			         JSONObject objFromJson = JSONObject.fromObject(obj);
			         JSONArray dataListFromJson = objFromJson.getJSONArray("list");
			         for (int i = 0; i < dataListFromJson.size(); i++) {
			        	 String dataString = dataListFromJson.getString(i);
			        	 JSONObject dataList = JSONObject.fromObject(dataString); 
			        	 if(!sum.contains(dataList.get("HouseId"))){
			        		 System.out.println("发布时间:"+dataList.getString("LastTime")+",HouseId:"+dataList.getString("HouseId"));
			        		 try {
			        			String date = null;
			        			Pattern p = Pattern.compile("[^0-9]");
			        			Matcher m = p.matcher(dataList.getString("LastTime"));
			        			if(dataList.getString("LastTime").indexOf("今天") != -1){
			        				date = DateUtil.getBeforeDayDate("0");
			        			}else if(dataList.getString("LastTime").indexOf("昨天") != -1){
			        				date = DateUtil.getBeforeDayDate("-1");
			        			}else if(dataList.getString("LastTime").indexOf("前天") != -1){
			        				date = DateUtil.getBeforeDayDate("-2");
			        			}else if(dataList.getString("LastTime").indexOf("-天前") != -1){
			        				date = DateUtil.getBeforeDayDate("-1");
			        			}else if(m.find()){
			        				date = DateUtil.getBeforeDayDate("-"+m.replaceAll("").trim());
			        			}
			 					FileOperation.writeTxtFileAppend(filePath, "url:"+shangquanStringJson.getString("url")+",HouseId:"+dataList.getString("HouseId")+",Title:"+dataList.getString("Title")+",CountRoom:"+dataList.getString("CountRoom")+",CountHall:"+dataList.getString("CountHall")+",CountBathroom:"+dataList.getString("CountBathroom")+",Mianji:"+dataList.getString("Mianji")+
			 							",Floor:"+dataList.getString("Floor")+",FloorHighest:"+dataList.getString("FloorHighest")+",DicChaoXiangName:"+dataList.getString("DicChaoXiangName")+",DicZhuangXiuName:"+dataList.getString("DicZhuangXiuName")+",BuildYear:"+dataList.getString("BuildYear")+",XqName:"+dataList.getString("XqName")+
			 							",PriceSum:"+dataList.getString("PriceSum")+",CountWeb:"+dataList.getString("CountWeb")+",area:"+shangquanStringJson.getString("parentid")+",street:"+shangquanStringJson.getString("name")+",lng:"+dataList.getString("Map_BD_Lng")+",lat:"+dataList.getString("map_bd_lat")+",LastTime:"+date+"\t\n");
			 					
			 					System.out.println("url:"+shangquanStringJson.getString("url")+",第"+j+"页数据："+"HouseId:"+dataList.getString("HouseId")+",Title:"+dataList.getString("Title")+",CountRoom:"+dataList.getString("CountRoom")+",CountHall:"+dataList.getString("CountHall")+",CountBathroom:"+dataList.getString("CountBathroom")+",Mianji:"+dataList.getString("Mianji")+
			 							",Floor:"+dataList.getString("Floor")+",FloorHighest:"+dataList.getString("FloorHighest")+",DicChaoXiangName:"+dataList.getString("DicChaoXiangName")+",DicZhuangXiuName:"+dataList.getString("DicZhuangXiuName")+",BuildYear:"+dataList.getString("BuildYear")+",XqName:"+dataList.getString("XqName")+
			 							",PriceSum:"+dataList.getString("PriceSum")+",CountWeb:"+dataList.getString("CountWeb")+",area:"+shangquanStringJson.getString("parentid")+",street:"+shangquanStringJson.getString("name")+",lng:"+dataList.getString("Map_BD_Lng")+",lat:"+dataList.getString("map_bd_lat")+",LastTime:"+date);
			        		 } catch (IOException e1) {
			 					// TODO Auto-generated catch block
			 					e1.printStackTrace();
			 				}
			 	        	 count++;
			        	 }
			        	 sum.add(dataList.get("HouseId"));
			         }
		         }else{
		        	 System.out.println("url:"+shangquanStringJson.getString("url"));
		        	 break;
		         }
		         try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			System.out.println(url);
	        System.out.println("一共"+count+"条数据！");
	        areaCount ++;
		}
		System.out.println("一共获取到"+areaCount+"个区域数据。");
     }
 
     public static String loadJSON(String url,String referer) {
         StringBuilder json = new StringBuilder();
         try {
             URL oracle = new URL(url);
             URLConnection yc = oracle.openConnection();
             yc.setRequestProperty("Referer",referer);
             yc.setRequestProperty("User-Agent","Baiduspider");
             BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"utf-8"));//防止乱码
             String inputLine = null;
             while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
             }
             in.close();
         } catch (MalformedURLException e) {
         } catch (IOException e) {
         }
         return json.toString();
     }
}
