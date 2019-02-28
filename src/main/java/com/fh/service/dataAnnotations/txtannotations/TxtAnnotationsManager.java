package com.fh.service.dataAnnotations.txtannotations;

import java.util.List;
import com.fh.entity.Page;
import com.fh.util.PageData;

/** 
 * 说明： 数据标定模块-文本标注接口
 * 创建人：FH Q313596790
 * 创建时间：2018-08-27
 * @version
 */
public interface TxtAnnotationsManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
	/**
	 * 根据文章ID修改文章是否有效状态
	 * @param pd
	 * @throws Exception
	 */
	public void updateTxtStatus(PageData pd) throws Exception;
	
	/**
	 * 根据文章ID修改是否审核状态
	 * @param pd
	 * @throws Exception
	 */
	public void updateTxtCheckStatus(PageData pd) throws Exception;
	
	/**
	 * 根据文章ID修改文本内容
	 */
	public void updateResultContent(PageData pd) throws Exception;
	
	/**
	 * 根据文章ID写入修改时间
	 * @param pd
	 * @throws Exception
	 */
	public void updateEditTime(PageData pd) throws Exception;
	
	/**
	 * 根据文章ID修改锁状态(标注时)
	 */
	public void updateLock(PageData pd) throws Exception;
	/**
	 * 根据文章ID修改锁状态(审核时)
	 */
	public void updateCheckLock(PageData pd) throws Exception;
	
	/**
	 * 获取全体当天完成数量
	 */
	public int getOverNumToday(PageData pd) throws Exception;
	
	/**
	 * 获取未标注的有效数据总量
	 */
	public int getUnmarkedNum() throws Exception;
	
	/**
	 * 获取有效未审核数据量
	 */
	public int getUncheckedNum() throws Exception;
	
	/**
	 * 根据txtId修改数据
	 * @throws Exception
	 */
	public void updateByXml(List<PageData> pdList) throws Exception;
}

