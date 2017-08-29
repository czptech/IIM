/**
 * 
 */
package com.organization.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.organization.service.ClassService;
import com.organization.web.beans.Clazz;

/**
 * @author sampath
 *
 */
@RestController
@RequestMapping(value="/class")
public class ClassController {
	
	@Autowired
	private ClassService classService;
	
	private static Log log = LogFactory.getLog(ClassController.class);
	
	@RequestMapping(value="/create", method = RequestMethod.POST, headers="Accept=application/json")
	@ResponseBody
	public String createClass(@RequestBody Clazz clazz) {
		return classService.createClass(clazz);
	}
	
	@RequestMapping(value="/{classId}", method = RequestMethod.GET)
	@ResponseBody
	public Clazz getClass(@PathVariable String classId) {
		Clazz clz = classService.getClazz(classId);
		return clz;
	}
	
	@RequestMapping(value="/updateClass", method = RequestMethod.POST, headers="Accept=application/json")
	@ResponseBody
	public String updateClass(@RequestBody Clazz clazz) {
		classService.updateClass(clazz);
		return "";
	}
	
	@RequestMapping(value="/delete/{classId}")
	@ResponseBody
	public String deleteClass(@PathVariable String classId) {
		classService.deleteClass(classId);
		return "";
	}
}
