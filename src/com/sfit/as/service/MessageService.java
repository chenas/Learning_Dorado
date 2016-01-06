package com.sfit.as.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.provider.Page;
import com.sfit.as.dao.SlEmployeeDao;
import com.sfit.as.dao.SlMessageDao;
import com.sfit.as.entity.SlEmployee;
import com.sfit.as.entity.SlMessage;

@Component
public class MessageService {

	@Resource
	private SlMessageDao slMessageDao;
	@Resource
	private SlEmployeeDao slEmployeeDao;

	@DataResolver
	@Transactional
	public void saveMessages(Collection<SlEmployee> employees) {
		for (SlEmployee ee : employees) {
			Collection<SlMessage> msgs = ee.getSlMessageSet();
			for (SlMessage msg : msgs) {
				msg.setSlEmployee(ee);
			}
			slMessageDao.persistEntities(msgs);
		}
	}

	@DataProvider
	public void getMessageByEmployeeId(Page<SlMessage> page, Integer employeeId){
		String hql = "from SlMessage where slEmployee.employeeId = :employeeId";
		Map param = new HashMap();
		param.put("employeeId", employeeId);
		slMessageDao.find(page, hql, param);
	}
	
	///保存联系方式，及消息
	@DataResolver
	@Transactional
	public void saveAll(Collection<SlEmployee> slEmployees){
		slEmployeeDao.persistEntities(slEmployees);
		for (SlEmployee ee : slEmployees){
			Collection<SlMessage> msgs = ee.getSlMessageSet();
			for (SlMessage msg : msgs){
				msg.setSlEmployee(ee);  ///维护关联表关系
			}
			slMessageDao.persistEntities(msgs);
		}
	}
	
	
}
