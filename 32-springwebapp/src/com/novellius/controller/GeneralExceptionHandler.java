package com.novellius.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex){
		System.out.println(ex.toString());
		return "error";
	}

//	@ExceptionHandler(IOException.class)
//	public String handleIOException(IOException ex){
//		System.out.println(ex.toString());
//		return "error";
//	}

	
	
}
