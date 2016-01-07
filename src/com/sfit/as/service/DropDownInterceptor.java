package com.sfit.as.service;

/**
 * �����������
 * Ϊ�������ṩ��ֵ�ԣ��ʺ�С���ݴ���
 * �Ƚϴ�����ݿ�����DataSetDropDown
 */

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
@Component
public class DropDownInterceptor {
    @DataProvider
    public Map<String,String> getMarriedState(){
        Map<String,String> mapValue = new LinkedHashMap<String, String>();
        mapValue.put("true", "�ѻ�");
        mapValue.put("false", "δ��");
        return mapValue;
    }
    
    @DataProvider
    public Map<String, String> getSexState(){
    	Map<String, String> mapValue = new LinkedHashMap<String, String>();
    	mapValue.put("true", "��");
    	mapValue.put("false", "Ů");
    	return mapValue;
    }
    
}