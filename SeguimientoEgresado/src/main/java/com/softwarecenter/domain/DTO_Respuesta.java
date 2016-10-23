package com.softwarecenter.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DTO_Respuesta implements Serializable{
	private String id_pregunta;
	private String id_encuesta;
	private String id_alternativa;
	private Date fecha_rpt;
	private Integer id_egresado;
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
	public String getId_alternativa() {
		return id_alternativa;
	}
	public void setId_alternativa(String id_alternativa) {
		this.id_alternativa = id_alternativa;
	}
	public Date getFecha_rpt() {
		return fecha_rpt;
	}
	public void setFecha_rpt(Date fecha_rpt) {
		this.fecha_rpt = fecha_rpt;
	}
	public Integer getId_egresado() {
		return id_egresado;
	}
	public void setId_egresado(Integer id_egresado) {
		this.id_egresado = id_egresado;
	}
	@Override
	public String toString() {
		return "DTO_Respuesta [id_pregunta=" + id_pregunta + ", id_encuesta=" + id_encuesta + ", id_alternativa="
				+ id_alternativa + ", fecha_rpt=" + fecha_rpt + ", id_egresado=" + id_egresado + "]";
	}
	
	
	
}
