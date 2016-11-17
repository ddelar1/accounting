package com.novellius.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.TransactionScoped;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.novellius.pojo.Admin;
import com.novellius.pojo.AdminRowMapper;

@Transactional
@Repository
	
public class AdminDaoImpl implements AdminDao{
	
	@Autowired
	private  SessionFactory sessionFactory;	

	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	@Override
	public void save(Admin admin) {
		// TODO Auto-generated method stub
		getSession().save(admin);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> findAll() {
		Query query = getSession().createQuery("from Admin");// MySql -> Select * from Admin;
		return query.list();
	}

	@Override
	public Admin findById(int id) {
		
		Criteria crit = getSession().createCriteria(Admin.class);
		crit.add(Restrictions.eq("idAd", id));
		
		return (Admin) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> findByNombre(String nombre) {
		Criteria crit  = getSession().createCriteria(Admin.class);
		crit.add(Restrictions.like("nombre", "%" + nombre + "%"));
		
		return crit.list();
	}

	@Override
	public void update(Admin admin) {
		getSession().update(admin);
	}

	@Override
	public void delete(Admin admin) {
		getSession().delete(admin);
		
	}
	
	
	

}
