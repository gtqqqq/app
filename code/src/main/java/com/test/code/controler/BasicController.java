package com.test.code.controler;

import javax.servlet.http.HttpServletRequest;

public abstract class BasicController {

	protected String paramString(HttpServletRequest request, String key) {
		String valueR = request.getParameter(key);
		if (valueR == null || "".equals(valueR)) {
			return "";
		} else {
			return valueR;
		}
	}

	protected int paramInt(HttpServletRequest request, String key) {
		String valueR = request.getParameter(key);
		if (valueR == null || "".equals(valueR)) {

			return 0;
		} else {
			return Integer.parseInt(valueR);
		}
	}

}
