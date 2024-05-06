package com.hasdedin.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hasdedin.user.dto.LoginUserdto;
import com.hasdedin.user.dto.RegisterUserdto;
import com.hasdedin.user.dto.ResponseModel;
import com.hasdedin.user.service.IAuthenticationService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	IAuthenticationService authenticationService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseModel> register(@Valid @RequestBody RegisterUserdto registerUserdto){
		log.info("Inside controller"+registerUserdto.toString());
		return authenticationService.saveUser(registerUserdto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseModel> login(@Valid @RequestBody LoginUserdto loginUserdto){
		return authenticationService.loginUser(loginUserdto);
	}
	
	@GetMapping("/test")
    public List<String> getStringList() {
		
		log.info("Inside stringList Method");
        List<String> stringList = new ArrayList<>();
        stringList.add("String 1");
        stringList.add("String 2");
        stringList.add("String 3");
        return stringList;
    }
}
