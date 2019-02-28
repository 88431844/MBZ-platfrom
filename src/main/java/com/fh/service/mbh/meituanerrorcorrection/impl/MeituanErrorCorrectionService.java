package com.fh.service.mbh.meituanerrorcorrection.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.mbh.meituanerrorcorrection.MeituanErrorCorrectionManager;

/** 
 * 说明： 美团文本标注_查询纠错项目
 * 创建人：FH Q313596790
 * 创建时间：2018-10-19
 * @version
 */
@Service("meituanerrorcorrectionService")
public class MeituanErrorCorrectionService implements MeituanErrorCorrectionManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void save(PageData pd)throws Exception{
		dao.save("MeituanErrorCorrectionMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void delete(PageData pd)throws Exception{
		dao.delete("MeituanErrorCorrectionMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void edit(PageData pd)throws Exception{
		dao.update("MeituanErrorCorrectionMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MeituanErrorCorrectionMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MeituanErrorCorrectionMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MeituanErrorCorrectionMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	@Override
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("MeituanErrorCorrectionMapper.deleteAll", ArrayDATA_IDS);
	}
	/**
	 * 写入新数据
	 * @param pdList
	 * @throws Exception
	 */
	@Override
	public void writeDataFromTxt(List<PageData> pdList)throws Exception{
		dao.batchSave("MeituanErrorCorrectionMapper.writeDataFromTxt", pdList);
	}
}

