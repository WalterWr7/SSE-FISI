package com.softwarecenter.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DTO_Usuario implements Serializable {
	private String username;
	private String password;
	private int activo;
	
	private DTO_Perfil perfil;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DTO_Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(DTO_Perfil perfil) {
		this.perfil = perfil;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	
}
