package com.hb.udemy.microservice.currencyconversionservice.logger;

public class Header {

	private Header() {
		super();
	}

    // HEADER REQUIRED
	public static final String X_TXREF = "X-txRef";
	public static final String X_CMREF = "X-cmRef";
	public static final String X_CHREF = "X-chRef";
	public static final String X_COUNTRY = "X-country";
	public static final String X_COMMERCE = "X-commerce";
	public static final String X_BUSINESSUNIT = "X-businessUnit";
	public static final String X_TENANTKEY = "X-tenantKey";
	public static final String X_CMDATETIME = "X-cmDateTime";
	public static final String X_ENVIRONMENT = "X-environment";	
	
    // HEADER OPTIONAL
	public static final String X_RHSREF = "X-rhsRef";  //with default value if header no present
	public static final String X_USRTX = "X-usrTx";  //with default value if header no present
	public static final String X_PRREF = "X-prRef";
	public static final String X_USER_AGENT = "X-user-Agent";
	public static final String X_SYSTEMDEVICE = "X-systemDevice"; //with default value if header no present
	public static final String X_SYSTEMVERSIONDEVICE = "X-systemVersionDevice"; //with default value if header no present
	
	// HEADER LOGGER
	public static final String X_SRVREF = "X-srvRef";
	public static final String X_NODEREF = "X-nodeRef"; 

}
