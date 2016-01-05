package com.sfit.as.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.sfit.as.dao.SlDeptDao;
import com.sfit.as.entity.SlDept;

@Component
public class DeptService {

	@Resource
	SlDeptDao slDeptDao;
	
	@DataProvider
	public SlDept getOneDept(){
		List<SlDept> deptList = slDeptDao.getAll();
		return deptList.get(0);
	}
	
}
