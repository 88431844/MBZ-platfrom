import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fh.util.mbh.FileOperation;
import com.fh.util.mbh.ReadText;

public class MeituanShitiBiaozhuCount {
	
	public static void main(String[] args) throws IOException{
		String filePath = "F:\\MBH\\数据标定\\美团\\团队数据\\实体标注、分词-团队完成数据\\实体标注、分词-团队完成数据\\实体标注团队标注总量\\兴黔未审核-1.txt";
		String resultFilePath = "F:\\MBH\\数据标定\\美团\\团队数据\\实体标注、分词-团队完成数据\\实体标注、分词-团队完成数据\\实体标注团队标注总量\\兴黔未审核-1-统计结果.txt";
		List<String> fileValueList = ReadText.readTxtToList(filePath);
		int valueCount = 0;
		int sum = 0;//总的汇总
		int count = 0;//每行的相当于多少条数据。
		for (int i = 0; i < fileValueList.size(); i++) {
			String regEx = "[\u4e00-\u9fa5]";
	        Pattern p = Pattern.compile(regEx);
	        Matcher m = p.matcher(fileValueList.get(i));
	        while(m.find())
	        {
	        	valueCount ++;
	        }
	        if(valueCount > 0){
	        	count = valueCount/120;
				System.out.println("第"+(i+1)+"行："+valueCount+"个汉字,相当于"+(count+1)+"条数据。");
				try {
					FileOperation.writeTxtFileAppend(resultFilePath, "第"+(i+1)+"行："+valueCount+"个汉字,相当于"+(count+1)+"条数据。");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				valueCount = 0;
				sum = sum + (count+1);
	        }else{
	        	System.out.println("第"+(i+1)+"行：空");
	        	FileOperation.writeTxtFileAppend(resultFilePath, "第"+(i+1)+"行：空");
	        }
	        
		}
		System.out.println("一共有"+fileValueList.size()+"条数据,相当于"+sum+"条。");
		FileOperation.writeTxtFileAppend(resultFilePath, "一共有"+fileValueList.size()+"条数据,相当于"+sum+"条。");
	}
}
