package com.softwarecenter.service;


public interface Constantes {
	public static final int OK = 1000;

	public static final String REPORTE_PARAM_NOREP = "repnorep";
	public static final String URI_PO_REPORTE = "/PO_Reporte";
	public static final String URI_PO_REPORTE_XLS = "/PO_ReporteExcel";
	public static final String URI_PO_REPORTE_MM = "/PO_ReporteMM";
	public static final String PARAM_REPORT_NAME = "id_rep";
	public static final String PARAM_REPORT_DOWNLOAD_FILENAME = "rep_dw_fn";
	public static final String PARAM_REPORT_JASPER_TEMPLATE = "rep_jp_temp";
	public static final String PARAM_REPORT_XLS_TEMPLATE = "rep_xls_temp";

	public static final String AUDIT_LOGIN_OK = "LGOK";
	public static final String AUDIT_LOGIN_BAD = "LGE1";
	
	
	/** Error de autenticación */
	public static final int AUTH_ERROR = 100;
	/** Parametro suministrado es invalido o nulo */
	public static final int INVALID_PARAM = 101;
	/** No se encontro lo que se solicita */
	public static final int NOT_FOUND = 102;
	/** Error de codigo de respuesta de aplicacion */
	public static final int ERROR_RC = 103;

	/** Password incorrecto */
	public static final int BAD_PASS = 105;
	/** Error de comunicacion */
	public static final int CONN_ERROR = 106;
	/** Registro ya inscrito */
	public static final int ALREADY_USED = 107;
	/** Error de validacion */
	public static final int VALIDATION_ERROR = 108;

	/** Password muy debil */
	public static final int WEAK_PASS = 109;

	/** Requiere investigacion de parte del usuario */
	public static final int GOT_WARNING = 110;

	/** Requiere autorizacion */
	public static final int REQ_AUTH = 111;

	/** Requiere autorizacion */
	public static final int INVALID_STATE = 112;

	/** Password vencido */
	public static final int PASSWORD_VENCIDO = 113;

	/** Password vencido */
	public static final int PASSWORD_REPETIDO = 114;

	/** usuario inactivo */
	public static final int NO_ACTIVO = 115;
	
	/** falta datos */
	public static final int MISSING_DATA = 116;
	
	/** No se encontro lo que se solicita en el SUM*/
	public static final int NOT_FOUND_SUM = 117;
	
	/** No se encontro lo que se solicita en la RENIEC */
	public static final int NOT_FOUND_RENIEC = 118;

	/**
	 * Llave para usuario de auditoria
	 */
	public static final String AUDIT_USER = "audit_user";

		
	public static final String SUPER_ADMIN = "SUPER_ADMIN";
	public static final String USR_ADMIN = "ADMIN";
	public static final String RESOURCE_AA = "AA";
}
