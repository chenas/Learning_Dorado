package com.sfit.as.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
    @DataProvider
    public Collection<SlDept> getTopDept(){
        return slDeptDao.find("from SlDept where slDept.deptId is null");
    }
	
    @DataProvider
    public Collection<SlDept> getDeptByParentId(Integer parentId){
    	if (null == parentId){
    		return null;
    	}
    	Map param = new HashMap();
    	param.put("deptId", parentId);
    	return slDeptDao.find("from SlDept where slDept.deptId = :deptId", param);
    }
    
}
