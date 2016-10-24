package com.softwarecenter.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DTO_Usuario implements Serializable {
	private Integer id_usuario;
	private String nombre_user;
	private String contrasenia;
	private String tipo_user;
	private Date fecha_login;
	
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNombre_user() {
		return nombre_user;
	}
	public void setNombre_user(String nombre_user) {
		this.nombre_user = nombre_user;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getTipo_user() {
		return tipo_user;
	}
	public void setTipo_user(String tipo_user) {
		this.tipo_user = tipo_user;
	}
	public Date getFecha_login() {
		return fecha_login;
	}
	public void setFecha_login(Date fecha_login) {
		this.fecha_login = fecha_login;
	}
	@Override
	public String toString() {
		return "DTO_Usuario [id_usuario=" + id_usuario + ", nombre_user=" + nombre_user + ", contrasenia=" + contrasenia
				+ ", tipo_user=" + tipo_user + ", fecha_login=" + fecha_login + "]";
	}
}
