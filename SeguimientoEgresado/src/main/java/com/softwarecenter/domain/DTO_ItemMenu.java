package com.softwarecenter.domain;

import java.util.List;

@SuppressWarnings("serial")
public class DTO_ItemMenu implements java.io.Serializable {
	
	private String id;
	private String page;
	private String icon;
	private String label;
	private String recurso;
	private List<DTO_ItemMenu> lista;
	
	
	public DTO_ItemMenu(String id, String page, String icon, String label,String recurso
			,List<DTO_ItemMenu> lista) {
		super();
		this.id = id;
		this.page = page;
		this.icon = icon;
		this.label = label;
		this.lista = lista;
		this.setRecurso(recurso);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<DTO_ItemMenu> getLista() {
		return lista;
	}
	public void setLista(List<DTO_ItemMenu> lista) {
		this.lista = lista;
	}
	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}
	public String getRecurso() {
		return recurso;
	}
}
