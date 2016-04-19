package com.cpj.controller;

import com.cpj.openstack.Authenticate;
import com.cpj.openstack.KeyStone;
import com.cpj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.openstack4j.api.OSClient;
import org.openstack4j.api.types.Facing;
import org.openstack4j.model.identity.Access;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by chenpengjiang on 2016/3/7.
 */
@RequestMapping("/login/")
@Controller
public class LoginController {
    @Resource
    UserService userService;
    @Resource
    Authenticate authenticate;
    @Resource
    KeyStone keystone;

    public final static String hostIp = "10.0.0.11";
    @RequestMapping("tologin")
    public ModelAndView tologin(){
        return new ModelAndView("login/login");
    }
//    @RequestMapping("login.do")
//    public ModelAndView login(@RequestParam("userName")String userName, @RequestParam("password")String password, HttpSession httpSession){
//        int loginflag = userService.logincheck(userName,password);
//        ModelAndView modelAndView = new ModelAndView();
//        if(loginflag==0){
//            modelAndView.addObject("errmsg","该用户不存在！");
//            modelAndView.setViewName("login/login");
//        }else if(loginflag==1){
//            modelAndView.addObject("errmsg","密码错误！");
//            modelAndView.setViewName("login/login");
//        }else if(loginflag==200||loginflag==201){
//            modelAndView.setViewName("index/index");
//            if(loginflag==200){
//                OSClient os_admin = authenticate.logauthold(hostIp,userName,"admin",password,Facing.ADMIN);
//                Access access = os_admin.getAccess();
//                httpSession.setAttribute("access_admin",access);
//                httpSession.setAttribute("access_internal",access);
//            }else if(loginflag==201){
//                OSClient os_internal = authenticate.logauthold(hostIp,userName,"demo",password,Facing.INTERNAL);
//                Access access = os_internal.getAccess();
//                httpSession.setAttribute("access_internal",access);
//            }
//
//        }else{
//            modelAndView.addObject("errmsg","请重新登陆！");
//            modelAndView.setViewName("login/login");
//        }
//
//        return modelAndView;
//
//    }

    @RequestMapping(value = "/dologin")
    public String doLogin(HttpServletRequest request, Model model) {
        String msg = "";
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        System.out.println(userName);
        System.out.println(password);
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        token.setRememberMe(false);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession();
            if (subject.isAuthenticated()) {
                OSClient os_internal = authenticate.logauthold(hostIp,userName,"demo",password, Facing.INTERNAL);
                Access access = os_internal.getAccess();
                session.setAttribute("access_INTERNAL", access);
                return "index/index";
            } else {
                return "login";
            }
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            model.addAttribute("message", msg);
            System.out.println(msg);
        }
        return "login/login";
    }

    @RequestMapping("logout.do")
    public String logout(HttpSession httpSession){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();

        return "login/login";
    }

}
