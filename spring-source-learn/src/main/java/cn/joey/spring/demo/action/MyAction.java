package cn.joey.spring.demo.action;

import cn.joey.spring.annotation.GPAutoWired;
import cn.joey.spring.annotation.GPController;
import cn.joey.spring.annotation.GPRequestMapping;
import cn.joey.spring.annotation.GPRequestParam;
import cn.joey.spring.demo.service.IModifyService;
import cn.joey.spring.demo.service.IQueryService;
import cn.joey.spring.webmvc.GPModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公布接口url
 * @author Tom
 *
 */
@GPController
@GPRequestMapping("/web")
public class MyAction {

	@GPAutoWired
	IQueryService queryService;
	@GPAutoWired
	IModifyService modifyService;
	
	@GPRequestMapping("/query.json")
	public GPModelAndView query(HttpServletRequest request, HttpServletResponse response,
								@GPRequestParam("name") String name){
		String result = queryService.query(name);
		System.out.println(result);
		return out(response,result);
	}
	
	@GPRequestMapping("/add*.json")
	public GPModelAndView add(HttpServletRequest request,HttpServletResponse response,
			   @GPRequestParam("name") String name,@GPRequestParam("addr") String addr){
		String result = modifyService.add(name,addr);
		return out(response,result);
	}
	
	@GPRequestMapping("/remove.json")
	public GPModelAndView remove(HttpServletRequest request,HttpServletResponse response,
		   @GPRequestParam("id") Integer id){
		String result = modifyService.remove(id);
		return out(response,result);
	}
	
	@GPRequestMapping("/edit.json")
	public GPModelAndView edit(HttpServletRequest request,HttpServletResponse response,
			@GPRequestParam("id") Integer id,
			@GPRequestParam("name") String name){
		String result = modifyService.edit(id,name);
		return out(response,result);
	}
	
	
	
	private GPModelAndView out(HttpServletResponse resp,String str){
		try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
