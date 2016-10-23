package com.softwarecenter.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.softwarecenter.domain.DTO_Input;
import com.softwarecenter.domain.DTO_Output;
import com.softwarecenter.domain.DTO_Perfil;

@Service
public class PerfilService {
	static Log logger = LogFactory.getLog(PerfilService.class);
	
	public DTO_Output getPerfil_cod(DTO_Input input){
		DTO_Output output = new DTO_Output();
		
		DTO_Perfil p = new DTO_Perfil();
		p.setId_perfil("ADMIN");
		p.setDescripcion("ADMINISTRADOR");
		
		output.setObject(p);
		output.setErrorCode(Constantes.OK);
		return output;
	}
}
