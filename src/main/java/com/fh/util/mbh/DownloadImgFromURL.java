package com.fh.util.mbh;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImgFromURL {
	public static void main(String[] args) {        
		String url = "http://oquqvdmso.bkt.clouddn.com/atflow-log-proxy/images/pulp-2018-03-19T19-30-42-ZH7f9Qnz0nWMTHr8gHY0cA==";        
		String path="F:/MBH/数据标定/七牛云/元数据/鉴黄图片分类/20180930/a.jpg";        
		downloadPicture(url,path);    
		}    
	//链接url下载图片   
	public static void downloadPicture(String urlList,String path) {        
		URL url = null;        
		try {            
			url = new URL(urlList);            
			DataInputStream dataInputStream = new DataInputStream(url.openStream());             
			FileOutputStream fileOutputStream = new FileOutputStream(new File(path));            
			ByteArrayOutputStream output = new ByteArrayOutputStream();             
			byte[] buffer = new byte[2048];            
			int length;             
			while ((length = dataInputStream.read(buffer)) > 0) {                
				output.write(buffer, 0, length);            
				}            
			fileOutputStream.write(output.toByteArray());            
			dataInputStream.close();            
			fileOutputStream.close();        
			} catch (MalformedURLException e) {            
				e.printStackTrace();        
			} catch (IOException e) {            
					e.printStackTrace();        
			}    
		}

}
