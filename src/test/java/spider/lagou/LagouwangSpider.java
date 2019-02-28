package spider.lagou;

import java.util.HashMap;
import java.util.Map;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

public class LagouwangSpider implements PageProcessor{
	
	 private Site site = Site.me()
	            .setRetryTimes(3)
	            .setSleepTime(3000)
	            .addHeader("Accept","application/json, text/javascript, */*; q=0.01")
	            .addHeader("Accept-Encoding","gzip, deflate, br")
	            .addHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
	            .addHeader("Connection","keep-alive")
	            .addHeader("Content-Length","55")
	            .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
	            .addHeader("Cookie","Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1516684224,1516688332,1516708458,1517989813; _ga=GA1.2.803780703.1515996477; user_trace_token=20180115140756-6e315eee-f9ba-11e7-a353-5254005c3644; LGUID=20180115140756-6e316229-f9ba-11e7-a353-5254005c3644; index_location_city=%E5%85%A8%E5%9B%BD; JSESSIONID=ABAAABAAADEAAFI7B8A950147564B82F61A115D162E1281; LGSID=20180207155015-888d0972-0bdb-11e8-bdd2-525400f775ce; LGRID=20180207163606-f07d2f3d-0be1-11e8-af98-5254005c3644; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1517992563; TG-TRACK-CODE=index_navigation; SEARCH_ID=ada31aea74d74f0ba5625adf851d1c6f; X_HTTP_TOKEN=4235610f3926fcdc9a4b942f0c350399; _putrc=0DA9DA012C722A8E; login=true; unick=%E7%8E%8B%E5%86%B6; showExpriedIndex=1; showExpriedCompanyHome=1; showExpriedMyPublish=1; hasDeliver=0; gate_login_token=fc49718b5340e22bfe7adebb2937015b765f94906d1f154c; _gat=1")
	            .addHeader("Host","www.lagou.com")
	            .addHeader("Origin","https://www.lagou.com")
	            .addHeader("Referer","https://www.lagou.com/jobs/list_%E6%95%B0%E6%8D%AE%E6%A0%87%E6%B3%A8?labelWords=&fromSearch=true&suginput=")
	            .addHeader("User-Agent","-Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3095.5 Mobile Safari/537.36")
	            .addHeader("X-Anit-Forge-Code","0")
	            .addHeader("X-Anit-Forge-Token","None")
	            .addHeader("X-Requested-With","XMLHttpRequest");

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	@Override
	public void process(Page page) {
        Request requests = new Request();
        Map<String,Object> map = new HashMap<String, Object>();
        for(int i=0;i<1;i++)
        {
            requests = new Request("https://www.lagou.com/jobs/positionAjax.json?needAddtionalResult=false");
            requests.setMethod(HttpConstant.Method.POST);
            if(i==0)
            {
                map.put("first","true");
                map.put("pn",i+1);
                map.put("kd","数据标注");
                requests.setRequestBody(HttpRequestBody.form(map,"utf-8"));
                page.addTargetRequest(requests);
                page.putField("result", page.getRawText());
            }
            else
            {
                map.put("first","false");
                map.put("pn",i+1);
                map.put("kd","数据标注");
                requests.setRequestBody(HttpRequestBody.form(map,"utf-8"));
                page.addTargetRequest(requests);
            }
            System.out.println("抓取第"+(i+1)+"页数据。");
        }
	}
	 
	public static void main(String[] args){
		Spider.create(new LagouwangSpider())
			  .addUrl("https://www.lagou.com/jobs/positionAjax.json?needAddtionalResult=false")
			  .addPipeline(new LagouJsonFilePipeline())
			  .thread(1)
			  .run();
	}
}
