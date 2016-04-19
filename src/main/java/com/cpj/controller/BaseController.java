package com.cpj.controller;

import com.cpj.pojo.SecUser;
import com.cpj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.identity.Access;
import org.openstack4j.openstack.OSFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenpengjiang on 2016/4/18.
 */
public class BaseController {
    @Autowired
    UserService userService;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    /**
     * 根据用户名得到用户信息
     * @return
     */
    public SecUser getCurrentUser(){
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        SecUser user = (SecUser)userService.getUserByname(userName);
        return user;

    }

    /**
     * 获取当前登录用户id
     * @return
     */
    public int getUserId() {
        return this.getCurrentUser().getUserId();
    }

    /**
     * 获取当前登录用户名
     * @return
     */
    public String getStaffName() {
        return this.getCurrentUser().getUserName();
    }

    /**
     * 获取当前登录Project
     * @return
     */
    public String getProjectName() {
        return this.getCurrentUser().getProjectname();
    }

    /**
     * 获取当前登录Project
     * @return
     */
    public String getRoleName() {
        return this.getCurrentUser().getRolename();
    }

    /**
     *获取普通用户认证信息
     * @return
     */
    public OSClient GetOs_INTERNAL(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        Access access = (Access)session.getAttribute("access_INTERNAL");
        return OSFactory.clientFromAccess(access);

    }
    /**
     *获取管理用户认证信息
     * @return
     */
    public OSClient GetOs_ADMIN(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        Access access = (Access)session.getAttribute("access_ADMIN");
        return OSFactory.clientFromAccess(access);

    }





}
