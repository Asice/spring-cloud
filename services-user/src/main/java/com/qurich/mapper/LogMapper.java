package com.qurich.mapper;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import com.qurich.model.Log;

@Mapper
public interface LogMapper {
	//添加
	@Insert("INSERT INTO t_log (`tid`,`tname`,`content`,`ip`,`location`,`ctime`,`utime`,`cname`,`uname`,`is_del`)"+
		"VALUES (#{tid},#{tname},#{content},#{ip},#{location},#{ctime},#{utime},#{cname},#{uname},#{is_del});")
	@Options(useGeneratedKeys=true)
	Integer saveLog(Log log);
	//////////////////////////////上面代码由代码生成器生成////////////////////////////////////

}