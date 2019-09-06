package com.test.web.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NullPointerException.class)
	public String nullpointHandler(Model model, NullPointerException ex)
	{
		model.addAttribute("msg","널 에러 발생");
		model.addAttribute("ex", ex);
		return "/exception/error";
	}
	
	@ExceptionHandler(ArithmeticException.class)
	public String ArithmeticException(Model model, ArithmeticException ex)
	{
		model.addAttribute("msg","널 에러 발생");
		model.addAttribute("ex", ex);
		return "/exception/error";
	}
	
	@ExceptionHandler(Exception.class)
	public String Exception(Model model, Exception ex)
	{
		model.addAttribute("msg","에러 발생");
		model.addAttribute("ex", ex);
		return "/exception/error";
	}
	
}
