package com.softwarecenter.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DTO_Alternativa implements Serializable{
	private String id_alternativa;
	private String id_pregunta;
	private String id_encuesta;
	private String enunciado_alt;
	
	public String getId_alternativa() {
		return id_alternativa;
	}
	public void setId_alternativa(String id_alternativa) {
		this.id_alternativa = id_alternativa;
	}
	public String getId_pregunta() {
		return id_pregunta;
	}
	public void setId_pregunta(String id_pregunta) {
		this.id_pregunta = id_pregunta;
	}
	public String getId_encuesta() {
		return id_encuesta;
	}
	public void setId_encuesta(String id_encuesta) {
		this.id_encuesta = id_encuesta;
	}
	public String getEnunciado_alt() {
		return enunciado_alt;
	}
	public void setEnunciado_alt(String enunciado_alt) {
		this.enunciado_alt = enunciado_alt;
	}
	@Override
	public String toString() {
		return "DTO_Alternativa [id_alternativa=" + id_alternativa + ", id_pregunta=" + id_pregunta + ", id_encuesta="
				+ id_encuesta + ", enunciado_alt=" + enunciado_alt + "]";
	}	
	
}
