package com.cpj.util;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by chenpengjiang on 2016/4/11.
 */
public class LoginFilter implements Filter {

    /** 要检查的 session 的名称 */
    private String sessionKey;

    /** 需要排除（不拦截）的URL的正则表达式 */
    private Pattern excepUrlPattern;

    /** 检查不通过时，转发的URL */
    private String forwardUrl;

    public void init(FilterConfig cfg) throws ServletException {
        sessionKey = cfg.getInitParameter("sessionKey");
        String excepUrlRegex = cfg.getInitParameter("excepUrlRegex");
        if (!StringUtils.isBlank(excepUrlRegex)) {
            excepUrlPattern = Pattern.compile(excepUrlRegex);
        }

        forwardUrl = cfg.getInitParameter("forwardUrl");

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 如果 sessionKey 为空，则直接放行
        if (StringUtils.isEmpty(sessionKey)) {filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();

        // 如果请求的路径与forwardUrl相同，或请求的路径是排除的URL时，则直接放行
        if (servletPath.equals(forwardUrl) || excepUrlPattern.matcher(servletPath).matches()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Object sessionObj = request.getSession().getAttribute(sessionKey);

        if (sessionObj == null) {
            String contextPath = request.getContextPath();
            request.getRequestDispatcher(forwardUrl).forward(request,response);
            filterChain.doFilter(request, response);

        }else {

            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    public void destroy() {

    }
}
