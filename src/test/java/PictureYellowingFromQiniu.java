import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fh.util.DateUtil;
import com.fh.util.FileUtil;
import com.fh.util.mbh.AnalogLanding;
import com.fh.util.mbh.DownloadImgFromURL;
import com.fh.util.mbh.FileOperation;
import com.fh.util.mbh.ReadText;
import com.fh.util.mbh.WriteStringOnImg;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PictureYellowingFromQiniu {
	
	public static void main(String[] args){
		String loadingUrl = "http://labelx-prod.apistore.qiniu.com/api/v2/users/signin";
		String selectUrl = "";
		String UserAndPower = "email=lilinwei@mbh.ai&password=231519149";
		String fileName = "jugg-1204-sexy-0108-8";
		String fileNum = "21704";
		String filePath = "F:/MBH/数据标定/七牛云/元数据/鉴黄图片分类/14分类/lilinwei20190201/";
		String imgePath = filePath+fileName;
		String newImgePath = filePath+fileName+"/结果集";
		String pathFile = filePath+fileName+".txt";
		String allPathFile = filePath+fileName+".txt";
		
		try {
			for (int i = 0; i < 367; i++) {
				selectUrl = "http://labelx-prod.apistore.qiniu.com/api/v2/tasks/"+fileNum+"/classify?limit=6&offset="+(i*6)+"&uncertain=false&sorted=false";
				String value = AnalogLanding.loadJSON(loadingUrl, selectUrl, UserAndPower);
				JSONObject valueJson = JSONObject.fromObject(value);
				JSONArray valueJsonItems = valueJson.getJSONArray("items");
				for (int j = 0; j < valueJsonItems.size(); j++) {
					String itemsValue = valueJsonItems.getString(j);
					JSONObject itemsValueJson = JSONObject.fromObject(itemsValue);
					String imageValue = itemsValueJson.getString("image");
					JSONObject imageValueJson = JSONObject.fromObject(imageValue);
					//获取标注结果
					String dataValue = itemsValueJson.getString("data").replace("[", "").replace("]", "");
					JSONObject dataValueO = JSONObject.fromObject(dataValue);
					String optionValue = dataValueO.getString("option");
					JSONObject optionValueJson = JSONObject.fromObject(optionValue);
					String displayTitle = optionValueJson.getString("displayTitle").replaceAll("/", "&");
					String url = imageValueJson.getString("url");
					String isGoldQuestion = "";
					if(url.indexOf("pbxzdfez3") != -1){
						isGoldQuestion = "_黄金题";
					}
					if(url.indexOf("http://") != -1){
						System.out.println("正在处理第"+(i+1)+"页：第"+(j+1)+"张："+url);
//						FileOperation.writeTxtFileAppend(pathFile, "第"+(i+1)+"页：第"+(j+1)+"张："+url+"\t\n");
						FileUtil.getFolderFlag(url);
						FileUtil.getFolderFlag(newImgePath+"/"+displayTitle);
						boolean folderFlag = FileUtil.getFileNameFlage(imgePath+"/第"+(i+1)+"页_第"+(j+1)+"张_"+displayTitle+".jpg");
						if(!folderFlag){
//							DownloadImgFromURL.downloadPicture(url, imgePath+"/第"+(i+1)+"页_第"+(j+1)+"张_"+displayTitle+isGoldQuestion+".jpg");
							System.out.println("第"+(i+1)+"页：第"+(j+1)+"张：下载完成！"+url);
						}
						if(!FileUtil.getFileNameFlage(newImgePath+"/"+displayTitle+"/第"+(i+1)+"页_第"+(j+1)+"张_"+displayTitle+".jpg")){
							File file = new File(newImgePath+"/"+displayTitle+"/第"+(i+1)+"页_第"+(j+1)+"张_"+displayTitle+".jpg");
							if(!file.exists()  && !file.isDirectory()){
//								WriteStringOnImg.writeStringToImg(imgePath+"/第"+(i+1)+"页_第"+(j+1)+"张_"+displayTitle+isGoldQuestion+".jpg", newImgePath+"/"+displayTitle+"/第"+(i+1)+"页_第"+(j+1)+"张_"+displayTitle+isGoldQuestion+".jpg", displayTitle+"\t\n"+DateUtil.getTime(),20);
								System.out.println("第"+(i+1)+"页：第"+(j+1)+"张：图片处理完成！");
							}
						}
					}
					FileOperation.writeTxtFileAppend(allPathFile, "第"+(i+1)+"页：第"+(j+1)+"张："+url+"\t\n");
					System.out.println("第"+(i+1)+"页：第"+(j+1)+"张："+url);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return result;
	}
	
	public  void pictureYellowingFromQiniu(){
		String loadingUrl = "http://labelx-prod.apistore.qiniu.com/api/v2/users/signin";
		String selectUrl = "";
//		String UserAndPower = "email=gaishjie@mbh.ai&password=589967631";
		String fileName = "jh-getu-20180927-mbh-ssac";
//		String fileNum = "14998";
		String filePath = "F:/MBH/数据标定/七牛云/元数据/鉴黄图片分类/黄金题汇总_20181002/";
		String imgePath = filePath+"/数据集";
		String newImgePath = filePath+"/结果集";
		
		String allImgePath = filePath+"/全部数据集";
		String allNewImgePath = filePath+"/全部结果集";
		
		String pathFile = filePath+"黄金题结果汇总.txt";
		String allPathFile = filePath+"全部结果汇总.txt";
		
		String userFilePath = "F:\\MBH\\数据标定\\七牛云\\元数据\\鉴黄图片分类\\黄金题汇总_20181002\\账号及题.txt";
		String failFilePath = "F:\\MBH\\数据标定\\七牛云\\元数据\\鉴黄图片分类\\黄金题汇总_20181002\\失败数据.txt";
		
		
		List userList = ReadText.readTxtToList(userFilePath);
		
		for (int i = 0; i < userList.size(); i++) {
			String[] oneUserList = userList.get(i).toString().split(" ");
			String UserAndPower =  "email="+oneUserList[0]+"&password="+oneUserList[1];
			for (int j = 2; j < oneUserList.length; j++) {
				String fileNum = oneUserList[j];
				try {
					for (int i1 = 0; i1 < 733; i1++) {
						selectUrl = "http://labelx-prod.apistore.qiniu.com/api/v2/tasks/"+fileNum+"/classify?limit=6&offset="+(i1*6)+"&uncertain=false";
						String value = AnalogLanding.loadJSON(loadingUrl, selectUrl, UserAndPower);
						JSONObject valueJson = JSONObject.fromObject(value);
						JSONArray valueJsonItems = valueJson.getJSONArray("items");
						for (int j1 = 0; j1 < valueJsonItems.size(); j1++) {
							String itemsValue = valueJsonItems.getString(j1);
							JSONObject itemsValueJson = JSONObject.fromObject(itemsValue);
							String imageValue = itemsValueJson.getString("image");
							JSONObject imageValueJson = JSONObject.fromObject(imageValue);
							//获取标注结果
							String dataValue = itemsValueJson.getString("data").replace("[", "").replace("]", "");
							JSONObject dataValueO = JSONObject.fromObject(dataValue);
							String optionValue = dataValueO.getString("option");
							JSONObject optionValueJson = JSONObject.fromObject(optionValue);
							String displayTitle = optionValueJson.getString("displayTitle");
							String url = imageValueJson.getString("url");
							String imgId = imageValueJson.getString("id");
							System.out.println("正在处理账户为："+UserAndPower+"，中编号："+fileNum+"，第"+(i1+1)+"页：第"+(j1+1)+"张："+url);
							if(url.indexOf("http://") != -1){
								FileOperation.writeTxtFileAppend(pathFile, "id:"+imgId+",displayTitle:"+displayTitle+",url:"+url+"\t\n");
								FileUtil.getFolderFlag(imgePath+"/"+displayTitle);
								FileUtil.getFolderFlag(newImgePath+"/"+displayTitle);
								boolean folderFlag = FileUtil.getFileNameFlage(imgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitle+".jpg");
								if(!folderFlag){
									DownloadImgFromURL.downloadPicture(url, imgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitle+".jpg");
									System.out.println("第"+(i1+1)+"页：第"+(j1+1)+"张："+imgId+"_"+displayTitle+".jpg"+"下载完成！"+url);
								}
								if(!FileUtil.getFileNameFlage(newImgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitle+".jpg")){
									try {
										WriteStringOnImg.writeStringToImg(imgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitle+".jpg", newImgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitle+".jpg", displayTitle,80);
										System.out.println("第"+(i+1)+"页：第"+(j+1)+"张："+imgId+"_"+displayTitle+".jpg"+"图片处理完成！");
									} catch (Exception e) {
										System.out.println("第"+(i+1)+"页：第"+(j+1)+"张："+imgId+"_"+displayTitle+".jpg"+"图片处理异常！");
										continue;
									}
								}
							}
							String displayTitleWrite = null;
							if(url.indexOf("http://") == -1){
								url = "http://labelx-prod.apistore.qiniu.com" + url;
								displayTitleWrite = displayTitle;
							}else{
								displayTitleWrite = displayTitle+"_黄金题";
							}
							//存储所以数据
							FileUtil.getFolderFlag(allImgePath+"/"+displayTitle);
							FileUtil.getFolderFlag(allNewImgePath+"/"+displayTitle);
							boolean folderFlag = FileUtil.getFileNameFlage(allImgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitleWrite+".jpg");
							//判断文件夹是否存在，不存在则进入
							if(!folderFlag){
								DownloadImgFromURL.downloadPicture(url, allImgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitleWrite+".jpg");
								System.out.println("第"+(i1+1)+"页：第"+(j1+1)+"张："+imgId+"_"+displayTitleWrite+".jpg"+"下载完成！"+url);
							}
							//判断文件是否存储
							if(!FileUtil.getFileNameFlage(allNewImgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitleWrite+".jpg")){
								try {
									WriteStringOnImg.writeStringToImg(allImgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitleWrite+".jpg", allNewImgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitleWrite+".jpg", displayTitleWrite,80);
									System.out.println("第"+(i1+1)+"页：第"+(j1+1)+"张："+imgId+"_"+displayTitleWrite+".jpg"+"图片处理完成！");
								} catch (Exception e) {
									System.out.println("第"+(i1+1)+"页：第"+(j1+1)+"张："+imgId+"_"+displayTitleWrite+".jpg"+"图片处理失败！");
									FileOperation.writeTxtFileAppend(failFilePath, "第"+(i1+1)+"页：第"+(j1+1)+"张："+imgId+"_"+displayTitleWrite+".jpg"+"图片处理失败！"+"id:"+imgId+",displayTitle:"+displayTitle+",url:"+url+"\t\n");
									continue;
								}
							}
							FileOperation.writeTxtFileAppend(allPathFile, "id:"+imgId+",displayTitle:"+displayTitle+",url:"+url+"\t\n");
//							System.out.println("第"+(i+1)+"页：第"+(j+1)+"张："+url);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	
//	public static void main(String[] args){
//		String loadingUrl = "http://labelx-prod.apistore.qiniu.com/api/v2/users/signin";
//		String selectUrl = "";
//		String UserAndPower = "email=gaishjie@mbh.ai&password=589967631";
//		String fileName = "jh-getu-20180927-mbh-ssac";
//		String fileNum = "14998";
//		String filePath = "F:/MBH/数据标定/七牛云/元数据/鉴黄图片分类/黄金题汇总_20181002/";
//		String imgePath = filePath+"/数据集";
//		String newImgePath = filePath+"/结果集";
//		String pathFile = filePath+"黄金题结果汇总.txt";
//		String allPathFile = filePath+fileName+".txt";
//		
//		try {
//			for (int i = 0; i < 733; i++) {
//				selectUrl = "http://labelx-prod.apistore.qiniu.com/api/v2/tasks/"+fileNum+"/classify?limit=6&offset="+(i*6)+"&uncertain=false";
//				String value = AnalogLanding.loadJSON(loadingUrl, selectUrl, UserAndPower);
//				JSONObject valueJson = JSONObject.fromObject(value);
//				JSONArray valueJsonItems = valueJson.getJSONArray("items");
//				for (int j = 0; j < valueJsonItems.size(); j++) {
//					String itemsValue = valueJsonItems.getString(j);
//					JSONObject itemsValueJson = JSONObject.fromObject(itemsValue);
//					String imageValue = itemsValueJson.getString("image");
//					JSONObject imageValueJson = JSONObject.fromObject(imageValue);
//					//获取标注结果
//					String dataValue = itemsValueJson.getString("data").replace("[", "").replace("]", "");
//					JSONObject dataValueO = JSONObject.fromObject(dataValue);
//					String optionValue = dataValueO.getString("option");
//					JSONObject optionValueJson = JSONObject.fromObject(optionValue);
//					String displayTitle = optionValueJson.getString("displayTitle");
//					String url = imageValueJson.getString("url");
//					String imgId = imageValueJson.getString("id");
//					if(url.indexOf("http://") != -1){
//						System.out.println("正在处理第"+(i+1)+"页：第"+(j+1)+"张："+url);
//						FileOperation.writeTxtFileAppend(pathFile, "id:"+imgId+",displayTitle:"+displayTitle+",url:"+url+"\t\n");
//						FileUtil.getFolderFlag(imgePath+"/"+displayTitle);
//						FileUtil.getFolderFlag(newImgePath+"/"+displayTitle);
//						boolean folderFlag = FileUtil.getFileNameFlage(imgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitle+".jpg");
//						if(!folderFlag){
//							DownloadImgFromURL.downloadPicture(url, imgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitle+".jpg");
//							System.out.println("第"+(i+1)+"页：第"+(j+1)+"张：下载完成！"+url);
//						}
//						if(!FileUtil.getFileNameFlage(newImgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitle+".jpg")){
//							File file = new File(newImgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitle+".jpg");
//							if(!file .exists()  && !file .isDirectory()){
//								WriteStringOnImg.writeStringToImg(imgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitle+".jpg", newImgePath+"/"+displayTitle+"/"+imgId+"_"+displayTitle+".jpg", displayTitle);
//								System.out.println("第"+(i+1)+"页：第"+(j+1)+"张：图片处理完成！");
//							}
//						}
//					}
////					FileOperation.writeTxtFileAppend(allPathFile, "第"+(i+1)+"页：第"+(j+1)+"张："+url+"\t\n");
////					System.out.println("第"+(i+1)+"页：第"+(j+1)+"张："+url);
//				}
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
