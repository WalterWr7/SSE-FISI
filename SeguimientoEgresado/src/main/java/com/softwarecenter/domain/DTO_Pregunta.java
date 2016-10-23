package com.softwarecenter.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DTO_Pregunta implements Serializable{
	private String id_pregunta;
	private String id_encuesta;
	private String enunciado_pr;
	
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
	public String getEnunciado_pr() {
		return enunciado_pr;
	}
	public void setEnunciado_pr(String enunciado_pr) {
		this.enunciado_pr = enunciado_pr;
	}
	@Override
	public String toString() {
		return "DTO_Pregunta [id_pregunta=" + id_pregunta + ", id_encuesta=" + id_encuesta + ", enunciado_pr="
				+ enunciado_pr + "]";
	}
	
}
