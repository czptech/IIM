package com.organization.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class SyllabusTrackerDao<T> {
	
	@Autowired
	private static SessionFactory sessionFactory;
    private Session session;
    private Transaction tx;
    private static Log log = LogFactory.getLog(SyllabusTrackerDao.class);
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    

    public Session openSession() throws Exception {
        return sessionFactory.openSession();
    }

    public static void closeFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException e) {
                log.error("Couldn't close SessionFactory", e);
            }
        }
    }

    public static void close(Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException e) {
                log.error("Couldn't close Session", e);
            }
        }
    }

    public static void rollback(Transaction tx) {
        try {
            if (tx != null) {
                tx.rollback();
            }
        } catch (Exception e) {
            log.error("Couldn't rollback Transaction", e);
        }
    }
    
    public void saveOrUpdate(Object obj) throws Exception {
        try {
        	session = openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
        } catch (Exception e) {
        	rollback(tx);
        	throw new Exception(e);
        } finally {
            close(session);
        }
    }

    public void delete(Object obj) throws Exception {
        try {
        	session = openSession();
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        } catch (HibernateException e) {
        	rollback(tx);
        	throw new Exception(e);
        } finally {
            close(session);
        }
    }

    public T find(Class clazz, Long id) throws Exception {
        T obj = null;
        try {
        	session = openSession();
            tx = session.beginTransaction();
            obj = (T) session.load(clazz, id);
            tx.commit();
        } catch (Exception e) {
        	rollback(tx);
        	throw new Exception(e);
        } finally {
            close(session);
        }
        return obj;
    }

    public List<T> findAll(Class clazz) throws Exception {
        List objects = null;
        try {
        	session = openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from " + clazz.getName());
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
        	rollback(tx);
        	throw new Exception(e);
        } finally {
            close(session);
        }
        return objects;
    }
}