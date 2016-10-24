package com.softwarecenter.po;

import java.util.ArrayList;

import com.softwarecenter.domain.DTO_ItemMenu;
import com.softwarecenter.service.Constantes;

@SuppressWarnings("serial")
public class OpcionesDeMenu {
	
	public static final  ArrayList<DTO_ItemMenu> itemMenu = new ArrayList<DTO_ItemMenu>(){{
	    add(new DTO_ItemMenu("op1","index.zul","media/icon/gohome.png","Inicio",null,null));
	    add(new DTO_ItemMenu("op2","","media/icon/search.png","Noticias",Constantes.RESOURCE_AA,null));	    		
	    add(new DTO_ItemMenu("op3","","media/icon/contents.png","Encuestas",Constantes.RESOURCE_AA,
	    		new ArrayList<DTO_ItemMenu>(){{
	    			add(new DTO_ItemMenu("op2_1","encuesta.zul","media/icon/icon_users.png","Evaluación SSE-FISI",Constantes.RESOURCE_AA,null));
	    		}}));
	    add(new DTO_ItemMenu("op4","","media/icon/encrypted.png","Seguridad",Constantes.RESOURCE_AA,null));
	    add(new DTO_ItemMenu("op5","","media/icon/report_icon.gif","Reportes",Constantes.RESOURCE_AA,null));	    
	}};


}
