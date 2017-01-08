package com.util;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xuxiaohe on 2017/1/3.
 */
public class CommonsMultipartResolverPhhc extends CommonsMultipartResolver {
    @Override
    public boolean isMultipart(HttpServletRequest request) {
        String url = request.getRequestURI();
        if (url!=null && url.contains("controller")) {
            return false;
        } else {
            return super.isMultipart(request);
        }
    }
}
