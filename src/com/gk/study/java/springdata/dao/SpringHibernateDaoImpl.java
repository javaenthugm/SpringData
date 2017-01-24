package com.gk.study.java.springdata.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpringHibernateDaoImpl {
	@Autowired
	private SessionFactory sessionFactory;
	
	public int getUserCount(){		
		String hql ="select count(*) from User";
		Query query = getSessionFactory().openSession().createQuery(hql);		
		return ((Long)query.uniqueResult()).intValue();
	}
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

}
