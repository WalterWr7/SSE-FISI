package com.softwarecenter.po;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Vbox;

import com.softwarecenter.domain.DTO_Alternativa;
import com.softwarecenter.domain.DTO_Pregunta;
import com.softwarecenter.domain.DTO_Respuesta;
import com.softwarecenter.service.EncuestaService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
public class PO_EncuestaINI extends SelectorComposer<Component>{
	
	@Wire
	private Grid grip_encuesta;
	
	@Wire
	private Button btn_siguiente;
	
	@Wire
	private Groupbox groupbox;
	
	private EncuestaService service;
   	private List<DTO_Pregunta> listPreguntas;
   	private List<DTO_Respuesta> listRespuestas;
   	private List<Radiogroup> listRG;
	
   	private final int limite = 2;
   	private int nro_pg;
   	private int pos_pg;
   	
   	@Listen("onClick = #btn_iniciar")
	public void iniciar(Event e){
   		service = (EncuestaService)getBean("encuestaService");
   		listRespuestas = new ArrayList<DTO_Respuesta>();
   		listRG = new ArrayList<Radiogroup>();
   		
   		listPreguntas = service.getPreguntas("0001");
   		System.out.println(listPreguntas);
   		pos_pg = 1;
   		nro_pg = (int)Math.ceil(listPreguntas.size() / limite);
   		
   		grip_encuesta.getRows().getChildren().clear();   		
   		for(int i=0; i<limite; i++){
   			Row row = createPregunta(i+1,listPreguntas.get(i), service.getAlternativas(listPreguntas.get(i).getId_encuesta(), listPreguntas.get(i).getId_pregunta()));
   			grip_encuesta.getRows().appendChild(row);
   		}
	} 
   	
   	private boolean validaSelect(){
   		for(int i=0; i<grip_encuesta.getRows().getChildren().size(); i++){
   			Row row = (Row)grip_encuesta.getRows().getChildren().get(i);
   			if(((Radiogroup)row.getAttribute("rg")).getSelectedItem()==null){
   				Messagebox.show("Warning is pressed", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
   				return false;
   			}
   		}
   		return true;
   	}
   	
   	private void saveSelect(){
   		for(int i=0; i<grip_encuesta.getRows().getChildren().size(); i++){
   			Row row = (Row)grip_encuesta.getRows().getChildren().get(i);
   			Radio rd = ((Radiogroup)row.getAttribute("rg")).getSelectedItem();
   			DTO_Alternativa rpt = (DTO_Alternativa)rd.getAttribute("alt");
   			
   			DTO_Respuesta rr = new DTO_Respuesta();
   			rr.setId_alternativa(rpt.getId_alternativa());
   			rr.setId_encuesta(rpt.getId_encuesta());
   			rr.setId_pregunta(rpt.getId_pregunta());
   			rr.setId_egresado(99);
   			rr.setFecha_rpt(new Date());
   			
   			listRespuestas.add(rr);
   		}
   	}
   	
   	@Listen("onClick = #btn_siguiente")
	public void siguiente(Event e){	
   		if(validaSelect()){
   			saveSelect();
   			if(pos_pg==nro_pg){
   				for(DTO_Respuesta rr : listRespuestas){
   		   			System.out.println(rr.toString());
   		   			service.addRespuesta(rr);
   		   		}   				
   	   		}
   	   		
   	   		if(pos_pg<nro_pg){
   	   			grip_encuesta.getRows().getChildren().clear(); 
   	   	   		for(int i=0; i<limite; i++){
   	   	   			int pp = pos_pg*limite+i;
   	   	   			if(pp==listPreguntas.size())
   	   	   				break;
   	   	   			Row row = createPregunta(pp+1,listPreguntas.get(pp), service.getAlternativas(listPreguntas.get(pp).getId_encuesta(), listPreguntas.get(pp).getId_pregunta()));
   	   	   			grip_encuesta.getRows().appendChild(row);
   	   	   		}
   	   			pos_pg++;
   	   			if(pos_pg==nro_pg)
   	   				btn_siguiente.setLabel("Finalizar");
   	   		} 
   		}
	}
   	
   	
   	private Row createPregunta(int nro, DTO_Pregunta pregunta, List<DTO_Alternativa> listAlternativas){
   		Row fila = new Row();   		
   		Vbox vbox = new Vbox();
   		Label pre = new Label(nro+". "+pregunta.getEnunciado_pr());
   		vbox.appendChild(pre);
   		Radiogroup rg = new Radiogroup();
   		
   		for(DTO_Alternativa alt: listAlternativas){
   			Radio rd = new Radio();
   			rd.setRadiogroup(rg);
   			rd.setLabel(alt.getEnunciado_alt());
   			rd.setAttribute("alt", alt);
   			vbox.appendChild(rd);
   		}
   		groupbox.appendChild(rg);
   		fila.setAttribute("rg", rg);
   		fila.appendChild(vbox);
   		return fila;
   	}
   	
	
	
	
	
	
	
	
		
	@SuppressWarnings("deprecation")
	public static Object getBean(String name) {
		ServletContext ctx = (ServletContext) Executions.getCurrent().getDesktop().getWebApp().getNativeContext();
		return WebApplicationContextUtils.getWebApplicationContext(ctx).getBean(name);
	}
}
