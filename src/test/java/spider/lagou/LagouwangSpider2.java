package spider.lagou;

import com.fh.util.mbh.HttpsUtils;

public class LagouwangSpider2 {

	public static void main(String[] args){
		String selectUrl = "https://www.lagou.com/jobs/positionAjax.json?needAddtionalResult=false";
		
		String result = HttpsUtils.sendPost(selectUrl, "first=true&pn=1&kd=数据标注");
		System.out.println(result);
	}
}
