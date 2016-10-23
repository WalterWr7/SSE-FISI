package com.softwarecenter.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.softwarecenter.domain.DTO_Alternativa;
import com.softwarecenter.domain.DTO_Encuesta;
import com.softwarecenter.domain.DTO_Pregunta;

public interface EncuestaDAO {

	@Select("SELECT id_encuesta, titulo_enc, dscp_enc, tema_enc FROM ENCUESTA WHERE id_encuesta = #{id_encuesta}")
	public DTO_Encuesta getEncuesta(String id_encuesta);
	
	@Select("SELECT id_pregunta, id_encuesta, enunciado_pr FROM PREGUNTA WHERE id_encuesta = #{id_encuesta}")
	public List<DTO_Pregunta> getPreguntas(String id_encuesta);
	
	@Select("SELECT id_alternativa, id_pregunta, id_encuesta, enunciado_alt FROM ALTERNATIVA WHERE id_encuesta = #{id_encuesta} AND id_pregunta = #{id_pregunta}")
	public List<DTO_Alternativa> getAlternativas(@Param("id_encuesta")String id_encuesta, @Param("id_pregunta")String id_pregunta);
}
