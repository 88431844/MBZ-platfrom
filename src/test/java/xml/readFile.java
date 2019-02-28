package xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class readFile {
	
	private static ArrayList<String> listname = new ArrayList<String>();
	//��¼����
	private static int countNum = 0;
	//�ļ���·��
	private static String fileName = "F:\\xml\\";
	
	public static void main(String[] args) throws Exception{
		readAllFile(fileName);
		for (int i = 0; i < listname.size(); i++) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
				Document document = db.parse(fileName+listname.get(i));
			    NodeList xmllist = document.getElementsByTagName("object");
			    System.out.println(listname.get(i)+":"+xmllist.getLength());
			    countNum +=  xmllist.getLength();
			}catch (ParserConfigurationException e){
			      e.printStackTrace();
		    }catch (IOException e){
		      e.printStackTrace();
		    }catch (SAXException e){
		      e.printStackTrace();
		    }
		}
		System.out.println(fileName+":"+listname.size()+"ƪ,"+countNum+"��!");
	}
	
	public static void readAllFile(String filepath){
		File file = new File(filepath);
		if(!file.isDirectory()){
			listname.add(file.getName());
		}else if(file.isDirectory()){
			System.out.println("�ļ�");
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath);
				if(!readfile.isDirectory()){
					listname.add(readfile.getName());
				}else if(readfile.isDirectory()){
					readAllFile(filepath+"\\"+filelist[i]);
				}
			}
		}
		
	}

}
