package spider.lagou;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fh.util.mbh.FileOperation;
import com.fh.util.mbh.StringToList;

import net.sf.json.JSONObject;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class LagouJsonFilePipeline implements Pipeline{

	@Override
	public void process(ResultItems arg0, Task arg1) {
		// TODO Auto-generated method stub
		System.out.println("开始写入数据！");
		String result =  (String) arg0.getAll().get("result");
		System.out.println(result);
		JSONObject resultJson = JSONObject.fromObject(result);
		String content = resultJson.getString("content");
		JSONObject contentJson = JSONObject.fromObject(content);
		String positionResult = contentJson.getString("positionResult");
		JSONObject positionResultJson = JSONObject.fromObject(positionResult);
		String companyPositionResult = positionResultJson.getString("result");
		List<String> companyPositionResultList = StringToList.stringToList1(companyPositionResult);
		List<LagouCompany> lagouCompanyList = new ArrayList<LagouCompany>();
		for (int i = 0; i < companyPositionResultList.size(); i++) {
			JSONObject companyPositionResultJson = JSONObject.fromObject(companyPositionResultList.get(i)+"}");
			LagouCompany lagouCompany = new LagouCompany();
			lagouCompany.setCompanyId(companyPositionResultJson.getString("companyId"));
			lagouCompany.setPositionName(companyPositionResultJson.getString("positionName"));
			lagouCompany.setWorkYear(companyPositionResultJson.getString("workYear"));
			lagouCompany.setEducation(companyPositionResultJson.getString("education"));
			lagouCompany.setJobNature(companyPositionResultJson.getString("jobNature"));
			lagouCompany.setIndustryField(companyPositionResultJson.getString("industryField"));
			lagouCompany.setFinanceStage(companyPositionResultJson.getString("financeStage"));
			lagouCompany.setCompanySize(companyPositionResultJson.getString("companySize"));
			lagouCompany.setCompanyLogo(companyPositionResultJson.getString("companyLogo"));
			lagouCompany.setCity(companyPositionResultJson.getString("city"));
			lagouCompany.setSalary(companyPositionResultJson.getString("salary"));
			lagouCompany.setPositionId(companyPositionResultJson.getString("positionId"));
			lagouCompany.setCompanyShortName(companyPositionResultJson.getString("companyShortName"));
			lagouCompany.setPositionAdvantage(companyPositionResultJson.getString("positionAdvantage"));
			lagouCompany.setCreateTime(companyPositionResultJson.getString("createTime"));
			lagouCompany.setDistrict(companyPositionResultJson.getString("district"));
			lagouCompany.setScore(companyPositionResultJson.getString("score"));
			lagouCompany.setApprove(companyPositionResultJson.getString("approve"));
			lagouCompany.setPositionLables(StringToList.stringToList2(companyPositionResultJson.getString("positionLables")));
			lagouCompany.setIndustryLables(StringToList.stringToList2(companyPositionResultJson.getString("industryLables")));
			lagouCompany.setPublisherId(companyPositionResultJson.getString("publisherId"));
			lagouCompany.setCompanyLabelList(StringToList.stringToList2(companyPositionResultJson.getString("companyLabelList")));
			lagouCompany.setBusinessZones(StringToList.stringToList2(companyPositionResultJson.getString("businessZones")));
			lagouCompany.setLongitude(companyPositionResultJson.getString("longitude"));
			lagouCompany.setLatitude(companyPositionResultJson.getString("latitude"));
			lagouCompany.setFormatCreateTime(companyPositionResultJson.getString("formatCreateTime"));
			lagouCompany.setCompanyFullName(companyPositionResultJson.getString("companyFullName"));
			lagouCompany.setAdWord(companyPositionResultJson.getString("adWord"));
			lagouCompany.setHitags(companyPositionResultJson.getString("hitags"));
			lagouCompany.setResumeProcessDay(companyPositionResultJson.getString("resumeProcessDay"));
			lagouCompany.setImState(companyPositionResultJson.getString("imState"));
			lagouCompany.setLastLogin(companyPositionResultJson.getString("lastLogin"));
			lagouCompany.setExplain(companyPositionResultJson.getString("explain"));
			lagouCompany.setPlus(companyPositionResultJson.getString("plus"));
			lagouCompany.setPcShow(companyPositionResultJson.getString("pcShow"));
			lagouCompany.setDeliver(companyPositionResultJson.getString("deliver"));
			lagouCompany.setGradeDescription(companyPositionResultJson.getString("gradeDescription"));
			lagouCompany.setPromotionScoreExplain(companyPositionResultJson.getString("promotionScoreExplain"));
			lagouCompany.setFirstType(companyPositionResultJson.getString("firstType"));
			lagouCompany.setSecondType(companyPositionResultJson.getString("secondType"));
			lagouCompany.setIsSchoolJob(companyPositionResultJson.getString("isSchoolJob"));
			lagouCompany.setSubwayline(companyPositionResultJson.getString("subwayline"));
			lagouCompany.setStationname(companyPositionResultJson.getString("stationname"));
			lagouCompany.setLinestaion(companyPositionResultJson.getString("linestaion"));
			lagouCompany.setThirdType(companyPositionResultJson.getString("thirdType"));
			lagouCompany.setSkillLables(companyPositionResultJson.getString("skillLables"));
			try {
				System.out.println("写入数据为："+companyPositionResultJson.getString("companyShortName")+"_"+companyPositionResultJson.getString("jobNature")+"_"+companyPositionResultJson.getString("city"));
				FileOperation.writeTxtFileAppend("F:\\学习资料\\webmagic\\www.lagou.com\\lagou_shujubiaozhu.txt", lagouCompany.toString()+"\t\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("数据写入完成！"+companyPositionResult);
	}

}
