package com.fh.util.mbh;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.fh.util.PageData;

public class XmlUtil {
	
	public static void main(String[] args){
		  SAXBuilder builder=new SAXBuilder();
		try {
			Document document = builder.build(new FileInputStream("F:\\MBH\\数据标定\\墨迹天气\\标注结果\\反馈数据20181017-汇总数据.xml"));
			Element row_element = (Element) document.getRootElement();
			
			List<Element> row_elementList = (List<Element>) row_element.getChildren();
			for(Element row : row_elementList){
				System.out.println("==========第"+(row_elementList.indexOf(row)+1)+"条===========");
				System.out.println(row.getValue());
//				List<Element> row_attr = row.getChildren();
//				for(Element attr : row_attr){
//					System.out.println(attr.getName()+":"+attr.getValue());
//				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	/**
	 * 
	 * @param filePath
	 * @param tagName
	 * @return
	 */
	public static List<PageData> getXmlValue(String filePath){
		List<PageData> xmLValuePdList = new ArrayList<PageData>();
		
		SAXBuilder builder=new SAXBuilder();
		try {
			Document document = builder.build(new FileInputStream(filePath));
			Element row_element = (Element) document.getRootElement();
			
			List<Element> row_elementList = (List<Element>) row_element.getChildren();
			for(Element row : row_elementList){
				System.out.println("==========第"+(row_elementList.indexOf(row)+1)+"条===========");
				PageData xmLValuePd = new PageData();
				List<Element> row_attr = row.getChildren();
				for(Element attr : row_attr){
					System.out.println(attr.getName().toString()+":"+attr.getValue().toString());
					xmLValuePd.put(attr.getName().toString(), attr.getValue().toString());
				}
				xmLValuePdList.add(xmLValuePd);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return xmLValuePdList;
	}
}
