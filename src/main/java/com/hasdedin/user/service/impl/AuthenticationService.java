package com.hasdedin.user.service.impl;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hasdedin.user.config.CustomUserDetailService;
import com.hasdedin.user.config.CustomUserDetails;
import com.hasdedin.user.config.exception.UserAlreadyExistsException;
import com.hasdedin.user.constants.ProjectContants;
import com.hasdedin.user.dto.LoginUserdto;
import com.hasdedin.user.dto.RegisterUserdto;
import com.hasdedin.user.dto.ResponseModel;
import com.hasdedin.user.entity.BudgetUser;
import com.hasdedin.user.repository.IUserRepository;
import com.hasdedin.user.service.IAuthenticationService;
import com.hasdedin.user.utility.Mapper;
import com.hasdedin.user.utility.ResponseUtility;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AuthenticationService implements IAuthenticationService {
	
	
	@Autowired
	private IUserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	CustomUserDetailService customUserDetailService;

	
	@Autowired
	private ResponseUtility responseUtility;
	
	@Autowired
	private JwtService jwtService;
	


	

	@Override
	public ResponseEntity<ResponseModel> saveUser(RegisterUserdto registerUserdto) {
		
	    try {
	    	
	        if(!repository.existsByName(registerUserdto.getName())) {
	        	
	            BudgetUser newuser = Mapper.convertToBudgetUser(registerUserdto);
	           
	            
	            newuser.setPassword(encoder.encode(registerUserdto.getPassword()));
	           
	            newuser.setRoles("USER");
	            repository.save(newuser);
	           
	            ResponseModel model = new ResponseModel();
	            model.setStatuscode(ProjectContants.USER_CREATED_STATUS_CODE);
	            model.setStatus(ProjectContants.USER_CREATED_MESSAGE);
	            model.setMessage(ProjectContants.USER_CREATED_MESSAGE);
	            
	            return responseUtility.createResponse(model);
	        } else {
	            // If user already exists, return appropriate error response
	            ResponseModel errorModel = new ResponseModel();
	            errorModel.setStatuscode(ProjectContants.USER_ALREADY_EXISTS_STATUS_CODE);
	            errorModel.setStatus(ProjectContants.USER_ALREADY_EXISTS_MESSAGE);
	            errorModel.setMessage(ProjectContants.USER_ALREADY_EXISTS_MESSAGE);
	            
	          
	            return responseUtility.createResponse(errorModel);
	        }
	    } catch (Exception e) {
	     
	        ResponseModel errorModel = new ResponseModel();
	        errorModel.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        errorModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	        errorModel.setMessage("An error occurred while processing the request.");
	        
	        return responseUtility.createResponse(errorModel);
	    }
	
	}

	@Override
	public ResponseEntity<ResponseModel> loginUser(LoginUserdto loginUserdto) {
		UserDetails userDetails = customUserDetailService.loadUserByUsername(loginUserdto.getName());
		Integer userId = ((CustomUserDetails) userDetails).getUserId();
	    String token = jwtService.generateToken(userId,loginUserdto.getName(), userDetails.getAuthorities());
		ResponseModel model = new ResponseModel();
		model.setData(token);
		model.setStatus(ProjectContants.USER_LOGIN_STATUS);
		model.setMessage(ProjectContants.USER_LOGIN_MESSAGE);
		model.setStatuscode(ProjectContants.USER_LOGIN_STATUS_CODE);
		
		return responseUtility.createResponse(model);
	}

}
