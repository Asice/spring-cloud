package com.qurich.service;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qurich.mapper.LogMapper;
import com.qurich.mapper.UserMapper;
import com.qurich.model.Log;
import com.qurich.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.qurich.common.model.PageMeta;
import com.qurich.common.model.Result;
import com.qurich.common.model.ResultCodeEnum;
import com.qurich.common.utils.IpAddressUtil;
@Service
public class UserService {
	private Logger log=Logger.getLogger(UserService.class);
	@Autowired
	private LogMapper logMapper;//操作日志，入库
	@Autowired
	private UserMapper userMapper;

	//跳转list页面
	public String page(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "user/list";
	}

	//ajax请求list
	public String list(User bean,int page, int perpage, String sort, String field,
					   HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject json=new JSONObject();
		try {
			List<User> lists=userMapper.getUsersByCondition(bean,sort,field,(page-1)*perpage,perpage);
			Integer count=userMapper.getUsersByConditionCount(bean);
			PageMeta pageBean=new PageMeta();
			pageBean.setPerpage(perpage);
			pageBean.setPage(page);
			pageBean.setTotal(null==count?0:count.intValue());
			pageBean.setSort(sort);
			pageBean.setField(field);
			json.put("meta", JSONObject.fromObject(pageBean));
			json.put("data", JSONArray.fromObject(lists).toString());
		}catch(Exception e) {
			log.error("UserService.list error",e);
		}
		return json.toString();
	}

	public String get(int id, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(id>0){
			User bean=userMapper.getUserById(id);
			model.addAttribute("bean", bean);
		}
		return "user/addOrUpdate";
	}

	@Transactional
	public Result add(User user,String username, HttpServletRequest request, HttpServletResponse response, Model model) {
		Result result=new Result();
		user.setCname(username);
		user.setCtime(new Date());
		Integer res=userMapper.saveUser(user);
		if(null!=res&&res>0&&user.getId()>0) {
			String ip=IpAddressUtil.getIp(request);
			logMapper.saveLog(new Log(user.getId(), "t_user", "添加t_user表："+user.getId(),ip,IpAddressUtil.ip2City(ip),username,username));
			result.setCode(ResultCodeEnum.SUCCESS);
			result.setMsg("添加成功！");
			return result;
		}
		result.setCode(ResultCodeEnum.FAIL);
		result.setMsg("操作失败！请重试");
		return result;
	}

	@Transactional
	public Result update(User user,int id,String username, HttpServletRequest request, HttpServletResponse response, Model model) {
		Result result=new Result();
		user.setId(id);
		user.setUname(username);
		user.setUtime(new Date());
		Integer res=userMapper.updateUser(user);
		if(null!=res&&res>0) {
			String ip=IpAddressUtil.getIp(request);
			logMapper.saveLog(new Log(id, "t_user", "修改t_user表："+id,ip,IpAddressUtil.ip2City(ip),username,username));
			result.setCode(ResultCodeEnum.SUCCESS);
			result.setMsg("修改成功！");
			return result;
		}
		result.setCode(ResultCodeEnum.FAIL);
		result.setMsg("操作失败！请重试");
		return result;
	}

	@Transactional
	public Result remove(int id,String username ,HttpServletRequest request, HttpServletResponse response, Model model) {
		Result result=new Result();
		Integer res=userMapper.deleteUser(id, new Date(),username);
		if(null!=res&&res>0) {
			String ip=IpAddressUtil.getIp(request);
			logMapper.saveLog(new Log(id, "t_user", "删除t_user表："+id,ip,IpAddressUtil.ip2City(ip),username,username));
			result.setCode(ResultCodeEnum.SUCCESS);
			result.setMsg("删除成功！");
			return result;
		}
		result.setCode(ResultCodeEnum.FAIL);
		result.setMsg("操作失败！请重试");
		return result;
	}

	//////////////////////////////上面代码由代码生成器生成////////////////////////////////////

}