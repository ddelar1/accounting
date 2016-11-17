package com.novellius.dao;

import java.util.List;

import com.novellius.pojo.Usuario;

public interface UsuarioDao {
	
	public Usuario findByUsername(String usuario);

	public void seve(Usuario usuario);

	public List<Usuario> findAll();

}
	