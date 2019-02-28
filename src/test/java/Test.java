import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fh.util.mbh.FileOperation;
import com.fh.util.mbh.ReadText;

public class Test {

	
	public static void main(String[] args){
		List readFileValue = ReadText.readTxtToList("F:\\MBH\\数据标定\\墨迹天气\\标注结果\\反馈数据20181017-汇总数据.xml");
		int sum = 0;
		for (int i = 0; i < readFileValue.size(); i++) {
			String oneValue = (String) readFileValue.get(i);
			Pattern pattern = Pattern.compile("<txtId>.*?</txtId>");
			Matcher  matcher = pattern.matcher(oneValue);
			String value = null;
			while(matcher.find()){
				value = matcher.group();
			}
			int fromIndex = 0;
			int count = 0;
			while(true){
				int index = oneValue.indexOf(")/", fromIndex);
				if(-1 != index){
					fromIndex = index + 1;
					count++;
				}else{
					break;
				}
			}
			sum = sum + count ;
			System.out.println(i+",文章ID为："+value.replace("<txtId>", "").replace("</txtId>", "")+",个数："+count);
			try {
				FileOperation.writeTxtFileAppend("F:\\MBH\\数据标定\\墨迹天气\\标注结果\\反馈数据20181017-汇总数据-个数统计.txt", "文章ID为："+value.replace("<txtId>", "").replace("</txtId>", "")+",个数："+count+"\t\n");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("总数："+sum);
		try {
			FileOperation.writeTxtFileAppend("F:\\MBH\\数据标定\\墨迹天气\\标注结果\\反馈数据20181017-汇总数据-个数统计.txt", "总数："+sum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
