package com.fh.util.mbh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 模拟登陆模块
 * @author MBHTech
 *
 */
public class AnalogLanding {
	private static String loadingUrl = "https://www.517.cn/usercenter/login/";  
	private static String UserAndPower = "Username=15204037661&Password=123456789ji.";
	
	public static void main(String[] args){
		String value = null;
		String selectUrl = null;
		for (int i = 1; i < 10; i++) {
			if(i == 1){
				selectUrl = "https://www.517.cn/website/Ashx/HouseHandler.ashx?action=newzufang&pagename=houselist&url=/zufang/area/pg1/&radius=3000&pageIndex=1&pageSize=20&DealType=78";
			}else{
				selectUrl = "https://www.517.cn/website/Ashx/HouseHandler.ashx?action=newzufang&pagename=houselist&url=/zufang/area/pg"+i+"/&radius=3000&pageIndex=1&pageSize=20&DealType=78";
			}
			try {
				value = loadJSON(loadingUrl,selectUrl,UserAndPower);
				System.out.println(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * loadingUrl为登陆地址，url为访问地址，UserAndPower为账户密码（格式为："email=gaishjie@mbh.ai&password=589967631"）
	 * @param loadingUrl
	 * @param url
	 * @param UserAndPower
	 * @return
	 * @throws IOException
	 */
	public static String loadJSON(String loadingUrl,String selectUrl,String UserAndPower) throws IOException {
        // 连接地址（通过阅读html源代码获得，即为登陆表单提交的URL）  
		/** 
		* 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using 
		* java.net.URL and //java.net.URLConnection 
		*/  
		URL newLoadingUrl = new URL(loadingUrl);  
		HttpURLConnection connection = (HttpURLConnection) newLoadingUrl.openConnection();  
		
		/** 
		* 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。 
		* 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做： 
		*/  
		connection.setDoOutput(true);  
		/** 
		* 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ... 
		*/  
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");  
		//其中的memberName和password也是阅读html代码得知的，即为表单中对应的参数名称  
		out.write(UserAndPower);// post的关键所在！ 
		//remember to clean up  
		out.flush();  
		out.close();  
		
		//取得cookie，相当于记录了身份，供下次访问时使用  
		String cookieVal = connection.getHeaderField("Set-Cookie"); 
		
		//重新打开一个连接  
		newLoadingUrl = new URL(selectUrl);  
		HttpURLConnection resumeConnection = (HttpURLConnection) newLoadingUrl.openConnection();  
		resumeConnection.setRequestProperty("User-Agent","Baiduspider");
		if (cookieVal != null) {  
		    //发送cookie信息上去，以表明自己的身份，否则会被认为没有权限  
		    resumeConnection.setRequestProperty("Cookie", cookieVal);  
		}  
		resumeConnection.connect();  
		
		
         StringBuilder json = new StringBuilder();
         InputStream urlStream = resumeConnection.getInputStream();   
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlStream));//防止乱码
         String inputLine = null;
         while ((inputLine = bufferedReader.readLine()) != null) {
             json.append(inputLine);
         }
         bufferedReader.close();
         return json.toString();
     	}
}
