package com.fh.controller.mbh;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.service.mbh.impl.DogService;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.fh.util.mbh.ReadText;

@Controller
@RequestMapping(value="/dogController")
public class DogController {
	
	@Resource(name="daoService")
	private DogService daoService;
	
	@RequestMapping(value="saveTxtValue")
	public void saveTxtValue() throws Exception{
		//存储从Txt中获取的原始数据
		List txtValueList = new ArrayList();
		//文件路径地址
		String filePath = "E:\\jsonTest\\29993.txt";
		//存储没条数据的值
		PageData txtValueMap = new PageData();
		
		txtValueList = ReadText.readTxtToList(filePath);
		
		for (int i = 0; i < txtValueList.size(); i++) {
			String txtValue = (String) txtValueList.get(i);
			
			String[] oneTxtValue = txtValue.split(" ");
			//文件名
			txtValueMap.put("fileName", oneTxtValue[0]);
			//年龄
			txtValueMap.put("age", oneTxtValue[1]);
			//性别
			txtValueMap.put("sex", oneTxtValue[2]);
			//头部装饰
			txtValueMap.put("head", oneTxtValue[3]);
			//面部装饰
			txtValueMap.put("face", oneTxtValue[4]);
			//头发装饰
			txtValueMap.put("hair", oneTxtValue[5]);
			//清晰度
			txtValueMap.put("definition", oneTxtValue[6]);
			//操作员
			txtValueMap.put("status", 0);
			//操作员
			txtValueMap.put("user", "系统写入");
			//修改次数
			txtValueMap.put("updateCount", 0);
			//写入时间
			txtValueMap.put("createTime", DateUtil.getTime());
			//修改时间
			txtValueMap.put("updateTime", DateUtil.getTime());
			
			//写入到数据库
			daoService.saveTxtValue(txtValueMap);
		}
		
	}
}
