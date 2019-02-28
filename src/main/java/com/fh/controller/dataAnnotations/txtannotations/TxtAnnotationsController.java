package com.fh.controller.dataAnnotations.txtannotations;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.SearchParameter;
import com.fh.entity.system.User;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.service.dataAnnotations.txtannotations.TxtAnnotationsManager;

/** 
 * 说明：数据标定模块-文本标注
 * 创建人：FH Q313596790
 * 创建时间：2018-08-27
 */
@Controller
@RequestMapping(value="/txtannotations")
public class TxtAnnotationsController extends BaseController {
	
	String menuUrl = "txtannotations/list.do"; //菜单地址(权限用)
	@Resource(name="txtannotationsService")
	private TxtAnnotationsManager txtannotationsService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增TxtAnnotations");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("TXTANNOTATIONS_ID", this.get32UUID());	//主键
		txtannotationsService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除TxtAnnotations");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		txtannotationsService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改TxtAnnotations");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		txtannotationsService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表TxtAnnotations");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		User user = (User) Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		
		page.setPd(pd);
		List<PageData>	varList = txtannotationsService.list(page);	//列出TxtAnnotations列表
		
		//当天完成总数量
		int overNumToday = txtannotationsService.getOverNumToday(pd);
		pd.put("userId", user.getUSER_ID());
		//当天用户完成的总标注量
		int overNumTodayByUserFromTagging = txtannotationsService.getOverNumToday(pd);
		pd.remove("userId");
		pd.put("checkUserId", user.getUSER_ID());
		//当天用户完成的总审核量
		int overNumTodayByUserFromCheck = txtannotationsService.getOverNumToday(pd);
		//获取剩余有效未标注量
		int unmarkedNum = txtannotationsService.getUnmarkedNum();
		//获取剩余有效未审核量
		int uncheckedNum = txtannotationsService.getUncheckedNum();
		
		pd.put("overNumToday", overNumToday);
		pd.put("overNumTodayByUserFromTagging", overNumTodayByUserFromTagging);
		pd.put("overNumTodayByUserFromCheck", overNumTodayByUserFromCheck);
		pd.put("unmarkedNum", unmarkedNum);
		pd.put("uncheckedNum", uncheckedNum);
		
		mv.setViewName("dataAnnotations/txtannotations/txtannotations_list");
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
		mv.setViewName("dataAnnotations/txtannotations/txtannotations_edit");
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
		pd = txtannotationsService.findById(pd);	//根据ID读取
		mv.setViewName("dataAnnotations/txtannotations/txtannotations_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除TxtAnnotations");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			txtannotationsService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出TxtAnnotations到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("文本ID");	//1
		titles.add("文本时间");	//2
		titles.add("文本标题");	//3
		titles.add("文本路径");	//4
		titles.add("结果路径");	//5
		titles.add("项目类型（0：墨迹天气）");	//6
		titles.add("状态（0：有效，1：无效）");	//7
		dataMap.put("titles", titles);
		List<PageData> varOList = txtannotationsService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).get("TXTID").toString());	//1
			vpd.put("var2", varOList.get(i).getString("TXTDATE"));	//2
			vpd.put("var3", varOList.get(i).getString("TXTTITLE"));	//3
			vpd.put("var4", varOList.get(i).getString("TXTFILEPATH"));	//4
			vpd.put("var5", varOList.get(i).getString("RESULTFILEPATH"));	//5
			vpd.put("var6", varOList.get(i).get("TYPE").toString());	//6
			vpd.put("var7", varOList.get(i).get("STATUS").toString());	//7
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
	
	@RequestMapping(value="/showContentById")
	public ModelAndView shwoContentById(SearchParameter parameter ) throws Exception{
		String startEditTime = DateUtil.getTime();
		Session session = Jurisdiction.getSession();
		session.setAttribute("startEditTime", startEditTime);
		
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		User user = (User) Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
		pd.put("userId", user.getUSER_ID());
		pd.put("userName", user.getUSERNAME());
		
		// 当前页码
		String currentPage = pd.getString("currentPage");
		// 每页显示数量
		String showCount = pd.getString("showCount");
		
		PageData txtPd = txtannotationsService.findById(pd);
		
		String resultContent = (txtPd.getString("resultContent")).replace(" ", "").replaceAll("\\s*", "");
		
		txtPd.remove("resultContent");
		
		txtPd.put("resultContent", resultContent);
		
		pd.put("id", pd.get("TXTANNOTATIONS_ID"));
		
		String getTxtContentType = pd.getString("getTxtContent");
		//修改锁状态的来源，0：标示从审核入口进入，1：标示从标注入口进入
		if("0".equals(getTxtContentType)){
			pd.put("checkUserId", user.getUSER_ID());
			pd.put("checkUserName", user.getUSERNAME());
			pd.put("isLock", 3);
			txtannotationsService.updateCheckLock(pd);
		}else{
			pd.put("isLock", 1);
			txtannotationsService.updateLock(pd);
		}
		
		
		pd.put("currentPage", currentPage);
		pd.put("showCount", showCount);
		mv.addObject("parameter", parameter);
		mv.addObject("txtPd", txtPd);
		mv.addObject("pd", pd);
		mv.setViewName("dataAnnotations/txtannotations/txtannotations_line");
		return mv;
	}
	
	/**
	 * @Title: updateRecord
	 * @Description: 根据文章ID修改文章是否有效状态
	 * @param articleid
	 * @param source
	 * @return: void
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateTxtStatus")
	@ResponseBody
	public void updateTxtStatus() throws Exception {
		PageData pd = getPageData();
		User user = (User) Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
		pd.put("userId", user.getUSER_ID());
		pd.put("userName", user.getUSERNAME());
		pd.put("isLock", pd.get("isLock"));
		//点击返回时，默认标注完成，修改状态为已完成
		txtannotationsService.updateLock(pd);
		try {
			txtannotationsService.updateTxtStatus(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: updateRecord
	 * @Description: 根据文章ID修改文章是否审核状态
	 * @param articleid
	 * @param source
	 * @return: void
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateTxtCheckStatus")
	@ResponseBody
	public void updateTxtCheckStatus() throws Exception {
		PageData pd = getPageData();
		int isLock = 0;
		User user = (User) Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
		pd.put("checkUserId", user.getUSER_ID());
		pd.put("checkUserName", user.getUSERNAME());
		if(pd.getString("checkStatus").equals("0")){
			isLock = 2;
		}else{
			isLock = 3;
		}
		pd.put("isLock", isLock);
		//点击返回时，默认标注完成，修改状态为已完成
		//txtannotationsService.updateLock(pd);
		try {
			txtannotationsService.updateTxtCheckStatus(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据文章ID修改文章的结果
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateResultContent")
	@ResponseBody
	public int updateResultContent() throws Exception{
		int result = 1;
		PageData pd = getPageData();
		pd.put("resultContent", pd.getString("resultContent").replaceAll("\\s", ""));
		pd.put("resultTitle", pd.getString("resultTitle").replaceAll("\\s", ""));
		User user = (User) Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
		
		//判断是否是从返回按钮进入后台
		if(pd.getString("goBack").equals("1")){
			String endEditTime = DateUtil.getTime();
			Session session = Jurisdiction.getSession();
			String startEditTime = (String) session.getAttribute("startEditTime");
			
			String editTime = DateUtil.getTimeSub(startEditTime, endEditTime);
			pd.put("editTime", editTime);
			//写入修改时间
			txtannotationsService.updateEditTime(pd);
		}
		
		String getTxtContentType = pd.getString("getTxtContentType");
		////修改锁状态的来源，0：标示从审核入口进入，1：标示从标注入口进入
		if("0".equals(getTxtContentType)){
			pd.put("checkUserId", user.getUSER_ID());
			pd.put("checkUserName", user.getUSERNAME());
			pd.put("isLock", 4);
			//点击返回时，默认标注完成，修改状态为已完成
			txtannotationsService.updateCheckLock(pd);
		}else{
			pd.put("userId", user.getUSER_ID());
			pd.put("userName", user.getUSERNAME());
			pd.put("isLock", pd.get("isLock"));
			//点击返回时，默认标注完成，修改状态为已完成
			txtannotationsService.updateLock(pd);
		}
		
		txtannotationsService.updateResultContent(pd);
		
		return result;
	}
	
	/**
	 * 获取锁状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getIsLock")
	@ResponseBody
	public int getIsLock() throws Exception{
		PageData pd = this.getPageData();
		User user = (User) Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
		PageData txtPd = txtannotationsService.findById(pd);
		int isLock = (int) txtPd.get("isLock");
		boolean isOrON = false;
		String getTxtContentType =  pd.getString("getTxtContent");
		//修改锁状态的来源，0：标示从审核入口进入，1：标示从标注入口进入
		if("1".equals(getTxtContentType)){
			//判断是不是本人修改
			isOrON = txtPd.getString("userName").equals(user.getUSERNAME());
		}else{
			if(isLock == 3 || isLock == 4){
				//判断是不是本人修改
				isOrON = txtPd.getString("checkUserName").equals(user.getUSERNAME());
			}else{
				//判断是不是本人修改
				isOrON = txtPd.getString("userName").equals(user.getUSERNAME());
			}
		}
		if(isLock == 1 || isLock == 2 || isLock == 3 || isLock == 4){
			if(isOrON){
				isLock = 0;
			}
		}
		return isLock;
	}
}
