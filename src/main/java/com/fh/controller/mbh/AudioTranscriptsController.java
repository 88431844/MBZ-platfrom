package com.fh.controller.mbh;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.fh.util.DateUtil;
import com.fh.util.FileUtil;
import com.fh.util.mbh.FileOperation;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AudioTranscriptsController {
	//科大讯飞task_id
	private static String task_id = "MBH_test_1";
	//日志路径
	private static String logPath = "E:\\jsonTest\\log.txt";
	//音频存放路径
	private static String path = "E:\\jsonTest";
	//存放需要的后缀名
	public static final String[] suffixName= new String[]{"mp3","mp4"};
	
	public static void main(String[] args) throws IOException{
		//存放已经生成的文件
		Set txtNameOverSet = new TreeSet();
		
		//log记录写入情况
		FileOperation.createTxtFile(logPath);
		
		Map<String,String> fileNameMap = FileUtil.getAllFileName(path,suffixName);
		
		//判断如果存在txt文件直接跳过
		isOrNoFileRepeat:
		for(Map.Entry<String, String> entry : fileNameMap.entrySet()){
			//获取其音频文件后缀名称
			String fileNameSuffix = FileUtil.getFileNameSuffix(entry.getKey());
			//新建转写文件同名txt文件
			String txtPath = entry.getValue().replace(fileNameSuffix, "txt");
			//判断文件是否重复
			if(!txtNameOverSet.contains(txtPath)){
				//新建同名txt文件
				FileOperation.createTxtFile(txtPath);
				
				//获取科大讯飞语音转写结果集
				String resultValue = null;
//				String resultValue = TestLfasr.getLfasrVale(entry.getValue() , task_id);
				JSONArray jsonArray = JSONArray.fromObject(resultValue);
				Object[] os = jsonArray.toArray();
				for(int i = 0; i < os.length; i++){
					//获取时间
					String startTime = DateUtil.getTime();
					
					JSONObject jsonObj = JSONObject.fromObject(os[i]);
					resultValue = (String) jsonObj.get("onebest");
					//把返回结果写入同名txt中
					try {
						FileOperation.writeTxtFile(txtPath, resultValue);
						//写入Set记录是否重复
						txtNameOverSet.add(txtPath);
						//写入日志
						FileOperation.writeTxtFileAppend(logPath,"开始时间："+startTime+" 结束时间："+ DateUtil.getTime()+"  "+entry.getKey()+":"+resultValue+"\r\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				System.out.println("数据都已转录完成！");
			}
		}
	}
}
