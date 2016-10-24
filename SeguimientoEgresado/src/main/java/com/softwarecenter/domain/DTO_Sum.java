package com.softwarecenter.domain;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DTO_Sum implements Serializable{
	private Integer codigo;
	private Integer dni;
	private String facultad;
	private String eap;
	private Date f_egreso;
	private Date f_grado;
	private Date f_titulo;
	
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	public String getEap() {
		return eap;
	}
	public void setEap(String eap) {
		this.eap = eap;
	}
	public Date getF_egreso() {
		return f_egreso;
	}
	public void setF_egreso(Date f_egreso) {
		this.f_egreso = f_egreso;
	}
	public Date getF_grado() {
		return f_grado;
	}
	public void setF_grado(Date f_grado) {
		this.f_grado = f_grado;
	}
	public Date getF_titulo() {
		return f_titulo;
	}
	public void setF_titulo(Date f_titulo) {
		this.f_titulo = f_titulo;
	}
	public Integer getCodigo() {
		return codigo;
	}
	@Override
	public String toString() {
		return "DTO_Sum [codigo=" + codigo + ", dni=" + dni + ", facultad=" + facultad + ", eap=" + eap + ", f_egreso="
				+ f_egreso + ", f_grado=" + f_grado + ", f_titulo=" + f_titulo + "]";
	}
	
}
