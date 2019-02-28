package com.fh.controller.mbh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.service.dataAnnotations.txtannotations.TxtAnnotationsManager;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.fh.util.mbh.FileOperation;
import com.fh.util.mbh.ReadText;
import com.fh.util.mbh.XmlUtil;
import com.fh.util.mbh.word.analysis.CosineTextSimilarity;
import com.fh.util.mbh.word.analysis.TextSimilarity;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/mojiTianQIController")
public class MojiTianQIController extends BaseController{
	//存储数据文件路径
	private static final String filePath = "E:\\txt\\墨迹天气有效数据汇总8862.txt";
	//存储读出数据的路径地址
	private static final String fileValuePath = "E:\\txt\\";
	//天气预报文本
	private static final String isfileValuePath = "E:\\txt\\天气预报文章汇总1.txt";
	//相关文本
	private static final String relevantfileValuePath = "E:\\txt\\相关文章汇总1.txt";
	//弱相关文件
	private static final String littleRelevantchainFileValuePath = "E:\\txt\\弱相关文章汇总1.txt";
	//非相关文件
	private static final String unrelevantchainFileValuePath = "E:\\txt\\非相关文章汇总1.txt";
	//非相关文件
	private static final String fileValuePath2 = "E:\\txt\\相关性大于2.txt";
	//非相关文件
	private static final String fileValuePath12 = "E:\\txt\\相关性大于1-2.txt";
	
	@Resource(name="txtannotationsService")
	private TxtAnnotationsManager txtannotationsService;
	
	@RequestMapping(value="/test")
	public void test(){
		System.out.println("ceshi");
	}
	
	@RequestMapping(value="/getMojiTianPage")
	public String getMojiTianPage() throws Exception{
		PageData pd = new PageData();
		String result = null;
		List fileValueList = ReadText.readTxtToList(filePath);
		for (int i = 0; i < fileValueList.size(); i++) {
			System.out.println(i);
			String[] value = ((String) fileValueList.get(i)).split(" _ ");
			if(value != null && !"".equals(value[0])){
				pd.put("relevance", value[0]);
				pd.put("TXTID", value[1]);
				pd.put("TXTDATE", value[2]);
				pd.put("TXTTITLE", value[3]);
				pd.put("txtContent", value[4]);
				pd.put("TXTFILEPATH", "");
				pd.put("relevanceContent", "");
				pd.put("resultTitle", "");
				pd.put("resultContent", "");
				pd.put("RESULTFILEPATH", "");
				pd.put("TYPE", 0);
				pd.put("isLock", 0);
				pd.put("STATUS", 1);
				pd.put("userId", 1);
				pd.put("userName", "超级管理员");
				pd.put("checkUserId", 1);
				pd.put("checkUserName", "超级管理员");
				pd.put("editTime", 0);
				pd.put("checkStatus", 0);
				pd.put("creatTime", DateUtil.getTime());
				pd.put("updateTime", DateUtil.getTime());
				pd.put("TXTANNOTATIONS_ID", this.get32UUID());	//主键
				txtannotationsService.save(pd);
			}
		}
		result = "写入完成!";
		return result;
	}
	
	@RequestMapping(value="/updateByXml")
	@ResponseBody
	public String updateByXml() throws Exception{
		PageData pd = this.getPageData();
		String result = null;
		String filePath = pd.getString("filePath");
		List<PageData> pdList = XmlUtil.getXmlValue(filePath);
		txtannotationsService.updateByXml(pdList);
		result = "数据更新"+pdList.size()+"成功！";
		return result;
	}

	public static void main(String[] args) throws IOException{
		List fileValueList = ReadText.readTxtToList(filePath);
		TextSimilarity textSimilarity = new CosineTextSimilarity();
		//分割符
		String delimiter = " _ ";
		String path = null;
		//存放不重复
		List list = new ArrayList();
		
		for (int i = 0; i < fileValueList.size(); i++) {
			JSONObject jsonObject = JSONObject.fromObject(fileValueList.get(i));
			
//			String fileName = (jsonObject.get("id")+"_"+jsonObject.get("date")+"_"+jsonObject.get("title")+".txt").replace(":", "：");
			if(jsonObject != null){
				//判断文章是否与天气预报有关
				double score = textSimilarity.similarScore(jsonObject.getString("content"), "天气预报，小雨转雨夹雪或小雪，气温持续偏低，雷阵雨，小雨转中雨，最高气，中雨转大雨，东部有阵雨或雷阵雨，东南风2-3级，温度18～29℃");
				
				String content = score+delimiter+jsonObject.get("id")+delimiter+jsonObject.get("date")+delimiter+jsonObject.get("title")+delimiter+jsonObject.get("content")+"\n\r";
//				FileOperation.writeTxtFileAppend(fileValuePath+"相关性评分汇总1.txt", score+delimiter+jsonObject.get("id")+delimiter+jsonObject.get("date")+delimiter+jsonObject.get("title")+"\n\r");
				
				boolean flag = jsonObject.getString("title").contains("天气预报");
				
//				if(flag){
//					FileOperation.writeTxtFileAppend(isfileValuePath, content);
//				}else{
//					if(score == 0.0){
//						FileOperation.writeTxtFileAppend(unrelevantchainFileValuePath, content);
//					}else if(score < 0.1){
//						FileOperation.writeTxtFileAppend(littleRelevantchainFileValuePath, content);
//					}else{
//						
//					}
//				}
				
				if(score > 0.2){
					list.add(content);
				}
				
				
//				FileOperation.createTxtFile(path+fileName);
//				try {
//					FileOperation.writeTxtFile(path+fileName, jsonObject.getString("title")+"\r\n"+jsonObject.getString("content"));
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		} 
		List listrom1 = getOverFileValue();
		list.removeAll(listrom1);
		for (int i = 0; i < list.size(); i++) {
			FileOperation.writeTxtFileAppend("E:\\txt\\相关性大于2去重复1.txt", list.get(i).toString());
		}
		
		System.out.println("总数量为："+fileValueList.size());
	}
	
	public static List getOverFileValue(){
		String path = "E:\\txt\\1.txt";
		
		List overFileValueList = ReadText.readTxtToList(path);
		return overFileValueList;
	}
}
