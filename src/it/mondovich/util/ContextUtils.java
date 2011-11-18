package it.mondovich.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class ContextUtils {
	
	private ContextUtils() {};
	
	public static String getParameter(String param) {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		return request.getParameter(param);
	}

}
