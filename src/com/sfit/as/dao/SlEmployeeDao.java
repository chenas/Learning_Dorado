package com.sfit.as.dao;

import org.springframework.stereotype.Repository;

import com.bstek.dorado.hibernate.HibernateDao;
import com.sfit.as.entity.SlEmployee;

@Repository
public class SlEmployeeDao  extends HibernateDao<SlEmployee, Long>{

}
