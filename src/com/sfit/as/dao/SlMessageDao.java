package com.sfit.as.dao;

import org.springframework.stereotype.Repository;

import com.bstek.dorado.hibernate.HibernateDao;
import com.sfit.as.entity.SlMessage;

@Repository
public class SlMessageDao extends HibernateDao<SlMessage, Long> {

}
