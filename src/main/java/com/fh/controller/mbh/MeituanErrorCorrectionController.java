package com.fh.controller.mbh;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
import com.fh.util.FileUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.mbh.FileOperation;
import com.fh.util.mbh.ReadText;
import com.fh.util.Jurisdiction;
import com.fh.service.mbh.meituanerrorcorrection.MeituanErrorCorrectionManager;

/** 
 * 说明：美团文本标注_查询纠错项目
 * 创建人：FH Q313596790
 * 创建时间：2018-10-19
 */
@Controller
@RequestMapping(value="/meituanErrorCorrectionController")
public class MeituanErrorCorrectionController extends BaseController {
	
	String menuUrl = "meituanErrorCorrectionController/list.do"; //菜单地址(权限用)
	@Resource(name="meituanerrorcorrectionService")
	private MeituanErrorCorrectionManager meituanerrorcorrectionService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增MeituanErrorCorrection");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("MEITUANERRORCORRECTION_ID", this.get32UUID());	//主键
		meituanerrorcorrectionService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除MeituanErrorCorrection");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		meituanerrorcorrectionService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改MeituanErrorCorrection");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		meituanerrorcorrectionService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表MeituanErrorCorrection");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = meituanerrorcorrectionService.list(page);	//列出MeituanErrorCorrection列表
		mv.setViewName("dataAnnotations/meituanerrorcorrection/meituanerrorcorrection_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("dataAnnotations/meituanerrorcorrection/meituanerrorcorrection_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = meituanerrorcorrectionService.findById(pd);	//根据ID读取
		mv.setViewName("dataAnnotations/meituanerrorcorrection/meituanerrorcorrection_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除MeituanErrorCorrection");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			meituanerrorcorrectionService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出MeituanErrorCorrection到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("ID");	//1
		titles.add("城市ID");	//2
		titles.add("城市名称");	//3
		titles.add("搜索词");	//4
		titles.add("改写词");	//5
		titles.add("结果");	//6
		titles.add("修改人id");	//7
		titles.add("修改人姓名");	//8
		titles.add("题锁（0：未标注，1：正在标注，2.标注完成，3.正在审核，4.审核完成）");	//9
		titles.add("状态（0:未回收，1：已回收）");	//10
		titles.add("审核人ID");	//11
		titles.add("审核人姓名");	//12
		titles.add("审核状态（0：未通过，1.已通过）");	//13
		titles.add("修改时间");	//14
		titles.add("写入时间");	//15
		dataMap.put("titles", titles);
		List<PageData> varOList = meituanerrorcorrectionService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("ID"));	//1
			vpd.put("var2", varOList.get(i).getString("CITYID"));	//2
			vpd.put("var3", varOList.get(i).getString("CITYNAME"));	//3
			vpd.put("var4", varOList.get(i).getString("KEYWORD"));	//4
			vpd.put("var5", varOList.get(i).getString("REWRITEKEYWORD"));	//5
			vpd.put("var6", varOList.get(i).getString("RESULT"));	//6
			vpd.put("var7", varOList.get(i).getString("USERID"));	//7
			vpd.put("var8", varOList.get(i).getString("USERNAME"));	//8
			vpd.put("var9", varOList.get(i).get("ISLOCK").toString());	//9
			vpd.put("var10", varOList.get(i).get("STATUS").toString());	//10
			vpd.put("var11", varOList.get(i).getString("CHECKUSERID"));	//11
			vpd.put("var12", varOList.get(i).getString("CHECKUSERNAME"));	//12
			vpd.put("var13", varOList.get(i).get("CHECKSTATUS").toString());	//13
			vpd.put("var14", varOList.get(i).getString("UPDATETIME"));	//14
			vpd.put("var15", varOList.get(i).getString("CREATETIME"));	//15
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/writeDataFromTxt")
	@ResponseBody
	public String writeDataFromTxt() throws Exception{
		PageData pd = this.getPageData();
		List valueListFromTxt = ReadText.readTxtToList(pd.getString("filePath"));
		List<PageData> pdList = new ArrayList<PageData>();
		for (int i = 1980; i < valueListFromTxt.size(); i++) {
			String[] value = valueListFromTxt.get(i).toString().split(" ");
			PageData pdValue = new PageData();
			pdValue.put("MEITUANERRORCORRECTION_ID", this.get32UUID());
			pdValue.put("CITYID", value[0]);
			pdValue.put("CITYNAME", value[1]);
			pdValue.put("KEYWORD", value[2]);
			pdValue.put("REWRITEKEYWORD", value[3]);
			pdValue.put("RESULT", "无结果");
			pdValue.put("USERID", "0");
			pdValue.put("USERNAME", "无标注人员");
			pdValue.put("ISLOCK", 0);
			pdValue.put("STATUS", 0);
			pdValue.put("CHECKUSERID", "0");
			pdValue.put("CHECKUSERNAME", "无审核人员");
			pdValue.put("CHECKSTATUS", 0);
			pdValue.put("UPDATETIME", DateUtil.getTime());
			pdValue.put("CREATETIME", DateUtil.getTime());
			meituanerrorcorrectionService.save(pdValue);
			FileOperation.writeTxtFileAppend("F:\\MBH\\数据标定\\美团\\正式数据\\查询纠错\\log.txt", "第"+(i+1)+"条："+value.toString()+"\t\n");
			pdList.add(pdValue);
		}
//		meituanerrorcorrectionService.writeDataFromTxt(pdList);
		
		return "写入成功";
	}
	
	public static void main(String[] args){
		List valueListFromTxt = ReadText.readTxtToList("F:\\MBH\\数据标定\\美团\\正式数据\\查询纠错\\rewrite_data_annotation_unreview_20181019_v1 - tes-1t.txt");
		for (int i = 0; i < valueListFromTxt.size(); i++) {
			String[] value = valueListFromTxt.get(i).toString().split(" ");
			System.out.println(value[0]+","+value[1]+","+value[2]+","+value[3]);
		}
	}
}
