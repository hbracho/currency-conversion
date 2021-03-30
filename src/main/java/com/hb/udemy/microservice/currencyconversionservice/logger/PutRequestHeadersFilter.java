package com.hb.udemy.microservice.currencyconversionservice.logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Order(-1)
@Component
public class PutRequestHeadersFilter implements Filter  {
	
    private Logger logger = LoggerFactory.getLogger(PutRequestHeadersFilter.class);
    
    @Autowired
    private Environment env;
    
    @Autowired
    private ContextLogger envCiam;
    
	/*
	 * @Autowired private AppInfo appInfo;
	 */
    
    private static final String OPERATION_ID = "OPERATION_ID";
    
	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
    	logger.info("Initializing filter :{}", this);
	}
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
        	UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
            HttpServletRequest req = (HttpServletRequest) request;
            ThreadContext.put("ATTRIBUTES", "{\"app_name\":\"developerportal\", \"channel\":\"web\"}");
            chain.doFilter(request, response);
    }
       
	@Override
	public void destroy() {
		logger.warn("Destructing filter :{}", this);
	}
	
}