package com.softwarecenter.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DTO_Encuesta implements Serializable{
	private String id_encuesta;
	private String titulo_enc;
	private String dscp_enc;
	private String tema_enc;
		
	public String getId_encuesta() {
		return id_encuesta;
	}
	public void setId_encuesta(String id_encuesta) {
		this.id_encuesta = id_encuesta;
	}
	public String getTitulo_enc() {
		return titulo_enc;
	}
	public void setTitulo_enc(String titulo_enc) {
		this.titulo_enc = titulo_enc;
	}
	public String getDscp_enc() {
		return dscp_enc;
	}
	public void setDscp_enc(String dscp_enc) {
		this.dscp_enc = dscp_enc;
	}
	public String getTema_enc() {
		return tema_enc;
	}
	public void setTema_enc(String tema_enc) {
		this.tema_enc = tema_enc;
	}
	@Override
	public String toString() {
		return "DTO_Encuesta [id_encuesta=" + id_encuesta + ", titulo_enc=" + titulo_enc + ", dscp_enc=" + dscp_enc
				+ ", tema_enc=" + tema_enc + "]";
	}
	
	
}
