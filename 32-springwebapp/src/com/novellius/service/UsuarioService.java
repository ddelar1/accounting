package com.novellius.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.novellius.dao.UsuarioDao;
import com.novellius.pojo.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void seve(Usuario usuario) {
		usuario.setFechaCreacion(new Timestamp(new Date().getTime()));
		String claveUsr = usuario.getClave();
		usuario.setClave(passwordEncoder.encode(claveUsr));
		usuarioDAO.seve(usuario);
	}

	public List<Usuario> findAll() {
		return usuarioDAO.findAll();
	}
	
}
