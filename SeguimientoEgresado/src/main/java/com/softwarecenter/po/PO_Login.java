package com.softwarecenter.po;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import javax.servlet.ServletContext;

@org.springframework.stereotype.Component
public class PO_Login extends SelectorComposer<Component>{
	private static final long serialVersionUID = 5266419264469563349L;
	
	@Wire
	private Textbox user;
	@Wire
	private Textbox pass;

	@Listen("onClick = #btn_ingresar; onOK = #pass")
    public void ingresar(Event e){
		//VALIDAR QUE EL USUARIO ESTE REGISTRADO
		
		//Executions.sendRedirect("index.zul");
		Window window = (Window) Executions.createComponents("encuestaINI.zul", null, null);
		window.doModal();
    }
	
	@Listen("onClick = #btn_registrar")
	public void registrar(Event e){
		Executions.sendRedirect("registrar.zul");
		
	}
		
	@SuppressWarnings("deprecation")
	public static Object getBean(String name) {
		ServletContext ctx = (ServletContext) Executions.getCurrent().getDesktop().getWebApp().getNativeContext();
		return WebApplicationContextUtils.getWebApplicationContext(ctx).getBean(name);
	}
}
