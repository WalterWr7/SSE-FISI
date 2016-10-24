/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     23/10/2016 07:08:09 p.m.                     */
/*==============================================================*/


drop table if exists ALTERNATIVA;

drop table if exists CONSULTA_EVENTOS;

drop table if exists CONSULTA_NOTICIAS;

drop table if exists DIRECTORACADEMICO;

drop table if exists EGRESADO;

drop table if exists ENCUESTA;

drop table if exists ESCUELA;

drop table if exists EVENTO;

drop table if exists NOTICIA;

drop table if exists PREGUNTA;

drop table if exists RENIEC;

drop table if exists REPORTE;

drop table if exists RESPONSABLEACREDITACION;

drop table if exists RESPONSABLEPOSTGRADO;

drop table if exists RESPUESTA;

drop table if exists SUM;

drop table if exists TIPOEGRESO;

drop table if exists USUARIO;

/*==============================================================*/
/* Table: ALTERNATIVA                                           */
/*==============================================================*/
create table ALTERNATIVA
(
   ID_ALTERNATIVA       varchar(4) not null,
   ID_PREGUNTA          varchar(4) not null,
   ID_ENCUESTA          varchar(4) not null,
   ENUNCIADO_ALT        varchar(200),
   FECHA_MODIF          date,
   primary key (ID_ALTERNATIVA, ID_PREGUNTA, ID_ENCUESTA)
);

/*==============================================================*/
/* Table: CONSULTA_EVENTOS                                      */
/*==============================================================*/
create table CONSULTA_EVENTOS
(
   ID_USUARIO           numeric(8,0) not null,
   ID_EVENTO            numeric(8,0) not null,
   primary key (ID_USUARIO, ID_EVENTO)
);

/*==============================================================*/
/* Table: CONSULTA_NOTICIAS                                     */
/*==============================================================*/
create table CONSULTA_NOTICIAS
(
   ID_USUARIO           numeric(8,0) not null,
   ID_NOTICIA           numeric(8,0) not null,
   primary key (ID_USUARIO, ID_NOTICIA)
);

/*==============================================================*/
/* Table: DIRECTORACADEMICO                                     */
/*==============================================================*/
create table DIRECTORACADEMICO
(
   ID_USUARIO           numeric(8,0) not null,
   ID_DIR_ACAD          numeric(8,0) not null,
   NOMBRE_USER          varchar(20),
   CONTRASENIA          varchar(20),
   TIPO_USER            varchar(20),
   FECHA_LOGIN          date,
   NOMBRE_DA            varchar(150),
   FECHA_I_DA           date,
   FECHA_S_DA           date,
   primary key (ID_USUARIO, ID_DIR_ACAD)
);

/*==============================================================*/
/* Table: EGRESADO                                              */
/*==============================================================*/
create table EGRESADO
(
   ID_USUARIO           numeric(8,0) not null,
   ID_EGRESADO          numeric(8,0) not null,
   NOMBRE_USER          varchar(20),
   CONTRASENIA          varchar(20),
   TIPO_USER            varchar(20),
   FECHA_LOGIN          date,
   COD_EGRESADO         numeric(8,0),
   AP_PATERNO           varchar(50),
   AP_MATERNO           varchar(50),
   NOMBRES              varchar(50),
   CORREO_INST          varchar(50),
   CORREO_PER           varchar(50),
   EDAD                 numeric(2,0),
   SEXO                 char(1),
   DOCUMENTO            varchar(10),
   SITUACION            varchar(30),
   FECHA_EGRESO         date,
   primary key (ID_USUARIO, ID_EGRESADO)
);

/*==============================================================*/
/* Table: ENCUESTA                                              */
/*==============================================================*/
create table ENCUESTA
(
   ID_ENCUESTA          varchar(4) not null,
   ID_USUARIO           numeric(8,0),
   ID_RACRED            numeric(8,0),
   DIR_ID_USUARIO       numeric(8,0),
   ID_DIR_ACAD          numeric(8,0),
   TITULO_ENC           varchar(50),
   DSCP_ENC             varchar(500),
   TEMA_ENC             varchar(150),
   primary key (ID_ENCUESTA)
);

/*==============================================================*/
/* Table: ESCUELA                                               */
/*==============================================================*/
create table ESCUELA
(
   ID_ESCUELA           numeric(8,0) not null,
   ID_USUARIO           numeric(8,0),
   ID_EGRESADO          numeric(8,0),
   CODIGO_ESCUELA       varchar(20),
   NOMBRE_ESCUELA       varchar(150),
   primary key (ID_ESCUELA)
);

