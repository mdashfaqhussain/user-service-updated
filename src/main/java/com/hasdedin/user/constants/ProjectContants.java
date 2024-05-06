package com.hasdedin.user.constants;

import org.springframework.http.HttpStatus;

public class ProjectContants {
	
	public static final int USER_ALREADY_EXISTS_STATUS_CODE = HttpStatus.CONFLICT.value();
    public static final String USER_ALREADY_EXISTS_MESSAGE = "User with this email already exists";
    public static final String USER_ALREADY_EXISTS_STATUS = "Conflict";
    
    
    
    
    public static final int USER_CREATED_STATUS_CODE= HttpStatus.CREATED.value();
    public static final String USER_CREATED_MESSAGE="User created successully";
    public static final String USER_CREATED_STATUS= HttpStatus.CREATED.toString();
    
    
    
    public static final int USER_LOGIN_STATUS_CODE= HttpStatus.OK.value();
    public static final String USER_LOGIN_MESSAGE="Token generated";
    public static final String USER_LOGIN_STATUS= HttpStatus.OK.toString();
    		

}
