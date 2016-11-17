package com.novellius.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.novellius.pojo.Usuario;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao{

	@Autowired
	private  SessionFactory sessionFactory;	

	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	@Override
	public Usuario findByUsername(String usuario) {
		Criteria crit = getSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("usuario", usuario));
		return (Usuario) crit.uniqueResult();
	}


	@Override
	public void seve(Usuario usuario) {
		getSession().save(usuario);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAll() {
		return getSession().createQuery("from Usuario").list();
	}

}
