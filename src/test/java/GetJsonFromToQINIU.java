import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fh.util.mbh.FileOperation;

public class GetJsonFromToQINIU {
	
	public static void main(String[] args){
		String value = null;
		int count = 0;
		try {
			for (int i = 0; i < 2213; i++) {
				String url = "http://labelx-prod.apistore.qiniu.com/api/v2/datasets/187703/images?offset="+i+"&limit=1&uncertain=false";
				value = loadJSON(url);
				if(value.indexOf("oi7xsjr83") != -1){
					FileOperation.writeTxtFileAppend("F:\\MBH\\数据标定\\七牛云\\元数据\\鉴黄图片检测\\juggdet_2018-11-16_50_wangyu-2.txt", "第"+(i+1)+"页："+value+"\t\n");
					System.out.println("第"+(i+1)+"页："+value);
					count ++;
				}
//				FileOperation.writeTxtFileAppend("F:\\MBH\\数据标定\\七牛云\\元数据\\鉴黄图片检测\\juggdet_2018-11-16_27_wangyu_all.txt", "第"+(i+1)+"页："+value+"\t\n");
			}
			System.out.println("一共"+count+"黄金题。");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String loadJSON(String url) throws IOException {
        // 连接地址（通过阅读html源代码获得，即为登陆表单提交的URL）  
		String surl = "http://labelx-prod.apistore.qiniu.com/api/v2/users/signin";  
		
		/** 
		* 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using 
		* java.net.URL and //java.net.URLConnection 
		*/  
		URL url1 = new URL(surl);  
		HttpURLConnection connection = (HttpURLConnection) url1.openConnection();  
		
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
		out.write("email=wangyu@mbh.ai&password=123456"); // post的关键所在！  
		//remember to clean up  
		out.flush();  
		out.close();  
		
		//取得cookie，相当于记录了身份，供下次访问时使用  
		String cookieVal = connection.getHeaderField("Set-Cookie"); 
		
//		 String s = "http://labelx-prod.apistore.qiniu.com/api/v2/datasets/14794/images?offset=2199&limit=1&uncertain=false";  
		//重新打开一个连接  
		url1 = new URL(url);  
		HttpURLConnection resumeConnection = (HttpURLConnection) url1.openConnection();  
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
