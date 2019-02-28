package xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetXml {
	public static void main(String[] args){
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try{
	      DocumentBuilder db = dbf.newDocumentBuilder();
	      Document document = db.parse("F:\\8600-8999\\0-0.xml");
	      NodeList booklist = document.getElementsByTagName("Entity");
	      System.out.println("0-0.xml:"+booklist.getLength());
	    }catch (ParserConfigurationException e){
	      e.printStackTrace();
	    }catch (IOException e){
	      e.printStackTrace();
	    }catch (SAXException e){
	      e.printStackTrace();
	    }
	  }
}
