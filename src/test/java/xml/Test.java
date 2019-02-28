package xml;

	 import java.io.ByteArrayOutputStream;    
	 import java.io.File;    
	 import java.io.FileOutputStream;    
	 import java.io.InputStream;    
	 import java.net.HttpURLConnection;    
	 import java.net.URL;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;    
	 /**  
	  * @˵�� �������ȡͼƬ������  
	  * @version 1.0  
	  * @since  
	  */    
	 public class Test {    
	     /**  
	      * ����  
	      * @param args  
	      */    
	     public static void main(String[] args) {    
//	         String url = "http://www.baidu.com/img/baidu_sylogo1.gif";    
//	         byte[] btImg = getImageFromNetByUrl(url);    
//	         
//	         if(null != btImg && btImg.length > 0){    
//	             System.out.println("��ȡ����" + btImg.length + " �ֽ�");    
//	             String fileName = "�ٶ�.gif";    
//	             writeImageToDisk(btImg, fileName);    
//	         }else{    
//	             System.out.println("û�дӸ����ӻ������");    
//	         }    
//	         System.out.println(btImg);
	    	 Test t = new Test();
	    	 System.out.println(t.image2byte("D:\\test\\�ٶ�.gif"));
	    	 t.byte2image(t.image2byte("D:\\test\\�ٶ�.gif"),"D:\\test\\�ٶ�1111.gif");
	     }    
	     
	     public byte[] image2byte(String path){
	    	    byte[] data = null;
	    	    FileImageInputStream input = null;
	    	    try {
	    	      input = new FileImageInputStream(new File(path));
	    	      ByteArrayOutputStream output = new ByteArrayOutputStream();
	    	      byte[] buf = new byte[1024];
	    	      int numBytesRead = 0;
	    	      while ((numBytesRead = input.read(buf)) != -1) {
	    	      output.write(buf, 0, numBytesRead);
	    	      }
	    	      data = output.toByteArray();
	    	      output.close();
	    	      input.close();
	    	    }
	    	
	    	    catch (Exception ex1) {
	    	      ex1.printStackTrace();
	    	    }
	    	    return data;
	    	  }
	     /**  
	      * ��ͼƬд�뵽����  
	      * @param img ͼƬ������  
	      * @param fileName �ļ�����ʱ������  
	      */    
	     public static void writeImageToDisk(byte[] img, String fileName){    
	         try {    
	             File file = new File("D:\\test\\" + fileName);    
	             FileOutputStream fops = new FileOutputStream(file);    
	             fops.write(img);    
	             fops.flush();    
	             fops.close();    
	             System.out.println("ͼƬ�Ѿ�д�뵽D��");    
	         } catch (Exception e) {    
	             e.printStackTrace();    
	         }    
	     }    
	     /**  
	      * ���ݵ�ַ������ݵ��ֽ���  
	      * @param strUrl �������ӵ�ַ  
	      * @return  
	      */    
	     public static byte[] getImageFromNetByUrl(String strUrl){    
	         try {    
	             URL url = new URL(strUrl);    
	             HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
	             conn.setRequestMethod("GET");    
	             conn.setConnectTimeout(5 * 1000);    
	             InputStream inStream = conn.getInputStream();//ͨ����������ȡͼƬ����    
	             byte[] btImg = readInputStream(inStream);//�õ�ͼƬ�Ķ���������    
	             return btImg;    
	         } catch (Exception e) {    
	             e.printStackTrace();    
	         }    
	         return null;    
	     }    
	     /**  
	      * ���������л�ȡ����  
	      * @param inStream ������  
	      * @return  
	      * @throws Exception  
	      */    
	     public static byte[] readInputStream(InputStream inStream) throws Exception{    
	         ByteArrayOutputStream outStream = new ByteArrayOutputStream();    
	         byte[] buffer = new byte[1024];    
	         int len = 0;    
	         while( (len=inStream.read(buffer)) != -1 ){    
	             outStream.write(buffer, 0, len);    
	         }    
	         inStream.close();    
	         return outStream.toByteArray();    
	     }    
	     
	     //byte���鵽ͼƬ
	     public void byte2image(byte[] data,String path){
	       if(data.length<3||path.equals("")) return;
	       try{
	       FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
	       imageOutput.write(data, 0, data.length);
	       imageOutput.close();
	       System.out.println("Make Picture success,Please find image in " + path);
	       } catch(Exception ex) {
	         System.out.println("Exception: " + ex);
	         ex.printStackTrace();
	       }
	     }
	 }    

