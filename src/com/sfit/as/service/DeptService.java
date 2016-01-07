package com.sfit.as.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.util.StringHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.provider.Page;
import com.sfit.as.dao.SlDeptDao;
import com.sfit.as.dao.SlEmployeeDao;
import com.sfit.as.entity.SlDept;
import com.sfit.as.entity.SlEmployee;

@Component
public class DeptService {

	@Resource
	SlDeptDao slDeptDao;
	
	@Resource
	SlEmployeeDao slEmployeeDao;

	@DataProvider
	public SlDept getOneDept() {
		List<SlDept> deptList = slDeptDao.getAll();
		return deptList.get(0);
	}

	@DataProvider
	public Collection<SlDept> getTopDept() {
		return slDeptDao.find("from SlDept where slDept.deptId is null");
	}

	@DataProvider
	public Collection<SlDept> getDeptByParentId(Integer parentId) {
		if (null == parentId) {
			return null;
		}
		Map param = new HashMap();
		param.put("deptId", parentId);
		return slDeptDao.find("from SlDept where slDept.deptId = :deptId",
				param);
	}
	
	@DataResolver
    @Transactional
    public void saveAll(Collection<SlDept> depts){
        for(SlDept dept:depts){
        	System.out.println(dept.getDeptName());
            slDeptDao.persistEntity(dept);
            Collection<SlDept> childs = dept.getSlDeptSet();
            if(!(childs ==  null) && childs.size() > 0){
                for(SlDept child:childs){
                    //维护关联关系
                    child.setSlDept(dept);
                }
                slDeptDao.persistEntities(childs);
                saveAll(childs);
            }
        }
    }
	
	@DataProvider
	public void getEmployeeByDeptId(Page<SlEmployee> page, Integer deptId){
		String hql = "from SlEmployee where slDept.deptId = :deptId";
		Map param = new HashMap();
		param.put("deptId", deptId);
		slEmployeeDao.find(page, hql, param);
	}

	@DataProvider
	public Collection<SlDept> getAll(){
		return slDeptDao.getAll();
	}
	
	@DataProvider
	public Collection<SlDept> getDeptByDeptName(String deptName){
		String hql = "from SlDept where deptName like :deptName";
		if (StringHelper.isNotEmpty(deptName)){
			Map param = new HashMap();
			param.put("deptName", "%" + deptName + "%");
			return slDeptDao.find(hql, param);
		}
		else
			return slDeptDao.getAll();
	}
	
}
