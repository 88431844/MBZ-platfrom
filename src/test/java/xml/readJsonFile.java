package xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;


public class readJsonFile {
	
	private static ArrayList<String> listname = new ArrayList<String>();
	
	//�ļ���·��
	private static String fileName = "E:\\json\\";
	
	private static int summuryNum = 0;

	public static void main(String[] args){
		readAllFile(fileName);
		
		for (int i = 0; i < listname.size(); i++) {
			try {
				//ʵ������
				int oneFileSummuryNum = 0;
				//��ȡԭʼ�ļ�
				BufferedReader br = new BufferedReader(new FileReader(fileName+listname.get(i)));
				System.out.println(listname.get(i)+"  ");
				
				String ZZ = null;//֢״
				String TZ = null;//����
				String XB = null;//ϸ��
				String QGZZ = null;//������֯
				String JCJY = null;//������
				String ZB = null ;//ָ��
				String YPMC = null;//ҩƷ����
				String WSW = null ;//΢���� 
				String HXWZ = null;//��ѧ����
				String JB = null ;//����
				String ZLSD = null;//�����ֶ�
				Map<String,String> jsonMap = new HashMap<String,String>();
				
				String s = null , ws = null;
				while((s = br.readLine()) != null){
					//�����µ�JSON
					JSONObject dataJson = new JSONObject();
				   
				    for( Iterator iter = dataJson.keys(); iter.hasNext();){
				    	String key = (String)iter.next();
				    	
				    	switch (key) {
						case "ZZ":
							jsonMap.put("֢״", new String(dataJson.get(key).toString().getBytes(),"UTF-8"));
							break;
						case "TZ":
							jsonMap.put("����", new String(dataJson.get(key).toString().getBytes(),"UTF-8"));
							break;
						case "XB":
							jsonMap.put("ϸ��", new String(dataJson.get(key).toString().getBytes(),"UTF-8"));
							break;
						case "QGZZ":
							jsonMap.put("������֯", new String(dataJson.get(key).toString().getBytes(),"UTF-8"));
							break;
						case "JCJY":
							jsonMap.put("������", new String(dataJson.get(key).toString().getBytes(),"UTF-8"));
							break;
						case "ZB":
							jsonMap.put("ָ��", new String(dataJson.get(key).toString().getBytes(),"UTF-8"));
							break;
						case "YPMC":
							jsonMap.put("ҩƷ����", new String(dataJson.get(key).toString().getBytes(),"UTF-8"));
							break;
						case "WSW":
							jsonMap.put("΢����", new String(dataJson.get(key).toString().getBytes(),"UTF-8"));
							break;
						case "HXWZ":
							jsonMap.put("��ѧ����", new String(dataJson.get(key).toString().getBytes(),"UTF-8"));
							break;
						case "JB":
							jsonMap.put("����", new String(dataJson.get(key).toString().getBytes(),"UTF-8"));
							break;
						case "ZLSD":
							jsonMap.put("�����ֶ�", new String(dataJson.get(key).toString().getBytes(),"UTF-8"));
							break;
						default:
							break;
						}
				    }
				    
				    for (Map.Entry<String,String> entry : jsonMap.entrySet()) {
				    	if(entry.getValue().isEmpty()){
				    		System.out.print(" "+entry.getKey()+":"+"0");
				    	}else {
				    		oneFileSummuryNum += entry.getValue().split("\",").length;
				    		System.out.print(" "+entry.getKey()+":"+entry.getValue().split("\",").length);
				    	}
					}
				}
				
				 System.out.println("    ��ƪ����ʵ������Ϊ:"+oneFileSummuryNum);
				 summuryNum += oneFileSummuryNum;
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		System.out.println("�����ļ�ʵ������Ϊ:"+summuryNum);
		
	}

	public static void readAllFile(String filepath){
		File file = new File(filepath);
		if(!file.isDirectory()){
			listname.add(file.getName());
		}else if(file.isDirectory()){
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
