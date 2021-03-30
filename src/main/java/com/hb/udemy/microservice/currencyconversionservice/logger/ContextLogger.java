package com.hb.udemy.microservice.currencyconversionservice.logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ContextLogger {
	@Autowired
	private Environment env;

	final Logger logger = (Logger) LogManager.getLogger();

	private static final String HTTP_STATUS = "HTTP_STATUS";
	private static final String TIME_RESPONSE = "TIME_RESPONSE";
	private static final String ATTEMPT = "ATTEMPT";

	public  void initNewLoggerContext(Map<String, String> newMap)
	{
		for (Map.Entry<String, String> entry : newMap.entrySet())
		{
			String key = entry.getKey();
			String value = entry.getValue();
			ThreadContext.put(key, value);
		}
	}

	public void upgradeContextLogger(String httpMessageType, String layer, String srvRef, String httpStatus, Long timeElapsed, Integer attempt)
	{
		Optional<HttpServletRequest> rr = Util.getCurrentHttpRequest();
		if (rr.isPresent()) {
			//HeaderMapRequestWrapper rWrapper = (HeaderMapRequestWrapper) rr.get();
			//rWrapper.addHeader("HTTP_MESSAGE_TYPE", httpMessageType);
			if(!StringUtils.isEmpty(layer)) {
				//rWrapper.addHeader("LAYER", layer);
				ThreadContext.put("LAYER", layer);
			}

			ThreadContext.put("HTTP_MESSAGE_TYPE", httpMessageType);

			String xSrvref = Header.X_SRVREF.toUpperCase().replace("-", "_");
			if(!StringUtils.isEmpty(srvRef))
			{
				//rWrapper.removeAttribute(xSrvref);
				//rWrapper.addHeader(xSrvref, srvRef);
				ThreadContext.put(xSrvref, srvRef);
			}
			else
			{
				UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
				//rWrapper.addHeader(xSrvref, env.getProperty("application.name")+builder.buildAndExpand().getPath());
				ThreadContext.put(xSrvref, env.getProperty("application.name")+builder.buildAndExpand().getPath());
			}
			if(!StringUtils.isEmpty(httpStatus))
			{
				//rWrapper.removeAttribute(HTTP_STATUS);
				//rWrapper.addHeader(HTTP_STATUS, httpStatus);
				ThreadContext.put(HTTP_STATUS, httpStatus);
			}
			else
			{
				//rWrapper.removeAttribute(HTTP_STATUS);
				//rWrapper.addHeader(HTTP_STATUS, StringUtils.EMPTY);
				ThreadContext.put(HTTP_STATUS, StringUtils.EMPTY);
			}
			if(timeElapsed != null){
				//rWrapper.removeAttribute(TIME_RESPONSE);
				//rWrapper.addHeader(TIME_RESPONSE, String.format("%s", String.valueOf(timeElapsed)));
				ThreadContext.put(TIME_RESPONSE, String.format("%s", String.valueOf(timeElapsed)));
			}else
			{
				//rWrapper.removeAttribute(TIME_RESPONSE);
				//rWrapper.addHeader(TIME_RESPONSE, NumberUtils.INTEGER_ZERO.toString());
				ThreadContext.put(TIME_RESPONSE, NumberUtils.INTEGER_ZERO.toString());
			}

			if(attempt != null)
			{
				//rWrapper.removeAttribute(ATTEMPT);
				//rWrapper.addHeader(ATTEMPT, attempt.toString());
				ThreadContext.put(ATTEMPT, attempt.toString());
			}else{
				//rWrapper.removeAttribute(ATTEMPT);
				//rWrapper.addHeader(ATTEMPT, "null");
				ThreadContext.put(ATTEMPT, "null");
			}
			//Map<String, String> allMap = rWrapper.getHeaderMap();
			Map<String, String> allMap = new HashMap<>();
			this.initNewLoggerContext(allMap);
		}
	}

	public void upgradeContextLogger(String httpMessageType, String layer) {
		upgradeContextLogger(httpMessageType, layer, null, null, null, null);
	}

	public void upgradeContextLogger(String httpMessageType, String layer, String srvRef, String httpStatus){
	upgradeContextLogger(httpMessageType, layer, srvRef, httpStatus, null, null);
}

	public void upgradeContextLogger(String httpMessageType, String layer, String httpStatus) {
		upgradeContextLogger(httpMessageType, layer, null, httpStatus, null, null);
	}

	public void upgradeContextLogger(String httpMessageType, String layer, String srvRef, String httpStatus, Long timeElapsed){
		upgradeContextLogger(httpMessageType, layer, srvRef,  httpStatus,  timeElapsed, null);
	}
	public void upgradeContextLoggerWithAttempt(String httpMessageType, String layer, String srvRef, String httpStatus, Integer attempt){
		upgradeContextLogger(httpMessageType, layer, srvRef, httpStatus, null, attempt);
	}

	@SuppressWarnings("unchecked")
	public void printResultBackend(Object obj, long timeElapsed, String srvRef, String method) 
	{
		ResponseEntity<?> responseVU = (ResponseEntity<?>) obj;
		this.upgradeContextLogger(
				"RESPONSE",
				"",
				"[" + method +"] " + srvRef, String.valueOf(responseVU.getStatusCode().value()),
				timeElapsed,
				null);
		if(responseVU.getBody()!=null) {
			logger.info("hola mundoa");	
		} else {
			logger.info("hola mundoa");	
		}

	}		
}