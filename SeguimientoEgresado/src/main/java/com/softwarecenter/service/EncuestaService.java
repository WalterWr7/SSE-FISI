package com.softwarecenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softwarecenter.domain.DTO_Alternativa;
import com.softwarecenter.domain.DTO_Encuesta;
import com.softwarecenter.domain.DTO_Pregunta;
import com.softwarecenter.domain.DTO_Respuesta;
import com.softwarecenter.mappers.EncuestaDAO;

@Service
public class EncuestaService {
	@Autowired
	private EncuestaDAO encuesta;
	
	@Transactional
	public DTO_Encuesta getEncuesta(String id_encuesta){
		return encuesta.getEncuesta(id_encuesta);
	}
	
	@Transactional
	public List<DTO_Pregunta> getPreguntas(String id_encuesta){
		return encuesta.getPreguntas(id_encuesta);
	}
	
	@Transactional
	public List<DTO_Alternativa> getAlternativas(String id_encuesta, String id_pregunta){
		return encuesta.getAlternativas(id_encuesta, id_pregunta);
	}
	
	@Transactional
	public void addRespuesta(DTO_Respuesta rpt){
		encuesta.addRespuesta(rpt);
	}
}