/*==============================================================*/
/* Table: EVENTO                                                */
/*==============================================================*/
create table EVENTO
(
   ID_EVENTO            numeric(8,0) not null,
   DESC_EVENTO          varchar(2000),
   AUTOR_EV             varchar(50),
   LUGAR_EV             varchar(100),
   TEMA_EV              varchar(50),
   TITULO_EV            varchar(50),
   FECHA_EV             date,
   primary key (ID_EVENTO)
);

/*==============================================================*/
/* Table: NOTICIA                                               */
/*==============================================================*/
create table NOTICIA
(
   ID_NOTICIA           numeric(8,0) not null,
   DESC_NOTICIA         text,
   FECHA_BAJA           date,
   FECHA_ALTA           date,
   AUTOR_NOT            varchar(150),
   TEMA_NOT             varchar(100),
   primary key (ID_NOTICIA)
);

/*==============================================================*/
/* Table: PREGUNTA                                              */
/*==============================================================*/
create table PREGUNTA
(
   ID_PREGUNTA          varchar(4) not null,
   ID_ENCUESTA          varchar(4) not null,
   ENUNCIADO_PR         varchar(300),
   TIPO_PR              varchar(10),
   primary key (ID_PREGUNTA, ID_ENCUESTA)
);

/*==============================================================*/
/* Table: RENIEC                                                */
/*==============================================================*/
create table RENIEC
(
   DNI                  numeric not null,
   NOMBRE               varchar(30),
   APELLIDO             varchar(30),
   SEXO                 varchar(1),
   FECHA_NAC            date,
   DIRECCION            varchar(100),
   DEPARTAMENTO         varchar(30),
   PROVINCIA            varchar(30),
   DISTRITO             varchar(30),
   primary key (DNI)
);

/*==============================================================*/
/* Table: REPORTE                                               */
/*==============================================================*/
create table REPORTE
(
   ID_REPORTE           numeric(8,0) not null,
   DIR_ID_USUARIO       numeric(8,0),
   ID_DIR_ACAD          numeric(8,0),
   ID_USUARIO           numeric(8,0),
   ID_RACRED            numeric(8,0),
   RES_ID_USUARIO       numeric(8,0),
   ID_REP_POST          numeric(8,0),
   DESC_REPORT          varchar(200),
   RUTA                 varchar(150),
   FECHA_REP            date,
   primary key (ID_REPORTE)
);

/*==============================================================*/
/* Table: RESPONSABLEACREDITACION                               */
/*==============================================================*/
create table RESPONSABLEACREDITACION
(
   ID_USUARIO           numeric(8,0) not null,
   ID_RACRED            numeric(8,0) not null,
   NOMBRE_USER          varchar(20),
   CONTRASENIA          varchar(20),
   TIPO_USER            varchar(20),
   FECHA_LOGIN          date,
   NOMBRE_R_A           varchar(150),
   FECHA_I_RA           date,
   FECHA_S_RA           date,
   primary key (ID_USUARIO, ID_RACRED)
);

/*==============================================================*/
/* Table: RESPONSABLEPOSTGRADO                                  */
/*==============================================================*/
create table RESPONSABLEPOSTGRADO
(
   ID_USUARIO           numeric(8,0) not null,
   ID_REP_POST          numeric(8,0) not null,
   NOMBRE_USER          varchar(20),
   CONTRASENIA          varchar(20),
   TIPO_USER            varchar(20),
   FECHA_LOGIN          date,
   NOMBRE_RP            varchar(150),
   FECHA_I_RP           date,
   FECHA_S_RP           date,
   primary key (ID_USUARIO, ID_REP_POST)
);

/*==============================================================*/
/* Table: RESPUESTA                                             */
/*==============================================================*/
create table RESPUESTA
(
   ID_PREGUNTA          varchar(4),
   ID_ENCUESTA          varchar(4),
   ID_ALTERNATIVA       varchar(4),
   FECHA_RPT            datetime,
   ID_EGRESADO          numeric
);

/*==============================================================*/
/* Table: SUM                                                   */
/*==============================================================*/
create table SUM
(
   CODIGO               numeric not null,
   DNI                  numeric,
   FACULTAD             varchar(30),
   EAP                  varchar(50),
   F_EGRESO             date,
   F_GRADO              date,
   F_TITULO             date,
   primary key (CODIGO)
);

