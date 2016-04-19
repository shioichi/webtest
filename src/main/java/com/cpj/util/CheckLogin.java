package com.cpj.util;

import org.openstack4j.model.identity.Access;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenpengjiang on 2016/4/10.
 */
public class CheckLogin implements HandlerInterceptor {
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {
        Access access = (Access)request.getSession().getAttribute("access_internal");
        if(access==null){
            //用户没有登录
            response.sendRedirect(request.getContextPath()+"/login/tologin");
            return false;
        }else {
            //用户已经登录
            return true;
        }
//        return true;
    }

}
