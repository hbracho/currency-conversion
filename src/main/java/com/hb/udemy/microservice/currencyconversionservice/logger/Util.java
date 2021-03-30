package com.hb.udemy.microservice.currencyconversionservice.logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Util {

	private static final Logger logger = LoggerFactory.getLogger(Util.class);

	
	public static Optional<HttpServletRequest> getCurrentHttpRequest() {
	    return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
	        .filter(requestAttributes -> ServletRequestAttributes.class.isAssignableFrom(requestAttributes.getClass()))
	        .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
	        .map(ServletRequestAttributes::getRequest);
	}
	
	public static String getHost() 
    {
    	String host = null;
		try 
		{
			InetAddress iAddress = InetAddress.getLocalHost();
			host = iAddress.getHostName();
		} 
		catch (UnknownHostException e) 
		{
			logger.error("Error get Host Node");
		}
		return host;
    }	
	
}
