package com.softwarecenter.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.softwarecenter.domain.DTO_Usuario;

public interface UsuarioDAO {
	
	@Select("SELECT id_usuario, nombre_user, contrasenia, tipo_user, fecha_login FROM USUARIO WHERE contrasenia = #{contrasenia} AND id_usuario = #{id_usuario}")
	public DTO_Usuario getUsuario(@Param("id_usuario")Integer id_usuario, @Param("contrasenia")String contrasenia);
	
	@Select("SELECT id_usuario, nombre_user, contrasenia, tipo_user, fecha_login FROM USUARIO WHERE id_usuario = #{id_usuario}")
	public DTO_Usuario getExistUsuario(@Param("id_usuario")Integer id_usuario);
	
	@Insert("INSERT INTO USUARIO(ID_USUARIO,NOMBRE_USER,CONTRASENIA,TIPO_USER,FECHA_LOGIN) VALUES(#{usuario.id_usuario},#{usuario.nombre_user},#{usuario.contrasenia},#{usuario.tipo_user},#{usuario.fecha_login})")
	public void addUsuario(@Param("usuario")DTO_Usuario usuario);
	
	@Update("UPDATE USUARIO SET FECHA_LOGIN = #{usuario.fecha_login} WHERE ID_USUARIO = #{usuario.id_usuario}")
	public void updateUsuarioLogin(@Param("usuario")DTO_Usuario usuario);
		
	
	
}
