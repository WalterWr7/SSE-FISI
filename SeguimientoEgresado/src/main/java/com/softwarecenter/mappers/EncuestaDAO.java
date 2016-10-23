package com.softwarecenter.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.softwarecenter.domain.DTO_Alternativa;
import com.softwarecenter.domain.DTO_Encuesta;
import com.softwarecenter.domain.DTO_Pregunta;
import com.softwarecenter.domain.DTO_Respuesta;

public interface EncuestaDAO {

	@Select("SELECT id_encuesta, titulo_enc, dscp_enc, tema_enc FROM ENCUESTA WHERE id_encuesta = #{id_encuesta}")
	public DTO_Encuesta getEncuesta(@Param("id_encuesta")String id_encuesta);
	
	@Select("SELECT id_pregunta, id_encuesta, enunciado_pr FROM PREGUNTA WHERE id_encuesta = #{id_encuesta}")
	public List<DTO_Pregunta> getPreguntas(@Param("id_encuesta")String id_encuesta);
	
	@Select("SELECT id_alternativa, id_pregunta, id_encuesta, enunciado_alt FROM ALTERNATIVA WHERE id_encuesta = #{id_encuesta} AND id_pregunta = #{id_pregunta}")
	public List<DTO_Alternativa> getAlternativas(@Param("id_encuesta")String id_encuesta, @Param("id_pregunta")String id_pregunta);
		
	@Insert("INSERT INTO RESPUESTA(ID_PREGUNTA, ID_ENCUESTA, ID_ALTERNATIVA, FECHA_RPT, ID_EGRESADO) VALUES (#{rpt.id_pregunta}, #{rpt.id_encuesta}, #{rpt.id_alternativa}, #{rpt.fecha_rpt}, #{rpt.id_egresado})")
	public void addRespuesta(@Param("rpt")DTO_Respuesta rpt);
}
