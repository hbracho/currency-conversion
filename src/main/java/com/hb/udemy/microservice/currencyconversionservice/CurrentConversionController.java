package com.hb.udemy.microservice.currencyconversionservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentConversionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, 
			@PathVariable String to, 
			@PathVariable BigDecimal quantity) {
		logger.info("Emitted quantity=1001 messages during the last durationInMs=93180 ms for customer scope=prod30");
		//return new CurrencyConversionBean(1L, from, to, quantity, BigDecimal.ONE, 8080);
		CurrencyConversionBean response = null;
		try {
			response = proxy.retrieveExchangeValue(from, to);
			logger.info("{}", response);
			return new CurrencyConversionBean(response.getId(), from, to, response.getConversionValue(), quantity,
					quantity.multiply(response.getConversionValue()), response.getPort());
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			logger.error("Errror during execution in the request",e.getCause());
			return new CurrencyConversionBean();
		}

		

	}
}
