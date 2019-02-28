import java.util.ArrayList;
import java.util.List;

import com.fh.util.mbh.DownloadImgFromURL;
import com.fh.util.mbh.ReadText;
import com.fh.util.mbh.WriteStringOnImg;

public class SmallDogAttributeClass2 {
	
	public static void main(String[] args){
		String filePath = "F:\\MBH\\数据标定\\小狗机器人\\人脸标注项目\\标注结果\\1-53271头部装饰-20190105.txt";
		String path = "F:/MBH/数据标定/小狗机器人/人脸标注项目/元数据/第二批数据/";
		String tagPath = "F:/MBH/数据标定/小狗机器人/人脸标注项目/元数据/第二批数据/标注数据3/";
		List<String> valueList = ReadText.readTxtToList(filePath);
		for (int i = 0; i < valueList.size(); i++) {
			System.out.println(valueList.get(i));
			String[] fileNameList = valueList.get(i).split(" ");
			String url = fileNameList[0];
			String fileName = url.split("/")[5];
			String age = fileNameList[1];
			String sex = null;
			String head = null;
			String eye = null;
			String face = null;
			String hair = null; 
			String definition = fileNameList[6];//清晰度
			if("0".equals(fileNameList[2])){
				sex = "(0)男性";
			}else if("1".equals(fileNameList[2])){
				sex = "(1)女性";
			}else{
				sex = "无效性别";
			}
				
			if("0".equals(fileNameList[3])){
				head = "(0)无头部装饰";
			}else if("1".equals(fileNameList[3])){
				head = "(1)带帽子";
			}else if("2".equals(fileNameList[3])){
				head = "(2)带头盔";
			}else{
				head = "无效头部装饰";
			}
			
			if("0".equals(fileNameList[4])){
				eye = "(0)无眼部装饰";
			}else if("1".equals(fileNameList[4])){
				eye = "(1)带正常眼睛";
			}else if("2".equals(fileNameList[4])){
				eye = "(2)带魔镜";
			}else{
				eye = "无效眼部装饰";
			}
			
//			if("0".equals(fileNameList[5])){
//				face = "(0)无面部装饰";
//			}else if("1".equals(fileNameList[5])){
//				face = "(1)带口罩";
//			}else{
//				face = "无效面部装饰";
//			}
			
			if("-1".equals(fileNameList[5])){
				hair = "(-1)无法判别类";
			}else if("0".equals(fileNameList[5])){
				hair = "(0)秃头";
			}else if("1".equals(fileNameList[5])){
				hair = "(1)短发";
			}else if("2".equals(fileNameList[5])){
				hair = "(2)中发";
			}else if("3".equals(fileNameList[5])){
				hair = "(3)中长发";
			}else if("4".equals(fileNameList[5])){
				hair = "(4)长发";
			}else{
				hair = "无效头发长度";
			}
			System.out.println("第"+(i+1)+"条："+fileName.replace(".jpg", ""));
//			DownloadImgFromURL.downloadPicture(url, path+fileName);
			List<String> contentList = new ArrayList<String>();
//			contentList.add(age);
//			contentList.add(sex);
			contentList.add(head);
//			contentList.add(eye);
//			contentList.add(hair);
//			contentList.add(definition);
			WriteStringOnImg.writeStringToImg(path+fileName, tagPath+head+"/"+fileName,head,30);
		}
	}
}
