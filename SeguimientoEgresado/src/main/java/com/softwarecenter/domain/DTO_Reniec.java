package com.softwarecenter.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DTO_Reniec implements Serializable {
	private Integer dni;
	private String nombre;
	private String apellido;
	private String sexo;
	private Date fecha_nac;
	private String direccion;
	private String departamento;
	private String provincia;
	private String distrito;
	
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFecha_nac() {
		return fecha_nac;
	}
	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	@Override
	public String toString() {
		return "DTO_Reniec [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo
				+ ", fecha_nac=" + fecha_nac + ", direccion=" + direccion + ", departamento=" + departamento
				+ ", provincia=" + provincia + ", distrito=" + distrito + "]";
	}
}
