package com.softwarecenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softwarecenter.domain.DTO_Output;
import com.softwarecenter.domain.DTO_Reniec;
import com.softwarecenter.domain.DTO_Sum;
import com.softwarecenter.mappers.ReniecSumDAO;
import com.softwarecenter.mappers.UsuarioDAO;

@Service
public class ReniecSumService {
	@Autowired
	private ReniecSumDAO reniecSumDao;
	
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Transactional
	public DTO_Output validaInfo(Integer codigo, Integer dni){
		DTO_Output output = new DTO_Output();
		
		if(codigo == null || dni == null){
			output.setErrorCode(Constantes.MISSING_DATA);
			return output;
		}
		//valida si ya se encuentra registrado
		if(usuarioDao.getExistUsuario(codigo)!=null){
			output.setErrorCode(Constantes.ALREADY_USED);
			return output;
		}
		
		//que pertenezca al sum
		DTO_Sum sum = reniecSumDao.getSum(codigo); 
		System.out.println(sum);
		if(sum == null){
			output.setErrorCode(Constantes.NOT_FOUND_SUM);
			return output;
		}
				
		//que pertenezca a la reniec
		DTO_Reniec reniec = reniecSumDao.getDNI(dni);
		System.out.println(reniec);
		if(reniec == null){
			output.setErrorCode(Constantes.NOT_FOUND_RENIEC);
			return output;
		}
		
		//que el dni asociado al sum sea el ingresado a el de la reniec
		if(!sum.getDni().equals(reniec.getDni())){
			output.setErrorCode(Constantes.VALIDATION_ERROR);
			return output;
		}
		output.addMapPair("DNI", reniec);
		output.addMapPair("SUM", sum);
		output.setErrorCode(Constantes.OK);
		return output;
		
	}
	
	
}
