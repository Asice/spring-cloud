package com.qurich.mapper;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;

import com.qurich.model.User;

@Mapper
public interface UserMapper {
	//添加
	@Insert("INSERT INTO t_user (`username`,`password`,`status`,`last_login_time`,`register_ip`,`last_login_ip`,`last_login_city`,`ctime`,`utime`,`cname`,`uname`,`is_del`)"+
		"VALUES (#{username},#{password},#{status},#{last_login_time},#{register_ip},#{last_login_ip},#{last_login_city},#{ctime},#{utime},#{cname},#{uname},#{is_del});")
	@Options(useGeneratedKeys=true)
	Integer saveUser(User user);

	//通过id查询
	@Select("select * from t_user where id=#{id};")
	@Results(id="userMap", value={
		@Result(property="last_login_time",column="last_login_time"),
		@Result(property="register_ip",column="register_ip"),
		@Result(property="last_login_ip",column="last_login_ip"),
		@Result(property="last_login_city",column="last_login_city"),
		@Result(property="is_del",column="is_del")
	})
	User getUserById(@Param("id")int id);

	//修改
	@Update("<script>update t_user set `utime`=#{utime}, `uname`=#{uname} "
		+ "<if test=\"username!=null\">,`username` = #{username}</if>"
		+ "<if test=\"password!=null\">,`password` = #{password}</if>"
		+ "<if test=\"status!=null\">,`status` = #{status}</if>"
		+ "<if test=\"last_login_time!=null\">,`last_login_time` = #{last_login_time}</if>"
		+ "<if test=\"register_ip!=null\">,`register_ip` = #{register_ip}</if>"
		+ "<if test=\"last_login_ip!=null\">,`last_login_ip` = #{last_login_ip}</if>"
		+ "<if test=\"last_login_city!=null\">,`last_login_city` = #{last_login_city}</if>"
		+ " WHERE id=#{id};</script>")
	Integer updateUser(User user);

	//逻辑删除
	@Delete("update t_user set is_del=1,utime=#{utime},uname=#{uname} where id=#{id};")
	Integer deleteUser(@Param("id")int id,@Param("utime")Date utime,@Param("uname")String uname);

	//查询条件列表
	@Select({"<script>select * from t_user where is_del=0 "
		+ "<if test=\"bean.id!=null and bean.id!=''\">and `id` = #{bean.id} </if>"
		+ "<if test=\"bean.username!=null and bean.username!=''\">and `username` = #{bean.username} </if>"
		+ "<if test=\"bean.password!=null and bean.password!=''\">and `password` = #{bean.password} </if>"
		+ "<if test=\"bean.status!=null and bean.status!=''\">and `status` = #{bean.status} </if>"
		+ "<if test=\"bean.last_login_time!=null and bean.last_login_time!=''\">and `last_login_time` = #{bean.last_login_time} </if>"
		+ "<if test=\"bean.register_ip!=null and bean.register_ip!=''\">and `register_ip` = #{bean.register_ip} </if>"
		+ "<if test=\"bean.last_login_ip!=null and bean.last_login_ip!=''\">and `last_login_ip` = #{bean.last_login_ip} </if>"
		+ "<if test=\"bean.last_login_city!=null and bean.last_login_city!=''\">and `last_login_city` = #{bean.last_login_city} </if>"
		+ "<if test=\"bean.cname!=null and bean.cname!=''\">and `cname` = #{bean.cname} </if>"
		+ "<if test=\"bean.uname!=null and bean.uname!=''\">and `uname` = #{bean.uname} </if>"
		+ "order by `${field}` ${sort} limit #{offset},#{rows}</script>"})
	@ResultMap("userMap")
	List<User> getUsersByCondition(@Param("bean")User bean, @Param("sort")String sort,@Param("field")String field,@Param("offset") int offset,@Param("rows") int rows);

	//查询条件列表总条数
	@Select({"<script>select count(1) from t_user where is_del=0 "
		+ "<if test=\"bean.id!=null and bean.id!=''\">and `id` = #{bean.id} </if>"
		+ "<if test=\"bean.username!=null and bean.username!=''\">and `username` = #{bean.username} </if>"
		+ "<if test=\"bean.password!=null and bean.password!=''\">and `password` = #{bean.password} </if>"
		+ "<if test=\"bean.status!=null and bean.status!=''\">and `status` = #{bean.status} </if>"
		+ "<if test=\"bean.last_login_time!=null and bean.last_login_time!=''\">and `last_login_time` = #{bean.last_login_time} </if>"
		+ "<if test=\"bean.register_ip!=null and bean.register_ip!=''\">and `register_ip` = #{bean.register_ip} </if>"
		+ "<if test=\"bean.last_login_ip!=null and bean.last_login_ip!=''\">and `last_login_ip` = #{bean.last_login_ip} </if>"
		+ "<if test=\"bean.last_login_city!=null and bean.last_login_city!=''\">and `last_login_city` = #{bean.last_login_city} </if>"
		+ "<if test=\"bean.cname!=null and bean.cname!=''\">and `cname` = #{bean.cname} </if>"
		+ "<if test=\"bean.uname!=null and bean.uname!=''\">and `uname` = #{bean.uname} </if>"
		+"</script>"})
	Integer getUsersByConditionCount(@Param("bean")User bean);

	//////////////////////////////上面代码由代码生成器生成////////////////////////////////////

}