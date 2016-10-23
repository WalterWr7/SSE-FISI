package com.softwarecenter.po;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

import com.softwarecenter.domain.DTO_ItemMenu;
import com.softwarecenter.domain.DTO_Usuario;

@SuppressWarnings("serial")
public class PO_Menu extends Menubar{
	
	private Menubar menubar;
	
	public void onCreate() {
		
		// si se requiere un recurso entonces se requiere usuario logeado
//		Session s = Executions.getCurrent().getSession();
//		if(s == null) {
//			throw new IllegalStateException("No se ha establecido sesion");
//		}
//		DTO_Usuario user = (DTO_Usuario) s.getAttribute("login");
//		if(user == null) {
//			throw new IllegalStateException("No se encontro usuario en la sesion");
//		}
		
//		PerfilRecursoService perfilRecursoService = (PerfilRecursoService) BaseWindow.getService("perfilRecursoService");
//		DTO_Input input = new DTO_Input();
//		input.setObject(user.getPerfil());
//		input.setVerbo("getRecursosPorPerfil");
//		DTO_Output output = perfilRecursoService.execute(input);
//		List<String> recursos = output.getLista();
		
		
		List<String> recursos = new ArrayList<String>();
		
		menubar = (Menubar) getFellow("menu_principal");
		menubar.setVisible(true);
		getItemMenuList(OpcionesDeMenu.itemMenu, menubar,recursos);
		createMenuItemHref(new DTO_ItemMenu("op7","/Escape","media/icon/logout.png","Salir",null,null), menubar);
//		createHelpItem(new DTO_ItemMenu("op8","/Escape","media/helpazul.png","Ayuda", null, null), menubar);
		
		if(getDesktop().getSession().getAttribute("login")!=null){
			DTO_Usuario validado = (DTO_Usuario)getDesktop().getSession().getAttribute("login");
			Menuitem menuitem = (Menuitem)this.getParent().getFellow("menu_user");
			menuitem.setLabel("Usuario: "+validado.getUsername());
		}
		
		//borrar
		Menuitem menuitem = (Menuitem)this.getParent().getFellow("menu_user");
		menuitem.setLabel("Usuario: ADMIN");	
	}

//	public void createHelpItem(DTO_ItemMenu item, Component parent) {
//		
//		for(Object obj : getPage().getFellows()) {
//			if(obj instanceof BaseWindow) {
//				final BaseWindow baseWindow = (BaseWindow)obj;
//				final String archivoHelp = baseWindow.archivoHelp();
//				if(archivoHelp == null) {
//					return;
//				}
//				Menuitem menuitem = new Menuitem();
//				menuitem.setLabel(item.getLabel());
//				menuitem.setImage(item.getIcon());
//				menuitem.setValue(item.getPage());
//				menuitem.setVisible(true);
//				menuitem.setParent(parent);
//				menuitem.addEventListener(Events.ON_CLICK, new EventListener() {
//					public void onEvent(Event e) {
//						baseWindow.help(archivoHelp);
//					}
//				});
//				return;
//			}
//		}	
//	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createMenuItem(DTO_ItemMenu item, Component parent, List<String> recursos) {
		Menuitem menuitem = new Menuitem();
		menuitem.setLabel(item.getLabel());
		menuitem.setImage(item.getIcon());
		menuitem.setValue(item.getPage());
		if(recursos.contains(item.getRecurso())) {
			menuitem.setVisible(true);
		}else if(item.getRecurso()==null){
			menuitem.setVisible(true);
		}
		else{
			menuitem.setVisible(true);
			//menuitem.setVisible(false);
		}		
		menuitem.setParent(parent);
		menuitem.addEventListener(Events.ON_CLICK, new EventListener() {
			public void onEvent(Event e) {
				Menuitem m = (Menuitem) e.getTarget();
				onSelect(m);
			}
		});
	}
	
	public void createMenuItemHref(DTO_ItemMenu item, Component parent) {
		Menuitem menuitem = new Menuitem();
		menuitem.setLabel(item.getLabel());
		menuitem.setImage(item.getIcon());
		menuitem.setValue(item.getPage());
		menuitem.setHref(item.getPage());
		menuitem.setVisible(true);
		
		menuitem.setParent(parent);
	}

	public void getItemMenuList(List<DTO_ItemMenu> menuList, Component parent,List<String> recursos) {

		for (DTO_ItemMenu item : menuList) {
			if (item.getLista() == null) {
				createMenuItem(item, parent,recursos);
			} else {
				Menu menu = new Menu();
				menu.setLabel(item.getLabel());
				menu.setImage(item.getIcon());
				// menu.setId(item.getId());
				Menupopup mp = new Menupopup();
				getItemMenuList(item.getLista(), mp,recursos);
				if(recursos.contains(item.getRecurso())) {
					menu.setVisible(true);
				}else if(item.getRecurso()==null){
					menu.setVisible(true);
				}
				else{
					menu.setVisible(true);
//					menu.setVisible(false);
				}
				mp.setParent(menu);
				menu.setParent(parent);
			}
		}
	}

	public void onSelect(Menuitem m) {
		if (m != null) {
			Executions.sendRedirect(m.getValue());
		}
	}

}
