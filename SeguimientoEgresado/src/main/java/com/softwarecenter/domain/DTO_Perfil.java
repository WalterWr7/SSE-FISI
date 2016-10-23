package com.softwarecenter.domain;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class DTO_Perfil implements Serializable{
	private String id_perfil;
	private String descripcion;
	private List<DTO_Recurso> recursos;
	
	public String getId_perfil() {
		return id_perfil;
	}
	public void setId_perfil(String id_perfil) {
		this.id_perfil = id_perfil;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<DTO_Recurso> getRecursos() {
		return recursos;
	}
	public void setRecursos(List<DTO_Recurso> recursos) {
		this.recursos = recursos;
	}
	
}