/*==============================================================*/
/* Table: TIPOEGRESO                                            */
/*==============================================================*/
create table TIPOEGRESO
(
   ID_TIPOEG            numeric(8,0) not null,
   ID_USUARIO           numeric(8,0),
   ID_EGRESADO          numeric(8,0),
   TIPO_EGRESO          varchar(50),
   primary key (ID_TIPOEG)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   ID_USUARIO           numeric(8,0) not null,
   NOMBRE_USER          varchar(20),
   CONTRASENIA          varchar(20),
   TIPO_USER            varchar(20),
   FECHA_LOGIN          date,
   primary key (ID_USUARIO)
);

alter table ALTERNATIVA add constraint FK_CONTIENE foreign key (ID_PREGUNTA, ID_ENCUESTA)
      references PREGUNTA (ID_PREGUNTA, ID_ENCUESTA) on delete restrict on update restrict;

alter table CONSULTA_EVENTOS add constraint FK_CONSULTA_EVENTOS foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO) on delete restrict on update restrict;

alter table CONSULTA_EVENTOS add constraint FK_CONSULTA_EVENTOS2 foreign key (ID_EVENTO)
      references EVENTO (ID_EVENTO) on delete restrict on update restrict;

alter table CONSULTA_NOTICIAS add constraint FK_CONSULTA_NOTICIAS foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO) on delete restrict on update restrict;

alter table CONSULTA_NOTICIAS add constraint FK_CONSULTA_NOTICIAS2 foreign key (ID_NOTICIA)
      references NOTICIA (ID_NOTICIA) on delete restrict on update restrict;

alter table DIRECTORACADEMICO add constraint FK_USER_DIR_ACAD foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO) on delete restrict on update restrict;

alter table EGRESADO add constraint FK_USER_EGRESADO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO) on delete restrict on update restrict;

alter table ENCUESTA add constraint FK_CREA_ENCUESTA_ACADE foreign key (DIR_ID_USUARIO, ID_DIR_ACAD)
      references DIRECTORACADEMICO (ID_USUARIO, ID_DIR_ACAD) on delete restrict on update restrict;

alter table ENCUESTA add constraint FK_CREA_RA foreign key (ID_USUARIO, ID_RACRED)
      references RESPONSABLEACREDITACION (ID_USUARIO, ID_RACRED) on delete restrict on update restrict;

alter table ESCUELA add constraint FK_PERTENECE foreign key (ID_USUARIO, ID_EGRESADO)
      references EGRESADO (ID_USUARIO, ID_EGRESADO) on delete restrict on update restrict;

alter table PREGUNTA add constraint FK_TIENE foreign key (ID_ENCUESTA)
      references ENCUESTA (ID_ENCUESTA) on delete restrict on update restrict;

alter table REPORTE add constraint FK_COMSULTA_RA foreign key (ID_USUARIO, ID_RACRED)
      references RESPONSABLEACREDITACION (ID_USUARIO, ID_RACRED) on delete restrict on update restrict;

alter table REPORTE add constraint FK_CONSULTA_REP foreign key (DIR_ID_USUARIO, ID_DIR_ACAD)
      references DIRECTORACADEMICO (ID_USUARIO, ID_DIR_ACAD) on delete restrict on update restrict;

alter table REPORTE add constraint FK_CONSULTA_REP_POST foreign key (RES_ID_USUARIO, ID_REP_POST)
      references RESPONSABLEPOSTGRADO (ID_USUARIO, ID_REP_POST) on delete restrict on update restrict;

alter table RESPONSABLEACREDITACION add constraint FK_USER_RESP_ACRED foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO) on delete restrict on update restrict;

alter table RESPONSABLEPOSTGRADO add constraint FK_USER_RESP_POST foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO) on delete restrict on update restrict;

alter table RESPUESTA add constraint FK_TIENE_RPTA foreign key (ID_PREGUNTA, ID_ENCUESTA)
      references PREGUNTA (ID_PREGUNTA, ID_ENCUESTA) on delete restrict on update restrict;

alter table SUM add constraint FK_R foreign key (DNI)
      references RENIEC (DNI) on delete restrict on update restrict;

alter table TIPOEGRESO add constraint FK_PERTENECETIPO foreign key (ID_USUARIO, ID_EGRESADO)
      references EGRESADO (ID_USUARIO, ID_EGRESADO) on delete restrict on update restrict;

