package com.qurich.controller;

import com.qurich.common.model.Result;
import com.qurich.model.User;
import com.qurich.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/v1/users")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;

	//跳转列表页面
	@RequestMapping(value="", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String page(HttpServletRequest request, HttpServletResponse response, Model model) {
		return userService.page(request,response,model);
	}
	@RequestMapping(value="", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String listAjax(
			@RequestParam(value="pagination[page]", required=false, defaultValue="1")int page,
			@RequestParam(value="pagination[perpage]", required=false, defaultValue="10")int perpage,
			@RequestParam(value="sort[sort]", required=false, defaultValue="asc")String sort,
			@RequestParam(value="sort[field]", required=false, defaultValue="id")String field,
			@RequestParam(value="query[undefined][cname]", required=false, defaultValue="")String cname,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user=new User();
		user.setCname(cname);
		return userService.list(user,page,perpage,sort,field,request,response,model);
	}

	//查找
	@RequestMapping(value="/{id}", method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String get(@PathVariable int id,
					   HttpServletRequest request,
					   HttpServletResponse response,
					   Model model) {
		return userService.get(id,request,response,model);
	}
	//添加
	@RequestMapping(value="", method=RequestMethod.PUT,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Result add(User user,HttpServletRequest request, HttpServletResponse response, Model model) {
		//String username=super.getCurrentUser().getUsername();
		String username="管理员";
		return userService.add(user,username,request,response,model);
	}
	//修改
	@RequestMapping(value="/{id}", method=RequestMethod.PATCH,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Result update(@PathVariable int id,User user,HttpServletRequest request, HttpServletResponse response, Model model) {
		String username="管理员";
		return userService.update(user,id,username,request,response,model);
	}
	//删除
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE,produces = "application/json; charset=utf-8")
	@ResponseBody
	public Result remove(@PathVariable int id, HttpServletRequest request, HttpServletResponse response, Model model) {
		String username="管理员";
		return userService.remove(id,username,request,response,model);
	}

}