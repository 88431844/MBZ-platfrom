package com.fh.service.dataAnnotations.txtannotations.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.dataAnnotations.txtannotations.TxtAnnotationsManager;

/** 
 * 说明： 数据标定模块-文本标注
 * 创建人：FH Q313596790
 * 创建时间：2018-08-27
 * @version
 */
@Service("txtannotationsService")
public class TxtAnnotationsService implements TxtAnnotationsManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void save(PageData pd)throws Exception{
		dao.save("TxtAnnotationsMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void delete(PageData pd)throws Exception{
		dao.delete("TxtAnnotationsMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void edit(PageData pd)throws Exception{
		dao.update("TxtAnnotationsMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("TxtAnnotationsMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("TxtAnnotationsMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("TxtAnnotationsMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("TxtAnnotationsMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/**
	 * 根据文章ID修改文章是否有效状态
	 */
	public void updateTxtStatus(PageData pd)throws Exception{
		dao.update("TxtAnnotationsMapper.updateTxtStatus", pd);
	}
	
	/**
	 * 根据文章ID修改是否审核的状态
	 * @param pd
	 * @throws Exception
	 */
	public void updateTxtCheckStatus(PageData pd)throws Exception{
		dao.update("TxtAnnotationsMapper.updateTxtCheckStatus", pd);
	}
	
	/**
	 * 异步修改文字
	 */
	public void updateResultContent(PageData pd) throws Exception{
		dao.update("TxtAnnotationsMapper.updateResultContent", pd);
	}
	
	/**
	 * 写入修改时间
	 */
	public void updateEditTime(PageData pd) throws Exception{
		dao.update("TxtAnnotationsMapper.updateEditTime", pd);
	}
	
	/**
	 * 修改文章上锁(标注时)
	 */
	public void updateLock(PageData pd) throws Exception{
		dao.update("TxtAnnotationsMapper.updateLock", pd);
	}
	
	/**
	 * 修改文章上锁(审核时)
	 */
	public void updateCheckLock(PageData pd) throws Exception{
		dao.update("TxtAnnotationsMapper.updateCheckLock", pd);
	}
	
	/**
	 * 获取当天所有完成数据
	 */
	public int getOverNumToday(PageData pd) throws Exception{
		return (int) dao.findForObject("TxtAnnotationsMapper.getOverNumToday", pd);
	}
	
	/**
	 * 获取有效未标注数据
	 */
	public int getUnmarkedNum() throws Exception{
		return (int) dao.findForObject("TxtAnnotationsMapper.getUnmarkedNum", null);
	}
	
	/**
	 * 获取有效未审核数据量
	 */
	public int getUncheckedNum() throws Exception{
		return (int) dao.findForObject("TxtAnnotationsMapper.getUncheckedNum", null);
	}
	
	/**
	 * 根据txtId修改数据
	 */
	public void updateByXml(List<PageData> pdList) throws Exception{
		dao.batchUpdate("TxtAnnotationsMapper.updateByXml", pdList);
	}
}

