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
import org.zkoss.zul.Window;

import com.softwarecenter.domain.DTO_Output;
import com.softwarecenter.domain.DTO_Reniec;
import com.softwarecenter.domain.DTO_Sum;
import com.softwarecenter.service.Constantes;
import com.softwarecenter.service.ReniecSumService;

import javax.servlet.ServletContext;

@org.springframework.stereotype.Component
public class PO_Registrar extends SelectorComposer<Component>{
	private static final long serialVersionUID = 5266419264469563349L;
	
	@Wire
	private Intbox dni;
	@Wire
	private Intbox codigo;
	
	private ReniecSumService reniecSumService;


	@Listen("onClick = #btn_validar")
	public void validar(Event e){
		//VALIDA CON EL SUM Y RENIEC
		reniecSumService = (ReniecSumService)getBean("reniecSumService");		
		DTO_Output output = reniecSumService.validaInfo(codigo.getValue(), dni.getValue());
		switch(output.getErrorCode()){
		case Constantes.MISSING_DATA:
			Messagebox.show("Debe ingresar en todos los campos.", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			break;
		case Constantes.ALREADY_USED:
			Messagebox.show("El usuario ya se encuentra registrado.", "Error", Messagebox.OK, Messagebox.ERROR);
			break;
		case Constantes.NOT_FOUND_RENIEC:
			Messagebox.show("DNI invalido, no se ha encontrado coincidencias en RENIEC.", "Error", Messagebox.OK, Messagebox.ERROR);
			break;
		case Constantes.NOT_FOUND_SUM:
			Messagebox.show("Codigo invalido, no se ha encontrado coincidencias en el SUM.", "Error", Messagebox.OK, Messagebox.ERROR);
			break;
		case Constantes.VALIDATION_ERROR:
			Messagebox.show("El DNI no pertenece al codigo del SUM.", "Error", Messagebox.OK, Messagebox.ERROR);
			break;			
		case Constantes.OK:
   			Executions.getCurrent().getDesktop().getSession().setAttribute("DNI", (DTO_Reniec)output.getMapa().get("DNI"));
   			Executions.getCurrent().getDesktop().getSession().setAttribute("SUM", (DTO_Sum)output.getMapa().get("SUM"));
   			
			Window window = (Window) Executions.createComponents("bienvenido.zul", null, null);
			window.doModal();		
			break;
		}			
	}
	
	@SuppressWarnings("deprecation")
	public static Object getBean(String name) {
		ServletContext ctx = (ServletContext) Executions.getCurrent().getDesktop().getWebApp().getNativeContext();
		return WebApplicationContextUtils.getWebApplicationContext(ctx).getBean(name);
	}
}
