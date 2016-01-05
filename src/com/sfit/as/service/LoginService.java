package com.sfit.as.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.web.DoradoContext;
import com.sfit.as.dao.SlEmployeeDao;
import com.sfit.as.entity.SlEmployee;

@Component
public class LoginService {
	@Resource
	private SlEmployeeDao slEmployeeDao;

	@Expose
	public Map doLogin(Map param) {
		String username = (String) param.get("username");
		String password = (String) param.get("password");
		Map result = new HashMap();
		if (isValid(username, password)) {
			// ������֤�ɹ���Ҫ��ת��ҳ��
			result.put("url", "com.sfit.as.contacts.Main.d");
			result.put("result", true);
			return result;
		} else {
			String errormsg = "�û����������벻��ȷ";
			result.put("errormsg", errormsg);
			result.put("result", false);
			return result;
		}
	}

	// ���·�����Ҫ�滻Ϊ�Լ�����֤�û��������ҵ���߼�
	public boolean isValid(String username, String password) {
		DetachedCriteria dc = DetachedCriteria.forClass(SlEmployee.class);
		if (username != null && !"".equals(username)) {
			dc.add(Restrictions.eq("userName", username.toUpperCase()));
		}
		List<SlEmployee> employees = slEmployeeDao.find(dc);
		if (employees.size() > 0) {
			SlEmployee employee = employees.get(0);
			if (password.equals(employee.getPassword())) {
				// ��֤�ɹ����û���Ϣ����session��
				DoradoContext ctx = DoradoContext.getCurrent();
				HttpServletRequest request = request = ctx.getRequest();
				request.getSession().setAttribute("user", employee);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Expose
	public Map doLogout() {
		DoradoContext ctx = DoradoContext.getCurrent();
		HttpServletRequest request = ctx.getRequest();
		request.getSession().setAttribute("user", null);
		Map result = new HashMap();
		result.put("url", "com.sfit.as.contacts.Login.d");
		result.put("result", true);
		return result;
	}
		
}