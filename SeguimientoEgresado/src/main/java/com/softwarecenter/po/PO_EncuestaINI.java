package com.softwarecenter.po;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Vbox;

import com.softwarecenter.domain.DTO_Alternativa;
import com.softwarecenter.domain.DTO_Pregunta;
import com.softwarecenter.service.EncuestaService;

import java.util.List;

import javax.servlet.ServletContext;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
public class PO_EncuestaINI extends SelectorComposer<Component>{
	
	@Wire
	private Grid grip_encuesta;
	
	private EncuestaService service;
   	private List<DTO_Pregunta> listPreguntas;
	
   	private int contador;
   	private final int limite = 2;
   	private int nro_pg;
   	
   	@Listen("onClick = #btn_iniciar")
	public void iniciar(Event e){
   		service = (EncuestaService)getBean("encuestaService");
   		listPreguntas = service.getPreguntas("0001");
   		System.out.println(listPreguntas);
   		contador = limite;
   		nro_pg = (int)Math.ceil(listPreguntas.size() / limite);
   		grip_encuesta.getRows().getChildren().clear();   		
   		for(int i=1; i<=limite; i++){
   			Row row = createPregunta(i,listPreguntas.get(i), service.getAlternativas(listPreguntas.get(i).getId_encuesta(), listPreguntas.get(i).getId_pregunta()));
   			grip_encuesta.getRows().appendChild(row);
   		}
	} 
   	
   	
   	@Listen("onClick = #btn_siguiente")
	public void siguiente(Event e){
   		//if(contador )
   		
   		
   		grip_encuesta.getRows().getChildren().clear(); 
   		for(int i=1; i<=limite; i++){
   			Row row = createPregunta(i,listPreguntas.get(i), service.getAlternativas(listPreguntas.get(i).getId_encuesta(), listPreguntas.get(i).getId_pregunta()));
   			grip_encuesta.getRows().appendChild(row);
   		}
	}
   	
   	
   	private Row createPregunta(int nro, DTO_Pregunta pregunta, List<DTO_Alternativa> listAlternativas){
   		System.out.println(pregunta);
   		System.out.println(listAlternativas);
   		Row fila = new Row();   		
   		Vbox vbox = new Vbox();
   		Label pre = new Label(nro+". "+pregunta.getEnunciado_pr());
   		vbox.appendChild(pre);
   		Radiogroup rg = new Radiogroup();
   		for(DTO_Alternativa alt: listAlternativas){
   			Radio rd = new Radio();
   			rd.setRadiogroup(rg);
   			rd.setLabel(alt.getEnunciado_alt());
   			rg.setAttribute("alt", alt);
   			vbox.appendChild(rd);
   		}
   		fila.setAttribute("pregunta", pregunta);
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
