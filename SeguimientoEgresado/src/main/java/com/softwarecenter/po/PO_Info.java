package com.softwarecenter.po;

import javax.servlet.ServletContext;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@org.springframework.stereotype.Component
public class PO_Info extends SelectorComposer<Component> {
    private static final long serialVersionUID = 1L;
    
        
    @Wire 
    private Textbox tx_pin;
    
    @Wire
    private Window modalDialog;
     
    @Listen("onClick = #b_firmar")
    public void showModal(Event e) throws Exception {
    	if(tx_pin.getValue()==null){
    		Messagebox.show("Pin invalido.", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
    	}else{
    		
        	Messagebox.show("Se ha firmado el certificado.", "Information", Messagebox.OK, Messagebox.INFORMATION);   
    	}
        modalDialog.detach();
    }
    
    
	@SuppressWarnings("deprecation")
	public static Object getBean(String name) {
		ServletContext ctx = (ServletContext) Executions.getCurrent().getDesktop().getWebApp().getNativeContext();
		return WebApplicationContextUtils.getWebApplicationContext(ctx).getBean(name);
	}
}