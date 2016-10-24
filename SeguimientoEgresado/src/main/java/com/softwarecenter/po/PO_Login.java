package com.softwarecenter.po;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.softwarecenter.domain.DTO_Output;
import com.softwarecenter.domain.DTO_Usuario;
import com.softwarecenter.service.AuthenticationService;
import com.softwarecenter.service.Constantes;

import javax.servlet.ServletContext;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
public class PO_Login extends SelectorComposer<Component>{
	
	@Wire
	private Intbox user;
	@Wire
	private Textbox pass;
	
	private AuthenticationService authenticationService;
	
	

	@Listen("onClick = #btn_ingresar; onOK = #pass")
    public void ingresar(Event e){
		authenticationService = (AuthenticationService)getBean("authenticationService");
	
		DTO_Output output = authenticationService.login(user.getValue(), pass.getValue().toString());
		
		switch(output.getErrorCode()){
		case Constantes.MISSING_DATA:
			Messagebox.show("Debe ingresar en todos los campos.", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			break;
		case Constantes.AUTH_ERROR:
			Messagebox.show("Usuario o contraseña invalido.", "Error", Messagebox.OK, Messagebox.ERROR);
			break;
		case Constantes.OK:			
			Executions.getCurrent().getDesktop().getSession().setAttribute("login", (DTO_Usuario)output.getObject());			
			Executions.sendRedirect("index.zul");
			break;
		}
			
		
		//VALIDAR QUE EL USUARIO ESTE REGISTRADO
		
		//Executions.sendRedirect("index.zul");
		//Window window = (Window) Executions.createComponents("encuestaINI.zul", null, null);
		//window.doModal();
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
