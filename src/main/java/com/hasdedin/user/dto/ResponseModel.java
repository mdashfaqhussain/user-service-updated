package com.hasdedin.user.dto;

import lombok.Data;

@Data
public class ResponseModel {
	
	private int statuscode;
	private String status;
	private String message;
	private Object data;


}
