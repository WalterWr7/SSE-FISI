package com.softwarecenter.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.softwarecenter.domain.DTO_Reniec;
import com.softwarecenter.domain.DTO_Sum;

public interface ReniecSumDAO {
	
	@Select("SELECT DNI,NOMBRE,APELLIDO,SEXO,FECHA_NAC,DIRECCION,DEPARTAMENTO,PROVINCIA,DISTRITO FROM RENIEC WHERE dni = #{dni}")
	public DTO_Reniec getDNI(@Param("dni")Integer dni);
		
	@Select("SELECT CODIGO,DNI,FACULTAD,EAP,F_EGRESO,F_GRADO,F_TITULO FROM SUM WHERE codigo = #{codigo}")
	public DTO_Sum getSum(@Param("codigo")Integer codigo);
}
