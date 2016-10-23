package com.softwarecenter.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DTO_Recurso implements Serializable{	
	private String id_recurso;
	private String descripcion;
	
	public String getId_recurso() {
		return id_recurso;
	}
	public void setId_recurso(String id_recurso) {
		this.id_recurso = id_recurso;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
