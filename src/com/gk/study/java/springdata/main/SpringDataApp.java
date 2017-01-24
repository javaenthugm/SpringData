package com.gk.study.java.springdata.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gk.study.java.springdata.dao.SimpleJdbcDaoImpl;
import com.gk.study.java.springdata.dao.SpringDaoImpl;
import com.gk.study.java.springdata.dao.SpringHibernateDaoImpl;
import com.gk.study.java.springdata.model.User;

public class SpringDataApp {
	
	public static void main(String args[]){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		SpringDaoImpl dao = ctx.getBean("springDaoImpl", SpringDaoImpl.class);
		//SpringDaoImpl dao = new SpringDaoImpl();
		
		//User user = (User)dao.getUser(1);
	    //System.out.println(user.getFirsName());
		
		System.out.println(dao.getUserCount());		
		System.out.println(dao.getUserName(1));		
		User u = dao.getUserObj(1);
		System.out.println(u.getFirsName());		
		System.out.println(dao.getUserList().size());
		
		User newUser = new User();
		newUser.setFirsName("UsingMapNamedParameterTemplate" );
		newUser.setLastName("TemplateLastName");
		//dao.insertUser(newUser);
		//dao.insertUserUsingNamedParameterJdbcTemplate(newUser);
		System.out.println(dao.getUserList().size());
		
		System.out.println("Using Spring DAO Support Classes");
		SimpleJdbcDaoImpl sDao = ctx.getBean("simpleJdbcDaoImpl",SimpleJdbcDaoImpl.class);
		System.out.println(sDao.getUserCount());
		
		
		System.out.println("Using Hiberate");
		SpringHibernateDaoImpl hDao = ctx.getBean("springHibernateDaoImpl",SpringHibernateDaoImpl.class);
		System.out.println(hDao.getUserCount());
		
	}

}
