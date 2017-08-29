package com.organization.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.organization.dao.SyllabusTrackerDaoImpl;
import com.organization.web.beans.Clazz;
import com.organization.web.beans.Standard;

/**
 * @author sampath
 *
 */
public class ClassServiceImpl implements ClassService {

	@Autowired
	private SyllabusTrackerDaoImpl syllabusTrackerDao;
	
	public void setSyllabusTrackerDao(SyllabusTrackerDaoImpl dao) {
		this.syllabusTrackerDao = dao;
		dao.setType(com.organization.dao.dto.Clazz.class);
	}

	@Override
	public String createClass(Clazz clazz) {
		com.organization.dao.dto.Clazz clz = new com.organization.dao.dto.Clazz();
		try {
			BeanUtils.copyProperties(clazz, clz);
			syllabusTrackerDao.save(clz);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Falilure";
		}
		
	}

	@Override
	public Clazz getClazz(String classId) {
		Clazz clazz = null;
		try {
			com.organization.dao.dto.Clazz clz = (com.organization.dao.dto.Clazz) syllabusTrackerDao.findById(Integer.valueOf(classId));
			if(clz != null) {
				clazz = new Clazz();
				BeanUtils.copyProperties(clz, clazz);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return clazz;
	}

	@Override
	public String updateClass(Clazz clazz) {
		com.organization.dao.dto.Clazz clz = new com.organization.dao.dto.Clazz();
		try {
			BeanUtils.copyProperties(clazz, clz);
			syllabusTrackerDao.update(clz);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Falilure";
		}
	}

	@Override
	public String deleteClass(String classId) {
		try {
			com.organization.dao.dto.Clazz stndrd = (com.organization.dao.dto.Clazz) syllabusTrackerDao.findById(Integer.valueOf(classId));
			if(stndrd != null) {
				syllabusTrackerDao.delete(stndrd);
			}
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}
	}

}
