package com.fh.service.mbh;

import java.util.Map;

import com.fh.util.PageData;

public interface DaoManager {

	//读取数据并存入数据库
	public void saveTxtValue(PageData pd) throws Exception;
	
	//保存数据
	public void save();
	
	//修改数据
	public void update();
	
	//查询数据
	public void search();
	
	//删除数据
	public void delete();
	
	//查询列表
	public void searchList();
}
