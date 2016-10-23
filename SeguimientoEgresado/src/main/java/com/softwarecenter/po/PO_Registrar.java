package com.softwarecenter.po;



import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;



import javax.servlet.ServletContext;

@org.springframework.stereotype.Component
public class PO_Registrar extends SelectorComposer<Component>{
	private static final long serialVersionUID = 5266419264469563349L;
	
	@Wire
	private Textbox dni;
	@Wire
	private Textbox codigo;


	@Listen("onClick = #btn_validar")
	public void validar(Event e){
		//VALIDA CON EL RUC Y RENIEC
		
		
		Executions.sendRedirect("index.zul");
		
	}
		
	
	@SuppressWarnings("deprecation")
	public static Object getBean(String name) {
		ServletContext ctx = (ServletContext) Executions.getCurrent().getDesktop().getWebApp().getNativeContext();
		return WebApplicationContextUtils.getWebApplicationContext(ctx).getBean(name);
	}
}
