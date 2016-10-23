package com.softwarecenter.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.softwarecenter.domain.DTO_Input;
import com.softwarecenter.domain.DTO_Output;
import com.softwarecenter.domain.DTO_Recurso;

@Service
public class RecursoService {
	static Log logger = LogFactory.getLog(RecursoService.class);
	
	public DTO_Output getRecursos_Perfil(DTO_Input input){
		DTO_Output output = new DTO_Output();
		
		DTO_Recurso r1 = new DTO_Recurso();
		r1.setId_recurso("R1");
		r1.setDescripcion("DES R1");
		
		DTO_Recurso r2 = new DTO_Recurso();
		r2.setId_recurso("R2");
		r2.setDescripcion("DES R2");
		
		List<DTO_Recurso> recursos = new ArrayList<DTO_Recurso>();
		recursos.add(r1);
		recursos.add(r2);
		output.setLista(recursos);
		return output;
	}
}
