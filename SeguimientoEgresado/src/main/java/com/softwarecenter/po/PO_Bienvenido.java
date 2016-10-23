package com.softwarecenter.po;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

import javax.servlet.ServletContext;

@org.springframework.stereotype.Component
public class PO_Bienvenido extends SelectorComposer<Component>{
	private static final long serialVersionUID = 5266419264469563349L;
	

	@Listen("onClick = #btn_encuesta")
    public void ingresar(Event e){
		//VALIDAR QUE EL USUARIO ESTE REGISTRADO
		Executions.sendRedirect("index.zul");
    }
	

	@SuppressWarnings("deprecation")
	public static Object getBean(String name) {
		ServletContext ctx = (ServletContext) Executions.getCurrent().getDesktop().getWebApp().getNativeContext();
		return WebApplicationContextUtils.getWebApplicationContext(ctx).getBean(name);
	}
}
