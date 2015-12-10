/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jittagornp.profile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jittagornp
 */
@WebFilter("/*")
public class CacheFilter implements Filter {

    private String etag;
    private long lastModified;
    private long expires = 0;
    private final int maxAge = 1000 * 60 * 30;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    private void resetExpires() {
        long current = System.currentTimeMillis();
        if (current > expires) {
            etag = UUID.randomUUID().toString();
            lastModified = current;
            expires = current + maxAge;
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        resetExpires();

        response.addHeader("etag", etag);
        response.addDateHeader("last-modified", lastModified);
        response.addDateHeader("expires", expires);
        response.addHeader("cache-control", "public, max-age=" + maxAge);

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }

}
