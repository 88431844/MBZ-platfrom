package com.fh.service.mbh.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.service.mbh.DaoManager;
import com.fh.util.PageData;

/**
 * 小狗项目人脸属性service
 * 1.写入数据（saveTxtValue）,2.保存数据（save）,3.修改数据（update）,4.查询数据（search）,5.删除数据（delete）,6.查询列表（searchList）
 * @author MBHTech
 *
 */
@Service("daoService")
public class DogService implements DaoManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Override
	public void saveTxtValue(PageData pd) throws Exception {
		dao.save("DogMapper.saveTxtValue", pd);
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchList() {
		// TODO Auto-generated method stub
		
	}

}
