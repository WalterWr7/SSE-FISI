package com.softwarecenter.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwarecenter.domain.DTO_Input;
import com.softwarecenter.domain.DTO_Output;
import com.softwarecenter.domain.DTO_Usuario;

@Service
public class AuthenticationService {
	static Log logger = LogFactory.getLog(AuthenticationService.class);

	@Autowired
	private PasswordHashService passwordHashService;

	public DTO_Output execute(DTO_Input input) {
		DTO_Output output = new DTO_Output();
		DTO_Usuario suposedUser = (DTO_Usuario) input.getObject();
		String username = (String) suposedUser.getUsername();
		String password = (String) suposedUser.getPassword();
		suposedUser.setPassword("erased");
		DTO_Usuario usr = null;// = dao.leeContacto(username);
		if (usr == null) {
			logger.error("No se proporciono usuario valido");
			output.setErrorCode(Constantes.NOT_FOUND);
			return output;
		}
		if (password == null || password.length() == 0) {
			logger.error("No se proporciono password valido");
			output.setErrorCode(Constantes.NOT_FOUND);
			return output;
		}

		// ver si esta activo
		if (usr.getActivo() != 1) {
			logger.error("El usuario " + username + " no esta activo");
			output.setErrorCode(Constantes.NO_ACTIVO);
			return output;
		}

		logger.debug("Ingreso a autenticacion local");
		if (encriptacion(password).equals(usr.getPassword())) {
			output.setObject(usr);
			output.setErrorCode(Constantes.OK);
			return output;

		} else {
			output.setErrorCode(Constantes.AUTH_ERROR);
			return output;
		}
	}

	private String encriptacion(String pass) {
		String passEncriptada = "";
		DTO_Output output;
		DTO_Input iPass = new DTO_Input();
		iPass.setObject(pass);
		output = passwordHashService.execute(iPass);
		if (output.getErrorCode() == Constantes.OK) {
			passEncriptada = (String) output.getObject();
		} else {
			throw new RuntimeException("Error en encriptacion hash de password");
		}
		return passEncriptada;
	}

	public int fechasDiferenciaEnDias(Date fechaInicial) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date fechaFinal = cal.getTime();

		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double dias = Math.round((diferencia / (1000f * 60f * 60f * 24f)) + 0.5f);
		return ((int) dias);
	}
}
