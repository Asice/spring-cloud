package com.qurich.common.filter;

import com.qurich.common.model.Result;
import com.qurich.common.model.ResultCodeEnum;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyExceptionResolver implements HandlerExceptionResolver {


    //全部返回json
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        Result result=new Result();
        //如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url
        if(ex instanceof UnauthorizedException){
            result.setCode(ResultCodeEnum.FAIL);
            result.setMsg("无权访问");
        }else
        if(ex instanceof MaxUploadSizeExceededException) {
            result.setCode(ResultCodeEnum.FAIL);
            result.setMsg("文件大小超过限制");
        }else
        if(ex instanceof DuplicateKeyException) {
            result.setCode(ResultCodeEnum.FAIL);
            if(ex.getMessage().contains("Duplicate entry")) {
                String msg=ex.getMessage().substring(ex.getMessage().lastIndexOf("Duplicate entry"), ex.getMessage().length());
                result.setMsg(msg);
            }
        }else {
            result.setCode(ResultCodeEnum.FAIL);
            result.setMsg(ex.getMessage());
        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            response.getWriter().write(JSONObject.fromObject(result).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ex.printStackTrace();
        return new ModelAndView();
    }

}
