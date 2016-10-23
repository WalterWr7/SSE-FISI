package com.softwarecenter.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class DTO_Input implements Serializable {
	private Serializable object;
	@SuppressWarnings("rawtypes")
	private List lista;
	@SuppressWarnings("rawtypes")
	private Map mapa;
	private String verbo;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addMapPair(Serializable k, Object v) {
		if (this.mapa == null) {
			this.mapa = new HashMap();
		}
		this.mapa.put(k, v);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addListEntry(Object v) {
		if (this.lista == null) {
			this.lista = new ArrayList();
		}
		this.lista.add(v);
	}

	public Serializable getObject() {
		return this.object;
	}

	public void setObject(Serializable object) {
		this.object = object;
	}

	@SuppressWarnings("rawtypes")
	public List getLista() {
		return this.lista;
	}

	@SuppressWarnings("rawtypes")
	public void setLista(List lista) {
		this.lista = lista;
	}

	@SuppressWarnings("rawtypes")
	public Map getMapa() {
		return this.mapa;
	}

	@SuppressWarnings("rawtypes")
	public void setMapa(Map mapa) {
		this.mapa = mapa;
	}

	public String getVerbo() {
		return this.verbo;
	}

	public void setVerbo(String verbo) {
		this.verbo = verbo;
	}
}