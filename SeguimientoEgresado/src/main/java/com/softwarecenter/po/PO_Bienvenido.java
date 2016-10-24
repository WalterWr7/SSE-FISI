package com.softwarecenter.po;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

import com.softwarecenter.domain.DTO_Alternativa;
import com.softwarecenter.domain.DTO_Pregunta;
import com.softwarecenter.domain.DTO_Reniec;
import com.softwarecenter.domain.DTO_Respuesta;
import com.softwarecenter.domain.DTO_Sum;
import com.softwarecenter.domain.DTO_Usuario;
import com.softwarecenter.service.AuthenticationService;
import com.softwarecenter.service.EncuestaService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
public class PO_Bienvenido extends SelectorComposer<Component> {
	@Wire
	private Vlayout v_p1, v_p2, v_p3;
	@Wire
	private Grid grip_encuesta;
	@Wire
	private Button btn_siguiente;
	@Wire
	private Groupbox groupbox;
	@Wire
	private Window modalDialog;

	// DATOS GENERALES
	@Wire
	private Label lb_nombre, lb_dni, lb_telf_fijo, lb_telf_cel, lb_direccion, lb_distrito, lb_departamento,
			lb_provincia;

	// DATOS ACADEMICOS
	@Wire
	private Label lb_codigo, lb_facultad, lb_eap, ld_tipo_formacion, lb_f_egreso, lb_f_grado, lb_f_titulo;

	// FINAL
	@Wire
	private Label lb_b_codigo;
	@Wire
	private Textbox pass1, pass2;
	
	private AuthenticationService authenticationService;

	private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	private EncuestaService service;
	private List<DTO_Pregunta> listPreguntas;
	private List<DTO_Respuesta> listRespuestas;

	private final int limite = 2;
	private int nro_pg;
	private int pos_pg;

	private DTO_Sum sum;
	private DTO_Reniec dni;
	
	
	@Listen("onClick = #btn_encuesta")
	public void iniciarEncuesta(Event e) {
		v_p1.setVisible(false);
		v_p2.setVisible(true);

		service = (EncuestaService) getBean("encuestaService");
		listRespuestas = new ArrayList<DTO_Respuesta>();
		listPreguntas = service.getPreguntas("0001");
		System.out.println(listPreguntas);
		pos_pg = 1;
		nro_pg = (int) Math.ceil((double) listPreguntas.size() / (double) limite);
		System.out.println(nro_pg);
		grip_encuesta.getRows().getChildren().clear();
		for (int i = 0; i < limite; i++) {
			Row row = createPregunta(i + 1, listPreguntas.get(i), service
					.getAlternativas(listPreguntas.get(i).getId_encuesta(), listPreguntas.get(i).getId_pregunta()));
			grip_encuesta.getRows().appendChild(row);
		}

	}

