package com.softwarecenter.po;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Escape extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(Escape.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(false);
		if(s != null) {
			logger.info("Invalidando sesion");
			s.invalidate();
		} else {
			logger.info("Procediendo sin sesion");
		}
		response.sendRedirect("login.zul");
	}

}
