package com.softwarecenter.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DTO_Audit implements Serializable{
	private int id;
	private String id_tipo;
	private String descripcionTipo;
	private String comentario;
	private String id_usuario;
	private Date fecha;
	private String hora;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(String idTipo) {
		id_tipo = idTipo;
	}
	public String getDescripcionTipo() {
		return descripcionTipo;
	}
	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}
	
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		if(comentario != null && comentario.length() > 200) {
			comentario = comentario.substring(0, 200);
		}
		this.comentario = comentario;
	}
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String idUsuario) {
		id_usuario = idUsuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
}
