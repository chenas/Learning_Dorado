package com.sfit.as.service;

/**
 * 下拉框过滤器
 * 为下来框提供键值对，适合小数据传输
 * 比较大的数据可以用DataSetDropDown
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
        mapValue.put("true", "已婚");
        mapValue.put("false", "未婚");
        return mapValue;
    }
    
    @DataProvider
    public Map<String, String> getSexState(){
    	Map<String, String> mapValue = new LinkedHashMap<String, String>();
    	mapValue.put("true", "男");
    	mapValue.put("false", "女");
    	return mapValue;
    }
    
}