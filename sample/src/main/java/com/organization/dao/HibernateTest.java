package com.organization.dao;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.organization.service.ClassServiceImpl;
import com.organization.web.beans.Standard;

public class HibernateTest {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext appContext =
		    	  new ClassPathXmlApplicationContext("file:C:/Users/bhums/workspace/syllabustracker/src/main/webapp/WEB-INF/applicationContext.xml");

		SyllabusTrackerDaoImpl dao = (SyllabusTrackerDaoImpl)appContext.getBean("syllabusTrackerDao");
		ClassServiceImpl svc = new ClassServiceImpl();
		//svc.setDao(dao);
		
		//Create
		Standard standard = new Standard();
		standard.setName("LKGG");
		standard.setCreatedOn(new Date());
		standard.setCreatedBy("2");
		//svc.createClass(standard);
		
		//Update
		Standard standard1 = new Standard();
		standard1.setId(3);
		standard1.setName("LKG");
		standard1.setCreatedOn(new Date());
		standard1.setCreatedBy("2");
		//svc.updateClass(standard1);
		
		svc.deleteClass("2");
	}
}
