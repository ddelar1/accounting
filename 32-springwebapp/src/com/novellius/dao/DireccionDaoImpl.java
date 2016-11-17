package com.novellius.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.TransactionScoped;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
import com.novellius.pojo.Direccion;

@Transactional
@Repository
	
public class DireccionDaoImpl implements DireccionDao{
	
	@Autowired
	private  SessionFactory sessionFactory;	

	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Direccion direccion) {
		getSession().save(direccion);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Direccion> findAll(Admin admin) {
		Criteria crti = getSession().createCriteria(Direccion.class)
				.setFetchMode("admin", FetchMode.JOIN)
				.add(Restrictions.eq("admin.idAd", admin.getIdAd()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crti.list();
	}

	
	
	

}