	private boolean validaSelect() {
		for (int i = 0; i < grip_encuesta.getRows().getChildren().size(); i++) {
			Row row = (Row) grip_encuesta.getRows().getChildren().get(i);
			if (((Radiogroup) row.getAttribute("rg")).getSelectedItem() == null) {
				Messagebox.show("Debes responder todas las preguntas.", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
				return false;
			}
		}
		return true;
	}

	private void saveSelect() {
		for (int i = 0; i < grip_encuesta.getRows().getChildren().size(); i++) {
			Row row = (Row) grip_encuesta.getRows().getChildren().get(i);
			Radio rd = ((Radiogroup) row.getAttribute("rg")).getSelectedItem();
			DTO_Alternativa rpt = (DTO_Alternativa) rd.getAttribute("alt");

			DTO_Respuesta rr = new DTO_Respuesta();
			rr.setId_alternativa(rpt.getId_alternativa());
			rr.setId_encuesta(rpt.getId_encuesta());
			rr.setId_pregunta(rpt.getId_pregunta());
			rr.setId_egresado(sum.getCodigo());
			rr.setFecha_rpt(new Date());

			listRespuestas.add(rr);
		}
	}

	@Listen("onClick = #btn_registrar")
	public void registra(Event e) {
		if (pass1.getValue().trim().equals("")  || pass2.getValue().trim().equals("")) {
			Messagebox.show("Debe ingresar en todos los campos.", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			if(!pass1.getValue().equals(pass2.getValue())){
				Messagebox.show("Las contraseñas no coinciden.", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}else{
				DTO_Usuario usuario = new DTO_Usuario();
				DTO_Sum sum = (DTO_Sum) Executions.getCurrent().getDesktop().getSession().getAttribute("SUM");
				DTO_Reniec dni = (DTO_Reniec) Executions.getCurrent().getDesktop().getSession().getAttribute("DNI");
				usuario.setId_usuario(sum.getCodigo());
				usuario.setContrasenia(pass1.getValue());
				usuario.setNombre_user(dni.getNombre());
				usuario.setTipo_user("EGRESADO");
				authenticationService.addUsuario(usuario);
							
				System.out.println("Registrando sus respuestas del "+sum.getCodigo());
				for (DTO_Respuesta rr : listRespuestas) {
					System.out.println(rr.toString());
					service.addRespuesta(rr);
				}
				Executions.getCurrent().getDesktop().getSession().setAttribute("login", usuario);
				modalDialog.detach();
				Executions.sendRedirect("index.zul");
			}
		}
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		authenticationService = (AuthenticationService)getBean("authenticationService");		
		
		sum = (DTO_Sum) Executions.getCurrent().getSession().getAttribute("SUM");
		dni = (DTO_Reniec) Executions.getCurrent().getSession().getAttribute("DNI");

		lb_nombre.setValue(dni.getNombre() +" "+ dni.getApellido());
		lb_dni.setValue("" + dni.getDni());
		lb_telf_fijo.setValue("-");
		lb_telf_cel.setValue("-");
		lb_direccion.setValue(dni.getDireccion());
		lb_distrito.setValue(dni.getDistrito());
		lb_departamento.setValue(dni.getDepartamento());
		lb_provincia.setValue(dni.getProvincia());

		lb_codigo.setValue("" + sum.getCodigo());
		lb_facultad.setValue(sum.getFacultad());
		lb_eap.setValue(sum.getEap());
		ld_tipo_formacion.setValue("-");

		if (sum.getF_egreso() != null)
			lb_f_egreso.setValue(format.format(sum.getF_egreso()));
		if (sum.getF_grado() != null)
			lb_f_grado.setValue(format.format(sum.getF_grado()));
		if (sum.getF_titulo() != null)
			lb_f_titulo.setValue(format.format(sum.getF_titulo()));

		lb_b_codigo.setValue("CÓDIGO: " + sum.getCodigo());
	}
	
	public void finalizarEncuesta(){
		v_p2.setVisible(false);
		v_p3.setVisible(true);
	}

	@Listen("onClick = #btn_siguiente")
	public void siguiente(Event e) {
		if (validaSelect()) {
			saveSelect();
			if (pos_pg == nro_pg) {
				finalizarEncuesta();
			}

			if (pos_pg < nro_pg) {
				grip_encuesta.getRows().getChildren().clear();
				for (int i = 0; i < limite; i++) {
					int pp = pos_pg * limite + i;
					if (pp == listPreguntas.size())
						break;
					Row row = createPregunta(pp + 1, listPreguntas.get(pp), service.getAlternativas(
							listPreguntas.get(pp).getId_encuesta(), listPreguntas.get(pp).getId_pregunta()));
					grip_encuesta.getRows().appendChild(row);
				}
				pos_pg++;
				if (pos_pg == nro_pg)
					btn_siguiente.setLabel("Finalizar");
			}
		}
	}

	private Row createPregunta(int nro, DTO_Pregunta pregunta, List<DTO_Alternativa> listAlternativas) {
		Row fila = new Row();
		Vbox vbox = new Vbox();
		Label pre = new Label(nro + ". " + pregunta.getEnunciado_pr());
		pre.setStyle("font-weight: bold;");
		
		vbox.appendChild(pre);
		Radiogroup rg = new Radiogroup();

		for (DTO_Alternativa alt : listAlternativas) {
			Radio rd = new Radio();
			rd.setStyle("margin-left: 5px;");
			rd.setRadiogroup(rg);
			rd.setLabel(alt.getEnunciado_alt());
			rd.setAttribute("alt", alt);
			vbox.appendChild(rd);
		}
		groupbox.appendChild(rg);
		fila.setAttribute("rg", rg);
		fila.appendChild(vbox);
		return fila;
	}

	@SuppressWarnings("deprecation")
	public static Object getBean(String name) {
		ServletContext ctx = (ServletContext) Executions.getCurrent().getDesktop().getWebApp().getNativeContext();
		return WebApplicationContextUtils.getWebApplicationContext(ctx).getBean(name);
	}
}
