package cn.joey.spring.demo.action;

import cn.joey.spring.annotation.GPAutoWired;
import cn.joey.spring.annotation.GPController;
import cn.joey.spring.annotation.GPRequestMapping;
import cn.joey.spring.annotation.GPRequestParam;
import cn.joey.spring.demo.service.IQueryService;
import cn.joey.spring.webmvc.GPModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 公布接口url
 * @author Tom
 *
 */
@GPController
@GPRequestMapping("/")
public class PageAction {

	@GPAutoWired
	IQueryService queryService;
	
	@GPRequestMapping("/first.html")
	public GPModelAndView query(@GPRequestParam("teacher") String teacher){
		String result = queryService.query(teacher);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("teacher", teacher);
		model.put("data", result);
		model.put("token", "123456");
		return new GPModelAndView("first.html",model);
	}
	
}
