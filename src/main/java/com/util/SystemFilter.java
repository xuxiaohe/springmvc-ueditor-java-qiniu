package com.util;


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA. User: Ambitor springMVC拦截器 判断session中用户是否过期 Date:
 * 13-6-27 Time: 下午7:31 To change this template use File | Settings | File
 * Templates.
 *
 * @author xurui
 */
@Component("SystemFilter")
public class SystemFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
            throw new ServletException("OncePerRequestFilter just supports HTTP requests");
        }
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(true);
        String name=(String) session.getAttribute("name");
        StringBuffer url = httpRequest.getRequestURL();
        RestTemplate restTemplate = new RestTemplate();
        //System.out.println("================="+session.getAttribute("name"));
        if(StringUtil.isBlank(name)){
            wrapper.sendRedirect("/mineproject/login");
            return;
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

    }


    /**
     * 判断是否为Ajax请求
     *
     * @param request HttpServletRequest
     * @return 是true, 否false
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/api");
        // String requestType = request.getHeader("X-Requested-With");
        // return requestType != null && requestType.equals("XMLHttpRequest");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /* 如果需要注入，请取消注释 */
        //System.out.println("===========");
    }

    @Override
    public void destroy() {
        // To change body of implemented methods use File | Settings | File
        // Templates.
    }

}
