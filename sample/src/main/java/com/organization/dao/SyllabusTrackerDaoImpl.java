package com.organization.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

public class SyllabusTrackerDaoImpl<T, PK extends Serializable> {

	private HibernateTemplate hibernateTemplate;
	private Class<T> type;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		hibernateTemplate.setCheckWriteOperations(false);
	}
	
	public void setType(Class<T> type) {
		this.type = type;
	}
	
	/**
	 * @param obj
	 * @return
	 */
	@Transactional(readOnly=false)
	public PK save(T obj) {
		return (PK)hibernateTemplate.save(obj);
	}

	/**
	 * @param obj
	 */
	public void update(T obj) {
		hibernateTemplate.saveOrUpdate(obj);
	}
	
	/**
	 * @param obj
	 */
	public void delete(T obj) {
		hibernateTemplate.delete(obj);
	}

	/**
	 * 
	 */
	public void deleteAll() {
		hibernateTemplate.bulkUpdate("delete from "+ type.getSimpleName());
	}

	/**
	 * @return
	 */
	public List<T> findAll() {
		return (List<T>) hibernateTemplate.find("from "+ type.getName());
	}

	/**
	 * @param obj
	 * @return
	 */
	public List<T> findByExample(T obj) {
		return hibernateTemplate.findByExample(obj);
	}
	
	/**
	 * @param id
	 * @return
	 */
	public T findById(PK id) {
		return (T) hibernateTemplate.get(type, id);
	}

	/**
	 * @param id
	 * @return
	 */
	public T findByIdUnique(PK id) {
		return (T) hibernateTemplate.get(type, id);
	}
	
	/**
	 * @param id
	 * @param lock
	 * @return
	 */
	public T findById(PK id, boolean lock) {
		if(lock)
			return (T) hibernateTemplate.get(type, id, LockMode.UPGRADE);
		else return (T) hibernateTemplate.get(type, id);
	}

	/**
	 * 
	 */
	public void flush() {
		hibernateTemplate.flush();
	}
}
