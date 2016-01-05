package com.sfit.as.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.sfit.as.dao.SlCompanyDao;
import com.sfit.as.entity.SlCompany;

@Component
public class CompanyService {
	@Resource
	private SlCompanyDao slcompanyDao;

	@DataProvider
	public Collection<SlCompany> getCompany() {
		return slcompanyDao.getAll();
	}

	@DataResolver
	@Transactional
	public void saveCompany(Collection<SlCompany> slcompanys) {
		slcompanyDao.persistEntities(slcompanys);
	}
}