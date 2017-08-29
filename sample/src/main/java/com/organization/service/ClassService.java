package com.organization.service;

import com.organization.web.beans.Clazz;

public interface ClassService {
	
	String createClass(Clazz clazz);
	Clazz getClazz(String classId);
	String updateClass(Clazz clazz);
	String deleteClass(String classId);

}
